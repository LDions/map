package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SituationAnalysisQM {

    @ApiModelProperty(value = "指标名称")
    private String target;
}
