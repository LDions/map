package com.ruowei.web.rest.vm;

import com.ruowei.domain.enumeration.RoleType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleQM {

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色编码")
    private String code;

    @ApiModelProperty(value = "角色类型(ADMIN|EMI|SINK)")
    private RoleType type;

    @ApiModelProperty(value = "是否系统内置角色")
    private Boolean isSys;

}
