package com.ruowei.web.rest.dto;

import com.ruowei.service.dto.WaterCarbonEmissionOutputDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 污水碳排放核算结果
 */
@Data
public class SewEmiAccountOutputDTO {
//TODO 待修改
    @ApiModelProperty(value = "核算年份")
    private String accYear;

    @ApiModelProperty(value = "核算月份")
    private String accMonth;

    @ApiModelProperty(value = "单据号")
    private String documentCode;

    @ApiModelProperty(value = "行业类型编码")
    private String industryCode;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "一级处理药剂投加产生的间接碳排放（kg/m）")
    private BigDecimal level1PotEmi;

    @ApiModelProperty(value = "二级处理药剂投加产生的间接碳排放（kg/m）")
    private BigDecimal level2PotEmi;

    @ApiModelProperty(value = "三级处理药剂投加产生的间接碳排放（kg/m）")
    private BigDecimal level3PotEmi;

    @ApiModelProperty(value = "污泥处理药剂投加产生的间接碳排放（kg/m）")
    private BigDecimal sluTreatPotEmi;

    @ApiModelProperty(value = "药剂投加产生的间接碳排放（kg/m）")
    private BigDecimal totalPotEmi;

    @ApiModelProperty(value = "进水总泵站耗电产生的间接碳排放（kg/m）")
    private BigDecimal inletPumpPowEmi;

    @ApiModelProperty(value = "鼓风机房耗电产生的间接碳排放（kg/m）")
    private BigDecimal blowerPowEmi;

    @ApiModelProperty(value = "回流污泥泵房耗电产生的间接碳排放（kg/m）")
    private BigDecimal retSluPumpPowEmi;

    @ApiModelProperty(value = "污泥处理用电耗电产生的间接碳排放（kg/m）")
    private BigDecimal sluTreatPowEmi;

    @ApiModelProperty(value = "紫外+氯消毒耗电产生的间接碳排放（kg/m）")
    private BigDecimal facilityPowEmi;

    @ApiModelProperty(value = "附属设施用电耗电产生的间接碳排放（kg/m）")
    private BigDecimal disinfectPowEmi;

    @ApiModelProperty(value = "其他耗电产生的间接碳排放（kg/m）")
    private BigDecimal otherPowEmi;

    @ApiModelProperty(value = "耗电产生的间接碳排放（kg/m）")
    private BigDecimal totalPowEmi;

    @ApiModelProperty(value = "污水处理CH4的CO2当量（kg/m）")
    private BigDecimal sewTreatCh4Emi;

    @ApiModelProperty(value = "污水处理N2O的CO2当量（kg/m）")
    private BigDecimal sewTreatN2oEmi;

    @ApiModelProperty(value = "污水处理的直接碳排放（kg/m）")
    private BigDecimal totalSewTreatEmi;

    @ApiModelProperty(value = "污泥处置CH4的CO2当量（kg/m）")
    private BigDecimal sluHandleCh4Emi;

    @ApiModelProperty(value = "污泥处置N2O的CO2当量（kg/m）")
    private BigDecimal sluHandleN2oEmi;

    @ApiModelProperty(value = "污泥处置的直接碳排放（kg/m）")
    private BigDecimal totalSluHandleDirEmi;

    @ApiModelProperty(value = "污泥处置药剂投加产生的间接碳排放")
    private BigDecimal sluHandlePotEmi;

    @ApiModelProperty(value = "污泥处置用电产生的间接碳排放")
    private BigDecimal sluHandlePowEmi;

    @ApiModelProperty(value = "污泥处置间接碳排放")
    private BigDecimal totalSluHandleIndirEmi;

    @ApiModelProperty(value = "太阳能间接减少的碳排放（kg/m）")
    private BigDecimal solarPowRed;

    @ApiModelProperty(value = "热泵间接减少的碳排放（kg/m）")
    private BigDecimal heatPumpRed;

    @ApiModelProperty(value = "热电联产发电间接减少的碳排放（kg/m）")
    private BigDecimal thermoElecRed;

    @ApiModelProperty(value = "热电联产产热间接减少的碳排放（kg/m）")
    private BigDecimal thermoEnerRed;

    @ApiModelProperty(value = "其他碳减排项目减少的碳排放")
    private BigDecimal otherEmiRed;

    @ApiModelProperty(value = "风电间接减少的碳排放")
    private BigDecimal windPowRed;

    @ApiModelProperty(value = "生态综合体减少的碳排放")
    private BigDecimal ecoComplexRed;

    @ApiModelProperty(value = "污水厂本月碳排放（kg/m）")
    private BigDecimal carbonEmi;

    @ApiModelProperty(value = "污水厂本月碳减排排放（kg/m）")
    private BigDecimal carbonRed;

    @ApiModelProperty(value = "污水厂本月直接碳排放（kg/m）")
    private BigDecimal carbonDirEmi;

    @ApiModelProperty(value = "污水厂本月间接碳排放（kg/m）")
    private BigDecimal carbonIndirEmi;

    public SewEmiAccountOutputDTO(String accYear, String accMonth, String documentCode, String enterpriseName) {
        this.accYear = accYear;
        this.accMonth = accMonth;
        this.documentCode = documentCode;
        this.enterpriseName = enterpriseName;

    }
}
