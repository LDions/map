package com.ruowei.modules.sys.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ruowei.common.entity.AbstractAuditingEntity;
import com.ruowei.modules.sys.domain.enumeration.EmployeeStatusType;
import com.ruowei.modules.sys.domain.table.SysCompany;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.domain.table.SysPost;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 * 员工信息
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_employee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysEmployee extends AbstractAuditingEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 员工编码
     */
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
     * 备注信息
     * sys_user、sys_employee都有备注信息，这里默认取sys_employee的
     */
    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    /**
     * 状态
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EmployeeStatusType status;

    /**
     * 用户
     */
    @OneToOne(mappedBy = "employee",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("employee")
    private SysUser user;

    /**
     * 机构
     */
    @ManyToOne
    @JoinColumn(name="sys_office_id")
    private SysOffice office;

    /**
     * 机构名称
     */
    @Size(max = 100)
    @Column(name = "office_name", length = 100)
    private String officeName;

    /**
     * 公司
     */
    @ManyToOne
    @JoinColumn(name="sys_company_id")
    private SysCompany company;

    /**
     * 公司名称
     */
    @Size(max = 100)
    @Column(name = "company_name", length = 100)
    private String companyName;

    /**
     * 所在岗位
     */
    @ManyToMany(fetch= FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name="sys_employee_post",joinColumns={@JoinColumn(name="sys_employee_id", referencedColumnName="id")}
        ,inverseJoinColumns={@JoinColumn(name="sys_post_id", referencedColumnName="id")})
    private List<SysPost> postList;

    /**
     *  附属机构及岗位
     */
    @OneToMany(fetch= FetchType.EAGER,mappedBy="employee",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("employee")
    private List<SysEmployeeOfficePost> officePostList;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNameEn() {
        return empNameEn;
    }

    public void setEmpNameEn(String empNameEn) {
        this.empNameEn = empNameEn;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public EmployeeStatusType getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatusType status) {
        this.status = status;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }


    public List<SysEmployeeOfficePost> getOfficePostList() {
        return officePostList;
    }

    public void setOfficePostList(List<SysEmployeeOfficePost> officePostList) {
        this.officePostList = officePostList;
    }

    public SysOffice getOffice() {
        return office;
    }

    public void setOffice(SysOffice office) {
        this.office = office;
    }

    public SysCompany getCompany() {
        return company;
    }

    public void setCompany(SysCompany company) {
        this.company = company;
    }

    public List<SysPost> getPostList() {
        return postList;
    }

    public void setPostList(List<SysPost> postList) {
        this.postList = postList;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysEmployee that = (SysEmployee) o;
        return Objects.equals(empCode, that.empCode) &&
            Objects.equals(empName, that.empName) &&
            Objects.equals(empNameEn, that.empNameEn) &&
            Objects.equals(remarks, that.remarks) &&
            status == that.status &&
            Objects.equals(user, that.user) &&
            Objects.equals(office, that.office) &&
            Objects.equals(officeName, that.officeName) &&
            Objects.equals(company, that.company) &&
            Objects.equals(companyName, that.companyName) &&
            Objects.equals(postList, that.postList) &&
            Objects.equals(officePostList, that.officePostList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empCode, empName, empNameEn, remarks, status, user, office, officeName, company, companyName, postList, officePostList);
    }

    @Override
    public String toString() {
         return "SysEmployee{" +
            "empCode='" + empCode + '\'' +
            ", empName='" + empName + '\'' +
            ", empNameEn='" + empNameEn + '\'' +
            ", remarks='" + remarks + '\'' +
            ", status=" + status +
            ", officeName='" + officeName + '\'' +
            ", companyName='" + companyName + '\'' +
            ", id=" + id +
            '}';
    }
}
