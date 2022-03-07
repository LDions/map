package com.ruowei.modules.sys.domain.table;

import com.ruowei.common.entity.AbstractAuditingEntity;
import com.ruowei.modules.sys.domain.enumeration.OfficeType;
import com.ruowei.modules.sys.domain.enumeration.OfficeStatusType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * 组织机构表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_office")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysOffice extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 机构编码
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "office_code", length = 100, nullable = false, unique = true)
    private String officeCode;

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
     * 机构代码
     */
    @Size(max = 100)
    @Column(name = "view_code", length = 100)
    private String viewCode;

    /**
     * 机构名称
     */
    @NotNull
    @Size(max = 200)
    @Column(name = "office_name", length = 200, nullable = false)
    private String officeName;

    /**
     * 机构全称
     */
    @NotNull
    @Size(max = 200)
    @Column(name = "full_name", length = 200, nullable = false, unique = true)
    private String fullName;

    /**
     * 机构类型
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "office_type", nullable = false)
    private OfficeType officeType;

    /**
     * 负责人
     */
    @Size(max = 100)
    @Column(name = "leader", length = 100)
    private String leader;

    /**
     * 办公电话
     */
    @Size(max = 100)
    @Column(name = "phone", length = 100)
    private String phone;

    /**
     * 联系地址
     */
    @Size(max = 255)
    @Column(name = "address", length = 255)
    private String address;

    /**
     * 邮政编码
     */
    @Size(max = 100)
    @Column(name = "zip_code", length = 100)
    private String zipCode;

    /**
     * 电子邮箱
     */
    @Size(max = 300)
    @Column(name = "email", length = 300)
    private String email;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    /**
     * 状态
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OfficeStatusType status;


    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getOfficeCode() {
        return officeCode;
    }

    public SysOffice officeCode(String officeCode) {
        this.officeCode = officeCode;
        return this;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public SysOffice parentCode(String parentCode) {
        this.parentCode = parentCode;
        return this;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentCodes() {
        return parentCodes;
    }

    public SysOffice parentCodes(String parentCodes) {
        this.parentCodes = parentCodes;
        return this;
    }

    public void setParentCodes(String parentCodes) {
        this.parentCodes = parentCodes;
    }

    public Integer getTreeSort() {
        return treeSort;
    }

    public SysOffice treeSort(Integer treeSort) {
        this.treeSort = treeSort;
        return this;
    }

    public void setTreeSort(Integer treeSort) {
        this.treeSort = treeSort;
    }

    public Integer getTreeSorts() {
        return treeSorts;
    }

    public SysOffice treeSorts(Integer treeSorts) {
        this.treeSorts = treeSorts;
        return this;
    }

    public void setTreeSorts(Integer treeSorts) {
        this.treeSorts = treeSorts;
    }

    public Boolean isTreeLeaf() {
        return treeLeaf;
    }

    public SysOffice treeLeaf(Boolean treeLeaf) {
        this.treeLeaf = treeLeaf;
        return this;
    }

    public void setTreeLeaf(Boolean treeLeaf) {
        this.treeLeaf = treeLeaf;
    }

    public Integer getTreeLevel() {
        return treeLevel;
    }

    public SysOffice treeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
        return this;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getTreeNames() {
        return treeNames;
    }

    public SysOffice treeNames(String treeNames) {
        this.treeNames = treeNames;
        return this;
    }

    public void setTreeNames(String treeNames) {
        this.treeNames = treeNames;
    }

    public String getViewCode() {
        return viewCode;
    }

    public SysOffice viewCode(String viewCode) {
        this.viewCode = viewCode;
        return this;
    }

    public void setViewCode(String viewCode) {
        this.viewCode = viewCode;
    }

    public String getOfficeName() {
        return officeName;
    }

    public SysOffice officeName(String officeName) {
        this.officeName = officeName;
        return this;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getFullName() {
        return fullName;
    }

    public SysOffice fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public OfficeType getOfficeType() {
        return officeType;
    }

    public SysOffice officeType(OfficeType officeType) {
        this.officeType = officeType;
        return this;
    }

    public void setOfficeType(OfficeType officeType) {
        this.officeType = officeType;
    }

    public String getLeader() {
        return leader;
    }

    public SysOffice leader(String leader) {
        this.leader = leader;
        return this;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPhone() {
        return phone;
    }

    public SysOffice phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public SysOffice address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public SysOffice zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public SysOffice email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemarks() {
        return remarks;
    }

    public SysOffice remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public OfficeStatusType getStatus() {
        return status;
    }

    public SysOffice status(OfficeStatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(OfficeStatusType status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysOffice sysOffice = (SysOffice) o;
        return  Objects.equals(id, sysOffice.id) &&
            Objects.equals(officeCode, sysOffice.officeCode) &&
            Objects.equals(parentCode, sysOffice.parentCode) &&
            Objects.equals(parentCodes, sysOffice.parentCodes) &&
            Objects.equals(treeSort, sysOffice.treeSort) &&
            Objects.equals(treeSorts, sysOffice.treeSorts) &&
            Objects.equals(treeLeaf, sysOffice.treeLeaf) &&
            Objects.equals(treeLevel, sysOffice.treeLevel) &&
            Objects.equals(treeNames, sysOffice.treeNames) &&
            Objects.equals(viewCode, sysOffice.viewCode) &&
            Objects.equals(officeName, sysOffice.officeName) &&
            Objects.equals(fullName, sysOffice.fullName) &&
            officeType == sysOffice.officeType &&
            Objects.equals(leader, sysOffice.leader) &&
            Objects.equals(phone, sysOffice.phone) &&
            Objects.equals(address, sysOffice.address) &&
            Objects.equals(zipCode, sysOffice.zipCode) &&
            Objects.equals(email, sysOffice.email) &&
            Objects.equals(remarks, sysOffice.remarks) &&
            status == sysOffice.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeCode, parentCode, parentCodes, treeSort, treeSorts, treeLeaf, treeLevel, treeNames, viewCode, officeName, fullName, officeType, leader, phone, address, zipCode, email, remarks, status);
    }
}
