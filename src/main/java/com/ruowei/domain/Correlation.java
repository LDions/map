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
    @ApiModelProperty(value = "关联数据名称")
    @Column(name = "name")
    private String name;

    /**
     * 关联数据指标
     */
    @ApiModelProperty(value = "关联数据指标")
    @Column(name = "indicator")
    private String indicator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public Correlation name(String name) {
        this.name = name;
        return this;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getIndicator() {
        return indicator;
    }
    public Correlation indicator(String indicator) {
        this.indicator = indicator;
        return this;
    }
    public void setIndicator(String indicator) {
        this.indicator = indicator;
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
            ", name='" + name + '\'' +
            ", indicator='" + indicator + '\'' +
            '}';
    }
}
