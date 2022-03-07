package com.ruowei.modules.sys.web.vm;

import com.ruowei.modules.sys.domain.table.SysRole;

import java.util.List;

/**
 * 角色分配
 * @author 刘东奇
 */
public class AssignRoleVM {

    private Long sysEmployeeId;
    private List<SysRole> roleList;

    public Long getSysEmployeeId() {
        return sysEmployeeId;
    }

    public void setSysEmployeeId(Long sysEmployeeId) {
        this.sysEmployeeId = sysEmployeeId;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }
}
