package com.ruowei.web.rest.dto;

import com.ruowei.domain.enumeration.SendStatusType;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * 污水碳排放核算结果
 */
@Data
public class SewEmiAccountOutputDTO {
    //TODO 待修改
    @ApiModelProperty(value = "单据号")
    private String documentCode;

    @ApiModelProperty(value = "碳排放数据编码")
    private String dataCode;

    @ApiModelProperty(value = "水厂code")
    private String enterpriseCode;

    @ApiModelProperty(value = "工艺code")
    private String craftCode;

    @ApiModelProperty(value = "核算方式（true自动、false手动）")
    private Boolean acctype;

    @ApiModelProperty(value = "核算年份")
    private Instant accTime;

    @ApiModelProperty(value = "预测未来时间")
    private Instant predictTime;

    @ApiModelProperty(value = "出水总氮")
    private BigDecimal totalOutN;

    @ApiModelProperty(value = "出水氨氮")
    private BigDecimal outAN;

    @ApiModelProperty(value = "碳源投加量）")
    private BigDecimal carbonAdd;

    @ApiModelProperty(value = "除磷药剂）")
    private BigDecimal phosphorusremover;

    @ApiModelProperty(value = "集团数据推送状态")
    private SendStatusType status;

    @ApiModelProperty(value = "平台数据推送状态")
    private SendStatusType plateStatus;

    public SewEmiAccountOutputDTO(String documentCode, String dataCode, String enterpriseCode, String craftCode, Boolean acctype, Instant accTime, Instant predictTime, BigDecimal totalOutN, BigDecimal outAN, BigDecimal carbonAdd, BigDecimal phosphorusremover, SendStatusType status, SendStatusType plateStatus) {
        this.documentCode = documentCode;
        this.dataCode = dataCode;
        this.enterpriseCode = enterpriseCode;
        this.craftCode = craftCode;
        this.acctype = acctype;
        this.accTime = accTime;
        this.predictTime = predictTime;
        this.totalOutN = totalOutN;
        this.outAN = outAN;
        this.carbonAdd = carbonAdd;
        this.phosphorusremover = phosphorusremover;
        this.status = status;
        this.plateStatus = plateStatus;
    }
}
