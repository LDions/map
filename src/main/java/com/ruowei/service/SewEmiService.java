package com.ruowei.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.config.ApplicationProperties;
import com.ruowei.domain.*;
import com.ruowei.repository.*;
import com.ruowei.security.UserModel;
import com.ruowei.security.jwt.JWTFilter;
import com.ruowei.service.dto.*;

import com.ruowei.util.BeanUtil;
import com.ruowei.web.rest.dto.SewDetailsDTO;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.SewEmiAccountVM;
import com.ruowei.web.rest.vm.SewEmiVM;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
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


    private final SewProcessRepository sewProcessRepository;
    private final SewSluRepository sewSluRepository;
    private final OtherIndexRepository otherIndexRepository;
    private final SewPotRepository sewPotRepository;
    private final SewMeterRepository sewMeterRepository;
    private final JPAQueryFactory jpaQueryFactory;


    public SewEmiService(SewProcessRepository sewProcessRepository, SewSluRepository sewSluRepository, OtherIndexRepository otherIndexRepository, SewPotRepository sewPotRepository, SewMeterRepository sewMeterRepository, JPAQueryFactory jpaQueryFactory) {

        this.sewProcessRepository = sewProcessRepository;
        this.sewSluRepository = sewSluRepository;
        this.otherIndexRepository = otherIndexRepository;
        this.sewPotRepository = sewPotRepository;
        this.sewMeterRepository = sewMeterRepository;
        this.jpaQueryFactory = jpaQueryFactory;
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
            .aerobicPoolDoSecond(vm.getInFlow())
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
     * 根据来源及id获取详情数据
     */
    public SewEmiVM getTarget(Long id, String source) {

        SewEmiVM sewEmiVM = new SewEmiVM();
        switch (source) {
            case "meter":
                SewProcess sewProcess = sewProcessRepository.findById(id).get();
                SewEmiVM.SewProcessVM sewProcessVM = new SewEmiVM.SewProcessVM();
                BeanUtils.copyProperties(sewProcess, sewProcessVM, BeanUtil.getNullPropertyNames(sewProcess));
                sewEmiVM = sewProcessVM;
                break;
            case "daily":
                SewPot sewPot = sewPotRepository.findById(id).get();
                SewEmiVM.SewPotVM sewPotVM = new SewEmiVM.SewPotVM();
                BeanUtils.copyProperties(sewPot, sewPotVM, BeanUtil.getNullPropertyNames(sewPot));
                sewEmiVM = sewPotVM;
                break;
            case "assay":
                SewSlu sewSlu = sewSluRepository.findById(id).get();
                SewEmiVM.SewSluVM sewSluVM = new SewEmiVM.SewSluVM();
                BeanUtils.copyProperties(sewSlu, sewSluVM, BeanUtil.getNullPropertyNames(sewSlu));
                sewEmiVM = sewSluVM;
                break;
            case "verify":
                SewMeter sewMeter = sewMeterRepository.findById(id).get();
                SewEmiVM.SewMeterVM sewMeterVM = new SewEmiVM.SewMeterVM();
                BeanUtils.copyProperties(sewMeter, sewMeterVM, BeanUtil.getNullPropertyNames(sewMeter));
                sewEmiVM = sewMeterVM;
        }
        return sewEmiVM;
    }

    /**
     * 批量修改
     */
    public void modificationByDocumentCode(Long id, SewDetailsDTO sewProcessDTO, String source) {

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
