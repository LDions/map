package com.ruowei.web.rest.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EnterpriseUserVM {
    @ApiModelProperty(value = "操作类型[0新增，1编辑]")
    private Integer operate;

    @ApiModelProperty(value = "用户编码")
    private String userCode;

    @ApiModelProperty(value = "用户名")
    private String login;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "水厂编码")
    private String enterpriseCode;

    @ApiModelProperty(value = "集团编码")
    private String groupCode;
}
