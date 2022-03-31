package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import liquibase.pro.packaged.S;
import lombok.Data;

@Data
public class UserQM {
    @ApiModelProperty(value = "登陆账号")
    private String login;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "水厂Code")
    private String enterpriseCode;

    @ApiModelProperty(value = "水厂名称")
    private String enterpriseName;

    @ApiModelProperty(value = "集团Code")
    private String groupCode;

    @ApiModelProperty(value = "集团名称")
    private String groupName;
}
