package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class  EmiFactorVM {

    @NotNull
    @ApiModelProperty(value = "历史因子ID", required = true)
    private Long historicalId;

    @NotEmpty(message = "请选择项目")
    @ApiModelProperty(value = "项目编码", required = true)
    private String projectCode;

    @NotEmpty(message = "请选择项目")
    @ApiModelProperty(value = "项目名称", required = true)
    private String projectName;

    @NotEmpty(message = "请输入备注")
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    @NotEmpty(message = "数据内容不能为空")
    @ApiModelProperty(value = "数据内容", required = true)
    private String content;
}
