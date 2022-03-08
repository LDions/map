package com.ruowei.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 碳排放数据
 */
@ApiModel(description = "碳排放数据")
@Entity
@Table(name = "emi_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmiData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 单据号
     */
    @NotNull
    @ApiModelProperty(value = "单据号", required = true)
    @Column(name = "document_code", nullable = false)
    private String documentCode;

    /**
     * 企业ID
     */
    @NotNull
    @ApiModelProperty(value = "企业ID", required = true)
    @Column(name = "enterprise_id", nullable = false)
    private Long enterpriseId;

    /**
     * 企业名称
     */
    @NotNull
    @ApiModelProperty(value = "企业名称", required = true)
    @Column(name = "enterprise_name", nullable = false)
    private String enterpriseName;

    /**
     * 录入员ID
     */
    @NotNull
    @ApiModelProperty(value = "录入员ID", required = true)
    @Column(name = "reporter_id", nullable = false)
    private Long reporterId;

    /**
     * 录入员姓名
     */
    @NotNull
    @ApiModelProperty(value = "录入员姓名", required = true)
    @Column(name = "reporter_name", nullable = false)
    private String reporterName;

    /**
     * 填报时间
     */
    @NotNull
    @ApiModelProperty(value = "填报时间", required = true)
    @Column(name = "report_time", nullable = false)
    private Instant reportTime;

    /**
     * 核算年份
     */
    @NotNull
    @ApiModelProperty(value = "核算年份", required = true)
    @Column(name = "acc_year", nullable = false)
    private String accYear;

    /**
     * 核算月份
     */
    @NotNull
    @ApiModelProperty(value = "核算月份", required = true)
    @Column(name = "acc_month", nullable = false)
    private String accMonth;

    /**
     * 核算时间
     */
    @NotNull
    @ApiModelProperty(value = "核算时间", required = true)
    @Column(name = "acc_time", nullable = false)
    private String accTime;

    /**
     * 行业类型编码
     */
    @NotNull
    @ApiModelProperty(value = "行业类型编码", required = true)
    @Column(name = "industry_code", nullable = false)
    private String industryCode;

    /**
     * 行业类型名称
     */
    @NotNull
    @ApiModelProperty(value = "行业类型名称", required = true)
    @Column(name = "industry_name", nullable = false)
    private String industryName;

    /**
     * 本月碳排放（kg/m）
     */
    @NotNull
    @ApiModelProperty(value = "本月碳排放（kg/m）", required = true)
    @Column(name = "carbon_emi", precision = 21, scale = 2, nullable = false)
    private BigDecimal carbonEmi;

    /**
     * 本月直接碳排放（kg）
     */
    @NotNull
    @ApiModelProperty(value = "本月直接碳排放（kg）", required = true)
    @Column(name = "carbon_dir_emi", precision = 21, scale = 2, nullable = false)
    private BigDecimal carbonDirEmi;

    /**
     * 本月间接碳排放（kg）
     */
    @NotNull
    @ApiModelProperty(value = "本月间接碳排放（kg）", required = true)
    @Column(name = "carbon_indir_emi", precision = 21, scale = 2, nullable = false)
    private BigDecimal carbonIndirEmi;

    /**
     * 本月负碳排放（kg）
     */
    @NotNull
    @ApiModelProperty(value = "本月负碳排放（kg）", required = true)
    @Column(name = "carbon_red", precision = 21, scale = 2, nullable = false)
    private BigDecimal carbonRed;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmiData id(Long id) {
        this.id = id;
        return this;
    }

    public String getDocumentCode() {
        return this.documentCode;
    }

    public EmiData documentCode(String documentCode) {
        this.documentCode = documentCode;
        return this;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public Long getEnterpriseId() {
        return this.enterpriseId;
    }

    public EmiData enterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
        return this;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return this.enterpriseName;
    }

    public EmiData enterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
        return this;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Long getReporterId() {
        return this.reporterId;
    }

    public EmiData reporterId(Long reporterId) {
        this.reporterId = reporterId;
        return this;
    }

    public void setReporterId(Long reporterId) {
        this.reporterId = reporterId;
    }

    public String getReporterName() {
        return this.reporterName;
    }

    public EmiData reporterName(String reporterName) {
        this.reporterName = reporterName;
        return this;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public Instant getReportTime() {
        return this.reportTime;
    }

    public EmiData reportTime(Instant reportTime) {
        this.reportTime = reportTime;
        return this;
    }

    public void setReportTime(Instant reportTime) {
        this.reportTime = reportTime;
    }

    public String getAccYear() {
        return this.accYear;
    }

    public EmiData accYear(String accYear) {
        this.accYear = accYear;
        return this;
    }

    public void setAccYear(String accYear) {
        this.accYear = accYear;
    }

    public String getAccMonth() {
        return this.accMonth;
    }

    public EmiData accMonth(String accMonth) {
        this.accMonth = accMonth;
        return this;
    }

    public void setAccMonth(String accMonth) {
        this.accMonth = accMonth;
    }

    public String getAccTime() {
        return accTime;
    }

    public EmiData accTime(String accTime) {
        this.accTime = accTime;
        return this;
    }

    public void setAccTime(String accTime) {
        this.accTime = accTime;
    }

    public String getIndustryCode() {
        return this.industryCode;
    }

    public EmiData industryCode(String industryCode) {
        this.industryCode = industryCode;
        return this;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getIndustryName() {
        return this.industryName;
    }

    public EmiData industryName(String industryName) {
        this.industryName = industryName;
        return this;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public BigDecimal getCarbonEmi() {
        return this.carbonEmi;
    }

    public EmiData carbonEmi(BigDecimal carbonEmi) {
        this.carbonEmi = carbonEmi;
        return this;
    }

    public void setCarbonEmi(BigDecimal carbonEmi) {
        this.carbonEmi = carbonEmi;
    }

    public BigDecimal getCarbonDirEmi() {
        return this.carbonDirEmi;
    }

    public EmiData carbonDirEmi(BigDecimal carbonDirEmi) {
        this.carbonDirEmi = carbonDirEmi;
        return this;
    }

    public void setCarbonDirEmi(BigDecimal carbonDirEmi) {
        this.carbonDirEmi = carbonDirEmi;
    }

    public BigDecimal getCarbonIndirEmi() {
        return this.carbonIndirEmi;
    }

    public EmiData carbonIndirEmi(BigDecimal carbonIndirEmi) {
        this.carbonIndirEmi = carbonIndirEmi;
        return this;
    }

    public void setCarbonIndirEmi(BigDecimal carbonIndirEmi) {
        this.carbonIndirEmi = carbonIndirEmi;
    }

    public BigDecimal getCarbonRed() {
        return this.carbonRed;
    }

    public EmiData carbonRed(BigDecimal carbonRed) {
        this.carbonRed = carbonRed;
        return this;
    }

    public void setCarbonRed(BigDecimal carbonRed) {
        this.carbonRed = carbonRed;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmiData)) {
            return false;
        }
        return id != null && id.equals(((EmiData) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmiData{" +
            "id=" + getId() +
            ", documentCode='" + getDocumentCode() + "'" +
            ", enterpriseId=" + getEnterpriseId() +
            ", enterpriseName='" + getEnterpriseName() + "'" +
            ", reporterId=" + getReporterId() +
            ", reporterName='" + getReporterName() + "'" +
            ", reportTime='" + getReportTime() + "'" +
            ", accYear='" + getAccYear() + "'" +
            ", accMonth='" + getAccMonth() + "'" +
            ", accTime='" + getAccTime() + "'" +
            ", industryCode='" + getIndustryCode() + "'" +
            ", industryName='" + getIndustryName() + "'" +
            ", carbonEmi=" + getCarbonEmi() +
            ", carbonDirEmi=" + getCarbonDirEmi() +
            ", carbonIndirEmi=" + getCarbonIndirEmi() +
            ", carbonRed=" + getCarbonRed() +
            "}";
    }
}
