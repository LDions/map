package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 核算结果保存
 */
@Data
public class AccountVM {

    @ApiModelProperty(value = "水厂ID", required = true)
    private Long enterpriseId;

    @ApiModelProperty(value = "工艺id", required = true)
    private Long craftId;

    @ApiModelProperty(value = "核算方式（自动、手动）", required = true)
    private Boolean acctype;

    @ApiModelProperty(value = "核算年份", required = true)
    private String accYear;

    @ApiModelProperty(value = "核算月份", required = true)
    private String accMonth;

    @ApiModelProperty(value = "核算时间", required = true)
    private Instant accTime;

    @ApiModelProperty(value = "预测未来时间", required = true)
    private Instant predictTime;

    @ApiModelProperty(value = "出水总氮", required = true)
    private BigDecimal totalOutN;

    @ApiModelProperty(value = "出水氨氮", required = true)
    private BigDecimal outAN;

    @ApiModelProperty(value = "碳源投加量）")
    private BigDecimal carbonAdd;

    @ApiModelProperty(value = "除磷药剂）")
    private BigDecimal phosphorusremover;


}
