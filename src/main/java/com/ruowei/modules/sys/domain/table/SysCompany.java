package com.ruowei.modules.sys.domain.table;

import com.ruowei.common.entity.PrimaryKeyAutoIncrementEntity;
import com.ruowei.modules.sys.domain.enumeration.CompanyStatusType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 公司表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_company")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysCompany extends PrimaryKeyAutoIncrementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司编码
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "company_code", length = 100, nullable = false, unique = true)
    private String companyCode;

    /**
     * 父级编号
     */
    @Size(max = 100)
    @Column(name = "parent_code", length = 100)
    private String parentCode;

    /**
     * 所有父级编号
     */
    @Size(max = 1000)
    @Column(name = "parent_codes", length = 1000)
    private String parentCodes;

    /**
     * 本级排序号（升序）
     */
    @Column(name = "tree_sort")
    private Integer treeSort;

    /**
     * 所有级别排序号
     */
    @Column(name = "tree_sorts")
    private Integer treeSorts;

    /**
     * 是否最末级
     */
    @NotNull
    @Column(name = "tree_leaf", nullable = false)
    private Boolean treeLeaf;

    /**
     * 层次级别
     */
    @Column(name = "tree_level", nullable = false)
    private Integer treeLevel;

    /**
     * 全节点名
     */
    @Size(max = 1000)
    @Column(name = "tree_names", length = 1000, nullable = false)
    private String treeNames;

    /**
     * 公司代码
     */
    @Size(max = 100)
    @Column(name = "view_code", length = 100)
    private String viewCode;

    /**
     * 公司名称
     */
    @NotNull
    @Size(max = 200)
    @Column(name = "company_name", length = 200, nullable = false)
    private String companyName;

    /**
     * 公司全称
     */
    @NotNull
    @Size(max = 200)
    @Column(name = "full_name", length = 200, nullable = false, unique = true)
    private String fullName;

    /**
     * 区域编码
     */
    @Size(max = 100)
    @Column(name = "area_code", length = 100)
    private String areaCode;

    /**
     * 状态
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CompanyStatusType status;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getCompanyCode() {
        return companyCode;
    }

    public SysCompany companyCode(String companyCode) {
        this.companyCode = companyCode;
        return this;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public SysCompany parentCode(String parentCode) {
        this.parentCode = parentCode;
        return this;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentCodes() {
        return parentCodes;
    }

    public SysCompany parentCodes(String parentCodes) {
        this.parentCodes = parentCodes;
        return this;
    }

    public void setParentCodes(String parentCodes) {
        this.parentCodes = parentCodes;
    }

    public Integer getTreeSort() {
        return treeSort;
    }

    public SysCompany treeSort(Integer treeSort) {
        this.treeSort = treeSort;
        return this;
    }

    public void setTreeSort(Integer treeSort) {
        this.treeSort = treeSort;
    }

    public Integer getTreeSorts() {
        return treeSorts;
    }

    public SysCompany treeSorts(Integer treeSorts) {
        this.treeSorts = treeSorts;
        return this;
    }

    public void setTreeSorts(Integer treeSorts) {
        this.treeSorts = treeSorts;
    }

    public Boolean isTreeLeaf() {
        return treeLeaf;
    }

    public SysCompany treeLeaf(Boolean treeLeaf) {
        this.treeLeaf = treeLeaf;
        return this;
    }

    public void setTreeLeaf(Boolean treeLeaf) {
        this.treeLeaf = treeLeaf;
    }

    public Integer getTreeLevel() {
        return treeLevel;
    }

    public SysCompany treeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
        return this;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getTreeNames() {
        return treeNames;
    }

    public SysCompany treeNames(String treeNames) {
        this.treeNames = treeNames;
        return this;
    }

    public void setTreeNames(String treeNames) {
        this.treeNames = treeNames;
    }

    public String getViewCode() {
        return viewCode;
    }

    public SysCompany viewCode(String viewCode) {
        this.viewCode = viewCode;
        return this;
    }

    public void setViewCode(String viewCode) {
        this.viewCode = viewCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public SysCompany companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFullName() {
        return fullName;
    }

    public SysCompany fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public SysCompany areaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public CompanyStatusType getStatus() {
        return status;
    }

    public SysCompany status(CompanyStatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(CompanyStatusType status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public SysCompany remarks(String remarks) {
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
        if (!(o instanceof SysCompany)) {
            return false;
        }
        return id != null && id.equals(((SysCompany) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysCompany{" +
            "id=" + getId() +
            ", companyCode='" + getCompanyCode() + "'" +
            ", parentCode='" + getParentCode() + "'" +
            ", parentCodes='" + getParentCodes() + "'" +
            ", treeSort=" + getTreeSort() +
            ", treeSorts=" + getTreeSorts() +
            ", treeLeaf='" + isTreeLeaf() + "'" +
            ", treeLevel=" + getTreeLevel() +
            ", treeNames='" + getTreeNames() + "'" +
            ", viewCode='" + getViewCode() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", areaCode='" + getAreaCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
