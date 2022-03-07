package com.ruowei.modules.sys.pojo.vm;

import java.util.List;

/**
 * 角色分配
 * @author 刘东奇
 */
public class AssignRoleVM {
    private Long sysEmployeeId;
    private List<Long> roleIdList;

    public Long getSysEmployeeId() {
        return sysEmployeeId;
    }

    public void setSysEmployeeId(Long sysEmployeeId) {
        this.sysEmployeeId = sysEmployeeId;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
