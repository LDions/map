package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
@Data
public class ThresholdDTO {

    @ApiModelProperty(value = "出水总氮阈值")
    private BigDecimal inTn;

    @ApiModelProperty(value = "出水氨氮阈值")
    private BigDecimal inAmmonia;

    @ApiModelProperty(value = "碳源投加量阈值")
    private BigDecimal carAdd;

}
