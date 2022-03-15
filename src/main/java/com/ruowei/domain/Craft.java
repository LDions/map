package com.ruowei.domain;


/**
 * 工艺段信息
 */
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@ApiModel(description = "工艺段信息")
@Entity
@Table(name = "sew_craft")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class Craft {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 工艺段名称
     */
    @ApiModelProperty(value = "工艺段名称")
    @Column(name = "name")
    private String name;

    /**
     * 工艺段编号
     */
    @ApiModelProperty(value = "工艺段编号")
    @Column(name = "craft_code")
    private String craftCode;

    /**
     * 上属平台
     */
    @ApiModelProperty(value = "上属水厂编号")
    @Column(name = "ent_code")
    private String entCode;

}
