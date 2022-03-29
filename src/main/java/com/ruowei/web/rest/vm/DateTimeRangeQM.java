package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.Instant;

@Data
public class DateTimeRangeQM {


    @ApiModelProperty(value = "开始数据时间")
    private Instant beginTime;

    @ApiModelProperty(value = "结束数据时间")
    private Instant endTime;

}
