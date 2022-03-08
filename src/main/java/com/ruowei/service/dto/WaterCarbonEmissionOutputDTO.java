package com.ruowei.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WaterCarbonEmissionOutputDTO {

    /**
     * 一级处理药剂投加产生的间接碳排放
     */
    private BigDecimal c11;

    /**
     * 二级处理药剂投加产生的间接碳排放
     */
    private BigDecimal c12;

    /**
     * 三级处理药剂投加产生的间接碳排放
     */
    private BigDecimal c13;

    /**
     * 污泥处理药剂投加产生的间接碳排放
     */
    private BigDecimal c14;

    /**
     * 药剂投加产生的间接碳排放
     */
    private BigDecimal totalC1;

    /**
     * 进水总泵站耗电产生的间接碳排放
     */
    private BigDecimal c21;

    /**
     * 鼓风机房耗电产生的间接碳排放
     */
    private BigDecimal c22;

    /**
     * 回流污泥泵房耗电产生的间接碳排放
     */
    private BigDecimal c23;

    /**
     * 污泥处理用电耗电产生的间接碳排放
     */
    private BigDecimal c24;

    /**
     * 紫外+氯消毒耗电产生的间接碳排放
     */
    private BigDecimal c25;

    /**
     * 附属设施用电耗电产生的间接碳排放
     */
    private BigDecimal c26;

    /**
     * 其他耗电产生的间接碳排放
     */
    private BigDecimal c27;

    /**
     * 耗电产生的间接碳排放
     */
    private BigDecimal totalC2;

    /**
     * 污水处理CH4的CO2当量
     */
    private BigDecimal c31;

    /**
     * 污水处理N2O的CO2当量
     */
    private BigDecimal c32;

    /**
     * 污水处理的直接碳排放
     */
    private BigDecimal totalC3;

    /**
     * 污泥处置CH4的CO2当量
     */
    private BigDecimal c41;

    /**
     * 污泥处置N2O的CO2当量
     */
    private BigDecimal c42;

    /**
     * 污泥处置的直接碳排放
     */
    private BigDecimal totalC4;

    /**
     * 污泥处置药剂投加产生的间接碳排放
     */
    private BigDecimal c91;

    /**
     * 污泥处置用电产生的间接碳排放
     */
    private BigDecimal c92;

    /**
     * 污泥处置间接碳排放
     */
    private BigDecimal totalC9;

    /**
     * 太阳能间接减少的碳排放
     */
    private BigDecimal totalC5;

    /**
     * 热泵间接减少的碳排放
     */
    private BigDecimal totalC6;

    /**
     * 热电联产发电间接减少的碳排放
     */
    private BigDecimal totalC7;

    /**
     * 热电联产产热间接减少的碳排放
     */
    private BigDecimal totalC8;

    /**
     * 污水厂碳减排发电输送到电网的电量对应CO2减排量
     */
    private BigDecimal totalC10;

    /**
     * 其他碳减排项目减少的碳排放
     */
    private BigDecimal totalC11;

    /**
     * 风电间接减少的碳排放
     */
    private BigDecimal totalC12;

    /**
     * 生态综合体减少的碳排放
     */
    private BigDecimal totalC13;

    /**
     * 污水厂本月的碳排放
     */
    private BigDecimal c;

    /**
     * 污水厂本月碳减排排放
     */
    private BigDecimal cReduction;

    /**
     * 污水厂本月直接碳排放
     */
    private BigDecimal cDirect;

    /**
     * 污水厂本月间接碳排放
     */
    private BigDecimal cIndirect;
}
