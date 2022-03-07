package com.ruowei.modules.sys.pojo.dto;

import com.ruowei.common.pojo.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 员工信息
 * @author 刘东奇
 * @date 2019/9/17
 */
public class SysUserEmployeeDTO extends BaseDTO implements Serializable {

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
     * 登录账号
     */
    @Size(max = 100)
    @ApiModelProperty(value = "登录账号")
    private String loginCode;

    /**
     * 用户昵称
     */
    @Size(max = 100)
    @ApiModelProperty(value = "用户昵称")
    private String userName;

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
     * 用户权重，用于排序（降序）
     */
    @ApiModelProperty(value = "用户权重")
    private Integer userSort;

    /**
     * 员工编码
     */
    @Size(max = 100)
    @ApiModelProperty(value = "员工编码")
    private String empCode;

    /**
     * 员工姓名
     */
    @Size(max = 100)
    @ApiModelProperty(value = "员工姓名")
    private String empName;

    /**
     * 所在岗位
     */
    @ApiModelProperty(value = "所在岗位")
    private List<SysPostDTO> sysPostDTOList;

    /**
     * 英文名
     */
    @Size(max = 1000)
    @ApiModelProperty(value = "英文名")
    private String empNameEn;

    /**
     * 附属机构及岗位
     */
    @ApiModelProperty(value = "所在岗位")
    private List<SysEmployeeOfficeDTO> sysEmployeeOfficeDTOList;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @ApiModelProperty(value = "备注信息")
    private String remarks;

    /**
     * 分配角色
     */
    @ApiModelProperty(value = "分配角色")
    private List<SysRoleDTO> sysRoleDTOList;

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

    public Integer getUserSort() {
        return userSort;
    }

    public void setUserSort(Integer userSort) {
        this.userSort = userSort;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public List<SysPostDTO> getSysPostDTOList() {
        return sysPostDTOList;
    }

    public void setSysPostDTOList(List<SysPostDTO> sysPostDTOList) {
        this.sysPostDTOList = sysPostDTOList;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<SysRoleDTO> getSysRoleDTOList() {
        return sysRoleDTOList;
    }

    public void setSysRoleDTOList(List<SysRoleDTO> sysRoleDTOList) {
        this.sysRoleDTOList = sysRoleDTOList;
    }

    public List<SysEmployeeOfficeDTO> getSysEmployeeOfficeDTOList() {
        return sysEmployeeOfficeDTOList;
    }

    public void setSysEmployeeOfficeDTOList(List<SysEmployeeOfficeDTO> sysEmployeeOfficeDTOList) {
        this.sysEmployeeOfficeDTOList = sysEmployeeOfficeDTOList;
    }
}
