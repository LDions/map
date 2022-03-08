package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 碳排放报告下载
 */
@Data
public class CarbonEmiReportVM {

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
    @ApiModelProperty(value = "行业类型编码")
    private String industryCode;
}
