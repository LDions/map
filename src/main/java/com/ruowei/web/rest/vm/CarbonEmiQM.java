package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 碳排放数据核算查询
 */
@Data
public class CarbonEmiQM {

    @ApiModelProperty(value = "单据号，模糊查询")
    private String documentCode;

    @ApiModelProperty(value = "行业类型编码")
    private String industryCode;

    @ApiModelProperty(value = "企业名称，模糊查询")
    private String enterpriseName;

    @ApiModelProperty(value = "录入员姓名，模糊查询")
    private String reporterName;

    @ApiModelProperty(value = "核算起始年份")
    private String accYearFrom;

    @ApiModelProperty(value = "核算截止年份")
    private String accYearTo;

    @ApiModelProperty(value = "核算起始月份")
    private String accMonthFrom;

    @ApiModelProperty(value = "核算截止月份")
    private String accMonthTo;

    @ApiModelProperty(value = "填报起始时间")
    private String reportTimeFrom;

    @ApiModelProperty(value = "填报截止时间")
    private String reportTimeTo;
}
