package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RelationQM {

    @ApiModelProperty(value = "指标来源")
    private String source;

    @ApiModelProperty(value = "指标名称")
    private List<String> target;


}
