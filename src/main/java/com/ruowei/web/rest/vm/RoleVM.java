package com.ruowei.web.rest.vm;

import com.ruowei.domain.Role;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class RoleVM extends Role {

    @ApiModelProperty("功能菜单")
    private List<String> menuIds = new ArrayList<>();

    public List<String> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
    }
}
