package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserEnterpriseDTO {

    @ApiModelProperty(value = "水厂编码")
    private String code;

    @ApiModelProperty(value = "集团编码")
    private String groupCode;
}
