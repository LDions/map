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

    @ApiModelProperty(value = "污泥处置情况")
    private List<OtherIndexVM> otherIndexs;


    @Data
    public static class SewPotVM {

        @NotNull(message = "请输入进水PH")
        @ApiModelProperty(value = "进水PH（mg/L）")
        private BigDecimal dayInPh;

        @NotNull(message = "请输入出水PH")
        @ApiModelProperty(value = "出水PH（mg/L）")
        private BigDecimal dayOutPh;

        @NotNull(message = "请输入初沉池排泥量")
        @ApiModelProperty(value = "初沉池排泥量（mg/L）")
        private BigDecimal dayFirstMud;

        @NotNull(message = "请输入二沉池排泥量")
        @ApiModelProperty(value = "二沉池排泥量（mg/L）")
        private BigDecimal daySecondMud;

        @NotNull(message = "请输入回流比")
        @ApiModelProperty(value = "回流比（mg/L）")
        private BigDecimal dayReflux;

        @NotNull(message = "请输入碳源投加量")
        @ApiModelProperty(value = "碳源投加量（mg/L）")
        private BigDecimal dayCarAdd;

        @NotNull(message = "请输入生化池-厌氧池PH")
        @ApiModelProperty(value = "生化池-厌氧池PH（mg/L）")
        private BigDecimal dayAnaerobicPoolPh;

        @NotNull(message = "请输入生化池-厌氧池ORP ")
        @ApiModelProperty(value = "生化池-厌氧池ORP （mg/L）")
        private BigDecimal dayAnaerobicPoolOrp;

        @NotNull(message = "请输入生化池-厌氧池DO")
        @ApiModelProperty(value = "生化池-厌氧池DO（mg/L）")
        private BigDecimal dayAnaerobicPoolDo;

        @NotNull(message = "请输入生化池-厌氧池SOUR")
        @ApiModelProperty(value = "生化池-厌氧池SOUR（mg/L）")
        private BigDecimal dayAnaerobicPoolSour;

        @NotNull(message = "请输入生化池-厌氧池SV")
        @ApiModelProperty(value = "生化池-厌氧池SV（mg/L）")
        private BigDecimal dayAnaerobicPoolSv;

        @NotNull(message = "请输入生化池-厌氧池MLSS")
        @ApiModelProperty(value = "生化池-厌氧池MLSS（mg/L）")
        private BigDecimal dayAnaerobicPoolMlss;

        @NotNull(message = "请输入生化池-厌氧池温度")
        @ApiModelProperty(value = "生化池-厌氧池温度（mg/L）")
        private BigDecimal dayAnaerobicPoolTemper;

        @NotNull(message = "请输入生化池-缺氧池PH")
        @ApiModelProperty(value = "生化池-缺氧池PH（mg/L）")
        private BigDecimal dayAnoxicPoolPh;

        @NotNull(message = "请输入生化池-缺氧池ORP ")
        @ApiModelProperty(value = "生化池-缺氧池ORP （mg/L）")
        private BigDecimal dayAnoxicPoolOrp;

        @NotNull(message = "请输入生化池-缺氧池DO")
        @ApiModelProperty(value = "生化池-缺氧池DO（mg/L）")
        private BigDecimal dayAnoxicPoolDo;

        @NotNull(message = "请输入生化池-缺氧池SOUR")
        @ApiModelProperty(value = "生化池-缺氧池SOUR（mg/L）")
        private BigDecimal dayAnoxicPoolSour;

        @NotNull(message = "请输入生化池-缺氧池SV")
        @ApiModelProperty(value = "生化池-缺氧池SV（mg/L）")
        private BigDecimal dayAnoxicPoolSv;

        @NotNull(message = "请输入生化池-缺氧池MLSS")
        @ApiModelProperty(value = "生化池-缺氧池MLSS（mg/L）")
        private BigDecimal dayAnoxicPoolMlss;

        @NotNull(message = "请输入生化池-缺氧池温度")
        @ApiModelProperty(value = "生化池-缺氧池温度（mg/L）")
        private BigDecimal dayAnoxicPoolTemper;

        @NotNull(message = "请输入生化池-好氧池PH")
        @ApiModelProperty(value = "生化池-好氧池PH（mg/L）")
        private BigDecimal dayAerobicPoolPh;

        @NotNull(message = "请输入生化池-好氧池ORP ")
        @ApiModelProperty(value = "生化池-好氧池ORP （mg/L）")
        private BigDecimal dayAerobicPoolOrp;

        @NotNull(message = "请输入生化池-好氧池DO")
        @ApiModelProperty(value = "生化池-好氧池DO（mg/L）")
        private BigDecimal dayAerobicPoolDo;

        @NotNull(message = "请输入生化池-好氧池SOUR")
        @ApiModelProperty(value = "生化池-好氧池SOUR（mg/L）")
        private BigDecimal dayAerobicPoolSour;

        @NotNull(message = "请输入生化池-好氧池SV")
        @ApiModelProperty(value = "生化池-好氧池SV（mg/L）")
        private BigDecimal dayAerobicPoolSv;

        @NotNull(message = "请输入生化池-好氧池MLSS")
        @ApiModelProperty(value = "生化池-好氧池MLSS（mg/L）")
        private BigDecimal dayAerobicPoolMlss;

        @NotNull(message = "请输入生化池-好氧池温度")
        @ApiModelProperty(value = "生化池-好氧池温度（mg/L）")
        private BigDecimal dayAerobicPoolTemper;
    }

    @Data
    public static class SewSluVM {

        @NotEmpty(message = "请选择处置方法")
        @ApiModelProperty(value = "污泥处置方法编码")
        private String methodCode;

        @NotEmpty(message = "请输入方法名称")
        @ApiModelProperty(value = "污泥处置方法名称")
        private String methodName;

        @NotNull(message = "请输入进水流量")
        @ApiModelProperty(value = "进水流量（mg/L）")
        private BigDecimal inFlow;

        @NotNull(message = "请输入进水氨氮")
        @ApiModelProperty(value = "进水氨氮（mg/L）")
        private BigDecimal assInAmmonia;

        @NotNull(message = "请输入进水COD")
        @ApiModelProperty(value = "进水COD（mg/L）")
        private BigDecimal assInCod;

        @NotNull(message = "请输入进水TN")
        @ApiModelProperty(value = "进水TN（mg/L）")
        private BigDecimal assInTn;

        @NotNull(message = "请输入进水TP")
        @ApiModelProperty(value = "进水TP（mg/L）")
        private BigDecimal assInTp;

        @NotNull(message = "缺氧池出口硝酸盐")
        @ApiModelProperty(value = "缺氧池出口硝酸盐（mg/L）")
        private BigDecimal assAnoxicPoolDoOutNit;

        @NotNull(message = "好氧池出口硝酸盐")
        @ApiModelProperty(value = "好氧池出口硝酸盐（mg/L）")
        private BigDecimal assAerobicPoolDoOutNit;

        @NotNull(message = "请输入出水流量")
        @ApiModelProperty(value = "出水流量（mg/L）")
        private BigDecimal assOutFlow;

        @NotNull(message = "请输入出水氨氮")
        @ApiModelProperty(value = "出水氨氮（mg/L）")
        private BigDecimal assOutAmmonia;

        @NotNull(message = "请输入出水COD")
        @ApiModelProperty(value = "出水COD（mg/L）")
        private BigDecimal assOutCod;

        @NotNull(message = "请输入出水TN")
        @ApiModelProperty(value = "出水TN（mg/L）")
        private BigDecimal assOutTn;

        @NotNull(message = "请输入出水TP")
        @ApiModelProperty(value = "出水TP（mg/L）")
        private BigDecimal assOutTp;


    }

    @Data
    public static class OtherIndexVM {

        @ApiModelProperty(value = "其他指标编码")
        private String methodCode;

        @ApiModelProperty(value = "其他指标名称")
        private String methodName;

        @ApiModelProperty(value = "指标量（kg/m）")
        private BigDecimal indexCapacity;
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

        @NotNull(message = "请输入进水流量")
        @ApiModelProperty(value = "进水流量（mg/L）", required = true)
        private BigDecimal inFlow;

        @NotNull(message = "请输入进水氨氮")
        @ApiModelProperty(value = "进水氨氮（mg/L）", required = true)
        private BigDecimal inAmmonia;

        @NotNull(message = "请输入进水COD")
        @ApiModelProperty(value = "进水COD（mg/L）", required = true)
        private BigDecimal inCod;

        @NotNull(message = "请输入进水TP")
        @ApiModelProperty(value = "进水TP（mg/L）", required = true)
        private BigDecimal inTp;

        @ApiModelProperty(value = "进水TN（mg/L）")
        private BigDecimal inTn;

        @ApiModelProperty(value = "进水PH（mg/L）")
        private BigDecimal inPh;

        @ApiModelProperty(value = "进水SS（mg/L）")
        private BigDecimal inSs;

        @NotNull(message = "请输入出水流量")
        @ApiModelProperty(value = "出水流量（mg/L）", required = true)
        private BigDecimal outFlow;

        @NotNull(message = "请输入出水氨氮")
        @ApiModelProperty(value = "出水氨氮（mg/L）", required = true)
        private BigDecimal outAmmonia;

        @NotNull(message = "请输入出水COD")
        @ApiModelProperty(value = "出水COD（mg/L）", required = true)
        private BigDecimal outCod;

        @NotNull(message = "请输入出水TP")
        @ApiModelProperty(value = "出水TP（mg/L）", required = true)
        private BigDecimal outTp;

        @ApiModelProperty(value = "出水TN（mg/L）")
        private BigDecimal outTn;

        @ApiModelProperty(value = "出水PH（mg/L）")
        private BigDecimal outPh;

        @ApiModelProperty(value = "出水SS（mg/L）")
        private BigDecimal outSs;

        @ApiModelProperty(value = "请输入厌氧池DO（mg/L）", required = true)
        private BigDecimal anaerobicPoolDo;

        @ApiModelProperty(value = "请输入缺氧池DO（mg/L）", required = true)
        private BigDecimal anoxicPoolDo;

        @ApiModelProperty(value = "请输入好氧池DO（mg/L）", required = true)
        private BigDecimal aerobicPoolDo;

        @ApiModelProperty(value = "缺氧池出口硝酸盐（mg/L）")
        private BigDecimal anoxicPoolDoOutNit;

        @ApiModelProperty(value = "亚硝酸盐（mg/L）")
        private BigDecimal aerobicPoolNit;

    }

}
