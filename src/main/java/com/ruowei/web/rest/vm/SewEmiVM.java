package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class SewEmiVM {

    @Data
    @ApiOperation(value = "仪表数据")
    @EqualsAndHashCode(callSuper = true)
    public static class SewProcessVM extends SewEmiVM {

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
    }

    @Data
    @ApiOperation(value = "校表数据")
    @EqualsAndHashCode(callSuper = true)
    public static class SewMeterVM extends SewEmiVM {

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

    @Data
    @ApiOperation(value = "化验数据")
    @EqualsAndHashCode(callSuper = true)
    public static class SewSluVM extends SewEmiVM {

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

    @Data
    @ApiOperation(value = "日报表数据")
    @EqualsAndHashCode(callSuper = true)
    public static class SewPotVM extends SewEmiVM {


        @ApiModelProperty(value = "碳源投加量（mg/L）")
        private BigDecimal dayCarAdd;

        @ApiModelProperty(value = "生化池-厌氧池PH（mg/L）")
        private BigDecimal dayAnaerobicPoolPh;

        @ApiModelProperty(value = "生化池-厌氧池ORP （mg/L）")
        private BigDecimal dayAnaerobicPoolOrp;

        @ApiModelProperty(value = "生化池-厌氧池DO（mg/L）")
        private BigDecimal dayAnaerobicPoolDo;

        @ApiModelProperty(value = "生化池-厌氧池SOUR（mg/L）")
        private BigDecimal dayAnaerobicPoolSour;

        @ApiModelProperty(value = "生化池-厌氧池SV（mg/L）")
        private BigDecimal dayAnaerobicPoolSv;

        @ApiModelProperty(value = "生化池-厌氧池MLSS（mg/L）")
        private BigDecimal dayAnaerobicPoolMlss;

        @ApiModelProperty(value = "生化池-厌氧池温度（mg/L）")
        private BigDecimal dayAnaerobicPoolTemper;

        @ApiModelProperty(value = "生化池-缺氧池PH（mg/L）")
        private BigDecimal dayAnoxicPoolPh;

        @ApiModelProperty(value = "生化池-缺氧池ORP （mg/L）")
        private BigDecimal dayAnoxicPoolOrp;

        @ApiModelProperty(value = "生化池-缺氧池DO（mg/L）")
        private BigDecimal dayAnoxicPoolDo;

        @ApiModelProperty(value = "生化池-缺氧池SOUR（mg/L）")
        private BigDecimal dayAnoxicPoolSour;

        @ApiModelProperty(value = "生化池-缺氧池SV（mg/L）")
        private BigDecimal dayAnoxicPoolSv;

        @ApiModelProperty(value = "生化池-缺氧池MLSS（mg/L）")
        private BigDecimal dayAnoxicPoolMlss;

        @ApiModelProperty(value = "生化池-缺氧池温度（mg/L）")
        private BigDecimal dayAnoxicPoolTemper;

        @ApiModelProperty(value = "生化池-好氧池PH（mg/L）")
        private BigDecimal dayAerobicPoolPh;

        @ApiModelProperty(value = "生化池-好氧池ORP （mg/L）")
        private BigDecimal dayAerobicPoolOrp;

        @ApiModelProperty(value = "生化池-好氧池DO（mg/L）")
        private BigDecimal dayAerobicPoolDo;

        @ApiModelProperty(value = "生化池-好氧池SOUR（mg/L）")
        private BigDecimal dayAerobicPoolSour;

        @ApiModelProperty(value = "生化池-好氧池SV（mg/L）")
        private BigDecimal dayAerobicPoolSv;

        @ApiModelProperty(value = "生化池-好氧池MLSS（mg/L）")
        private BigDecimal dayAerobicPoolMlss;

        @ApiModelProperty(value = "生化池-好氧池MLVSS（mg/L）")
        private BigDecimal dayAerobicPoolMlvss;

        @ApiModelProperty(value = "生化池-好氧池SVI（mg/L）")
        private BigDecimal dayAerobicPoolSvi;

        @ApiModelProperty(value = "生化池-好氧池温度（mg/L）")
        private BigDecimal dayAerobicPoolTemper;
    }
}
