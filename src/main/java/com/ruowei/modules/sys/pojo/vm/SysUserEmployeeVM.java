package com.ruowei.modules.sys.pojo.vm;

import com.ruowei.common.pojo.BaseView;
import com.ruowei.modules.sys.domain.enumeration.EmployeeStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SysUserEmployeeVM extends BaseView {

    /**
     * 用户编码 ，该字段不作为表的关联外键，仅供展示
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "用户编码 ，该字段不作为表的关联外键，仅供展示", required = true)
    private String userCode;

    /**
     * 登录账号
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "登录账号", required = true)
    private String loginCode;

    /**
     * 用户昵称
     */
    @Size(max = 100)
    @ApiModelProperty(value = "用户昵称")
    private String userName;

    /**
     * 员工姓名
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "员工姓名", required = true)
    private String empName;

    /**
     * 英文名
     */
    @Size(max = 1000)
    @ApiModelProperty(value = "英文名")
    private String empNameEn;

    /**
     * 归属
     * 机构ID
     */
    @ApiModelProperty(value = "机构ID")
    private Long sysOfficeId;

    /**
     * 归属
     * 机构名称
     */
    @Size(max = 100)
    @ApiModelProperty(value = "机构名称")
    private String officeName;

    /**
     * 归属
     * 公司ID
     */
    @ApiModelProperty(value = "公司ID")
    private Long sysCompanyId;

    /**
     * 归属
     * 公司名称
     */
    @Size(max = 100)
    @ApiModelProperty(value = "公司名称")
    private String companyName;

    /**
     * 电子邮箱
     */
    @Size(max = 300)
    @ApiModelProperty(value = "电子邮箱")
    private String email;

    /**
     * 手机号码
     */
    @Size(max = 100)
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    /**
     * 办公电话
     */
    @Size(max = 100)
    @ApiModelProperty(value = "办公电话")
    private String phone;

    /**
     * 用户状态
     */
    @NotNull
    @ApiModelProperty(value = "用户状态", required = true)
    private UserStatusType userStatus;

    /**
     * 员工状态
     */
    @NotNull
    @ApiModelProperty(value = "员工状态", required = true)
    private EmployeeStatusType empStatus;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Long getSysOfficeId() {
        return sysOfficeId;
    }

    public void setSysOfficeId(Long sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Long getSysCompanyId() {
        return sysCompanyId;
    }

    public void setSysCompanyId(Long sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserStatusType getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatusType userStatus) {
        this.userStatus = userStatus;
    }

    public EmployeeStatusType getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(EmployeeStatusType empStatus) {
        this.empStatus = empStatus;
    }
}
