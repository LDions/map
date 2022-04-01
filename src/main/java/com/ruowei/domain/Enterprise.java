package com.ruowei.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 水厂信息
 */
@ApiModel(description = "水厂信息")
@Entity
@Table(name = "sys_enterprise")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Enterprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 水厂名称
     */
    @ApiModelProperty(value = "水厂名称")
    @Column(name = "name")
    private String name;

    /**
     * 水厂编码
     */
    @ApiModelProperty(value = "水厂编码")
    @Column(name = "code")
    private String code;

    /**
     * 水厂地址经度
     */
    @ApiModelProperty(value = "水厂地址经度")
    @Column(name = "enterprise_longitude")
    private String enterpriseLongitude;

    /**
     * 水厂地址纬度
     */
    @ApiModelProperty(value = "水厂地址纬度")
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
    @ApiModelProperty(value = "是否为试点水厂",required = true)
    @Column(name = "is_try")
    private Boolean isTry;

    /**
     * 水厂所属集团Code
     */
    @ApiModelProperty(value = "水厂所属集团Code")
    @Column(name = "group_code")
    private String groupCode;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "集团联系人")
    @Column(name = "contact_name")
    private String contactName;

    /**
     * 电话
     */
    @ApiModelProperty(value = "联系人电话")
    @Column(name = "contact_phone")
    private String contactPhone;


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Enterprise id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Enterprise name(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Enterprise code(String code) {
        this.code = code;
        return this;
    }

    public String getEnterpriseLongitude() {
        return enterpriseLongitude;
    }

    public void setEnterpriseLongitude(String enterpriseLongitude) {
        this.enterpriseLongitude = enterpriseLongitude;
    }
    public Enterprise enterpriseLongitude(String enterpriseLongitude) {
        this.enterpriseLongitude = enterpriseLongitude;
        return this;
    }

    public String getEnterpriseLatitude() {
        return enterpriseLatitude;
    }

    public void setEnterpriseLatitude(String enterpriseLatitude) {
        this.enterpriseLatitude = enterpriseLatitude;
    }
    public Enterprise enterpriseLatitude(String enterpriseLatitude) {
        this.enterpriseLatitude = enterpriseLatitude;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Enterprise remark(String remark) {
        this.remark = remark;
        return this;
    }

    public Boolean getIsTry() {
        return isTry;
    }

    public void setIsTry(Boolean isTry) {
        this.isTry = isTry;
    }
    public Enterprise isTry(Boolean isTry) {
        this.isTry = isTry;
        return this;
    }

    public String getGroupCode() {
        return groupCode;
    }
    public Enterprise groupCode(String groupCode) {
        this.groupCode = groupCode;
        return this;
    }
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getContactName() {
        return contactName;
    }
    public Enterprise contactName(String contactName) {
        this.contactName = contactName;
        return this;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }
    public Enterprise contactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Enterprise)) {
            return false;
        }
        return id != null && id.equals(((Enterprise) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Enterprise{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", enterpriseLongitude='" + getEnterpriseLongitude() + "'" +
            ", enterpriseLatitude='" + getEnterpriseLatitude() + "'" +
            ", remark='" + getRemark() + "'" +
            ", isTry='" + getIsTry() + "'" +
            ", groupCode='" + getGroupCode() + "'" +
            ", contactName='" + getContactName() + "'" +
            ", contactPhone='" + getContactPhone() + "'" +
            "}";
    }
}
