package com.ruowei.modules.sys.domain.table;

import com.ruowei.common.entity.PrimaryKeyAutoIncrementEntity;
import com.ruowei.modules.sys.domain.enumeration.DataScopeType;
import com.ruowei.modules.sys.domain.enumeration.RoleStatusType;
import com.ruowei.modules.sys.domain.enumeration.RoleType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 角色表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysRole extends PrimaryKeyAutoIncrementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public SysRole(){}

    public SysRole(Long id) {
        this.id = id;
    }

    public SysRole(String id) {
        this.id = (id == null ? null : Long.parseLong(id));
    }

    /**
     * 角色编码 ，该字段不作为表的关联外键，仅供展示
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "role_code", length = 100, nullable = false, unique = true)
    private String roleCode;

    /**
     * 角色名称
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "role_name", length = 100, nullable = false, unique = true)
    private String roleName;

    /**
     * 角色类型
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", nullable = false)
    private RoleType roleType;

    /**
     * 角色排序（升序）
     */
    @Column(name = "role_sort")
    private Integer roleSort;

    /**
     * 是否系统内置
     */
    @NotNull
    @Column(name = "is_sys", nullable = false)
    private Boolean isSys;

    /**
     * 数据范围设置
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "data_scope")
    private DataScopeType dataScope;

    /**
     * 适应业务范围
     */
    @Size(max = 255)
    @Column(name = "biz_scope", length = 255)
    private String bizScope;

    /**
     * 状态
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RoleStatusType status;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getRoleCode() {
        return roleCode;
    }

    public SysRole roleCode(String roleCode) {
        this.roleCode = roleCode;
        return this;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public SysRole roleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public SysRole roleType(RoleType roleType) {
        this.roleType = roleType;
        return this;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Integer getRoleSort() {
        return roleSort;
    }

    public SysRole roleSort(Integer roleSort) {
        this.roleSort = roleSort;
        return this;
    }

    public void setRoleSort(Integer roleSort) {
        this.roleSort = roleSort;
    }

    public Boolean isIsSys() {
        return isSys;
    }

    public SysRole isSys(Boolean isSys) {
        this.isSys = isSys;
        return this;
    }

    public void setIsSys(Boolean isSys) {
        this.isSys = isSys;
    }

    public DataScopeType getDataScope() {
        return dataScope;
    }

    public SysRole dataScope(DataScopeType dataScope) {
        this.dataScope = dataScope;
        return this;
    }

    public void setDataScope(DataScopeType dataScope) {
        this.dataScope = dataScope;
    }

    public String getBizScope() {
        return bizScope;
    }

    public SysRole bizScope(String bizScope) {
        this.bizScope = bizScope;
        return this;
    }

    public void setBizScope(String bizScope) {
        this.bizScope = bizScope;
    }

    public RoleStatusType getStatus() {
        return status;
    }

    public SysRole status(RoleStatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(RoleStatusType status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public SysRole remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysRole)) {
            return false;
        }
        return id != null && id.equals(((SysRole) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysRole{" +
            "id=" + getId() +
            ", roleCode='" + getRoleCode() + "'" +
            ", roleName='" + getRoleName() + "'" +
            ", roleType='" + getRoleType() + "'" +
            ", roleSort=" + getRoleSort() +
            ", isSys='" + isIsSys() + "'" +
            ", dataScope='" + getDataScope() + "'" +
            ", bizScope='" + getBizScope() + "'" +
            ", status='" + getStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
