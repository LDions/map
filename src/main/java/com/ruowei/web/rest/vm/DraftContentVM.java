package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DraftContentVM {

    @NotNull(message = "草稿内容不能为空")
    @ApiModelProperty(value = "草稿内容", required = true)
    private String content;
}
