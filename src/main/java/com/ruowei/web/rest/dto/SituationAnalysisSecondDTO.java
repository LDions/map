package com.ruowei.web.rest.dto;

import com.ruowei.web.rest.vm.DateTimeRangeVM;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.List;

@Data
public class SituationAnalysisSecondDTO {

    @ApiParam(value = "时间范围集合")
    private final List<DateTimeRangeVM> datetimeRange;

    @ApiParam(value = "数据所在工艺Id")
    private final String craftCode;

    @ApiParam(value = "日期分段")
    private final String subsection;

    @ApiParam(value = "指标")
    private final String target;

}

