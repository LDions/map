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
     * 仪表编码
     */
    @ApiModelProperty(value = "仪表编码")
    @Column(name = "process_code")
    private String processCode;

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
     * 好氧池（mg/L）
     */
    @ApiModelProperty(value = "好氧池1Do（mg/L）")
    @Column(name = "aerobic_pool_do", precision = 21, scale = 2)
    private BigDecimal aerobicPoolDo;

    /**
     * 缺氧池（mg/L）
     */
    @ApiModelProperty(value = "好氧池2Do（mg/L）")
    @Column(name = "aerobic_pool_do_second", precision = 21, scale = 2)
    private BigDecimal aerobicPoolDoSecond;

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
     * 时间 yyyy/MM/dd HH:mm:ss
     */
    @ApiModelProperty(value = "所属工艺ID", required = true)
    @Column(name = "day_time", nullable = false)
    private Instant dayTime;

    /**
     * 所属工艺ID
     */
    @ApiModelProperty(value = "工艺ID", required = true)
    @Column(name = "craft_id", nullable = false)
    private Long craftId;

    /**
     * 所属工艺编码
     */
    @ApiModelProperty(value = "工艺编码")
    @Column(name = "craft_code")
    private String craftCode;

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

    public SewProcess id(Long id) {
        this.id = id;
        return this;
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


    public BigDecimal getAerobicPoolDoSecond() {
        return aerobicPoolDoSecond;
    }
    public SewProcess aerobicPoolDoSecond(BigDecimal aerobicPoolDoSecond) {
        this.aerobicPoolDoSecond = aerobicPoolDoSecond;
        return this;
    }
    public void setAerobicPoolDoSecond(BigDecimal aerobicPoolDoSecond) {
        this.aerobicPoolDoSecond = aerobicPoolDoSecond;
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

    public Long getCraftId() {
        return craftId;
    }
    public SewProcess craftId(Long craftId) {
        this.craftId = craftId;
        return this;
    }
    public void setCraftId(Long craftId) {
        this.craftId = craftId;
    }

    public String getCraftCode() {
        return craftCode;
    }

    public void setCraftCode(String craftCode) {
        this.craftCode = craftCode;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public SendStatusType getStatus() {
        return status;
    }
    public SewProcess status(SendStatusType status) {
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

    @Override
    public String toString() {
        return "SewProcess{" +
            "id=" + id +
            ", processCode='" + processCode + '\'' +
            ", inFlow=" + inFlow +
            ", inAmmonia=" + inAmmonia +
            ", inCod=" + inCod +
            ", inTn=" + inTn +
            ", inTp=" + inTp +
            ", inSs=" + inSs +
            ", outFlow=" + outFlow +
            ", outAmmonia=" + outAmmonia +
            ", outCod=" + outCod +
            ", outTn=" + outTn +
            ", outTp=" + outTp +
            ", outSs=" + outSs +
            ", aerobicPoolDo=" + aerobicPoolDo +
            ", aerobicPoolDoSecond=" + aerobicPoolDoSecond +
            ", anoxicPoolDoOutNit=" + anoxicPoolDoOutNit +
            ", aerobicPoolNit=" + aerobicPoolNit +
            ", dayTime=" + dayTime +
            ", craftId=" + craftId +
            ", craftCode='" + craftCode + '\'' +
            ", status=" + status +
            ", plateStatus=" + plateStatus +
            '}';
    }
}
