package com.ruowei.modules.sys.domain.relationship;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户与角色关系表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_user_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysUserRoleRelationship implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Long id;

    /**
     * 用户ID
     */
    @NotNull
    @Column(name = "sys_user_id", nullable = false)
    private Long sysUserId;

    /**
     * 角色ID
     */
    @NotNull
    @Column(name = "sys_role_id",nullable = false)
    private Long sysRoleId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getSysUserId() {
        return sysUserId;
    }

    public SysUserRoleRelationship sysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
        return this;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Long getSysRoleId() {
        return sysRoleId;
    }

    public SysUserRoleRelationship sysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
        return this;
    }

    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysUserRoleRelationship)) {
            return false;
        }
        return id != null && id.equals(((SysUserRoleRelationship) o).id);
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
