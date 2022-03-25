package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
public class GroupVM {

    @ApiModelProperty(value = "集团编码")
    private String groupCode;

    @ApiModelProperty(value = "集团名称")
    private String groupName;

    @ApiModelProperty(value = "集团位置经度")
    private BigDecimal groupLongitude;

    @ApiModelProperty(value = "集团位置纬度")
    private BigDecimal groupLatitude;

    @ApiModelProperty(value = "集团联系人")
    private String groupContactName;

    @ApiModelProperty(value = "联系人电话")
    private String groupContactPhone;

}
