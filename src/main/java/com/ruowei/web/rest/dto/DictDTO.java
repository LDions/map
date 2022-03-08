package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiModelProperty;

public interface DictDTO {

    @ApiModelProperty(value = "编码")
    String getDictCode();

    @ApiModelProperty(value = "名称")
    String getDictName();
}
