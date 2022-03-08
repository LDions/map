package com.ruowei.domain;

import com.ruowei.domain.enumeration.MenuStatusType;
import com.ruowei.domain.enumeration.MenuType;
import com.ruowei.domain.enumeration.SysType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 菜单表
 */
@ApiModel(description = "菜单表")
@Entity
@Table(name = "sys_menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 菜单编码
     */
    @NotNull
    @ApiModelProperty(value = "菜单编码", required = true)
    @Column(name = "menu_code", nullable = false, unique = true)
    private String menuCode;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 所有父级ID
     */
    @ApiModelProperty(value = "所有父级ID")
    @Column(name = "parent_ids")
    private String parentIds;

    /**
     * 本级排序号（升序）
     */
    @ApiModelProperty(value = "本级排序号（升序）")
    @Column(name = "tree_sort")
    private Integer treeSort;

    /**
     * 是否最末级
     */
    @NotNull
    @ApiModelProperty(value = "是否最末级", required = true)
    @Column(name = "tree_leaf", nullable = false)
    private Boolean treeLeaf;

    /**
     * 层次级别
     */
    @NotNull
    @ApiModelProperty(value = "层次级别", required = true)
    @Column(name = "tree_level", nullable = false)
    private Integer treeLevel;

    /**
     * 全节点名
     */
    @ApiModelProperty(value = "全节点名")
    @Column(name = "tree_names")
    private String treeNames;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单类型
     */
    @NotNull
    @ApiModelProperty(value = "菜单类型", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "menu_type", nullable = false)
    private MenuType menuType;

    /**
     * 链接
     */
    @ApiModelProperty(value = "链接")
    @Column(name = "menu_href")
    private String menuHref;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    @Column(name = "menu_icon")
    private String menuIcon;

    /**
     * 菜单标题
     */
    @ApiModelProperty(value = "菜单标题")
    @Column(name = "menu_title")
    private String menuTitle;

    /**
     * 菜单排序（升序）
     */
    @ApiModelProperty(value = "菜单排序（升序）")
    @Column(name = "menu_sort")
    private Integer menuSort;

    /**
     * 是否显示
     */
    @NotNull
    @ApiModelProperty(value = "是否显示", required = true)
    @Column(name = "is_show", nullable = false)
    private Boolean isShow;

    /**
     * 归属系统
     */
    @NotNull
    @ApiModelProperty(value = "归属系统", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "sys_code", nullable = false)
    private SysType sysCode;

    /**
     * 状态
     */
    @NotNull
    @ApiModelProperty(value = "状态", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MenuStatusType status;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Menu id(Long id) {
        this.id = id;
        return this;
    }

    public String getMenuCode() {
        return this.menuCode;
    }

    public Menu menuCode(String menuCode) {
        this.menuCode = menuCode;
        return this;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public Menu parentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return this.parentIds;
    }

    public Menu parentIds(String parentIds) {
        this.parentIds = parentIds;
        return this;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Integer getTreeSort() {
        return this.treeSort;
    }

    public Menu treeSort(Integer treeSort) {
        this.treeSort = treeSort;
        return this;
    }

    public void setTreeSort(Integer treeSort) {
        this.treeSort = treeSort;
    }

    public Boolean getTreeLeaf() {
        return this.treeLeaf;
    }

    public Menu treeLeaf(Boolean treeLeaf) {
        this.treeLeaf = treeLeaf;
        return this;
    }

    public void setTreeLeaf(Boolean treeLeaf) {
        this.treeLeaf = treeLeaf;
    }

    public Integer getTreeLevel() {
        return this.treeLevel;
    }

    public Menu treeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
        return this;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getTreeNames() {
        return this.treeNames;
    }

    public Menu treeNames(String treeNames) {
        this.treeNames = treeNames;
        return this;
    }

    public void setTreeNames(String treeNames) {
        this.treeNames = treeNames;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public Menu menuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public MenuType getMenuType() {
        return this.menuType;
    }

    public Menu menuType(MenuType menuType) {
        this.menuType = menuType;
        return this;
    }

    public void setMenuType(MenuType menuType) {
        this.menuType = menuType;
    }

    public String getMenuHref() {
        return this.menuHref;
    }

    public Menu menuHref(String menuHref) {
        this.menuHref = menuHref;
        return this;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }

    public String getMenuIcon() {
        return this.menuIcon;
    }

    public Menu menuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
        return this;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuTitle() {
        return this.menuTitle;
    }

    public Menu menuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
        return this;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public Integer getMenuSort() {
        return this.menuSort;
    }

    public Menu menuSort(Integer menuSort) {
        this.menuSort = menuSort;
        return this;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public Boolean getIsShow() {
        return this.isShow;
    }

    public Menu isShow(Boolean isShow) {
        this.isShow = isShow;
        return this;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public SysType getSysCode() {
        return this.sysCode;
    }

    public Menu sysCode(SysType sysCode) {
        this.sysCode = sysCode;
        return this;
    }

    public void setSysCode(SysType sysCode) {
        this.sysCode = sysCode;
    }

    public MenuStatusType getStatus() {
        return this.status;
    }

    public Menu status(MenuStatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(MenuStatusType status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Menu)) {
            return false;
        }
        return id != null && id.equals(((Menu) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Menu{" +
            "id=" + getId() +
            ", menuCode='" + getMenuCode() + "'" +
            ", parentId=" + getParentId() +
            ", parentIds='" + getParentIds() + "'" +
            ", treeSort=" + getTreeSort() +
            ", treeLeaf='" + getTreeLeaf() + "'" +
            ", treeLevel=" + getTreeLevel() +
            ", treeNames='" + getTreeNames() + "'" +
            ", menuName='" + getMenuName() + "'" +
            ", menuType='" + getMenuType() + "'" +
            ", menuHref='" + getMenuHref() + "'" +
            ", menuIcon='" + getMenuIcon() + "'" +
            ", menuTitle='" + getMenuTitle() + "'" +
            ", menuSort=" + getMenuSort() +
            ", isShow='" + getIsShow() + "'" +
            ", sysCode='" + getSysCode() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
