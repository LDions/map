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
 * 化验数据
 */
@ApiModel(description = "化验数据")
@Entity
@Table(name = "sew_slu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SewSlu implements Serializable {

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
     * 污泥处置方法编码
     */
    @NotNull
    @ApiModelProperty(value = "污泥处置方法编码", required = true)
    @Column(name = "method_code", nullable = false)
    private String methodCode;

    /**
     * 污泥处置方法名称
     */
    @NotNull
    @ApiModelProperty(value = "污泥处置方法名称", required = true)
    @Column(name = "method_name", nullable = false)
    private String methodName;

    /**
     * 进水流量（mg/L）
     */
    @ApiModelProperty(value = "进水流量（mg/L）")
    @Column(name = "ass_in_flow", precision = 21, scale = 2)
    private BigDecimal assInFlow;

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
     * 出水流量（mg/L）
     */
    @ApiModelProperty(value = "出水流量（mg/L）")
    @Column(name = "ass_out_flow", precision = 21, scale = 2)
    private BigDecimal assOutFlow;

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

    @NotNull
    @ApiModelProperty(value = "日报表时间", required = true)
    @Column(name = "day_time", nullable = false)
    private Instant dayTime;

    /**
     * 数据推送状态
     */
    @ApiModelProperty(value = "数据推送状态", required = true)
    @Column(name = "status", nullable = false)
    private SendStatusType status;

    /**
     * 所属工艺ID
     */
    @ApiModelProperty(value = "日报表时间", required = true)
    @Column(name = "craft_id", nullable = false)
    private Long craftId;

    public SendStatusType getStatus() {
        return status;
    }
    public SewSlu status(SendStatusType status) {
        this.status = status;
        return this;
    }
    public void setStatus(SendStatusType status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SewSlu id(Long id) {
        this.id = id;
        return this;
    }

    public String getDocumentCode() {
        return this.documentCode;
    }

    public SewSlu documentCode(String documentCode) {
        this.documentCode = documentCode;
        return this;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public String getMethodCode() {
        return this.methodCode;
    }

    public SewSlu methodCode(String methodCode) {
        this.methodCode = methodCode;
        return this;
    }

    public void setMethodCode(String methodCode) {
        this.methodCode = methodCode;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public SewSlu methodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public BigDecimal getAssInFlow() {
        return assInFlow;
    }
    public SewSlu assInFlow(BigDecimal assInFlow) {
        this.assInFlow = assInFlow;
        return this;
    }
    public void setAssInFlow(BigDecimal assInFlow) {
        this.assInFlow = assInFlow;
    }

    public BigDecimal getAssInAmmonia() {
        return assInAmmonia;
    }
    public SewSlu assInAmmonia(BigDecimal assInAmmonia) {
        this.assInAmmonia = assInAmmonia;
        return this;
    }
    public void setAssInAmmonia(BigDecimal assInAmmonia) {
        this.assInAmmonia = assInAmmonia;
    }

    public BigDecimal getAssInCod() {
        return assInCod;
    }
    public SewSlu assInCod(BigDecimal assInCod) {
        this.assInCod = assInCod;
        return this;
    }
    public void setAssInCod(BigDecimal assInCod) {
        this.assInCod = assInCod;
    }

    public BigDecimal getAssInTn() {
        return assInTn;
    }
    public SewSlu assInTn(BigDecimal assInTn) {
        this.assInTn = assInTn;
        return this;
    }
    public void setAssInTn(BigDecimal assInTn) {
        this.assInTn = assInTn;
    }

    public BigDecimal getAssInTp() {
        return assInTp;
    }
    public SewSlu assInTp(BigDecimal assInTp) {
        this.assInTp = assInTp;
        return this;
    }
    public void setAssInTp(BigDecimal assInTp) {
        this.assInTp = assInTp;
    }

    public BigDecimal getAssAnoxicPoolDoOutNit() {
        return assAnoxicPoolDoOutNit;
    }
    public SewSlu assAnoxicPoolDoOutNit(BigDecimal assAnoxicPoolDoOutNit) {
        this.assAnoxicPoolDoOutNit = assAnoxicPoolDoOutNit;
        return this;
    }
    public void setAssAnoxicPoolDoOutNit(BigDecimal assAnoxicPoolDoOutNit) {
        this.assAnoxicPoolDoOutNit = assAnoxicPoolDoOutNit;
    }

    public BigDecimal getAssAerobicPoolDoOutNit() {
        return assAerobicPoolDoOutNit;
    }
    public SewSlu assAerobicPoolDoOutNit(BigDecimal assAerobicPoolDoOutNit) {
        this.assAerobicPoolDoOutNit = assAerobicPoolDoOutNit;
        return this;
    }
    public void setAssAerobicPoolDoOutNit(BigDecimal assAerobicPoolDoOutNit) {
        this.assAerobicPoolDoOutNit = assAerobicPoolDoOutNit;
    }

    public BigDecimal getAssOutFlow() {
        return assOutFlow;
    }
    public SewSlu assOutFlow(BigDecimal assOutFlow) {
        this.assOutFlow = assOutFlow;
        return this;
    }
    public void setAssOutFlow(BigDecimal assOutFlow) {
        this.assOutFlow = assOutFlow;
    }

    public BigDecimal getAssOutAmmonia() {
        return assOutAmmonia;
    }
    public SewSlu assOutAmmonia(BigDecimal assOutAmmonia) {
        this.assOutAmmonia = assOutAmmonia;
        return this;
    }
    public void setAssOutAmmonia(BigDecimal assOutAmmonia) {
        this.assOutAmmonia = assOutAmmonia;
    }

    public BigDecimal getAssOutCod() {
        return assOutCod;
    }
    public SewSlu assOutCod(BigDecimal assOutCod) {
        this.assOutCod = assOutCod;
        return this;
    }
    public void setAssOutCod(BigDecimal assOutCod) {
        this.assOutCod = assOutCod;
    }

    public BigDecimal getAssOutTn() {
        return assOutTn;
    }
    public SewSlu assOutTn(BigDecimal assOutTn) {
        this.assOutTn = assOutTn;
        return this;
    }
    public void setAssOutTn(BigDecimal assOutTn) {
        this.assOutTn = assOutTn;
    }

    public BigDecimal getAssOutTp() {
        return assOutTp;
    }
    public SewSlu assOutTp(BigDecimal assOutTp) {
        this.assOutTp = assOutTp;
        return this;
    }
    public void setAssOutTp(BigDecimal assOutTp) {
        this.assOutTp = assOutTp;
    }

    public Instant getDayTime() {
        return dayTime;
    }

    public void setDayTime(Instant dayTime) {
        this.dayTime = dayTime;
    }
    public SewSlu dayTime(Instant dayTime) {
        this.dayTime = dayTime;
        return this;
    }

    public Long getCraftId() {
        return craftId;
    }

    public void setCraftId(Long craftId) {
        this.craftId = craftId;
    }
    public SewSlu craftId(Long craftId) {
        this.craftId = craftId;
        return this;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SewSlu)) {
            return false;
        }
        return id != null && id.equals(((SewSlu) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "SewSlu{" +
            "id=" + id +
            ", documentCode='" + documentCode + '\'' +
            ", methodCode='" + methodCode + '\'' +
            ", methodName='" + methodName + '\'' +
            ", assInFlow=" + assInFlow +
            ", assInAmmonia=" + assInAmmonia +
            ", assInCod=" + assInCod +
            ", assInTn=" + assInTn +
            ", assInTp=" + assInTp +
            ", assAnoxicPoolDoOutNit=" + assAnoxicPoolDoOutNit +
            ", assAerobicPoolDoOutNit=" + assAerobicPoolDoOutNit +
            ", assOutFlow=" + assOutFlow +
            ", assOutAmmonia=" + assOutAmmonia +
            ", assOutCod=" + assOutCod +
            ", assOutTn=" + assOutTn +
            ", assOutTp=" + assOutTp +
            ", dayTime=" + dayTime +
            ", status=" + status +
            ", craftId=" + craftId +
            '}';
    }
}
