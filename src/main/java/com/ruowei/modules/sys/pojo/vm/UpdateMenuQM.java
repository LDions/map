package com.ruowei.modules.sys.pojo.vm;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class UpdateMenuQM {

    @ApiModelProperty(value = "角色ID")
    private String roleId;

    @ApiModelProperty(value = "菜单权限ID集合")
    private List<String> menuIds;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
    }

    @Override
    public String toString() {
        return "UpdateMenuQM{" +
            "roleId=" + roleId +
            ", menuIds=" + menuIds +
            '}';
    }
}
