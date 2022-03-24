package com.ruowei.web.rest.vm;


import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class DateTimeRangeVM {


    @ApiModelProperty(value = "开始数据时间")
    private String beginTime;
    @ApiModelProperty(value = "结束数据时间")
    private String endTime;

}
