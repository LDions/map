package com.ruowei.web.rest.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MqReceiveDTO {

    /**
     * 仪表数据
     */
    private List<WaterQualityDTO> waterQualityDTOList;

    /**
     * 化验数据
     */
    private List<ElectricityDTO> fuelDTOList;

    /**
     * 日报数据
     */
    private List<PharmacyDTO> pharmacyDTOList;

    /**
     * 水厂ID
     */
    private Long enterpriseId;


    /**
     * 仪表数据
     */
    @Data
    public static class WaterQualityDTO {

    }

    /**
     * 化验数据
     */
    @Data
    public static class ElectricityDTO {

    }

    /**
     * 日报数据
     */
    @Data
    public static class PharmacyDTO {


    }
}
