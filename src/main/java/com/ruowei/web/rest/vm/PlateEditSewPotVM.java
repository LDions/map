package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class PlateEditSewPotVM {
    //用于区分是集团传来的数据还是水厂传来的数据
    @ApiModelProperty(value = "是否是试点水厂")
    private Boolean isTry;

    //用于在平台中确定某个集团
    @ApiModelProperty(value = "集团编码")
    private String groupCode;

    //通过改字段确定是集团中哪个水厂的仪表数据修改
    @ApiModelProperty(value = "水厂编码")
    private String code;

    @ApiModelProperty(value = "日报编码")
    private String potCode;

    @ApiModelProperty(value = "工艺编码")
    private String craftCode;

    @ApiModelProperty(value = "进水PH")
    private BigDecimal dayInPh;

    @ApiModelProperty(value = "出水PH")
    private BigDecimal dayOutPh;

    @ApiModelProperty(value = "初沉池排泥量")
    private BigDecimal dayFirstMud;

    @ApiModelProperty(value = "二沉池排泥量")
    private BigDecimal daySecondMud;

    @ApiModelProperty(value = "回流比")
    private BigDecimal dayReflux;

    @ApiModelProperty(value = "碳源投加量")
    private BigDecimal dayCarAdd;

    @ApiModelProperty(value = "生化池-厌氧池PH")
    private BigDecimal dayAnaerobicPoolPh;

    @ApiModelProperty(value = "生化池-厌氧池ORP")
    private BigDecimal dayAnaerobicPoolOrp;

    @ApiModelProperty(value = "生化池-厌氧池DO")
    private BigDecimal dayAnaerobicPoolDo;

    @ApiModelProperty(value = "生化池-厌氧池SOUR")
    private BigDecimal dayAnaerobicPoolSour;

    @ApiModelProperty(value = "生化池-厌氧池SV")
    private BigDecimal dayAnaerobicPoolSv;

    @ApiModelProperty(value = "生化池-厌氧池MLSS")
    private BigDecimal dayAnaerobicPoolMlss;

    @ApiModelProperty(value = "生化池-厌氧池温度")
    private BigDecimal dayAnaerobicPoolTemper;

    @ApiModelProperty(value = "生化池-缺氧池PH")
    private BigDecimal dayAnoxicPoolPh;

    @ApiModelProperty(value = "生化池-缺氧池ORP")
    private BigDecimal dayAnoxicPoolOrp;

    @ApiModelProperty(value = "生化池-缺氧池DO")
    private BigDecimal dayAnoxicPoolDo;

    @ApiModelProperty(value = "生化池-缺氧池SOUR")
    private BigDecimal dayAnoxicPoolSour;

    @ApiModelProperty(value = "生化池-缺氧池SV")
    private BigDecimal dayAnoxicPoolSv;

    @ApiModelProperty(value = "生化池-缺氧池MLSS")
    private BigDecimal dayAnoxicPoolMlss;

    @ApiModelProperty(value = "生化池-缺氧池温度")
    private BigDecimal dayAnoxicPoolTemper;

    @ApiModelProperty(value = "生化池-好氧池PH")
    private BigDecimal dayAerobicPoolPh;

    @ApiModelProperty(value = "生化池-好氧池ORP")
    private BigDecimal dayAerobicPoolOrp;

    @ApiModelProperty(value = "生化池-好氧池DO")
    private BigDecimal dayAerobicPoolDo;

    @ApiModelProperty(value = "生化池-好氧池SOUR")
    private BigDecimal dayAerobicPoolSour;

    @ApiModelProperty(value = "生化池-好氧池SV")
    private BigDecimal dayAerobicPoolSv;

    @ApiModelProperty(value = "生化池-好氧池MLSS")
    private BigDecimal dayAerobicPoolMlss;

    @ApiModelProperty(value = "生化池-好氧池MLVSS")
    private BigDecimal dayAerobicPoolMlvss;

    @ApiModelProperty(value = "生化池-好氧池SVI")
    private BigDecimal dayAerobicPoolSvi;

    @ApiModelProperty(value = "生化池-好氧池温度")
    private BigDecimal dayAerobicPoolTemper;

    @ApiModelProperty(value = "采集时间")
    private Instant dayTime;
}
