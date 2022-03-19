package com.ruowei.web.rest.vm;

import com.ruowei.domain.enumeration.EnterpriseStatusType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class EnterpriseVM {


    /**
     * 水厂名称
     */
    @ApiModelProperty(value = "水厂名称")
    @Column(name = "name")
    private String name;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    @Column(name = "equipment_name")
    private String equipmentName;

    /**
     * 数据类型
     */
    @ApiModelProperty(value = "经营详细地址")
    @Column(name = "enterprise_address")
    private String enterpriseAddress;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;

    /**
     * 点数据名称
     */
    @ApiModelProperty(value = "是否为试点水厂")
    @Column(name = "is_try")
    private String isTry;

    /**
     * 水厂所属集团编码
     */
    @ApiModelProperty(value = "水厂所属集团id")
    @Column(name = "group_Id")
    private Long groupId;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "集团联系人")
    @Column(name = "contact_name")
    private String contactName;

    /**
     * 电话
     */
    @ApiModelProperty(value = "联系人电话")
    @Column(name = "contact_phone")
    private String contactPhone;

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

    public String getEnterpriseAddress() {
        return enterpriseAddress;
    }

    public void setEnterpriseAddress(String enterpriseAddress) {
        this.enterpriseAddress = enterpriseAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsTry() {
        return isTry;
    }

    public void setIsTry(String isTry) {
        this.isTry = isTry;
    }

    public Long groupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
