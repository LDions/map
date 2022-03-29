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
     * 所属工艺ID
     */
    @ApiModelProperty(value = "工艺ID", required = true)
    @Column(name = "craft_id", nullable = false)
    private Long craftId;

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
     * 生化池-厌氧池PH1
     */
    @ApiModelProperty(value = "生化池-厌氧池PH1", required = true)
    @Column(name = "day_anaerobic_pool_ph", nullable = false)
    private BigDecimal dayAnaerobicPoolPh;

    /**
     * 生化池-厌氧池PH2
     */
    @ApiModelProperty(value = "生化池-厌氧池PH2", required = true)
    @Column(name = "day_anaerobic_pool_ph_second", nullable = false)
    private BigDecimal dayAnaerobicPoolPhSecond;

    /**
     * 生化池-厌氧池ORP1
     */
    @ApiModelProperty(value = "生化池-厌氧池ORP1", required = true)
    @Column(name = "day_anaerobic_pool_orp", nullable = false)
    private BigDecimal dayAnaerobicPoolOrp;

    /**
     * 生化池-厌氧池ORP2
     */
    @ApiModelProperty(value = "生化池-厌氧池ORP2", required = true)
    @Column(name = "day_anaerobic_pool_orp_second", nullable = false)
    private BigDecimal dayAnaerobicPoolOrpSecond;

    /**
     * 生化池-厌氧池DO1
     */
    @ApiModelProperty(value = "生化池-厌氧池DO1", required = true)
    @Column(name = "day_anaerobic_pool_do", nullable = false)
    private BigDecimal dayAnaerobicPoolDo;

    /**
     * 生化池-厌氧池DO2
     */
    @ApiModelProperty(value = "生化池-厌氧池DO2", required = true)
    @Column(name = "day_anaerobic_pool_do_second", nullable = false)
    private BigDecimal dayAnaerobicPoolDoSecond;

    /**
     * 生化池-厌氧池SOUR1
     */
    @ApiModelProperty(value = "生化池-厌氧池SOUR1", required = true)
    @Column(name = "day_anaerobic_pool_sour", nullable = false)
    private BigDecimal dayAnaerobicPoolSour;

    /**
     * 生化池-厌氧池SOUR2
     */
    @ApiModelProperty(value = "生化池-厌氧池SOUR2", required = true)
    @Column(name = "day_anaerobic_pool_sour_second", nullable = false)
    private BigDecimal dayAnaerobicPoolSourSecond;

    /**
     * 生化池-厌氧池SV1
     */
    @ApiModelProperty(value = "生化池-厌氧池SV1", required = true)
    @Column(name = "day_anaerobic_pool_sv", nullable = false)
    private BigDecimal dayAnaerobicPoolSv;

    /**
     * 生化池-厌氧池SV2
     */
    @ApiModelProperty(value = "生化池-厌氧池SV2", required = true)
    @Column(name = "day_anaerobic_pool_sv_second", nullable = false)
    private BigDecimal dayAnaerobicPoolSvSecond;

    /**
     * 生化池-厌氧池MLSS1
     */
    @ApiModelProperty(value = "生化池-厌氧池MLSS1", required = true)
    @Column(name = "day_anaerobic_pool_mlss", nullable = false)
    private BigDecimal dayAnaerobicPoolMlss;

    /**
     * 生化池-厌氧池MLSS2
     */
    @ApiModelProperty(value = "生化池-厌氧池MLSS2", required = true)
    @Column(name = "day_anaerobic_pool_mlss_second", nullable = false)
    private BigDecimal dayAnaerobicPoolMlssSecond;

    /**
     * 生化池-厌氧池MLVSS1
     */
    @ApiModelProperty(value = "生化池-厌氧池MLVSS1", required = true)
    @Column(name = "day_anaerobic_pool_mlvss", nullable = false)
    private BigDecimal dayAnaerobicPoolMlvss;

    /**
     * 生化池-厌氧池MLVSS2
     */
    @ApiModelProperty(value = "生化池-厌氧池MLVSS2", required = true)
    @Column(name = "day_anaerobic_pool_mlvss_second", nullable = false)
    private BigDecimal dayAnaerobicPoolMlvssSecond;

    /**
     * 生化池-厌氧池温度1
     */
    @ApiModelProperty(value = "生化池-厌氧池温度1", required = true)
    @Column(name = "day_anaerobic_pool_temper", nullable = false)
    private BigDecimal dayAnaerobicPoolTemper;

    /**
     * 生化池-厌氧池温度2
     */
    @ApiModelProperty(value = "生化池-厌氧池温度2", required = true)
    @Column(name = "day_anaerobic_pool_temper_second", nullable = false)
    private BigDecimal dayAnaerobicPoolTemperSecond;

    /**
     * 生化池-缺氧池PH1
     */
    @ApiModelProperty(value = "生化池-缺氧池PH1", required = true)
    @Column(name = "day_anoxic_pool_ph", nullable = false)
    private BigDecimal dayAnoxicPoolPh;

    /**
     * 生化池-缺氧池PH2
     */
    @ApiModelProperty(value = "生化池-缺氧池PH2", required = true)
    @Column(name = "day_anoxic_pool_ph_second", nullable = false)
    private BigDecimal dayAnoxicPoolPhSecond;

    /**
     * 生化池-缺氧池ORP1
     */
    @ApiModelProperty(value = "生化池-缺氧池ORP1", required = true)
    @Column(name = "day_anoxic_pool_orp", nullable = false)
    private BigDecimal dayAnoxicPoolOrp;

    /**
     * 生化池-缺氧池ORP2
     */
    @ApiModelProperty(value = "生化池-缺氧池ORP2", required = true)
    @Column(name = "day_anoxic_pool_orp_second", nullable = false)
    private BigDecimal dayAnoxicPoolOrpSecond;

    /**
     * 生化池-缺氧池DO1
     */
    @ApiModelProperty(value = "生化池-缺氧池DO1", required = true)
    @Column(name = "day_anoxic_pool_do", nullable = false)
    private BigDecimal dayAnoxicPoolDo;

    /**
     * 生化池-缺氧池DO2
     */
    @ApiModelProperty(value = "生化池-缺氧池DO2", required = true)
    @Column(name = "day_anoxic_pool_do_second", nullable = false)
    private BigDecimal dayAnoxicPoolDoSecond;

    /**
     * 生化池-缺氧池SOUR1
     */
    @ApiModelProperty(value = "生化池-缺氧池SOUR1", required = true)
    @Column(name = "day_anoxic_pool_sour", nullable = false)
    private BigDecimal dayAnoxicPoolSour;

    /**
     * 生化池-缺氧池SOUR2
     */
    @ApiModelProperty(value = "生化池-缺氧池SOUR2", required = true)
    @Column(name = "day_anoxic_pool_sour_second", nullable = false)
    private BigDecimal dayAnoxicPoolSourSecond;

    /**
     * 生化池-缺氧池SV1
     */
    @ApiModelProperty(value = "生化池-缺氧池SV1", required = true)
    @Column(name = "day_anoxic_pool_sv", nullable = false)
    private BigDecimal dayAnoxicPoolSv;

    /**
     * 生化池-缺氧池SV2
     */
    @ApiModelProperty(value = "生化池-缺氧池SV2", required = true)
    @Column(name = "day_anoxic_pool_sv_second", nullable = false)
    private BigDecimal dayAnoxicPoolSvSecond;

    /**
     * 生化池-缺氧池MLSS1
     */
    @ApiModelProperty(value = "生化池-缺氧池MLSS1", required = true)
    @Column(name = "day_anoxic_pool_mlss", nullable = false)
    private BigDecimal dayAnoxicPoolMlss;

    /**
     * 生化池-缺氧池MLSS2
     */
    @ApiModelProperty(value = "生化池-缺氧池MLSS2", required = true)
    @Column(name = "day_anoxic_pool_mlss_second", nullable = false)
    private BigDecimal dayAnoxicPoolMlssSecond;

    /**
     * 生化池-缺氧池MLVSS1
     */
    @ApiModelProperty(value = "生化池-缺氧池MLVSS1", required = true)
    @Column(name = "day_anoxic_pool_mlvss", nullable = false)
    private BigDecimal dayAnoxicPoolMlvss;

    /**
     * 生化池-缺氧池MLVSS2
     */
    @ApiModelProperty(value = "生化池-缺氧池MLVSS2", required = true)
    @Column(name = "day_anoxic_pool_mlvss_second", nullable = false)
    private BigDecimal dayAnoxicPoolMlvssSecond;

    /**
     * 生化池-缺氧池温度1
     */
    @ApiModelProperty(value = "生化池-缺氧池温度1", required = true)
    @Column(name = "day_anoxic_pool_temper", nullable = false)
    private BigDecimal dayAnoxicPoolTemper;

    /**
     * 生化池-缺氧池温度2
     */
    @ApiModelProperty(value = "生化池-缺氧池温度2", required = true)
    @Column(name = "day_anoxic_pool_temper_second", nullable = false)
    private BigDecimal dayAnoxicPoolTemperSecond;

    /**
     * 生化池-好氧池PH1
     */
    @ApiModelProperty(value = "生化池-好氧池PH1", required = true)
    @Column(name = "day_aerobic_pool_ph", nullable = false)
    private BigDecimal dayAerobicPoolPh;

    /**
     * 生化池-好氧池PH2
     */
    @ApiModelProperty(value = "生化池-好氧池PH2", required = true)
    @Column(name = "day_aerobic_pool_ph_second", nullable = false)
    private BigDecimal dayAerobicPoolPhSecond;

    /**
     * 生化池-好氧池ORP1
     */
    @ApiModelProperty(value = "生化池-好氧池ORP1", required = true)
    @Column(name = "day_aerobic_pool_orp", nullable = false)
    private BigDecimal dayAerobicPoolOrp;

    /**
     * 生化池-好氧池ORP2
     */
    @ApiModelProperty(value = "生化池-好氧池ORP2", required = true)
    @Column(name = "day_aerobic_pool_orp_second", nullable = false)
    private BigDecimal dayAerobicPoolOrpSecond;

    /**
     * 生化池-好氧池DO1
     */
    @ApiModelProperty(value = "生化池-好氧池DO1", required = true)
    @Column(name = "day_aerobic_pool_do", nullable = false)
    private BigDecimal dayAerobicPoolDo;

    /**
     * 生化池-好氧池DO2
     */
    @ApiModelProperty(value = "生化池-好氧池DO2", required = true)
    @Column(name = "day_aerobic_pool_do_second", nullable = false)
    private BigDecimal dayAerobicPoolDoSecond;

    /**
     * 生化池-好氧池SOUR1
     */
    @ApiModelProperty(value = "生化池-好氧池SOUR1", required = true)
    @Column(name = "day_aerobic_pool_sour", nullable = false)
    private BigDecimal dayAerobicPoolSour;

    /**
     * 生化池-好氧池SOUR2
     */
    @ApiModelProperty(value = "生化池-好氧池SOUR2", required = true)
    @Column(name = "day_aerobic_pool_sour_second", nullable = false)
    private BigDecimal dayAerobicPoolSourSecond;

    /**
     * 生化池-好氧池SV1
     */
    @ApiModelProperty(value = "生化池-好氧池SV1", required = true)
    @Column(name = "day_aerobic_pool_sv", nullable = false)
    private BigDecimal dayAerobicPoolSv;

    /**
     * 生化池-好氧池SV2
     */
    @ApiModelProperty(value = "生化池-好氧池SV2", required = true)
    @Column(name = "day_aerobic_pool_sv_second", nullable = false)
    private BigDecimal dayAerobicPoolSvSecond;

    /**
     * 生化池-好氧池MLSS1
     */
    @ApiModelProperty(value = "生化池-好氧池MLSS1", required = true)
    @Column(name = "day_aerobic_pool_mlss", nullable = false)
    private BigDecimal dayAerobicPoolMlss;

    /**
     * 生化池-好氧池MLSS2
     */
    @ApiModelProperty(value = "生化池-好氧池MLSS2", required = true)
    @Column(name = "day_aerobic_pool_mlss_second", nullable = false)
    private BigDecimal dayAerobicPoolMlssSecond;

    /**
     * 生化池-好氧池MLVSS1
     */
    @ApiModelProperty(value = "生化池-好氧池MLVSS1", required = true)
    @Column(name = "day_aerobic_pool_mlvss", nullable = false)
    private BigDecimal dayAerobicPoolMlvss;

    /**
     * 生化池-好氧池MLVSS2
     */
    @ApiModelProperty(value = "生化池-好氧池MLVSS2", required = true)
    @Column(name = "day_aerobic_pool_mlvss_second", nullable = false)
    private BigDecimal dayAerobicPoolMlvssSecond;

    /**
     * 生化池-好氧池SVI1
     */
    @ApiModelProperty(value = "生化池-好氧池SVI1", required = true)
    @Column(name = "day_aerobic_pool_svi", nullable = false)
    private BigDecimal dayAerobicPoolSvi;

    /**
     * 生化池-好氧池SVI2
     */
    @ApiModelProperty(value = "生化池-好氧池SVI2", required = true)
    @Column(name = "day_aerobic_pool_svi_second", nullable = false)
    private BigDecimal dayAerobicPoolSviSecond;

    /**
     * 生化池-好氧池温度1
     */
    @ApiModelProperty(value = "生化池-好氧池温度1", required = true)
    @Column(name = "day_aerobic_pool_temper", nullable = false)
    private BigDecimal dayAerobicPoolTemper;

    /**
     * 生化池-好氧池温度2
     */
    @ApiModelProperty(value = "生化池-好氧池温度2", required = true)
    @Column(name = "day_aerobic_pool_temper_second", nullable = false)
    private BigDecimal dayAerobicPoolTemperSecond;

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

    public String getPotCode() {
        return potCode;
    }

    public void setPotCode(String potCode) {
        this.potCode = potCode;
    }

    public String getCraftCode() {
        return craftCode;
    }

    public void setCraftCode(String craftCode) {
        this.craftCode = craftCode;
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


    public BigDecimal getDayAnaerobicPoolPhSecond() {
        return dayAnaerobicPoolPhSecond;
    }
    public SewPot dayAnaerobicPoolPhSecond(BigDecimal dayAnaerobicPoolPhSecond) {
        this.dayAnaerobicPoolPhSecond = dayAnaerobicPoolPhSecond;
        return this;
    }
    public void setDayAnaerobicPoolPhSecond(BigDecimal dayAnaerobicPoolPhSecond) {
        this.dayAnaerobicPoolPhSecond = dayAnaerobicPoolPhSecond;
    }

    public BigDecimal getDayAnaerobicPoolOrpSecond() {
        return dayAnaerobicPoolOrpSecond;
    }
    public SewPot dayAnaerobicPoolOrpSecond(BigDecimal dayAnaerobicPoolOrpSecond) {
        this.dayAnaerobicPoolOrpSecond = dayAnaerobicPoolOrpSecond;
        return this;
    }
    public void setDayAnaerobicPoolOrpSecond(BigDecimal dayAnaerobicPoolOrpSecond) {
        this.dayAnaerobicPoolOrpSecond = dayAnaerobicPoolOrpSecond;
    }

    public BigDecimal getDayAnaerobicPoolDoSecond() {
        return dayAnaerobicPoolDoSecond;
    }
    public SewPot dayAnaerobicPoolDoSecond(BigDecimal dayAnaerobicPoolDoSecond) {
        this.dayAnaerobicPoolDoSecond = dayAnaerobicPoolDoSecond;
        return this;
    }
    public void setDayAnaerobicPoolDoSecond(BigDecimal dayAnaerobicPoolDoSecond) {
        this.dayAnaerobicPoolDoSecond = dayAnaerobicPoolDoSecond;
    }

    public BigDecimal getDayAnaerobicPoolSourSecond() {
        return dayAnaerobicPoolSourSecond;
    }
    public SewPot dayAnaerobicPoolSourSecond(BigDecimal dayAnaerobicPoolSourSecond) {
        this.dayAnaerobicPoolSourSecond = dayAnaerobicPoolSourSecond;
        return this;
    }
    public void setDayAnaerobicPoolSourSecond(BigDecimal dayAnaerobicPoolSourSecond) {
        this.dayAnaerobicPoolSourSecond = dayAnaerobicPoolSourSecond;
    }

    public BigDecimal getDayAnaerobicPoolSvSecond() {
        return dayAnaerobicPoolSvSecond;
    }
    public SewPot dayAnaerobicPoolSvSecond(BigDecimal dayAnaerobicPoolSvSecond) {
        this.dayAnaerobicPoolSvSecond = dayAnaerobicPoolSvSecond;
        return this;
    }
    public void setDayAnaerobicPoolSvSecond(BigDecimal dayAnaerobicPoolSvSecond) {
        this.dayAnaerobicPoolSvSecond = dayAnaerobicPoolSvSecond;
    }

    public BigDecimal getDayAnaerobicPoolMlssSecond() {
        return dayAnaerobicPoolMlssSecond;
    }
    public SewPot dayAnaerobicPoolMlssSecond(BigDecimal dayAnaerobicPoolMlssSecond) {
        this.dayAnaerobicPoolMlssSecond = dayAnaerobicPoolMlssSecond;
        return this;
    }
    public void setDayAnaerobicPoolMlssSecond(BigDecimal dayAnaerobicPoolMlssSecond) {
        this.dayAnaerobicPoolMlssSecond = dayAnaerobicPoolMlssSecond;
    }

    public BigDecimal getDayAnaerobicPoolTemperSecond() {
        return dayAnaerobicPoolTemperSecond;
    }
    public SewPot dayAnaerobicPoolTemperSecond(BigDecimal dayAnaerobicPoolTemperSecond) {
        this.dayAnaerobicPoolTemperSecond = dayAnaerobicPoolTemperSecond;
        return this;
    }
    public void setDayAnaerobicPoolTemperSecond(BigDecimal dayAnaerobicPoolTemperSecond) {
        this.dayAnaerobicPoolTemperSecond = dayAnaerobicPoolTemperSecond;
    }

    public BigDecimal getDayAnoxicPoolPhSecond() {
        return dayAnoxicPoolPhSecond;
    }
    public SewPot dayAnoxicPoolPhSecond(BigDecimal dayAnoxicPoolPhSecond) {
        this.dayAnoxicPoolPhSecond = dayAnoxicPoolPhSecond;
        return this;
    }
    public void setDayAnoxicPoolPhSecond(BigDecimal dayAnoxicPoolPhSecond) {
        this.dayAnoxicPoolPhSecond = dayAnoxicPoolPhSecond;
    }

    public BigDecimal getDayAnoxicPoolOrpSecond() {
        return dayAnoxicPoolOrpSecond;
    }
    public SewPot dayAnoxicPoolOrpSecond(BigDecimal dayAnoxicPoolOrpSecond) {
        this.dayAnoxicPoolOrpSecond = dayAnoxicPoolOrpSecond;
        return this;
    }
    public void setDayAnoxicPoolOrpSecond(BigDecimal dayAnoxicPoolOrpSecond) {
        this.dayAnoxicPoolOrpSecond = dayAnoxicPoolOrpSecond;
    }

    public BigDecimal getDayAnoxicPoolDoSecond() {
        return dayAnoxicPoolDoSecond;
    }
    public SewPot dayAnoxicPoolDoSecond(BigDecimal dayAnoxicPoolDoSecond) {
        this.dayAnoxicPoolDoSecond = dayAnoxicPoolDoSecond;
        return this;
    }
    public void setDayAnoxicPoolDoSecond(BigDecimal dayAnoxicPoolDoSecond) {
        this.dayAnoxicPoolDoSecond = dayAnoxicPoolDoSecond;
    }

    public BigDecimal getDayAnoxicPoolSourSecond() {
        return dayAnoxicPoolSourSecond;
    }
    public SewPot dayAnoxicPoolSourSecond(BigDecimal dayAnoxicPoolSourSecond) {
        this.dayAnoxicPoolSourSecond = dayAnoxicPoolSourSecond;
        return this;
    }
    public void setDayAnoxicPoolSourSecond(BigDecimal dayAnoxicPoolSourSecond) {
        this.dayAnoxicPoolSourSecond = dayAnoxicPoolSourSecond;
    }

    public BigDecimal getDayAnoxicPoolSvSecond() {
        return dayAnoxicPoolSvSecond;
    }
    public SewPot dayAnoxicPoolSvSecond(BigDecimal dayAnoxicPoolSvSecond) {
        this.dayAnoxicPoolSvSecond = dayAnoxicPoolSvSecond;
        return this;
    }
    public void setDayAnoxicPoolSvSecond(BigDecimal dayAnoxicPoolSvSecond) {
        this.dayAnoxicPoolSvSecond = dayAnoxicPoolSvSecond;
    }

    public BigDecimal getDayAnoxicPoolMlssSecond() {
        return dayAnoxicPoolMlssSecond;
    }
    public SewPot dayAnoxicPoolMlssSecond(BigDecimal dayAnoxicPoolMlssSecond) {
        this.dayAnoxicPoolMlssSecond = dayAnoxicPoolMlssSecond;
        return this;
    }
    public void setDayAnoxicPoolMlssSecond(BigDecimal dayAnoxicPoolMlssSecond) {
        this.dayAnoxicPoolMlssSecond = dayAnoxicPoolMlssSecond;
    }

    public BigDecimal getDayAnoxicPoolTemperSecond() {
        return dayAnoxicPoolTemperSecond;
    }
    public SewPot dayAnoxicPoolTemperSecond(BigDecimal dayAnoxicPoolTemperSecond) {
        this.dayAnoxicPoolTemperSecond = dayAnoxicPoolTemperSecond;
        return this;
    }
    public void setDayAnoxicPoolTemperSecond(BigDecimal dayAnoxicPoolTemperSecond) {
        this.dayAnoxicPoolTemperSecond = dayAnoxicPoolTemperSecond;
    }

    public BigDecimal getDayAerobicPoolPhSecond() {
        return dayAerobicPoolPhSecond;
    }
    public SewPot dayAerobicPoolPhSecond(BigDecimal dayAerobicPoolPhSecond) {
        this.dayAerobicPoolPhSecond = dayAerobicPoolPhSecond;
        return this;
    }
    public void setDayAerobicPoolPhSecond(BigDecimal dayAerobicPoolPhSecond) {
        this.dayAerobicPoolPhSecond = dayAerobicPoolPhSecond;
    }

    public BigDecimal getDayAerobicPoolOrpSecond() {
        return dayAerobicPoolOrpSecond;
    }
    public SewPot dayAerobicPoolOrpSecond(BigDecimal dayAerobicPoolOrpSecond) {
        this.dayAerobicPoolOrpSecond = dayAerobicPoolOrpSecond;
        return this;
    }
    public void setDayAerobicPoolOrpSecond(BigDecimal dayAerobicPoolOrpSecond) {
        this.dayAerobicPoolOrpSecond = dayAerobicPoolOrpSecond;
    }

    public BigDecimal getDayAerobicPoolDoSecond() {
        return dayAerobicPoolDoSecond;
    }
    public SewPot dayAerobicPoolDoSecond(BigDecimal dayAerobicPoolDoSecond) {
        this.dayAerobicPoolDoSecond = dayAerobicPoolDoSecond;
        return this;
    }
    public void setDayAerobicPoolDoSecond(BigDecimal dayAerobicPoolDoSecond) {
        this.dayAerobicPoolDoSecond = dayAerobicPoolDoSecond;
    }

    public BigDecimal getDayAerobicPoolSourSecond() {
        return dayAerobicPoolSourSecond;
    }
    public SewPot dayAerobicPoolSourSecond(BigDecimal dayAerobicPoolSourSecond) {
        this.dayAerobicPoolSourSecond = dayAerobicPoolSourSecond;
        return this;
    }
    public void setDayAerobicPoolSourSecond(BigDecimal dayAerobicPoolSourSecond) {
        this.dayAerobicPoolSourSecond = dayAerobicPoolSourSecond;
    }

    public BigDecimal getDayAerobicPoolSvSecond() {
        return dayAerobicPoolSvSecond;
    }
    public SewPot dayAerobicPoolSvSecond(BigDecimal dayAerobicPoolSvSecond) {
        this.dayAerobicPoolSvSecond = dayAerobicPoolSvSecond;
        return this;
    }
    public void setDayAerobicPoolSvSecond(BigDecimal dayAerobicPoolSvSecond) {
        this.dayAerobicPoolSvSecond = dayAerobicPoolSvSecond;
    }

    public BigDecimal getDayAerobicPoolMlssSecond() {
        return dayAerobicPoolMlssSecond;
    }
    public SewPot dayAerobicPoolMlssSecond(BigDecimal dayAerobicPoolMlssSecond) {
        this.dayAerobicPoolMlssSecond = dayAerobicPoolMlssSecond;
        return this;
    }
    public void setDayAerobicPoolMlssSecond(BigDecimal dayAerobicPoolMlssSecond) {
        this.dayAerobicPoolMlssSecond = dayAerobicPoolMlssSecond;
    }

    public BigDecimal getDayAerobicPoolMlvssSecond() {
        return dayAerobicPoolMlvssSecond;
    }
    public SewPot dayAerobicPoolMlvssSecond(BigDecimal dayAerobicPoolMlvssSecond) {
        this.dayAerobicPoolMlvssSecond = dayAerobicPoolMlvssSecond;
        return this;
    }
    public void setDayAerobicPoolMlvssSecond(BigDecimal dayAerobicPoolMlvssSecond) {
        this.dayAerobicPoolMlvssSecond = dayAerobicPoolMlvssSecond;
    }

    public BigDecimal getDayAerobicPoolSviSecond() {
        return dayAerobicPoolSviSecond;
    }
    public SewPot dayAerobicPoolSviSecond(BigDecimal dayAerobicPoolSviSecond) {
        this.dayAerobicPoolSviSecond = dayAerobicPoolSviSecond;
        return this;
    }
    public void setDayAerobicPoolSviSecond(BigDecimal dayAerobicPoolSviSecond) {
        this.dayAerobicPoolSviSecond = dayAerobicPoolSviSecond;
    }

    public BigDecimal getDayAerobicPoolTemperSecond() {
        return dayAerobicPoolTemperSecond;
    }
    public SewPot dayAerobicPoolTemperSecond(BigDecimal dayAerobicPoolTemperSecond) {
        this.dayAerobicPoolTemperSecond = dayAerobicPoolTemperSecond;
        return this;
    }
    public void setDayAerobicPoolTemperSecond(BigDecimal dayAerobicPoolTemperSecond) {
        this.dayAerobicPoolTemperSecond = dayAerobicPoolTemperSecond;
    }

    public BigDecimal getDayAnaerobicPoolMlvss() {
        return dayAnaerobicPoolMlvss;
    }
    public SewPot dayAnaerobicPoolMlvss(BigDecimal dayAnaerobicPoolMlvss) {
        this.dayAnaerobicPoolMlvss = dayAnaerobicPoolMlvss;
        return this;
    }
    public void setDayAnaerobicPoolMlvss(BigDecimal dayAnaerobicPoolMlvss) {
        this.dayAnaerobicPoolMlvss = dayAnaerobicPoolMlvss;
    }

    public BigDecimal getDayAnaerobicPoolMlvssSecond() {
        return dayAnaerobicPoolMlvssSecond;
    }
    public SewPot dayAnaerobicPoolMlvssSecond(BigDecimal dayAnaerobicPoolMlvssSecond) {
        this.dayAnaerobicPoolMlvssSecond = dayAnaerobicPoolMlvssSecond;
        return this;
    }
    public void setDayAnaerobicPoolMlvssSecond(BigDecimal dayAnaerobicPoolMlvssSecond) {
        this.dayAnaerobicPoolMlvssSecond = dayAnaerobicPoolMlvssSecond;
    }

    public BigDecimal getDayAnoxicPoolMlvss() {
        return dayAnoxicPoolMlvss;
    }
    public SewPot dayAnoxicPoolMlvss(BigDecimal dayAnoxicPoolMlvss) {
        this.dayAnoxicPoolMlvss = dayAnoxicPoolMlvss;
        return this;
    }
    public void setDayAnoxicPoolMlvss(BigDecimal dayAnoxicPoolMlvss) {
        this.dayAnoxicPoolMlvss = dayAnoxicPoolMlvss;
    }

    public BigDecimal getDayAnoxicPoolMlvssSecond() {
        return dayAnoxicPoolMlvssSecond;
    }
    public SewPot dayAnoxicPoolMlvssSecond(BigDecimal dayAnoxicPoolMlvssSecond) {
        this.dayAnoxicPoolMlvssSecond = dayAnoxicPoolMlvssSecond;
        return this;
    }
    public void setDayAnoxicPoolMlvssSecond(BigDecimal dayAnoxicPoolMlvssSecond) {
        this.dayAnoxicPoolMlvssSecond = dayAnoxicPoolMlvssSecond;
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

    @Override
    public String toString() {
        return "SewPot{" +
            "id=" + id +
            ", craftId=" + craftId +
            ", potCode='" + potCode + '\'' +
            ", craftCode='" + craftCode + '\'' +
            ", dayInPh=" + dayInPh +
            ", dayOutPh=" + dayOutPh +
            ", dayFirstMud=" + dayFirstMud +
            ", daySecondMud=" + daySecondMud +
            ", dayReflux=" + dayReflux +
            ", dayCarAdd=" + dayCarAdd +
            ", dayAnaerobicPoolPh=" + dayAnaerobicPoolPh +
            ", dayAnaerobicPoolPhSecond=" + dayAnaerobicPoolPhSecond +
            ", dayAnaerobicPoolOrp=" + dayAnaerobicPoolOrp +
            ", dayAnaerobicPoolOrpSecond=" + dayAnaerobicPoolOrpSecond +
            ", dayAnaerobicPoolDo=" + dayAnaerobicPoolDo +
            ", dayAnaerobicPoolDoSecond=" + dayAnaerobicPoolDoSecond +
            ", dayAnaerobicPoolSour=" + dayAnaerobicPoolSour +
            ", dayAnaerobicPoolSourSecond=" + dayAnaerobicPoolSourSecond +
            ", dayAnaerobicPoolSv=" + dayAnaerobicPoolSv +
            ", dayAnaerobicPoolSvSecond=" + dayAnaerobicPoolSvSecond +
            ", dayAnaerobicPoolMlss=" + dayAnaerobicPoolMlss +
            ", dayAnaerobicPoolMlssSecond=" + dayAnaerobicPoolMlssSecond +
            ", dayAnaerobicPoolMlvss=" + dayAnaerobicPoolMlvss +
            ", dayAnaerobicPoolMlvssSecond=" + dayAnaerobicPoolMlvssSecond +
            ", dayAnaerobicPoolTemper=" + dayAnaerobicPoolTemper +
            ", dayAnaerobicPoolTemperSecond=" + dayAnaerobicPoolTemperSecond +
            ", dayAnoxicPoolPh=" + dayAnoxicPoolPh +
            ", dayAnoxicPoolPhSecond=" + dayAnoxicPoolPhSecond +
            ", dayAnoxicPoolOrp=" + dayAnoxicPoolOrp +
            ", dayAnoxicPoolOrpSecond=" + dayAnoxicPoolOrpSecond +
            ", dayAnoxicPoolDo=" + dayAnoxicPoolDo +
            ", dayAnoxicPoolDoSecond=" + dayAnoxicPoolDoSecond +
            ", dayAnoxicPoolSour=" + dayAnoxicPoolSour +
            ", dayAnoxicPoolSourSecond=" + dayAnoxicPoolSourSecond +
            ", dayAnoxicPoolSv=" + dayAnoxicPoolSv +
            ", dayAnoxicPoolSvSecond=" + dayAnoxicPoolSvSecond +
            ", dayAnoxicPoolMlss=" + dayAnoxicPoolMlss +
            ", dayAnoxicPoolMlssSecond=" + dayAnoxicPoolMlssSecond +
            ", dayAnoxicPoolMlvss=" + dayAnoxicPoolMlvss +
            ", dayAnoxicPoolMlvssSecond=" + dayAnoxicPoolMlvssSecond +
            ", dayAnoxicPoolTemper=" + dayAnoxicPoolTemper +
            ", dayAnoxicPoolTemperSecond=" + dayAnoxicPoolTemperSecond +
            ", dayAerobicPoolPh=" + dayAerobicPoolPh +
            ", dayAerobicPoolPhSecond=" + dayAerobicPoolPhSecond +
            ", dayAerobicPoolOrp=" + dayAerobicPoolOrp +
            ", dayAerobicPoolOrpSecond=" + dayAerobicPoolOrpSecond +
            ", dayAerobicPoolDo=" + dayAerobicPoolDo +
            ", dayAerobicPoolDoSecond=" + dayAerobicPoolDoSecond +
            ", dayAerobicPoolSour=" + dayAerobicPoolSour +
            ", dayAerobicPoolSourSecond=" + dayAerobicPoolSourSecond +
            ", dayAerobicPoolSv=" + dayAerobicPoolSv +
            ", dayAerobicPoolSvSecond=" + dayAerobicPoolSvSecond +
            ", dayAerobicPoolMlss=" + dayAerobicPoolMlss +
            ", dayAerobicPoolMlssSecond=" + dayAerobicPoolMlssSecond +
            ", dayAerobicPoolMlvss=" + dayAerobicPoolMlvss +
            ", dayAerobicPoolMlvssSecond=" + dayAerobicPoolMlvssSecond +
            ", dayAerobicPoolSvi=" + dayAerobicPoolSvi +
            ", dayAerobicPoolSviSecond=" + dayAerobicPoolSviSecond +
            ", dayAerobicPoolTemper=" + dayAerobicPoolTemper +
            ", dayAerobicPoolTemperSecond=" + dayAerobicPoolTemperSecond +
            ", dayTime=" + dayTime +
            ", status=" + status +
            ", plateStatus=" + plateStatus +
            '}';
    }
}
