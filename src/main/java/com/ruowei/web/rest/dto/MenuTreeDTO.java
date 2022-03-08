package com.ruowei.web.rest.dto;

import com.ruowei.domain.enumeration.MenuStatusType;
import com.ruowei.domain.enumeration.MenuType;
import com.ruowei.domain.enumeration.SysType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

public class MenuTreeDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "菜单编码")
    private String menuCode;

    @ApiModelProperty(value = "父级ID")
    private Long parentId;

    @ApiModelProperty(value = "所有父级ID")
    private String parentIds;

    @ApiModelProperty(value = "本级排序号（升序）")
    private Integer treeSort;

    @ApiModelProperty(value = "是否最末级")
    private Boolean treeLeaf;

    @ApiModelProperty(value = "层次级别")
    private Integer treeLevel;

    @ApiModelProperty(value = "全节点名")
    private String treeNames;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单类型")
    private MenuType menuType;

    @ApiModelProperty(value = "链接")
    private String menuHref;

    @ApiModelProperty(value = "图标")
    private String menuIcon;

    @ApiModelProperty(value = "菜单标题")
    private String menuTitle;

    @ApiModelProperty(value = "菜单排序（升序）")
    private Integer menuSort;

    @ApiModelProperty(value = "是否显示")
    private Boolean isShow;

    @ApiModelProperty(value = "归属系统")
    private SysType sysCode;

    @ApiModelProperty(value = "状态")
    private MenuStatusType status;

    @ApiModelProperty(value = "子节点")
    private List<MenuTreeDTO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Integer getTreeSort() {
        return treeSort;
    }

    public void setTreeSort(Integer treeSort) {
        this.treeSort = treeSort;
    }

    public Boolean getTreeLeaf() {
        return treeLeaf;
    }

    public void setTreeLeaf(Boolean treeLeaf) {
        this.treeLeaf = treeLeaf;
    }

    public Integer getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getTreeNames() {
        return treeNames;
    }

    public void setTreeNames(String treeNames) {
        this.treeNames = treeNames;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuType menuType) {
        this.menuType = menuType;
    }

    public String getMenuHref() {
        return menuHref;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean show) {
        isShow = show;
    }

    public SysType getSysCode() {
        return sysCode;
    }

    public void setSysCode(SysType sysCode) {
        this.sysCode = sysCode;
    }

    public MenuStatusType getStatus() {
        return status;
    }

    public void setStatus(MenuStatusType status) {
        this.status = status;
    }

    public List<MenuTreeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTreeDTO> children) {
        this.children = children;
    }
}
