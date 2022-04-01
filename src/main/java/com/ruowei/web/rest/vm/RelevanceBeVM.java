package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RelevanceBeVM {

    private Long id;

    @ApiModelProperty(value = "指标名称")
    private String target;
}
