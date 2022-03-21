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
import com.ruowei.util.DateUtil;
import com.ruowei.web.rest.dto.SewDetailsDTO;
import com.ruowei.web.rest.dto.SewEmiDetailDTO;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.CarbonEmiMonthVM;
import com.ruowei.web.rest.vm.CarbonEmiQM;
import com.ruowei.web.rest.vm.SewEmiAccountVM;
import com.ruowei.web.rest.vm.SewEmiVM;
import liquibase.pro.packaged.S;
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

import static com.ruowei.config.Constants.*;
import static com.ruowei.domain.enumeration.SendStatusType.FAILED;

@Service
@Slf4j
public class SewEmiService {

    private final EmiFactorRepository emiFactorRepository;
    private final ObjectMapper objectMapper;
    private final ApplicationProperties applicationProperties;
    private final EmiDataRepository emiDataRepository;
    private final SewProcessRepository sewProcessRepository;
    private final SewSluRepository sewSluRepository;
    private final OtherIndexRepository otherIndexRepository;
    private final SewPotRepository sewPotRepository;
    private final SewMeterRepository sewMeterRepository;
    private final JPAQueryFactory jpaQueryFactory;


    public SewEmiService(EmiFactorRepository emiFactorRepository, ObjectMapper objectMapper, ApplicationProperties applicationProperties, EmiDataRepository emiDataRepository, SewProcessRepository sewProcessRepository, SewSluRepository sewSluRepository, OtherIndexRepository otherIndexRepository, SewPotRepository sewPotRepository, SewMeterRepository sewMeterRepository, JPAQueryFactory jpaQueryFactory) {
        this.emiFactorRepository = emiFactorRepository;
        this.objectMapper = objectMapper;
        this.applicationProperties = applicationProperties;
        this.emiDataRepository = emiDataRepository;
        this.sewProcessRepository = sewProcessRepository;
        this.sewSluRepository = sewSluRepository;
        this.otherIndexRepository = otherIndexRepository;
        this.sewPotRepository = sewPotRepository;
        this.sewMeterRepository = sewMeterRepository;
        this.jpaQueryFactory = jpaQueryFactory;
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

            // 封装工艺水质信息
            List<WaterCarbonEmissionEnterDTO.WaterQualityDTO> waterQualityDTOList = new ArrayList<>();

            // 封装因子项

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
     * 封装数据
     *
     * @param craftId
     * @param userModel
     * @param vm
     * @param outputDTO
     * @param nowInstant
     * @return
     */
    public EmiDataDTO convertToEmiDataDTO(String craftId,
                                          UserModel userModel,
                                          SewEmiAccountVM vm,
                                          WaterCarbonEmissionOutputDTO outputDTO,
                                          Instant nowInstant) {
        return new EmiDataDTO(
            craftId,
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
     * 调用智能合约接口保存污水厂碳排放核算数据（现将数据保存在MySQL数据库中，此方法暂未用到）
     *
     * @param request
     * @param craftId
     * @param userModel
     * @param vm
     * @param outputDTO
     */
    public void saveAccountingResult(HttpServletRequest request,
                                     String craftId,
                                     UserModel userModel,
                                     SewEmiAccountVM vm,
                                     WaterCarbonEmissionEnterDTO enterDTO,
                                     WaterCarbonEmissionOutputDTO outputDTO,
                                     Instant nowInstant) {

        // 封装请求体
        List<Object> list = new ArrayList<>();
        EmiDataDTO emiDataDTO = convertToEmiDataDTO(craftId, userModel, vm, outputDTO, nowInstant);
        list.add(emiDataDTO);
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
     * 保存核算信息
     *
     * @param craftId

     * @param vm
     * @param nowInstant
     */
    //TODO 保存所有数据到数据库
    public void saveAccountingResultToMySQL(Long craftId, SewEmiAccountVM.SewProcessVM vm, Instant nowInstant) {
        SewProcess sewProcess = new SewProcess()
            .craftId(craftId)
            .outSs(vm.getOutSs())
            .inFlow(vm.getInFlow())
            .inAmmonia(vm.getInFlow())
            .inCod(vm.getInFlow())
            .inTn(vm.getInFlow())
            .inTp(vm.getInFlow())
            .inSs(vm.getInFlow())
            .outFlow(vm.getInFlow())
            .outAmmonia(vm.getInFlow())
            .outCod(vm.getInFlow())
            .outTn(vm.getInFlow())
            .outTp(vm.getInFlow())
            .outSs(vm.getInFlow())
            .anoxicPoolDo(vm.getInFlow())
            .aerobicPoolDo(vm.getInFlow())
            .anoxicPoolDoOutNit(vm.getInFlow())
            .aerobicPoolNit(vm.getInFlow())
            .dayTime(nowInstant)
            .status(FAILED);
//        List<SewProcess> sewProcessList = new ArrayList<>();
//        for (SewEmiAccountVM.SewProcessVM processVm : vm.getSewProcesss()) {
//            SewProcess sewProcess = new SewProcess()
//                .craftId(craftId)
//                .outSs(processVm.getOutSs())
//                .inFlow(processVm.getInFlow())
//                .inAmmonia(processVm.getInFlow())
//                .inCod(processVm.getInFlow())
//                .inTn(processVm.getInFlow())
//                .inTp(processVm.getInFlow())
//                .inSs(processVm.getInFlow())
//                .outFlow(processVm.getInFlow())
//                .outAmmonia(processVm.getInFlow())
//                .outCod(processVm.getInFlow())
//                .outTn(processVm.getInFlow())
//                .outTp(processVm.getInFlow())
//                .outSs(processVm.getInFlow())
//                .anoxicPoolDo(processVm.getInFlow())
//                .aerobicPoolDo(processVm.getInFlow())
//                .anoxicPoolDoOutNit(processVm.getInFlow())
//                .aerobicPoolNit(processVm.getInFlow())
//                .dayTime(nowInstant)
//                .status(FAILED);
//            sewProcessList.add(sewProcess);
//        }
//        List<SewPot> sewPotList = new ArrayList<>();
//        for (SewEmiAccountVM.SewPotVM potVm : vm.getSewPots()) {
//            SewPot sewPot = new SewPot()
//                .craftId(craftId)
//                ;
//
//            sewPotList.add(sewPot);
//        }
//        List<SewSlu> sewSluList = new ArrayList<>();
//        for (SewEmiAccountVM.SewSluVM sluVm : vm.getSewSlus()) {
//            SewSlu sewSlu = new SewSlu()
//                .craftId(craftId);
//
//            sewSluList.add(sewSlu);
//        }
//        List<OtherIndex> otherIndexList = new ArrayList<>();
//        for (SewEmiAccountVM.OtherIndexVM otherIndexVM : vm.getOtherIndexs()) {
//            OtherIndex otherIndex = new OtherIndex()
//                .craftId(craftId);
//
//            otherIndexList.add(otherIndex);
//        }
        sewProcessRepository.save(sewProcess);
//        sewPotRepository.saveAll(sewPotList);
//        sewSluRepository.saveAll(sewSluList);
//        otherIndexRepository.saveAll(otherIndexList);
    }

    /**
     * 根据单据号获取污水厂碳排放数据，并封装为SewEmiDetailDTO
     *
     * @param craftId 单据号
     * @return
     */
    public SewEmiDetailDTO convertToSewEmiDetailDtoBycraftId(Long craftId) {
        /*SewEmi sewEmi = sewEmiRepository.findBycraftId(craftId).orElseThrow(() -> new BadRequestProblem("查询详情失败", "该污水厂碳排放数据不存在"));*/
        SewEmiDetailDTO sewEmiDetailDTO = new SewEmiDetailDTO();

        /*BeanUtils.copyProperties(sewEmi, sewEmiDetailDTO, BeanUtil.getNullPropertyNames(sewEmi));*/
        List<SewProcess> sewProcessList = sewProcessRepository.findByCraftId(craftId);
        List<SewEmiAccountVM.SewProcessVM> sewProcessVms = new ArrayList<>();
        for (SewProcess sewProcess : sewProcessList) {
            SewEmiAccountVM.SewProcessVM vm = new SewEmiAccountVM.SewProcessVM();
            BeanUtils.copyProperties(sewProcess, vm, BeanUtil.getNullPropertyNames(sewProcess));
            sewProcessVms.add(vm);
        }
        sewEmiDetailDTO.setSewProcesss(sewProcessVms);
        List<SewPot> sewPotList = sewPotRepository.findByCraftId(craftId);
        List<SewEmiAccountVM.SewPotVM> sewPotVms = new ArrayList<>();
        for (SewPot sewPot : sewPotList) {
            SewEmiAccountVM.SewPotVM vm = new SewEmiAccountVM.SewPotVM();
            BeanUtils.copyProperties(sewPot, vm, BeanUtil.getNullPropertyNames(sewPot));
            sewPotVms.add(vm);
        }
        sewEmiDetailDTO.setSewPots(sewPotVms);
        List<SewSlu> sewSluList = sewSluRepository.findByCraftId(craftId);
        List<SewEmiAccountVM.SewSluVM> sewSluVms = new ArrayList<>();
        for (SewSlu sewSlu : sewSluList) {
            SewEmiAccountVM.SewSluVM vm = new SewEmiAccountVM.SewSluVM();
            BeanUtils.copyProperties(sewSlu, vm, BeanUtil.getNullPropertyNames(sewSlu));
            sewSluVms.add(vm);
        }
        sewEmiDetailDTO.setSewSlus(sewSluVms);

        List<OtherIndex> otherIndexList = otherIndexRepository.findByCraftId(craftId);
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
     * @param craftId 单据号
     * @return
     */
    public List<SewEmiVM> convertToSewDetailDtoBycraftId(Long craftId) {
        /*SewEmi sewEmi = sewEmiRepository.findBycraftId(craftId).orElseThrow(() -> new BadRequestProblem("查询详情失败", "该污水厂碳排放数据不存在"));*/

        List<SewEmiVM> emiVMS = new ArrayList<>();
        /*BeanUtils.copyProperties(sewEmi, sewEmiDetailDTO, BeanUtil.getNullPropertyNames(sewEmi));*/
        List<SewProcess> sewProcessList = sewProcessRepository.findByCraftId(craftId);
        for (SewProcess sewProcess : sewProcessList) {
            SewEmiVM.SewProcessVM vm = new SewEmiVM.SewProcessVM();
            BeanUtils.copyProperties(sewProcess, vm, BeanUtil.getNullPropertyNames(sewProcess));
            emiVMS.add(vm);
        }
        List<SewPot> sewPotList = sewPotRepository.findByCraftId(craftId);
        for (SewPot sewPot : sewPotList) {
            SewEmiVM.SewPotVM vm = new SewEmiVM.SewPotVM();
            BeanUtils.copyProperties(sewPot, vm, BeanUtil.getNullPropertyNames(sewPot));
            emiVMS.add(vm);
        }
        List<SewSlu> sewSluList = sewSluRepository.findByCraftId(craftId);
        for (SewSlu sewSlu : sewSluList) {
            SewEmiVM.SewSluVM vm = new SewEmiVM.SewSluVM();
            BeanUtils.copyProperties(sewSlu, vm, BeanUtil.getNullPropertyNames(sewSlu));
            emiVMS.add(vm);
        }

        return emiVMS;
    }

    /**
     * 根据来源及id获取详情数据
     */
    public List<SewEmiVM> getTerget(Long id, String source) {

        List<SewEmiVM> emiVMS = new ArrayList<>();

        switch (source) {
            case "meter":
                SewProcess sewProcess = sewProcessRepository.findById(id).get();
                SewEmiVM.SewProcessVM vm = new SewEmiVM.SewProcessVM();
                BeanUtils.copyProperties(sewProcess, vm, BeanUtil.getNullPropertyNames(sewProcess));
                emiVMS.add(vm);
                break;
            case "daily":
                SewPot sewPot = sewPotRepository.findById(id).get();
                SewEmiVM.SewPotVM vm1 = new SewEmiVM.SewPotVM();
                BeanUtils.copyProperties(sewPot, vm1, BeanUtil.getNullPropertyNames(sewPot));
                emiVMS.add(vm1);
                break;
            case "assay":
                SewSlu sewSlu = sewSluRepository.findById(id).get();
                SewEmiVM.SewSluVM vm2 = new SewEmiVM.SewSluVM();
                BeanUtils.copyProperties(sewSlu, vm2, BeanUtil.getNullPropertyNames(sewSlu));
                emiVMS.add(vm2);
                break;
            case "verify":
                SewMeter sewMeter = sewMeterRepository.findById(id).get();
                SewEmiVM.SewMeterVM vm3 = new SewEmiVM.SewMeterVM();
                BeanUtils.copyProperties(sewMeter, vm3, BeanUtil.getNullPropertyNames(sewMeter));
                emiVMS.add(vm3);
        }
        return emiVMS;
    }

    /**
     *
     * 批量修改
     *
     * */
    public void modificationBycraftId(Long id, SewDetailsDTO sewProcessDTO,String source) {

       /* switch (source){
            case "meter":
                SewProcess sewProcess = sewProcessRepository.findById(id).get();
                sewProcess.setAerobicPoolDo(sewProcessDTO.getClass());
                for (SewProcess sew : sewProcessRepository.findBycraftId(craftId)) {
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
                };
            case "assay":;
            case "daily":;
            case "verify":;
            default:;
        }*/
    }

//    /**
//     * 获取上一个月（下一个月）污水厂碳排放详情接口
//     *
//     * @param vm
//     * @return
//     */
//    public SewEmiDetailDTO getAndConvertDetailOfPreviousOrNextMonth(CarbonEmiMonthVM vm) {
//        if (vm.getIsNext()) {
//            List<SewEmiMonthDTO> list = jpaQueryFactory
//                .select(Projections.bean(SewEmiMonthDTO.class, qSewEmi.id, qSewEmi.craftId, qSewEmi.accYear, qSewEmi.accMonth))
//                .from(qSewEmi)
//                .where(qSewEmi.enterpriseId.eq(vm.getEnterpriseId()).and(qSewEmi.accTime.goe(vm.getAccYear().concat(vm.getAccMonth()))))
//                .orderBy(qSewEmi.accYear.asc())
//                .orderBy(qSewEmi.accMonth.asc())
//                .fetch();
//            Integer index = IntStream.range(0, list.size())
//                .filter(i -> vm.getcraftId().equals(list.get(i).getcraftId()))
//                .boxed()
//                .findFirst()
//                .orElseThrow(() -> new BadRequestProblem("查询失败", "定位当前核算时间的碳排放核算数据失败"));
//            if (index + 1 == list.size()) {
//                throw new BadRequestProblem("暂无下一个月数据");
//            }
//            return convertToSewEmiDetailDtoBycraftId(list.get(index + 1).getcraftId());
//        } else {
//            List<SewEmiMonthDTO> list = jpaQueryFactory
//                .select(Projections.bean(SewEmiMonthDTO.class, qSewEmi.id, qSewEmi.craftId, qSewEmi.accYear, qSewEmi.accMonth))
//                .from(qSewEmi)
//                .where(qSewEmi.enterpriseId.eq(vm.getEnterpriseId()).and(qSewEmi.accYear.loe(vm.getAccYear().concat(vm.getAccMonth()))))
//                .orderBy(qSewEmi.accYear.desc())
//                .orderBy(qSewEmi.accMonth.desc())
//                .fetch();
//            Integer index = IntStream.range(0, list.size())
//                .filter(i -> vm.getcraftId().equals(list.get(i).getcraftId()))
//                .boxed()
//                .findFirst()
//                .orElseThrow(() -> new BadRequestProblem("查询失败", "定位当前核算时间的碳排放核算数据失败"));
//            if (index + 1 == list.size()) {
//                throw new BadRequestProblem("暂无上一个月数据");
//            }
//            return convertToSewEmiDetailDtoBycraftId(list.get(index + 1).getcraftId());
//        }
//    }

    public Instant getInstant(String string) {
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
