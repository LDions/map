package com.ruowei.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.config.ApplicationProperties;
import com.ruowei.domain.*;
import com.ruowei.repository.*;
import com.ruowei.security.UserModel;
import com.ruowei.security.jwt.JWTFilter;
import com.ruowei.service.dto.*;
import com.ruowei.service.enumeration.FunctionEnum;
import com.ruowei.service.enumeration.OperationTypeEnum;
import com.ruowei.util.BeanUtil;
import com.ruowei.util.CarbonAccountingReport;
import com.ruowei.util.DateUtil;
import com.ruowei.web.rest.dto.SewDetailsDTO;
import com.ruowei.web.rest.dto.SewEmiDetailDTO;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.CarbonEmiMonthVM;
import com.ruowei.web.rest.vm.CarbonEmiQM;
import com.ruowei.web.rest.vm.SewEmiAccountVM;
import com.ruowei.web.rest.vm.SewEmiVM;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static com.ruowei.config.Constants.*;

@Service
@Slf4j
public class SewEmiService {

    private final EmiFactorRepository emiFactorRepository;
    private final ObjectMapper objectMapper;
    private final ApplicationProperties applicationProperties;
    private final SewEmiRepository sewEmiRepository;
    private final EmiDataRepository emiDataRepository;
    private final SewProcessRepository sewProcessRepository;
    private final SewSluRepository sewSluRepository;
    private final OtherIndexRepository otherIndexRepository;
    private final SewPotRepository sewPotRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private QSewEmi qSewEmi = QSewEmi.sewEmi;

    public SewEmiService(EmiFactorRepository emiFactorRepository, ObjectMapper objectMapper, ApplicationProperties applicationProperties, SewEmiRepository sewEmiRepository, EmiDataRepository emiDataRepository, SewProcessRepository sewProcessRepository, SewSluRepository sewSluRepository, OtherIndexRepository otherIndexRepository, SewPotRepository sewPotRepository, JPAQueryFactory jpaQueryFactory) {
        this.emiFactorRepository = emiFactorRepository;
        this.objectMapper = objectMapper;
        this.applicationProperties = applicationProperties;
        this.sewEmiRepository = sewEmiRepository;
        this.emiDataRepository = emiDataRepository;
        this.sewProcessRepository = sewProcessRepository;
        this.sewSluRepository = sewSluRepository;
        this.otherIndexRepository = otherIndexRepository;
        this.sewPotRepository = sewPotRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    /**
     * 计算各设施耗电量之和
     *
     * @param vm
     * @return
     */
    public BigDecimal calculateSumOfEachPower(SewEmiAccountVM vm) {
        BigDecimal sum = new BigDecimal(0);
        if (vm.getInPumpPow() != null) {
            sum = sum.add(vm.getInPumpPow());
        }
        if (vm.getBlowerPow() != null) {
            sum = sum.add(vm.getBlowerPow());
        }
        if (vm.getRetSluPumpPow() != null) {
            sum = sum.add(vm.getRetSluPumpPow());
        }
        if (vm.getSluTreatPow() != null) {
            sum = sum.add(vm.getSluTreatPow());
        }
        if (vm.getDisinfectPow() != null) {
            sum = sum.add(vm.getDisinfectPow());
        }
        if (vm.getFacilityPow() != null) {
            sum = sum.add(vm.getFacilityPow());
        }
        if (vm.getOtherPow() != null) {
            sum = sum.add(vm.getOtherPow());
        }
        return sum;
    }

    /**
     * 计算某种药剂各投加量之和
     *
     * @param vm
     * @return
     */
    public BigDecimal calculateSumOfEachDosage(SewEmiAccountVM.SewPotVM vm) {
        BigDecimal sum = new BigDecimal(0);

        return sum;
    }

    /**
     * 污水碳排放输入数据和排放因子参数封装
     *
     * @param vm
     * @return
     */
    public WaterCarbonEmissionEnterDTO packageParametersOfAccounting(SewEmiAccountVM vm) {
        EmiFactor emiFactor = emiFactorRepository.findFirstByProjectCodeOrderByModifyDateDesc(SEW_TREAT).orElseThrow(() -> new BadRequestProblem("核算失败", "未找到碳排放因子"));
        if (StringUtils.isBlank(emiFactor.getContent())) {
            throw new BadRequestProblem("核算失败", "未找到碳排放因子");
        }
        try {
            // 解析碳排放因子数据信息
            SewEmiFactorDTO sewEmiFactorDTO = objectMapper.readValue(emiFactor.getContent(), SewEmiFactorDTO.class);
            // 创建碳排放核算输入对象
            WaterCarbonEmissionEnterDTO enterDTO = new WaterCarbonEmissionEnterDTO();
            // 封装碳排放因子版本号
            enterDTO.setFactorVersionNum(emiFactor.getVersionNum());
            // 封装工艺水质信息
            List<WaterCarbonEmissionEnterDTO.WaterQualityDTO> waterQualityDTOList = new ArrayList<>();
            for (SewEmiAccountVM.SewProcessVM sewProcessVm : vm.getSewProcesss()) {
                if (StringUtils.isBlank(sewProcessVm.getProcessTypeCode())) {
                    throw new BadRequestProblem("核算失败", "工艺类型编码不能为空");
                }
                if (sewEmiFactorDTO.getProcessTypeNi().get(sewProcessVm.getProcessTypeCode()) == null) {
                    throw new BadRequestProblem("核算失败", "不存在" + sewProcessVm.getProcessTypeName() + "工艺");
                }
                WaterCarbonEmissionEnterDTO.WaterQualityDTO waterQualityDTO = new WaterCarbonEmissionEnterDTO.WaterQualityDTO(
                    sewProcessVm.getDailyScale(),
                    new BigDecimal(sewProcessVm.getOperatingDays()),
                    sewEmiFactorDTO.getProcessTypeNi().get(sewProcessVm.getProcessTypeCode()).getValue(),
                    sewProcessVm.getInFlow(),
                    sewProcessVm.getInCod(),
                    sewProcessVm.getInAmmonia(),
                    sewProcessVm.getInTp(),
                    sewProcessVm.getInTn() != null ? sewProcessVm.getInTn() : new BigDecimal(0),
                    sewProcessVm.getOutFlow(),
                    sewProcessVm.getOutCod(),
                    sewProcessVm.getOutAmmonia(),
                    sewProcessVm.getOutTp(),
                    sewProcessVm.getOutTn() != null ? sewProcessVm.getOutTn() : new BigDecimal(0)
                );
                waterQualityDTOList.add(waterQualityDTO);
            }
            enterDTO.setWaterQualityDTOList(waterQualityDTOList);
            // 封装电量信息
            WaterCarbonEmissionEnterDTO.ElectricityDTO electricityDTO = new WaterCarbonEmissionEnterDTO.ElectricityDTO(
                vm.getTotalPow(),
                vm.getInPumpPow() != null ? vm.getInPumpPow() : new BigDecimal(0),
                vm.getBlowerPow() != null ? vm.getBlowerPow() : new BigDecimal(0),
                vm.getRetSluPumpPow() != null ? vm.getRetSluPumpPow() : new BigDecimal(0),
                vm.getSluTreatPow() != null ? vm.getSluTreatPow() : new BigDecimal(0),
                vm.getDisinfectPow() != null ? vm.getDisinfectPow() : new BigDecimal(0),
                vm.getFacilityPow() != null ? vm.getFacilityPow() : new BigDecimal(0),
                vm.getOtherPow() != null ? vm.getOtherPow() : new BigDecimal(0),
                vm.getSluHandlePow() != null ? vm.getSluHandlePow() : new BigDecimal(0)
            );
            enterDTO.setElectricityDTO(electricityDTO);
            // TODO 日报表数据核算
            List<WaterCarbonEmissionEnterDTO.PharmacyDTO> pharmacyDTOList = new ArrayList<>();
            for (SewEmiAccountVM.SewPotVM sewPotVm : vm.getSewPots()) {

            }
            enterDTO.setPharmacyDTOList(pharmacyDTOList);
            // 封装碳减排信息
            WaterCarbonEmissionEnterDTO.CarbonNegativeDTO carbonNegativeDTO = new WaterCarbonEmissionEnterDTO.CarbonNegativeDTO(
                vm.getSolarPow() != null ? vm.getSolarPow() : new BigDecimal(0),
                vm.getHeatPumpHeat() != null ? vm.getHeatPumpHeat() : new BigDecimal(0),
                vm.getHeatPumpRefr() != null ? vm.getHeatPumpRefr() : new BigDecimal(0),
                vm.getHeatPumpHotHours() != null ? vm.getHeatPumpHotHours() : new BigDecimal(0),
                vm.getHeatPumpColdHours() != null ? vm.getHeatPumpColdHours() : new BigDecimal(0),
                vm.getThermoElec() != null ? vm.getThermoElec() : new BigDecimal(0),
                vm.getThermoEner() != null ? vm.getThermoEner() : new BigDecimal(0),
                vm.getToGirdPow() != null ? vm.getToGirdPow() : new BigDecimal(0),
                vm.getOtherEmiReduction() != null ? vm.getOtherEmiReduction() : new BigDecimal(0),
                vm.getWindPow() != null ? vm.getWindPow() : new BigDecimal(0),
                vm.getEcoComplexReduction() != null ? vm.getEcoComplexReduction() : new BigDecimal(0)
            );
            enterDTO.setCarbonNegativeDTO(carbonNegativeDTO);
            // 封装污泥处置信息
            enterDTO.setMSludge1(new BigDecimal(0));
            enterDTO.setWc1(new BigDecimal(60));
            enterDTO.setMSludge2(new BigDecimal(0));
            enterDTO.setWc2(new BigDecimal(60));
            enterDTO.setMSludge3(new BigDecimal(0));
            enterDTO.setWc3(new BigDecimal(60));
            for (SewEmiAccountVM.SewSluVM sewSluVm : vm.getSewSlus()) {

            }
            enterDTO.setOdeg1(new BigDecimal(0));
            enterDTO.setOdeg2(new BigDecimal(0));
            enterDTO.setOdeg3(new BigDecimal(0));
            enterDTO.setOdeg4(new BigDecimal(0));
            enterDTO.setOdeg5(new BigDecimal(0));
            enterDTO.setOdeg6(new BigDecimal(0));
            enterDTO.setOdeg7(new BigDecimal(0));
            enterDTO.setOdeg8(new BigDecimal(0));
            enterDTO.setOdeg9(new BigDecimal(0));
            for (SewEmiAccountVM.OtherIndexVM otherIndexVM : vm.getOtherIndexs()) {
                switch (otherIndexVM.getMethodCode()) {
                    case INRETURNFLOW:
                        enterDTO.setOdeg1(otherIndexVM.getIndexCapacity());
                        break;
                    case OUTRETURNFLOW:
                        enterDTO.setOdeg2(otherIndexVM.getIndexCapacity());
                        break;
                    case FIRSTMUD:
                        enterDTO.setOdeg3(otherIndexVM.getIndexCapacity());
                        break;
                    case SECONDMUD:
                        enterDTO.setOdeg4(otherIndexVM.getIndexCapacity());
                        break;
                    case REFLUX:
                        enterDTO.setOdeg5(otherIndexVM.getIndexCapacity());
                        break;
                    case CARADD:
                        enterDTO.setOdeg6(otherIndexVM.getIndexCapacity());
                        break;
                    case INRETURNPUMP:
                        enterDTO.setOdeg7(otherIndexVM.getIndexCapacity());
                        break;
                    case OUTRETURNPUMP:
                        enterDTO.setOdeg8(otherIndexVM.getIndexCapacity());
                        break;
                    case FANSTATE:
                        enterDTO.setOdeg9(otherIndexVM.getIndexCapacity());
                        break;
                    default:
                        throw new BadRequestProblem("核算失败", "不存在该指标");
                }
            }
            // 污泥处置是否为本厂管理
            enterDTO.setManagedBySelf(vm.getManagedBySelf());
            // 封装因子项
            enterDTO.setCfE(sewEmiFactorDTO.getProElecEmiFactor().get(vm.getProvinceCode()).getValue());
            enterDTO.setCfHp(sewEmiFactorDTO.getGasEmiFactor().getCfhp().getValue());
            enterDTO.setCfCo2(sewEmiFactorDTO.getGasEmiFactor().getCfco2().getValue());
            enterDTO.setCfCh4(sewEmiFactorDTO.getGasEmiFactor().getCfch4().getValue());
            enterDTO.setCfN2o(sewEmiFactorDTO.getGasEmiFactor().getCfn2o().getValue());
            enterDTO.setB0Bod(sewEmiFactorDTO.getSewTreatFactor().getB0bod().getValue());
            enterDTO.setB0Cod(sewEmiFactorDTO.getSewTreatFactor().getB0cod().getValue());
            enterDTO.setMcf(sewEmiFactorDTO.getSewTreatFactor().getMcf().getValue());
            enterDTO.setGama(sewEmiFactorDTO.getHeatPumpFactor().getY().getValue());
            enterDTO.setWc(sewEmiFactorDTO.getSluTreatFactor().getWc().getValue());
            enterDTO.setEfs1(sewEmiFactorDTO.getSluTreatFactor().getEfs1().getValue());
            enterDTO.setEfs2(sewEmiFactorDTO.getSluTreatFactor().getEfs2().getValue());
            enterDTO.setSTech3Doc(sewEmiFactorDTO.getSluTreatFactor().getDoc().getValue());
            enterDTO.setSTech3Docf(sewEmiFactorDTO.getSluTreatFactor().getDocf().getValue());
            enterDTO.setSTech3Mcf(sewEmiFactorDTO.getSluTreatFactor().getMcf().getValue());
            enterDTO.setSTech3F(sewEmiFactorDTO.getSluTreatFactor().getF().getValue());
            enterDTO.setSTech3Ox(sewEmiFactorDTO.getSluTreatFactor().getOx().getValue());
            enterDTO.setEfs3(sewEmiFactorDTO.getSluTreatFactor().getEfs3().getValue());
            enterDTO.setEfs4(sewEmiFactorDTO.getSluTreatFactor().getEfs4().getValue());
            return enterDTO;
        } catch (JsonProcessingException e) {
            throw new BadRequestProblem("核算失败", "获取碳排放因子失败");
        }
    }

    /**
     * 调用智能合约接口（现将数据保存在MySQL数据库中，此方法暂未用到）
     *
     * @param request
     * @param operationTypeEnum 操作类型
     * @param functionEnum      方法
     * @param requestArgs       请求体（json格式）
     * @return
     */
    public ChainCodeResponseDTO callTheSmartContractInterface(HttpServletRequest request,
                                                              OperationTypeEnum operationTypeEnum,
                                                              FunctionEnum functionEnum,
                                                              String requestArgs) {
        ChainCodeRequestDTO chainCodeRequestDTO = new ChainCodeRequestDTO();
        ChainCodeRequestDTO.Operation operation = new ChainCodeRequestDTO.Operation(
            applicationProperties.getBlockChain().getChaincodeId(),
            operationTypeEnum.getName(),
            "Cnsp:" + functionEnum.getName(),
            requestArgs
        );
        chainCodeRequestDTO.setChaincode_operation(operation);
        // 创建WebClient对象
        WebClient webClient = WebClient.builder()
            .baseUrl(applicationProperties.getBlockChain().getBaseUrl())
            .build();
        // 发起请求，获取响应体
        Mono<ChainCodeResponseDTO> mono = webClient
            .post()
            .uri("/v2/channels/{1}/appOperation", applicationProperties.getBlockChain().getChannelId())
            .contentType(MediaType.APPLICATION_JSON)
            .header(JWTFilter.AUTHORIZATION_HEADER, request.getHeader(JWTFilter.AUTHORIZATION_HEADER))
            .bodyValue(chainCodeRequestDTO)
            .retrieve()
            .bodyToMono(ChainCodeResponseDTO.class);
        return mono.block();
    }

    /**
     * 封装碳排放数据
     *
     * @param documentCode
     * @param userModel
     * @param vm
     * @param outputDTO
     * @param nowInstant
     * @return
     */
    public EmiDataDTO convertToEmiDataDTO(String documentCode,
                                          UserModel userModel,
                                          SewEmiAccountVM vm,
                                          WaterCarbonEmissionOutputDTO outputDTO,
                                          Instant nowInstant) {
        return new EmiDataDTO(
            documentCode,
            userModel.getEnterpriseId(),
            userModel.getEnterpriseName(),
            userModel.getUserId(),
            userModel.getNickName(),
            nowInstant,
            vm,
            outputDTO
        );
    }

    /**
     * 封装污水碳排放核算数据
     *
     * @param documentCode
     * @param userModel
     * @param vm
     * @param factorVersionNum
     * @param outputDTO
     * @param nowInstant
     * @return
     */
    public SewEmiDTO convertToSewEmiDTO(String documentCode,
                                        UserModel userModel,
                                        SewEmiAccountVM vm,
                                        String factorVersionNum,
                                        WaterCarbonEmissionOutputDTO outputDTO,
                                        Instant nowInstant) {
        return new SewEmiDTO(
            documentCode,
            vm.getEnterpriseId(),
            vm.getEnterpriseName(),
            userModel.getUserId(),
            userModel.getNickName(),
            nowInstant,
            vm.getProvinceCode(),
            vm.getProvinceName(),
            vm,
            factorVersionNum,
            outputDTO
        );
    }

    /**
     * 调用智能合约接口保存污水厂碳排放核算数据（现将数据保存在MySQL数据库中，此方法暂未用到）
     *
     * @param request
     * @param documentCode
     * @param userModel
     * @param vm
     * @param outputDTO
     */
    public void saveAccountingResult(HttpServletRequest request,
                                     String documentCode,
                                     UserModel userModel,
                                     SewEmiAccountVM vm,
                                     WaterCarbonEmissionEnterDTO enterDTO,
                                     WaterCarbonEmissionOutputDTO outputDTO,
                                     Instant nowInstant) {

        // 封装请求体
        List<Object> list = new ArrayList<>();
        EmiDataDTO emiDataDTO = convertToEmiDataDTO(documentCode, userModel, vm, outputDTO, nowInstant);
        list.add(emiDataDTO);
        SewEmiDTO sewEmiDTO = convertToSewEmiDTO(documentCode, userModel, vm, enterDTO.getFactorVersionNum(), outputDTO, nowInstant);
        list.add(sewEmiDTO);
        String requestArgs = "";
        try {
            requestArgs = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new BadRequestProblem("保存核算数据失败", "未能正确封装请求体");
        }
        if (StringUtils.isBlank(requestArgs)) {
            throw new BadRequestProblem("保存核算数据失败", "未能正确封装请求体");
        }
        // 调用智能合约接口
        ChainCodeResponseDTO responseDTO = callTheSmartContractInterface(
            request,
            OperationTypeEnum.INVOKE,
            FunctionEnum.UPSERT,
            Base64.getEncoder().encodeToString(requestArgs.getBytes(StandardCharsets.UTF_8))
        );
        if (responseDTO == null) {
            throw new BadRequestProblem("保存核算数据失败", "获取响应体失败");
        }
        if (responseDTO.getSuccess() == null || !responseDTO.getSuccess()) {
            throw new BadRequestProblem("保存核算数据失败", responseDTO.getMessage());
        }
    }

    /**
     * 保存碳排放核算信息
     *
     * @param documentCode
     * @param userModel
     * @param vm
     * @param enterDTO
     * @param outputDTO
     * @param nowInstant
     */
    public void saveAccountingResultToMySQL(String documentCode, UserModel userModel, SewEmiAccountVM vm, WaterCarbonEmissionEnterDTO enterDTO, WaterCarbonEmissionOutputDTO outputDTO, Instant nowInstant) {
        EmiData emiData = new EmiData()
            .documentCode(documentCode)
            .enterpriseId(vm.getEnterpriseId())
            .enterpriseName(vm.getEnterpriseName())
            .reporterId(userModel.getUserId())
            .reporterName(userModel.getNickName())
            .reportTime(nowInstant)
            .accYear(vm.getAccYear())
            .accMonth(vm.getAccMonth())
            .accTime(vm.getAccYear().concat(vm.getAccMonth()))
            .industryCode(vm.getIndustryCode())
            .industryName(vm.getIndustryName())
            .carbonEmi(outputDTO.getC())
            .carbonDirEmi(outputDTO.getCDirect())
            .carbonIndirEmi(outputDTO.getCIndirect())
            .carbonRed(outputDTO.getCReduction());
        SewEmi sewEmi = new SewEmi()
            .documentCode(documentCode)
            .enterpriseId(vm.getEnterpriseId())
            .enterpriseName(vm.getEnterpriseName())
            .reporterId(userModel.getUserId())
            .reporterName(userModel.getNickName())
            .reportTime(nowInstant)
            .provinceCode(vm.getProvinceCode())
            .provinceName(vm.getProvinceName())
            .accYear(vm.getAccYear())
            .accMonth(vm.getAccMonth())
            .accTime(vm.getAccYear().concat(vm.getAccMonth()))
            .totalPow(vm.getTotalPow())
            .inPumpPow(vm.getInPumpPow())
            .blowerPow(vm.getBlowerPow())
            .retSluPumpPow(vm.getRetSluPumpPow())
            .sluTreatPow(vm.getSluTreatPow())
            .disinfectPow(vm.getDisinfectPow())
            .facilityPow(vm.getFacilityPow())
            .otherPow(vm.getOtherPow())
            .sluHandlePow(vm.getSluHandlePow())
            .solarPow(vm.getSolarPow())
            .heatPumpHeat(vm.getHeatPumpHeat())
            .heatPumpRefr(vm.getHeatPumpRefr())
            .heatPumpHotHours(vm.getHeatPumpHotHours())
            .heatPumpColdHours(vm.getHeatPumpColdHours())
            .thermoElec(vm.getThermoElec())
            .thermoEner(vm.getThermoEner())
            .toGirdPow(vm.getToGirdPow())
            .otherText(vm.getOtherText())
            .otherEmiReduction(vm.getOtherEmiReduction())
            .windPow(vm.getWindPow())
            .ecoComplexReduction(vm.getEcoComplexReduction())
            .managedBySelf(vm.getManagedBySelf())
            .sluMoistureAfterTreat(vm.getSluMoistureAfterTreat())
            .factorVersionNum(enterDTO.getFactorVersionNum())
            .level1PotEmi(outputDTO.getC11())
            .level2PotEmi(outputDTO.getC12())
            .level3PotEmi(outputDTO.getC13())
            .sluTreatPotEmi(outputDTO.getC14())
            .totalPotEmi(outputDTO.getTotalC1())
            .inletPumpPowEmi(outputDTO.getC21())
            .blowerPowEmi(outputDTO.getC22())
            .retSluPumpPowEmi(outputDTO.getC23())
            .sluTreatPowEmi(outputDTO.getC24())
            .facilityPowEmi(outputDTO.getC25())
            .disinfectPowEmi(outputDTO.getC26())
            .otherPowEmi(outputDTO.getC27())
            .totalPowEmi(outputDTO.getTotalC2())
            .sewTreatCh4Emi(outputDTO.getC31())
            .sewTreatN2oEmi(outputDTO.getC32())
            .totalSewTreatEmi(outputDTO.getTotalC3())
            .sluHandleCh4Emi(outputDTO.getC41())
            .sluHandleN2oEmi(outputDTO.getC42())
            .totalSluHandleDirEmi(outputDTO.getTotalC4())
            .sluHandlePotEmi(outputDTO.getC91())
            .sluHandlePowEmi(outputDTO.getC92())
            .totalSluHandleIndirEmi(outputDTO.getTotalC9())
            .solarPowRed(outputDTO.getTotalC5())
            .heatPumpRed(outputDTO.getTotalC6())
            .thermoElecRed(outputDTO.getTotalC7())
            .thermoEnerRed(outputDTO.getTotalC8())
            .otherEmiRed(outputDTO.getTotalC11())
            .windPowRed(outputDTO.getTotalC12())
            .ecoComplexRed(outputDTO.getTotalC13())
            .carbonRed(outputDTO.getCReduction())
            .carbonDirEmi(outputDTO.getCDirect())
            .carbonIndirEmi(outputDTO.getCIndirect())
            .carbonEmi(outputDTO.getC());
        List<SewProcess> sewProcessList = new ArrayList<>();
        for (SewEmiAccountVM.SewProcessVM processVm : vm.getSewProcesss()) {
            SewProcess sewProcess = new SewProcess()
                .documentCode(documentCode)
                .processTypeCode(processVm.getProcessTypeCode())
                .processTypeName(processVm.getProcessTypeName())
                .dailyScale(processVm.getDailyScale())
                .operatingDays(processVm.getOperatingDays())
                .inFlow(processVm.getInFlow())
                .inAmmonia(processVm.getInAmmonia())
                .inCod(processVm.getInCod())
                .inTn(processVm.getInTp())
                .inTp(processVm.getInTn())
                .inSs(processVm.getInSs())
                .outFlow(processVm.getOutFlow())
                .outAmmonia(processVm.getOutAmmonia())
                .outCod(processVm.getOutCod())
                .outTn(processVm.getOutTp())
                .outTp(processVm.getOutTn())
                .outSs(processVm.getOutSs())
                .anoxicPoolDo(processVm.getAnoxicPoolDo())
                .aerobicPoolDo(processVm.getAerobicPoolDo())
                .anoxicPoolDoOutNit(processVm.getAnoxicPoolDoOutNit())
                .aerobicPoolNit(processVm.getAerobicPoolNit())
                .dayTime(processVm.getDayTime())
                .craftCode(processVm.getCraftCode());
            sewProcessList.add(sewProcess);
        }
        List<SewPot> sewPotList = new ArrayList<>();
        for (SewEmiAccountVM.SewPotVM potVm : vm.getSewPots()) {
            SewPot sewPot = new SewPot()
                .documentCode(documentCode)
                .dayInPh(potVm.getDayInPh())
                .dayOutPh(potVm.getDayOutPh())
                .dayFirstMud(potVm.getDayFirstMud())
                .daySecondMud(potVm.getDaySecondMud())
                .dayReflux(potVm.getDayReflux())
                .dayCarAdd(potVm.getDayCarAdd())
                .dayAnaerobicPoolPh(potVm.getDayAnaerobicPoolPh())
                .dayAnaerobicPoolOrp(potVm.getDayAnaerobicPoolOrp())
                .dayAnaerobicPoolDo(potVm.getDayAnaerobicPoolDo())
                .dayAnaerobicPoolSour(potVm.getDayAnaerobicPoolSour())
                .dayAnaerobicPoolSv(potVm.getDayAnaerobicPoolSv())
                .dayAnaerobicPoolMlss(potVm.getDayAnaerobicPoolMlss())
                .dayAnaerobicPoolTemper(potVm.getDayAnaerobicPoolTemper())
                .dayAnoxicPoolPh(potVm.getDayAnoxicPoolPh())
                .dayAnoxicPoolOrp(potVm.getDayAnoxicPoolOrp())
                .dayAnoxicPoolDo(potVm.getDayAnoxicPoolDo())
                .dayAnoxicPoolSour(potVm.getDayAnoxicPoolSour())
                .dayAnoxicPoolSv(potVm.getDayAnoxicPoolSv())
                .dayAnoxicPoolMlss(potVm.getDayAnoxicPoolMlss())
                .dayAnoxicPoolTemper(potVm.getDayAnoxicPoolTemper())
                .dayAerobicPoolPh(potVm.getDayAerobicPoolPh())
                .dayAerobicPoolOrp(potVm.getDayAerobicPoolOrp())
                .dayAerobicPoolDo(potVm.getDayAerobicPoolDo())
                .dayAerobicPoolSour(potVm.getDayAerobicPoolSour())
                .dayAerobicPoolSv(potVm.getDayAerobicPoolSv())
                .dayAerobicPoolMlss(potVm.getDayAerobicPoolMlss())
                .dayAerobicPoolMlvss(potVm.getDayAerobicPoolMlvss())
                .dayAerobicPoolSvi(potVm.getDayAerobicPoolSvi())
                .dayAerobicPoolTemper(potVm.getDayAerobicPoolTemper())
                .dayTime(potVm.getDayTime());
            sewPotList.add(sewPot);
        }
        List<SewSlu> sewSluList = new ArrayList<>();
        for (SewEmiAccountVM.SewSluVM sluVm : vm.getSewSlus()) {
            SewSlu sewSlu = new SewSlu()
                .documentCode(documentCode)
                .methodCode(sluVm.getMethodCode())
                .methodName(sluVm.getMethodName())
                .assInFlow(sluVm.getInFlow())
                .assInAmmonia(sluVm.getAssInAmmonia())
                .assInCod(sluVm.getAssInCod())
                .assInTp(sluVm.getAssInTp())
                .assAnoxicPoolDoOutNit(sluVm.getAssAnoxicPoolDoOutNit())
                .assAerobicPoolDoOutNit(sluVm.getAssAerobicPoolDoOutNit())
                .assOutFlow(sluVm.getAssOutFlow())
                .assOutAmmonia(sluVm.getAssOutAmmonia())
                .assOutCod(sluVm.getAssOutCod())
                .assOutTn(sluVm.getAssOutTn())
                .assOutTp(sluVm.getAssOutTp())
                .dayTime(sluVm.getDayTime());
            sewSluList.add(sewSlu);
        }
        List<OtherIndex> otherIndexList = new ArrayList<>();
        for (SewEmiAccountVM.OtherIndexVM otherIndexVM : vm.getOtherIndexs()) {
            OtherIndex otherIndex = new OtherIndex()
                .documentCode(documentCode)
                .methodCode(otherIndexVM.getMethodCode())
                .methodName(otherIndexVM.getMethodName())
                .indexCapacity(otherIndexVM.getIndexCapacity());
            otherIndexList.add(otherIndex);
        }
        emiDataRepository.save(emiData);
        sewEmiRepository.save(sewEmi);
        sewProcessRepository.saveAll(sewProcessList);
        sewPotRepository.saveAll(sewPotList);
        sewSluRepository.saveAll(sewSluList);
        otherIndexRepository.saveAll(otherIndexList);
    }

    /**
     * 生成/重新生成 碳排放核算报告（异步）
     *
     * @param sewEmiDTO
     */
    @Async("taskExecutor")
    public void asyncGenerateReport(SewEmiDTO sewEmiDTO) {
        try {
            CarbonAccountingReport.MergePdf(sewEmiDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 生成/重新生成 碳排放核算报告（同步）
     *
     * @param sewEmiDTO
     */
    public void syncGenerateReport(SewEmiDTO sewEmiDTO) {
        try {
            CarbonAccountingReport.MergePdf(sewEmiDTO);
        } catch (Exception e) {
            throw new BadRequestProblem("生成报告失败", e.getMessage());
        }
    }

    /**
     * 根据单据号获取污水厂碳排放数据，并封装为SewEmiDetailDTO
     *
     * @param documentCode 单据号
     * @return
     */
    public SewEmiDetailDTO convertToSewEmiDetailDtoByDocumentCode(String documentCode) {
        /*SewEmi sewEmi = sewEmiRepository.findByDocumentCode(documentCode).orElseThrow(() -> new BadRequestProblem("查询详情失败", "该污水厂碳排放数据不存在"));*/
        SewEmiDetailDTO sewEmiDetailDTO = new SewEmiDetailDTO();

        /*BeanUtils.copyProperties(sewEmi, sewEmiDetailDTO, BeanUtil.getNullPropertyNames(sewEmi));*/
        List<SewProcess> sewProcessList = sewProcessRepository.findByDocumentCode(documentCode);
        List<SewEmiAccountVM.SewProcessVM> sewProcessVms = new ArrayList<>();
        for (SewProcess sewProcess : sewProcessList) {
            SewEmiAccountVM.SewProcessVM vm = new SewEmiAccountVM.SewProcessVM();
            BeanUtils.copyProperties(sewProcess, vm, BeanUtil.getNullPropertyNames(sewProcess));
            sewProcessVms.add(vm);
        }
        sewEmiDetailDTO.setSewProcesss(sewProcessVms);
        List<SewPot> sewPotList = sewPotRepository.findByDocumentCode(documentCode);
        List<SewEmiAccountVM.SewPotVM> sewPotVms = new ArrayList<>();
        for (SewPot sewPot : sewPotList) {
            SewEmiAccountVM.SewPotVM vm = new SewEmiAccountVM.SewPotVM();
            BeanUtils.copyProperties(sewPot, vm, BeanUtil.getNullPropertyNames(sewPot));
            sewPotVms.add(vm);
        }
        sewEmiDetailDTO.setSewPots(sewPotVms);
        List<SewSlu> sewSluList = sewSluRepository.findByDocumentCode(documentCode);
        List<SewEmiAccountVM.SewSluVM> sewSluVms = new ArrayList<>();
        for (SewSlu sewSlu : sewSluList) {
            SewEmiAccountVM.SewSluVM vm = new SewEmiAccountVM.SewSluVM();
            BeanUtils.copyProperties(sewSlu, vm, BeanUtil.getNullPropertyNames(sewSlu));
            sewSluVms.add(vm);
        }
        sewEmiDetailDTO.setSewSlus(sewSluVms);

        List<OtherIndex> otherIndexList = otherIndexRepository.findByDocumentCode(documentCode);
        List<SewEmiAccountVM.OtherIndexVM> otherIndexVMS = new ArrayList<>();
        for (OtherIndex otherIndex : otherIndexList) {
            SewEmiAccountVM.OtherIndexVM vm = new SewEmiAccountVM.OtherIndexVM();
            BeanUtils.copyProperties(otherIndex, vm, BeanUtil.getNullPropertyNames(otherIndex));
            otherIndexVMS.add(vm);
        }
        sewEmiDetailDTO.setOtherIndexs(otherIndexVMS);
        return sewEmiDetailDTO;
    }

    /**
     * 根据单据号获取污水厂碳排放数据，并封装为SewDetailDTO
     *
     * @param documentCode 单据号
     * @return
     */
    public List<SewEmiVM> convertToSewDetailDtoByDocumentCode(String documentCode) {
        /*SewEmi sewEmi = sewEmiRepository.findByDocumentCode(documentCode).orElseThrow(() -> new BadRequestProblem("查询详情失败", "该污水厂碳排放数据不存在"));*/

        List<SewEmiVM> emiVMS = new ArrayList<>();
        /*BeanUtils.copyProperties(sewEmi, sewEmiDetailDTO, BeanUtil.getNullPropertyNames(sewEmi));*/
        List<SewProcess> sewProcessList = sewProcessRepository.findByDocumentCode(documentCode);
        for (SewProcess sewProcess : sewProcessList) {
            SewEmiVM.SewProcessVM vm = new SewEmiVM.SewProcessVM();
            BeanUtils.copyProperties(sewProcess, vm, BeanUtil.getNullPropertyNames(sewProcess));
            emiVMS.add(vm);
        }
        List<SewPot> sewPotList = sewPotRepository.findByDocumentCode(documentCode);
        for (SewPot sewPot : sewPotList) {
            SewEmiVM.SewPotVM vm = new SewEmiVM.SewPotVM();
            BeanUtils.copyProperties(sewPot, vm, BeanUtil.getNullPropertyNames(sewPot));
            emiVMS.add(vm);
        }
        List<SewSlu> sewSluList = sewSluRepository.findByDocumentCode(documentCode);
        for (SewSlu sewSlu : sewSluList) {
            SewEmiVM.SewSluVM vm = new SewEmiVM.SewSluVM();
            BeanUtils.copyProperties(sewSlu, vm, BeanUtil.getNullPropertyNames(sewSlu));
            emiVMS.add(vm);
        }

        /*List<OtherIndex> otherIndexList = otherIndexRepository.findByDocumentCode(documentCode);
        List<SewEmiAccountVM.OtherIndexVM> otherIndexVMS = new ArrayList<>();
        for (OtherIndex otherIndex : otherIndexList) {
            SewEmiAccountVM.OtherIndexVM vm = new SewEmiAccountVM.OtherIndexVM();
            BeanUtils.copyProperties(otherIndex, vm, BeanUtil.getNullPropertyNames(otherIndex));
            otherIndexVMS.add(vm);
        }
        sewEmiDetailDTO.setOtherIndexs(otherIndexVMS);*/
        return emiVMS;
    }

    /**
     * 根据单据号获取污水厂碳排放数据，并封装为SewDetailDTO
     *
     * @param documentCode 单据号
     * @return
     */
    public void modificationByDocumentCode(String documentCode,SewDetailsDTO.SewProcessDTO sewProcessDTO) {

        for (SewProcess sew:sewProcessRepository.findByDocumentCode(documentCode)) {
            sew.setId(sewProcessDTO.getId());
            sew.setInAmmonia(sewProcessDTO.getInAmmonia());
            sew.setInCod(sewProcessDTO.getInCod());
            sew.setInFlow(sewProcessDTO.getInFlow());
            sew.setInSs(sewProcessDTO.getInSs());
            sew.setInTn(sewProcessDTO.getInTn());
            sew.setInTp(sewProcessDTO.getInTp());
            sew.setOutAmmonia(sewProcessDTO.getOutAmmonia());
            sew.setOutCod(sewProcessDTO.getOutCod());
            sew.setOutFlow(sewProcessDTO.getOutFlow());
            sew.setOutSs(sewProcessDTO.getOutSs());
            sew.setOutTn(sewProcessDTO.getOutTn());
            sew.setOutTp(sewProcessDTO.getOutTp());
            sewProcessRepository.save(sew);
        }
    }

    /**
     * 根据单据号获取污水厂碳排放数据，并封装为SewEmiDTO
     *
     * @param documentCode 单据号
     * @return
     */
    public SewEmiDTO convertToSewEmiDtoByDocumentCode(String documentCode) {
        SewEmi sewEmi = sewEmiRepository.findByDocumentCode(documentCode).orElseThrow(() -> new BadRequestProblem("查询详情失败", "该污水厂碳排放数据不存在"));
        SewEmiDTO sewEmiDTO = new SewEmiDTO();
        BeanUtils.copyProperties(sewEmi, sewEmiDTO, BeanUtil.getNullPropertyNames(sewEmi));
        List<SewProcess> sewProcessList = sewProcessRepository.findByDocumentCode(documentCode);
        List<SewEmiAccountVM.SewProcessVM> sewProcessVms = new ArrayList<>();
        for (SewProcess sewProcess : sewProcessList) {
            SewEmiAccountVM.SewProcessVM vm = new SewEmiAccountVM.SewProcessVM();
            BeanUtils.copyProperties(sewProcess, vm, BeanUtil.getNullPropertyNames(sewProcess));
            sewProcessVms.add(vm);
        }
        sewEmiDTO.setSewProcesss(sewProcessVms);
        List<SewPot> sewPotList = sewPotRepository.findByDocumentCode(documentCode);
        List<SewEmiAccountVM.SewPotVM> sewPotVms = new ArrayList<>();
        for (SewPot sewPot : sewPotList) {
            SewEmiAccountVM.SewPotVM vm = new SewEmiAccountVM.SewPotVM();
            BeanUtils.copyProperties(sewPot, vm, BeanUtil.getNullPropertyNames(sewPot));
            sewPotVms.add(vm);
        }
        sewEmiDTO.setSewPots(sewPotVms);
        List<SewSlu> sewSluList = sewSluRepository.findByDocumentCode(documentCode);
        List<SewEmiAccountVM.SewSluVM> sewSluVms = new ArrayList<>();
        for (SewSlu sewSlu : sewSluList) {
            SewEmiAccountVM.SewSluVM vm = new SewEmiAccountVM.SewSluVM();
            BeanUtils.copyProperties(sewSlu, vm, BeanUtil.getNullPropertyNames(sewSlu));
            sewSluVms.add(vm);
        }
        sewEmiDTO.setSewSlus(sewSluVms);
        List<OtherIndex> otherIndexList = otherIndexRepository.findByDocumentCode(documentCode);
        List<SewEmiAccountVM.OtherIndexVM> otherIndexVMS = new ArrayList<>();
        for (OtherIndex otherIndex : otherIndexList) {
            SewEmiAccountVM.OtherIndexVM vm = new SewEmiAccountVM.OtherIndexVM();
            BeanUtils.copyProperties(otherIndex, vm, BeanUtil.getNullPropertyNames(otherIndex));
            otherIndexVMS.add(vm);
        }
        sewEmiDTO.setOtherIndexs(otherIndexVMS);
        return sewEmiDTO;
    }

    /**
     * 封装碳排放核算列表数据查询条件
     *
     * @param qm
     * @param qEmiData
     * @param userModel
     * @return
     */
    public BooleanBuilder convertCarbonEmiQuery(CarbonEmiQM qm, QEmiData qEmiData, UserModel userModel) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (StringUtils.isNotBlank(qm.getDocumentCode())) {
            booleanBuilder.and(qEmiData.documentCode.contains(qm.getDocumentCode()));
        }
        if (StringUtils.isNotBlank(qm.getIndustryCode())) {
            booleanBuilder.and(qEmiData.industryCode.eq(qm.getIndustryCode()));
        }
        if (userModel.getEnterpriseId() != null) {
            booleanBuilder.and(qEmiData.enterpriseId.eq(userModel.getEnterpriseId()));
        }
        if (StringUtils.isNotBlank(qm.getEnterpriseName())) {
            booleanBuilder.and(qEmiData.enterpriseName.contains(qm.getEnterpriseName()));
        }
        if (StringUtils.isNotBlank(qm.getReporterName())) {
            booleanBuilder.and(qEmiData.reporterName.contains(qm.getReporterName()));
        }
        if (StringUtils.isNotBlank(qm.getAccYearFrom()) && StringUtils.isNotBlank(qm.getAccMonthFrom())) {
            booleanBuilder.and(qEmiData.accTime.goe(qm.getAccYearFrom().concat(qm.getAccMonthFrom())));
        }
        if (StringUtils.isNotBlank(qm.getAccYearTo()) && StringUtils.isNotBlank(qm.getAccMonthTo())) {
            booleanBuilder.and(qEmiData.accTime.loe(qm.getAccYearTo().concat(qm.getAccMonthTo())));
        }
        if (StringUtils.isNotBlank(qm.getReportTimeFrom())) {
            booleanBuilder.and(qEmiData.reportTime.after(DateUtil.stringToInstant(qm.getReportTimeFrom())));
        }
        if (StringUtils.isNotBlank(qm.getReportTimeTo())) {
            booleanBuilder.and(qEmiData.reportTime.before(DateUtil.stringToInstant(qm.getReportTimeTo())));
        }
        return booleanBuilder;
    }

    /**
     * 获取上一个月（下一个月）污水厂碳排放详情接口
     *
     * @param vm
     * @return
     */
    public SewEmiDetailDTO getAndConvertDetailOfPreviousOrNextMonth(CarbonEmiMonthVM vm) {
        if (vm.getIsNext()) {
            List<SewEmiMonthDTO> list = jpaQueryFactory
                .select(Projections.bean(SewEmiMonthDTO.class, qSewEmi.id, qSewEmi.documentCode, qSewEmi.accYear, qSewEmi.accMonth))
                .from(qSewEmi)
                .where(qSewEmi.enterpriseId.eq(vm.getEnterpriseId()).and(qSewEmi.accTime.goe(vm.getAccYear().concat(vm.getAccMonth()))))
                .orderBy(qSewEmi.accYear.asc())
                .orderBy(qSewEmi.accMonth.asc())
                .fetch();
            Integer index = IntStream.range(0, list.size())
                .filter(i -> vm.getDocumentCode().equals(list.get(i).getDocumentCode()))
                .boxed()
                .findFirst()
                .orElseThrow(() -> new BadRequestProblem("查询失败", "定位当前核算时间的碳排放核算数据失败"));
            if (index + 1 == list.size()) {
                throw new BadRequestProblem("暂无下一个月数据");
            }
            return convertToSewEmiDetailDtoByDocumentCode(list.get(index + 1).getDocumentCode());
        } else {
            List<SewEmiMonthDTO> list = jpaQueryFactory
                .select(Projections.bean(SewEmiMonthDTO.class, qSewEmi.id, qSewEmi.documentCode, qSewEmi.accYear, qSewEmi.accMonth))
                .from(qSewEmi)
                .where(qSewEmi.enterpriseId.eq(vm.getEnterpriseId()).and(qSewEmi.accYear.loe(vm.getAccYear().concat(vm.getAccMonth()))))
                .orderBy(qSewEmi.accYear.desc())
                .orderBy(qSewEmi.accMonth.desc())
                .fetch();
            Integer index = IntStream.range(0, list.size())
                .filter(i -> vm.getDocumentCode().equals(list.get(i).getDocumentCode()))
                .boxed()
                .findFirst()
                .orElseThrow(() -> new BadRequestProblem("查询失败", "定位当前核算时间的碳排放核算数据失败"));
            if (index + 1 == list.size()) {
                throw new BadRequestProblem("暂无上一个月数据");
            }
            return convertToSewEmiDetailDtoByDocumentCode(list.get(index + 1).getDocumentCode());
        }
    }

    /**
     * 初始化碳排放因子
     *
     * @return
     */
    public EmiFactor create() {
        EmiFactor emiFactor = new EmiFactor();
        emiFactor.setModifyDate(LocalDate.now());
        emiFactor.setProjectCode(SEW_TREAT);
        emiFactor.setProjectName("污水处理厂碳排放因子");
        emiFactor.setVersionNum("污水处理厂碳排放因子-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        emiFactor.setRemark("因子默认值初始化");
        emiFactor.setOperatorId(1L);
        emiFactor.setOperatorName("system");

        SewEmiFactorDTO sewEmiFactorDTO = new SewEmiFactorDTO();
        // 字段含义解释
        List<SewEmiFactorDTO.Explain> explainList = new ArrayList<>();
        explainList.add(new SewEmiFactorDTO.Explain("proElecEmiFactor", "省电网排放系数"));
        explainList.add(new SewEmiFactorDTO.Explain("chemicalsEmiFactor", "药剂排放参数"));
        explainList.add(new SewEmiFactorDTO.Explain("processTypeNi", "工艺排放系数"));
        explainList.add(new SewEmiFactorDTO.Explain("heatPumpFactor", "热泵对应参数"));
        explainList.add(new SewEmiFactorDTO.Explain("gasEmiFactor", "气体排放系数"));
        explainList.add(new SewEmiFactorDTO.Explain("sewTreatFactor", "污水处理排放系数"));
        explainList.add(new SewEmiFactorDTO.Explain("sluTreatFactor", "污泥处置排放系数"));
        sewEmiFactorDTO.setExplain(explainList);
        // 省市电网平均CO2排放因子
        LinkedHashMap<String, SewEmiFactorDTO.InfoPro> proElecEmiFactorMap = new LinkedHashMap<>();
        proElecEmiFactorMap.put("11", new SewEmiFactorDTO.InfoPro("北京", "CF_E_1", "11", BigDecimal.valueOf(0.6168), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("12", new SewEmiFactorDTO.InfoPro("天津", "CF_E_2", "12", BigDecimal.valueOf(0.8119), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("13", new SewEmiFactorDTO.InfoPro("河北", "CF_E_3", "13", BigDecimal.valueOf(0.9029), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("14", new SewEmiFactorDTO.InfoPro("山西", "CF_E_4", "14", BigDecimal.valueOf(0.7399), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("15", new SewEmiFactorDTO.InfoPro("内蒙古", "CF_E_5", "15", BigDecimal.valueOf(0.7533), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("22", new SewEmiFactorDTO.InfoPro("吉林", "CF_E_6", "22", BigDecimal.valueOf(0.6147), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("23", new SewEmiFactorDTO.InfoPro("黑龙江", "CF_E_7", "23", BigDecimal.valueOf(0.6634), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("21", new SewEmiFactorDTO.InfoPro("辽宁", "CF_E_8", "21", BigDecimal.valueOf(0.7219), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("31", new SewEmiFactorDTO.InfoPro("上海", "CF_E_9", "31", BigDecimal.valueOf(0.5641), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("32", new SewEmiFactorDTO.InfoPro("江苏", "CF_E_10", "32", BigDecimal.valueOf(0.6829), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("33", new SewEmiFactorDTO.InfoPro("浙江", "CF_E_11", "33", BigDecimal.valueOf(0.5246), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("34", new SewEmiFactorDTO.InfoPro("安徽", "CF_E_12", "34", BigDecimal.valueOf(0.7759), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("35", new SewEmiFactorDTO.InfoPro("福建", "CF_E_13", "35", BigDecimal.valueOf(0.391), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("36", new SewEmiFactorDTO.InfoPro("江西", "CF_E_14", "36", BigDecimal.valueOf(0.6339), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("37", new SewEmiFactorDTO.InfoPro("山东", "CF_E_15", "37", BigDecimal.valueOf(0.8606), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("41", new SewEmiFactorDTO.InfoPro("河南", "CF_E_16", "41", BigDecimal.valueOf(0.7906), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("43", new SewEmiFactorDTO.InfoPro("湖南", "CF_E_17", "43", BigDecimal.valueOf(0.4987), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("42", new SewEmiFactorDTO.InfoPro("湖北", "CF_E_18", "42", BigDecimal.valueOf(0.3574), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("44", new SewEmiFactorDTO.InfoPro("广东", "CF_E_19", "44", BigDecimal.valueOf(0.4512), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("45", new SewEmiFactorDTO.InfoPro("广西", "CF_E_20", "45", BigDecimal.valueOf(0.3938), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("46", new SewEmiFactorDTO.InfoPro("海南", "CF_E_21", "46", BigDecimal.valueOf(0.5147), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("51", new SewEmiFactorDTO.InfoPro("四川", "CF_E_22", "51", BigDecimal.valueOf(0.1031), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("52", new SewEmiFactorDTO.InfoPro("贵州", "CF_E_23", "52", BigDecimal.valueOf(0.4275), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("53", new SewEmiFactorDTO.InfoPro("云南", "CF_E_24", "53", BigDecimal.valueOf(0.0921), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("61", new SewEmiFactorDTO.InfoPro("陕西", "CF_E_25", "61", BigDecimal.valueOf(0.7673), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("64", new SewEmiFactorDTO.InfoPro("宁夏", "CF_E_26", "64", BigDecimal.valueOf(0.6195), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("63", new SewEmiFactorDTO.InfoPro("青海", "CF_E_27", "63", BigDecimal.valueOf(0.2602), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("50", new SewEmiFactorDTO.InfoPro("重庆", "CF_E_28", "50", BigDecimal.valueOf(0.4405), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("62", new SewEmiFactorDTO.InfoPro("甘肃", "CF_E_29", "62", BigDecimal.valueOf(0.4912), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("65", new SewEmiFactorDTO.InfoPro("新疆", "CF_E_30", "65", BigDecimal.valueOf(0.622), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("54", new SewEmiFactorDTO.InfoPro("西藏", "CF_E_31", "54", BigDecimal.valueOf(0.6031), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("81", new SewEmiFactorDTO.InfoPro("香港", "CF_E_32", "81", new BigDecimal(0), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("82", new SewEmiFactorDTO.InfoPro("澳门", "CF_E_33", "82", new BigDecimal(0), "kg CO2 eq / kWh", "proElecEmiFactor"));
        proElecEmiFactorMap.put("71", new SewEmiFactorDTO.InfoPro("台湾", "CF_E_34", "71", new BigDecimal(0), "kg CO2 eq / kWh", "proElecEmiFactor"));
        sewEmiFactorDTO.setProElecEmiFactor(proElecEmiFactorMap);
        // 药剂CO2排放因子
        LinkedHashMap<String, SewEmiFactorDTO.Info> chemicalsEmiFactorMap = new LinkedHashMap<>();
        chemicalsEmiFactorMap.put("CF_M_1", new SewEmiFactorDTO.Info("氯化铝", "CF_M_1", BigDecimal.valueOf(6.98248755057367), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        chemicalsEmiFactorMap.put("CF_M_2", new SewEmiFactorDTO.Info("硫酸铝|粉", "CF_M_2", BigDecimal.valueOf(0.756288833442325), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        chemicalsEmiFactorMap.put("CF_M_3", new SewEmiFactorDTO.Info("硫酸铝|无水|含硫酸铝4.33%", "CF_M_3", BigDecimal.valueOf(0.559368134327201), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        chemicalsEmiFactorMap.put("CF_M_4", new SewEmiFactorDTO.Info("硫酸亚铁", "CF_M_4", BigDecimal.valueOf(0.259138217385877), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        chemicalsEmiFactorMap.put("CF_M_5", new SewEmiFactorDTO.Info("硫酸铁|无水|含硫酸铁12.5%", "CF_M_5", BigDecimal.valueOf(0.303814581820279), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        chemicalsEmiFactorMap.put("CF_M_6", new SewEmiFactorDTO.Info("氯化亚铁", "CF_M_6", BigDecimal.valueOf(0.242343451466328), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        chemicalsEmiFactorMap.put("CF_M_7", new SewEmiFactorDTO.Info("氯化铁|无水|含氯化铁40%", "CF_M_7", BigDecimal.valueOf(1.05836918051482), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        chemicalsEmiFactorMap.put("CF_M_8", new SewEmiFactorDTO.Info("氯化铁|无水|含氯化铁14%", "CF_M_8", BigDecimal.valueOf(0.643396285826687), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        chemicalsEmiFactorMap.put("CF_M_9", new SewEmiFactorDTO.Info("氯化铁|无水|含氯化铁12%", "CF_M_9", BigDecimal.valueOf(0.628227564402345), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        chemicalsEmiFactorMap.put("CF_M_10", new SewEmiFactorDTO.Info("PAC (聚合氯化铝)", "CF_M_10", BigDecimal.valueOf(1.65484334133258), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        chemicalsEmiFactorMap.put("CF_M_11", new SewEmiFactorDTO.Info("PAM (聚丙烯酰胺)", "CF_M_11", BigDecimal.valueOf(2.84525201426426), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        chemicalsEmiFactorMap.put("CF_M_12", new SewEmiFactorDTO.Info("乙酸|无水|含乙酸98%", "CF_M_12", BigDecimal.valueOf(1.92087590438908), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        chemicalsEmiFactorMap.put("CF_M_13", new SewEmiFactorDTO.Info("葡萄糖", "CF_M_13", BigDecimal.valueOf(1.40029217235606), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        chemicalsEmiFactorMap.put("CF_M_14", new SewEmiFactorDTO.Info("甲醇", "CF_M_14", BigDecimal.valueOf(0.648435046545033), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        chemicalsEmiFactorMap.put("CF_M_15", new SewEmiFactorDTO.Info("次氯酸钠|含次氯酸钠15%", "CF_M_15", BigDecimal.valueOf(2.99030768545925), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        chemicalsEmiFactorMap.put("CF_M_16", new SewEmiFactorDTO.Info("生石灰|研磨", "CF_M_16", BigDecimal.valueOf(0.0384503323345839), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        chemicalsEmiFactorMap.put("CF_M_17", new SewEmiFactorDTO.Info("二氧化氯", "CF_M_17", BigDecimal.valueOf(9.31336987800091), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        chemicalsEmiFactorMap.put("CF_M_18", new SewEmiFactorDTO.Info("乙酸钠", "CF_M_18", BigDecimal.valueOf(2.89602995303398), "kg CO2 eq / kg", "chemicalsEmiFactor"));
        sewEmiFactorDTO.setChemicalsEmiFactor(chemicalsEmiFactorMap);
        // 工艺类型排放因子
        LinkedHashMap<String, SewEmiFactorDTO.Info> processTypeNiMap = new LinkedHashMap<>();
        processTypeNiMap.put("N1", new SewEmiFactorDTO.Info("AO", "N1", BigDecimal.valueOf(0.013), "kg N2O-N / kg N", "processTypeNi"));
        processTypeNiMap.put("N2", new SewEmiFactorDTO.Info("AAO", "N2", BigDecimal.valueOf(0.013), "kg N2O-N / kg N", "processTypeNi"));
        processTypeNiMap.put("N3", new SewEmiFactorDTO.Info("CAST(CASS)", "N3", BigDecimal.valueOf(0.013), "kg N2O-N / kg N", "processTypeNi"));
        processTypeNiMap.put("N4", new SewEmiFactorDTO.Info("氧化沟", "N4", BigDecimal.valueOf(0.00016), "kg N2O-N / kg N", "processTypeNi"));
        processTypeNiMap.put("N5", new SewEmiFactorDTO.Info("SBR", "N5", BigDecimal.valueOf(0.029), "kg N2O-N / kg N", "processTypeNi"));
        processTypeNiMap.put("N6", new SewEmiFactorDTO.Info("其他活性污泥法", "N6", BigDecimal.valueOf(0.016), "kg N2O-N / kg N", "processTypeNi"));
        processTypeNiMap.put("N7", new SewEmiFactorDTO.Info("其他工艺", "N7", BigDecimal.valueOf(0.016), "kg N2O-N / kg N", "processTypeNi"));
        processTypeNiMap.put("N8", new SewEmiFactorDTO.Info("MBR", "N8", BigDecimal.valueOf(0.016), "kg N2O-N / kg N", "processTypeNi"));
        sewEmiFactorDTO.setProcessTypeNi(processTypeNiMap);
        // 热泵对应参数
        SewEmiFactorDTO.HeatPumpFactor heatPumpFactor = new SewEmiFactorDTO.HeatPumpFactor();
        heatPumpFactor.setY(new SewEmiFactorDTO.Info("燃气电厂供热的热电折算系数", "y", new BigDecimal(6500), "kJ/kWh", "heatPumpFactor"));
        sewEmiFactorDTO.setHeatPumpFactor(heatPumpFactor);
        // 气体排放系数
        SewEmiFactorDTO.GasEmiFactor gasEmiFactor = new SewEmiFactorDTO.GasEmiFactor();
        gasEmiFactor.setCfhp(new SewEmiFactorDTO.Info("燃煤锅炉二氧化碳排放因子", "CF_HP", BigDecimal.valueOf(96.1), "kg CO2 eq / GJ", "gasEmiFactor"));
        gasEmiFactor.setCfco2(new SewEmiFactorDTO.Info("CO2", "CF_CO2", new BigDecimal(1), "kg CO2 eq / kg CO2", "gasEmiFactor"));
        gasEmiFactor.setCfch4(new SewEmiFactorDTO.Info("CH4", "CF_CH4", new BigDecimal(28), "kg CO2 eq / kg CH4", "gasEmiFactor"));
        gasEmiFactor.setCfn2o(new SewEmiFactorDTO.Info("N2O", "CF_N2O", new BigDecimal(298), "kg CO2 eq / kg N2O", "gasEmiFactor"));
        sewEmiFactorDTO.setGasEmiFactor(gasEmiFactor);
        // 污水处理排放系数
        SewEmiFactorDTO.SewTreatFactor sewTreatFactor = new SewEmiFactorDTO.SewTreatFactor();
        sewTreatFactor.setB0bod(new SewEmiFactorDTO.Info("最大CH4生产能力", "B0_BOD", BigDecimal.valueOf(0.6), "kg CH4 / kg BOD", "sewTreatFactor"));
        sewTreatFactor.setB0cod(new SewEmiFactorDTO.Info("最大CH4生产能力", "B0_COD", BigDecimal.valueOf(0.25), "kg CH4 / kg COD", "sewTreatFactor"));
        sewTreatFactor.setMcf(new SewEmiFactorDTO.Info("CH4修正因子_集中好氧处理厂", "MCF", BigDecimal.valueOf(0.03), "1", "sewTreatFactor"));
        sewEmiFactorDTO.setSewTreatFactor(sewTreatFactor);
        // 污泥处置排放系数
        SewEmiFactorDTO.SluTreatFactor sluTreatFactor = new SewEmiFactorDTO.SluTreatFactor();
        sluTreatFactor.setWc(new SewEmiFactorDTO.Info("污泥处置工艺污泥含水率", "wc", BigDecimal.valueOf(60.0), "%", "sluTreatFactor"));
        sluTreatFactor.setEfs1(new SewEmiFactorDTO.Info("堆肥CH4排放系数", "EFS_1", BigDecimal.valueOf(0.004), "kg CH4 / kg 湿物质", "sluTreatFactor"));
        sluTreatFactor.setEfs2(new SewEmiFactorDTO.Info("厌氧消化CH4排放系数", "EFS_2", BigDecimal.valueOf(0.0008), "kg CH4 / kg 湿物质", "sluTreatFactor"));
        sluTreatFactor.setDoc(new SewEmiFactorDTO.Info("污泥脱水后填埋CH4排放系数DOC", "DOC", BigDecimal.valueOf(0.3), "kg碳/kg 湿物质", "sluTreatFactor"));
        sluTreatFactor.setDocf(new SewEmiFactorDTO.Info("污泥脱水后填埋CH4排放系数DOCf", "DOCf", BigDecimal.valueOf(0.5), "1", "sluTreatFactor"));
        sluTreatFactor.setMcf(new SewEmiFactorDTO.Info("污泥脱水后填埋CH4排放系数MCF", "MCF", new BigDecimal(1), "1", "sluTreatFactor"));
        sluTreatFactor.setF(new SewEmiFactorDTO.Info("污泥脱水后填埋CH4排放系数F", "F", BigDecimal.valueOf(0.5), "1", "sluTreatFactor"));
        sluTreatFactor.setOx(new SewEmiFactorDTO.Info("污泥脱水后填埋CH4排放系数OX", "OX", BigDecimal.valueOf(0.1), "1", "sluTreatFactor"));
        sluTreatFactor.setEfs3(new SewEmiFactorDTO.Info("堆肥N2O排放系数", "EFS_3", BigDecimal.valueOf(0.00024), "kg N2O / kg 湿物质", "sluTreatFactor"));
        sluTreatFactor.setEfs4(new SewEmiFactorDTO.Info("厌氧消化N2O排放系数", "EFS_4", new BigDecimal(0), "kg N2O / kg 湿物质", "sluTreatFactor"));
        sewEmiFactorDTO.setSluTreatFactor(sluTreatFactor);

        try {
            emiFactor.setContent(objectMapper.writeValueAsString(sewEmiFactorDTO));
        } catch (JsonProcessingException e) {
            throw new BadRequestProblem("初始化失败");
        }

        return emiFactorRepository.save(emiFactor);
    }

    public Instant getInstant(String string){
        if (string == null) return null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //将 string 装换成带有 T 的国际时间，但是没有进行，时区的转换，即没有将时间转为国际时间，只是格式转为国际时间
        LocalDateTime parse = LocalDateTime.parse(string, dateTimeFormatter);
        //+8 小时，offset 可以理解为时间偏移量
        ZoneOffset offset = OffsetDateTime.now().getOffset();
        //转换为 通过时间偏移量将 string -8小时 变为 国际时间，因为亚洲上海是8时区
        Instant instant = parse.toInstant(offset).plusMillis(TimeUnit.HOURS.toMillis(8));
        return instant;
    }
}
