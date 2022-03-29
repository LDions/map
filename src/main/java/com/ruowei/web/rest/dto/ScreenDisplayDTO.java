package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ScreenDisplayDTO {


    @ApiModelProperty(value = "指标名称")
    private List<String> targets;
    @ApiModelProperty(value = "工艺Code")
    private String craftCode;


}
