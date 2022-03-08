package com.ruowei.service.dto;

import com.ruowei.web.rest.dto.MenuTreeDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SimpleMenuTreeDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "链接")
    private String menuHref;

    @ApiModelProperty(value = "子节点")
    private List<SimpleMenuTreeDTO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuHref() {
        return menuHref;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }

    public List<SimpleMenuTreeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<SimpleMenuTreeDTO> children) {
        this.children = children;
    }
}
