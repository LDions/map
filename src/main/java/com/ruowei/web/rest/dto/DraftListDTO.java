package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import java.time.Instant;

public interface DraftListDTO {

    @ApiModelProperty(value = "id")
    Long getId();
//
//    @ApiModelProperty(value = "用户id")
//    Long getUserId();
//
//    @ApiModelProperty(value = "行业类型编码")
//    String getIndustryCode();

    @ApiModelProperty(value = "单据号")
    String getDocumentCode();

    @ApiModelProperty(value = "最后更新时间")
    Instant getModifyTime();




}
