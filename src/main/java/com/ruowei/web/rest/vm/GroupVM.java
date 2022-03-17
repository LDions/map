package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;

public class GroupVM {

    @ApiModelProperty(value = "集团名称")
    private String groupAddress;

    @ApiModelProperty(value = "水厂所属平台id")
    private Long platformId;

    @ApiModelProperty(value = "集团详细地址")
    private String groupName;

    public String getGroupAddress() {
        return groupAddress;
    }

    public void setGroupAddress(String groupAddress) {
        this.groupAddress = groupAddress;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
