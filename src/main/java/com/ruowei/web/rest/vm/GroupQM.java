package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GroupQM {

    @ApiModelProperty(value = "集团详细地址")
    private String groupAddress;

    @ApiModelProperty(value = "水厂所属平台id")
    private Long platformId;

    @ApiModelProperty(value = "集团名称")
    private String groupName;
}
