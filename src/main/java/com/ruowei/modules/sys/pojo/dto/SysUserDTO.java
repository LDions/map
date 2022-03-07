package com.ruowei.modules.sys.pojo.dto;

import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.modules.sys.domain.enumeration.GenderType;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.ruowei.modules.sys.domain.SysUser} entity.
 * @author 刘东奇
 */
@ApiModel(description = "用户表 @author 刘东奇")
public class SysUserDTO extends BaseDTO {

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
     * 登录密码
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "登录密码", required = true)
    private String password;

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
     * 用户性别
     */
    @ApiModelProperty(value = "用户性别")
    private GenderType sex;

    /**
     * 头像相对路径
     */
    @Size(max = 1000)
    @ApiModelProperty(value = "头像相对路径")
    private String avatar;

    /**
     * 个性签名
     */
    @Size(max = 200)
    @ApiModelProperty(value = "个性签名")
    private String sign;

    /**
     * 绑定的微信号
     */
    @Size(max = 1000)
    @ApiModelProperty(value = "绑定的微信号")
    private String wxOpenid;

    /**
     * 绑定的手机MID
     */
    @Size(max = 100)
    @ApiModelProperty(value = "绑定的手机MID")
    private String mobileImei;

    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型")
    private UserType userType;

    /**
     * 用户类型引用编号（雇员表或会员表的ID）
     */
    @ApiModelProperty(value = "用户类型引用编号（雇员表或会员表的ID）")
    private Long refCode;

    /**
     * 用户类型引用姓名
     */
    @Size(max = 100)
    @ApiModelProperty(value = "用户类型引用姓名")
    private String refName;

    /**
     * 最后登录IP
     */
    @Size(max = 100)
    @ApiModelProperty(value = "最后登录IP")
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    private Instant lastLoginDate;

    /**
     * 冻结时间
     */
    @ApiModelProperty(value = "冻结时间")
    private Instant freezeDate;

    /**
     * 冻结原因
     */
    @Size(max = 200)
    @ApiModelProperty(value = "冻结原因")
    private String freezeCause;

    /**
     * 用户权重，用于排序（降序）
     */
    @ApiModelProperty(value = "用户权重，用于排序（降序）")
    private Integer userSort;

    /**
     * 用户状态
     */
    @NotNull
    @ApiModelProperty(value = "用户状态", required = true)
    private UserStatusType status;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @ApiModelProperty(value = "备注信息")
    private String remarks;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public GenderType getSex() {
        return sex;
    }

    public void setSex(GenderType sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getMobileImei() {
        return mobileImei;
    }

    public void setMobileImei(String mobileImei) {
        this.mobileImei = mobileImei;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Long getRefCode() {
        return refCode;
    }

    public void setRefCode(Long refCode) {
        this.refCode = refCode;
    }

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Instant getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Instant lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Instant getFreezeDate() {
        return freezeDate;
    }

    public void setFreezeDate(Instant freezeDate) {
        this.freezeDate = freezeDate;
    }

    public String getFreezeCause() {
        return freezeCause;
    }

    public void setFreezeCause(String freezeCause) {
        this.freezeCause = freezeCause;
    }

    public Integer getUserSort() {
        return userSort;
    }

    public void setUserSort(Integer userSort) {
        this.userSort = userSort;
    }

    public UserStatusType getStatus() {
        return status;
    }

    public void setStatus(UserStatusType status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysUserDTO sysUserDTO = (SysUserDTO) o;
        if (sysUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysUserDTO{" +
            "id=" + getId() +
            ", userCode='" + getUserCode() + "'" +
            ", loginCode='" + getLoginCode() + "'" +
            ", userName='" + getUserName() + "'" +
            ", password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", phone='" + getPhone() + "'" +
            ", sex='" + getSex() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", sign='" + getSign() + "'" +
            ", wxOpenid='" + getWxOpenid() + "'" +
            ", mobileImei='" + getMobileImei() + "'" +
            ", userType='" + getUserType() + "'" +
            ", refCode='" + getRefCode() + "'" +
            ", refName='" + getRefName() + "'" +
            ", lastLoginIp='" + getLastLoginIp() + "'" +
            ", lastLoginDate='" + getLastLoginDate() + "'" +
            ", freezeDate='" + getFreezeDate() + "'" +
            ", freezeCause='" + getFreezeCause() + "'" +
            ", userSort=" + getUserSort() +
            ", status='" + getStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
