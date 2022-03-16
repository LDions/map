package com.ruowei.web.rest.dto;

import com.ruowei.web.rest.vm.SewEmiAccountVM;
import com.ruowei.web.rest.vm.SewEmiVM;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class SewDetailsDTO {

    @Data
    public static class SewProcessDTO {

        private Long id;

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

        @ApiModelProperty(value = "缺氧池DO（mg/L）")
        private BigDecimal anoxicPoolDo;

        @ApiModelProperty(value = "好氧池DO（mg/L）")
        private BigDecimal aerobicPoolDo;

        @ApiModelProperty(value = "缺氧池出口硝酸盐（mg/L）")
        private BigDecimal anoxicPoolDoOutNit;

        @ApiModelProperty(value = "好氧池出口亚硝酸盐（mg/L）")
        private BigDecimal aerobicPoolNit;
    }

    @Data
    public static class SewSluDTO {
        private Long id;

        @ApiModelProperty(value = "进水氨氮（mg/L）")
        private BigDecimal inAmmonia;

        @ApiModelProperty(value = "进水COD（mg/L）")
        private BigDecimal inCod;

        @ApiModelProperty(value = "进水TP（mg/L）")
        private BigDecimal inTp;

        @ApiModelProperty(value = "进水TN（mg/L）")
        private BigDecimal inTn;

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

        @ApiModelProperty(value = "缺氧池出口硝酸盐（mg/L）")
        private BigDecimal anoxicPoolDoOutNit;

        @ApiModelProperty(value = "好氧池出口亚硝酸盐（mg/L）")
        private BigDecimal aerobicPoolNit;
    }

}
