package com.ruowei.modules.sys.pojo.dto;

import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.modules.sys.domain.SysMenu;
import com.ruowei.modules.sys.domain.enumeration.MenuStatusType;
import com.ruowei.modules.sys.domain.enumeration.MenuType;
import com.ruowei.modules.sys.domain.enumeration.SysType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link SysMenu} entity.
 */
@ApiModel(description = "菜单表 @author 刘东奇")
public class SysMenuDTO extends BaseDTO implements Serializable {

    private Long id;

    /**
     * 菜单编码
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "菜单编码", required = true)
    private String menuCode;

    /**
     * 父级编号
     */
    @Size(max = 100)
    @ApiModelProperty(value = "父级编号")
    private String parentCode;

    /**
     * 所有父级编号
     */
    @Size(max = 1000)
    @ApiModelProperty(value = "所有父级编号")
    private String parentCodes;

    /**
     * 本级排序号（升序）
     */
    @ApiModelProperty(value = "本级排序号（升序）")
    private Integer treeSort;

    /**
     * 所有级别排序号
     */
    @ApiModelProperty(value = "所有级别排序号")
    private Integer treeSorts;

    /**
     * 是否最末级
     */
    @NotNull
    @ApiModelProperty(value = "是否最末级", required = true)
    private Boolean treeLeaf;

    /**
     * 层次级别
     */
    @NotNull
    @ApiModelProperty(value = "层次级别", required = true)
    private Integer treeLevel;

    /**
     * 全节点名
     */
    @NotNull
    @Size(max = 1000)
    @ApiModelProperty(value = "全节点名", required = true)
    private String treeNames;

    /**
     * 菜单名称
     */
    @Size(max = 100)
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    /**
     * 菜单类型
     */
    @NotNull
    @ApiModelProperty(value = "菜单类型", required = true)
    private MenuType menuType;

    /**
     * 链接
     */
    @Size(max = 1000)
    @ApiModelProperty(value = "链接")
    private String menuHref;

    /**
     * 图标
     */
    @Size(max = 100)
    @ApiModelProperty(value = "图标")
    private String menuIcon;

    /**
     * 菜单标题
     */
    @Size(max = 100)
    @ApiModelProperty(value = "菜单标题")
    private String menuTitle;

    /**
     * 权限标识
     */
    @Size(max = 1000)
    @ApiModelProperty(value = "权限标识")
    private String permission;

    /**
     * 菜单排序（升序）
     */
    @ApiModelProperty(value = "菜单排序（升序）")
    private Integer menuSort;

    /**
     * 是否显示
     */
    @NotNull
    @ApiModelProperty(value = "是否显示", required = true)
    private Boolean isShow;

    /**
     * 归属系统（default:主导航菜单、mobileApp:APP菜单）
     */
    @NotNull
    @ApiModelProperty(value = "归属系统（default:主导航菜单、mobileApp:APP菜单）", required = true)
    private SysType sysCode;

    /**
     * 状态
     */
    @NotNull
    @ApiModelProperty(value = "状态", required = true)
    private MenuStatusType status;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @ApiModelProperty(value = "备注信息")
    private String remarks;


    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentCodes() {
        return parentCodes;
    }

    public void setParentCodes(String parentCodes) {
        this.parentCodes = parentCodes;
    }

    public Integer getTreeSort() {
        return treeSort;
    }

    public void setTreeSort(Integer treeSort) {
        this.treeSort = treeSort;
    }

    public Integer getTreeSorts() {
        return treeSorts;
    }

    public void setTreeSorts(Integer treeSorts) {
        this.treeSorts = treeSorts;
    }

    public Boolean isTreeLeaf() {
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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public Boolean isIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysMenuDTO sysMenuDTO = (SysMenuDTO) o;
        if (sysMenuDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysMenuDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysMenuDTO{" +
            "id=" + getId() +
            ", menuCode='" + getMenuCode() + "'" +
            ", parentCode='" + getParentCode() + "'" +
            ", parentCodes='" + getParentCodes() + "'" +
            ", treeSort=" + getTreeSort() +
            ", treeSorts=" + getTreeSorts() +
            ", treeLeaf='" + isTreeLeaf() + "'" +
            ", treeLevel=" + getTreeLevel() +
            ", treeNames='" + getTreeNames() + "'" +
            ", menuName='" + getMenuName() + "'" +
            ", menuType='" + getMenuType() + "'" +
            ", menuHref='" + getMenuHref() + "'" +
            ", menuIcon='" + getMenuIcon() + "'" +
            ", menuTitle='" + getMenuTitle() + "'" +
            ", permission='" + getPermission() + "'" +
            ", menuSort=" + getMenuSort() +
            ", isShow='" + isIsShow() + "'" +
            ", sysCode='" + getSysCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
