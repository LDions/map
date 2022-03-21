package com.ruowei.domain;


import com.ruowei.domain.enumeration.SendStatusType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

@ApiModel(description = "数据校验表")
@Entity
@Table(name = "sew_meter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class SewMeter {

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
     * 进水氨氮（mg/L）
     */
    @ApiModelProperty(value = "进水氨氮（mg/L）")
    @Column(name = "ass_in_ammonia", precision = 21, scale = 2)
    private BigDecimal assInAmmonia;

    /**
     * 进水COD（mg/L）
     */
    @ApiModelProperty(value = "进水COD（mg/L）")
    @Column(name = "ass_in_cod", precision = 21, scale = 2)
    private BigDecimal assInCod;

    /**
     * 进水TN（mg/L）
     */
    @ApiModelProperty(value = "进水TN（mg/L）")
    @Column(name = "ass_in_tn", precision = 21, scale = 2)
    private BigDecimal assInTn;

    /**
     * 进水TP（mg/L）
     */
    @ApiModelProperty(value = "进水TP（mg/L）")
    @Column(name = "ass_in_tp", precision = 21, scale = 2)
    private BigDecimal assInTp;

    /**
     * 缺氧池出口硝酸盐（mg/L）
     */
    @ApiModelProperty(value = "缺氧池出口硝酸盐（mg/L）")
    @Column(name = "ass_anoxic_pool_do_out_nit", precision = 21, scale = 2)
    private BigDecimal assAnoxicPoolDoOutNit;

    /**
     * 好氧池出口硝酸盐（mg/L）
     */
    @ApiModelProperty(value = "好氧池出口硝酸盐（mg/L）")
    @Column(name = "ass_aerobic_pool_do_out_nit", precision = 21, scale = 2)
    private BigDecimal assAerobicPoolDoOutNit;

    /**
     * 出水氨氮（mg/L）
     */
    @ApiModelProperty(value = "出水氨氮（mg/L）")
    @Column(name = "ass_out_ammonia", precision = 21, scale = 2)
    private BigDecimal assOutAmmonia;

    /**
     * 出水COD（mg/L）
     */
    @ApiModelProperty(value = "出水COD（mg/L）")
    @Column(name = "ass_out_cod", precision = 21, scale = 2)
    private BigDecimal assOutCod;

    /**
     * 出水TN（mg/L）
     */
    @ApiModelProperty(value = "出水TN（mg/L）")
    @Column(name = "ass_out_tn", precision = 21, scale = 2)
    private BigDecimal assOutTn;

    /**
     * 出水TP（mg/L）
     */
    @ApiModelProperty(value = "出水TP（mg/L）")
    @Column(name = "ass_out_tp", precision = 21, scale = 2)
    private BigDecimal assOutTp;

    /**
     * 时间
     */
    @NotNull
    @ApiModelProperty(value = "时间", required = true)
    @Column(name = "day_time", nullable = false)
    private Instant dayTime;

    /**
     * 所属工艺ID
     */
    @ApiModelProperty(value = "日报表时间", required = true)
    @Column(name = "craft_id", nullable = false)
    private Long craftId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentCode() {
        return documentCode;
    }
    public SewMeter documentCode(String documentCode) {
        this.documentCode = documentCode;
        return this;
    }
    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public BigDecimal getAssInAmmonia() {
        return assInAmmonia;
    }
    public SewMeter assInAmmonia(BigDecimal assInAmmonia) {
        this.assInAmmonia = assInAmmonia;
        return this;
    }
    public void setAssInAmmonia(BigDecimal assInAmmonia) {
        this.assInAmmonia = assInAmmonia;
    }

    public BigDecimal getAssInCod() {
        return assInCod;
    }
    public SewMeter assInCod(BigDecimal assInCod) {
        this.assInCod = assInCod;
        return this;
    }
    public void setAssInCod(BigDecimal assInCod) {
        this.assInCod = assInCod;
    }

    public BigDecimal getAssInTn() {
        return assInTn;
    }
    public SewMeter assInTn(BigDecimal assInTn) {
        this.assInTn = assInTn;
        return this;
    }
    public void setAssInTn(BigDecimal assInTn) {
        this.assInTn = assInTn;
    }

    public BigDecimal getAssInTp() {
        return assInTp;
    }
    public SewMeter assInTp(BigDecimal assInTp) {
        this.assInTp = assInTp;
        return this;
    }
    public void setAssInTp(BigDecimal assInTp) {
        this.assInTp = assInTp;
    }

    public BigDecimal getAssAnoxicPoolDoOutNit() {
        return assAnoxicPoolDoOutNit;
    }
    public SewMeter assAnoxicPoolDoOutNit(BigDecimal assAnoxicPoolDoOutNit) {
        this.assAnoxicPoolDoOutNit = assAnoxicPoolDoOutNit;
        return this;
    }
    public void setAssAnoxicPoolDoOutNit(BigDecimal assAnoxicPoolDoOutNit) {
        this.assAnoxicPoolDoOutNit = assAnoxicPoolDoOutNit;
    }

    public BigDecimal getAssAerobicPoolDoOutNit() {
        return assAerobicPoolDoOutNit;
    }
    public SewMeter assAerobicPoolDoOutNit(BigDecimal assAerobicPoolDoOutNit) {
        this.assAerobicPoolDoOutNit = assAerobicPoolDoOutNit;
        return this;
    }
    public void setAssAerobicPoolDoOutNit(BigDecimal assAerobicPoolDoOutNit) {
        this.assAerobicPoolDoOutNit = assAerobicPoolDoOutNit;
    }

    public BigDecimal getAssOutAmmonia() {
        return assOutAmmonia;
    }
    public SewMeter assOutAmmonia(BigDecimal assOutAmmonia) {
        this.assOutAmmonia = assOutAmmonia;
        return this;
    }
    public void setAssOutAmmonia(BigDecimal assOutAmmonia) {
        this.assOutAmmonia = assOutAmmonia;
    }

    public BigDecimal getAssOutCod() {
        return assOutCod;
    }
    public SewMeter assOutCod(BigDecimal assOutCod) {
        this.assOutCod = assOutCod;
        return this;
    }
    public void setAssOutCod(BigDecimal assOutCod) {
        this.assOutCod = assOutCod;
    }

    public BigDecimal getAssOutTn() {
        return assOutTn;
    }
    public SewMeter assOutTn(BigDecimal assOutTn) {
        this.assOutTn = assOutTn;
        return this;
    }
    public void setAssOutTn(BigDecimal assOutTn) {
        this.assOutTn = assOutTn;
    }

    public BigDecimal getAssOutTp() {
        return assOutTp;
    }
    public SewMeter assOutTp(BigDecimal assOutTp) {
        this.assOutTp = assOutTp;
        return this;
    }
    public void setAssOutTp(BigDecimal assOutTp) {
        this.assOutTp = assOutTp;
    }

    public Instant getDayTime() {
        return dayTime;
    }
    public SewMeter dayTime(Instant dayTime) {
        this.dayTime = dayTime;
        return this;
    }
    public void setDayTime(Instant dayTime) {
        this.dayTime = dayTime;
    }

    public Long getCraftId() {
        return craftId;
    }

    public void setCraftId(Long craftId) {
        this.craftId = craftId;
    }
    public SewMeter craftId(Long craftId) {
        this.craftId = craftId;
        return this;
    }

    public SendStatusType getStatus() {
        return status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SewMeter)) {
            return false;
        }
        return id != null && id.equals(((SewMeter) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "SewMeter{" +
            "id=" + id +
            ", documentCode='" + documentCode + '\'' +
            ", assInAmmonia=" + assInAmmonia +
            ", assInCod=" + assInCod +
            ", assInTn=" + assInTn +
            ", assInTp=" + assInTp +
            ", assAnoxicPoolDoOutNit=" + assAnoxicPoolDoOutNit +
            ", assAerobicPoolDoOutNit=" + assAerobicPoolDoOutNit +
            ", assOutAmmonia=" + assOutAmmonia +
            ", assOutCod=" + assOutCod +
            ", assOutTn=" + assOutTn +
            ", assOutTp=" + assOutTp +
            ", dayTime=" + dayTime +
            ", craftId=" + craftId +
            '}';
    }
}
