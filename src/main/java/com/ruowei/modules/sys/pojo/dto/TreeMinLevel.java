package com.ruowei.modules.sys.pojo.dto;

import io.swagger.annotations.ApiModelProperty;

public class TreeMinLevel {

    @ApiModelProperty(value = "树")
    private String treeName;

    @ApiModelProperty(value = "层")
    private Integer treeLevel;

    public TreeMinLevel(String treeName, Integer treeLevel) {
        this.treeName = treeName;
        this.treeLevel = treeLevel;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public Integer getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }
}
