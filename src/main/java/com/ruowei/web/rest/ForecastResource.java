package com.ruowei.web.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.*;
import com.ruowei.repository.EnterpriseRepository;
import com.ruowei.util.OptionalBooleanBuilder;
import com.ruowei.web.rest.vm.AmmoniaNitrogenVM;
import com.ruowei.web.rest.vm.TotalNitrogenVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(tags = "预测计算")
public class ForecastResource {
    private final EnterpriseRepository enterpriseRepository;
    private final JPAQueryFactory queryFactory;
    private final QSewMeter qSewMeter = QSewMeter.sewMeter;
    private final QSewProcess qSewProcess = QSewProcess.sewProcess;
    private List<SewMeter> sewMeters;
    private List<SewProcess> sewProcesses;
    private QSewPot qSewPot = QSewPot.sewPot;
    private Instant time;

    public ForecastResource(EnterpriseRepository enterpriseRepository, JPAQueryFactory queryFactory) {
        this.enterpriseRepository = enterpriseRepository;
        this.queryFactory = queryFactory;
    }

    //    SewProcess 仪表
    @PostMapping("/forecast/ammonia_nitrogen")
    @ApiOperation(value = "氨氮预测传参", notes = "作者：孙小楠")
    public List<String> forecastAn(Long id, @RequestParam(value = "hours", required = false) List<Instant> hours, String craftCode) {
        AmmoniaNitrogenVM ammoniaNitrogenVM = new AmmoniaNitrogenVM();
        List<String> result = new ArrayList<>();
        for (Instant hour : hours) {
            Date date = Date.from(hour);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = format.format(date);
            sewMeters = null;
            sewProcesses = null;
            //          取日报
            JPAQuery<SewPot> jpaQuery = queryFactory
                .select(qSewPot)
                .from(qSewPot)
                .where(qSewPot.dayTime.stringValue().contains(formatDate))
                .orderBy(qSewPot.id.desc());
            SewPot sewPot = jpaQuery.fetchFirst();
            //          data参数
            List<AmmoniaNitrogen> data = new ArrayList<>();
            ammoniaNitrogenVM.setInflowList(this.getInflows(hour, craftCode));
            //      判断是否试点
            Optional<Enterprise> enterprise = enterpriseRepository.findById(id);
            Boolean isTry;
            if (enterprise.isPresent()) {
                //            试点
                isTry = enterprise.get().getIsTry();
                if (isTry.equals(true)) {
                    OptionalBooleanBuilder builder1 = new OptionalBooleanBuilder()
                        .notEmptyAnd(qSewMeter.dayTime::goe, hour.minus(30, ChronoUnit.HOURS))
                        .notEmptyAnd(qSewMeter.dayTime::loe, hour)
                        .notEmptyAnd(qSewMeter.craftCode::eq, craftCode);
                    JPAQuery<SewMeter> jpaQuery1 = queryFactory
                        .select(qSewMeter)
                        .from(qSewMeter)
                        .where(builder1.build());
                    sewMeters = jpaQuery1.fetch();
                    time = hour;
                    for (SewMeter s : sewMeters) {
                        if (s.getDayTime().equals(time)) {
                            time = time.plus(2, ChronoUnit.HOURS);
                            AmmoniaNitrogen ammoniaNitrogen = new AmmoniaNitrogen();
                            ammoniaNitrogen.setIn_cod(s.getCorInCod());
                            ammoniaNitrogen.setIn_tn(s.getCorInTn());
                            ammoniaNitrogen.setIn_ammomia(s.getCorInAmmonia());
                            ammoniaNitrogen.setOut_ammoni(s.getCorOutAmmonia());
                            ammoniaNitrogen.setTime(s.getDayTime());
                            ammoniaNitrogen.setCar_add(sewPot.getDayCarAdd());
                            ammoniaNitrogen.setAerobic_pool_ph(sewPot.getDayAerobicPoolPh());
                            ammoniaNitrogen.setAerobic_pool_sv(sewPot.getDayAerobicPoolSv());
                            ammoniaNitrogen.setAerobic_pool_ss(sewPot.getDayAerobicPoolMlss());
                            ammoniaNitrogen.setSecond_mud(sewPot.getDaySecondMud());
                            data.add(ammoniaNitrogen);
                        }
                    }
                    List<SewProcess> sewProcesses1 = sewProcesses;
                    time = hour;
                    for (AmmoniaNitrogen ammoniaNitrogen : data) {
                        Iterator it = sewProcesses1.iterator();
                        while (it.hasNext()) {
                            SewProcess s = (SewProcess) it.next();
                            if (s.getDayTime().equals(time)) {
                                time = time.plus(2, ChronoUnit.HOURS);
                                ammoniaNitrogen.setAerobic_pool_ss(s.getInSs());
                                ammoniaNitrogen.setAerobic_pool_do(s.getAerobicPoolDo());
                                break;
                            }
                            it.remove();
                        }
                    }
                } else {
//            非试点 用仪表
                    time = hour;
                    for (SewProcess s : sewProcesses) {
                        if (s.getDayTime().equals(time)) {
                            time = time.plus(2, ChronoUnit.HOURS);
                            AmmoniaNitrogen ammoniaNitrogen = new AmmoniaNitrogen();
                            ammoniaNitrogen.setIn_cod(s.getInCod());
                            ammoniaNitrogen.setIn_tn(s.getInTn());
                            ammoniaNitrogen.setIn_ammomia(s.getInAmmonia());
                            ammoniaNitrogen.setOut_ammoni(s.getOutAmmonia());
                            ammoniaNitrogen.setTime(s.getDayTime());
                            ammoniaNitrogen.setCar_add(sewPot.getDayCarAdd());
                            ammoniaNitrogen.setAerobic_pool_ph(sewPot.getDayAerobicPoolPh());
                            ammoniaNitrogen.setAerobic_pool_sv(sewPot.getDayAerobicPoolSv());
                            ammoniaNitrogen.setAerobic_pool_ss(sewPot.getDayAerobicPoolMlss());
                            ammoniaNitrogen.setSecond_mud(sewPot.getDaySecondMud());
                            ammoniaNitrogen.setAerobic_pool_ss(s.getInSs());
                            ammoniaNitrogen.setAerobic_pool_do(s.getAerobicPoolDo());
                            data.add(ammoniaNitrogen);
                        }
                    }
                }
            }
            ammoniaNitrogenVM.setData(data);
            JSONObject obj = JSON.parseObject(JSONObject.toJSONString(ammoniaNitrogenVM));
            String a = "0.503472";
            result.add(a);
        }
        return result;
    }

    @PostMapping("/forecast/total_nitrogen")
    @ApiOperation(value = "总氮预测传参", notes = "作者：孙小楠")
    public List<String> forecastTn(Long id, @RequestParam(value = "hours", required = false) List<Instant> hours, String craftCode) {
        TotalNitrogenVM totalNitrogenVM = new TotalNitrogenVM();
        List<String> result = new ArrayList<>();
        for (Instant hour : hours) {
            Date date = Date.from(hour);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = format.format(date);
            sewMeters = null;
            sewProcesses = null;
            //          取日报
            JPAQuery<SewPot> jpaQuery = queryFactory
                .select(qSewPot)
                .from(qSewPot)
                .where(qSewPot.dayTime.stringValue().contains(formatDate))
                .orderBy(qSewPot.id.desc());
            SewPot sewPot = jpaQuery.fetchFirst();
            //          data参数
            List<TnData> data = new ArrayList<>();
            totalNitrogenVM.setInflowList(this.getInflows(hour, craftCode));
//      判断是否试点
            Optional<Enterprise> enterprise = enterpriseRepository.findById(id);
            Boolean isTry;
            if (enterprise.isPresent()) {
                //            试点
                isTry = enterprise.get().getIsTry();
                if (isTry) {
                    OptionalBooleanBuilder builder1 = new OptionalBooleanBuilder()
                        .notEmptyAnd(qSewMeter.dayTime::goe, hour.minus(30, ChronoUnit.HOURS))
                        .notEmptyAnd(qSewMeter.dayTime::loe, hour)
                        .notEmptyAnd(qSewMeter.craftCode::eq, craftCode);
                    JPAQuery<SewMeter> jpaQuery1 = queryFactory
                        .select(qSewMeter)
                        .from(qSewMeter)
                        .where(builder1.build());
                    sewMeters = jpaQuery1.fetch();
                    time = hour;
                    for (SewMeter s : sewMeters) {
                        if (s.getDayTime().equals(time)) {
                            time = time.plus(2, ChronoUnit.HOURS);
                            TnData tnData = new TnData();
                            tnData.setTime(s.getDayTime());
                            tnData.setIn_tn(s.getCorInTn());
                            tnData.setIn_cod(s.getCorInCod());
                            tnData.setAnoxic_pool_do_out_nit(s.getCorAnoxicPoolDoOutNit());
                            tnData.setAerobic_pool_nit(s.getCorAerobicPoolDoOutNit());
                            tnData.setOut_tn(s.getCorOutTn());
                            tnData.setAnaerobic_pool_do(sewPot.getDayAnaerobicPoolDo());
                            tnData.setCar_add(sewPot.getDayCarAdd());
                            tnData.setAerobic_pool_temper(sewPot.getDayAerobicPoolTemper());
                            tnData.setAerobic_pool_mlss(sewPot.getDayAerobicPoolMlss());
                            data.add(tnData);
                        }
                    }
                    List<SewProcess> sewProcesses1 = sewProcesses;
                    time = hour;
                    for (TnData tnData : data) {
                        Iterator it1 = sewProcesses1.iterator();
                        while (it1.hasNext()) {
                            SewProcess s = (SewProcess) it1.next();
                            if (s.getDayTime().equals(time)) {
                                time = time.plus(2, ChronoUnit.HOURS);
                                tnData.setIn_flow(s.getInFlow());
                                tnData.setAerobic_pool_do(s.getAerobicPoolDo());
                                break;
                            }
                            it1.remove();
                        }
                    }
                } else {
//            非试点 用化验数据（机器人）
                    time = hour;
                    for (SewProcess s : sewProcesses) {
                        if (s.getDayTime().equals(time)) {
                            time = time.plus(2, ChronoUnit.HOURS);
                            TnData tnData = new TnData();
                            tnData.setTime(s.getDayTime());
                            tnData.setIn_tn(s.getInTn());
                            tnData.setIn_cod(s.getInCod());
                            tnData.setIn_flow(s.getInFlow());
                            tnData.setAnoxic_pool_do_out_nit(s.getAnoxicPoolDoOutNit());
                            tnData.setAerobic_pool_nit(s.getAerobicPoolNit());
                            tnData.setOut_tn(s.getOutTn());
                            tnData.setAnaerobic_pool_do(sewPot.getDayAnaerobicPoolDo());
                            tnData.setCar_add(sewPot.getDayCarAdd());
                            tnData.setAerobic_pool_temper(sewPot.getDayAerobicPoolTemper());
                            tnData.setAerobic_pool_mlss(sewPot.getDayAerobicPoolMlss());
                            tnData.setAerobic_pool_do(s.getAerobicPoolDo());
                            data.add(tnData);
                        }
                    }
                }
            }
            totalNitrogenVM.setData(data);
            JSONObject obj = JSON.parseObject(JSONObject.toJSONString(totalNitrogenVM));
            String a = "0.503472";
            result.add(a);
        }
        return result;
    }

    //   进水参数list
    public List<Inflow> getInflows(Instant hour, String craftCode) {
//          30h仪表数据
        OptionalBooleanBuilder builder3 = new OptionalBooleanBuilder()
            .notEmptyAnd(qSewProcess.dayTime::goe, hour.minus(30, ChronoUnit.HOURS))
            .notEmptyAnd(qSewProcess.dayTime::loe, hour)
            .notEmptyAnd(qSewProcess.craftCode::eq, craftCode);
        JPAQuery<SewProcess> jpaQuery3 = queryFactory
            .select(qSewProcess)
            .from(qSewProcess)
            .where(builder3.build());
        sewProcesses = jpaQuery3.fetch();
        return sewProcesses.stream().map(sewProcess -> {
            //          获取二十分钟前的
            Inflow inflow = new Inflow();
            Instant before = sewProcess.getDayTime().minus(20, ChronoUnit.MINUTES);
            //            每分钟
            for (int i = 1; i <= 20; i++) {
                Instant instant = before.plus(1, ChronoUnit.MINUTES);
                inflow.setTime(instant);

                inflow.setIn_flow(sewProcess.getInFlow());
            }
            return inflow;
        }).collect(Collectors.toList());
    }
}
