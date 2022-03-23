package com.ruowei.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

@Data
public class AmmoniaNitrogen {

    private DateTimeFormat time; //
    private BigDecimal in_tn; //进水IN
    private BigDecimal in_cod; //进水COD
    private BigDecimal in_ammomia; //进水氨氮
    private BigDecimal car_add; // 碳源投加量
    private BigDecimal aerobic_pool_do; //好氧池DO
    private BigDecimal aerobic_pool_temper; //好氧池水温
    private BigDecimal aerobic_pool_ph; //好氧池ph
    private BigDecimal aerobic_pool_sv; //好氧池sv
    private BigDecimal aerobic_pool_ss; //好氧池ss
    private BigDecimal second_mud; //出泥量
    private BigDecimal out_ammoni; //出水氨氮

}
