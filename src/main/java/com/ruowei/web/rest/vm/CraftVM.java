package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CraftVM {

    @ApiModelProperty(value = "工艺名称")
    private String craftName;

    @ApiModelProperty(value = "工艺编码")
    private String craftCode;

    @ApiModelProperty(value = "厌氧池池容")
    private String anaerobicPoolVolume;

    @ApiModelProperty(value = "缺氧池池容")
    private String anoxicPoolVolume;

    @ApiModelProperty(value = "好氧池池容（mg/L）")
    private BigDecimal aerobicPoolVolume;

    @ApiModelProperty(value = "内回流泵流量（mg/L）")
    private BigDecimal inRefluxFlow;

    @ApiModelProperty(value = "内回流泵台数（mg/L）")
    private BigDecimal inRefluxNum;

    @ApiModelProperty(value = "外回流泵流量（mg/L）")
    private BigDecimal outRefluxFlow;

    @ApiModelProperty(value = "外回流泵台数（mg/L）")
    private BigDecimal outRefluxNum;

    @ApiModelProperty(value = "传统公式：好氧区硝酸盐浓度（mg/L）")
    private BigDecimal aerobioticNitrateConcentration;

    @ApiModelProperty(value = "传统公式：缺氧区硝酸盐浓度（mg/L）")
    private BigDecimal anoxiaNitrateConcentration;

    @ApiModelProperty(value = "传统公式：硝化液回流比")
    private BigDecimal nitrateRefluxRatio;

    @ApiModelProperty(value = "传统公式：BOD/COD（mg/L）")
    private BigDecimal bodCodRatio;

    @ApiModelProperty(value = "传统公式：COD校准参数（mg/L）")
    private BigDecimal codCalibration;

    @ApiModelProperty(value = "传统公式：BOD/N（mg/L）")
    private BigDecimal bodNRatio;

    @ApiModelProperty(value = "传统公式：BOD当量")
    private BigDecimal bodEquivalentWeight;

    @ApiModelProperty(value = "除磷公式：外加碳源相对亲密度")
    private BigDecimal intimacy;

    @ApiModelProperty(value = "传统公式：碳源稀释倍数")
    private BigDecimal dilutionRatio;

    @ApiModelProperty(value = "除磷公式：二沉池出水正磷酸盐设定值")
    private BigDecimal phosphate;

    @ApiModelProperty(value = "除磷公式：铁盐或铝盐的摩尔质量比")
    private BigDecimal FeAlRatio;

    @ApiModelProperty(value = "除磷公式：投加系数")
    private BigDecimal phosphorusDosing;

    @ApiModelProperty(value = "除磷公式：铁盐或铝盐的有效成分")
    private BigDecimal feAlActiveIngredients;

    @ApiModelProperty(value = "除磷公式：除磷药剂配药浓度或者相对密度")
    private BigDecimal concentration;


}
