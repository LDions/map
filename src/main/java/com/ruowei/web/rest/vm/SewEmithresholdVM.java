package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SewEmithresholdVM {
    @ApiModelProperty(value = "操作[0新增，1编辑]")
    private Integer operate;

    @ApiModelProperty(value = "水厂编码")
    private String enterpriseCode;

    @ApiModelProperty(value = "进水COD阈值")
    private BigDecimal inCodLimit;

    @ApiModelProperty(value = "进水总氮上限（mg/L）")
    private BigDecimal inTotalNLimit;

    @ApiModelProperty(value = "进水氨氮期上限（mg/L）")
    private BigDecimal inTotalANLimit;

    @ApiModelProperty(value = "进水总磷期上限（mg/L）")
    private BigDecimal inTotalPLimit;

    @ApiModelProperty(value = "出水COD阈值（mg/L）")
    private BigDecimal outCodLimit;

    @ApiModelProperty(value = "出水总氮上限（mg/L）")
    private BigDecimal outTotalNLimit;

    @ApiModelProperty(value = "出水氨氮期上限（mg/L）")
    private BigDecimal outTotalANLimit;

    @ApiModelProperty(value = "出水总磷期上限（mg/L）")
    private BigDecimal outTotalPLimit;
}
