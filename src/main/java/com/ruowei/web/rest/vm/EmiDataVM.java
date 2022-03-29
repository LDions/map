package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class EmiDataVM {

//    @ApiModelProperty(value = "操作类型（0新增，1编辑）")
//    private Integer operate;

    @ApiModelProperty(value = "水厂编码")
    private String enterpriseCode;

    @ApiModelProperty(value = "碳排放数据编码")
    private String dataCode;

    @ApiModelProperty(value = "核算方式（自动、手动）")
    private String acctype;

    @ApiModelProperty(value = "核算年份")
    private String accYear;

    @ApiModelProperty(value = "核算月份")
    private String accMonth;

    @ApiModelProperty(value = "核算时间范围起")
    private String accTimeStart;

    @ApiModelProperty(value = "核算时间范围止")
    private String accTimeStop;

    @ApiModelProperty(value = "预测未来时间")
    private Instant predictTime;

    @ApiModelProperty(value = "工艺编码")
    private String craftCode;

    @ApiModelProperty(value = "出水总氮")
    private BigDecimal totalOutN;

    @ApiModelProperty(value = "出水氨氮")
    private BigDecimal outAN;

    @ApiModelProperty(value = "碳源投加量）")
    private BigDecimal carbonAdd;

    @ApiModelProperty(value = "除磷药剂）")
    private BigDecimal phosphorusremover;
}
