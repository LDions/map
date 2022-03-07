package com.ruowei.modules.sys.pojo;

import com.ruowei.modules.sys.domain.table.SysCompany;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SysCompanyNode {

    @ApiModelProperty(value = "公司信息")
    private SysCompany sysCompany;

    @ApiModelProperty(value = "下属公司信息")
    private List<SysCompanyNode> children;

    public SysCompany getSysCompany() {
        return sysCompany;
    }

    public void setSysCompany(SysCompany sysCompany) {
        this.sysCompany = sysCompany;
    }

    public List<SysCompanyNode> getChildren() {
        return children;
    }

    public void setChildren(List<SysCompanyNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SysCompanyNode{" +
            "sysCompany=" + sysCompany +
            ", children=" + children +
            '}';
    }
}
