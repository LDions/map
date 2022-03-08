package com.ruowei.web.rest.vm;

import com.ruowei.domain.enumeration.EnterpriseStatusType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

public class EnterpriseVM {

    @NotNull(message = "单位名称不能为空")
    @ApiModelProperty(value = "单位名称")
    private String name;

    @ApiModelProperty(value = "企业性质")
    private String nature;

    @NotNull(message = "统一信用代码不能为空")
    @ApiModelProperty(value = "统一信用代码")
    private String uniCreditCode;

    @ApiModelProperty(value = "法定代表人")
    private String legalRepresentative;

    @NotNull(message = "经营地址所在省不能为空")
    @ApiModelProperty(value = "经营地址所在省")
    private String businessProvince;

    @NotNull(message = "经营地址所在市不能为空")
    @ApiModelProperty(value = "经营地址所在市")
    private String businessCity;

    @NotNull(message = "经营地址所在区不能为空")
    @ApiModelProperty(value = "经营地址所在区")
    private String businessArea;

    @NotNull(message = "经营详细地址不能为空")
    @ApiModelProperty(value = "经营详细地址")
    private String businessAddress;

    @ApiModelProperty(value = "联系人姓名")
    private String contactName;

    @ApiModelProperty(value = "联系人手机")
    private String contactPhone;

    @ApiModelProperty(value = "备注")
    private String remark;

    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = "状态(NORMAL|DELETE)")
    private EnterpriseStatusType status;

    @ApiModelProperty(value = "企业用户信息")
    private UserVM userInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getUniCreditCode() {
        return uniCreditCode;
    }

    public void setUniCreditCode(String uniCreditCode) {
        this.uniCreditCode = uniCreditCode;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getBusinessProvince() {
        return businessProvince;
    }

    public void setBusinessProvince(String businessProvince) {
        this.businessProvince = businessProvince;
    }

    public String getBusinessCity() {
        return businessCity;
    }

    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public EnterpriseStatusType getStatus() {
        return status;
    }

    public void setStatus(EnterpriseStatusType status) {
        this.status = status;
    }

    public UserVM getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserVM userInfo) {
        this.userInfo = userInfo;
    }
}
