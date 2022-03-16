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
 * 仪表数据
 */
@ApiModel(description = "仪表数据")
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
    @ApiModelProperty(value = "单据号", required = true)
    @Column(name = "document_code", nullable = false)
    private String documentCode;

    /**
     * 工艺类型编码
     */
    @ApiModelProperty(value = "工艺类型编码", required = true)
    @Column(name = "process_type_code", nullable = false)
    private String processTypeCode;

    /**
     * 工艺类型名称
     */
    @ApiModelProperty(value = "工艺类型名称", required = true)
    @Column(name = "process_type_name", nullable = false)
    private String processTypeName;

    /**
     * 日均规模（m3/d）
     */
    @ApiModelProperty(value = "日均规模（m3/d）", required = true)
    @Column(name = "daily_scale", precision = 21, scale = 2, nullable = false)
    private BigDecimal dailyScale;

    /**
     * 本月运行天数
     */
    @ApiModelProperty(value = "本月运行天数", required = true)
    @Column(name = "operating_days", nullable = false)
    private Integer operatingDays;

    /**
     * 进水总氮（mg/L）
     */
    @ApiModelProperty(value = "进水流量（mg/L）", required = true)
    @Column(name = "in_flow", precision = 21, scale = 2, nullable = false)
    private BigDecimal inFlow;

    /**
     * 进水氨氮（mg/L）
     */
    @ApiModelProperty(value = "进水氨氮（mg/L）", required = true)
    @Column(name = "in_ammonia", precision = 21, scale = 2, nullable = false)
    private BigDecimal inAmmonia;

    /**
     * 进水COD（mg/L）
     */
    @ApiModelProperty(value = "进水COD（mg/L）", required = true)
    @Column(name = "in_cod", precision = 21, scale = 2, nullable = false)
    private BigDecimal inCod;

    /**
     * 进水TN（mg/L）
     */
    @ApiModelProperty(value = "进水TN（mg/L）", required = true)
    @Column(name = "in_tn", precision = 21, scale = 2, nullable = false)
    private BigDecimal inTn;

    /**
     * 进水Tp（mg/L）
     */
    @ApiModelProperty(value = "进水Tp（mg/L）")
    @Column(name = "in_tp", precision = 21, scale = 2)
    private BigDecimal inTp;

    /**
     * 进水SS（mg/L）
     */
    @ApiModelProperty(value = "进水Ss（mg/L）")
    @Column(name = "in_ss", precision = 21, scale = 2)
    private BigDecimal inSs;

    /**
     * 出水总氮（mg/L）
     */
    @ApiModelProperty(value = "出水流量（mg/L）", required = true)
    @Column(name = "out_flow", precision = 21, scale = 2, nullable = false)
    private BigDecimal outFlow;

    /**
     * 出水氨氮（mg/L）
     */
    @ApiModelProperty(value = "出水氨氮（mg/L）", required = true)
    @Column(name = "out_ammonia", precision = 21, scale = 2, nullable = false)
    private BigDecimal outAmmonia;

    /**
     * 出水COD（mg/L）
     */
    @ApiModelProperty(value = "出水COD（mg/L）", required = true)
    @Column(name = "out_cod", precision = 21, scale = 2, nullable = false)
    private BigDecimal outCod;

    /**
     * 出水TN（mg/L）
     */
    @ApiModelProperty(value = "出水TN（mg/L）", required = true)
    @Column(name = "out_tn", precision = 21, scale = 2, nullable = false)
    private BigDecimal outTn;

    /**
     * 出水Tp（mg/L）
     */
    @ApiModelProperty(value = "出水Tp（mg/L）")
    @Column(name = "out_tp", precision = 21, scale = 2)
    private BigDecimal outTp;

    /**
     * 出水Ss（mg/L）
     */
    @ApiModelProperty(value = "出水Ss（mg/L）")
    @Column(name = "out_ss", precision = 21, scale = 2)
    private BigDecimal outSs;

    /**
     * 缺氧池（mg/L）
     */
    @ApiModelProperty(value = "缺氧池（mg/L）")
    @Column(name = "anoxic_pool_do", precision = 21, scale = 2)
    private BigDecimal anoxicPoolDo;

    /**
     * 好氧池（mg/L）
     */
    @ApiModelProperty(value = "好氧池（mg/L）")
    @Column(name = "aerobic_pool_do", precision = 21, scale = 2)
    private BigDecimal aerobicPoolDo;

    /**
     * 缺氧池出口硝酸盐（mg/L）
     */
    @ApiModelProperty(value = "缺氧池出口硝酸盐（mg/L）")
    @Column(name = "anoxic_pool_do_out_nit", precision = 21, scale = 2)
    private BigDecimal anoxicPoolDoOutNit;

    /**
     * 亚硝酸盐（mg/L）
     */
    @ApiModelProperty(value = "好氧池出口亚硝酸盐（mg/L）")
    @Column(name = "aerobic_pool_nit", precision = 21, scale = 2)
    private BigDecimal aerobicPoolNit;

    /**
     * 日报表时间 yyyy/MM/dd HH:mm:ss
     */
    @ApiModelProperty(value = "日报表时间", required = true)
    @Column(name = "day_time", nullable = false)
    private Instant dayTime;

    /**
     * 所属工艺段
     */
    @ApiModelProperty(value = "所属工艺段", required = true)
    @Column(name = "craft_code", nullable = false)
    private String craftCode;


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

    public BigDecimal getInFlow() {
        return this.inFlow;
    }

    public SewProcess inFlow(BigDecimal inFlow) {
        this.inFlow = inFlow;
        return this;
    }

    public void setInFlow(BigDecimal inFlow) {
        this.inFlow = inFlow;
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

    public BigDecimal getInTn() {
        return this.inTn;
    }

    public SewProcess inTn(BigDecimal inTn) {
        this.inTn = inTn;
        return this;
    }

    public void setInTn(BigDecimal inTn) {
        this.inTn = inTn;
    }

    public BigDecimal getInTp() {
        return this.inTp;
    }

    public SewProcess inTp(BigDecimal inTp) {
        this.inTp = inTp;
        return this;
    }

    public void setInTp(BigDecimal inTp) {
        this.inTp = inTp;
    }

    public BigDecimal getOutFlow() {
        return this.outFlow;
    }

    public SewProcess outFlow(BigDecimal outFlow) {
        this.outFlow = outFlow;
        return this;
    }

    public void setOutFlow(BigDecimal outFlow) {
        this.outFlow = outFlow;
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

    public BigDecimal getOutTn() {
        return this.outTn;
    }

    public SewProcess outTn(BigDecimal outTn) {
        this.outTn = outTn;
        return this;
    }

    public void setOutTn(BigDecimal outTn) {
        this.outTn = outTn;
    }

    public BigDecimal getOutTp() {
        return this.outTp;
    }

    public SewProcess outTp(BigDecimal outTp) {
        this.outTp = outTp;
        return this;
    }

    public void setOutTp(BigDecimal outTp) {
        this.outTp = outTp;
    }

    public BigDecimal getInSs() {
        return inSs;
    }
    public SewProcess inSs(BigDecimal inSs) {
        this.inSs = inSs;
        return this;
    }
    public void setInSs(BigDecimal inSs) {
        this.inSs = inSs;
    }

    public BigDecimal getOutSs() {
        return outSs;
    }
    public SewProcess outSs(BigDecimal outSs) {
        this.outSs = outSs;
        return this;
    }
    public void setOutSs(BigDecimal outSs) {
        this.outSs = outSs;
    }

    public BigDecimal getAnoxicPoolDo() {
        return anoxicPoolDo;
    }
    public SewProcess anoxicPoolDo(BigDecimal anoxicPoolDo) {
        this.anoxicPoolDo = anoxicPoolDo;
        return this;
    }

    public void setAnoxicPoolDo(BigDecimal anoxicPoolDo) {
        this.anoxicPoolDo = anoxicPoolDo;
    }

    public BigDecimal getAerobicPoolDo() {
        return aerobicPoolDo;
    }
    public SewProcess aerobicPoolDo(BigDecimal aerobicPoolDo) {
        this.aerobicPoolDo = aerobicPoolDo;
        return this;
    }
    public void setAerobicPoolDo(BigDecimal aerobicPoolDo) {
        this.aerobicPoolDo = aerobicPoolDo;
    }

    public BigDecimal getAnoxicPoolDoOutNit() {
        return anoxicPoolDoOutNit;
    }
    public SewProcess anoxicPoolDoOutNit(BigDecimal anoxicPoolDoOutNit) {
        this.anoxicPoolDoOutNit = anoxicPoolDoOutNit;
        return this;
    }
    public void setAnoxicPoolDoOutNit(BigDecimal anoxicPoolDoOutNit) {
        this.anoxicPoolDoOutNit = anoxicPoolDoOutNit;
    }

    public BigDecimal getAerobicPoolNit() {
        return aerobicPoolNit;
    }
    public SewProcess aerobicPoolNit(BigDecimal aerobicPoolNit) {
        this.aerobicPoolNit = aerobicPoolNit;
        return this;
    }
    public void setAerobicPoolNit(BigDecimal aerobicPoolNit) {
        this.aerobicPoolNit = aerobicPoolNit;
    }

    public Instant getDayTime() {
        return dayTime;
    }
    public void setDayTime(Instant dayTime) {
        this.dayTime = dayTime;
    }
    public SewProcess dayTime(Instant dayTime) {
        this.dayTime = dayTime;
        return this;
    }

    public String getCraftCode() {
        return craftCode;
    }

    public void setCraftCode(String craftCode) {
        this.craftCode = craftCode;
    }
    public SewProcess craftCode(String craftCode) {
        this.craftCode = craftCode;
        return this;
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
            ", inFlow=" + getInFlow() +
            ", inAmmonia=" + getInAmmonia() +
            ", inCod=" + getInCod() +
            ", inTn=" + getInTn() +
            ", inTp=" + getInTp() +
            ", inSs=" + getInSs() +
            ", outFlow=" + getOutFlow() +
            ", outAmmonia=" + getOutAmmonia() +
            ", outCod=" + getOutCod() +
            ", outTn=" + getOutTn() +
            ", outTp=" + getOutTp() +
            ", outSs=" + getOutSs() +
            ", anoxicPoolDo=" + getAnoxicPoolDo() +
            ", aerobicPoolDo=" + getAerobicPoolDo() +
            ", anoxicPoolDoOutNit=" + getAnoxicPoolDoOutNit() +
            ", aerobicPoolNit=" + getAerobicPoolNit() +
            ", dayTime=" + getDayTime() +
            ", craftCode=" + getCraftCode() +
            "}";
    }
}
