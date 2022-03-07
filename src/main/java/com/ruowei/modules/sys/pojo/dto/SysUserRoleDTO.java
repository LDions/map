package com.ruowei.modules.sys.pojo.dto;

import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.modules.sys.domain.SysUserRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link SysUserRole} entity.
 */
@ApiModel(description = "用户与角色关联表 @author 刘东奇")
public class SysUserRoleDTO extends BaseDTO implements Serializable {

    /**
     * 用户外键
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "用户外键", required = true)
    private String sysUserId;

    /**
     * 角色外键
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "角色外键", required = true)
    private String sysRoleId;

    private SysRoleDTO sysRoleDTO;

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public SysRoleDTO getSysRoleDTO() {
        return sysRoleDTO;
    }

    public void setSysRoleDTO(SysRoleDTO sysRoleDTO) {
        this.sysRoleDTO = sysRoleDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysUserRoleDTO sysUserRoleDTO = (SysUserRoleDTO) o;
        if (sysUserRoleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysUserRoleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysUserRoleDTO{" +
            "id=" + getId() +
            ", sysUserId='" + getSysUserId() + "'" +
            ", sysRoleId='" + getSysRoleId() + "'" +
            "}";
    }
}
