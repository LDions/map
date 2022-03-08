package com.ruowei.service;

import com.ruowei.web.rest.dto.SewEmiTableDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SewStatisticsService {

    /**
     * 千克换算成吨
     * @param kg
     * @return
     */
    public BigDecimal kgToT(BigDecimal kg) {
        return kg.divide(new BigDecimal(1000), 0, RoundingMode.HALF_UP);
    }

    public Map<String, Object> convertToFlare(SewEmiTableDTO dto) {
        // 直接
        Map<String, Object> sewTreatCh4Emi = new HashMap<>();
        sewTreatCh4Emi.put("name", "污水处理CH4的CO2直接碳排放量");
        sewTreatCh4Emi.put("value", dto.getSewTreatCh4Emi());
        Map<String, Object> sewTreatN2oEmi = new HashMap<>();
        sewTreatN2oEmi.put("name", "污水处理N2O的CO2直接碳排放量");
        sewTreatN2oEmi.put("value", dto.getSewTreatN2oEmi());
        Map<String, Object> totalSewTreatEmi = new HashMap<>();
        totalSewTreatEmi.put("name", "污水处理直接碳排放量");
        totalSewTreatEmi.put("value", new BigDecimal(0));
        totalSewTreatEmi.put("children", Arrays.asList(sewTreatCh4Emi, sewTreatN2oEmi));

        Map<String, Object> sluHandleCh4Emi = new HashMap<>();
        sluHandleCh4Emi.put("name", "污泥处置CH4的CO2直接碳排放量");
        sluHandleCh4Emi.put("value", dto.getSluHandleCh4Emi());
        Map<String, Object> sluHandleN2oEmi = new HashMap<>();
        sluHandleN2oEmi.put("name", "污泥处置N2O的CO2直接碳排放量");
        sluHandleN2oEmi.put("value", dto.getSluHandleN2oEmi());
        Map<String, Object> totalSluHandleDirEmi = new HashMap<>();
        totalSluHandleDirEmi.put("name", "污泥处置直接碳排放量");
        totalSluHandleDirEmi.put("value", new BigDecimal(0));
        totalSluHandleDirEmi.put("children", Arrays.asList(sluHandleCh4Emi, sluHandleN2oEmi));

        Map<String, Object> carbonDirEmi = new HashMap<>();
        carbonDirEmi.put("name", "直接碳排放量");
        carbonDirEmi.put("value", new BigDecimal(0));
        carbonDirEmi.put("children", Arrays.asList(totalSewTreatEmi, totalSluHandleDirEmi));
        // 间接
        Map<String, Object> inletPumpPowEmi = new HashMap<>();
        inletPumpPowEmi.put("name", "进水总泵站耗电间接碳排放量");
        inletPumpPowEmi.put("value", dto.getInletPumpPowEmi());
        Map<String, Object> blowerPowEmi = new HashMap<>();
        blowerPowEmi.put("name", "鼓风机房耗电间接碳排放量");
        blowerPowEmi.put("value", dto.getBlowerPowEmi());
        Map<String, Object> retSluPumpPowEmi = new HashMap<>();
        retSluPumpPowEmi.put("name", "回流污泥泵房耗电间接碳排放量");
        retSluPumpPowEmi.put("value", dto.getRetSluPumpPowEmi());
        Map<String, Object> facilityPowEmi = new HashMap<>();
        facilityPowEmi.put("name", "紫外+氯消毒耗电间接碳排放量");
        facilityPowEmi.put("value", dto.getFacilityPowEmi());
        Map<String, Object> disinfectPowEmi = new HashMap<>();
        disinfectPowEmi.put("name", "附属设施用电耗电间接碳排放量");
        disinfectPowEmi.put("value", dto.getDisinfectPowEmi());
        Map<String, Object> otherPowEmi = new HashMap<>();
        otherPowEmi.put("name", "其他耗电间接碳排放量");
        otherPowEmi.put("value", dto.getOtherPowEmi());
        Map<String, Object> totalPowEmi = new HashMap<>();
        totalPowEmi.put("name", "耗电间接碳排放量");
        totalPowEmi.put("value", dto.getTotalPowEmi().subtract(dto.getInletPumpPowEmi()).subtract(dto.getBlowerPowEmi())
            .subtract(dto.getRetSluPumpPowEmi()).subtract(dto.getFacilityPowEmi()).subtract(dto.getDisinfectPowEmi())
            .subtract(dto.getOtherPowEmi()));
        totalPowEmi.put("children", Arrays.asList(inletPumpPowEmi, blowerPowEmi, retSluPumpPowEmi, facilityPowEmi, disinfectPowEmi, otherPowEmi));

        Map<String, Object> level1PotEmi = new HashMap<>();
        level1PotEmi.put("name", "一级处理药剂投加间接碳排放量");
        level1PotEmi.put("value", dto.getLevel1PotEmi());
        Map<String, Object> level2PotEmi = new HashMap<>();
        level2PotEmi.put("name", "二级处理药剂投加间接碳排放量");
        level2PotEmi.put("value", dto.getLevel2PotEmi());
        Map<String, Object> level3PotEmi = new HashMap<>();
        level3PotEmi.put("name", "三级处理药剂投加间接碳排放量");
        level3PotEmi.put("value", dto.getLevel3PotEmi());
        Map<String, Object> totalPotEmi = new HashMap<>();
        totalPotEmi.put("name", "药剂投加间接碳排放量");
        totalPotEmi.put("value", dto.getTotalPotEmi().subtract(dto.getLevel1PotEmi()).subtract(dto.getLevel2PotEmi()).subtract(dto.getLevel3PotEmi()));
        totalPotEmi.put("children", Arrays.asList(level1PotEmi, level2PotEmi, level3PotEmi));

        Map<String, Object> sluTreatPowEmi = new HashMap<>();
        sluTreatPowEmi.put("name", "污泥处理耗电间接碳排放量");
        sluTreatPowEmi.put("value", dto.getSluTreatPowEmi());
        Map<String, Object> sluTreatPotEmi = new HashMap<>();
        sluTreatPotEmi.put("name", "污泥处理药剂投加间接碳排放量");
        sluTreatPotEmi.put("value", dto.getSluTreatPotEmi());
        Map<String, Object> sluTreatIndirEmi = new HashMap<>();
        sluTreatIndirEmi.put("name", "污泥处理间接碳排放量");
        sluTreatIndirEmi.put("value", new BigDecimal(0));
        sluTreatIndirEmi.put("children", Arrays.asList(sluTreatPowEmi, sluTreatPotEmi));

        Map<String, Object> sluHandlePotEmi = new HashMap<>();
        sluHandlePotEmi.put("name", "污泥处置药剂投加间接碳排放量");
        sluHandlePotEmi.put("value", dto.getSluHandlePotEmi());
        Map<String, Object> sluHandlePowEmi = new HashMap<>();
        sluHandlePowEmi.put("name", "污泥处置耗电间接碳排放量");
        sluHandlePowEmi.put("value", dto.getSluHandlePowEmi());
        Map<String, Object> totalSluHandleIndirEmi = new HashMap<>();
        totalSluHandleIndirEmi.put("name", "污泥处置间接碳排放量");
        totalSluHandleIndirEmi.put("value", new BigDecimal(0));
        totalSluHandleIndirEmi.put("children", Arrays.asList(sluHandlePotEmi, sluHandlePowEmi));

        Map<String, Object> carbonIndirEmi = new HashMap<>();
        carbonIndirEmi.put("name", "间接碳排放量");
        carbonIndirEmi.put("value", new BigDecimal(0));
        carbonIndirEmi.put("children", Arrays.asList(totalPowEmi, totalPotEmi, sluTreatIndirEmi, totalSluHandleIndirEmi));
        //减排量
        Map<String, Object> solarPowRed = new HashMap<>();
        solarPowRed.put("name", "太阳能间接碳减排量");
        solarPowRed.put("value", dto.getSolarPowRed());
        Map<String, Object> heatPumpRed = new HashMap<>();
        heatPumpRed.put("name", "热泵间接碳减排量");
        heatPumpRed.put("value", dto.getHeatPumpRed());
        Map<String, Object> thermoElecRed = new HashMap<>();
        thermoElecRed.put("name", "热电联产发电间接碳减排量");
        thermoElecRed.put("value", dto.getThermoElecRed());
        Map<String, Object> thermoEnerRed = new HashMap<>();
        thermoEnerRed.put("name", "热电联产产热间接碳减排量");
        thermoEnerRed.put("value", dto.getThermoEnerRed());
        Map<String, Object> windPowRed = new HashMap<>();
        windPowRed.put("name", "风电间接碳减排量");
        windPowRed.put("value", dto.getWindPowRed());
        Map<String, Object> ecoComplexRed = new HashMap<>();
        ecoComplexRed.put("name", "生态综合体间接碳减排量");
        ecoComplexRed.put("value", dto.getEcoComplexRed());
        Map<String, Object> indirCarbonRed = new HashMap<>();
        indirCarbonRed.put("name", "间接碳减排量");
        indirCarbonRed.put("value", new BigDecimal(0));
        indirCarbonRed.put("children", Arrays.asList(solarPowRed, heatPumpRed, thermoElecRed, thermoEnerRed, windPowRed, ecoComplexRed));

        Map<String, Object> otherEmiRed = new HashMap<>();
        otherEmiRed.put("name", "其他碳减排项目直接碳减排量");
        otherEmiRed.put("value", dto.getOtherEmiRed());
        Map<String, Object> dirCarbonRed = new HashMap<>();
        dirCarbonRed.put("name", "直接碳减排量");
        dirCarbonRed.put("value", new BigDecimal(0));
        dirCarbonRed.put("children", Arrays.asList(otherEmiRed));


        Map<String, Object> result = new HashMap<>();
        result.put("name", "污水处理厂");
        result.put("children", Arrays.asList(carbonDirEmi, carbonIndirEmi, indirCarbonRed, dirCarbonRed));
        return result;
    }
}
