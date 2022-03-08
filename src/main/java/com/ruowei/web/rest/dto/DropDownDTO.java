package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DropDownDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;
}
