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
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class EntDTO extends DataDTO {

        @ApiModelProperty(value = "水厂名称")
        private String name;

        private Long id;

        @ApiModelProperty(value = "上属集团名称")
        private String groupName;

    }

    @Data
    public static class CraftDTO{

        private Long id;

        @ApiModelProperty(value = "工艺段名称")
        private String craftName;
    }
}
