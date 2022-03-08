package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CarbonEmiMonthVM {

    @NotNull
    @ApiModelProperty(value = "单据号")
    private String documentCode;

    @NotNull
    @ApiModelProperty(value = "核算年份")
    private String accYear;

    @NotNull
    @ApiModelProperty(value = "核算月份")
    private String accMonth;

    @NotNull
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    @NotNull
    @ApiModelProperty(value = "行业类型编码")
    private String industryCode;

    @NotNull
    @ApiModelProperty(value = "下一个月传TRUE，上一个月传FALSE")
    private Boolean isNext;
}
