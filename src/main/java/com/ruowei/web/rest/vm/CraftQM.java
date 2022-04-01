package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CraftQM {


    @ApiModelProperty(value = "工艺Code", required = true)
    private String craftCode;

    @ApiModelProperty(value = "工艺名称", required = true)
    private String craftName;
}
