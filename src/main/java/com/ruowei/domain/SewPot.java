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
 * 污水处理药剂月投加量数据
 */
@ApiModel(description = "污水处理药剂月投加量数据")
@Entity
@Table(name = "sew_pot")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SewPot implements Serializable {

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
     * 药剂编码
     */
    @NotNull
    @ApiModelProperty(value = "药剂编码", required = true)
    @Column(name = "potion_code", nullable = false)
    private String potionCode;

    /**
     * 药剂名称
     */
    @NotNull
    @ApiModelProperty(value = "药剂名称", required = true)
    @Column(name = "potion_name", nullable = false)
    private String potionName;

    /**
     * 药剂月投加量（单位kWh/m）
     */
    @NotNull
    @ApiModelProperty(value = "药剂月投加量（单位kWh/m）", required = true)
    @Column(name = "total_pot", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalPot;

    /**
     * 一级投加量（单位kWh/m）
     */
    @ApiModelProperty(value = "一级投加量（单位kWh/m）")
    @Column(name = "level_1_pot", precision = 21, scale = 2)
    private BigDecimal level1Pot;

    /**
     * 二级投加量（单位kWh/m）
     */
    @ApiModelProperty(value = "二级投加量（单位kWh/m）")
    @Column(name = "level_2_pot", precision = 21, scale = 2)
    private BigDecimal level2Pot;

    /**
     * 三级投加量（单位kWh/m）
     */
    @ApiModelProperty(value = "三级投加量（单位kWh/m）")
    @Column(name = "level_3_pot", precision = 21, scale = 2)
    private BigDecimal level3Pot;

    /**
     * 污泥处理投加量（单位kWh/m）
     */
    @ApiModelProperty(value = "污泥处理投加量（单位kWh/m）")
    @Column(name = "slu_treat_pot", precision = 21, scale = 2)
    private BigDecimal sluTreatPot;

    /**
     * 污泥处置投加量（单位kWh/m）
     */
    @ApiModelProperty(value = "污泥处置投加量（单位kWh/m）")
    @Column(name = "slu_handle_pot", precision = 21, scale = 2)
    private BigDecimal sluHandlePot;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SewPot id(Long id) {
        this.id = id;
        return this;
    }

    public String getDocumentCode() {
        return this.documentCode;
    }

    public SewPot documentCode(String documentCode) {
        this.documentCode = documentCode;
        return this;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public String getPotionCode() {
        return this.potionCode;
    }

    public SewPot potionCode(String potionCode) {
        this.potionCode = potionCode;
        return this;
    }

    public void setPotionCode(String potionCode) {
        this.potionCode = potionCode;
    }

    public String getPotionName() {
        return this.potionName;
    }

    public SewPot potionName(String potionName) {
        this.potionName = potionName;
        return this;
    }

    public void setPotionName(String potionName) {
        this.potionName = potionName;
    }

    public BigDecimal getTotalPot() {
        return this.totalPot;
    }

    public SewPot totalPot(BigDecimal totalPot) {
        this.totalPot = totalPot;
        return this;
    }

    public void setTotalPot(BigDecimal totalPot) {
        this.totalPot = totalPot;
    }

    public BigDecimal getLevel1Pot() {
        return this.level1Pot;
    }

    public SewPot level1Pot(BigDecimal level1Pot) {
        this.level1Pot = level1Pot;
        return this;
    }

    public void setLevel1Pot(BigDecimal level1Pot) {
        this.level1Pot = level1Pot;
    }

    public BigDecimal getLevel2Pot() {
        return this.level2Pot;
    }

    public SewPot level2Pot(BigDecimal level2Pot) {
        this.level2Pot = level2Pot;
        return this;
    }

    public void setLevel2Pot(BigDecimal level2Pot) {
        this.level2Pot = level2Pot;
    }

    public BigDecimal getLevel3Pot() {
        return this.level3Pot;
    }

    public SewPot level3Pot(BigDecimal level3Pot) {
        this.level3Pot = level3Pot;
        return this;
    }

    public void setLevel3Pot(BigDecimal level3Pot) {
        this.level3Pot = level3Pot;
    }

    public BigDecimal getSluTreatPot() {
        return this.sluTreatPot;
    }

    public SewPot sluTreatPot(BigDecimal sluTreatPot) {
        this.sluTreatPot = sluTreatPot;
        return this;
    }

    public void setSluTreatPot(BigDecimal sluTreatPot) {
        this.sluTreatPot = sluTreatPot;
    }

    public BigDecimal getSluHandlePot() {
        return this.sluHandlePot;
    }

    public SewPot sluHandlePot(BigDecimal sluHandlePot) {
        this.sluHandlePot = sluHandlePot;
        return this;
    }

    public void setSluHandlePot(BigDecimal sluHandlePot) {
        this.sluHandlePot = sluHandlePot;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SewPot)) {
            return false;
        }
        return id != null && id.equals(((SewPot) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SewPot{" +
            "id=" + getId() +
            ", documentCode='" + getDocumentCode() + "'" +
            ", potionCode='" + getPotionCode() + "'" +
            ", potionName='" + getPotionName() + "'" +
            ", totalPot=" + getTotalPot() +
            ", level1Pot=" + getLevel1Pot() +
            ", level2Pot=" + getLevel2Pot() +
            ", level3Pot=" + getLevel3Pot() +
            ", sluTreatPot=" + getSluTreatPot() +
            ", sluHandlePot=" + getSluHandlePot() +
            "}";
    }
}
