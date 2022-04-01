package com.ruowei.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class AmmoniaNitrogen {

    private Instant time; //
    private BigDecimal in_tn; //进水IN
    private BigDecimal in_cod; //进水COD
    private BigDecimal in_ammomia; //进水氨氮
    private BigDecimal car_add; // 碳源投加量
    private BigDecimal aerobic_pool_do; //好氧池DO
    private BigDecimal aerobic_pool_temper; //好氧池水温
    private BigDecimal aerobic_pool_ph; //好氧池ph
    private BigDecimal aerobic_pool_sv; //好氧池sv
    private BigDecimal aerobic_pool_ss; //进水ss
    private BigDecimal second_mud; //出泥量
    private BigDecimal out_ammoni; //出水氨氮

}
