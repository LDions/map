package com.ruowei.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.*;
import com.ruowei.repository.CorrelationRepository;
import com.ruowei.repository.SewMeterRepository;
import com.ruowei.repository.SewProcessRepository;
import com.ruowei.repository.SewSluRepository;
import com.ruowei.service.SewEmiService;
import com.ruowei.web.rest.vm.SewEmiVM;
import com.ruowei.web.rest.vm.SituationAnalysisVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
    private final CorrelationRepository correlationRepository;
    private final SewProcessRepository sewProcessRepository;
    private final SewSluRepository sewSluRepository;
    private final SewMeterRepository sewMeterRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final QSewProcess qSewProcess = QSewProcess.sewProcess;
    private final QSewMeter qSewMeter = QSewMeter.sewMeter;
    private final QSewSlu qSewSlu = QSewSlu.sewSlu;

    public SelectUtil(ObjectMapper objectMapper, SewEmiService sewEmiService, CorrelationRepository correlationRepository, SewProcessRepository sewProcessRepository, SewSluRepository sewSluRepository, SewMeterRepository sewMeterRepository, JPAQueryFactory jpaQueryFactory) {
        this.objectMapper = objectMapper;
        this.sewEmiService = sewEmiService;
        this.correlationRepository = correlationRepository;
        this.sewProcessRepository = sewProcessRepository;
        this.sewSluRepository = sewSluRepository;
        this.sewMeterRepository = sewMeterRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<SituationAnalysisVM> getSome(String target, String beginTime, String endTime, String craftCode) {

        List<SituationAnalysisVM> situationAnalysisVMList = new ArrayList<>();
        List<SewProcess> sewProcessList = sewProcessRepository.findByDayTimeIsGreaterThanEqualAndDayTimeIsLessThanEqualAndCraftCode(sewEmiService.getInstant(beginTime)
            , sewEmiService.getInstant(endTime), craftCode);
        List<SewSlu> sewSluList = sewSluRepository.findByDayTimeIsGreaterThanEqualAndDayTimeIsLessThanEqualAndCraftCode(sewEmiService.getInstant(beginTime)
            , sewEmiService.getInstant(endTime), craftCode);
        List<SewMeter> sewMeterList = sewMeterRepository.findByDayTimeIsGreaterThanEqualAndDayTimeIsLessThanEqualAndCraftCode(sewEmiService.getInstant(beginTime)
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
            case "anoxicPoolDo":
                for (SewProcess sewProcess : sewProcessList) {
                    SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                    situationAnalysisVM.setTime(GregorianCalendar.from(ZonedDateTime.ofInstant(sewProcess.getDayTime(), ZoneId.systemDefault())));
                    situationAnalysisVM.setValue(sewProcess.getAnoxicPoolDo());
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
        }
        return situationAnalysisVMList;
    }
}
