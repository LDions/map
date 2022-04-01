package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.List;

@Data
public class SituationAnalysisFourthlyDTO {

    @ApiParam(value = "被关联的指标id")
    private final Long id;

    @ApiParam(value = "数据所在工艺Id")
    private final String craftCode;

    @ApiParam(value = "日期分段")
    private final String subsection;

    @ApiParam(value = "开始时间")
    private final String beginTime;

    @ApiParam(value = "结束时间")
    private final String endTime;

}
