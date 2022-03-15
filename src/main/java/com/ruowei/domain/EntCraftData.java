package com.ruowei.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;

@ApiModel(description = "仪表数据与工艺、水厂关联表")
@Entity
@Table(name = "sys_ent_craft_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class EntCraftData {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 集团名称
     */

    @ApiModelProperty(value = "水厂编号")
    @Column(name = "group_name")
    private String groupName;


    /**
     * 水厂名称
     */

    @ApiModelProperty(value = "水厂编号")
    @Column(name = "ent_code")
    private String entCode;


    /**
     * 水厂名称
     */

    @ApiModelProperty(value = "水厂名称")
    @Column(name = "ent_name")
    private String entName;


    /**
     * 水厂名称
     */

    @ApiModelProperty(value = "工艺段编号")
    @Column(name = "craft_code")
    private String craftCode;


    /**
     * 工艺段名称
     */

    @ApiModelProperty(value = "工艺段名称")
    @Column(name = "craft_name")
    private String craftName;


    /**
     * 仪表数据id
     */

    @ApiModelProperty(value = "仪表数据id")
    @Column(name = "process_id")
    private String groupCode;


    /**
     * 数据时间
     */

    @ApiModelProperty(value = "数据时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "day_time")
    private Instant dayTime;

    /**
     * 数据来源
     */

    @ApiModelProperty(value = "数据来源")
    @Column(name = "message_source")
    private String messageSource;
}
