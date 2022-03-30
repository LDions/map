package com.ruowei.domain;

import com.ruowei.domain.enumeration.SendStatusType;
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
     * 碳排放数据编码
     */
    @ApiModelProperty(value = "碳排放数据编码")
    @Column(name = "data_code")
    private String dataCode;

    /**
     * 水厂Code
     */
    @NotNull
    @ApiModelProperty(value = "水厂编码", required = true)
    @Column(name = "enterprise_code", nullable = false)
    private String enterpriseCode;

    /**
     * 工艺Code
     */
    @NotNull
    @ApiModelProperty(value = "工艺编码", required = true)
    @Column(name = "craft_code", nullable = false)
    private String craftCode;

    /**
     * 核算方式（自动、手动）
     */
    @NotNull
    @ApiModelProperty(value = "核算方式（自动、手动）", required = true)
    @Column(name = "acctype", nullable = false)
    private Boolean acctype;

    /**
     * 核算时间范围起
     */
    @NotNull
    @ApiModelProperty(value = "核算时间", required = true)
    @Column(name = "acc_time", nullable = false)
    private Instant accTime;

    /**
     * 预测未来时间
     */
    @NotNull
    @ApiModelProperty(value = "预测未来时间", required = true)
    @Column(name = "predict_time", nullable = false)
    private Instant predictTime;

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

    /**
     * 集团数据推送状态
     */
    @ApiModelProperty(value = "集团数据推送状态")
    @Column(name = "status")
    private SendStatusType status;

    /**
     * 平台数据推送状态
     */
    @ApiModelProperty(value = "平台数据推送状态")
    @Column(name = "plate_status")
    private SendStatusType plateStatus;

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

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
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

    public Boolean getAcctype() {
        return acctype;
    }
    public EmiData acctype(Boolean acctype) {
        this.acctype = acctype;
        return this;
    }
    public void setAcctype(Boolean acctype) {
        this.acctype = acctype;
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

    public SendStatusType getStatus() {
        return status;
    }
    public EmiData status(SendStatusType status) {
        this.status = status;
        return this;
    }
    public void setStatus(SendStatusType status) {
        this.status = status;
    }

    public SendStatusType getPlateStatus() {
        return plateStatus;
    }
    public EmiData plateStatus(SendStatusType plateStatus) {
        this.plateStatus = plateStatus;
        return this;
    }
    public void setPlateStatus(SendStatusType plateStatus) {
        this.plateStatus = plateStatus;
    }

    public Instant getAccTime() {
        return accTime;
    }
    public EmiData accTime(Instant accTime) {
        this.accTime = accTime;
        return this;
    }
    public void setAccTime(Instant accTime) {
        this.accTime = accTime;
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
            ", documentCode='" + documentCode +
            ", dataCode='" + dataCode +
            ", enterpriseCode='" + enterpriseCode +
            ", craftCode='" + craftCode +
            ", acctype=" + acctype +
            ", accTime=" + accTime +
            ", predictTime=" + predictTime +
            ", totalOutN=" + totalOutN +
            ", outAN=" + outAN +
            ", carbonAdd=" + carbonAdd +
            ", phosphorusremover=" + phosphorusremover +
            ", status=" + status +
            ", plateStatus=" + plateStatus +
            '}';
    }
}
