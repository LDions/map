package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 污水碳排放核算请求参数
 */
@Data
public class SewEmiAccountVM {

    @ApiModelProperty(value = "草稿箱ID")
    private Long draftId;

    @NotEmpty(message = "请选择省直辖市")
    @ApiModelProperty(value = "省直辖市编码", required = true)
    private String provinceCode;

    @NotEmpty(message = "请选择省直辖市")
    @ApiModelProperty(value = "省直辖市名称", required = true)
    private String provinceName;

    @NotNull(message = "请选择企业")
    @ApiModelProperty(value = "企业ID", required = true)
    private Long enterpriseId;

    @NotEmpty(message = "请选择企业")
    @ApiModelProperty(value = "企业名称", required = true)
    private String enterpriseName;

    @NotEmpty(message = "请选择省直辖市")
    @ApiModelProperty(value = "行业类型编码", required = true)
    private String industryCode;

    @NotEmpty(message = "请选择行业类型")
    @ApiModelProperty(value = "行业类型名称", required = true)
    private String industryName;

    @NotEmpty(message = "请输入核算时间")
    @ApiModelProperty(value = "核算年份", required = true)
    private String accYear;

    @NotEmpty(message = "请输入核算时间")
    @ApiModelProperty(value = "核算月份", required = true)
    private String accMonth;

    @ApiModelProperty(value = "工艺水质信息")
    private List<SewProcessVM> sewProcesss;

    @NotNull(message = "请输入月度总电量消耗")
    @ApiModelProperty(value = "月度总电量消耗（kWh/m）", required = true)
    private BigDecimal totalPow;

    @ApiModelProperty(value = "进水总泵站耗电（kWh/m）")
    private BigDecimal inPumpPow;

    @ApiModelProperty(value = "鼓风机房耗电（kWh/m）")
    private BigDecimal blowerPow;

    @ApiModelProperty(value = "回流污泥泵房耗电（kWh）")
    private BigDecimal retSluPumpPow;

    @ApiModelProperty(value = "污泥处理耗电（kWh/m）")
    private BigDecimal sluTreatPow;

    @ApiModelProperty(value = "紫外+氯消毒耗电（kWh/m）")
    private BigDecimal disinfectPow;

    @ApiModelProperty(value = "附属设施耗电（kWh/m）")
    private BigDecimal facilityPow;

    @ApiModelProperty(value = "其他耗电（kWh/m）")
    private BigDecimal otherPow;

    @ApiModelProperty(value = "污泥处置用电（kWh/m）")
    private BigDecimal sluHandlePow;

    @ApiModelProperty(value = "药剂月投加情况")
    private List<SewPotVM> sewPots;

    @ApiModelProperty(value = "太阳能月发电量（kWh/m）")
    private BigDecimal solarPow;

    @ApiModelProperty(value = "热泵月供热量（GJ/m）")
    private BigDecimal heatPumpHeat;

    @ApiModelProperty(value = "热泵月制冷量（GJ/m）")
    private BigDecimal heatPumpRefr;

    @ApiModelProperty(value = "热泵供热运行时间（h）")
    private BigDecimal heatPumpHotHours;

    @ApiModelProperty(value = "热泵制冷运行时间（h）")
    private BigDecimal heatPumpColdHours;

    @ApiModelProperty(value = "热电联产月产电量（kWh/m）")
    private BigDecimal thermoElec;

    @ApiModelProperty(value = "热电联产月产热量（GJ/m）")
    private BigDecimal thermoEner;

    @ApiModelProperty(value = "负碳发电输送到电网的电量")
    private BigDecimal toGirdPow;

    @ApiModelProperty(value = "其他负碳项目文字说明")
    private String otherText;

    @ApiModelProperty(value = "其他负碳项目减排量")
    private BigDecimal otherEmiReduction;

    @ApiModelProperty(value = "风能月发电量")
    private BigDecimal windPow;

    @ApiModelProperty(value = "生态综合体碳减排量")
    private BigDecimal ecoComplexReduction;

    @ApiModelProperty(value = "污泥处置是否为本厂管理")
    private Boolean managedBySelf;

    @NotNull(message = "请输入污泥处理后含水率")
    @ApiModelProperty(value = "污泥处理后含水率")
    private BigDecimal sluMoistureAfterTreat;

    @ApiModelProperty(value = "污泥处置情况")
    private List<SewSluVM> sewSlus;

    @Data
    public static class SewPotVM {

        @NotEmpty(message = "请选择药剂")
        @ApiModelProperty(value = "药剂编码", required = true)
        private String potionCode;

        @NotEmpty(message = "请选择药剂")
        @ApiModelProperty(value = "药剂名称", required = true)
        private String potionName;

        @NotNull(message = "请输入药剂月投加量")
        @ApiModelProperty(value = "药剂月投加量（单位kg/m）", required = true)
        private BigDecimal totalPot;

        @ApiModelProperty(value = "一级投加量（单位kg/m）")
        private BigDecimal level1Pot;

        @ApiModelProperty(value = "二级投加量（单位kg/m）")
        private BigDecimal level2Pot;

        @ApiModelProperty(value = "三级投加量（单位kg/m）")
        private BigDecimal level3Pot;

        @ApiModelProperty(value = "污泥处理投加量（单位kg/m）")
        private BigDecimal sluTreatPot;

        @ApiModelProperty(value = "污泥处置投加量（单位kg/m）")
        private BigDecimal sluHandlePot;
    }

    @Data
    public static class SewSluVM {

        @ApiModelProperty(value = "污泥处置方法编码")
        private String methodCode;

        @ApiModelProperty(value = "污泥处置方法名称")
        private String methodName;

        @ApiModelProperty(value = "污泥处置量（kg/m）")
        private BigDecimal sluCapacity;

        @ApiModelProperty(value = "污泥处置前含水率（%）")
        private BigDecimal sluMoisture;
    }

    @Data
    public static class SewProcessVM {

        @NotNull(message = "请输入日均运行规模")
        @ApiModelProperty(value = "日均规模（m3/d）", required = true)
        private BigDecimal dailyScale;

        @NotNull(message = "请输入本月运行天数")
        @ApiModelProperty(value = "本月运行天数", required = true)
        private Integer operatingDays;

        @NotEmpty(message = "请选择工艺类型")
        @ApiModelProperty(value = "工艺类型编码", required = true)
        private String processTypeCode;

        @NotEmpty(message = "请选择工艺类型")
        @ApiModelProperty(value = "工艺类型名称", required = true)
        private String processTypeName;

        @NotNull(message = "请输入进水总氮")
        @ApiModelProperty(value = "进水总氮（mg/L）", required = true)
        private BigDecimal inNitrogen;

        @NotNull(message = "请输入进水氨氮")
        @ApiModelProperty(value = "进水氨氮（mg/L）", required = true)
        private BigDecimal inAmmonia;

        @NotNull(message = "请输入进水COD")
        @ApiModelProperty(value = "进水COD（mg/L）", required = true)
        private BigDecimal inCod;

        @NotNull(message = "请输入进水总磷")
        @ApiModelProperty(value = "进水总磷（mg/L）", required = true)
        private BigDecimal inPhosphorus;

        @ApiModelProperty(value = "进水BOD（mg/L）")
        private BigDecimal inBod;

        @NotNull(message = "请输入出水总氮")
        @ApiModelProperty(value = "出水总氮（mg/L）", required = true)
        private BigDecimal outNitrogen;

        @NotNull(message = "请输入出水氨氮")
        @ApiModelProperty(value = "出水氨氮（mg/L）", required = true)
        private BigDecimal outAmmonia;

        @NotNull(message = "请输入出水COD")
        @ApiModelProperty(value = "出水COD（mg/L）", required = true)
        private BigDecimal outCod;

        @NotNull(message = "请输入出水总磷")
        @ApiModelProperty(value = "出水总磷（mg/L）", required = true)
        private BigDecimal outPhosphorus;

        @ApiModelProperty(value = "出水BOD（mg/L）")
        private BigDecimal outBod;
    }

}
