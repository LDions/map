package com.ruowei.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SewEmiQueryDTO {

    /**
     * 核算年份
     */
    private String accYear;

    /**
     * 核算月份
     */
    private String accMonth;

    /**
     * 一级处理药剂投加产生的间接碳排放（kg/m）
     */
    private BigDecimal level1PotEmi;

    /**
     * 二级处理药剂投加产生的间接碳排放（kg/m）
     */
    private BigDecimal level2PotEmi;

    /**
     * 三级处理药剂投加产生的间接碳排放（kg/m）
     */
    private BigDecimal level3PotEmi;

    /**
     * 污泥处理药剂投加产生的间接碳排放（kg/m）
     */
    private BigDecimal sluTreatPotEmi;

    /**
     * 药剂投加产生的间接碳排放（kg/m）
     */
    private BigDecimal totalPotEmi;

    /**
     * 进水总泵站耗电产生的间接碳排放（kg/m）
     */
    private BigDecimal inletPumpPowEmi;

    /**
     * 鼓风机房耗电产生的间接碳排放（kg/m）
     */
    private BigDecimal blowerPowEmi;

    /**
     * 回流污泥泵房耗电产生的间接碳排放（kg/m）
     */
    private BigDecimal retSluPumpPowEmi;

    /**
     * 污泥处理用电耗电产生的间接碳排放（kg/m）
     */
    private BigDecimal sluTreatPowEmi;

    /**
     * 紫外+氯消毒耗电产生的间接碳排放（kg/m）
     */
    private BigDecimal facilityPowEmi;

    /**
     * 附属设施用电耗电产生的间接碳排放（kg/m）
     */
    private BigDecimal disinfectPowEmi;

    /**
     * 其他耗电产生的间接碳排放（kg/m）
     */
    private BigDecimal otherPowEmi;

    /**
     * 耗电产生的间接碳排放（kg/m）
     */
    private BigDecimal totalPowEmi;

    /**
     * 污水处理CH4的CO2当量（kg/m）
     */
    private BigDecimal sewTreatCh4Emi;

    /**
     * 污水处理N2O的CO2当量（kg/m）
     */
    private BigDecimal sewTreatN2oEmi;

    /**
     * 污水处理的直接碳排放（kg/m）
     */
    private BigDecimal totalSewTreatEmi;

    /**
     * 污泥处置CH4的CO2当量（kg/m）
     */
    private BigDecimal sluHandleCh4Emi;

    /**
     * 污泥处置N2O的CO2当量（kg/m）
     */
    private BigDecimal sluHandleN2oEmi;

    /**
     * 污泥处置的直接碳排放（kg/m）
     */
    private BigDecimal totalSluHandleDirEmi;

    /**
     * 污泥处置药剂投加产生的间接碳排放
     */
    private BigDecimal sluHandlePotEmi;

    /**
     * 污泥处置用电产生的间接碳排放
     */
    private BigDecimal sluHandlePowEmi;

    /**
     * 污泥处置间接碳排放
     */
    private BigDecimal totalSluHandleIndirEmi;

    /**
     * 太阳能间接减少的碳排放（kg/m）
     */
    private BigDecimal solarPowRed;

    /**
     * 热泵间接减少的碳排放（kg/m）
     */
    private BigDecimal heatPumpRed;

    /**
     * 热电联产发电间接减少的碳排放（kg/m）
     */
    private BigDecimal thermoElecRed;

    /**
     * 热电联产产热间接减少的碳排放（kg/m）
     */
    private BigDecimal thermoEnerRed;

    /**
     * 其他碳减排项目减少的碳排放
     */
    private BigDecimal otherEmiRed;

    /**
     * 风电间接减少的碳排放
     */
    private BigDecimal windPowRed;

    /**
     * 生态综合体减少的碳排放
     */
    private BigDecimal ecoComplexRed;

    /**
     * 污水厂本月碳排放（kg/m）
     */
    private BigDecimal carbonEmi;

    /**
     * 污水厂本月碳减排排放（kg/m）
     */
    private BigDecimal carbonRed;

    /**
     * 污水厂本月直接碳排放（kg/m）
     */
    private BigDecimal carbonDirEmi;

    /**
     * 污水厂本月间接碳排放（kg/m）
     */
    private BigDecimal carbonIndirEmi;

    public SewEmiQueryDTO() {
    }

    public SewEmiQueryDTO(BigDecimal b) {
        this.level1PotEmi = b;
        this.level2PotEmi = b;
        this.level3PotEmi = b;
        this.sluTreatPotEmi = b;
        this.totalPotEmi = b;
        this.inletPumpPowEmi = b;
        this.blowerPowEmi = b;
        this.retSluPumpPowEmi = b;
        this.sluTreatPowEmi = b;
        this.facilityPowEmi = b;
        this.disinfectPowEmi = b;
        this.otherPowEmi = b;
        this.totalPowEmi = b;
        this.sewTreatCh4Emi = b;
        this.sewTreatN2oEmi = b;
        this.totalSewTreatEmi = b;
        this.sluHandleCh4Emi = b;
        this.sluHandleN2oEmi = b;
        this.totalSluHandleDirEmi = b;
        this.sluHandlePotEmi = b;
        this.sluHandlePowEmi = b;
        this.totalSluHandleIndirEmi = b;
        this.solarPowRed = b;
        this.heatPumpRed = b;
        this.thermoElecRed = b;
        this.thermoEnerRed = b;
        this.otherEmiRed = b;
        this.windPowRed = b;
        this.ecoComplexRed = b;
        this.carbonEmi = b;
        this.carbonRed = b;
        this.carbonDirEmi = b;
        this.carbonIndirEmi = b;
    }
}
