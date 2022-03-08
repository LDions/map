package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 碳排放数据核算查询展示
 */
@Data
public class CarbonEmiDataDTO {

    @ApiModelProperty(value = "单据号")
    private String documentCode;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "录入员姓名")
    private String reporterName;

    @ApiModelProperty(value = "填报时间")
    private Instant reportTime;

    @ApiModelProperty(value = "核算年份")
    private String accYear;

    @ApiModelProperty(value = "核算月份")
    private String accMonth;

    @ApiModelProperty(value = "行业类型编码")
    private String industryCode;

    @ApiModelProperty(value = "行业类型名称")
    private String industryName;

    @ApiModelProperty(value = "本月碳排放（kg/m）")
    private BigDecimal carbonEmi;

    @ApiModelProperty(value = "本月直接碳排放（kg）")
    private BigDecimal carbonDirEmi;

    @ApiModelProperty(value = "本月间接碳排放（kg）")
    private BigDecimal carbonIndirEmi;

    @ApiModelProperty(value = "本月负碳排放（kg）")
    private BigDecimal carbonRed;
}
