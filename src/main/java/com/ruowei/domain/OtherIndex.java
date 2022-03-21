package com.ruowei.domain;

import com.ruowei.domain.enumeration.SendStatusType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 其他指标数据
 */
@ApiModel(description = "其他指标数据")
@Entity
@Table(name = "other_index")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OtherIndex implements Serializable {

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
    @Column(name = "index_capacity", precision = 21, scale = 2)
    private BigDecimal indexCapacity;

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

    public OtherIndex id(Long id) {
        this.id = id;
        return this;
    }

    public String getDocumentCode() {
        return this.documentCode;
    }

    public OtherIndex documentCode(String documentCode) {
        this.documentCode = documentCode;
        return this;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public String getMethodCode() {
        return this.methodCode;
    }

    public OtherIndex methodCode(String methodCode) {
        this.methodCode = methodCode;
        return this;
    }

    public void setMethodCode(String methodCode) {
        this.methodCode = methodCode;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public OtherIndex methodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public BigDecimal getIndexCapacity() {
        return this.indexCapacity;
    }

    public OtherIndex indexCapacity(BigDecimal indexCapacity) {
        this.indexCapacity = indexCapacity;
        return this;
    }

    public void setIndexCapacity(BigDecimal indexCapacity) {
        this.indexCapacity = indexCapacity;
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
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OtherIndex)) {
            return false;
        }
        return id != null && id.equals(((OtherIndex) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OtherIndex{" +
            "id=" + getId() +
            ", documentCode='" + getDocumentCode() + "'" +
            ", methodCode='" + getMethodCode() + "'" +
            ", methodName='" + getMethodName() + "'" +
            ", indexCapacity=" + getIndexCapacity() +
            "}";
    }
}
