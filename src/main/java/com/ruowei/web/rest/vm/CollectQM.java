package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CollectQM {

    private Long total;

    private Long id;

    @ApiModelProperty(value = "被关联数据所属工艺code")
    private String entper;

    @ApiModelProperty(value = "被关联的数据")
    private String beAssociated;

    @ApiModelProperty(value = "关联到的数据")
    private List<String> relation;

}
