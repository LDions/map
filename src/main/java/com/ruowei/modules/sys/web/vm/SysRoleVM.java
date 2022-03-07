package com.ruowei.modules.sys.web.vm;

import com.ruowei.modules.sys.domain.table.SysRole;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SysRoleVM extends SysRole {

    @ApiModelProperty("功能菜单")
    private List<String> sysMenuIds;

    public List<String> getSysMenuIds() {
        return sysMenuIds;
    }

    public void setSysMenuIds(List<String> sysMenuIds) {
        this.sysMenuIds = sysMenuIds;
    }
}
