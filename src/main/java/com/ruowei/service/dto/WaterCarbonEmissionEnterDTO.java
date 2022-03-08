package com.ruowei.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class WaterCarbonEmissionEnterDTO {

    /**
     * 碳排放因子版本号
     */
    private String factorVersionNum;

    /*
      用户输入项
     */

    /**
     * 水质信息
     */
    private List<WaterQualityDTO> waterQualityDTOList;

    /**
     * 电量信息
     */
    private ElectricityDTO electricityDTO;

    /**
     * 药剂月投量信息
     */
    private List<PharmacyDTO> pharmacyDTOList;

    /**
     * 碳减排信息
     */
    private CarbonNegativeDTO carbonNegativeDTO;

    /**
     * STech1污泥处置量（kg/mth）
     */
    private BigDecimal mSludge1;

    /**
     * STech1污泥处置前含水率（%）
     */
    private BigDecimal wc1;

    /**
     * STech2污泥处置量（kg/mth）
     */
    private BigDecimal mSludge2;

    /**
     * STech2污泥处置前含水率（%）
     */
    private BigDecimal wc2;

    /**
     * STech3污泥处置量（kg/mth）
     */
    private BigDecimal mSludge3;

    /**
     * STech3污泥处置前含水率（%）
     */
    private BigDecimal wc3;

    /**
     * OOTher1其他指标量（kg/mth）
     */
    private BigDecimal odeg1;

    /**
     * OOTher2其他指标量（kg/mth）
     */
    private BigDecimal odeg2;

    /**
     * OOTher3其他指标量（kg/mth）
     */
    private BigDecimal odeg3;

    /**
     * OOTher4其他指标量（kg/mth）
     */
    private BigDecimal odeg4;

    /**
     * OOTher5其他指标量（kg/mth）
     */
    private BigDecimal odeg5;

    /**
     * OOTher6其他指标量（kg/mth）
     */
    private BigDecimal odeg6;

    /**
     * OOTher7其他指标量（kg/mth）
     */
    private BigDecimal odeg7;

    /**
     * OOTher8其他指标量（kg/mth）
     */
    private BigDecimal odeg8;

    /**
     * OOTher9其他指标量（kg/mth）
     */
    private BigDecimal odeg9;


    /**
     * 污泥处置是否为本厂管理
     */
    private Boolean managedBySelf;

    /*
      因子项
     */

    /**
     * CO2排放因子，根据所选城市获取
     */
    private BigDecimal cfE;

    /**
     * 燃煤锅炉CO2排放因子
     */
    private BigDecimal cfHp;

    /**
     * CO2转CO2气体排放因子
     */
    private BigDecimal cfCo2;

    /**
     * CH4转CO2气体排放因子
     */
    private BigDecimal cfCh4;

    /**
     * N20转CO2气体排放因子
     */
    private BigDecimal cfN2o;

    /**
     * 以BOD计算污水处理CH4排放最大生产能力系数
     */
    private BigDecimal b0Bod;

    /**
     * 以COD计算污水处理CH4排放最大生产能力系数
     */
    private BigDecimal b0Cod;

    /**
     * CH4修正因子_集中好氧处理厂
     */
    private BigDecimal mcf;

    /**
     * 燃气电厂供热的热电折算系数
     */
    private BigDecimal gama;

    /**
     * STech 污泥含水率
     */
    private BigDecimal wc;

    /**
     * STech1 CH4排放系数
     */
    private BigDecimal efs1;

    /**
     * STech2 CH4排放系数
     */
    private BigDecimal efs2;

    /**
     * STech3 CH4排放系数DOC
     */
    private BigDecimal sTech3Doc;

    /**
     * STech3 CH4排放系数DOCf
     */
    private BigDecimal sTech3Docf;

    /**
     * STech3 CH4排放系数MCF
     */
    private BigDecimal sTech3Mcf;

    /**
     * STech3 CH4排放系数F
     */
    private BigDecimal sTech3F;

    /**
     * STech3 CH4排放系数OX
     */
    private BigDecimal sTech3Ox;

    /**
     * STech1 N2O排放系数
     */
    private BigDecimal efs3;

    /**
     * STech2 N2O排放系数
     */
    private BigDecimal efs4;

    /**
     * 水质信息
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

        public WaterQualityDTO(BigDecimal q, BigDecimal day, BigDecimal ni, BigDecimal tnI, BigDecimal codI, BigDecimal nh3nI, BigDecimal tpI, BigDecimal bodI, BigDecimal tnO, BigDecimal codO, BigDecimal nh3nO, BigDecimal tpO, BigDecimal bodO) {
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
     * 电量信息
     */
    @Data
    public static class ElectricityDTO {
        /**
         * 月度总耗电量（kWh/mth）
         */
        private BigDecimal e;

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
         * 污泥处理用电（kWh/mth）（选填）
         */
        private BigDecimal e4;

        /**
         * 紫外+氯消毒（kWh/mth）（选填）
         */
        private BigDecimal e5;

        /**
         * 附属设施用电（kWh/mth）（选填）
         */
        private BigDecimal e6;

        /**
         * 其他（kWh/mth）（选填）
         */
        private BigDecimal e7;

        /**
         * 污泥处置用电（kWh/mth）（选填）
         */
        private BigDecimal e8;

        public ElectricityDTO(BigDecimal e, BigDecimal e1, BigDecimal e2, BigDecimal e3, BigDecimal e4, BigDecimal e5, BigDecimal e6, BigDecimal e7, BigDecimal e8) {
            this.e = e;
            this.e1 = e1;
            this.e2 = e2;
            this.e3 = e3;
            this.e4 = e4;
            this.e5 = e5;
            this.e6 = e6;
            this.e7 = e7;
            this.e8 = e8;
        }
    }

    /**
     * 药剂月投量信息
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
         * 污泥处理药剂月投加量（kg/mth）
         */
        private BigDecimal m4;

        /**
         * 污泥处置药剂月投加量（kg/mth）
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

    /**
     * 碳减排信息
     */
    @Data
    public static class CarbonNegativeDTO {
        /**
         * 太阳能年发电量
         */
        private BigDecimal e8;

        /**
         * 月热泵供热量
         */
        private BigDecimal ah;

        /**
         * 月热泵制冷量
         */
        private BigDecimal ac;

        /**
         * 热泵供热运行时间
         */
        private BigDecimal thot;

        /**
         * 热泵制冷运行时间
         */
        private BigDecimal tcold;

        /**
         * 月产电量
         */
        private BigDecimal e9;

        /**
         * 月产生的热能
         */
        private BigDecimal e10;

        /**
         * 碳减排发电输送到电网的电量
         */
        private BigDecimal e11;

        /**
         * 其他碳减排项目减排量
         */
        private BigDecimal co27;

        /**
         * 风能月发电量
         */
        private BigDecimal e12;

        /**
         * 生态综合体碳减排量
         */
        private BigDecimal co28;

        public CarbonNegativeDTO(BigDecimal e8, BigDecimal ah, BigDecimal ac, BigDecimal thot, BigDecimal tcold, BigDecimal e9, BigDecimal e10, BigDecimal e11, BigDecimal co27, BigDecimal e12, BigDecimal co28) {
            this.e8 = e8;
            this.ah = ah;
            this.ac = ac;
            this.thot = thot;
            this.tcold = tcold;
            this.e9 = e9;
            this.e10 = e10;
            this.e11 = e11;
            this.co27 = co27;
            this.e12 = e12;
            this.co28 = co28;
        }
    }
}
