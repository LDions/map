package com.ruowei.domain;

import com.ruowei.domain.enumeration.EnterpriseStatusType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 企业信息
 */
@ApiModel(description = "企业信息")
@Entity
@Table(name = "sys_enterprise")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Enterprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 单位名称
     */
    @NotNull
    @ApiModelProperty(value = "单位名称", required = true)
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 企业性质
     */
    @ApiModelProperty(value = "企业性质")
    @Column(name = "nature")
    private String nature;

    /**
     * 统一信用代码
     */
    @NotNull
    @ApiModelProperty(value = "统一信用代码", required = true)
    @Column(name = "uni_credit_code", nullable = false)
    private String uniCreditCode;

    /**
     * 法定代表人
     */
    @ApiModelProperty(value = "法定代表人")
    @Column(name = "legal_representative")
    private String legalRepresentative;

    /**
     * 经营地址所在省
     */
    @NotNull
    @ApiModelProperty(value = "经营地址所在省", required = true)
    @Column(name = "business_province", nullable = false)
    private String businessProvince;

    /**
     * 经营地址所在市
     */
    @NotNull
    @ApiModelProperty(value = "经营地址所在市", required = true)
    @Column(name = "business_city", nullable = false)
    private String businessCity;

    /**
     * 经营地址所在区
     */
    @NotNull
    @ApiModelProperty(value = "经营地址所在区", required = true)
    @Column(name = "business_area", nullable = false)
    private String businessArea;

    /**
     * 经营详细地址
     */
    @NotNull
    @ApiModelProperty(value = "经营详细地址", required = true)
    @Column(name = "business_address", nullable = false)
    private String businessAddress;

    /**
     * 联系人姓名
     */
    @ApiModelProperty(value = "联系人姓名")
    @Column(name = "contact_name")
    private String contactName;

    /**
     * 联系人手机
     */
    @ApiModelProperty(value = "联系人手机")
    @Column(name = "contact_phone")
    private String contactPhone;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;

    /**
     * 状态
     */
    @NotNull
    @ApiModelProperty(value = "状态", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EnterpriseStatusType status;

    // jhipster-needle-entity-add-field - JHipster will add fields here
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
        return this.name;
    }

    public Enterprise name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNature() {
        return this.nature;
    }

    public Enterprise nature(String nature) {
        this.nature = nature;
        return this;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getUniCreditCode() {
        return this.uniCreditCode;
    }

    public Enterprise uniCreditCode(String uniCreditCode) {
        this.uniCreditCode = uniCreditCode;
        return this;
    }

    public void setUniCreditCode(String uniCreditCode) {
        this.uniCreditCode = uniCreditCode;
    }

    public String getLegalRepresentative() {
        return this.legalRepresentative;
    }

    public Enterprise legalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
        return this;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getBusinessProvince() {
        return this.businessProvince;
    }

    public Enterprise businessProvince(String businessProvince) {
        this.businessProvince = businessProvince;
        return this;
    }

    public void setBusinessProvince(String businessProvince) {
        this.businessProvince = businessProvince;
    }

    public String getBusinessCity() {
        return this.businessCity;
    }

    public Enterprise businessCity(String businessCity) {
        this.businessCity = businessCity;
        return this;
    }

    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }

    public String getBusinessArea() {
        return this.businessArea;
    }

    public Enterprise businessArea(String businessArea) {
        this.businessArea = businessArea;
        return this;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public String getBusinessAddress() {
        return this.businessAddress;
    }

    public Enterprise businessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
        return this;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getContactName() {
        return this.contactName;
    }

    public Enterprise contactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }

    public Enterprise contactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getRemark() {
        return this.remark;
    }

    public Enterprise remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public EnterpriseStatusType getStatus() {
        return this.status;
    }

    public void setStatus(EnterpriseStatusType status) {
        this.status = status;
    }

    public Enterprise status(EnterpriseStatusType status) {
        this.status = status;
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

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
            ", nature='" + getNature() + "'" +
            ", uniCreditCode='" + getUniCreditCode() + "'" +
            ", legalRepresentative='" + getLegalRepresentative() + "'" +
            ", businessProvince='" + getBusinessProvince() + "'" +
            ", businessCity='" + getBusinessCity() + "'" +
            ", businessArea='" + getBusinessArea() + "'" +
            ", businessAddress='" + getBusinessAddress() + "'" +
            ", contactName='" + getContactName() + "'" +
            ", contactPhone='" + getContactPhone() + "'" +
            ", remark='" + getRemark() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
