package com.ruowei.web.rest.dto;

import com.ruowei.web.rest.vm.SewEmiAccountVM;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * 污水碳排放数据核算详情
 */
@Data
public class SewEmiDetailDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "单据号")
    private String documentCode;

    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "录入员ID")
    private Long reporterId;

    @ApiModelProperty(value = "录入员姓名")
    private String reporterName;

    @ApiModelProperty(value = "填报时间")
    private Instant reportTime;

    @ApiModelProperty(value = "省份编码")
    private String provinceCode;

    @ApiModelProperty(value = "省份名称")
    private String provinceName;

    @ApiModelProperty(value = "核算年份")
    private String accYear;

    @ApiModelProperty(value = "核算月份")
    private String accMonth;

    @ApiModelProperty(value = "核算时间")
    private String accTime;

    @ApiModelProperty(value = "工艺水质信息")
    private List<SewEmiAccountVM.SewProcessVM> sewProcesss;

    @ApiModelProperty(value = "月度总电量消耗")
    private BigDecimal totalPow;

    @ApiModelProperty(value = "进水总泵站耗电")
    private BigDecimal inPumpPow;

    @ApiModelProperty(value = "鼓风机房耗电")
    private BigDecimal blowerPow;

    @ApiModelProperty(value = "回流污泥泵房耗电")
    private BigDecimal retSluPumpPow;

    @ApiModelProperty(value = "污泥处理耗电")
    private BigDecimal sluTreatPow;

    @ApiModelProperty(value = "紫外+氯消毒耗电")
    private BigDecimal disinfectPow;

    @ApiModelProperty(value = "附属设施耗电")
    private BigDecimal facilityPow;

    @ApiModelProperty(value = "其他耗电")
    private BigDecimal otherPow;

    @ApiModelProperty(value = "污泥处置用电")
    private BigDecimal sluHandlePow;

    @ApiModelProperty(value = "污水处理药剂月投加情况")
    private List<SewEmiAccountVM.SewPotVM> sewPots;

    @ApiModelProperty(value = "太阳能月发电量")
    private BigDecimal solarPow;

    @ApiModelProperty(value = "热泵月供热量")
    private BigDecimal heatPumpHeat;

    @ApiModelProperty(value = "热泵月制冷量")
    private BigDecimal heatPumpRefr;

    @ApiModelProperty(value = "热泵供热运行时间")
    private BigDecimal heatPumpHotHours;

    @ApiModelProperty(value = "热泵制冷运行时间")
    private BigDecimal heatPumpColdHours;

    @ApiModelProperty(value = "热电联产月产电量")
    private BigDecimal thermoElec;

    @ApiModelProperty(value = "热电联产月产热量")
    private BigDecimal thermoEner;

    @ApiModelProperty(value = "碳减排发电输送到电网的电量")
    private BigDecimal toGirdPow;

    @ApiModelProperty(value = "其他碳减排项目文字说明")
    private String otherText;

    @ApiModelProperty(value = "其他碳减排项目减排量")
    private BigDecimal otherEmiReduction;

    @ApiModelProperty(value = "风能月发电量")
    private BigDecimal windPow;

    @ApiModelProperty(value = "生态综合体碳减排量")
    private BigDecimal ecoComplexReduction;

    @ApiModelProperty(value = "污泥处置是否为本厂管理")
    private Boolean managedBySelf;

    @ApiModelProperty(value = "污泥处理后含水率")
    private BigDecimal sluMoistureAfterTreat;

    @ApiModelProperty(value = "污泥处置情况")
    private List<SewEmiAccountVM.SewSluVM> sewSlus;

    @ApiModelProperty(value = "碳排放因子版本号")
    private String factorVersionNum;

    @ApiModelProperty(value = "一级处理药剂投加产生的间接碳排放")
    private BigDecimal level1PotEmi;

    @ApiModelProperty(value = "二级处理药剂投加产生的间接碳排放")
    private BigDecimal level2PotEmi;

    @ApiModelProperty(value = "三级处理药剂投加产生的间接碳排放")
    private BigDecimal level3PotEmi;

    @ApiModelProperty(value = "污泥处理药剂投加产生的间接碳排放")
    private BigDecimal sluTreatPotEmi;

    @ApiModelProperty(value = "药剂投加产生的间接碳排放")
    private BigDecimal totalPotEmi;

    @ApiModelProperty(value = "进水总泵站耗电产生的间接碳排放")
    private BigDecimal inletPumpPowEmi;

    @ApiModelProperty(value = "鼓风机房耗电产生的间接碳排放")
    private BigDecimal blowerPowEmi;

    @ApiModelProperty(value = "回流污泥泵房耗电产生的间接碳排放")
    private BigDecimal retSluPumpPowEmi;

    @ApiModelProperty(value = "污泥处理用电耗电产生的间接碳排放")
    private BigDecimal sluTreatPowEmi;

    @ApiModelProperty(value = "紫外+氯消毒耗电产生的间接碳排放")
    private BigDecimal facilityPowEmi;

    @ApiModelProperty(value = "附属设施用电耗电产生的间接碳排放")
    private BigDecimal disinfectPowEmi;

    @ApiModelProperty(value = "其他耗电产生的间接碳排放")
    private BigDecimal otherPowEmi;

    @ApiModelProperty(value = "耗电产生的间接碳排放")
    private BigDecimal totalPowEmi;

    @ApiModelProperty(value = "污水处理CH4的CO2当量")
    private BigDecimal sewTreatCh4Emi;

    @ApiModelProperty(value = "污水处理N2O的CO2当量")
    private BigDecimal sewTreatN2oEmi;

    @ApiModelProperty(value = "污水处理的直接碳排放")
    private BigDecimal totalSewTreatEmi;

    @ApiModelProperty(value = "污泥处置CH4的CO2当量")
    private BigDecimal sluHandleCh4Emi;

    @ApiModelProperty(value = "污泥处置N2O的CO2当量")
    private BigDecimal sluHandleN2oEmi;

    @ApiModelProperty(value = "污泥处置的直接碳排放")
    private BigDecimal totalSluHandleDirEmi;

    @ApiModelProperty(value = "污泥处置药剂投加产生的间接碳排放")
    private BigDecimal sluHandlePotEmi;

    @ApiModelProperty(value = "污泥处置用电产生的间接碳排放")
    private BigDecimal sluHandlePowEmi;

    @ApiModelProperty(value = "污泥处置间接碳排放")
    private BigDecimal totalSluHandleIndirEmi;

    @ApiModelProperty(value = "太阳能间接减少的碳排放")
    private BigDecimal solarPowRed;

    @ApiModelProperty(value = "热泵间接减少的碳排放")
    private BigDecimal heatPumpRed;

    @ApiModelProperty(value = "热电联产发电间接减少的碳排放")
    private BigDecimal thermoElecRed;

    @ApiModelProperty(value = "热电联产产热间接减少的碳排放")
    private BigDecimal thermoEnerRed;

    @ApiModelProperty(value = "其他碳减排项目减少的碳排放")
    private BigDecimal otherEmiRed;

    @ApiModelProperty(value = "风电间接减少的碳排放")
    private BigDecimal windPowRed;

    @ApiModelProperty(value = "生态综合体减少的碳排放")
    private BigDecimal ecoComplexRed;

    @ApiModelProperty(value = "污水厂本月碳排放")
    private BigDecimal carbonEmi;

    @ApiModelProperty(value = "污水厂本月碳减排排放")
    private BigDecimal carbonRed;

    @ApiModelProperty(value = "污水厂本月直接碳排放")
    private BigDecimal carbonDirEmi;

    @ApiModelProperty(value = "污水厂本月间接碳排放")
    private BigDecimal carbonIndirEmi;
}
