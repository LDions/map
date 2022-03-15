package com.ruowei.domain;

import com.ruowei.domain.enumeration.EnterpriseStatusType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
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
     * 水厂编号
     */
    @ApiModelProperty(value = "水厂编号")
    @Column(name = "ent_code")
    private String entCode;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    @Column(name = "equipment_name")
    private String equipmentName;

    /**
     * 数据类型
     */
    @ApiModelProperty(value = "数据类型")
    @Column(name = "data_type")
    private String dataType;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;

    /**
     * 点数据名称
     */
    @ApiModelProperty(value = "点数据名称")
    @Column(name = "point_data_name")
    private String pointDataName;

    /**
     * 水厂所属集团编码
     */
    @ApiModelProperty(value = "水厂所属集团编码")
    @Column(name = "group_code")
    private String groupCode;

    /**
     * 水厂所属集团名称
     */
    @ApiModelProperty(value = "水厂所属集团名称")
    @Column(name = "group_name")
    private String groupName;

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

    public String getEntCode() {
        return entCode;
    }

    public void setEntCode(String entCode) {
        this.entCode = entCode;
    }
    public Enterprise entCode(String entCode) {
        this.entCode = entCode;
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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public Enterprise dataType(String dataType) {
        this.dataType = dataType;
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

    public String getPointDataName() {
        return pointDataName;
    }

    public void setPointDataName(String pointDataName) {
        this.pointDataName = pointDataName;
    }
    public Enterprise pointDataName(String pointDataName) {
        this.pointDataName = pointDataName;
        return this;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    public Enterprise groupCode(String groupCode) {
        this.groupCode = groupCode;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public Enterprise groupName(String groupName) {
        this.groupName = groupName;
        return this;
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
            ", entCode='" + getEntCode() + "'" +
            ", equipmentName='" + getEquipmentName() + "'" +
            ", dataType='" + getDataType() + "'" +
            ", remark='" + getRemark() + "'" +
            ", pointDataName='" + getPointDataName() + "'" +
            ", groupCode='" + getGroupCode() + "'" +
            ", groupName='" + getGroupName() + "'" +
            "}";
    }
}
