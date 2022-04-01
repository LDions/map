package com.ruowei.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MqEnterDTO {

    /**
     * 仪表数据
     */
    private List<SewProcessDTO> gaugeDate;

    /**
     * 日报数据
     */
    private List<SewPotDTO> dailyDate;


    /**
     * 仪表数据
     */
    @Data
    public static class SewProcessDTO {

    }

    /**
     * 日报数据
     */
    @Data
    public static class SewPotDTO {

    }


}
