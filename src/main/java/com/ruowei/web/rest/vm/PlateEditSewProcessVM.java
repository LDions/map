package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class PlateEditSewProcessVM {
    //用于在平台中确定某个集团
    @ApiModelProperty(value = "集团编码")
    private String groupCode;

    //通过改字段确定是集团中哪个水厂的仪表数据修改
    @ApiModelProperty(value = "水厂编码")
    private String code;

    @ApiModelProperty(value = "仪表编码")
    private String processCode;

    @ApiModelProperty(value = "工艺类型编码")
    private String craftCode;

    //可能修改的仪表数据
    @ApiModelProperty(value = "进水流量（mg/L）")
    private BigDecimal inFlow;

    @ApiModelProperty(value = "进水氨氮（mg/L）")
    private BigDecimal inAmmonia;

    @ApiModelProperty(value = "进水COD（mg/L）")
    private BigDecimal inCod;

    @ApiModelProperty(value = "进水TN（mg/L）")
    private BigDecimal inTn;

    @ApiModelProperty(value = "进水Tp（mg/L）")
    private BigDecimal inTp;

    @ApiModelProperty(value = "进水Ss（mg/L）")
    private BigDecimal inSs;

    @ApiModelProperty(value = "出水流量（mg/L）")
    private BigDecimal outFlow;

    @ApiModelProperty(value = "出水氨氮（mg/L）")
    private BigDecimal outAmmonia;

    @ApiModelProperty(value = "出水COD（mg/L）")
    private BigDecimal outCod;

    @ApiModelProperty(value = "出水TN（mg/L）")
    private BigDecimal outTn;

    @ApiModelProperty(value = "出水Tp（mg/L）")
    private BigDecimal outTp;

    @ApiModelProperty(value = "出水Ss（mg/L）")
    private BigDecimal outSs;

    @ApiModelProperty(value = "缺氧池（mg/L）")
    private BigDecimal anoxicPoolDo;

    @ApiModelProperty(value = "好氧池（mg/L）")
    private BigDecimal aerobicPoolDo;

    @ApiModelProperty(value = "缺氧池出口硝酸盐（mg/L）")
    private BigDecimal anoxicPoolDoOutNit;

    @ApiModelProperty(value = "好氧池出口亚硝酸盐（mg/L）")
    private BigDecimal aerobicPoolNit;

    @ApiModelProperty(value = "采集时间")
    private Instant dayTime;
}
