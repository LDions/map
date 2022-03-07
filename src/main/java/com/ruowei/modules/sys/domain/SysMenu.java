package com.ruowei.modules.sys.domain;
import com.ruowei.common.entity.PrimaryKeyAutoIncrementEntity;
import com.ruowei.modules.sys.domain.enumeration.MenuStatusType;
import com.ruowei.modules.sys.domain.enumeration.MenuType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.ruowei.modules.sys.domain.enumeration.SysType;

/**
 * 菜单表
 * @author JeeSite
 */
@ApiModel(description = "菜单表 @author JeeSite")
@Entity
@Table(name = "sys_menu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysMenu extends PrimaryKeyAutoIncrementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /*@Id
    @GenericGenerator(name="idGenerator", strategy = "com.ruowei.common.idgen.IdGenerator")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGenerator")
    private Long id;*/

    /**
     * 菜单编码
     */
    @Size(max = 100)
    @ApiModelProperty(value = "菜单编码")
    @Column(name = "menu_code", length = 100)
    private String menuCode;

    /**
     * 父级编号
     */
    @ApiModelProperty(value = "父级编号")
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 所有父级编号
     */
    @Size(max = 1000)
    @ApiModelProperty(value = "所有父级编号")
    @Column(name = "parent_ids", length = 1000)
    private String parentIds;

    /**
     * 本级排序号（升序）
     */
    @ApiModelProperty(value = "本级排序号（升序）")
    @Column(name = "tree_sort")
    private Integer treeSort;

    /**
     * 所有级别排序号
     */
    @ApiModelProperty(value = "所有级别排序号")
    @Column(name = "tree_sorts")
    private Integer treeSorts;

    /**
     * 是否最末级
     */
    @ApiModelProperty(value = "是否最末级", required = true)
    @Column(name = "tree_leaf")
    private boolean treeLeaf;

    /**
     * 层次级别
     */
    @ApiModelProperty(value = "层次级别", required = true)
    @Column(name = "tree_level")
    private Integer treeLevel;

    /**
     * 全节点名
     */
    @Size(max = 1000)
    @ApiModelProperty(value = "全节点名")
    @Column(name = "tree_names", length = 1000)
    private String treeNames;

    /**
     * 菜单名称
     */
    @Size(max = 100)
    @ApiModelProperty(value = "菜单名称", required = true)
    @Column(name = "menu_name", length = 100)
    private String menuName;

    /**
     * 菜单类型
     */
    @ApiModelProperty(value = "菜单类型", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "menu_type")
    private MenuType menuType;

    /**
     * 链接
     */
    @Size(max = 1000)
    @ApiModelProperty(value = "链接")
    @Column(name = "menu_href", length = 1000)
    private String menuHref;

    /**
     * 图标
     */
    @Size(max = 100)
    @ApiModelProperty(value = "图标")
    @Column(name = "menu_icon", length = 100)
    private String menuIcon;

    /**
     * 菜单标题
     */
    @Size(max = 100)
    @ApiModelProperty(value = "菜单标题")
    @Column(name = "menu_title", length = 100)
    private String menuTitle;

    /**
     * 权限标识
     */
    @Size(max = 1000)
    @ApiModelProperty(value = "权限标识")
    @Column(name = "permission", length = 1000)
    private String permission;

    /**
     * 菜单排序（升序）
     */
    @ApiModelProperty(value = "菜单排序（升序）", required = true)
    @Column(name = "menu_sort")
    private Integer menuSort;

    /**
     * 是否显示
     */
    @ApiModelProperty(value = "是否显示", required = true)
    @Column(name = "is_show")
    private boolean isShow;

    /**
     * 归属系统（default:主导航菜单、mobileApp:APP菜单）
     */
    @ApiModelProperty(value = "归属系统（default:主导航菜单、mobileApp:APP菜单）")
    @Enumerated(EnumType.STRING)
    @Column(name = "sys_code")
    private SysType sysCode;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MenuStatusType status;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @ApiModelProperty(value = "备注信息")
    @Column(name = "remarks", length = 500)
    private String remarks;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public SysMenu menuCode(String menuCode) {
        this.menuCode = menuCode;
        return this;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public Long getParentId() {
        return parentId;
    }

    public SysMenu parentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public SysMenu parentIds(String parentIds) {
        this.parentIds = parentIds;
        return this;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Integer getTreeSort() {
        return treeSort;
    }

    public SysMenu treeSort(Integer treeSort) {
        this.treeSort = treeSort;
        return this;
    }

    public void setTreeSort(Integer treeSort) {
        this.treeSort = treeSort;
    }

    public Integer getTreeSorts() {
        return treeSorts;
    }

    public SysMenu treeSorts(Integer treeSorts) {
        this.treeSorts = treeSorts;
        return this;
    }

    public void setTreeSorts(Integer treeSorts) {
        this.treeSorts = treeSorts;
    }

    public boolean isTreeLeaf() {
        return treeLeaf;
    }

    public SysMenu treeLeaf(boolean treeLeaf) {
        this.treeLeaf = treeLeaf;
        return this;
    }

    public void setTreeLeaf(boolean treeLeaf) {
        this.treeLeaf = treeLeaf;
    }

    public Integer getTreeLevel() {
        return treeLevel;
    }

    public SysMenu treeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
        return this;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getTreeNames() {
        return treeNames;
    }

    public SysMenu treeNames(String treeNames) {
        this.treeNames = treeNames;
        return this;
    }

    public void setTreeNames(String treeNames) {
        this.treeNames = treeNames;
    }

    public String getMenuName() {
        return menuName;
    }

    public SysMenu menuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public SysMenu menuType(MenuType menuType) {
        this.menuType = menuType;
        return this;
    }

    public void setMenuType(MenuType menuType) {
        this.menuType = menuType;
    }

    public String getMenuHref() {
        return menuHref;
    }

    public SysMenu menuHref(String menuHref) {
        this.menuHref = menuHref;
        return this;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public SysMenu menuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
        return this;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public SysMenu menuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
        return this;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getPermission() {
        return permission;
    }

    public SysMenu permission(String permission) {
        this.permission = permission;
        return this;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public SysMenu menuSort(Integer menuSort) {
        this.menuSort = menuSort;
        return this;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public boolean isIsShow() {
        return isShow;
    }

    public SysMenu isShow(boolean isShow) {
        this.isShow = isShow;
        return this;
    }

    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

    public SysType getSysCode() {
        return sysCode;
    }

    public SysMenu sysCode(SysType sysCode) {
        this.sysCode = sysCode;
        return this;
    }

    public void setSysCode(SysType sysCode) {
        this.sysCode = sysCode;
    }

    public MenuStatusType getStatus() {
        return status;
    }

    public SysMenu status(MenuStatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(MenuStatusType status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public SysMenu remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysMenu)) {
            return false;
        }
        return id != null && id.equals(((SysMenu) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
            "id=" + getId() +
            ", menuCode='" + getMenuCode() + "'" +
            ", parentId=" + getParentId() +
            ", parentIds='" + getParentIds() + "'" +
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
