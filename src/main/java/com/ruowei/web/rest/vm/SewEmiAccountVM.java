package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * 污水核算请求参数
 */
@Data
public class SewEmiAccountVM {
    //TODO 待修改 一次核算所需所有参数

    @ApiModelProperty(value = "单据号", required = true)
    private String documentCode;

    @ApiModelProperty(value = "水厂ID", required = true)
    private Long enterpriseId;

    @ApiModelProperty(value = "核算方式（自动、手动）", required = true)
    private String acctype;

    @ApiModelProperty(value = "核算年份", required = true)
    private String accYear;

    @ApiModelProperty(value = "核算月份", required = true)
    private String accMonth;

    @ApiModelProperty(value = "核算时间范围起", required = true)
    private String accTimeStart;

    @ApiModelProperty(value = "核算时间范围止", required = true)
    private String accTimeStop;

    @ApiModelProperty(value = "预测未来时间", required = true)
    private String predictTime;

    @ApiModelProperty(value = "工艺id", required = true)
    private Long craftId;

    @ApiModelProperty(value = "出水总氮", required = true)
    private BigDecimal totalOutN;

    @ApiModelProperty(value = "出水氨氮", required = true)
    private BigDecimal outAN;

    @ApiModelProperty(value = "碳源投加量）", required = true)
    private BigDecimal carbonAdd;

    @ApiModelProperty(value = "除磷药剂）", required = true)
    private BigDecimal phosphorusremover;

    @ApiModelProperty(value = "仪表数据")
    private List<SewProcessVM> sewProcesss;

    @ApiModelProperty(value = "日报表数据")
    private List<SewPotVM> sewPots;

    @ApiModelProperty(value = "化验数据")
    private List<SewSluVM> sewSlus;

    @ApiModelProperty(value = "其他指标")
    private List<OtherIndexVM> otherIndexs;

    @ApiModelProperty(value = "校表数据")
    private List<SewMeterVM> sewMeterVMS;


    public static class SewMeterVM extends SewEmiAccountVM{

        @ApiModelProperty(value = "进水氨氮（mg/L）")
        private BigDecimal assInAmmonia;

        @ApiModelProperty(value = "进水COD（mg/L）")
        private BigDecimal assInCod;

        @ApiModelProperty(value = "进水TN（mg/L）")
        private BigDecimal assInTn;

        @ApiModelProperty(value = "进水TP（mg/L）")
        private BigDecimal assInTp;

        @ApiModelProperty(value = "缺氧池出口硝酸盐（mg/L）")
        private BigDecimal assAnoxicPoolDoOutNit;

        @ApiModelProperty(value = "好氧池出口硝酸盐（mg/L）")
        private BigDecimal assAerobicPoolDoOutNit;

        @ApiModelProperty(value = "出水氨氮（mg/L）")
        private BigDecimal assOutAmmonia;

        @ApiModelProperty(value = "出水COD（mg/L）")
        private BigDecimal assOutCod;

        @ApiModelProperty(value = "出水TN（mg/L）")
        private BigDecimal assOutTn;

        @ApiModelProperty(value = "出水TP（mg/L）")
        private BigDecimal assOutTp;
    }
    public static class SewPotVM extends SewEmiAccountVM{

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

        @NotNull(message = "请输入生化池-好氧池MLVSS")
        @ApiModelProperty(value = "生化池-好氧池MLVSS（mg/L）")
        private BigDecimal dayAerobicPoolMlvss;

        @NotNull(message = "请输入生化池-好氧池SVI")
        @ApiModelProperty(value = "生化池-好氧池SVI（mg/L）")
        private BigDecimal dayAerobicPoolSvi;

        @NotNull(message = "请输入生化池-好氧池温度")
        @ApiModelProperty(value = "生化池-好氧池温度（mg/L）")
        private BigDecimal dayAerobicPoolTemper;

        @NotNull(message = "采集时间")
        @ApiModelProperty(value = "采集时间")
        private Instant dayTime;
    }

    public static class SewSluVM extends SewEmiAccountVM{

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

        @NotNull(message = "采集时间")
        @ApiModelProperty(value = "采集时间")
        private Instant dayTime;
    }

    public static class OtherIndexVM extends SewEmiAccountVM{

        @ApiModelProperty(value = "其他指标编码")
        private String methodCode;

        @ApiModelProperty(value = "其他指标名称")
        private String methodName;

        @ApiModelProperty(value = "指标量（kg/m）")
        private BigDecimal indexCapacity;
    }

    public static class SewProcessVM extends SewEmiAccountVM{

        @ApiModelProperty(value = "工艺类型编码")
        private String craftCode;

        @ApiModelProperty(value = "工艺类型名称")
        private String processTypeName;

        @ApiModelProperty(value = "进水流量（mg/L）")
        private BigDecimal inFlow;

        @ApiModelProperty(value = "进水氨氮（mg/L）")
        private BigDecimal inAmmonia;

        @ApiModelProperty(value = "进水COD（mg/L）")
        private BigDecimal inCod;

        @ApiModelProperty(value = "进水TP（mg/L）")
        private BigDecimal inTp;

        @ApiModelProperty(value = "进水TN（mg/L）")
        private BigDecimal inTn;

        @ApiModelProperty(value = "进水SS（mg/L）")
        private BigDecimal inSs;

        @ApiModelProperty(value = "出水流量（mg/L）")
        private BigDecimal outFlow;

        @ApiModelProperty(value = "出水氨氮（mg/L）")
        private BigDecimal outAmmonia;

        @ApiModelProperty(value = "出水COD（mg/L）")
        private BigDecimal outCod;

        @ApiModelProperty(value = "出水TP（mg/L）")
        private BigDecimal outTp;

        @ApiModelProperty(value = "出水TN（mg/L）")
        private BigDecimal outTn;

        @ApiModelProperty(value = "出水PH（mg/L）")
        private BigDecimal outPh;

        @ApiModelProperty(value = "出水SS（mg/L）")
        private BigDecimal outSs;

        @ApiModelProperty(value = "请输入缺氧池DO（mg/L）")
        private BigDecimal anoxicPoolDo;

        @ApiModelProperty(value = "请输入好氧池DO（mg/L）")
        private BigDecimal aerobicPoolDo;

        @ApiModelProperty(value = "缺氧池出口硝酸盐（mg/L）")
        private BigDecimal anoxicPoolDoOutNit;

        @ApiModelProperty(value = "好氧池出口亚硝酸盐（mg/L）")
        private BigDecimal aerobicPoolNit;

        @ApiModelProperty(value = "采集时间")
        private Instant dayTime;

    }

}
