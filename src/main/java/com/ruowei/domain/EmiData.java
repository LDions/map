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
     * 水厂ID
     */
    @NotNull
    @ApiModelProperty(value = "水厂ID", required = true)
    @Column(name = "enterprise_code", nullable = false)
    private String enterpriseCode;

    /**
     * 核算方式（自动、手动）
     */
    @NotNull
    @ApiModelProperty(value = "核算方式（自动、手动）", required = true)
    @Column(name = "acctype", nullable = false)
    private String acctype;

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
     * 核算时间范围起
     */
    @NotNull
    @ApiModelProperty(value = "核算时间范围起", required = true)
    @Column(name = "acc_time_start", nullable = false)
    private String accTimeStart;

    /**
     * 核算时间范围止
     */
    @NotNull
    @ApiModelProperty(value = "核算时间范围止", required = true)
    @Column(name = "acc_time_stop", nullable = false)
    private String accTimeStop;

    /**
     * 预测未来时间
     */
    @NotNull
    @ApiModelProperty(value = "预测未来时间", required = true)
    @Column(name = "predict_time", nullable = false)
    private Instant predictTime;

    /**
     * 工艺id
     */
    @NotNull
    @ApiModelProperty(value = "工艺id", required = true)
    @Column(name = "craft_code", nullable = false)
    private String craftCode;

    /**
     * 出水总氮
     */
    @NotNull
    @ApiModelProperty(value = "出水总氮", required = true)
    @Column(name = "total_out_n", nullable = false)
    private BigDecimal totalOutN;

    /**
     * 出水氨氮
     */
    @NotNull
    @ApiModelProperty(value = "出水氨氮", required = true)
    @Column(name = "out_an", nullable = false)
    private BigDecimal outAN;

    /**
     * 碳源投加量
     */
    @NotNull
    @ApiModelProperty(value = "碳源投加量）", required = true)
    @Column(name = "carbon_add", precision = 21, scale = 2, nullable = false)
    private BigDecimal carbonAdd;

    /**
     * 除磷药剂
     */
    @NotNull
    @ApiModelProperty(value = "除磷药剂）", required = true)
    @Column(name = "phosphorusremover", precision = 21, scale = 2, nullable = false)
    private BigDecimal phosphorusremover;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentCode() {
        return documentCode;
    }
    public EmiData documentCode(String documentCode) {
        this.documentCode = documentCode;
        return this;
    }
    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }
    public EmiData enterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
        return this;
    }
    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getAcctype() {
        return acctype;
    }
    public EmiData acctype(String acctype) {
        this.acctype = acctype;
        return this;
    }
    public void setAcctype(String acctype) {
        this.acctype = acctype;
    }

    public String getAccYear() {
        return accYear;
    }
    public EmiData accYear(String accYear) {
        this.accYear = accYear;
        return this;
    }
    public void setAccYear(String accYear) {
        this.accYear = accYear;
    }

    public String getAccMonth() {
        return accMonth;
    }
    public EmiData accMonth(String accMonth) {
        this.accMonth = accMonth;
        return this;
    }
    public void setAccMonth(String accMonth) {
        this.accMonth = accMonth;
    }

    public String getAccTimeStart() {
        return accTimeStart;
    }
    public EmiData accTimeStart(String accTimeStart) {
        this.accTimeStart = accTimeStart;
        return this;
    }
    public void setAccTimeStart(String accTimeStart) {
        this.accTimeStart = accTimeStart;
    }

    public String getAccTimeStop() {
        return accTimeStop;
    }
    public EmiData accTimeStop(String accTimeStop) {
        this.accTimeStop = accTimeStop;
        return this;
    }
    public void setAccTimeStop(String accTimeStop) {
        this.accTimeStop = accTimeStop;
    }

    public Instant getPredictTime() {
        return predictTime;
    }
    public EmiData predictTime(Instant predictTime) {
        this.predictTime = predictTime;
        return this;
    }
    public void setPredictTime(Instant predictTime) {
        this.predictTime = predictTime;
    }

    public String getCraftCode() {
        return craftCode;
    }
    public EmiData craftCode(String craftCode) {
        this.craftCode = craftCode;
        return this;
    }
    public void setCraftCode(String craftCode) {
        this.craftCode = craftCode;
    }

    public BigDecimal getTotalOutN() {
        return totalOutN;
    }
    public EmiData totalOutN(BigDecimal totalOutN) {
        this.totalOutN = totalOutN;
        return this;
    }
    public void setTotalOutN(BigDecimal totalOutN) {
        this.totalOutN = totalOutN;
    }

    public BigDecimal getOutAN() {
        return outAN;
    }
    public EmiData outAN(BigDecimal outAN) {
        this.outAN = outAN;
        return this;
    }
    public void setOutAN(BigDecimal outAN) {
        this.outAN = outAN;
    }

    public BigDecimal getCarbonAdd() {
        return carbonAdd;
    }
    public EmiData carbonAdd(BigDecimal carbonAdd) {
        this.carbonAdd = carbonAdd;
        return this;
    }
    public void setCarbonAdd(BigDecimal carbonAdd) {
        this.carbonAdd = carbonAdd;
    }

    public BigDecimal getPhosphorusremover() {
        return phosphorusremover;
    }
    public EmiData phosphorusremover(BigDecimal phosphorusremover) {
        this.phosphorusremover = phosphorusremover;
        return this;
    }
    public void setPhosphorusremover(BigDecimal phosphorusremover) {
        this.phosphorusremover = phosphorusremover;
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

    @Override
    public String toString() {
        return "EmiData{" +
            "id=" + id +
            ", documentCode='" + documentCode + '\'' +
            ", enterpriseCode='" + enterpriseCode + '\'' +
            ", acctype='" + acctype + '\'' +
            ", accYear='" + accYear + '\'' +
            ", accMonth='" + accMonth + '\'' +
            ", accTimeStart='" + accTimeStart + '\'' +
            ", accTimeStop='" + accTimeStop + '\'' +
            ", predictTime='" + predictTime + '\'' +
            ", craftCode='" + craftCode + '\'' +
            ", totalOutN=" + totalOutN +
            ", outAN=" + outAN +
            ", carbonAdd=" + carbonAdd +
            ", phosphorusremover=" + phosphorusremover +
            '}';
    }
}
