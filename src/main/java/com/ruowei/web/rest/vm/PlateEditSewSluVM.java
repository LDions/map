package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class PlateEditSewSluVM {

    //用于在平台中确定某个集团
    @ApiModelProperty(value = "集团编码")
    private String groupCode;

    //通过改字段确定是集团中哪个水厂的仪表数据修改
    @ApiModelProperty(value = "水厂编码")
    private String code;

    @ApiModelProperty(value = "化验编码")
    private String sluCode;

    @ApiModelProperty(value = "工艺编码")
    private String craftCode;

    @ApiModelProperty(value = "污泥处置方法编码")
    private String methodCode;

    @ApiModelProperty(value = "污泥处置方法名称")
    private String methodName;

    @ApiModelProperty(value = "进水流量（mg/L）")
    private BigDecimal assInFlow;

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

    @ApiModelProperty(value = "出水流量（mg/L）")
    private BigDecimal assOutFlow;

    @ApiModelProperty(value = "出水氨氮（mg/L）")
    private BigDecimal assOutAmmonia;

    @ApiModelProperty(value = "出水COD（mg/L）")
    private BigDecimal assOutCod;

    @ApiModelProperty(value = "出水TN（mg/L）")
    private BigDecimal assOutTn;

    @ApiModelProperty(value = "出水TP（mg/L）")
    private BigDecimal assOutTp;

    @ApiModelProperty(value = "采集时间")
    private Instant dayTime;
}
