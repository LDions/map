package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SituationAnalysisQM {

    @ApiModelProperty(value = "指标名称")
    private String target;

    @ApiModelProperty(value = "指标值")
    private BigDecimal value;
}
