package com.ruowei.web.rest.vm;

import com.ruowei.domain.enumeration.EnterpriseStatusType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

@Data
public class EnterpriseQM {

    @ApiModelProperty(value = "水厂名称")
    private String name;

    @ApiModelProperty(value = "是否为试点水厂")
    private String isTry;

    @ApiModelProperty(value = "水厂所属集团id")
    private Long groupId;
}
