package com.ruowei.modules.sys.domain;
import com.ruowei.common.entity.PrimaryKeyAutoIncrementEntity;
import com.ruowei.modules.sys.domain.enumeration.ControlType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * 角色数据权限表
 * @author JeeSite
 */
@ApiModel(description = "角色数据权限表 @author JeeSite")
@Entity
@Table(name = "sys_role_data_scope")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysRoleDataScope extends PrimaryKeyAutoIncrementEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 控制角色ID
     */
    @Size(max = 100)
    @ApiModelProperty(value = "控制角色ID")
    @Column(name = "sys_role_id", length = 100)
    private Long sysRoleId;

    /**
     * 控制类型
     */
    @ApiModelProperty(value = "控制类型")
    @Enumerated(EnumType.STRING)
    @Column(name = "ctrl_type")
    private ControlType ctrlType;

    /**
     * 控制数据，如控制类型为公司，那么这里就是公司编号
     */
    @Size(max = 64)
    @ApiModelProperty(value = "控制数据，如控制类型为公司，那么这里就是公司编号")
    @Column(name = "ctrl_data", length = 64)
    private String ctrlData;

    /**
     * 控制权限 ???????????????????
     */
    @Size(max = 64)
    @ApiModelProperty(value = "控制权限 ???????????????????")
    @Column(name = "ctrl_permi", length = 64)
    private String ctrlPermi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getSysRoleId() {
        return sysRoleId;
    }

    public SysRoleDataScope sysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
        return this;
    }

    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public ControlType getCtrlType() {
        return ctrlType;
    }

    public SysRoleDataScope ctrlType(ControlType ctrlType) {
        this.ctrlType = ctrlType;
        return this;
    }

    public void setCtrlType(ControlType ctrlType) {
        this.ctrlType = ctrlType;
    }

    public String getCtrlData() {
        return ctrlData;
    }

    public SysRoleDataScope ctrlData(String ctrlData) {
        this.ctrlData = ctrlData;
        return this;
    }

    public void setCtrlData(String ctrlData) {
        this.ctrlData = ctrlData;
    }

    public String getCtrlPermi() {
        return ctrlPermi;
    }

    public SysRoleDataScope ctrlPermi(String ctrlPermi) {
        this.ctrlPermi = ctrlPermi;
        return this;
    }

    public void setCtrlPermi(String ctrlPermi) {
        this.ctrlPermi = ctrlPermi;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysRoleDataScope)) {
            return false;
        }
        return id != null && id.equals(((SysRoleDataScope) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysRoleDataScope{" +
            "id=" + getId() +
            ", sysRoleId='" + getSysRoleId() + "'" +
            ", ctrlType='" + getCtrlType() + "'" +
            ", ctrlData='" + getCtrlData() + "'" +
            ", ctrlPermi='" + getCtrlPermi() + "'" +
            "}";
    }
}
