package com.ruowei.modules.sys.domain;
import com.ruowei.common.entity.PrimaryKeyAutoIncrementEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * 角色与菜单关联表
 * @author JeeSite
 */
@ApiModel(description = "角色与菜单关联表 @author JeeSite")
@Entity
@Table(name = "sys_role_menu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysRoleMenu extends PrimaryKeyAutoIncrementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色外键
     */
    @ApiModelProperty(value = "角色外键")
    @Column(name = "sys_role_id")
    private Long sysRoleId;

    /**
     * 菜单外键
     */
    @ApiModelProperty(value = "菜单外键")
    @Column(name = "sys_menu_id")
    private Long sysMenuId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getSysRoleId() {
        return sysRoleId;
    }

    public SysRoleMenu sysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
        return this;
    }

    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Long getSysMenuId() {
        return sysMenuId;
    }

    public SysRoleMenu sysMenuId(Long sysMenuId) {
        this.sysMenuId = sysMenuId;
        return this;
    }

    public void setSysMenuId(Long sysMenuId) {
        this.sysMenuId = sysMenuId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysRoleMenu)) {
            return false;
        }
        return id != null && id.equals(((SysRoleMenu) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysRoleMenu{" +
            "id=" + getId() +
            ", sysRoleId='" + getSysRoleId() + "'" +
            ", sysMenuId='" + getSysMenuId() + "'" +
            "}";
    }
}
