package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class DistrictDTO {

    @ApiModelProperty(value = "地区编码")
    private String value;

    @ApiModelProperty(value = "地区名称")
    private String label;

    @ApiModelProperty(value = "子地区信息")
    private List<DistrictDTO> children;
}
