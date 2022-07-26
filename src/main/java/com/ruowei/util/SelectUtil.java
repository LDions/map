package com.ruowei.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.*;
import com.ruowei.repository.SewMeterRepository;
import com.ruowei.repository.SewPotRepository;
import com.ruowei.repository.SewProcessRepository;
import com.ruowei.repository.SewSluRepository;
import com.ruowei.service.SewEmiService;
import com.ruowei.web.rest.vm.SituationAnalysisQM;
import com.ruowei.web.rest.vm.SituationAnalysisVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class SelectUtil {


    private final Logger log = LoggerFactory.getLogger(SelectUtil.class);
    private final ObjectMapper objectMapper;
    private final SewEmiService sewEmiService;
    private final SewProcessRepository sewProcessRepository;
    private final SewSluRepository sewSluRepository;
    private final SewMeterRepository sewMeterRepository;
    private final SewPotRepository sewPotRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final QSewProcess qSewProcess = QSewProcess.sewProcess;
    private final QSewMeter qSewMeter = QSewMeter.sewMeter;
    private final QSewSlu qSewSlu = QSewSlu.sewSlu;

    public SelectUtil(ObjectMapper objectMapper, SewEmiService sewEmiService, SewProcessRepository sewProcessRepository, SewSluRepository sewSluRepository, SewMeterRepository sewMeterRepository, SewPotRepository sewPotRepository, JPAQueryFactory jpaQueryFactory) {
        this.objectMapper = objectMapper;
        this.sewEmiService = sewEmiService;
        this.sewProcessRepository = sewProcessRepository;
        this.sewSluRepository = sewSluRepository;
        this.sewMeterRepository = sewMeterRepository;
        this.sewPotRepository = sewPotRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    //查询某段时间内某个指标的值
    public List<SituationAnalysisVM> getSome(String target, String beginTime, String endTime, String craftCode) {

        List<SituationAnalysisVM> situationAnalysisVMList = new ArrayList<>();
        List<SewProcess> sewProcessList = sewProcessRepository.findByDayTimeIsGreaterThanEqualAndDayTimeIsLessThanEqualAndCraftCode(sewEmiService.getInstant(beginTime)
            , sewEmiService.getInstant(endTime), craftCode);
        List<SewSlu> sewSluList = sewSluRepository.findByDayTimeIsGreaterThanEqualAndDayTimeIsLessThanEqualAndCraftCode(sewEmiService.getInstant(beginTime)
            , sewEmiService.getInstant(endTime), craftCode);
        List<SewMeter> sewMeterList = sewMeterRepository.findByDayTimeIsGreaterThanEqualAndDayTimeIsLessThanEqualAndCraftCode(sewEmiService.getInstant(beginTime)
            , sewEmiService.getInstant(endTime), craftCode);
        List<SewPot> sewPotList = sewPotRepository.findByDayTimeIsGreaterThanEqualAndDayTimeIsLessThanEqualAndCraftCode(sewEmiService.getInstant(beginTime)
            , sewEmiService.getInstant(endTime), craftCode);
        switch (target) {
            case "inFlow":
                for (SewProcess sewProcess : sewProcessList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewProcess.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewProcess.getInFlow());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "inAmmonia":
                for (SewProcess sewProcess : sewProcessList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewProcess.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewProcess.getInAmmonia());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "inCod":
                for (SewProcess sewProcess : sewProcessList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewProcess.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewProcess.getInCod());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "inTp":
                for (SewProcess sewProcess : sewProcessList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewProcess.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewProcess.getInTp());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "inTn":
                for (SewProcess sewProcess : sewProcessList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewProcess.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewProcess.getInTn());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "inSs":
                for (SewProcess sewProcess : sewProcessList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewProcess.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewProcess.getInSs());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "outFlow":
                for (SewProcess sewProcess : sewProcessList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewProcess.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewProcess.getOutFlow());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "outCod":
                for (SewProcess sewProcess : sewProcessList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewProcess.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewProcess.getOutCod());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "outTp":
                for (SewProcess sewProcess : sewProcessList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewProcess.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewProcess.getOutTp());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "outTn":
                for (SewProcess sewProcess : sewProcessList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewProcess.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewProcess.getOutTn());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "outSs":
                for (SewProcess sewProcess : sewProcessList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewProcess.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewProcess.getOutSs());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "aerobicPoolDoSecond":
                for (SewProcess sewProcess : sewProcessList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewProcess.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewProcess.getAerobicPoolDoSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "aerobicPoolDo":
                for (SewProcess sewProcess : sewProcessList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewProcess.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewProcess.getAerobicPoolDo());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "anoxicPoolDoOutNit":
                for (SewProcess sewProcess : sewProcessList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewProcess.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewProcess.getAerobicPoolNit());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "aerobicPoolNit":
                for (SewProcess sewProcess : sewProcessList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewProcess.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewProcess.getAerobicPoolNit());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "assInAmmonia":
                for (SewSlu sewSlu : sewSluList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewSlu.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewSlu.getAssInAmmonia());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "assInCod":
                for (SewSlu sewSlu : sewSluList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewSlu.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewSlu.getAssInCod());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "assInTn":
                for (SewSlu sewSlu : sewSluList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewSlu.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewSlu.getAssInTn());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "assInTp":
                for (SewSlu sewSlu : sewSluList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewSlu.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewSlu.getAssInTp());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "assAnoxicPoolDoOutNit":
                for (SewSlu sewSlu : sewSluList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewSlu.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewSlu.getAssAnoxicPoolDoOutNit());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "assAerobicPoolDoOutNit":
                for (SewSlu sewSlu : sewSluList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewSlu.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewSlu.getAssAerobicPoolDoOutNit());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "assOutAmmonia":
                for (SewSlu sewSlu : sewSluList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewSlu.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewSlu.getAssOutAmmonia());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "assOutCod":
                for (SewSlu sewSlu : sewSluList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewSlu.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewSlu.getAssOutCod());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "assOutTn":
                for (SewSlu sewSlu : sewSluList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewSlu.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewSlu.getAssOutTn());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "assOutTp":
                for (SewSlu sewSlu : sewSluList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewSlu.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewSlu.getAssOutTp());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;


            case "corInCod":
                for (SewMeter sewMeter : sewMeterList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewMeter.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewMeter.getCorInCod());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "corInTn":
                for (SewMeter sewMeter : sewMeterList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewMeter.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewMeter.getCorInTn());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "corInTp":
                for (SewMeter sewMeter : sewMeterList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewMeter.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewMeter.getCorInTp());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "corInAmmonia":
                for (SewMeter sewMeter : sewMeterList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewMeter.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewMeter.getCorInAmmonia());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "corAnoxicPoolDoOutNit":
                for (SewMeter sewMeter : sewMeterList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewMeter.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewMeter.getCorAnoxicPoolDoOutNit());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "corAerobicPoolDoOutNit":
                for (SewMeter sewMeter : sewMeterList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewMeter.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewMeter.getCorAerobicPoolDoOutNit());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "corOutAmmonia":
                for (SewMeter sewMeter : sewMeterList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewMeter.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewMeter.getCorOutAmmonia());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "corOutCod":
                for (SewMeter sewMeter : sewMeterList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewMeter.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewMeter.getCorOutCod());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "corOutTn":
                for (SewMeter sewMeter : sewMeterList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewMeter.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewMeter.getCorOutTn());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "corOutTp":
                for (SewMeter sewMeter : sewMeterList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewMeter.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewMeter.getCorOutTp());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnaerobicPoolPh":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnaerobicPoolPh());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnaerobicPoolPhSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnaerobicPoolPhSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnaerobicPoolOrp":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnaerobicPoolOrp());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnaerobicPoolOrpSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnaerobicPoolOrpSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnaerobicPoolDo":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnaerobicPoolDo());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnaerobicPoolDoSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnaerobicPoolDoSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnaerobicPoolSour":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnaerobicPoolSour());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnaerobicPoolSourSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnaerobicPoolSourSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnaerobicPoolSv":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnaerobicPoolSv());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnaerobicPoolSvSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnaerobicPoolSvSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnaerobicPoolMlss":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnaerobicPoolMlss());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnaerobicPoolMlssSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnaerobicPoolMlssSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnaerobicPoolTemper":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnaerobicPoolTemper());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnaerobicPoolTemperSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnaerobicPoolTemperSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnoxicPoolPh":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnoxicPoolPh());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnoxicPoolPhSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnoxicPoolPhSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnoxicPoolOrp":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnoxicPoolOrp());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnoxicPoolOrpSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnoxicPoolOrpSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnoxicPoolDo":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnoxicPoolDo());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnoxicPoolDoSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnoxicPoolDoSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnoxicPoolSour":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnoxicPoolSour());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnoxicPoolSourSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnoxicPoolSourSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnoxicPoolSv":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnoxicPoolSv());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnoxicPoolSvSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnoxicPoolSvSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnoxicPoolMlss":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnoxicPoolMlss());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnoxicPoolMlssSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnoxicPoolMlssSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnoxicPoolTemper":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnoxicPoolTemper());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAnoxicPoolTemperSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAnoxicPoolTemperSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolPh":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolPh());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolPhSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolPhSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolOrp":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolOrp());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolOrpSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolOrpSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolDo":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolDo());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolDoSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolDoSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolSour":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolSour());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolSourSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolSourSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolSv":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolSv());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolSvSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolSvSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolMlss":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolMlss());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolMlssSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolMlssSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolMlvss":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolMlvss());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolMlvssSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolMlvssSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolSvi":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolSvi());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolSviSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolSviSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolTemper":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolTemper());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
            case "dayAerobicPoolTemperSecond":
                for (SewPot sewPot : sewPotList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewPot.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewPot.getDayAerobicPoolTemperSecond());
                    situationAnalysisVMList.add(situationAnalysisVM);
                }
                break;
        }
        return situationAnalysisVMList;
    }

    //查询最新的某个指标的值
    public List<SituationAnalysisQM> getSome(List<String> targets, String craftCode) {

        List<SituationAnalysisQM> situationAnalysisQMList = new ArrayList<>();
        SewProcess sewProcess = jpaQueryFactory.selectFrom(qSewProcess)
            .where(qSewProcess.craftCode.eq(craftCode))
            .orderBy(qSewProcess.dayTime.desc()).fetchFirst();
        SewSlu sewSlu = jpaQueryFactory.selectFrom(qSewSlu)
            .where(qSewSlu.craftCode.eq(craftCode))
            .orderBy(qSewSlu.dayTime.desc()).fetchFirst();
        SewMeter sewMeter = jpaQueryFactory.selectFrom(qSewMeter)
            .where(qSewMeter.craftCode.eq(craftCode))
            .orderBy(qSewMeter.dayTime.desc()).fetchFirst();
        for (String target : targets) {
            SituationAnalysisQM situationAnalysisQM = new SituationAnalysisQM();
            switch (target) {
                case "inFlow":
                    situationAnalysisQM.setTarget("inFlow");
                    situationAnalysisQM.setValue(sewProcess.getInFlow());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "inAmmonia":
                    situationAnalysisQM.setTarget("inAmmonia");
                    situationAnalysisQM.setValue(sewProcess.getInAmmonia());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "inCod":
                    situationAnalysisQM.setTarget("inCod");
                    situationAnalysisQM.setValue(sewProcess.getInCod());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "inTp":
                    situationAnalysisQM.setTarget("inTp");
                    situationAnalysisQM.setValue(sewProcess.getInTp());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "inTn":
                    situationAnalysisQM.setTarget("inTn");
                    situationAnalysisQM.setValue(sewProcess.getInTn());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "inSs":
                    situationAnalysisQM.setTarget("inSs");
                    situationAnalysisQM.setValue(sewProcess.getInSs());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "outFlow":
                    situationAnalysisQM.setTarget("outFlow");
                    situationAnalysisQM.setValue(sewProcess.getOutFlow());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "outCod":
                    situationAnalysisQM.setTarget("outCod");
                    situationAnalysisQM.setValue(sewProcess.getOutCod());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "outTp":
                    situationAnalysisQM.setTarget("outTp");
                    situationAnalysisQM.setValue(sewProcess.getOutTp());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "outTn":
                    situationAnalysisQM.setTarget("outTn");
                    situationAnalysisQM.setValue(sewProcess.getOutTn());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "outSs":
                    situationAnalysisQM.setTarget("outSs");
                    situationAnalysisQM.setValue(sewProcess.getOutSs());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "aerobicPoolDoSecond":
                    situationAnalysisQM.setTarget("anoxicPoolDo");
                    situationAnalysisQM.setValue(sewProcess.getAerobicPoolDoSecond());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "aerobicPoolDo":
                    situationAnalysisQM.setTarget("aerobicPoolDo");
                    situationAnalysisQM.setValue(sewProcess.getAerobicPoolDo());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "anoxicPoolDoOutNit":
                    situationAnalysisQM.setTarget("anoxicPoolDoOutNit");
                    situationAnalysisQM.setValue(sewProcess.getAnoxicPoolDoOutNit());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "aerobicPoolNit":
                    situationAnalysisQM.setTarget("aerobicPoolNit");
                    situationAnalysisQM.setValue(sewProcess.getAerobicPoolNit());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "assInAmmonia":
                    situationAnalysisQM.setTarget("assInAmmonia");
                    situationAnalysisQM.setValue(sewSlu.getAssInAmmonia());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "assInCod":
                    situationAnalysisQM.setTarget("assInCod");
                    situationAnalysisQM.setValue(sewSlu.getAssInCod());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "assInTn":
                    situationAnalysisQM.setTarget("assInTn");
                    situationAnalysisQM.setValue(sewSlu.getAssInTn());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "assInTp":
                    situationAnalysisQM.setTarget("assInTp");
                    situationAnalysisQM.setValue(sewSlu.getAssInTp());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "assAnoxicPoolDoOutNit":
                    situationAnalysisQM.setTarget("assAnoxicPoolDoOutNit");
                    situationAnalysisQM.setValue(sewSlu.getAssAnoxicPoolDoOutNit());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "assAerobicPoolDoOutNit":
                    situationAnalysisQM.setTarget("assAerobicPoolDoOutNit");
                    situationAnalysisQM.setValue(sewSlu.getAssAerobicPoolDoOutNit());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "assOutAmmonia":
                    situationAnalysisQM.setTarget("assOutAmmonia");
                    situationAnalysisQM.setValue(sewSlu.getAssOutAmmonia());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "assOutCod":
                    situationAnalysisQM.setTarget("assOutCod");
                    situationAnalysisQM.setValue(sewSlu.getAssOutCod());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "assOutTn":
                    situationAnalysisQM.setTarget("assOutTn");
                    situationAnalysisQM.setValue(sewSlu.getAssOutTn());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "assOutTp":
                    situationAnalysisQM.setTarget("assOutTp");
                    situationAnalysisQM.setValue(sewSlu.getAssOutTp());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;

                case "corInCod":
                    situationAnalysisQM.setTarget("corInCod");
                    situationAnalysisQM.setValue(sewMeter.getCorInCod());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "corInTn":
                    situationAnalysisQM.setTarget("corInTn");
                    situationAnalysisQM.setValue(sewMeter.getCorInTn());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "corInTp":
                    situationAnalysisQM.setTarget("corInTp");
                    situationAnalysisQM.setValue(sewMeter.getCorInTp());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "corInAmmonia":
                    situationAnalysisQM.setTarget("corInAmmonia");
                    situationAnalysisQM.setValue(sewMeter.getCorInAmmonia());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "corAnoxicPoolDoOutNit":
                    situationAnalysisQM.setTarget("corAnoxicPoolDoOutNit");
                    situationAnalysisQM.setValue(sewMeter.getCorAnoxicPoolDoOutNit());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "corAerobicPoolDoOutNit":
                    situationAnalysisQM.setTarget("corAerobicPoolDoOutNit");
                    situationAnalysisQM.setValue(sewMeter.getCorAerobicPoolDoOutNit());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "corOutAmmonia":
                    situationAnalysisQM.setTarget("corOutAmmonia");
                    situationAnalysisQM.setValue(sewMeter.getCorOutAmmonia());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "corOutCod":
                    situationAnalysisQM.setTarget("corOutCod");
                    situationAnalysisQM.setValue(sewMeter.getCorOutCod());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "corOutTn":
                    situationAnalysisQM.setTarget("corOutTn");
                    situationAnalysisQM.setValue(sewMeter.getCorOutTn());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;
                case "corOutTp":
                    situationAnalysisQM.setTarget("corOutTp");
                    situationAnalysisQM.setValue(sewMeter.getCorOutTp());
                    situationAnalysisQMList.add(situationAnalysisQM);
                    break;

            }
        }
        return situationAnalysisQMList;
    }
}
