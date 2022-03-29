package com.ruowei.web.rest.vm;

import com.ruowei.domain.enumeration.SendStatusType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class ComprehensiveDataVM {

    //用于在集团中确定某个水厂
    @ApiModelProperty(value = "水厂编码")
    private String code;

    @ApiModelProperty(value = "采集时间")
    private Instant dayTime;

    @ApiModelProperty(value = "工艺类型编码")
    private String craftCode;

    //仪表数据
    @ApiModelProperty(value = "进水流量（mg/L）")
    private BigDecimal inFlow;

    @ApiModelProperty(value = "进水氨氮（mg/L）")
    private BigDecimal inAmmonia;

    @ApiModelProperty(value = "进水COD（mg/L）")
    private BigDecimal inCod;

    @ApiModelProperty(value = "进水TN（mg/L）")
    private BigDecimal inTn;

    @ApiModelProperty(value = "进水Tp（mg/L）")
    private BigDecimal inTp;

    @ApiModelProperty(value = "进水Ss（mg/L）")
    private BigDecimal inSs;

    @ApiModelProperty(value = "出水流量（mg/L）")
    private BigDecimal outFlow;

    @ApiModelProperty(value = "出水氨氮（mg/L）")
    private BigDecimal outAmmonia;

    @ApiModelProperty(value = "出水COD（mg/L）")
    private BigDecimal outCod;

    @ApiModelProperty(value = "出水TN（mg/L）")
    private BigDecimal outTn;

    @ApiModelProperty(value = "出水Tp（mg/L）")
    private BigDecimal outTp;

    @ApiModelProperty(value = "出水Ss（mg/L）")
    private BigDecimal outSs;

    @ApiModelProperty(value = "缺氧池（mg/L）")
    private BigDecimal anoxicPoolDo;

    @ApiModelProperty(value = "好氧池（mg/L）")
    private BigDecimal aerobicPoolDo;

    @ApiModelProperty(value = "缺氧池出口硝酸盐（mg/L）")
    private BigDecimal anoxicPoolDoOutNit;

    @ApiModelProperty(value = "好氧池出口亚硝酸盐（mg/L）")
    private BigDecimal aerobicPoolNit;

    //化验数据
    @ApiModelProperty(value = "污泥处置方法编码")
    private String methodCode;

    @ApiModelProperty(value = "污泥处置方法名称")
    private String methodName;

    @ApiModelProperty(value = "进水流量（mg/L）")
    private BigDecimal assInFlow;

    @ApiModelProperty(value = "进水氨氮（mg/L）")
    private BigDecimal assInAmmonia;

    @ApiModelProperty(value = "进水COD（mg/L）")
    private BigDecimal assInCod;

    @ApiModelProperty(value = "进水TN（mg/L）")
    private BigDecimal assInTn;

    @ApiModelProperty(value = "进水TP（mg/L）")
    private BigDecimal assInTp;

    @ApiModelProperty(value = "缺氧池出口硝酸盐（mg/L）")
    private BigDecimal assAnoxicPoolDoOutNit;

    @ApiModelProperty(value = "好氧池出口硝酸盐（mg/L）")
    private BigDecimal assAerobicPoolDoOutNit;

    @ApiModelProperty(value = "出水流量（mg/L）")
    private BigDecimal assOutFlow;

    @ApiModelProperty(value = "出水氨氮（mg/L）")
    private BigDecimal assOutAmmonia;

    @ApiModelProperty(value = "出水COD（mg/L）")
    private BigDecimal assOutCod;

    @ApiModelProperty(value = "出水TN（mg/L）")
    private BigDecimal assOutTn;

    @ApiModelProperty(value = "出水TP（mg/L）")
    private BigDecimal assOutTp;

    //日报表数据
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

    //校表数据
    @ApiModelProperty(value = "校表编码")
    private String meterCode;

    @ApiModelProperty(value = "进水氨氮（mg/L）")
    private BigDecimal corInAmmonia;

    @ApiModelProperty(value = "进水COD（mg/L）")
    private BigDecimal corInCod;

    @ApiModelProperty(value = "进水TN（mg/L）")
    private BigDecimal corInTn;

    @ApiModelProperty(value = "进水TP（mg/L）")
    private BigDecimal corInTp;

    @ApiModelProperty(value = "缺氧池出口硝酸盐（mg/L）")
    private BigDecimal corAnoxicPoolDoOutNit;

    @ApiModelProperty(value = "好氧池出口硝酸盐（mg/L）")
    private BigDecimal corAerobicPoolDoOutNit;

    @ApiModelProperty(value = "出水氨氮（mg/L）")
    private BigDecimal corOutAmmonia;

    @ApiModelProperty(value = "出水COD（mg/L）")
    private BigDecimal corOutCod;

    @ApiModelProperty(value = "出水TN（mg/L）")
    private BigDecimal corOutTn;

    @ApiModelProperty(value = "出水TP（mg/L）")
    private BigDecimal corOutTp;
}
