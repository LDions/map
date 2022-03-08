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
 * 污泥处置方法数据
 */
@ApiModel(description = "污泥处置方法数据")
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
     * 污泥处置量（kg/m）
     */
    @ApiModelProperty(value = "污泥处置量（kg/m）")
    @Column(name = "slu_capacity", precision = 21, scale = 2)
    private BigDecimal sluCapacity;

    /**
     * 污泥处置前含水率（%）
     */
    @ApiModelProperty(value = "污泥处置前含水率（%）")
    @Column(name = "slu_moisture", precision = 21, scale = 2)
    private BigDecimal sluMoisture;

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

    public BigDecimal getSluCapacity() {
        return this.sluCapacity;
    }

    public SewSlu sluCapacity(BigDecimal sluCapacity) {
        this.sluCapacity = sluCapacity;
        return this;
    }

    public void setSluCapacity(BigDecimal sluCapacity) {
        this.sluCapacity = sluCapacity;
    }

    public BigDecimal getSluMoisture() {
        return this.sluMoisture;
    }

    public SewSlu sluMoisture(BigDecimal sluMoisture) {
        this.sluMoisture = sluMoisture;
        return this;
    }

    public void setSluMoisture(BigDecimal sluMoisture) {
        this.sluMoisture = sluMoisture;
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

    // prettier-ignore
    @Override
    public String toString() {
        return "SewSlu{" +
            "id=" + getId() +
            ", documentCode='" + getDocumentCode() + "'" +
            ", methodCode='" + getMethodCode() + "'" +
            ", methodName='" + getMethodName() + "'" +
            ", sluCapacity=" + getSluCapacity() +
            ", sluMoisture=" + getSluMoisture() +
            "}";
    }
}
