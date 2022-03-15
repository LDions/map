package com.ruowei.web.rest.dto;

import com.ruowei.web.rest.vm.DataVM;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class DataDTO {

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class GroupDTO extends DataDTO {

        @ApiModelProperty(value = "集团名称")
        private String name;

        @ApiModelProperty(value = "集团编码")
        private String groupCode;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class EntDTO extends DataDTO {

        @ApiModelProperty(value = "水厂名称")
        private String name;

        @ApiModelProperty(value = "水厂编码")
        private String entCode;

        @ApiModelProperty(value = "上属集团")
        private String groupCode;

    }

    @Data
    public static class CraftDTO{

        @ApiModelProperty(value = "工艺段名称")
        private String name;

        @ApiModelProperty(value = "工艺段编码")
        private String craftCode;
    }
}
