package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EmiFactorDetailDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "版本号")
    private String versionNum;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "数据内容")
    private String content;

    @ApiModelProperty(value = "历史数据内容")
    private String oldContent;
}
