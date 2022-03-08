package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmiFactorDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "版本号")
    private String versionNum;

    @ApiModelProperty(value = "操作员姓名")
    private String operatorName;

    @ApiModelProperty(value = "修改时间")
    private LocalDate modifyDate;

    @ApiModelProperty(value = "备注")
    private String remark;
}
