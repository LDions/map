package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQM {
    @ApiModelProperty(value = "登陆账号")
    private String login;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;
}
