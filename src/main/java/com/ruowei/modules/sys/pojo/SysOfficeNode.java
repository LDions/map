package com.ruowei.modules.sys.pojo;

import com.ruowei.modules.sys.domain.table.SysOffice;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SysOfficeNode {

    @ApiModelProperty(value = "机构信息")
    private SysOffice sysOffice;

    @ApiModelProperty(value = "下属结构信息")
    private List<SysOfficeNode> children;

    public SysOffice getSysOffice() {
        return sysOffice;
    }

    public void setSysOffice(SysOffice sysOffice) {
        this.sysOffice = sysOffice;
    }

    public List<SysOfficeNode> getChildren() {
        return children;
    }

    public void setChildren(List<SysOfficeNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SysOfficeNode{" +
            "sysOffice=" + sysOffice +
            ", children=" + children +
            '}';
    }
}
