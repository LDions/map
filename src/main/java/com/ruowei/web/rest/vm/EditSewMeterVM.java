package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class EditSewMeterVM {
    //通过改字段确定是集团中哪个水厂的仪表数据修改
    @ApiModelProperty(value = "水厂编码")
    private String code;

    @ApiModelProperty(value = "校表编码")
    private String meterCode;

    @ApiModelProperty(value = "工艺code")
    private String craftCode;

    @ApiModelProperty(value = "进水氨氮（mg/L）")
    private BigDecimal corInAmmonia;

    @ApiModelProperty(value = "进水COD（mg/L）")
    private BigDecimal corInCod;

    @ApiModelProperty(value = "进水TN（mg/L）")
    private BigDecimal corInTn;

    @ApiModelProperty(value = "进水TP（mg/L）")
    private BigDecimal corInTp;

    @ApiModelProperty(value = "缺氧池出口硝酸盐（mg/L）")
    private BigDecimal corAnoxicPoolDoOutNit;

    @ApiModelProperty(value = "好氧池出口硝酸盐（mg/L）")
    private BigDecimal corAerobicPoolDoOutNit;

    @ApiModelProperty(value = "出水氨氮（mg/L）")
    private BigDecimal corOutAmmonia;

    @ApiModelProperty(value = "出水COD（mg/L）")
    private BigDecimal corOutCod;

    @ApiModelProperty(value = "出水TN（mg/L）")
    private BigDecimal corOutTn;

    @ApiModelProperty(value = "出水TP（mg/L）")
    private BigDecimal corOutTp;

    @ApiModelProperty(value = "采集时间")
    private Instant dayTime;
}
