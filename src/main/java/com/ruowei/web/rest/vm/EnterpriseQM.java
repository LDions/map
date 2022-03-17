package com.ruowei.web.rest.vm;

import com.ruowei.domain.enumeration.EnterpriseStatusType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EnterpriseQM {

    @ApiModelProperty(value = "水厂名称")
    private String name;

    @ApiModelProperty(value = "设备名称")
    private String equipmentName;

    @ApiModelProperty(value = "数据类型")
    private String dataType;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "点数据名称")
    private String pointDataName;

    @ApiModelProperty(value = "水厂所属集团id")
    private Long groupId;

    @ApiModelProperty(value = "水厂所属集团名称")
    private String groupName;
}
