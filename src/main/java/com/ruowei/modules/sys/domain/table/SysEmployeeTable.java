package com.ruowei.modules.sys.domain.table;

import com.ruowei.common.entity.AbstractAuditingEntity;
import com.ruowei.modules.sys.domain.enumeration.EmployeeStatusType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * 员工表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_employee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysEmployeeTable extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工编码
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "emp_code", length = 100, nullable = false, unique = true)
    private String empCode;

    /**
     * 员工姓名
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "emp_name", length = 100, nullable = false)
    private String empName;

    /**
     * 英文名
     */
    @Size(max = 1000)
    @Column(name = "emp_name_en", length = 1000)
    private String empNameEn;

    /**
     * 机构ID
     */
    @Column(name = "sys_office_id", length = 20)
    private Long sysOfficeId;

    /**
     * 机构名称
     */
    @Size(max = 100)
    @Column(name = "office_name", length = 100)
    private String officeName;

    /**
     * 公司ID
     */
    @Column(name = "sys_company_id")
    private Long sysCompanyId;

    /**
     * 公司名称
     */
    @Size(max = 100)
    @Column(name = "company_name", length = 100)
    private String companyName;

    /**
     * 状态
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EmployeeStatusType status;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getEmpCode() {
        return empCode;
    }

    public SysEmployeeTable empCode(String empCode) {
        this.empCode = empCode;
        return this;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public SysEmployeeTable empName(String empName) {
        this.empName = empName;
        return this;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNameEn() {
        return empNameEn;
    }

    public SysEmployeeTable empNameEn(String empNameEn) {
        this.empNameEn = empNameEn;
        return this;
    }

    public void setEmpNameEn(String empNameEn) {
        this.empNameEn = empNameEn;
    }

    public Long getSysOfficeId() {
        return sysOfficeId;
    }

    public SysEmployeeTable sysOfficeId(Long sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
        return this;
    }

    public void setSysOfficeId(Long sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public SysEmployeeTable officeName(String officeName) {
        this.officeName = officeName;
        return this;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Long getSysCompanyId() {
        return sysCompanyId;
    }

    public SysEmployeeTable sysCompanyId(Long sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
        return this;
    }

    public void setSysCompanyId(Long sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public SysEmployeeTable companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public EmployeeStatusType getStatus() {
        return status;
    }

    public SysEmployeeTable status(EmployeeStatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(EmployeeStatusType status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public SysEmployeeTable remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysEmployeeTable that = (SysEmployeeTable) o;
        return Objects.equals(empCode, that.empCode) &&
            Objects.equals(empName, that.empName) &&
            Objects.equals(empNameEn, that.empNameEn) &&
            Objects.equals(sysOfficeId, that.sysOfficeId) &&
            Objects.equals(officeName, that.officeName) &&
            Objects.equals(sysCompanyId, that.sysCompanyId) &&
            Objects.equals(companyName, that.companyName) &&
            status == that.status &&
            Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empCode, empName, empNameEn, sysOfficeId, officeName, sysCompanyId, companyName, status, remarks);
    }

    @Override
    public String toString() {
        return "SysEmployee{" +
            "empCode='" + empCode + '\'' +
            ", empName='" + empName + '\'' +
            ", empNameEn='" + empNameEn + '\'' +
            ", sysOfficeId=" + sysOfficeId +
            ", officeName='" + officeName + '\'' +
            ", sysCompanyId=" + sysCompanyId +
            ", companyName='" + companyName + '\'' +
            ", status=" + status +
            ", remarks='" + remarks + '\'' +
            ", id=" + id +
            '}';
    }
}
