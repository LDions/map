package com.ruowei.domain;

/**
 * 集团信息
 */
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@ApiModel(description = "水厂信息")
@Entity
@Table(name = "sew_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class Group {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 集团名称
     */
    @ApiModelProperty(value = "集团名称")
    @Column(name = "group_name")
    private String name;

    /**
     * 集团位置
     */
    @ApiModelProperty(value = "集团位置")
    @Column(name = "location")
    private String location;

    /**
     * 集团位置
     */
    @ApiModelProperty(value = "集团编号")
    @Column(name = "group_code")
    private String groupCode;

    /**
     * 上属平台
     */
    @ApiModelProperty(value = "上属平台")
    @Column(name = "plan_id")
    private String planId;


}
