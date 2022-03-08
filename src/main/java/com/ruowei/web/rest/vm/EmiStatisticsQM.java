package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EmiStatisticsQM {

    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    @ApiModelProperty(value = "起始核算年份")
    private String accYearFrom;

    @ApiModelProperty(value = "截止核算年份")
    private String accYearTo;

    @ApiModelProperty(value = "起始核算时间")
    private String accTimeFrom;

    @ApiModelProperty(value = "截止核算时间")
    private String accTimeTo;
}
