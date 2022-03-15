package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.Instant;


@Data
public class EntCraftDataDTO {

    private Long id;

    @ApiModelProperty(value = "集团名称")
    private String groupName;

    @ApiModelProperty(value = "水厂名称")
    private String entName;

    @ApiModelProperty(value = "工艺段名称")
    private String craftName;

    @ApiModelProperty(value = "仪表数据时间")
    private Instant dayTime;

}
