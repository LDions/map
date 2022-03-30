package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;

public class EnterpriseVM {

    /**
     * 水厂名称
     */
    @ApiModelProperty(value = "水厂名称")
    private String name;

    /**
     * 数据类型
     */
    @ApiModelProperty(value = "经营详细地址经度")
    private String enterpriseLongitude;

    /**
     * 数据类型
     */
    @ApiModelProperty(value = "经营详细地址纬度")
    private String enterpriseLatitude;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 是否为试点水厂
     */
    @ApiModelProperty(value = "是否为试点水厂", required = true)
    private String isTry;

    /**
     * 水厂所属集团编码
     */
    @ApiModelProperty(value = "水厂所属集团code")
    private String groupCode;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "水厂联系人")
    private String contactName;

    /**
     * 电话
     */
    @ApiModelProperty(value = "联系人电话")
    private String contactPhone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnterpriseLongitude() {
        return enterpriseLongitude;
    }

    public void setEnterpriseLongitude(String enterpriseLongitude) {
        this.enterpriseLongitude = enterpriseLongitude;
    }

    public String getEnterpriseLatitude() {
        return enterpriseLatitude;
    }

    public void setEnterpriseLatitude(String enterpriseLatitude) {
        this.enterpriseLatitude = enterpriseLatitude;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsTry() {
        return isTry;
    }

    public void setIsTry(String isTry) {
        this.isTry = isTry;
    }
    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
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
}
