package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SituationAnalysisQM {

    @ApiModelProperty(value = "指标名称")
    private String target;

    @ApiModelProperty(value = "指标来源")
    private String source;
}
