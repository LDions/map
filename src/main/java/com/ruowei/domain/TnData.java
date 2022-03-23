package com.ruowei.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

@Data
public class TnData {
    private DateTimeFormat time;
    private BigDecimal in_tn; //进水IN
    private BigDecimal in_cod; //进水COD
    private BigDecimal in_flow; //进水流量
    private BigDecimal anaerobic_pool_do; //厌氧池DO
    private BigDecimal car_add; // 碳源投加量
    private BigDecimal anoxic_pool_do_out_nit; //缺氧出口硝酸盐
    private BigDecimal aerobic_pol_nit; //好氧出口硝酸盐
    private BigDecimal aerobic_pool_do; //好氧池DO
    private BigDecimal aerobic_pool_temper; //好氧池水温
    private BigDecimal aerobic_pool_mlss; //好氧池MLSS
    private BigDecimal out_tn; //出水IN
}
