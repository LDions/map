package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class EnterpriseLocationDTO {

    /**
     * 水厂名称
     */
    @ApiModelProperty(value = "水厂名称")
    private String name;

    /**
     * 水厂id
     */
    @ApiModelProperty(value = "水厂id")
    private Long id;

    /**
     * 是否为试点水厂
     */
    @ApiModelProperty(value = "是否为试点水厂")
    private Boolean isTry;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private String lat;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private String lng;

    /**
     * 工艺数量
     */
    @ApiModelProperty(value = "工艺数量")
    private Long craftNumber;

    /**
     * 上属集团
     */
    @ApiModelProperty(value = "上属集团")
    private String groupName;



}
