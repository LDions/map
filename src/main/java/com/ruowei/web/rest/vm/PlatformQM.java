package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PlatformQM {

    @ApiModelProperty(value = "平台名称")
    private String platformName;

    @ApiModelProperty(value = "平台详细地址")
    private String platformAddress;
}
