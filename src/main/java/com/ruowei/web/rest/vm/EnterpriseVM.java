package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;

public class EnterpriseVM {


    /**
     * 水厂名id
     */
    @ApiModelProperty(value = "水厂id")
    @Column(name = "id")
    private Long id;

    /**
     * 水厂名称
     */
    @ApiModelProperty(value = "水厂名称")
    @Column(name = "name")
    private String name;

    /**
     * 数据类型
     */
    @ApiModelProperty(value = "经营详细地址经度")
    @Column(name = "enterprise_address")
    private String enterpriseLongitude;

    /**
     * 数据类型
     */
    @ApiModelProperty(value = "经营详细地址纬度")
    @Column(name = "enterprise_latitude")
    private String enterpriseLatitude;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;

    /**
     * 是否为试点水厂
     */
    @ApiModelProperty(value = "是否为试点水厂", required = true)
    @Column(name = "is_try")
    private String isTry;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "水厂联系人")
    @Column(name = "contact_name")
    private String contactName;

    /**
     * 电话
     */
    @ApiModelProperty(value = "联系人电话")
    @Column(name = "contact_phone")
    private String contactPhone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
