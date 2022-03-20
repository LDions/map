package com.ruowei.web.rest.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MqReceiveDTO {

    /**
     * 单据号
     */
    private String documentCode;

    /*
      用户输入项
     */

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

        /**
         * 日均规模
         */
        private BigDecimal q;

        /**
         * 本月运行天数
         */
        private BigDecimal day;

        /**
         * 所选择工艺对应的N2O-N排放因子
         */
        private BigDecimal ni;

        /**
         * 进水总氮（mg/L）
         */
        private BigDecimal tnI;

        /**
         * 进水COD（mg/L）
         */
        private BigDecimal codI;

        /**
         * 进水氨氮（mg/L）
         */
        private BigDecimal nh3nI;

        /**
         * 进水总磷（mg/L）
         */
        private BigDecimal tpI;

        /**
         * 进水BOD（mg/L）（选填）
         */
        private BigDecimal bodI;

        /**
         * 出水总氮（mg/L）
         */
        private BigDecimal tnO;

        /**
         * 出水COD（mg/L）
         */
        private BigDecimal codO;

        /**
         * 出水氨氮（mg/L）
         */
        private BigDecimal nh3nO;

        /**
         * 出水总磷（mg/L）
         */
        private BigDecimal tpO;

        /**
         * 出水BOD（mg/L）（选填）
         */
        private BigDecimal bodO;

        public WaterQualityDTO(
            BigDecimal q,
            BigDecimal day,
            BigDecimal ni,
            BigDecimal tnI,
            BigDecimal codI,
            BigDecimal nh3nI,
            BigDecimal tpI,
            BigDecimal bodI,
            BigDecimal tnO,
            BigDecimal codO,
            BigDecimal nh3nO,
            BigDecimal tpO,
            BigDecimal bodO
        ) {
            this.q = q;
            this.day = day;
            this.ni = ni;
            this.tnI = tnI;
            this.codI = codI;
            this.nh3nI = nh3nI;
            this.tpI = tpI;
            this.bodI = bodI;
            this.tnO = tnO;
            this.codO = codO;
            this.nh3nO = nh3nO;
            this.tpO = tpO;
            this.bodO = bodO;
        }
    }

    /**
     * 化验数据
     */
    @Data
    public static class ElectricityDTO {

        /**
         * 月度总耗电量（kWh/mth）
         */
        private BigDecimal e;

        /**
         * 月度净购入的热力消费量（GJ/mth）
         */
        private BigDecimal ad;

        /**
         * 进水总泵站（kWh/mth）（选填）
         */
        private BigDecimal e1;

        /**
         * 鼓风机房（kWh/mth）（选填）
         */
        private BigDecimal e2;

        /**
         * 回流污泥泵房（kWh/mth）（选填）
         */
        private BigDecimal e3;

        /**
         * 污泥脱水用电（kWh/mth）（选填）
         */
        private BigDecimal e4;

        /**
         * 紫外+氯消毒（kWh/mth）（选填）
         */
        private BigDecimal e5;

        /**
         * 深度处理用电（kWh/mth）（选填）
         */
        private BigDecimal e6;

        /**
         * 生活区耗电（kWh/mth）（选填）
         */
        private BigDecimal eLiving;

        /**
         * 其他（kWh/mth）（选填）
         */
        private BigDecimal e7;

        /**
         * 污泥处理与污泥处置用电（kWh/mth）（选填）
         */
        private BigDecimal e8;

        public ElectricityDTO(
            BigDecimal e,
            BigDecimal ad,
            BigDecimal e1,
            BigDecimal e2,
            BigDecimal e3,
            BigDecimal e4,
            BigDecimal e5,
            BigDecimal e6,
            BigDecimal eLiving,
            BigDecimal e7,
            BigDecimal e8
        ) {
            this.e = e;
            this.ad = ad;
            this.e1 = e1;
            this.e2 = e2;
            this.e3 = e3;
            this.e4 = e4;
            this.e5 = e5;
            this.e6 = e6;
            this.eLiving = eLiving;
            this.e7 = e7;
            this.e8 = e8;
        }
    }

    /**
     * 日报数据
     */
    @Data
    public static class PharmacyDTO {

        /**
         * 药剂月投加量（kg/mth）
         */
        private BigDecimal m;

        /**
         * 一级处理药剂月投加量（kg/mth）
         */
        private BigDecimal m1;

        /**
         * 二级处理药剂月投加量（kg/mth）
         */
        private BigDecimal m2;

        /**
         * 三级处理药剂月投加量（kg/mth）
         */
        private BigDecimal m3;

        /**
         * 污泥脱水药剂月投加量（kg/mth）
         */
        private BigDecimal m4;

        /**
         * 污泥处理与污泥处置药剂月投加量（kg/mth）
         */
        private BigDecimal m5;

        /**
         * 药剂CO2排放因子
         */
        private BigDecimal cfM;

        public PharmacyDTO(BigDecimal m, BigDecimal m1, BigDecimal m2, BigDecimal m3, BigDecimal m4, BigDecimal m5, BigDecimal cfM) {
            this.m = m;
            this.m1 = m1;
            this.m2 = m2;
            this.m3 = m3;
            this.m4 = m4;
            this.m5 = m5;
            this.cfM = cfM;
        }
    }
}
