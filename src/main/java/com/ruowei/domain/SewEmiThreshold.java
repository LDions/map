package com.ruowei.domain;

import com.ruowei.domain.enumeration.SendStatusType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 进出水指标阈值
 */
@ApiModel(description = "进出水指标阈值")
@Entity
@Table(name = "sew_emi_threshold")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SewEmiThreshold implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 单据号
     */
    @ApiModelProperty(value = "企业id", required = true)
    @Column(name = "enterprise_id", nullable = false)
    private Long enterpriseId;

    /**
     * 进水COD阈值
     */
    @ApiModelProperty(value = "进水COD阈值", required = true)
    @Column(name = "in_cod_limit", nullable = false)
    private BigDecimal inCodLimit;

    /**
     * 进水总氮上限（mg/L）
     */
    @ApiModelProperty(value = "进水总氮上限（mg/L）", required = true)
    @Column(name = "in_totalN_limit", precision = 21, scale = 2, nullable = false)
    private BigDecimal inTotalNLimit;

    /**
     * 进水氨氮期上限（mg/L）
     */
    @ApiModelProperty(value = "进水氨氮期上限（mg/L）", required = true)
    @Column(name = "in_totalAN_limit", precision = 21, scale = 2, nullable = false)
    private BigDecimal inTotalANLimit;

    /**
     * 进水总磷期上限（mg/L）
     */
    @ApiModelProperty(value = "进水总磷期上限（mg/L）", required = true)
    @Column(name = "in_totalP_limit", precision = 21, scale = 2, nullable = false)
    private BigDecimal inTotalPLimit;

    /**
     * 出水COD阈值（mg/L）
     */
    @ApiModelProperty(value = "出水COD阈值（mg/L）", required = true)
    @Column(name = "out_cod_limit", precision = 21, scale = 2, nullable = false)
    private BigDecimal outCodLimit;

    /**
     * 出水总氮上限（mg/L）
     */
    @ApiModelProperty(value = "出水总氮上限（mg/L）")
    @Column(name = "out_totalN_limit", precision = 21, scale = 2)
    private BigDecimal outTotalNLimit;

    /**
     * 出水氨氮期上限（mg/L）
     */
    @ApiModelProperty(value = "出水氨氮期上限（mg/L）")
    @Column(name = "out_totalAN_limit", precision = 21, scale = 2)
    private BigDecimal outTotalANLimit;

    /**
     * 出水总磷期上限（mg/L）
     */
    @ApiModelProperty(value = "出水总磷期上限（mg/L）", required = true)
    @Column(name = "out——totalP_limit", precision = 21, scale = 2, nullable = false)
    private BigDecimal outTotalPLimit;

    /**
     * 集团数据推送状态
     */
    @ApiModelProperty(value = "集团数据推送状态", required = true)
    @Column(name = "status", nullable = false)
    private SendStatusType status;

    /**
     * 平台数据推送状态
     */
    @ApiModelProperty(value = "平台数据推送状态", required = true)
    @Column(name = "plate_status", nullable = false)
    private SendStatusType plateStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SewEmiThreshold id(Long id) {
        this.id = id;
        return this;
    }

    public Long getEnterpriseId() {
        return this.enterpriseId;
    }

    public SewEmiThreshold enterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
        return this;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public BigDecimal getInCodLimit() {
        return this.inCodLimit;
    }

    public SewEmiThreshold inCodLimit(BigDecimal inCodLimit) {
        this.inCodLimit = inCodLimit;
        return this;
    }

    public void setInCodLimit(BigDecimal inCodLimit) {
        this.inCodLimit = inCodLimit;
    }


    public BigDecimal getInTotalNLimit() {
        return this.inTotalNLimit;
    }

    public SewEmiThreshold inTotalNLimit(BigDecimal inTotalNLimit) {
        this.inTotalNLimit = inTotalNLimit;
        return this;
    }

    public void setInTotalNLimit(BigDecimal inTotalNLimit) {
        this.inTotalNLimit = inTotalNLimit;
    }

    public BigDecimal getInTotalANLimit() {
        return this.inTotalANLimit;
    }

    public SewEmiThreshold inTotalANLimit(BigDecimal inTotalANLimit) {
        this.inTotalANLimit = inTotalANLimit;
        return this;
    }

    public void setInTotalANLimit(BigDecimal inAmmonia) {
        this.inTotalANLimit = inTotalANLimit;
    }

    public BigDecimal getInTotalPLimit() {
        return this.inTotalPLimit;
    }

    public SewEmiThreshold inTotalPLimit(BigDecimal inTotalPLimit) {
        this.inTotalPLimit = inTotalPLimit;
        return this;
    }

    public void setInTotalPLimit(BigDecimal inTotalPLimit) {
        this.inTotalPLimit = inTotalPLimit;
    }

    public BigDecimal getOutCodLimit() {
        return this.outCodLimit;
    }

    public SewEmiThreshold outCodLimit(BigDecimal outCodLimit) {
        this.outCodLimit = outCodLimit;
        return this;
    }

    public void setOutCodLimit(BigDecimal outCodLimit) {
        this.outCodLimit = outCodLimit;
    }

    public BigDecimal getOutTotalNLimit() {
        return this.outTotalNLimit;
    }

    public SewEmiThreshold outTotalNLimit(BigDecimal outTotalNLimit) {
        this.outTotalNLimit = outTotalNLimit;
        return this;
    }

    public void setOutTotalNLimit(BigDecimal outTotalNLimit) {
        this.outTotalNLimit = outTotalNLimit;
    }

    public BigDecimal getOutTotalANLimit() {
        return this.outTotalANLimit;
    }

    public SewEmiThreshold outTotalANLimit(BigDecimal outTotalANLimit) {
        this.outTotalANLimit = outTotalANLimit;
        return this;
    }

    public void setOutTotalANLimit(BigDecimal outTotalANLimit) {
        this.outTotalANLimit = outTotalANLimit;
    }

    public BigDecimal getOutTotalPLimit() {
        return this.outTotalPLimit;
    }

    public SewEmiThreshold outTotalPLimit(BigDecimal outTotalPLimit) {
        this.outTotalPLimit = outTotalPLimit;
        return this;
    }

    public void setOutTotalPLimit(BigDecimal outTotalPLimit) {
        this.outTotalPLimit = outTotalPLimit;
    }

    public SendStatusType getStatus() {
        return status;
    }
    public SewEmiThreshold status(SendStatusType status) {
        this.status = status;
        return this;
    }
    public void setStatus(SendStatusType status) {
        this.status = status;
    }

    public SendStatusType getPlateStatus() {
        return plateStatus;
    }

    public void setPlateStatus(SendStatusType plateStatus) {
        this.plateStatus = plateStatus;
    }
    public SewEmiThreshold plateStatus(SendStatusType plateStatus) {
        this.plateStatus = plateStatus;
        return this;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SewEmiThreshold)) {
            return false;
        }
        return id != null && id.equals(((SewEmiThreshold) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SewProcess{" +
            "id=" + getId() +
            ", enterpriseId='" + getEnterpriseId() + "'" +
            ", inCodLimit='" + getInCodLimit() + "'" +
            ", inTotalNLimit=" + getInTotalNLimit() +
            ", inTotalANLimit=" + getInTotalANLimit() +
            ", inTotalPLimit=" + getInTotalPLimit() +
            ", outCodLimit=" + getOutCodLimit() +
            ", outTotalNLimit=" + getOutTotalNLimit() +
            ", outTotalANLimit=" + getOutTotalANLimit() +
            ", outTotalPLimit=" + getOutTotalPLimit() +
            ", status=" + getStatus() +
            "}";
    }
}
