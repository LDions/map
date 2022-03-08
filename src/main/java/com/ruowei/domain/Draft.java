package com.ruowei.domain;

import com.ruowei.domain.enumeration.DraftType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 核算数据草稿
 */
@ApiModel(description = "核算数据草稿")
@Entity
@Table(name = "app_draft")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Draft implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
     */
    @NotNull
    @ApiModelProperty(value = "用户ID", required = true)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 最后更新时间
     */
    @NotNull
    @ApiModelProperty(value = "最后更新时间", required = true)
    @Column(name = "modify_time", nullable = false)
    private Instant modifyTime;

    /**
     * 草稿类型
     */
    @NotNull
    @ApiModelProperty(value = "草稿类型", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private DraftType type;

    /**
     * 行业类型编码
     */
    @NotNull
    @ApiModelProperty(value = "行业类型编码", required = true)
    @Column(name = "industry_code", nullable = false)
    private String industryCode;

    /**
     * 单据号
     */
    @ApiModelProperty(value = "单据号")
    @Column(name = "document_code")
    private String documentCode;

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

    public Draft id(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Draft userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Instant getModifyTime() {
        return this.modifyTime;
    }

    public Draft modifyTime(Instant modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public void setModifyTime(Instant modifyTime) {
        this.modifyTime = modifyTime;
    }

    public DraftType getType() {
        return this.type;
    }

    public Draft type(DraftType type) {
        this.type = type;
        return this;
    }

    public void setType(DraftType type) {
        this.type = type;
    }

    public String getIndustryCode() {
        return this.industryCode;
    }

    public Draft industryCode(String industryCode) {
        this.industryCode = industryCode;
        return this;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getDocumentCode() {
        return this.documentCode;
    }

    public Draft documentCode(String documentCode) {
        this.documentCode = documentCode;
        return this;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public String getContent() {
        return this.content;
    }

    public Draft content(String content) {
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
        if (!(o instanceof Draft)) {
            return false;
        }
        return id != null && id.equals(((Draft) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Draft{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", modifyTime='" + getModifyTime() + "'" +
            ", type='" + getType() + "'" +
            ", industryCode='" + getIndustryCode() + "'" +
            ", documentCode='" + getDocumentCode() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
}
