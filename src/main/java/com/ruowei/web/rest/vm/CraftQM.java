package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CraftQM {


    private Long id;

    @ApiModelProperty(value = "工艺名称", required = true)
    private String name;
}
