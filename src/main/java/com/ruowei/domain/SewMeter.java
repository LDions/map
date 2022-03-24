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
     * 所属工艺code
     */
    @ApiModelProperty(value = "工艺code", required = true)
    @Column(name = "craft_code", nullable = false)
    private String craftCode;

    /**
     * 进水氨氮（mg/L）
     */
    @ApiModelProperty(value = "进水氨氮（mg/L）")
    @Column(name = "cor_in_ammonia", precision = 21, scale = 2)
    private BigDecimal corInAmmonia;

    /**
     * 进水COD（mg/L）
     */
    @ApiModelProperty(value = "进水COD（mg/L）")
    @Column(name = "cor_in_cod", precision = 21, scale = 2)
    private BigDecimal corInCod;

    /**
     * 进水TN（mg/L）
     */
    @ApiModelProperty(value = "进水TN（mg/L）")
    @Column(name = "cor_in_tn", precision = 21, scale = 2)
    private BigDecimal corInTn;

    /**
     * 进水TP（mg/L）
     */
    @ApiModelProperty(value = "进水TP（mg/L）")
    @Column(name = "cor_in_tp", precision = 21, scale = 2)
    private BigDecimal corInTp;

    /**
     * 缺氧池出口硝酸盐（mg/L）
     */
    @ApiModelProperty(value = "缺氧池出口硝酸盐（mg/L）")
    @Column(name = "cor_anoxic_pool_do_out_nit", precision = 21, scale = 2)
    private BigDecimal corAnoxicPoolDoOutNit;

    /**
     * 好氧池出口硝酸盐（mg/L）
     */
    @ApiModelProperty(value = "好氧池出口硝酸盐（mg/L）")
    @Column(name = "cor_aerobic_pool_do_out_nit", precision = 21, scale = 2)
    private BigDecimal corAerobicPoolDoOutNit;

    /**
     * 出水氨氮（mg/L）
     */
    @ApiModelProperty(value = "出水氨氮（mg/L）")
    @Column(name = "cor_out_ammonia", precision = 21, scale = 2)
    private BigDecimal corOutAmmonia;

    /**
     * 出水COD（mg/L）
     */
    @ApiModelProperty(value = "出水COD（mg/L）")
    @Column(name = "cor_out_cod", precision = 21, scale = 2)
    private BigDecimal corOutCod;

    /**
     * 出水TN（mg/L）
     */
    @ApiModelProperty(value = "出水TN（mg/L）")
    @Column(name = "cor_out_tn", precision = 21, scale = 2)
    private BigDecimal corOutTn;

    /**
     * 出水TP（mg/L）
     */
    @ApiModelProperty(value = "出水TP（mg/L）")
    @Column(name = "cor_out_tp", precision = 21, scale = 2)
    private BigDecimal corOutTp;

    /**
     * 时间
     */
    @NotNull
    @ApiModelProperty(value = "时间", required = true)
    @Column(name = "day_time", nullable = false)
    private Instant dayTime;

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

    public BigDecimal getCorInAmmonia() {
        return corInAmmonia;
    }
    public SewMeter corInAmmonia(BigDecimal corInAmmonia) {
        this.corInAmmonia = corInAmmonia;
        return this;
    }
    public void setCorInAmmonia(BigDecimal corInAmmonia) {
        this.corInAmmonia = corInAmmonia;
    }

    public BigDecimal getCorInCod() {
        return corInCod;
    }
    public SewMeter corInCod(BigDecimal corInCod) {
        this.corInCod = corInCod;
        return this;
    }
    public void setCorInCod(BigDecimal corInCod) {
        this.corInCod = corInCod;
    }

    public BigDecimal getCorInTn() {
        return corInTn;
    }
    public SewMeter corInTn(BigDecimal corInTn) {
        this.corInTn = corInTn;
        return this;
    }
    public void setCorInTn(BigDecimal corInTn) {
        this.corInTn = corInTn;
    }

    public BigDecimal getCorInTp() {
        return corInTp;
    }
    public SewMeter corInTp(BigDecimal corInTp) {
        this.corInTp = corInTp;
        return this;
    }
    public void setCorInTp(BigDecimal corInTp) {
        this.corInTp = corInTp;
    }

    public BigDecimal getCorAnoxicPoolDoOutNit() {
        return corAnoxicPoolDoOutNit;
    }
    public SewMeter corAnoxicPoolDoOutNit(BigDecimal corAnoxicPoolDoOutNit) {
        this.corAnoxicPoolDoOutNit = corAnoxicPoolDoOutNit;
        return this;
    }
    public void setCorAnoxicPoolDoOutNit(BigDecimal corAnoxicPoolDoOutNit) {
        this.corAnoxicPoolDoOutNit = corAnoxicPoolDoOutNit;
    }

    public BigDecimal getCorAerobicPoolDoOutNit() {
        return corAerobicPoolDoOutNit;
    }
    public SewMeter corAerobicPoolDoOutNit(BigDecimal corAerobicPoolDoOutNit) {
        this.corAerobicPoolDoOutNit = corAerobicPoolDoOutNit;
        return this;
    }
    public void setCorAerobicPoolDoOutNit(BigDecimal corAerobicPoolDoOutNit) {
        this.corAerobicPoolDoOutNit = corAerobicPoolDoOutNit;
    }

    public BigDecimal getCorOutAmmonia() {
        return corOutAmmonia;
    }
    public SewMeter corOutAmmonia(BigDecimal corOutAmmonia) {
        this.corOutAmmonia = corOutAmmonia;
        return this;
    }
    public void setCorOutAmmonia(BigDecimal corOutAmmonia) {
        this.corOutAmmonia = corOutAmmonia;
    }

    public BigDecimal getCorOutCod() {
        return corOutCod;
    }
    public SewMeter corOutCod(BigDecimal corOutCod) {
        this.corOutCod = corOutCod;
        return this;
    }
    public void setCorOutCod(BigDecimal corOutCod) {
        this.corOutCod = corOutCod;
    }

    public BigDecimal getCorOutTn() {
        return corOutTn;
    }
    public SewMeter corOutTn(BigDecimal corOutTn) {
        this.corOutTn = corOutTn;
        return this;
    }
    public void setCorOutTn(BigDecimal corOutTn) {
        this.corOutTn = corOutTn;
    }

    public BigDecimal getCorOutTp() {
        return corOutTp;
    }
    public SewMeter corOutTp(BigDecimal corOutTp) {
        this.corOutTp = corOutTp;
        return this;
    }
    public void setCorOutTp(BigDecimal corOutTp) {
        this.corOutTp = corOutTp;
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

    public String getCraftCode() {
        return craftCode;
    }

    public void setCraftCode(String craftCode) {
        this.craftCode = craftCode;
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
            ", craftCode='" + craftCode + '\'' +
            ", corInAmmonia=" + corInAmmonia +
            ", corInCod=" + corInCod +
            ", corInTn=" + corInTn +
            ", corInTp=" + corInTp +
            ", corAnoxicPoolDoOutNit=" + corAnoxicPoolDoOutNit +
            ", corAerobicPoolDoOutNit=" + corAerobicPoolDoOutNit +
            ", corOutAmmonia=" + corOutAmmonia +
            ", corOutCod=" + corOutCod +
            ", corOutTn=" + corOutTn +
            ", corOutTp=" + corOutTp +
            ", dayTime=" + dayTime +
            ", status=" + status +
            ", plateStatus=" + plateStatus +
            '}';
    }
}
