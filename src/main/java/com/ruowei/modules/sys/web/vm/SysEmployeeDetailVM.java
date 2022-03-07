package com.ruowei.modules.sys.web.vm;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ruowei.common.json.LongJsonDeserializer;
import com.ruowei.common.json.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 * 员工信息
 * 用于员工表单数据传递
 * @author 刘东奇
 */
@ApiModel(description = "员工信息 @author 刘东奇")
public class SysEmployeeDetailVM implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    /**
     * 员工编码
     */
    @ApiModelProperty(value = "员工编码")
    private String empCode;

    /**
     * 员工姓名
     */
    @NotNull
    @ApiModelProperty(value = "员工姓名", required = true)
    private String empName;

    /**
     * 英文名
     */
    @ApiModelProperty(value = "英文名")
    private String empNameEn;

    /**
     * 备注信息
     * sys_user、sys_employee都有备注信息，这里默认取sys_employee的
     */
    @ApiModelProperty(value = "备注信息")
    private String remarks;

    /**
     * 用户
     */
    @ApiModelProperty(value = "用户")
    private SysEmployeeUserVM userInfo;

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

    /**
     *  附属机构及岗位
     */
    @ApiModelProperty(value = "附属机构及岗位")
    private List<SysEmployeeOfficePostVM> officePostInfoList;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public SysEmployeeUserVM getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(SysEmployeeUserVM userInfo) {
        this.userInfo = userInfo;
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

    public List<SysEmployeeOfficePostVM> getOfficePostInfoList() {
        return officePostInfoList;
    }

    public void setOfficePostInfoList(List<SysEmployeeOfficePostVM> officePostInfoList) {
        this.officePostInfoList = officePostInfoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysEmployeeDetailVM that = (SysEmployeeDetailVM) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(empCode, that.empCode) &&
            Objects.equals(empName, that.empName) &&
            Objects.equals(empNameEn, that.empNameEn) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(userInfo, that.userInfo) &&
            Objects.equals(officeInfo, that.officeInfo) &&
            Objects.equals(companyInfo, that.companyInfo) &&
            Objects.equals(postInfoList, that.postInfoList) &&
            Objects.equals(officePostInfoList, that.officePostInfoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, empCode, empName, empNameEn, remarks, userInfo, officeInfo, companyInfo, postInfoList, officePostInfoList);
    }

    @Override
    public String toString() {
        return "SysEmployeeDetail{" +
            "id=" + id +
            ", empCode='" + empCode + '\'' +
            ", empName='" + empName + '\'' +
            ", empNameEn='" + empNameEn + '\'' +
            ", remarks='" + remarks + '\'' +
            ", userInfo=" + userInfo +
            ", officeInfo=" + officeInfo +
            ", companyInfo=" + companyInfo +
            ", postInfoList=" + postInfoList +
            ", officePostInfoList=" + officePostInfoList +
            '}';
    }
}
