package com.ruowei.domain.enumeration;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;


public class SendDTO {
        @ApiModelProperty("碳层名称")
        private String id;

        @ApiModelProperty("碳层面积")
        private BigDecimal layerArea;

    }


