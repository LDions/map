package com.ruowei.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 水厂信息
 */
@ApiModel(description = "水厂信息")
@Entity
@Table(name = "sys_enterprise")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Enterprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Enterprise id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Enterprise name(String name) {
        this.name = name;
        return this;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
    public Enterprise equipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
        return this;
    }

    public String getEnterpriseAddress() {
        return enterpriseAddress;
    }

    public void setEnterpriseAddress(String enterpriseAddress) {
        this.enterpriseAddress = enterpriseAddress;
    }
    public Enterprise enterpriseAddress(String enterpriseAddress) {
        this.enterpriseAddress = enterpriseAddress;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Enterprise remark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getIsTry() {
        return isTry;
    }

    public void setIsTry(String isTry) {
        this.isTry = isTry;
    }
    public Enterprise isTry(String isTry) {
        this.isTry = isTry;
        return this;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    public Enterprise groupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getContactName() {
        return contactName;
    }
    public Enterprise contactName(String contactName) {
        this.contactName = contactName;
        return this;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }
    public Enterprise contactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Enterprise)) {
            return false;
        }
        return id != null && id.equals(((Enterprise) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Enterprise{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", equipmentName='" + getEquipmentName() + "'" +
            ", enterpriseAddress='" + getEnterpriseAddress() + "'" +
            ", remark='" + getRemark() + "'" +
            ", isTry='" + getIsTry() + "'" +
            ", groupCode='" + getGroupId() + "'" +
            ", contactName='" + getContactName() + "'" +
            ", contactPhone='" + getContactPhone() + "'" +
            "}";
    }
}
