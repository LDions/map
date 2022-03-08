package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EmiFactorQM {

    @NotEmpty(message = "请选择项目")
    @ApiModelProperty(value = "项目编码", required = true)
    private String projectCode;

    @ApiModelProperty(value = "修改起始日期")
    private String modifyDateFrom;

    @ApiModelProperty(value = "修改截止日期")
    private String modifyDateTo;
}
