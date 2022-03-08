package com.ruowei.service.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class SewEmiFactorDTO {

    /**
     * 字段含义解释
     */
    private List<Explain> explain;

    /**
     * 药剂CO2排放因子
     */
    private LinkedHashMap<String, Info> chemicalsEmiFactor;

    /**
     * 省市电网平均CO2排放因子
     */
    private LinkedHashMap<String, InfoPro> proElecEmiFactor;

    /**
     * 工艺类型排放因子
     */
    private LinkedHashMap<String, Info> processTypeNi;

    /**
     * 热泵对应参数
     */
    private HeatPumpFactor heatPumpFactor;

    /**
     * 气体排放系数
     */
    private GasEmiFactor gasEmiFactor;

    /**
     * 污水处理排放系数
     */
    private SewTreatFactor sewTreatFactor;

    /**
     * 污泥处理排放系数
     */
    private SluTreatFactor sluTreatFactor;


    @Data
    public static class Info {
        private String name;

        private String title;

        private BigDecimal value;

        private String unit;

        private String label;

        public Info() {
        }

        public Info(String name, String title, BigDecimal value, String unit, String label) {
            this.name = name;
            this.title = title;
            this.value = value;
            this.unit = unit;
            this.label = label;
        }
    }

    @Data
    public static class InfoPro {
        private String name;

        private String title;

        private String code;

        private BigDecimal value;

        private String unit;

        private String label;

        public InfoPro() {
        }

        public InfoPro(String name, String title, String code, BigDecimal value, String unit, String label) {
            this.name = name;
            this.title = title;
            this.code = code;
            this.value = value;
            this.unit = unit;
            this.label = label;
        }
    }

    @Data
    public static class Explain {
        private String code;

        private String name;

        public Explain() {
        }

        public Explain(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }

    @Data
    public static class SluTreatFactor {

        /**
         * 污泥处理工艺污泥含水率
         */
        private Info wc;

        /**
         * 堆肥CH4排放系数
         */
        private Info efs1;

        /**
         * 厌氧消化CH4排放系数
         */
        private Info efs2;

        /**
         * 污泥脱水后填埋CH4排放系数DOC
         */
        private Info doc;

        /**
         * 污泥脱水后填埋CH4排放系数DOCf
         */
        private Info docf;

        /**
         * 污泥脱水后填埋CH4排放系数MCF
         */
        private Info mcf;

        /**
         * 污泥脱水后填埋CH4排放系数F
         */
        private Info f;

        /**
         * 污泥脱水后填埋CH4排放系数OX
         */
        private Info ox;

        /**
         * 堆肥N2O排放系数
         */
        private Info efs3;

        /**
         * 厌氧消化N2O排放系数
         */
        private Info efs4;

        public Info getWc() {
            return wc;
        }

        public void setWc(Info wc) {
            this.wc = wc;
        }

        @JsonGetter("EFS_1")
        public Info getEfs1() {
            return efs1;
        }

        @JsonSetter("EFS_1")
        public void setEfs1(Info efs1) {
            this.efs1 = efs1;
        }

        @JsonGetter("EFS_2")
        public Info getEfs2() {
            return efs2;
        }

        @JsonSetter("EFS_2")
        public void setEfs2(Info efs2) {
            this.efs2 = efs2;
        }

        @JsonGetter("DOC")
        public Info getDoc() {
            return doc;
        }

        @JsonSetter("DOC")
        public void setDoc(Info doc) {
            this.doc = doc;
        }

        @JsonGetter("DOCf")
        public Info getDocf() {
            return docf;
        }

        @JsonSetter("DOCf")
        public void setDocf(Info docf) {
            this.docf = docf;
        }

        @JsonGetter("MCF")
        public Info getMcf() {
            return mcf;
        }

        @JsonSetter("MCF")
        public void setMcf(Info mcf) {
            this.mcf = mcf;
        }

        @JsonGetter("F")
        public Info getF() {
            return f;
        }

        @JsonSetter("F")
        public void setF(Info f) {
            this.f = f;
        }

        @JsonGetter("OX")
        public Info getOx() {
            return ox;
        }

        @JsonSetter("OX")
        public void setOx(Info ox) {
            this.ox = ox;
        }

        @JsonGetter("EFS_3")
        public Info getEfs3() {
            return efs3;
        }

        @JsonSetter("EFS_3")
        public void setEfs3(Info efs3) {
            this.efs3 = efs3;
        }

        @JsonGetter("EFS_4")
        public Info getEfs4() {
            return efs4;
        }

        @JsonSetter("EFS_4")
        public void setEfs4(Info efs4) {
            this.efs4 = efs4;
        }
    }

    public static class HeatPumpFactor {
        /**
         * 热电折算系数
         */
        private Info y;

        public Info getY() {
            return y;
        }

        public void setY(Info y) {
            this.y = y;
        }
    }

    public static class GasEmiFactor {
        /**
         * 燃煤锅炉二氧化碳排放因子
         */
        private Info cfhp;

        /**
         * CO2转CO2气体排放因子
         */
        private Info cfco2;

        /**
         * CH4转CO2气体排放因子
         */
        private Info cfch4;

        /**
         * N2O转CO2气体排放因子
         */
        private Info cfn2o;

        @JsonGetter("CF_HP")
        public Info getCfhp() {
            return cfhp;
        }

        @JsonSetter("CF_HP")
        public void setCfhp(Info cfhp) {
            this.cfhp = cfhp;
        }

        @JsonGetter("CF_CO2")
        public Info getCfco2() {
            return cfco2;
        }

        @JsonSetter("CF_CO2")
        public void setCfco2(Info cfco2) {
            this.cfco2 = cfco2;
        }

        @JsonGetter("CF_CH4")
        public Info getCfch4() {
            return cfch4;
        }

        @JsonSetter("CF_CH4")
        public void setCfch4(Info cfch4) {
            this.cfch4 = cfch4;
        }

        @JsonGetter("CF_N2O")
        public Info getCfn2o() {
            return cfn2o;
        }

        @JsonSetter("CF_N2O")
        public void setCfn2o(Info cfn2o) {
            this.cfn2o = cfn2o;
        }
    }

    public static class SewTreatFactor {
        /**
         * 以BOD计算污水处理CH4排放最大生产能力
         */
        private Info b0bod;

        /**
         * 以COD计算污水处理CH4排放最大生产能力
         */
        private Info b0cod;

        /**
         * CH4修正因子
         */
        private Info mcf;

        @JsonGetter("B0_BOD")
        public Info getB0bod() {
            return b0bod;
        }

        @JsonSetter("B0_BOD")
        public void setB0bod(Info b0bod) {
            this.b0bod = b0bod;
        }

        @JsonGetter("B0_COD")
        public Info getB0cod() {
            return b0cod;
        }

        @JsonSetter("B0_COD")
        public void setB0cod(Info b0cod) {
            this.b0cod = b0cod;
        }

        @JsonGetter("MCF")
        public Info getMcf() {
            return mcf;
        }

        @JsonSetter("MCF")
        public void setMcf(Info mcf) {
            this.mcf = mcf;
        }
    }
}
