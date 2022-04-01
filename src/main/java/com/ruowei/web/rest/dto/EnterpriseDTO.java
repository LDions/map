package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EnterpriseDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "水厂名称")
    private String name;

    @ApiModelProperty(value = "经营详细地址经度")
    private String enterpriseLongitude;

    @ApiModelProperty(value = "经营详细地址纬度")
    private String enterpriseLatitude;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否为试点水厂", required = true)
    private String isTry;

    @ApiModelProperty(value = "水厂所属集团id")
    private Long groupId;

    @ApiModelProperty(value = "集团联系人")
    private String contactName;

    @ApiModelProperty(value = "联系人电话")
    private String contactPhone;
}
