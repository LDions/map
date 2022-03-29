package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Data
public class SituationAnalysisDTO {

    @ApiParam(value = "指标")
    private final List<String> targets;

    @ApiParam(value = "数据所在工艺Id")
    private final String craftCode;

    @ApiParam(value = "日期分段")
    private final String subsection;

    @ApiParam(value = "开始时间")
    private final String beginTime;

    @ApiParam(value = "结束时间")
    private final String endTime;

}
