package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;

public class PlatformVM {

    @ApiModelProperty(value = "平台名称")
    private String platformName;

    @ApiModelProperty(value = "平台详细地址")
    private String platformAddress;

    public String getplatformName() {
        return platformName;
    }

    public void setplatformName(String platformName) {
        this.platformName = platformName;
    }
    public String getPlatformAddress() {
        return platformAddress;
    }

    public void setPlatformAddress(String platformAddress) {
        this.platformAddress = platformAddress;
    }
}
