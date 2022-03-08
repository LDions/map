package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SewEmiTableDTO {

    @ApiModelProperty(value = "一级处理药剂投加间接碳排放量")
    private BigDecimal level1PotEmi;

    @ApiModelProperty(value = "二级处理药剂投加间接碳排放量")
    private BigDecimal level2PotEmi;

    @ApiModelProperty(value = "三级处理药剂投加间接碳排放量")
    private BigDecimal level3PotEmi;

    @ApiModelProperty(value = "药剂投加间接碳排放量")
    private BigDecimal totalPotEmi;

    @ApiModelProperty(value = "污泥处理药剂投加间接碳排放量")
    private BigDecimal sluTreatPotEmi;

    @ApiModelProperty(value = "污泥处理耗电间接碳排放量")
    private BigDecimal sluTreatPowEmi;

    @ApiModelProperty(value = "污泥处理间接碳排放量")
    private BigDecimal sluTreatIndirEmi;

    @ApiModelProperty(value = "进水总泵站耗电间接碳排放量")
    private BigDecimal inletPumpPowEmi;

    @ApiModelProperty(value = "鼓风机房耗电间接碳排放量")
    private BigDecimal blowerPowEmi;

    @ApiModelProperty(value = "回流污泥泵房耗电间接碳排放量")
    private BigDecimal retSluPumpPowEmi;

    @ApiModelProperty(value = "紫外+氯消毒耗电间接碳排放量")
    private BigDecimal facilityPowEmi;

    @ApiModelProperty(value = "附属设施用电耗电间接碳排放量")
    private BigDecimal disinfectPowEmi;

    @ApiModelProperty(value = "其他耗电间接碳排放量")
    private BigDecimal otherPowEmi;

    @ApiModelProperty(value = "耗电间接碳排放量")
    private BigDecimal totalPowEmi;

    @ApiModelProperty(value = "污水处理CH4的CO2直接碳排放量")
    private BigDecimal sewTreatCh4Emi;

    @ApiModelProperty(value = "污水处理N2O的CO2直接碳排放量")
    private BigDecimal sewTreatN2oEmi;

    @ApiModelProperty(value = "污水处理直接碳排放量")
    private BigDecimal totalSewTreatEmi;

    @ApiModelProperty(value = "污泥处置CH4的CO2直接碳排放量")
    private BigDecimal sluHandleCh4Emi;

    @ApiModelProperty(value = "污泥处置N2O的CO2直接碳排放量")
    private BigDecimal sluHandleN2oEmi;

    @ApiModelProperty(value = "污泥处置直接碳排放量")
    private BigDecimal totalSluHandleDirEmi;

    @ApiModelProperty(value = "污泥处置药剂投加间接碳排放量")
    private BigDecimal sluHandlePotEmi;

    @ApiModelProperty(value = "污泥处置耗电间接碳排放量")
    private BigDecimal sluHandlePowEmi;

    @ApiModelProperty(value = "污泥处置间接碳排放量")
    private BigDecimal totalSluHandleIndirEmi;

    @ApiModelProperty(value = "太阳能间接碳减排量")
    private BigDecimal solarPowRed;

    @ApiModelProperty(value = "热泵间接碳减排量")
    private BigDecimal heatPumpRed;

    @ApiModelProperty(value = "热电联产发电间接碳减排量")
    private BigDecimal thermoElecRed;

    @ApiModelProperty(value = "热电联产产热间接碳减排量")
    private BigDecimal thermoEnerRed;

    @ApiModelProperty(value = "其他碳减排项目直接碳减排量")
    private BigDecimal otherEmiRed;

    @ApiModelProperty(value = "风电间接碳减排量")
    private BigDecimal windPowRed;

    @ApiModelProperty(value = "生态综合体间接碳减排量")
    private BigDecimal ecoComplexRed;

    @ApiModelProperty(value = "总碳排放量")
    private BigDecimal carbonEmi;

    @ApiModelProperty(value = "碳减排量")
    private BigDecimal carbonRed;

    @ApiModelProperty(value = "直接碳排放量")
    private BigDecimal carbonDirEmi;

    @ApiModelProperty(value = "间接碳排放量")
    private BigDecimal carbonIndirEmi;

    public SewEmiTableDTO(BigDecimal b) {
        this.level1PotEmi = b;
        this.level2PotEmi = b;
        this.level3PotEmi = b;
        this.totalPotEmi = b;
        this.sluTreatPotEmi = b;
        this.sluTreatPowEmi = b;
        this.sluTreatIndirEmi = b;
        this.inletPumpPowEmi = b;
        this.blowerPowEmi = b;
        this.retSluPumpPowEmi = b;
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
