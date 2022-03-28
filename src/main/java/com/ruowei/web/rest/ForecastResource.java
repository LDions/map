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
    private List<SewSlu> sewSlus;
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
    public JSONObject forecastAn(Long id,Instant hour,String craftCode) {
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
            .notEmptyAnd(qSewSlu.dayTime::goe, start)
            .notEmptyAnd(qSewSlu.dayTime::loe,hour)
            .notEmptyAnd(qSewSlu.craftCode::eq,craftCode);
        JPAQuery<SewSlu> jpaQuery1 = queryFactory
            .select(qSewSlu)
            .from(qSewSlu)
            .where(builder1.build());
        sewSlus = jpaQuery1.fetch();
        ammoniaNitrogenVM.setInflowList(this.getInflows(hour));
        //      判断是否试点
        Optional<Enterprise> enterprise = enterpriseRepository.findById(id);
        Boolean isTry;
        if (enterprise.isPresent()) {
            //            试点
            isTry = enterprise.get().getIsTry();
            if (isTry.equals(true)) {
                OptionalBooleanBuilder builder2 = new OptionalBooleanBuilder()
                    .notEmptyAnd(qSewMeter.dayTime::goe, start)
                    .notEmptyAnd(qSewMeter.dayTime::loe,hour)
                    .notEmptyAnd(qSewMeter.craftCode::eq,craftCode);
                JPAQuery<SewMeter> jpaQuery2 = queryFactory
                    .select(qSewMeter)
                    .from(qSewMeter)
                    .where(builder2.build());
                sewMeters = jpaQuery2.fetch();
                Iterator it = sewMeters.listIterator();
                time = hour;
                while (it.hasNext()) {
                    SewMeter s = (SewMeter) it.next();
                    if (s.getDayTime().equals(time)) {
                        time = time.plus(2,ChronoUnit.HOURS);
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
            } else {
//            非试点 用化验数据（机器人）
                Iterator it1 = sewSlus.iterator();
                time = hour;
                while (it1.hasNext()) {
                    SewSlu s = (SewSlu) it1.next();
                    if (s.getDayTime().equals(time)) {
                        time = time.plus(2,ChronoUnit.HOURS);
                        AmmoniaNitrogen ammoniaNitrogen = new AmmoniaNitrogen();
                        ammoniaNitrogen.setIn_cod(s.getAssInCod());
                        ammoniaNitrogen.setIn_tn(s.getAssInTn());
                        ammoniaNitrogen.setIn_ammomia(s.getAssInAmmonia());
                        ammoniaNitrogen.setOut_ammoni(s.getAssOutAmmonia());
                        ammoniaNitrogen.setTime(s.getDayTime());
                        ammoniaNitrogen.setCar_add(sewPot.getDayCarAdd());
                        ammoniaNitrogen.setAerobic_pool_ph(sewPot.getDayAerobicPoolPh());
                        ammoniaNitrogen.setAerobic_pool_sv(sewPot.getDayAerobicPoolSv());
                        ammoniaNitrogen.setAerobic_pool_ss(sewPot.getDayAerobicPoolMlss());
                        ammoniaNitrogen.setSecond_mud(sewPot.getDaySecondMud());
                        data.add(ammoniaNitrogen);
                    }
                }
            }
        }
//          30h仪表数据
        OptionalBooleanBuilder builder3 = new OptionalBooleanBuilder()
            .notEmptyAnd(qSewProcess.dayTime::goe, start)
            .notEmptyAnd(qSewProcess.dayTime::loe,hour)
            .notEmptyAnd(qSewProcess.craftCode::eq,craftCode);
        JPAQuery<SewProcess> jpaQuery3 = queryFactory
            .select(qSewProcess)
            .from(qSewProcess)
            .where(builder3.build());
        sewProcesses = jpaQuery3.fetch();
        time = hour;
        for (AmmoniaNitrogen ammoniaNitrogen : data) {
            Iterator it2 = sewProcesses.iterator();
            while (it2.hasNext()) {
                SewProcess s = (SewProcess) it2.next();
                if (s.getDayTime().equals(time)) {
                    time = time.plus(2,ChronoUnit.HOURS);
                    ammoniaNitrogen.setAerobic_pool_ss(s.getInSs());
                    ammoniaNitrogen.setAerobic_pool_do(s.getAerobicPoolDo());
                    break;
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
    public JSONObject forecastTn(Long id,Instant hour,String craftCode) {

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
            .notEmptyAnd(qSewSlu.dayTime::goe, start)
            .notEmptyAnd(qSewSlu.dayTime::loe,hour)
            .notEmptyAnd(qSewSlu.craftCode::eq,craftCode);
        JPAQuery<SewSlu> jpaQuery1 = queryFactory
            .select(qSewSlu)
            .from(qSewSlu)
            .where(builder1.build());
        sewSlus = jpaQuery1.fetch();
        totalNitrogenVM.setInflowList(this.getInflows(hour));
//      判断是否试点
        Optional<Enterprise> enterprise = enterpriseRepository.findById(id);
        Boolean isTry;
        if (enterprise.isPresent()) {
            //            试点
            isTry = enterprise.get().getIsTry();
            if (isTry.equals(true)) {
                OptionalBooleanBuilder builder2 = new OptionalBooleanBuilder()
                    .notEmptyAnd(qSewMeter.dayTime::goe, start)
                    .notEmptyAnd(qSewMeter.dayTime::loe,hour)
                    .notEmptyAnd(qSewMeter.craftCode::eq,craftCode);
                JPAQuery<SewMeter> jpaQuery2 = queryFactory
                    .select(qSewMeter)
                    .from(qSewMeter)
                    .where(builder2.build());
                sewMeters = jpaQuery2.fetch();
                time = hour;
                Iterator it = sewMeters.listIterator();
                time = hour;
                while (it.hasNext()) {
                    SewMeter s = (SewMeter) it.next();
                    if (s.getDayTime().equals(time)) {
                        time = time.plus(2,ChronoUnit.HOURS);
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
            } else {
//            非试点 用化验数据（机器人）
                Iterator it1 = sewSlus.iterator();
                time = hour;
                while (it1.hasNext()) {
                    SewMeter s = (SewMeter) it1.next();
                    if (s.getDayTime().equals(time)) {
                        time = time.plus(2,ChronoUnit.HOURS);
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
            }
        }
        //          30h仪表数据
        OptionalBooleanBuilder builder3 = new OptionalBooleanBuilder()
            .notEmptyAnd(qSewProcess.dayTime::goe, start)
            .notEmptyAnd(qSewProcess.dayTime::loe,hour)
            .notEmptyAnd(qSewProcess.craftCode::eq,craftCode);
        JPAQuery<SewProcess> jpaQuery3 = queryFactory
            .select(qSewProcess)
            .from(qSewProcess)
            .where(builder3.build());
        sewProcesses = jpaQuery3.fetch();
        Iterator it2 = sewProcesses.iterator();
        time = hour;
        for (TnData tnData : data) {
            while (it2.hasNext()) {
                SewProcess s = (SewProcess) it2.next();
                if (s.getDayTime().equals(time)) {
                    time = time.plus(2,ChronoUnit.HOURS);
                    tnData.setIn_flow(s.getInFlow());
                    tnData.setAerobic_pool_do(s.getAerobicPoolDo());
                    break;
                }
                it2.remove();
            }
        }

        totalNitrogenVM.setData(data);
        JSONObject obj = JSON.parseObject(String.valueOf(totalNitrogenVM));
        return obj;
    }

    //   进水参数list
    public List<Inflow> getInflows(Instant hour) {

        start = hour.minus(30, ChronoUnit.HOURS);

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
