package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Calendar;

@Data
public class SituationAnalysisVM {

    @ApiParam(value = "指标平均值")
    private BigDecimal value;

    @ApiParam(value = "指标平均时间")
    private Calendar time;
}
