package com.ruowei.domain;


/**
 * 工艺段信息
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;

@ApiModel(description = "工艺信息")
@Entity
@Table(name = "sew_craft")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data

public class Craft {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 工艺名称
     */
    @ApiModelProperty(value = "工艺名称")
    @Column(name = "craft_name")
    private String craftName;

    /**
     * 工艺编码
     */
    @ApiModelProperty(value = "工艺编码")
    @Column(name = "craft_code")
    private String craftCode;

    /**
     * 厌氧池池容
     */
    @ApiModelProperty(value = "厌氧池池容")
    @Column(name = "anaerobic_pool_volume")
    private BigDecimal anaerobicPoolVolume;

    /**
     * 缺氧池池容
     */
    @ApiModelProperty(value = "缺氧池池容")
    @Column(name = "anoxic_pool_volume")
    private BigDecimal anoxicPoolVolume;

    /**
     * 好氧池池容
     */
    @ApiModelProperty(value = "好氧池池容")
    @Column(name = "aerobic_pool_volume")
    private BigDecimal aerobicPoolVolume;

    /**
     * 内回流泵流量
     */
    @ApiModelProperty(value = "内回流泵流量")
    @Column(name = "in_reflux_flow")
    private BigDecimal inRefluxFlow;

    /**
     * 内回流泵台数
     */
    @ApiModelProperty(value = "内回流泵台数")
    @Column(name = "in_reflux_num")
    private BigDecimal inRefluxNum;

    /**
     * 外回流泵流量
     */
    @ApiModelProperty(value = "外回流泵流量")
    @Column(name = "out_reflux_flow")
    private BigDecimal outRefluxFlow;

    /**
     * 外回流泵台数
     */
    @ApiModelProperty(value = "外回流泵台数")
    @Column(name = "out_reflux_num")
    private BigDecimal outRefluxNum;

    /**
     * 传统公式：好氧区硝酸盐浓度
     */
    @ApiModelProperty(value = "传统公式：好氧区硝酸盐浓度")
    @Column(name = "aerobiotic_nitrate_concentration")
    private BigDecimal aerobioticNitrateConcentration;

    /**
     * 传统公式：缺氧区硝酸盐浓度
     */
    @ApiModelProperty(value = "传统公式：缺氧区硝酸盐浓度")
    @Column(name = "anoxia_nitrate_concentration")
    private BigDecimal anoxiaNitrateConcentration;

    /**
     * 传统公式：BOD/COD
     */
    @ApiModelProperty(value = "传统公式：BOD/COD")
    @Column(name = "bod_cod_ratio")
    private BigDecimal bodCodRatio;

    /**
     * 传统公式：COD校准参数
     */
    @ApiModelProperty(value = "传统公式：COD校准参数")
    @Column(name = "cod_calibration")
    private BigDecimal codCalibration;

    /**
     * 传统公式：BOD/N
     */
    @ApiModelProperty(value = "传统公式：BOD/N")
    @Column(name = "bod_n_ratio")
    private BigDecimal bodNRatio;

    /**
     * 传统公式：BOD当量
     */
    @ApiModelProperty(value = "传统公式：BOD当量")
    @Column(name = "bod_equivalent_weight")
    private BigDecimal bodEquivalentWeight;

    /**
     * 传统公式：碳源稀释倍数
     */
    @ApiModelProperty(value = "传统公式：碳源稀释倍数")
    @Column(name = "dilution_ratio")
    private BigDecimal dilutionRatio;

    /**
     * 传统公式：外加碳源相对亲密度
     */
    @ApiModelProperty(value = "传统公式：外加碳源相对亲密度")
    @Column(name = "intimacy")
    private BigDecimal intimacy;

    /**
     * 除磷公式：二沉池出水正磷酸盐设定值
     */
    @ApiModelProperty(value = "除磷公式：二沉池出水正磷酸盐设定值 ")
    @Column(name = "phosphate")
    private BigDecimal phosphate;

    /**
     * 除磷公式：铁盐或铝盐的摩尔质量比
     */
    @ApiModelProperty(value = "除磷公式：铁盐或铝盐的摩尔质量比")
    @Column(name = "fe_al_ratio ")
    private BigDecimal FeAlRatio;

    /**
     * 除磷公式：投加系数
     */
    @ApiModelProperty(value = "除磷公式：投加系数")
    @Column(name = "phosphorus_dosing")
    private BigDecimal phosphorusDosing;

    /**
     * 除磷公式：铁盐或铝盐的有效成分
     */
    @ApiModelProperty(value = "除磷公式：铁盐或铝盐的有效成分")
    @Column(name = "fe_al_active_ingredients")
    private BigDecimal feAlActiveIngredients;

    /**
     * 除磷公式：除磷药剂配药浓度或者相对密度
     */
    @ApiModelProperty(value = "除磷公式：除磷药剂配药浓度或者相对密度  ")
    @Column(name = "concentration")
    private BigDecimal concentration;

    /**
     * 传统公式:硝化液回流比
     */
    @ApiModelProperty(value = "传统公式:硝化液回流比  ")
    @Column(name = "nitrate_reflux_ratio")
    private BigDecimal nitrateRefluxRatio;

    /**
     * 上属水厂code
     */
    @ApiModelProperty(value = "上属水厂code")
    @Column(name = "ent_code")
    private String entCode;

    /**
     * 上属水厂名称
     */
    @ApiModelProperty(value = "上属水厂名称")
    @Column(name = "ent_name")
    private String entName;

    public Long getId() {
        return id;
    }

    public Craft id(Long id) {
        this.id = id;
        return this;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCraftCode() {
        return craftCode;
    }
    public Craft craftCode(String craftCode) {
        this.craftCode = craftCode;
        return this;
    }

    public void setCraftCode(String craftCode) {
        this.craftCode = craftCode;
    }

    public String getCraftName() {
        return craftName;
    }

    public Craft craftName(String craftName) {
        this.craftName = craftName;
        return this;
    }

    public void setCraftName(String craftName) {
        this.craftName = craftName;
    }

    public BigDecimal getAerobioticNitrateConcentration() {
        return aerobioticNitrateConcentration;
    }

    public Craft aerobioticNitrateConcentration(BigDecimal aerobioticNitrateConcentration) {
        this.aerobioticNitrateConcentration = aerobioticNitrateConcentration;
        return this;
    }

    public void setAerobioticNitrateConcentration(BigDecimal aerobioticNitrateConcentration) {
        this.aerobioticNitrateConcentration = aerobioticNitrateConcentration;
    }

    public BigDecimal getAnoxiaNitrateConcentration() {
        return anoxiaNitrateConcentration;
    }

    public Craft anoxiaNitrateConcentration(BigDecimal anoxiaNitrateConcentration) {
        this.anoxiaNitrateConcentration = anoxiaNitrateConcentration;
        return this;
    }

    public void setAnoxiaNitrateConcentration(BigDecimal anoxiaNitrateConcentration) {
        this.anoxiaNitrateConcentration = anoxiaNitrateConcentration;
    }

    public BigDecimal getBodCodRatio() {
        return bodCodRatio;
    }

    public Craft bodCodRatio(BigDecimal bodCodRatio) {
        this.bodCodRatio = bodCodRatio;
        return this;
    }

    public void setBodCodRatio(BigDecimal bodCodRatio) {
        this.bodCodRatio = bodCodRatio;
    }

    public BigDecimal getCodCalibration() {
        return codCalibration;
    }

    public Craft codCalibration(BigDecimal codCalibration) {
        this.codCalibration = codCalibration;
        return this;
    }

    public void setCodCalibration(BigDecimal codCalibration) {
        this.codCalibration = codCalibration;
    }

    public BigDecimal getBodNRatio() {
        return bodNRatio;
    }

    public Craft bodNRatio(BigDecimal bodNRatio) {
        this.bodNRatio = bodNRatio;
        return this;
    }

    public void setBodNRatio(BigDecimal bodNRatio) {
        this.bodNRatio = bodNRatio;
    }

    public BigDecimal getBodEquivalentWeight() {
        return bodEquivalentWeight;
    }

    public Craft bodEquivalentWeight(BigDecimal bodEquivalentWeight) {
        this.bodEquivalentWeight = bodEquivalentWeight;
        return this;
    }

    public void setBodEquivalentWeight(BigDecimal bodEquivalentWeight) {
        this.bodEquivalentWeight = bodEquivalentWeight;
    }

    public BigDecimal getIntimacy() {
        return intimacy;
    }

    public Craft intimacy(BigDecimal intimacy) {
        this.intimacy = intimacy;
        return this;
    }

    public void setIntimacy(BigDecimal intimacy) {
        this.intimacy = intimacy;
    }

    public String getEntCode() {
        return entCode;
    }

    public Craft entCode(String entCode) {
        this.entCode = entCode;
        return this;
    }

    public void setEntCode(String entCode) {
        this.entCode = entCode;
    }

    public BigDecimal getDilutionRatio() {
        return dilutionRatio;
    }

    public Craft dilutionRatio(BigDecimal dilutionRatio) {
        this.dilutionRatio = dilutionRatio;
        return this;
    }

    public void setDilutionRatio(BigDecimal dilutionRatio) {
        this.dilutionRatio = dilutionRatio;
    }

    public BigDecimal getPhosphate() {
        return phosphate;
    }

    public Craft phosphate(BigDecimal phosphate) {
        this.phosphate = phosphate;
        return this;
    }

    public void setPhosphate(BigDecimal phosphate) {
        this.phosphate = phosphate;
    }

    public BigDecimal getFeAlRatio() {
        return FeAlRatio;
    }

    public Craft FeAlRatio(BigDecimal FeAlRatio) {
        this.FeAlRatio = FeAlRatio;
        return this;
    }

    public void setFeAlRatio(BigDecimal feAlRatio) {
        FeAlRatio = feAlRatio;
    }

    public BigDecimal getPhosphorusDosing() {
        return phosphorusDosing;
    }

    public Craft phosphorusDosing(BigDecimal phosphorusDosing) {
        this.phosphorusDosing = phosphorusDosing;
        return this;
    }

    public void setPhosphorusDosing(BigDecimal phosphorusDosing) {
        this.phosphorusDosing = phosphorusDosing;
    }

    public BigDecimal getFeAlActiveIngredients() {
        return feAlActiveIngredients;
    }

    public Craft feAlActiveIngredients(BigDecimal feAlActiveIngredients) {
        this.feAlActiveIngredients = feAlActiveIngredients;
        return this;
    }

    public void setFeAlActiveIngredients(BigDecimal feAlActiveIngredients) {
        this.feAlActiveIngredients = feAlActiveIngredients;
    }

    public BigDecimal getConcentration() {
        return concentration;
    }

    public Craft concentration(BigDecimal concentration) {
        this.concentration = concentration;
        return this;
    }

    public void setConcentration(BigDecimal concentration) {
        this.concentration = concentration;
    }

    public BigDecimal nitrateRefluxRatio() {
        return nitrateRefluxRatio;
    }

    public Craft nitrateRefluxRatio(BigDecimal nitrateRefluxRatio) {
        this.nitrateRefluxRatio = nitrateRefluxRatio;
        return this;
    }

    public void setNitrateRefluxRatio(BigDecimal nitrateRefluxRatio) {
        this.nitrateRefluxRatio = nitrateRefluxRatio;
    }

    public String getEntName() {
        return entName;
    }

    public Craft entName(String entName) {
        this.entName = entName;
        return this;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public BigDecimal getAnaerobicPoolVolume() {
        return anaerobicPoolVolume;
    }
    public Craft anaerobicPoolVolume(BigDecimal anaerobicPoolVolume) {
        this.anaerobicPoolVolume = anaerobicPoolVolume;
        return this;
    }
    public void setAnaerobicPoolVolume(BigDecimal anaerobicPoolVolume) {
        this.anaerobicPoolVolume = anaerobicPoolVolume;
    }

    public BigDecimal getAnoxicPoolVolume() {
        return anoxicPoolVolume;
    }
    public Craft anoxicPoolVolume(BigDecimal anoxicPoolVolume) {
        this.anoxicPoolVolume = anoxicPoolVolume;
        return this;
    }
    public void setAnoxicPoolVolume(BigDecimal anoxicPoolVolume) {
        this.anoxicPoolVolume = anoxicPoolVolume;
    }

    public BigDecimal getAerobicPoolVolume() {
        return aerobicPoolVolume;
    }
    public Craft aerobicPoolVolume(BigDecimal aerobicPoolVolume) {
        this.aerobicPoolVolume = aerobicPoolVolume;
        return this;
    }
    public void setAerobicPoolVolume(BigDecimal aerobicPoolVolume) {
        this.aerobicPoolVolume = aerobicPoolVolume;
    }

    public BigDecimal getInRefluxFlow() {
        return inRefluxFlow;
    }
    public Craft inRefluxFlow(BigDecimal inRefluxFlow) {
        this.inRefluxFlow = inRefluxFlow;
        return this;
    }
    public void setInRefluxFlow(BigDecimal inRefluxFlow) {
        this.inRefluxFlow = inRefluxFlow;
    }

    public BigDecimal getInRefluxNum() {
        return inRefluxNum;
    }
    public Craft inRefluxNum(BigDecimal inRefluxNum) {
        this.inRefluxNum = inRefluxNum;
        return this;
    }
    public void setInRefluxNum(BigDecimal inRefluxNum) {
        this.inRefluxNum = inRefluxNum;
    }

    public BigDecimal getOutRefluxFlow() {
        return outRefluxFlow;
    }
    public Craft outRefluxFlow(BigDecimal outRefluxFlow) {
        this.outRefluxFlow = outRefluxFlow;
        return this;
    }
    public void setOutRefluxFlow(BigDecimal outRefluxFlow) {
        this.outRefluxFlow = outRefluxFlow;
    }

    public BigDecimal getOutRefluxNum() {
        return outRefluxNum;
    }
    public Craft outRefluxNum(BigDecimal outRefluxNum) {
        this.outRefluxNum = outRefluxNum;
        return this;
    }
    public void setOutRefluxNum(BigDecimal outRefluxNum) {
        this.outRefluxNum = outRefluxNum;
    }
    public Craft entName(BigDecimal nitrateRefluxRatio) {
        this.nitrateRefluxRatio = nitrateRefluxRatio;
        return this;
    }
    public BigDecimal getNitrateRefluxRatio() {
        return nitrateRefluxRatio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Craft)) {
            return false;
        }
        return id != null && id.equals(((Craft) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Craft{" +
            "id=" + id +
            ", craftName='" + craftName + '\'' +
            ", craftCode='" + craftCode + '\'' +
            ", anaerobicPoolVolume='" + anaerobicPoolVolume + '\'' +
            ", anoxicPoolVolume=" + anoxicPoolVolume +
            ", aerobicPoolVolume=" + aerobicPoolVolume +
            ", inRefluxFlow=" + inRefluxFlow +
            ", inRefluxNum=" + inRefluxNum +
            ", outRefluxFlow=" + outRefluxFlow +
            ", outRefluxNum=" + outRefluxNum +
            ", aerobioticNitrateConcentration=" + aerobioticNitrateConcentration +
            ", anoxiaNitrateConcentration=" + anoxiaNitrateConcentration +
            ", bodCodRatio=" + bodCodRatio +
            ", codCalibration=" + codCalibration +
            ", bodNRatio=" + bodNRatio +
            ", bodEquivalentWeight=" + bodEquivalentWeight +
            ", dilutionRatio=" + dilutionRatio +
            ", intimacy=" + intimacy +
            ", phosphate=" + phosphate +
            ", FeAlRatio=" + FeAlRatio +
            ", phosphorusDosing=" + phosphorusDosing +
            ", feAlActiveIngredients=" + feAlActiveIngredients +
            ", concentration=" + concentration +
            ", concentration=" + nitrateRefluxRatio +
            ", entId=" + entCode +
            ", entName='" + entName + '\'' +
            '}';
    }
}
