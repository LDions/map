package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class AssociateVM {

    @ApiModelProperty(value = "操作类型[0新增，1编辑]")
    private Integer operate;

    @ApiModelProperty(value = "被关联数据名称")
    private String beAssociatedName;

    @ApiModelProperty(value = "关联编码")
    private String associatedCode;

    @ApiModelProperty(value = "水厂编码")
    private String beAssociatedEnterpriseCode;

    @ApiModelProperty(value = "关联数据名称")
    private String relationTarget;
}
