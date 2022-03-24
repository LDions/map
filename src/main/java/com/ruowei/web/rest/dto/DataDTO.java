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

    }

    @Data
    public static class CraftDTO{

        @ApiModelProperty(value = "工艺段code")
        private String craftCode;

        @ApiModelProperty(value = "工艺段名称")
        private String craftName;
    }
}
