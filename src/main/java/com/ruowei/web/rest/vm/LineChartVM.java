package com.ruowei.web.rest.vm;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class LineChartVM {

    @ApiModelProperty(value = "工艺编码")
    private String craftCode;

    @ApiModelProperty(value = "折线图数据")
    private List<List<List<Object>>> chartData;

    @ApiModelProperty(value = "折线图中点时间")
    private Instant splitPoint;
}
