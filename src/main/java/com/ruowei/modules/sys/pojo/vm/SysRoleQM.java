package com.ruowei.modules.sys.pojo.vm;

import com.ruowei.modules.sys.domain.enumeration.RoleStatusType;
import com.ruowei.modules.sys.domain.enumeration.RoleType;
import io.swagger.annotations.ApiModelProperty;

public class SysRoleQM {

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    @ApiModelProperty(value = "用户类型")
    private RoleType roleType;

    @ApiModelProperty(value = "是否系统")
    private Boolean isSys;

    @ApiModelProperty(value = "状态")
    private RoleStatusType roleStatusType;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Boolean getSys() {
        return isSys;
    }

    public void setSys(Boolean sys) {
        isSys = sys;
    }

    public RoleStatusType getRoleStatusType() {
        return roleStatusType;
    }

    public void setRoleStatusType(RoleStatusType roleStatusType) {
        this.roleStatusType = roleStatusType;
    }

    @Override
    public String toString() {
        return "SysRoleQM{" +
            "roleName='" + roleName + '\'' +
            ", roleCode='" + roleCode + '\'' +
            ", roleType=" + roleType +
            ", isSys=" + isSys +
            ", roleStatusType=" + roleStatusType +
            '}';
    }
}
