package com.ruowei.web.rest;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.service.SewStatisticsService;
import com.ruowei.util.DateUtil;
import com.ruowei.util.OptionalBooleanBuilder;
import com.ruowei.web.rest.dto.EmiStatisticsDTO;
import com.ruowei.service.dto.SewEmiQueryDTO;
import com.ruowei.web.rest.dto.SewEmiTableDTO;
import com.ruowei.web.rest.vm.EmiStatisticsQM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api")
@Api(tags = "碳排放数据统计")
public class EmiStatisticsResource {

    private final Logger log = LoggerFactory.getLogger(EmiStatisticsResource.class);
    private final JPAQueryFactory jpaQueryFactory;
    private final SewStatisticsService sewStatisticsService;

    public EmiStatisticsResource(JPAQueryFactory jpaQueryFactory, SewStatisticsService sewStatisticsService) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.sewStatisticsService = sewStatisticsService;
    }

    @GetMapping("/carbon-emission-data/statistics")
    @ApiOperation(value = "碳排放数据统计接口", notes = "作者：林宏栋")
    public ResponseEntity<EmiStatisticsDTO> emissionStatistics(EmiStatisticsQM qm) throws ExecutionException, InterruptedException {
        // 折线图X轴日期参数
        List<String> dateList = new ArrayList<>();
        List<String> accTimeList = new ArrayList<>();
        if (StringUtils.isNotBlank(qm.getAccYearFrom()) && StringUtils.isNotBlank(qm.getAccYearTo())) {
            IntStream.range(Integer.parseInt(qm.getAccYearFrom()), Integer.parseInt(qm.getAccYearTo()) + 1).boxed().forEach(year -> {
                IntStream.range(1, 13).boxed().forEach(month -> {
                    dateList.add(String.valueOf(year).concat("-").concat(String.format("%02d", month)));
                    accTimeList.add(String.valueOf(year).concat(String.format("%02d", month)));
                });
            });
        }else if (StringUtils.isNotBlank(qm.getAccTimeFrom()) && StringUtils.isNotBlank(qm.getAccTimeTo())) {
            List<String> list = DateUtil.getEachYearAndMonth(qm.getAccTimeFrom(), qm.getAccTimeTo());
            dateList.addAll(list);
            accTimeList.addAll(list.stream().map(str -> str.substring(0, 4).concat(str.substring(5))).collect(Collectors.toList()));
        }
        EmiStatisticsDTO dto = new EmiStatisticsDTO();

        SewEmiTableDTO sewEmiTableDTO = new SewEmiTableDTO(new BigDecimal(0));
        List<BigDecimal> level1PotEmiList = new ArrayList<>();
        List<BigDecimal> level2PotEmiList = new ArrayList<>();
        List<BigDecimal> level3PotEmiList = new ArrayList<>();
        List<BigDecimal> sluTreatPotEmiList = new ArrayList<>();
        List<BigDecimal> totalPotEmiList = new ArrayList<>();
        List<BigDecimal> inletPumpPowEmiList = new ArrayList<>();
        List<BigDecimal> blowerPowEmiList = new ArrayList<>();
        List<BigDecimal> retSluPumpPowEmiList = new ArrayList<>();
        List<BigDecimal> sluTreatPowEmiList = new ArrayList<>();
        List<BigDecimal> facilityPowEmiList = new ArrayList<>();
        List<BigDecimal> disinfectPowEmiList = new ArrayList<>();
        List<BigDecimal> otherPowEmiList = new ArrayList<>();
        List<BigDecimal> totalPowEmiList = new ArrayList<>();
        List<BigDecimal> sewTreatCh4EmiList = new ArrayList<>();
        List<BigDecimal> sewTreatN2oEmiList = new ArrayList<>();
        List<BigDecimal> totalSewTreatEmiList = new ArrayList<>();
        List<BigDecimal> sluHandleCh4EmiList = new ArrayList<>();
        List<BigDecimal> sluHandleN2oEmiList = new ArrayList<>();
        List<BigDecimal> totalSluHandleDirEmiList = new ArrayList<>();
        List<BigDecimal> sluHandlePotEmiList = new ArrayList<>();
        List<BigDecimal> sluHandlePowEmiList = new ArrayList<>();
        List<BigDecimal> totalSluHandleIndirEmiList = new ArrayList<>();
        List<BigDecimal> solarPowRedList = new ArrayList<>();
        List<BigDecimal> heatPumpRedList = new ArrayList<>();
        List<BigDecimal> thermoElecRedList = new ArrayList<>();
        List<BigDecimal> thermoEnerRedList = new ArrayList<>();
        List<BigDecimal> otherEmiRedList = new ArrayList<>();
        List<BigDecimal> windPowRedList = new ArrayList<>();
        List<BigDecimal> ecoComplexRedList = new ArrayList<>();
        List<BigDecimal> carbonEmiList = new ArrayList<>();
        List<BigDecimal> carbonRedList = new ArrayList<>();
        List<BigDecimal> carbonDirEmiList = new ArrayList<>();
        List<BigDecimal> carbonIndirEmiList = new ArrayList<>();

        List<BigDecimal> sluTreatIndirEmiList = new ArrayList<>();
        List<BigDecimal> sluHandleEmiList = new ArrayList<>();


        List<CompletableFuture<SewEmiQueryDTO>> futures = new ArrayList<>();

//        for (String accTime : accTimeList) {
//            // 拼接查询条件
//            OptionalBooleanBuilder builder = new OptionalBooleanBuilder()
//                .notEmptyAnd(qSewEmi.enterpriseId::eq, qm.getEnterpriseId())
//                .notEmptyAnd(qSewEmi.accTime::eq, accTime);
//            // 查询碳排放数据
//            futures.add(CompletableFuture.supplyAsync(() ->
//                jpaQueryFactory.select(Projections.bean(SewEmiQueryDTO.class, qSewEmi.accYear, qSewEmi.accMonth,
//                    qSewEmi.level1PotEmi, qSewEmi.level2PotEmi, qSewEmi.level3PotEmi, qSewEmi.sluTreatPotEmi,
//                    qSewEmi.totalPotEmi, qSewEmi.inletPumpPowEmi, qSewEmi.blowerPowEmi, qSewEmi.retSluPumpPowEmi,
//                    qSewEmi.sluTreatPowEmi, qSewEmi.facilityPowEmi, qSewEmi.disinfectPowEmi, qSewEmi.otherPowEmi,
//                    qSewEmi.totalPowEmi, qSewEmi.sewTreatCh4Emi, qSewEmi.sewTreatN2oEmi, qSewEmi.totalSewTreatEmi,
//                    qSewEmi.sluHandleCh4Emi, qSewEmi.sluHandleN2oEmi, qSewEmi.totalSluHandleDirEmi, qSewEmi.sluHandlePotEmi,
//                    qSewEmi.sluHandlePowEmi, qSewEmi.totalSluHandleIndirEmi, qSewEmi.solarPowRed, qSewEmi.heatPumpRed,
//                    qSewEmi.thermoElecRed, qSewEmi.thermoEnerRed, qSewEmi.otherEmiRed, qSewEmi.windPowRed,
//                    qSewEmi.ecoComplexRed, qSewEmi.carbonEmi, qSewEmi.carbonRed, qSewEmi.carbonDirEmi, qSewEmi.carbonIndirEmi))
//                    .from(qSewEmi)
//                    .where(builder.build())
//                    .fetchFirst()
//            ));
//        }

        for (CompletableFuture<SewEmiQueryDTO> future : futures) {
            SewEmiQueryDTO tableDto = future.get();
            if (tableDto == null) {
                tableDto = new SewEmiQueryDTO(new BigDecimal(0));
            }
            sewEmiTableDTO.setLevel1PotEmi(sewEmiTableDTO.getLevel1PotEmi().add(sewStatisticsService.kgToT(tableDto.getLevel1PotEmi())));
            level1PotEmiList.add(sewStatisticsService.kgToT(tableDto.getLevel1PotEmi()));
            sewEmiTableDTO.setLevel2PotEmi(sewEmiTableDTO.getLevel2PotEmi().add(sewStatisticsService.kgToT(tableDto.getLevel2PotEmi())));
            level2PotEmiList.add(sewStatisticsService.kgToT(tableDto.getLevel2PotEmi()));
            sewEmiTableDTO.setLevel3PotEmi(sewEmiTableDTO.getLevel3PotEmi().add(sewStatisticsService.kgToT(tableDto.getLevel3PotEmi())));
            level3PotEmiList.add(sewStatisticsService.kgToT(tableDto.getLevel3PotEmi()));
            sewEmiTableDTO.setSluTreatPotEmi(sewEmiTableDTO.getSluTreatPotEmi().add(sewStatisticsService.kgToT(tableDto.getSluTreatPotEmi())));
            sluTreatPotEmiList.add(sewStatisticsService.kgToT(tableDto.getSluTreatPotEmi()));
            sewEmiTableDTO.setTotalPotEmi(sewEmiTableDTO.getTotalPotEmi().add(sewStatisticsService.kgToT(tableDto.getTotalPotEmi())));
            totalPotEmiList.add(sewStatisticsService.kgToT(tableDto.getTotalPotEmi()));
            sewEmiTableDTO.setInletPumpPowEmi(sewEmiTableDTO.getInletPumpPowEmi().add(sewStatisticsService.kgToT(tableDto.getInletPumpPowEmi())));
            inletPumpPowEmiList.add(sewStatisticsService.kgToT(tableDto.getInletPumpPowEmi()));
            sewEmiTableDTO.setBlowerPowEmi(sewEmiTableDTO.getBlowerPowEmi().add(sewStatisticsService.kgToT(tableDto.getBlowerPowEmi())));
            blowerPowEmiList.add(sewStatisticsService.kgToT(tableDto.getBlowerPowEmi()));
            sewEmiTableDTO.setRetSluPumpPowEmi(sewEmiTableDTO.getRetSluPumpPowEmi().add(sewStatisticsService.kgToT(tableDto.getRetSluPumpPowEmi())));
            retSluPumpPowEmiList.add(sewStatisticsService.kgToT(tableDto.getRetSluPumpPowEmi()));
            sewEmiTableDTO.setSluTreatPowEmi(sewEmiTableDTO.getSluTreatPowEmi().add(sewStatisticsService.kgToT(tableDto.getSluTreatPowEmi())));
            sluTreatPowEmiList.add(sewStatisticsService.kgToT(tableDto.getSluTreatPowEmi()));
            sewEmiTableDTO.setFacilityPowEmi(sewEmiTableDTO.getFacilityPowEmi().add(sewStatisticsService.kgToT(tableDto.getFacilityPowEmi())));
            facilityPowEmiList.add(sewStatisticsService.kgToT(tableDto.getFacilityPowEmi()));
            sewEmiTableDTO.setDisinfectPowEmi(sewEmiTableDTO.getDisinfectPowEmi().add(sewStatisticsService.kgToT(tableDto.getDisinfectPowEmi())));
            disinfectPowEmiList.add(sewStatisticsService.kgToT(tableDto.getDisinfectPowEmi()));
            sewEmiTableDTO.setOtherPowEmi(sewEmiTableDTO.getOtherPowEmi().add(sewStatisticsService.kgToT(tableDto.getOtherPowEmi())));
            otherPowEmiList.add(sewStatisticsService.kgToT(tableDto.getOtherPowEmi()));
            sewEmiTableDTO.setTotalPowEmi(sewEmiTableDTO.getTotalPowEmi().add(sewStatisticsService.kgToT(tableDto.getTotalPowEmi())));
            totalPowEmiList.add(sewStatisticsService.kgToT(tableDto.getTotalPowEmi()));
            sewEmiTableDTO.setSewTreatCh4Emi(sewEmiTableDTO.getSewTreatCh4Emi().add(sewStatisticsService.kgToT(tableDto.getSewTreatCh4Emi())));
            sewTreatCh4EmiList.add(sewStatisticsService.kgToT(tableDto.getSewTreatCh4Emi()));
            sewEmiTableDTO.setSewTreatN2oEmi(sewEmiTableDTO.getSewTreatN2oEmi().add(sewStatisticsService.kgToT(tableDto.getSewTreatN2oEmi())));
            sewTreatN2oEmiList.add(sewStatisticsService.kgToT(tableDto.getSewTreatN2oEmi()));
            sewEmiTableDTO.setTotalSewTreatEmi(sewEmiTableDTO.getTotalSewTreatEmi().add(sewStatisticsService.kgToT(tableDto.getTotalSewTreatEmi())));
            totalSewTreatEmiList.add(sewStatisticsService.kgToT(tableDto.getTotalSewTreatEmi()));
            sewEmiTableDTO.setSluHandleCh4Emi(sewEmiTableDTO.getSluHandleCh4Emi().add(sewStatisticsService.kgToT(tableDto.getSluHandleCh4Emi())));
            sluHandleCh4EmiList.add(sewStatisticsService.kgToT(tableDto.getSluHandleCh4Emi()));
            sewEmiTableDTO.setSluHandleN2oEmi(sewEmiTableDTO.getSluHandleN2oEmi().add(sewStatisticsService.kgToT(tableDto.getSluHandleN2oEmi())));
            sluHandleN2oEmiList.add(sewStatisticsService.kgToT(tableDto.getSluHandleN2oEmi()));
            sewEmiTableDTO.setTotalSluHandleDirEmi(sewEmiTableDTO.getTotalSluHandleDirEmi().add(sewStatisticsService.kgToT(tableDto.getTotalSluHandleDirEmi())));
            totalSluHandleDirEmiList.add(sewStatisticsService.kgToT(tableDto.getTotalSluHandleDirEmi()));
            sewEmiTableDTO.setSluHandlePotEmi(sewEmiTableDTO.getSluHandlePotEmi().add(sewStatisticsService.kgToT(tableDto.getSluHandlePotEmi())));
            sluHandlePotEmiList.add(sewStatisticsService.kgToT(tableDto.getSluHandlePotEmi()));
            sewEmiTableDTO.setSluHandlePowEmi(sewEmiTableDTO.getSluHandlePowEmi().add(sewStatisticsService.kgToT(tableDto.getSluHandlePowEmi())));
            sluHandlePowEmiList.add(sewStatisticsService.kgToT(tableDto.getSluHandlePowEmi()));
            sewEmiTableDTO.setTotalSluHandleIndirEmi(sewEmiTableDTO.getTotalSluHandleIndirEmi().add(sewStatisticsService.kgToT(tableDto.getTotalSluHandleIndirEmi())));
            totalSluHandleIndirEmiList.add(sewStatisticsService.kgToT(tableDto.getTotalSluHandleIndirEmi()));
            sewEmiTableDTO.setSolarPowRed(sewEmiTableDTO.getSolarPowRed().add(sewStatisticsService.kgToT(tableDto.getSolarPowRed())));
            solarPowRedList.add(sewStatisticsService.kgToT(tableDto.getSolarPowRed()));
            sewEmiTableDTO.setHeatPumpRed(sewEmiTableDTO.getHeatPumpRed().add(sewStatisticsService.kgToT(tableDto.getHeatPumpRed())));
            heatPumpRedList.add(sewStatisticsService.kgToT(tableDto.getHeatPumpRed()));
            sewEmiTableDTO.setThermoElecRed(sewEmiTableDTO.getThermoElecRed().add(sewStatisticsService.kgToT(tableDto.getThermoElecRed())));
            thermoElecRedList.add(sewStatisticsService.kgToT(tableDto.getThermoElecRed()));
            sewEmiTableDTO.setThermoEnerRed(sewEmiTableDTO.getThermoEnerRed().add(sewStatisticsService.kgToT(tableDto.getThermoEnerRed())));
            thermoEnerRedList.add(sewStatisticsService.kgToT(tableDto.getThermoEnerRed()));
            sewEmiTableDTO.setOtherEmiRed(sewEmiTableDTO.getOtherEmiRed().add(sewStatisticsService.kgToT(tableDto.getOtherEmiRed())));
            otherEmiRedList.add(sewStatisticsService.kgToT(tableDto.getOtherEmiRed()));
            sewEmiTableDTO.setWindPowRed(sewEmiTableDTO.getWindPowRed().add(sewStatisticsService.kgToT(tableDto.getWindPowRed())));
            windPowRedList.add(sewStatisticsService.kgToT(tableDto.getWindPowRed()));
            sewEmiTableDTO.setEcoComplexRed(sewEmiTableDTO.getEcoComplexRed().add(sewStatisticsService.kgToT(tableDto.getEcoComplexRed())));
            ecoComplexRedList.add(sewStatisticsService.kgToT(tableDto.getEcoComplexRed()));
            sewEmiTableDTO.setCarbonEmi(sewEmiTableDTO.getCarbonEmi().add(sewStatisticsService.kgToT(tableDto.getCarbonEmi())));
            carbonEmiList.add(sewStatisticsService.kgToT(tableDto.getCarbonEmi()));
            sewEmiTableDTO.setCarbonRed(sewEmiTableDTO.getCarbonRed().add(sewStatisticsService.kgToT(
                tableDto.getSolarPowRed().add(tableDto.getHeatPumpRed()).add(tableDto.getThermoElecRed())
                    .add(tableDto.getThermoEnerRed()).add(tableDto.getWindPowRed()).add(tableDto.getEcoComplexRed()))));
            carbonRedList.add(sewStatisticsService.kgToT(tableDto.getSolarPowRed().add(tableDto.getHeatPumpRed()).add(tableDto.getThermoElecRed())
                .add(tableDto.getThermoEnerRed()).add(tableDto.getWindPowRed()).add(tableDto.getEcoComplexRed())));
            sewEmiTableDTO.setCarbonDirEmi(sewEmiTableDTO.getCarbonDirEmi().add(sewStatisticsService.kgToT(tableDto.getCarbonDirEmi())));
            carbonDirEmiList.add(sewStatisticsService.kgToT(tableDto.getCarbonDirEmi()));
            sewEmiTableDTO.setCarbonIndirEmi(sewEmiTableDTO.getCarbonIndirEmi().add(sewStatisticsService.kgToT(tableDto.getCarbonIndirEmi())));
            carbonIndirEmiList.add(sewStatisticsService.kgToT(tableDto.getCarbonIndirEmi()));

            sewEmiTableDTO.setSluTreatIndirEmi(sewEmiTableDTO.getSluTreatIndirEmi().add(sewStatisticsService.kgToT(tableDto.getSluTreatPotEmi().add(tableDto.getSluTreatPowEmi()))));
            sluTreatIndirEmiList.add(sewStatisticsService.kgToT(tableDto.getSluTreatPotEmi().add(tableDto.getSluTreatPowEmi())));
            sluHandleEmiList.add(sewStatisticsService.kgToT(tableDto.getTotalSluHandleDirEmi().add(tableDto.getTotalSluHandleIndirEmi())));
        }

        dto.setDateList(dateList);
        dto.setTableOfEmiData(sewEmiTableDTO);
        List<EmiStatisticsDTO.Line> changeOfTotalEmi = new ArrayList<>();
        changeOfTotalEmi.add(new EmiStatisticsDTO.Line("总碳排放量", carbonEmiList));
        changeOfTotalEmi.add(new EmiStatisticsDTO.Line("直接碳排放量", carbonDirEmiList));
        changeOfTotalEmi.add(new EmiStatisticsDTO.Line("间接碳排放量", carbonIndirEmiList));
        changeOfTotalEmi.add(new EmiStatisticsDTO.Line("间接碳减排量", carbonRedList));
        changeOfTotalEmi.add(new EmiStatisticsDTO.Line("直接碳减排量", otherEmiRedList));
        dto.setChangeOfTotalEmi(changeOfTotalEmi);
        List<EmiStatisticsDTO.Block> percentageOfTotalEmi = new ArrayList<>();
        percentageOfTotalEmi.add(new EmiStatisticsDTO.Block("直接碳排放量", sewEmiTableDTO.getCarbonDirEmi()));
        percentageOfTotalEmi.add(new EmiStatisticsDTO.Block("间接碳排放量", sewEmiTableDTO.getCarbonIndirEmi()));
        percentageOfTotalEmi.add(new EmiStatisticsDTO.Block("间接碳减排量", sewEmiTableDTO.getCarbonRed()));
        percentageOfTotalEmi.add(new EmiStatisticsDTO.Block("直接碳减排量", sewEmiTableDTO.getOtherEmiRed()));
        dto.setPercentageOfTotalEmi(percentageOfTotalEmi);
        List<EmiStatisticsDTO.Line> changeInVariousTypesOfEmi = new ArrayList<>();
        changeInVariousTypesOfEmi.add(new EmiStatisticsDTO.Line("耗电间接碳排放量", totalPowEmiList));
        changeInVariousTypesOfEmi.add(new EmiStatisticsDTO.Line("药剂投加间接碳排放量", totalPotEmiList));
        changeInVariousTypesOfEmi.add(new EmiStatisticsDTO.Line("污水处理直接碳排放量", totalSewTreatEmiList));
        changeInVariousTypesOfEmi.add(new EmiStatisticsDTO.Line("污泥处理间接碳排放量", sluTreatIndirEmiList));
        changeInVariousTypesOfEmi.add(new EmiStatisticsDTO.Line("污泥处置碳排放量", sluHandleEmiList));
        dto.setChangeInVariousTypesOfEmi(changeInVariousTypesOfEmi);
        List<EmiStatisticsDTO.Block> percentageInVariousTypesOfEmi = new ArrayList<>();
        percentageInVariousTypesOfEmi.add(new EmiStatisticsDTO.Block("耗电间接碳排放量", sewEmiTableDTO.getTotalPowEmi()));
        percentageInVariousTypesOfEmi.add(new EmiStatisticsDTO.Block("药剂投加间接碳排放量", sewEmiTableDTO.getTotalPotEmi()));
        percentageInVariousTypesOfEmi.add(new EmiStatisticsDTO.Block("污水处理直接碳排放量", sewEmiTableDTO.getTotalSewTreatEmi()));
        percentageInVariousTypesOfEmi.add(new EmiStatisticsDTO.Block("污泥处理间接碳排放量", sewEmiTableDTO.getSluTreatIndirEmi()));
        percentageInVariousTypesOfEmi.add(new EmiStatisticsDTO.Block("污泥处置碳排放量", sluHandleEmiList.stream().reduce(BigDecimal.ZERO, BigDecimal::add)));
        dto.setPercentageInVariousTypesOfEmi(percentageInVariousTypesOfEmi);
        List<EmiStatisticsDTO.Line> changeOfIndirectEmiFromElec = new ArrayList<>();
        changeOfIndirectEmiFromElec.add(new EmiStatisticsDTO.Line("进水总泵站耗电间接碳排放量", inletPumpPowEmiList));
        changeOfIndirectEmiFromElec.add(new EmiStatisticsDTO.Line("鼓风机房耗电间接碳排放量", blowerPowEmiList));
        changeOfIndirectEmiFromElec.add(new EmiStatisticsDTO.Line("回流污泥泵房耗电间接碳排放量", retSluPumpPowEmiList));
        changeOfIndirectEmiFromElec.add(new EmiStatisticsDTO.Line("紫外+氯消毒耗电间接碳排放量", facilityPowEmiList));
        changeOfIndirectEmiFromElec.add(new EmiStatisticsDTO.Line("附属设施用电耗电间接碳排放量", disinfectPowEmiList));
        changeOfIndirectEmiFromElec.add(new EmiStatisticsDTO.Line("其他耗电间接碳排放量", otherPowEmiList));
        dto.setChangeOfIndirectEmiFromElec(changeOfIndirectEmiFromElec);
        List<EmiStatisticsDTO.Line> changeOfIndirectEmiFromPot = new ArrayList<>();
        changeOfIndirectEmiFromPot.add(new EmiStatisticsDTO.Line("一级处理药剂投加间接碳排放量", level1PotEmiList));
        changeOfIndirectEmiFromPot.add(new EmiStatisticsDTO.Line("二级处理药剂投加间接碳排放量", level2PotEmiList));
        changeOfIndirectEmiFromPot.add(new EmiStatisticsDTO.Line("三级处理药剂投加间接碳排放量", level3PotEmiList));
        dto.setChangeOfIndirectEmiFromPot(changeOfIndirectEmiFromPot);
        List<EmiStatisticsDTO.Line> changeOfDirectEmiFromSewTreat = new ArrayList<>();
        changeOfDirectEmiFromSewTreat.add(new EmiStatisticsDTO.Line("污水处理CH4的CO2直接碳排放量", sewTreatCh4EmiList));
        changeOfDirectEmiFromSewTreat.add(new EmiStatisticsDTO.Line("污水处理N2O的CO2直接碳排放量", sewTreatN2oEmiList));
        dto.setChangeOfDirectEmiFromSewTreat(changeOfDirectEmiFromSewTreat);
        List<EmiStatisticsDTO.Line> changeOfIndirectEmiFromSluTreat = new ArrayList<>();
        changeOfIndirectEmiFromSluTreat.add(new EmiStatisticsDTO.Line("污泥处理药剂投加间接碳排放量", sluTreatPotEmiList));
        changeOfIndirectEmiFromSluTreat.add(new EmiStatisticsDTO.Line("污泥处理耗电间接碳排放量", sluTreatPowEmiList));
        dto.setChangeOfIndirectEmiFromSluTreat(changeOfIndirectEmiFromSluTreat);
        List<EmiStatisticsDTO.Line> changeOfCarbonReduction = new ArrayList<>();
        changeOfCarbonReduction.add(new EmiStatisticsDTO.Line("太阳能间接碳减排量", solarPowRedList));
        changeOfCarbonReduction.add(new EmiStatisticsDTO.Line("热泵间接碳减排量", heatPumpRedList));
        changeOfCarbonReduction.add(new EmiStatisticsDTO.Line("热电联产发电间接碳减排量", thermoElecRedList));
        changeOfCarbonReduction.add(new EmiStatisticsDTO.Line("热电联产产热间接碳减排量", thermoEnerRedList));
        changeOfCarbonReduction.add(new EmiStatisticsDTO.Line("其他碳减排项目直接碳减排量", otherEmiRedList));
        changeOfCarbonReduction.add(new EmiStatisticsDTO.Line("风电间接碳减排量", windPowRedList));
        changeOfCarbonReduction.add(new EmiStatisticsDTO.Line("生态综合体间接碳减排量", ecoComplexRedList));
        dto.setChangeOfCarbonReduction(changeOfCarbonReduction);
        List<EmiStatisticsDTO.Line> changeOfEmiFromSluHandle = new ArrayList<>();
        changeOfEmiFromSluHandle.add(new EmiStatisticsDTO.Line("污泥处置CH4的CO2直接碳排放量", sluHandleCh4EmiList));
        changeOfEmiFromSluHandle.add(new EmiStatisticsDTO.Line("污泥处置N2O的CO2直接碳排放量", sluHandleN2oEmiList));
        changeOfEmiFromSluHandle.add(new EmiStatisticsDTO.Line("污泥处置药剂投加间接碳排放量", sluHandlePotEmiList));
        changeOfEmiFromSluHandle.add(new EmiStatisticsDTO.Line("污泥处置耗电间接碳排放量", sluHandlePowEmiList));
        dto.setChangeOfEmiFromSluHandle(changeOfEmiFromSluHandle);
        dto.setPercentageOfEmiData(sewStatisticsService.convertToFlare(sewEmiTableDTO));

        return ResponseEntity.ok().body(dto);
    }
}
