package com.ruowei.modules.sys.web.vm;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ruowei.common.json.LongJsonDeserializer;
import com.ruowei.common.json.LongJsonSerializer;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;


/**
 * 用户员工实体-列表模型
 * sys_employee作为主表、sys_user作为从表，通过sys_user.ref_code进行关联
 * @author 刘东奇
 */
@ApiModel(description = "用户员工实体-列表模型 @author 刘东奇")
public class SysEmployeeListVM implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    /**
     * 用户
     */
    @ApiModelProperty(value = "用户")
    private SysEmployeeUserVM userInfo;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Instant lastModifiedDate;

    /**
     * 员工姓名
     */
    @ApiModelProperty(value = "员工姓名")
    private String empName;

    /**
     * 机构
     */
    @ApiModelProperty(value = "机构")
    private SysEmployeeOfficeVM officeInfo;

    /**
     * 公司
     */
    @ApiModelProperty(value = "公司")
    private SysEmployeeCompanyVM companyInfo;

    /**
     * 所在岗位
     */
    @ApiModelProperty(value = "所在岗位")
    private List<SysEmployeePostVM> postInfoList;

    private UserStatusType userStatusType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public SysEmployeeUserVM getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(SysEmployeeUserVM userInfo) {
        this.userInfo = userInfo;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public SysEmployeeOfficeVM getOfficeInfo() {
        return officeInfo;
    }

    public void setOfficeInfo(SysEmployeeOfficeVM officeInfo) {
        this.officeInfo = officeInfo;
    }

    public SysEmployeeCompanyVM getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(SysEmployeeCompanyVM companyInfo) {
        this.companyInfo = companyInfo;
    }

    public List<SysEmployeePostVM> getPostInfoList() {
        return postInfoList;
    }

    public void setPostInfoList(List<SysEmployeePostVM> postInfoList) {
        this.postInfoList = postInfoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysEmployeeListVM that = (SysEmployeeListVM) o;
        return Objects.equals(userInfo, that.userInfo) &&
            Objects.equals(lastModifiedDate, that.lastModifiedDate) &&
            Objects.equals(empName, that.empName) &&
            Objects.equals(officeInfo, that.officeInfo) &&
            Objects.equals(companyInfo, that.companyInfo) &&
            Objects.equals(postInfoList, that.postInfoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userInfo, lastModifiedDate, empName, officeInfo, companyInfo, postInfoList);
    }

    @Override
    public String toString() {
        return "SysEmployeeList{" +
            "userInfo=" + userInfo +
            ", lastModifiedDate=" + lastModifiedDate +
            ", empName='" + empName + '\'' +
            ", officeInfo=" + officeInfo +
            ", companyInfo=" + companyInfo +
            ", postInfoList=" + postInfoList +
            ", id=" + id +
            '}';
    }
}
