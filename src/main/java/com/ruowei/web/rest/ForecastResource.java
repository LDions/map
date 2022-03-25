package com.ruowei.web.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.*;
import com.ruowei.repository.*;
import com.ruowei.util.OptionalBooleanBuilder;
import com.ruowei.web.rest.vm.AmmoniaNitrogenVM;
import com.ruowei.web.rest.vm.TotalNitrogenVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(tags = "预测计算")
public class ForecastResource {
    private final EnterpriseRepository enterpriseRepository;
    private final JPAQueryFactory queryFactory;
    private final QSewSlu qSewSlu = QSewSlu.sewSlu;
    private final QSewMeter qSewMeter = QSewMeter.sewMeter;
    private final QSewProcess qSewProcess = QSewProcess.sewProcess;
    private Instant start;
    private Instant end;
    private List<SewSlu> sewSlus;
    private List<SewMeter> sewMeters;
    private List<SewProcess> sewProcesses;
    private BigDecimal six = new BigDecimal(6);
    private QSewPot qSewPot = QSewPot.sewPot;
    private int i = 0;

    public ForecastResource(EnterpriseRepository enterpriseRepository, JPAQueryFactory queryFactory) {
        this.enterpriseRepository = enterpriseRepository;
        this.queryFactory = queryFactory;
    }

    //    SewProcess 仪表
    @PostMapping("/forecast/ammonia_nitrogen")
    @ApiOperation(value = "氨氮预测传参", notes = "作者：孙小楠")
    public JSONObject forecastAn(Long id) {
        sewSlus = null;
        sewMeters = null;
        sewProcesses = null;
        AmmoniaNitrogenVM ammoniaNitrogenVM = new AmmoniaNitrogenVM();
        //          取日报
        JPAQuery<SewPot> jpaQuery = queryFactory
            .select(qSewPot)
            .from(qSewPot)
            .orderBy(qSewPot.id.desc());
        SewPot sewPot = jpaQuery.fetchFirst();
        //          data参数
        List<AmmoniaNitrogen> data = new ArrayList<>();
        //          拿到30小时的所有机器人（化验）数据
        OptionalBooleanBuilder builder1 = new OptionalBooleanBuilder()
            .notEmptyAnd(qSewSlu.dayTime::gt, end);
        JPAQuery<SewSlu> jpaQuery1 = queryFactory
            .select(qSewSlu)
            .from(qSewSlu)
            .where(builder1.build());
        sewSlus = jpaQuery1.fetch();
        ammoniaNitrogenVM.setInflowList(this.getInflows());
        //      判断是否试点
        Optional<Enterprise> enterprise = enterpriseRepository.findById(id);
        Boolean isTry;
        if (enterprise.isPresent()) {
            //            试点
            isTry = enterprise.get().getIsTry();
            if (isTry.equals(true)) {
                OptionalBooleanBuilder builder = new OptionalBooleanBuilder()
                    .notEmptyAnd(qSewMeter.dayTime::gt, end);
                JPAQuery<SewMeter> jpaQuery2 = queryFactory
                    .select(qSewMeter)
                    .from(qSewMeter)
                    .where(builder1.build());
                sewMeters = jpaQuery2.fetch();
                Iterator it = sewMeters.listIterator();
                BigDecimal corInCod = null;
                BigDecimal inTn = null;
                BigDecimal assInAmmonia = null;
                BigDecimal assOutAmmonia = null;
                i = 0;
                while (it.hasNext()) {
                    i++;
                    SewMeter s = (SewMeter) it.next();
                    corInCod = corInCod.add(s.getCorInCod());
                    inTn = inTn.add(s.getCorInTn());
                    assInAmmonia = assInAmmonia.add(s.getCorInAmmonia());
                    assOutAmmonia = assOutAmmonia.add(s.getCorOutAmmonia());
                    if (i == 6) {
                        i = 0;
                        corInCod = corInCod.divide(six);
                        inTn = inTn.divide(six);
                        assInAmmonia = assInAmmonia.divide(six);
                        assOutAmmonia = assOutAmmonia.divide(six);
                        AmmoniaNitrogen ammoniaNitrogen = new AmmoniaNitrogen();
                        ammoniaNitrogen.setIn_cod(corInCod);
                        ammoniaNitrogen.setIn_tn(inTn);
                        ammoniaNitrogen.setIn_ammomia(assInAmmonia);
                        ammoniaNitrogen.setOut_ammoni(assOutAmmonia);
                        ammoniaNitrogen.setTime(s.getDayTime());
                        ammoniaNitrogen.setCar_add(sewPot.getDayCarAdd());
                        ammoniaNitrogen.setAerobic_pool_ph(sewPot.getDayAerobicPoolPh());
                        ammoniaNitrogen.setAerobic_pool_sv(sewPot.getDayAerobicPoolSv());
                        ammoniaNitrogen.setAerobic_pool_ss(sewPot.getDayAerobicPoolMlss());
                        ammoniaNitrogen.setSecond_mud(sewPot.getDaySecondMud());
                        data.add(ammoniaNitrogen);
                    }
                    it.remove();
                }
            } else {
//            非试点 用化验数据（机器人）
                Iterator it1 = sewSlus.iterator();
                BigDecimal corInCod = null;
                BigDecimal inTn = null;
                BigDecimal assInAmmonia = null;
                BigDecimal assOutAmmonia = null;
                i = 0;
                while (it1.hasNext()) {
                    i++;
                    SewSlu s = (SewSlu) it1.next();
                    corInCod = corInCod.add(s.getAssInCod());
                    inTn = inTn.add(s.getAssInTn());
                    assInAmmonia = assInAmmonia.add(s.getAssInAmmonia());
                    assOutAmmonia = assOutAmmonia.add(s.getAssOutAmmonia());
                    if (i == 6) {
                        i = 0;
                        corInCod = corInCod.divide(six);
                        inTn = inTn.divide(six);
                        assInAmmonia = assInAmmonia.divide(six);
                        assOutAmmonia = assOutAmmonia.divide(six);
                        AmmoniaNitrogen ammoniaNitrogen = new AmmoniaNitrogen();
                        ammoniaNitrogen.setIn_cod(corInCod);
                        ammoniaNitrogen.setIn_tn(inTn);
                        ammoniaNitrogen.setIn_ammomia(assInAmmonia);
                        ammoniaNitrogen.setOut_ammoni(assOutAmmonia);
                        ammoniaNitrogen.setTime(s.getDayTime());
                        ammoniaNitrogen.setCar_add(sewPot.getDayCarAdd());
                        ammoniaNitrogen.setAerobic_pool_ph(sewPot.getDayAerobicPoolPh());
                        ammoniaNitrogen.setAerobic_pool_sv(sewPot.getDayAerobicPoolSv());
                        ammoniaNitrogen.setAerobic_pool_ss(sewPot.getDayAerobicPoolMlss());
                        ammoniaNitrogen.setSecond_mud(sewPot.getDaySecondMud());
                        data.add(ammoniaNitrogen);
                    }
                    it1.remove();
                }
            }
        }
//          30h仪表数据
        OptionalBooleanBuilder builder3 = new OptionalBooleanBuilder()
            .notEmptyAnd(qSewProcess.dayTime::gt, end);
        JPAQuery<SewProcess> jpaQuery3 = queryFactory
            .select(qSewProcess)
            .from(qSewProcess)
            .where(builder3.build());
        sewProcesses = jpaQuery3.fetch();
        Iterator it2 = sewProcesses.iterator();
        BigDecimal aerobic_pool_do = null;
        BigDecimal aerobic_pool_ss = null;
        i = 0;
        for (AmmoniaNitrogen ammoniaNitrogen : data) {
            while (it2.hasNext()) {
                i++;
                SewProcess s = (SewProcess) it2.next();
                aerobic_pool_do = aerobic_pool_do.add(s.getAerobicPoolDo());
                aerobic_pool_ss = aerobic_pool_ss.add(s.getInSs());
                if (i == 6) {
                    i = 0;
                    aerobic_pool_do = aerobic_pool_do.divide(six);
                    aerobic_pool_ss = aerobic_pool_ss.divide(six);
                    ammoniaNitrogen.setAerobic_pool_ss(aerobic_pool_ss);
                    ammoniaNitrogen.setAerobic_pool_do(aerobic_pool_do);
                }
                it2.remove();
            }
        }
        ammoniaNitrogenVM.setData(data);
        JSONObject obj = JSON.parseObject(String.valueOf(ammoniaNitrogenVM));
        return obj;
    }

    @PostMapping("/forecast/total_nitrogen")
    @ApiOperation(value = "总氮预测传参", notes = "作者：孙小楠")
    public JSONObject forecastTn(Long id) {

        sewSlus = null;
        sewMeters = null;
        sewProcesses = null;
        TotalNitrogenVM totalNitrogenVM = new TotalNitrogenVM();
        //          取日报
        JPAQuery<SewPot> jpaQuery = queryFactory
            .select(qSewPot)
            .from(qSewPot)
            .orderBy(qSewPot.id.desc());
        SewPot sewPot = jpaQuery.fetchFirst();
        //          data参数
        List<TnData> data = new ArrayList<>();
        //          拿到30小时的所有机器人（化验）数据
        OptionalBooleanBuilder builder1 = new OptionalBooleanBuilder()
            .notEmptyAnd(qSewSlu.dayTime::gt, end);
        JPAQuery<SewSlu> jpaQuery1 = queryFactory
            .select(qSewSlu)
            .from(qSewSlu)
            .where(builder1.build());
        sewSlus = jpaQuery1.fetch();
        totalNitrogenVM.setInflowList(this.getInflows());
//      判断是否试点
        Optional<Enterprise> enterprise = enterpriseRepository.findById(id);
        Boolean isTry;
        if (enterprise.isPresent()) {
            //            试点
            isTry = enterprise.get().getIsTry();
            if (isTry.equals(true)) {
                OptionalBooleanBuilder builder2 = new OptionalBooleanBuilder()
                    .notEmptyAnd(qSewMeter.dayTime::gt, end);
                JPAQuery<SewMeter> jpaQuery2 = queryFactory
                    .select(qSewMeter)
                    .from(qSewMeter)
                    .where(builder2.build());
                sewMeters = jpaQuery2.fetch();

                Iterator it = sewMeters.listIterator();
                BigDecimal assInCod = null;
                BigDecimal inTn = null;
                BigDecimal assAnoxicPoolDoOutNit = null;
                BigDecimal assAerobicPoolDoOutNit = null;
                BigDecimal assOutTn = null;
                i = 0;
                while (it.hasNext()) {
                    i++;
                    SewMeter s = (SewMeter) it.next();
                    assInCod = assInCod.add(s.getAssInCod());
                    inTn = inTn.add(s.getAssInTn());
                    assAnoxicPoolDoOutNit = assAnoxicPoolDoOutNit.add(s.getAssAnoxicPoolDoOutNit());
                    assAerobicPoolDoOutNit = assAerobicPoolDoOutNit.add(s.getAssAerobicPoolDoOutNit());
                    assOutTn = assOutTn.add(s.getAssOutTn());
                    if (i == 6) {
                        i = 0;
                        assInCod = assInCod.divide(six);
                        inTn = inTn.divide(six);
                        assAnoxicPoolDoOutNit = assAnoxicPoolDoOutNit.divide(six);
                        assAerobicPoolDoOutNit = assAerobicPoolDoOutNit.divide(six);
                        assOutTn = assOutTn.divide(six);
                        TnData tnData = new TnData();
                        tnData.setTime(s.getDayTime());
                        tnData.setIn_tn(inTn);
                        tnData.setIn_cod(assInCod);
                        tnData.setAnoxic_pool_do_out_nit(assAnoxicPoolDoOutNit);
                        tnData.setAerobic_pool_nit(assAerobicPoolDoOutNit);
                        tnData.setOut_tn(assOutTn);
                        tnData.setAnaerobic_pool_do(sewPot.getDayAnaerobicPoolDo());
                        tnData.setCar_add(sewPot.getDayCarAdd());
                        tnData.setAerobic_pool_temper(sewPot.getDayAerobicPoolTemper());
                        tnData.setAerobic_pool_mlss(sewPot.getDayAerobicPoolMlss());
                        data.add(tnData);
                    }
                    it.remove();
                }
            } else {
//            非试点 用化验数据（机器人）
                Iterator it1 = sewSlus.iterator();
                BigDecimal assInCod = null;
                BigDecimal inTn = null;
                BigDecimal assAnoxicPoolDoOutNit = null;
                BigDecimal assAerobicPoolDoOutNit = null;
                BigDecimal assOutTn = null;
                i = 0;
                while (it1.hasNext()) {
                    i++;
                    SewMeter s = (SewMeter) it1.next();
                    assInCod = assInCod.add(s.getAssInCod());
                    inTn = inTn.add(s.getAssInTn());
                    assAnoxicPoolDoOutNit = assAnoxicPoolDoOutNit.add(s.getAssAnoxicPoolDoOutNit());
                    assAerobicPoolDoOutNit = assAerobicPoolDoOutNit.add(s.getAssAerobicPoolDoOutNit());
                    assOutTn = assOutTn.add(s.getAssOutTn());
                    if (i == 6) {
                        i = 0;
                        assInCod = assInCod.divide(six);
                        inTn = inTn.divide(six);
                        assAnoxicPoolDoOutNit = assAnoxicPoolDoOutNit.divide(six);
                        assAerobicPoolDoOutNit = assAerobicPoolDoOutNit.divide(six);
                        assOutTn = assOutTn.divide(six);
                        TnData tnData = new TnData();
                        tnData.setTime(s.getDayTime());
                        tnData.setIn_tn(inTn);
                        tnData.setIn_cod(assInCod);
                        tnData.setAnoxic_pool_do_out_nit(assAnoxicPoolDoOutNit);
                        tnData.setAerobic_pool_nit(assAerobicPoolDoOutNit);
                        tnData.setOut_tn(assOutTn);
                        tnData.setAnaerobic_pool_do(sewPot.getDayAnaerobicPoolDo());
                        tnData.setCar_add(sewPot.getDayCarAdd());
                        tnData.setAerobic_pool_temper(sewPot.getDayAerobicPoolTemper());
                        tnData.setAerobic_pool_mlss(sewPot.getDayAerobicPoolMlss());
                        data.add(tnData);
                    }
                    it1.remove();
                }
            }
        }
        //          30h仪表数据
        OptionalBooleanBuilder builder3 = new OptionalBooleanBuilder()
            .notEmptyAnd(qSewProcess.dayTime::gt, end);
        JPAQuery<SewProcess> jpaQuery3 = queryFactory
            .select(qSewProcess)
            .from(qSewProcess)
            .where(builder3.build());
        sewProcesses = jpaQuery3.fetch();
        Iterator it2 = sewProcesses.iterator();
        BigDecimal in_flow = null;
        BigDecimal aerobic_pool_do = null;
        i = 0;
        for (TnData tnData : data) {
            while (it2.hasNext()) {
                i++;
                SewProcess s = (SewProcess) it2.next();
                in_flow = in_flow.add(s.getInFlow());
                aerobic_pool_do = aerobic_pool_do.add(s.getAerobicPoolDo());
                if (i == 6) {
                    i = 0;
                    in_flow = in_flow.divide(six);
                    aerobic_pool_do = aerobic_pool_do.divide(six);
                    tnData.setIn_flow(in_flow);
                    tnData.setAerobic_pool_do(aerobic_pool_do);
                }
                it2.remove();
            }
        }

        totalNitrogenVM.setData(data);
        JSONObject obj = JSON.parseObject(String.valueOf(totalNitrogenVM));
        return obj;
    }

    //   进水参数list
    public List<Inflow> getInflows() {
        JPAQuery<SewSlu> jpaQuery = queryFactory
            .select(qSewSlu)
            .from(qSewSlu)
            .orderBy(qSewSlu.id.desc());
        start = jpaQuery.fetchFirst().getDayTime();
        end = start.minus(30, ChronoUnit.HOURS);

        List<Inflow> inflowList = new ArrayList<>();
        sewSlus.stream().map(sewSlu -> {
            Inflow inflow = new Inflow();
            //          获取二十分钟前的
            Instant before = sewSlu.getDayTime().minus(20, ChronoUnit.MINUTES);
            //            每分钟
            for (int i = 1; i <= 20; i++) {
                Instant instant = before.plus(1, ChronoUnit.MINUTES);
                inflow.setTime(instant);
                inflow.setIn_flow(sewSlu.getAssInFlow());
                inflowList.add(inflow);
            }
            return null;
        }).collect(Collectors.toList());
        return inflowList;
    }
}
