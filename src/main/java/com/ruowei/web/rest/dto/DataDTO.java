package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class DataDTO {

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupDTO extends DataDTO {

        @ApiModelProperty(value = "集团名称")
        private String groupName;

        @ApiModelProperty(value = "集团Code")
        private String groupCode;

        @ApiModelProperty(value = "经营详细地址经度")
        private String groupLongitude;

        @ApiModelProperty(value = "经营详细地址纬度")
        private String groupLatitude;

        @ApiModelProperty(value = "联系人姓名")
        private String groupContactName;

        @ApiModelProperty(value = "联系人手机")
        private String groupContactPhone;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class EntDTO extends DataDTO {

        @ApiModelProperty(value = "水厂名称")
        private String name;

        @ApiModelProperty(value = "水厂Code")
        private String code;

        @ApiModelProperty(value = "上属集团Code")
        private String groupCode;

        @ApiModelProperty(value = "经营详细地址经度")
        private String enterpriseLongitude;

        @ApiModelProperty(value = "经营详细地址纬度")
        private String enterpriseLatitude;

        @ApiModelProperty(value = "联系人姓名")
        private String contactName;

        @ApiModelProperty(value = "联系人手机")
        private String contactPhone;

        @ApiModelProperty(value = "是否为试点水厂")
        private Boolean isTry;

        @ApiModelProperty(value = "备注")
        private String remark;

    }

    @Data
    public static class CraftDTO{

        @ApiModelProperty(value = "工艺段code")
        private String craftCode;

        @ApiModelProperty(value = "工艺段名称")
        private String craftName;
    }
}
