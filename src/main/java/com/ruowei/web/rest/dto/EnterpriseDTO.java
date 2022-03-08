package com.ruowei.web.rest.dto;

import com.ruowei.domain.enumeration.EnterpriseStatusType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EnterpriseDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "单位名称")
    private String name;

    @ApiModelProperty(value = "企业性质")
    private String nature;

    @ApiModelProperty(value = "统一信用代码")
    private String uniCreditCode;

    @ApiModelProperty(value = "法定代表人")
    private String legalRepresentative;

    @ApiModelProperty(value = "经营地址所在省")
    private String businessProvince;

    @ApiModelProperty(value = "经营地址所在市")
    private String businessCity;

    @ApiModelProperty(value = "经营地址所在区")
    private String businessArea;

    @ApiModelProperty(value = "经营详细地址")
    private String businessAddress;

    @ApiModelProperty(value = "联系人姓名")
    private String contactName;

    @ApiModelProperty(value = "联系人手机")
    private String contactPhone;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态")
    private EnterpriseStatusType status;
}
