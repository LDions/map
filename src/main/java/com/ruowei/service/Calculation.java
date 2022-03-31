package com.ruowei.service;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Calculation {

    @ApiModelProperty(value = "缺氧区需去除硝酸盐量")
    private BigDecimal nitrateToBeRemoved;

    @ApiModelProperty(value = "外投加碳源量")
    private BigDecimal additionOfCarbonSource;

    @ApiModelProperty(value = "加药泵流量")
    private BigDecimal dosingPumpFlow;
}
