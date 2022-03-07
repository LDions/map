package com.ruowei.modules.sys.pojo.dto;

import com.ruowei.common.pojo.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.ruowei.domain.SysRoleMenu} entity.
 */
@ApiModel(description = "角色与菜单关联表 @author 刘东奇")
public class SysRoleMenuDTO extends BaseDTO implements Serializable {

    /**
     * 角色外键
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "角色外键", required = true)
    private String sysRoleId;

    /**
     * 菜单外键
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "菜单外键", required = true)
    private String sysMenuId;

    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public String getSysMenuId() {
        return sysMenuId;
    }

    public void setSysMenuId(String sysMenuId) {
        this.sysMenuId = sysMenuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysRoleMenuDTO sysRoleMenuDTO = (SysRoleMenuDTO) o;
        if (sysRoleMenuDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysRoleMenuDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysRoleMenuDTO{" +
            "id=" + getId() +
            ", sysRoleId='" + getSysRoleId() + "'" +
            ", sysMenuId='" + getSysMenuId() + "'" +
            "}";
    }
}
