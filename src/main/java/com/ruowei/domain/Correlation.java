package com.ruowei.domain;

/**
 * 指标关联表
 */
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@ApiModel(description = "指标关联表")
@Entity
@Table(name = "sew_Correlation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class Correlation {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联数据名称
     */
    @ApiModelProperty(value = "关联数据来源")
    @Column(name = "relation_source")
    private String relationSource;

    /**
     * 关联数据来源
     */
    @ApiModelProperty(value = "关联数据名称")
    @Column(name = "relation_target")
    private String relationTarget;

    /**
     * 关联数据来源
     */
    @ApiModelProperty(value = "关联数据Id")
    @Column(name = "relevance_id")
    private Long relevanceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelationSource() {
        return relationSource;
    }
    public Correlation relationSource(String relationSource) {
        this.relationSource = relationSource;
        return this;
    }
    public void setRelationSource(String relationSource) {
        this.relationSource = relationSource;
    }

    public String getRelationTarget() {
        return relationTarget;
    }
    public Correlation relationTarget(String relationTarget) {
        this.relationTarget = relationTarget;
        return this;
    }
    public void setRelationTarget(String relationTarget) {
        this.relationTarget = relationTarget;
    }

    public Long getRelevanceId() {
        return relevanceId;
    }
    public Correlation relevanceId(Long relevanceId) {
        this.relevanceId = relevanceId;
        return this;
    }
    public void setRelevanceId(Long relevanceId) {
        this.relevanceId = relevanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Correlation)) {
            return false;
        }
        return id != null && id.equals(((Correlation) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Correlation{" +
            "id=" + id +
            ", relationSource='" + relationSource + '\'' +
            ", relationTarget='" + relationTarget + '\'' +
            ", relevanceId=" + relevanceId +
            '}';
    }
}
