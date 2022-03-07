package com.ruowei.modules.sys.web.vm;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ruowei.common.json.LongJsonDeserializer;
import com.ruowei.common.json.LongJsonSerializer;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 * 员工的用户信息
 * @author 刘东奇
 */
@ApiModel(description = "员工的用户信息 @author 刘东奇")
public class SysEmployeeUserVM implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

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
     * 电子邮箱
     */
    @Email
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
    @ApiModelProperty(value = "用户权重，用于排序（降序）")
    private Integer userSort;

    /**
     * 用户角色
     */
    @ApiModelProperty(value = "用户角色")
    private List<SysUserRoleVM> roleList;

    /**
     * 用户状态
     */
    @ApiModelProperty(value = "用户状态")
    private UserStatusType status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getLoginCode() {
        return loginCode;
    }

    public SysEmployeeUserVM loginCode(String loginCode) {
        this.loginCode = loginCode;
        return this;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getUserName() {
        return userName;
    }

    public SysEmployeeUserVM userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public SysEmployeeUserVM email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public SysEmployeeUserVM mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public SysEmployeeUserVM phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserSort() {
        return userSort;
    }

    public SysEmployeeUserVM userSort(Integer userSort) {
        this.userSort = userSort;
        return this;
    }

    public void setUserSort(Integer userSort) {
        this.userSort = userSort;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SysUserRoleVM> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysUserRoleVM> roleList) {
        this.roleList = roleList;
    }

    public UserStatusType getStatus() {
        return status;
    }

    public void setStatus(UserStatusType status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysEmployeeUserVM that = (SysEmployeeUserVM) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(loginCode, that.loginCode) &&
            Objects.equals(userName, that.userName) &&
            Objects.equals(email, that.email) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(userSort, that.userSort) &&
            Objects.equals(roleList, that.roleList) &&
            status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loginCode, userName, email, mobile, phone, userSort, roleList, status);
    }

    @Override
    public String toString() {
        return "SysEmployeeUserVM{" +
            "id=" + id +
            ", loginCode='" + loginCode + '\'' +
            ", userName='" + userName + '\'' +
            ", email='" + email + '\'' +
            ", mobile='" + mobile + '\'' +
            ", phone='" + phone + '\'' +
            ", userSort=" + userSort +
            ", roleList=" + roleList +
            ", status=" + status +
            '}';
    }
}
