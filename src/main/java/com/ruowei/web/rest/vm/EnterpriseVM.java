package com.ruowei.web.rest.vm;

import com.ruowei.domain.enumeration.EnterpriseStatusType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

public class EnterpriseVM {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPointDataName() {
        return pointDataName;
    }

    public void setPointDataName(String pointDataName) {
        this.pointDataName = pointDataName;
    }

    public Long groupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
