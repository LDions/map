package com.ruowei.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 污水处理工艺数据
 */
@ApiModel(description = "污水处理工艺数据")
@Entity
@Table(name = "sew_process")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SewProcess implements Serializable {

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
     * 工艺类型编码
     */
    @NotNull
    @ApiModelProperty(value = "工艺类型编码", required = true)
    @Column(name = "process_type_code", nullable = false)
    private String processTypeCode;

    /**
     * 工艺类型名称
     */
    @NotNull
    @ApiModelProperty(value = "工艺类型名称", required = true)
    @Column(name = "process_type_name", nullable = false)
    private String processTypeName;

    /**
     * 日均规模（m3/d）
     */
    @NotNull
    @ApiModelProperty(value = "日均规模（m3/d）", required = true)
    @Column(name = "daily_scale", precision = 21, scale = 2, nullable = false)
    private BigDecimal dailyScale;

    /**
     * 本月运行天数
     */
    @NotNull
    @ApiModelProperty(value = "本月运行天数", required = true)
    @Column(name = "operating_days", nullable = false)
    private Integer operatingDays;

    /**
     * 进水总氮（mg/L）
     */
    @NotNull
    @ApiModelProperty(value = "进水总氮（mg/L）", required = true)
    @Column(name = "in_nitrogen", precision = 21, scale = 2, nullable = false)
    private BigDecimal inNitrogen;

    /**
     * 进水氨氮（mg/L）
     */
    @NotNull
    @ApiModelProperty(value = "进水氨氮（mg/L）", required = true)
    @Column(name = "in_ammonia", precision = 21, scale = 2, nullable = false)
    private BigDecimal inAmmonia;

    /**
     * 进水COD（mg/L）
     */
    @NotNull
    @ApiModelProperty(value = "进水COD（mg/L）", required = true)
    @Column(name = "in_cod", precision = 21, scale = 2, nullable = false)
    private BigDecimal inCod;

    /**
     * 进水总磷（mg/L）
     */
    @NotNull
    @ApiModelProperty(value = "进水总磷（mg/L）", required = true)
    @Column(name = "in_phosphorus", precision = 21, scale = 2, nullable = false)
    private BigDecimal inPhosphorus;

    /**
     * 进水BOD（mg/L）
     */
    @ApiModelProperty(value = "进水BOD（mg/L）")
    @Column(name = "in_bod", precision = 21, scale = 2)
    private BigDecimal inBod;

    /**
     * 出水总氮（mg/L）
     */
    @NotNull
    @ApiModelProperty(value = "出水总氮（mg/L）", required = true)
    @Column(name = "out_nitrogen", precision = 21, scale = 2, nullable = false)
    private BigDecimal outNitrogen;

    /**
     * 出水氨氮（mg/L）
     */
    @NotNull
    @ApiModelProperty(value = "出水氨氮（mg/L）", required = true)
    @Column(name = "out_ammonia", precision = 21, scale = 2, nullable = false)
    private BigDecimal outAmmonia;

    /**
     * 出水COD（mg/L）
     */
    @NotNull
    @ApiModelProperty(value = "出水COD（mg/L）", required = true)
    @Column(name = "out_cod", precision = 21, scale = 2, nullable = false)
    private BigDecimal outCod;

    /**
     * 出水总磷（mg/L）
     */
    @NotNull
    @ApiModelProperty(value = "出水总磷（mg/L）", required = true)
    @Column(name = "out_phosphorus", precision = 21, scale = 2, nullable = false)
    private BigDecimal outPhosphorus;

    /**
     * 出水BOD（mg/L）
     */
    @ApiModelProperty(value = "出水BOD（mg/L）")
    @Column(name = "out_bod", precision = 21, scale = 2)
    private BigDecimal outBod;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SewProcess id(Long id) {
        this.id = id;
        return this;
    }

    public String getDocumentCode() {
        return this.documentCode;
    }

    public SewProcess documentCode(String documentCode) {
        this.documentCode = documentCode;
        return this;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public String getProcessTypeCode() {
        return this.processTypeCode;
    }

    public SewProcess processTypeCode(String processTypeCode) {
        this.processTypeCode = processTypeCode;
        return this;
    }

    public void setProcessTypeCode(String processTypeCode) {
        this.processTypeCode = processTypeCode;
    }

    public String getProcessTypeName() {
        return this.processTypeName;
    }

    public SewProcess processTypeName(String processTypeName) {
        this.processTypeName = processTypeName;
        return this;
    }

    public void setProcessTypeName(String processTypeName) {
        this.processTypeName = processTypeName;
    }

    public BigDecimal getDailyScale() {
        return this.dailyScale;
    }

    public SewProcess dailyScale(BigDecimal dailyScale) {
        this.dailyScale = dailyScale;
        return this;
    }

    public void setDailyScale(BigDecimal dailyScale) {
        this.dailyScale = dailyScale;
    }

    public Integer getOperatingDays() {
        return this.operatingDays;
    }

    public SewProcess operatingDays(Integer operatingDays) {
        this.operatingDays = operatingDays;
        return this;
    }

    public void setOperatingDays(Integer operatingDays) {
        this.operatingDays = operatingDays;
    }

    public BigDecimal getInNitrogen() {
        return this.inNitrogen;
    }

    public SewProcess inNitrogen(BigDecimal inNitrogen) {
        this.inNitrogen = inNitrogen;
        return this;
    }

    public void setInNitrogen(BigDecimal inNitrogen) {
        this.inNitrogen = inNitrogen;
    }

    public BigDecimal getInAmmonia() {
        return this.inAmmonia;
    }

    public SewProcess inAmmonia(BigDecimal inAmmonia) {
        this.inAmmonia = inAmmonia;
        return this;
    }

    public void setInAmmonia(BigDecimal inAmmonia) {
        this.inAmmonia = inAmmonia;
    }

    public BigDecimal getInCod() {
        return this.inCod;
    }

    public SewProcess inCod(BigDecimal inCod) {
        this.inCod = inCod;
        return this;
    }

    public void setInCod(BigDecimal inCod) {
        this.inCod = inCod;
    }

    public BigDecimal getInPhosphorus() {
        return this.inPhosphorus;
    }

    public SewProcess inPhosphorus(BigDecimal inPhosphorus) {
        this.inPhosphorus = inPhosphorus;
        return this;
    }

    public void setInPhosphorus(BigDecimal inPhosphorus) {
        this.inPhosphorus = inPhosphorus;
    }

    public BigDecimal getInBod() {
        return this.inBod;
    }

    public SewProcess inBod(BigDecimal inBod) {
        this.inBod = inBod;
        return this;
    }

    public void setInBod(BigDecimal inBod) {
        this.inBod = inBod;
    }

    public BigDecimal getOutNitrogen() {
        return this.outNitrogen;
    }

    public SewProcess outNitrogen(BigDecimal outNitrogen) {
        this.outNitrogen = outNitrogen;
        return this;
    }

    public void setOutNitrogen(BigDecimal outNitrogen) {
        this.outNitrogen = outNitrogen;
    }

    public BigDecimal getOutAmmonia() {
        return this.outAmmonia;
    }

    public SewProcess outAmmonia(BigDecimal outAmmonia) {
        this.outAmmonia = outAmmonia;
        return this;
    }

    public void setOutAmmonia(BigDecimal outAmmonia) {
        this.outAmmonia = outAmmonia;
    }

    public BigDecimal getOutCod() {
        return this.outCod;
    }

    public SewProcess outCod(BigDecimal outCod) {
        this.outCod = outCod;
        return this;
    }

    public void setOutCod(BigDecimal outCod) {
        this.outCod = outCod;
    }

    public BigDecimal getOutPhosphorus() {
        return this.outPhosphorus;
    }

    public SewProcess outPhosphorus(BigDecimal outPhosphorus) {
        this.outPhosphorus = outPhosphorus;
        return this;
    }

    public void setOutPhosphorus(BigDecimal outPhosphorus) {
        this.outPhosphorus = outPhosphorus;
    }

    public BigDecimal getOutBod() {
        return this.outBod;
    }

    public SewProcess outBod(BigDecimal outBod) {
        this.outBod = outBod;
        return this;
    }

    public void setOutBod(BigDecimal outBod) {
        this.outBod = outBod;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SewProcess)) {
            return false;
        }
        return id != null && id.equals(((SewProcess) o).id);
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
            ", documentCode='" + getDocumentCode() + "'" +
            ", processTypeCode='" + getProcessTypeCode() + "'" +
            ", processTypeName='" + getProcessTypeName() + "'" +
            ", dailyScale=" + getDailyScale() +
            ", operatingDays=" + getOperatingDays() +
            ", inNitrogen=" + getInNitrogen() +
            ", inAmmonia=" + getInAmmonia() +
            ", inCod=" + getInCod() +
            ", inPhosphorus=" + getInPhosphorus() +
            ", inBod=" + getInBod() +
            ", outNitrogen=" + getOutNitrogen() +
            ", outAmmonia=" + getOutAmmonia() +
            ", outCod=" + getOutCod() +
            ", outPhosphorus=" + getOutPhosphorus() +
            ", outBod=" + getOutBod() +
            "}";
    }
}
