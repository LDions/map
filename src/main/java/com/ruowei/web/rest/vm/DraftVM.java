package com.ruowei.web.rest.vm;

import com.ruowei.domain.enumeration.DraftType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DraftVM {

    @NotNull(message = "行业类型编码不能为空")
    @ApiModelProperty(value = "行业类型编码", required = true)
    private String industryCode;

    @NotNull(message = "草稿内容不能为空")
    @ApiModelProperty(value = "草稿内容", required = true)
    private String content;

    @NotNull(message = "草稿类型不能为空")
    @ApiModelProperty(value = "草稿类型", required = true)
    private DraftType type;
}
