package com.ruowei.modules.sys.domain;

import com.ruowei.common.entity.PrimaryKeyAutoIncrementEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户与角色关联表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_user_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysUserRole extends PrimaryKeyAutoIncrementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户外键
     */
    @ApiModelProperty(value = "用户外键")
    @Column(name = "sys_user_id")
    private Long sysUserId;

    /**
     * 角色外键
     */
    @ApiModelProperty(value = "角色外键")
    @Column(name = "sys_role_id")
    private Long sysRoleId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getSysUserId() {
        return sysUserId;
    }

    public SysUserRole sysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
        return this;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Long getSysRoleId() {
        return sysRoleId;
    }

    public SysUserRole sysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
        return this;
    }

    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysUserRole)) {
            return false;
        }
        return id != null && id.equals(((SysUserRole) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
            "id=" + getId() +
            ", sysUserId='" + getSysUserId() + "'" +
            ", sysRoleId='" + getSysRoleId() + "'" +
            "}";
    }
}
