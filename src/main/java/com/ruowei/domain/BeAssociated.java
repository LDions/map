package com.ruowei.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@ApiModel(description = "被关联的数据")
@Entity
@Table(name = "sew_be_associated")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class BeAssociated {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 数据名称
     */
    @ApiModelProperty(value = "被关联数据名称")
    @Column(name = "be_associated_name")
    private String beAssociatedName;
}
