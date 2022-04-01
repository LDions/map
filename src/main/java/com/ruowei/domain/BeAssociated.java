package com.ruowei.domain;

import com.ruowei.domain.enumeration.SendStatusType;
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
     * 被关联指标名称
     */
    @ApiModelProperty(value = "被关联数据名称")
    @Column(name = "be_associated_name")
    private String beAssociatedName;

    /**
     * 关联指标
     */
    @ApiModelProperty(value = "关联数据名称")
    @Column(name = "relation_target")
    private String relationTarget;

    /**
     * 被关联指标所属水厂Code
     */
    @ApiModelProperty(value = "所属水厂Code")
    @Column(name = "be_associated_enterprise_code")
    private String beAssociatedEnterpriseCode;

    /**
     * 关联编码
     */
    @ApiModelProperty(value = "关联编码")
    @Column(name = "associate_code")
    private String associatedCode;

    /**
     * 集团数据推送状态
     */
    @ApiModelProperty(value = "集团数据推送状态")
    @Column(name = "status")
    private SendStatusType status;

    /**
     * 平台数据推送状态
     */
    @ApiModelProperty(value = "平台数据推送状态")
    @Column(name = "plate_status")
    private SendStatusType plateStatus;
}
