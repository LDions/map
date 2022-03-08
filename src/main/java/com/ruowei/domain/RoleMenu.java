package com.ruowei.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 角色菜单关系
 */
@ApiModel(description = "角色菜单关系")
@Entity
@Table(name = "sys_role_menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色ID
     */
    @NotNull
    @ApiModelProperty(value = "角色ID", required = true)
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    /**
     * 菜单ID
     */
    @NotNull
    @ApiModelProperty(value = "菜单ID", required = true)
    @Column(name = "menu_id", nullable = false)
    private Long menuId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleMenu id(Long id) {
        this.id = id;
        return this;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public RoleMenu roleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return this.menuId;
    }

    public RoleMenu menuId(Long menuId) {
        this.menuId = menuId;
        return this;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleMenu)) {
            return false;
        }
        return id != null && id.equals(((RoleMenu) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoleMenu{" +
            "id=" + getId() +
            ", roleId=" + getRoleId() +
            ", menuId=" + getMenuId() +
            "}";
    }
}
