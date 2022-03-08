package com.ruowei.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 碳排放因子
 */
@ApiModel(description = "碳排放因子")
@Entity
@Table(name = "emi_factor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmiFactor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 版本号
     */
    @NotNull
    @ApiModelProperty(value = "版本号", required = true)
    @Column(name = "version_num", nullable = false)
    private String versionNum;

    /**
     * 历史因子ID
     */
    @ApiModelProperty(value = "历史因子ID")
    @Column(name = "historical_id")
    private Long historicalId;

    /**
     * 项目编码
     */
    @NotNull
    @ApiModelProperty(value = "项目编码", required = true)
    @Column(name = "project_code", nullable = false)
    private String projectCode;

    /**
     * 项目名称
     */
    @NotNull
    @ApiModelProperty(value = "项目名称", required = true)
    @Column(name = "project_name", nullable = false)
    private String projectName;

    /**
     * 操作员ID
     */
    @NotNull
    @ApiModelProperty(value = "操作员ID", required = true)
    @Column(name = "operator_id", nullable = false)
    private Long operatorId;

    /**
     * 操作员姓名
     */
    @NotNull
    @ApiModelProperty(value = "操作员姓名", required = true)
    @Column(name = "operator_name", nullable = false)
    private String operatorName;

    /**
     * 最后更新日期
     */
    @NotNull
    @ApiModelProperty(value = "最后更新日期", required = true)
    @Column(name = "modify_date", nullable = false)
    private LocalDate modifyDate;

    /**
     * 备注
     */

    @ApiModelProperty(value = "备注", required = true)
    @Lob
    @Column(name = "remark", nullable = false)
    private String remark;

    /**
     * 数据内容
     */

    @ApiModelProperty(value = "数据内容", required = true)
    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmiFactor id(Long id) {
        this.id = id;
        return this;
    }

    public String getVersionNum() {
        return this.versionNum;
    }

    public EmiFactor versionNum(String versionNum) {
        this.versionNum = versionNum;
        return this;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public Long getHistoricalId() {
        return this.historicalId;
    }

    public EmiFactor historicalId(Long historicalId) {
        this.historicalId = historicalId;
        return this;
    }

    public void setHistoricalId(Long historicalId) {
        this.historicalId = historicalId;
    }

    public String getProjectCode() {
        return this.projectCode;
    }

    public EmiFactor projectCode(String projectCode) {
        this.projectCode = projectCode;
        return this;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public EmiFactor projectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getOperatorId() {
        return this.operatorId;
    }

    public EmiFactor operatorId(Long operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return this.operatorName;
    }

    public EmiFactor operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public LocalDate getModifyDate() {
        return this.modifyDate;
    }

    public EmiFactor modifyDate(LocalDate modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(LocalDate modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getRemark() {
        return this.remark;
    }

    public EmiFactor remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContent() {
        return this.content;
    }

    public EmiFactor content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmiFactor)) {
            return false;
        }
        return id != null && id.equals(((EmiFactor) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmiFactor{" +
            "id=" + getId() +
            ", versionNum='" + getVersionNum() + "'" +
            ", historicalId=" + getHistoricalId() +
            ", projectCode='" + getProjectCode() + "'" +
            ", projectName='" + getProjectName() + "'" +
            ", operatorId=" + getOperatorId() +
            ", operatorName='" + getOperatorName() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", remark='" + getRemark() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
}
