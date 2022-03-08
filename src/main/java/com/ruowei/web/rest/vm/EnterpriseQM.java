package com.ruowei.web.rest.vm;

import com.ruowei.domain.enumeration.EnterpriseStatusType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EnterpriseQM {

    @ApiModelProperty(value = "单位名称")
    private String name;

    @ApiModelProperty(value = "企业性质")
    private String nature;

    @ApiModelProperty(value = "统一信用代码")
    private String uniCreditCode;

    @ApiModelProperty(value = "状态(NORMAL|DELETE)")
    private EnterpriseStatusType status;

}
