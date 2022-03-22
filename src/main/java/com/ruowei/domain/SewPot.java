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
 * 日报表数据
 */
@ApiModel(description = "日报表数据")
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
    @ApiModelProperty(value = "单据号", required = true)
    @Column(name = "document_code", nullable = false)
    private String documentCode;

    /**
     * 日报编码
     */
    @ApiModelProperty(value = "日报编码", required = true)
    @Column(name = "pot_code", nullable = false)
    private String potCode;

    /**
     * 工艺编码
     */
    @ApiModelProperty(value = "工艺编码", required = true)
    @Column(name = "craft_code", nullable = false)
    private String craftCode;

    /**
     * 进水PH
     */
    @ApiModelProperty(value = "进水PH", required = true)
    @Column(name = "day_in_ph", nullable = false)
    private BigDecimal dayInPh;

    /**
     * 出水PH
     */
    @ApiModelProperty(value = "出水PH", required = true)
    @Column(name = "day_out_ph", nullable = false)
    private BigDecimal dayOutPh;

    /**
     * 初沉池排泥量
     */
    @ApiModelProperty(value = "初沉池排泥量", required = true)
    @Column(name = "day_first_mud", nullable = false)
    private BigDecimal dayFirstMud;

    /**
     * 二沉池排泥量
     */
    @ApiModelProperty(value = "二沉池排泥量", required = true)
    @Column(name = "day_second_mud", nullable = false)
    private BigDecimal daySecondMud;

    /**
     * 回流比
     */
    @ApiModelProperty(value = "回流比", required = true)
    @Column(name = "day_reflux", nullable = false)
    private BigDecimal dayReflux;

    /**
     * 碳源投加量
     */
    @ApiModelProperty(value = "碳源投加量", required = true)
    @Column(name = "day_car_add", nullable = false)
    private BigDecimal dayCarAdd;

    /**
     * 生化池-厌氧池PH
     */
    @ApiModelProperty(value = "生化池-厌氧池PH", required = true)
    @Column(name = "day_anaerobic_pool_ph", nullable = false)
    private BigDecimal dayAnaerobicPoolPh;

    /**
     * 生化池-厌氧池ORP
     */
    @ApiModelProperty(value = "生化池-厌氧池ORP", required = true)
    @Column(name = "day_anaerobic_pool_orp", nullable = false)
    private BigDecimal dayAnaerobicPoolOrp;

    /**
     * 生化池-厌氧池DO
     */
    @ApiModelProperty(value = "生化池-厌氧池DO", required = true)
    @Column(name = "day_anaerobic_pool_do", nullable = false)
    private BigDecimal dayAnaerobicPoolDo;

    /**
     * 生化池-厌氧池SOUR
     */
    @ApiModelProperty(value = "生化池-厌氧池SOUR", required = true)
    @Column(name = "day_anaerobic_pool_sour", nullable = false)
    private BigDecimal dayAnaerobicPoolSour;

    /**
     * 生化池-厌氧池SV
     */
    @ApiModelProperty(value = "生化池-厌氧池SV", required = true)
    @Column(name = "day_anaerobic_pool_sv", nullable = false)
    private BigDecimal dayAnaerobicPoolSv;

    /**
     * 生化池-厌氧池MLSS
     */
    @ApiModelProperty(value = "生化池-厌氧池MLSS", required = true)
    @Column(name = "day_anaerobic_pool_mlss", nullable = false)
    private BigDecimal dayAnaerobicPoolMlss;

    /**
     * 生化池-厌氧池温度
     */
    @ApiModelProperty(value = "生化池-厌氧池温度", required = true)
    @Column(name = "day_anaerobic_pool_temper", nullable = false)
    private BigDecimal dayAnaerobicPoolTemper;

    /**
     * 生化池-缺氧池PH
     */
    @ApiModelProperty(value = "生化池-缺氧池PH", required = true)
    @Column(name = "day_anoxic_pool_ph", nullable = false)
    private BigDecimal dayAnoxicPoolPh;

    /**
     * 生化池-缺氧池ORP
     */
    @ApiModelProperty(value = "生化池-缺氧池ORP", required = true)
    @Column(name = "day_anoxic_pool_orp", nullable = false)
    private BigDecimal dayAnoxicPoolOrp;

    /**
     * 生化池-缺氧池DO
     */
    @ApiModelProperty(value = "生化池-缺氧池DO", required = true)
    @Column(name = "day_anoxic_pool_do", nullable = false)
    private BigDecimal dayAnoxicPoolDo;

    /**
     * 生化池-缺氧池SOUR
     */
    @ApiModelProperty(value = "生化池-缺氧池SOUR", required = true)
    @Column(name = "day_anoxic_pool_sour", nullable = false)
    private BigDecimal dayAnoxicPoolSour;

    /**
     * 生化池-缺氧池SV
     */
    @ApiModelProperty(value = "生化池-缺氧池SV", required = true)
    @Column(name = "day_anoxic_pool_sv", nullable = false)
    private BigDecimal dayAnoxicPoolSv;

    /**
     * 生化池-缺氧池MLSS
     */
    @ApiModelProperty(value = "生化池-缺氧池MLSS", required = true)
    @Column(name = "day_anoxic_pool_mlss", nullable = false)
    private BigDecimal dayAnoxicPoolMlss;

    /**
     * 生化池-缺氧池温度
     */
    @ApiModelProperty(value = "生化池-缺氧池温度", required = true)
    @Column(name = "day_anoxic_pool_temper", nullable = false)
    private BigDecimal dayAnoxicPoolTemper;

    /**
     * 生化池-好氧池PH
     */
    @ApiModelProperty(value = "生化池-好氧池PH", required = true)
    @Column(name = "day_aerobic_pool_ph", nullable = false)
    private BigDecimal dayAerobicPoolPh;

    /**
     * 生化池-好氧池ORP
     */
    @ApiModelProperty(value = "生化池-好氧池ORP", required = true)
    @Column(name = "day_aerobic_pool_orp", nullable = false)
    private BigDecimal dayAerobicPoolOrp;

    /**
     * 生化池-好氧池DO
     */
    @ApiModelProperty(value = "生化池-好氧池DO", required = true)
    @Column(name = "day_aerobic_pool_do", nullable = false)
    private BigDecimal dayAerobicPoolDo;

    /**
     * 生化池-好氧池SOUR
     */
    @ApiModelProperty(value = "生化池-好氧池SOUR", required = true)
    @Column(name = "day_aerobic_pool_sour", nullable = false)
    private BigDecimal dayAerobicPoolSour;

    /**
     * 生化池-好氧池SV
     */
    @ApiModelProperty(value = "生化池-好氧池SV", required = true)
    @Column(name = "day_aerobic_pool_sv", nullable = false)
    private BigDecimal dayAerobicPoolSv;

    /**
     * 生化池-好氧池MLSS
     */
    @ApiModelProperty(value = "生化池-好氧池MLSS", required = true)
    @Column(name = "day_aerobic_pool_mlss", nullable = false)
    private BigDecimal dayAerobicPoolMlss;

    /**
     * 生化池-好氧池MLVSS
     */
    @ApiModelProperty(value = "生化池-好氧池MLVSS", required = true)
    @Column(name = "day_aerobic_pool_mlvss", nullable = false)
    private BigDecimal dayAerobicPoolMlvss;

    /**
     * 生化池-好氧池SVI
     */
    @ApiModelProperty(value = "生化池-好氧池SVI", required = true)
    @Column(name = "day_aerobic_pool_svi", nullable = false)
    private BigDecimal dayAerobicPoolSvi;

    /**
     * 生化池-好氧池温度
     */
    @ApiModelProperty(value = "生化池-好氧池温度", required = true)
    @Column(name = "day_aerobic_pool_temper", nullable = false)
    private BigDecimal dayAerobicPoolTemper;

    /**
     * 日报表时间 yyyy/MM/dd HH:mm:ss
     */
    @ApiModelProperty(value = "日报表时间", required = true)
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

    /**
     * 所属工艺ID
     */
    @ApiModelProperty(value = "日报表时间", required = true)
    @Column(name = "craft_id", nullable = false)
    private Long craftId;

    public SendStatusType getStatus() {
        return status;
    }
    public SewPot status(SendStatusType status) {
        this.status = status;
        return this;
    }
    public void setStatus(SendStatusType status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


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
        return documentCode;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }
    public SewPot documentCode(String documentCode) {
        this.documentCode = documentCode;
        return this;
    }
    public BigDecimal getDayInPh() {
        return dayInPh;
    }

    public void setDayInPh(BigDecimal dayInPh) {
        this.dayInPh = dayInPh;
    }
    public SewPot dayInPh(BigDecimal dayInPh) {
        this.dayInPh = dayInPh;
        return this;
    }

    public BigDecimal getDayOutPh() {
        return dayOutPh;
    }

    public void setDayOutPh(BigDecimal dayOutPh) {
        this.dayOutPh = dayOutPh;
    }
    public SewPot dayOutPh(BigDecimal dayOutPh) {
        this.dayOutPh = dayOutPh;
        return this;
    }
    public BigDecimal getDayFirstMud() {
        return dayFirstMud;
    }

    public void setDayFirstMud(BigDecimal dayFirstMud) {
        this.dayFirstMud = dayFirstMud;
    }
    public SewPot dayFirstMud(BigDecimal dayFirstMud) {
        this.dayFirstMud = dayFirstMud;
        return this;
    }
    public BigDecimal getDaySecondMud() {
        return daySecondMud;
    }

    public void setDaySecondMud(BigDecimal daySecondMud) {
        this.daySecondMud = daySecondMud;
    }
    public SewPot daySecondMud(BigDecimal daySecondMud) {
        this.daySecondMud = daySecondMud;
        return this;
    }
    public BigDecimal getDayReflux() {
        return dayReflux;
    }

    public void setDayReflux(BigDecimal dayReflux) {
        this.dayReflux = dayReflux;
    }
    public SewPot dayReflux(BigDecimal dayReflux) {
        this.dayReflux = dayReflux;
        return this;
    }
    public BigDecimal getDayCarAdd() {
        return dayCarAdd;
    }

    public void setDayCarAdd(BigDecimal dayCarAdd) {
        this.dayCarAdd = dayCarAdd;
    }
    public SewPot dayCarAdd(BigDecimal dayCarAdd) {
        this.dayCarAdd = dayCarAdd;
        return this;
    }
    public BigDecimal getDayAnaerobicPoolPh() {
        return dayAnaerobicPoolPh;
    }

    public void setDayAnaerobicPoolPh(BigDecimal dayAnaerobicPoolPh) {
        this.dayAnaerobicPoolPh = dayAnaerobicPoolPh;
    }
    public SewPot dayAnaerobicPoolPh(BigDecimal dayAnaerobicPoolPh) {
        this.dayAnaerobicPoolPh = dayAnaerobicPoolPh;
        return this;
    }
    public BigDecimal getDayAnaerobicPoolOrp() {
        return dayAnaerobicPoolOrp;
    }

    public void setDayAnaerobicPoolOrp(BigDecimal dayAnaerobicPoolOrp) {
        this.dayAnaerobicPoolOrp = dayAnaerobicPoolOrp;
    }
    public SewPot dayAnaerobicPoolOrp(BigDecimal dayAnaerobicPoolOrp) {
        this.dayAnaerobicPoolOrp = dayAnaerobicPoolOrp;
        return this;
    }
    public BigDecimal getDayAnaerobicPoolDo() {
        return dayAnaerobicPoolDo;
    }

    public void setDayAnaerobicPoolDo(BigDecimal dayAnaerobicPoolDo) {
        this.dayAnaerobicPoolDo = dayAnaerobicPoolDo;
    }
    public SewPot dayAnaerobicPoolDo(BigDecimal dayAnaerobicPoolDo) {
        this.dayAnaerobicPoolDo = dayAnaerobicPoolDo;
        return this;
    }
    public BigDecimal getDayAnaerobicPoolSour() {
        return dayAnaerobicPoolSour;
    }

    public void setDayAnaerobicPoolSour(BigDecimal dayAnaerobicPoolSour) {
        this.dayAnaerobicPoolSour = dayAnaerobicPoolSour;
    }
    public SewPot dayAnaerobicPoolSour(BigDecimal dayAnaerobicPoolSour) {
        this.dayAnaerobicPoolSour = dayAnaerobicPoolSour;
        return this;
    }
    public BigDecimal getDayAnaerobicPoolSv() {
        return dayAnaerobicPoolSv;
    }

    public void setDayAnaerobicPoolSv(BigDecimal dayAnaerobicPoolSv) {
        this.dayAnaerobicPoolSv = dayAnaerobicPoolSv;
    }
    public SewPot dayAnaerobicPoolSv(BigDecimal dayAnaerobicPoolSv) {
        this.dayAnaerobicPoolSv = dayAnaerobicPoolSv;
        return this;
    }
    public BigDecimal getDayAnaerobicPoolMlss() {
        return dayAnaerobicPoolMlss;
    }

    public void setDayAnaerobicPoolMlss(BigDecimal dayAnaerobicPoolMlss) {
        this.dayAnaerobicPoolMlss = dayAnaerobicPoolMlss;
    }
    public SewPot dayAnaerobicPoolMlss(BigDecimal dayAnaerobicPoolMlss) {
        this.dayAnaerobicPoolMlss = dayAnaerobicPoolMlss;
        return this;
    }
    public BigDecimal getDayAnaerobicPoolTemper() {
        return dayAnaerobicPoolTemper;
    }

    public void setDayAnaerobicPoolTemper(BigDecimal dayAnaerobicPoolTemper) {
        this.dayAnaerobicPoolTemper = dayAnaerobicPoolTemper;
    }
    public SewPot dayAnaerobicPoolTemper(BigDecimal dayAnaerobicPoolTemper) {
        this.dayAnaerobicPoolTemper = dayAnaerobicPoolTemper;
        return this;
    }
    public BigDecimal getDayAnoxicPoolPh() {
        return dayAnoxicPoolPh;
    }

    public void setDayAnoxicPoolPh(BigDecimal dayAnoxicPoolPh) {
        this.dayAnoxicPoolPh = dayAnoxicPoolPh;
    }
    public SewPot dayAnoxicPoolPh(BigDecimal dayAnoxicPoolPh) {
        this.dayAnoxicPoolPh = dayAnoxicPoolPh;
        return this;
    }
    public BigDecimal getDayAnoxicPoolOrp() {
        return dayAnoxicPoolOrp;
    }

    public void setDayAnoxicPoolOrp(BigDecimal dayAnoxicPoolOrp) {
        this.dayAnoxicPoolOrp = dayAnoxicPoolOrp;
    }
    public SewPot dayAnoxicPoolOrp(BigDecimal dayAnoxicPoolOrp) {
        this.dayAnoxicPoolOrp = dayAnoxicPoolOrp;
        return this;
    }
    public BigDecimal getDayAnoxicPoolDo() {
        return dayAnoxicPoolDo;
    }

    public void setDayAnoxicPoolDo(BigDecimal dayAnoxicPoolDo) {
        this.dayAnoxicPoolDo = dayAnoxicPoolDo;
    }
    public SewPot dayAnoxicPoolDo(BigDecimal dayAnoxicPoolDo) {
        this.dayAnoxicPoolDo = dayAnoxicPoolDo;
        return this;
    }
    public BigDecimal getDayAnoxicPoolSour() {
        return dayAnoxicPoolSour;
    }

    public void setDayAnoxicPoolSour(BigDecimal dayAnoxicPoolSour) {
        this.dayAnoxicPoolSour = dayAnoxicPoolSour;
    }
    public SewPot dayAnoxicPoolSour(BigDecimal dayAnoxicPoolSour) {
        this.dayAnoxicPoolSour = dayAnoxicPoolSour;
        return this;
    }
    public BigDecimal getDayAnoxicPoolSv() {
        return dayAnoxicPoolSv;
    }

    public void setDayAnoxicPoolSv(BigDecimal dayAnoxicPoolSv) {
        this.dayAnoxicPoolSv = dayAnoxicPoolSv;
    }
    public SewPot dayAnoxicPoolSv(BigDecimal dayAnoxicPoolSv) {
        this.dayAnoxicPoolSv = dayAnoxicPoolSv;
        return this;
    }
    public BigDecimal getDayAnoxicPoolMlss() {
        return dayAnoxicPoolMlss;
    }

    public void setDayAnoxicPoolMlss(BigDecimal dayAnoxicPoolMlss) {
        this.dayAnoxicPoolMlss = dayAnoxicPoolMlss;
    }
    public SewPot dayAnoxicPoolMlss(BigDecimal dayAnoxicPoolMlss) {
        this.dayAnoxicPoolMlss = dayAnoxicPoolMlss;
        return this;
    }
    public BigDecimal getDayAnoxicPoolTemper() {
        return dayAnoxicPoolTemper;
    }

    public void setDayAnoxicPoolTemper(BigDecimal dayAnoxicPoolTemper) {
        this.dayAnoxicPoolTemper = dayAnoxicPoolTemper;
    }
    public SewPot dayAnoxicPoolTemper(BigDecimal dayAnoxicPoolTemper) {
        this.dayAnoxicPoolTemper = dayAnoxicPoolTemper;
        return this;
    }
    public BigDecimal getDayAerobicPoolPh() {
        return dayAerobicPoolPh;
    }

    public void setDayAerobicPoolPh(BigDecimal dayAerobicPoolPh) {
        this.dayAerobicPoolPh = dayAerobicPoolPh;
    }
    public SewPot dayAerobicPoolPh(BigDecimal dayAerobicPoolPh) {
        this.dayAerobicPoolPh = dayAerobicPoolPh;
        return this;
    }
    public BigDecimal getDayAerobicPoolOrp() {
        return dayAerobicPoolOrp;
    }

    public void setDayAerobicPoolOrp(BigDecimal dayAerobicPoolOrp) {
        this.dayAerobicPoolOrp = dayAerobicPoolOrp;
    }
    public SewPot dayAerobicPoolOrp(BigDecimal dayAerobicPoolOrp) {
        this.dayAerobicPoolOrp = dayAerobicPoolOrp;
        return this;
    }
    public BigDecimal getDayAerobicPoolDo() {
        return dayAerobicPoolDo;
    }

    public void setDayAerobicPoolDo(BigDecimal dayAerobicPoolDo) {
        this.dayAerobicPoolDo = dayAerobicPoolDo;
    }
    public SewPot dayAerobicPoolDo(BigDecimal dayAerobicPoolDo) {
        this.dayAerobicPoolDo = dayAerobicPoolDo;
        return this;
    }
    public BigDecimal getDayAerobicPoolSour() {
        return dayAerobicPoolSour;
    }

    public void setDayAerobicPoolSour(BigDecimal dayAerobicPoolSour) {
        this.dayAerobicPoolSour = dayAerobicPoolSour;
    }
    public SewPot dayAerobicPoolSour(BigDecimal dayAerobicPoolSour) {
        this.dayAerobicPoolSour = dayAerobicPoolSour;
        return this;
    }
    public BigDecimal getDayAerobicPoolSv() {
        return dayAerobicPoolSv;
    }

    public void setDayAerobicPoolSv(BigDecimal dayAerobicPoolSv) {
        this.dayAerobicPoolSv = dayAerobicPoolSv;
    }
    public SewPot dayAerobicPoolSv(BigDecimal dayAerobicPoolSv) {
        this.dayAerobicPoolSv = dayAerobicPoolSv;
        return this;
    }
    public BigDecimal getDayAerobicPoolMlss() {
        return dayAerobicPoolMlss;
    }

    public void setDayAerobicPoolMlss(BigDecimal dayAerobicPoolMlss) {
        this.dayAerobicPoolMlss = dayAerobicPoolMlss;
    }
    public SewPot dayAerobicPoolMlss(BigDecimal dayAerobicPoolMlss) {
        this.dayAerobicPoolMlss = dayAerobicPoolMlss;
        return this;
    }

    public BigDecimal getDayAerobicPoolMlvss() {
        return dayAerobicPoolMlvss;
    }

    public void setDayAerobicPoolMlvss(BigDecimal dayAerobicPoolMlvss) {
        this.dayAerobicPoolMlvss = dayAerobicPoolMlvss;
    }
    public SewPot dayAerobicPoolMlvss(BigDecimal dayAerobicPoolMlvss) {
        this.dayAerobicPoolMlvss = dayAerobicPoolMlvss;
        return this;
    }

    public BigDecimal getDayAerobicPoolSvi() {
        return dayAerobicPoolSvi;
    }

    public void setDayAerobicPoolSvi(BigDecimal dayAerobicPoolSvi) {
        this.dayAerobicPoolSvi = dayAerobicPoolSvi;
    }
    public SewPot dayAerobicPoolSvi(BigDecimal dayAerobicPoolSvi) {
        this.dayAerobicPoolSvi = dayAerobicPoolSvi;
        return this;
    }

    public BigDecimal getDayAerobicPoolTemper() {
        return dayAerobicPoolTemper;
    }

    public void setDayAerobicPoolTemper(BigDecimal dayAerobicPoolTemper) {
        this.dayAerobicPoolTemper = dayAerobicPoolTemper;
    }
    public SewPot dayAerobicPoolTemper(BigDecimal dayAerobicPoolTemper) {
        this.dayAerobicPoolTemper = dayAerobicPoolTemper;
        return this;
    }

    public Instant getDayTime() {
        return dayTime;
    }

    public void setDayTime(Instant dayTime) {
        this.dayTime = dayTime;
    }
    public SewPot dayTime(Instant dayTime) {
        this.dayTime = dayTime;
        return this;
    }

    public Long getCraftId() {
        return craftId;
    }

    public void setCraftId(Long craftId) {
        this.craftId = craftId;
    }
    public SewPot craftId(Long craftId) {
        this.craftId = craftId;
        return this;
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
            ", dayInPh='" + getDayInPh() + "'" +
            ", dayOutPh='" + getDayOutPh() + "'" +
            ", dayFirstMud='" + getDayFirstMud() + "'" +
            ", daySecondMud='" + getDaySecondMud() + "'" +
            ", dayReflux='" + getDayReflux() + "'" +
            ", dayCarAdd='" + getDayCarAdd() + "'" +
            ", dayAnaerobicPoolPh='" + getDayAnaerobicPoolPh() + "'" +
            ", dayAnaerobicPoolOrp='" + getDayAnaerobicPoolOrp() + "'" +
            ", dayAnaerobicPoolDo='" + getDayAnaerobicPoolDo() + "'" +
            ", dayAnaerobicPoolSour='" + getDayAnaerobicPoolSour() + "'" +
            ", dayAnaerobicPoolSv='" + getDayAnaerobicPoolSv() + "'" +
            ", dayAnaerobicPoolMlss='" + getDayAnaerobicPoolMlss() + "'" +
            ", dayAnaerobicPoolTemper='" + getDayAnaerobicPoolTemper() + "'" +
            ", dayAnoxicPoolPh='" + getDayAnoxicPoolPh() + "'" +
            ", dayAnoxicPoolOrp='" + getDayAnoxicPoolOrp() + "'" +
            ", dayAnoxicPoolDo='" + getDayAnoxicPoolDo() + "'" +
            ", dayAnoxicPoolSour='" + getDayAnoxicPoolSour() + "'" +
            ", dayAnoxicPoolSv='" + getDayAnoxicPoolSv() + "'" +
            ", dayAnoxicPoolMlss='" + getDayAnoxicPoolMlss() + "'" +
            ", dayAnoxicPoolTemper='" + getDayAnoxicPoolTemper() + "'" +
            ", dayAerobicPoolPh='" + getDayAerobicPoolPh() + "'" +
            ", dayAerobicPoolOrp='" + getDayAerobicPoolOrp() + "'" +
            ", dayAerobicPoolDo='" + getDayAerobicPoolDo() + "'" +
            ", dayAerobicPoolSour='" + getDayAerobicPoolSour() + "'" +
            ", dayAerobicPoolSv='" + getDayAerobicPoolSv() + "'" +
            ", dayAerobicPoolMlss='" + getDayAerobicPoolMlss() + "'" +
            ", dayAerobicPoolMlvss='" + getDayAerobicPoolMlvss() + "'" +
            ", dayAerobicPoolSvi='" + getDayAerobicPoolSvi() + "'" +
            ", dayAerobicPoolTemper='" + getDayAerobicPoolTemper() + "'" +
            ", dayTime='" + getDayTime() + "'" +
            ", craftId='" + getCraftId() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
