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
    @Column(name = "name")
    private String name;

    /**
     * 工艺编号
     */
    @ApiModelProperty(value = "工艺编号")
    @Column(name = "process_period")
    private String processPeriod;

    /**
     * 工艺段名称
     */
    @ApiModelProperty(value = "工艺段名称")
    @Column(name = "craft_name")
    private String craftName;

    /**
     * 工艺段编号
     */
    @ApiModelProperty(value = "工艺段编号")
    @Column(name = "craft_code")
    private String craftCode;

    /**
     * 工艺段池容
     */
    @ApiModelProperty(value = "工艺段池容")
    @Column(name = "craft_capacity")
    private BigDecimal craftCapacity;

    /**
     * 内回流比
     */
    @ApiModelProperty(value = "内回流比")
    @Column(name = "in_reflux_ratio")
    private BigDecimal inRefluxRatio;

    /**
     * 外回流比
     */
    @ApiModelProperty(value = "外回流比")
    @Column(name = "out_reflux_ratio")
    private BigDecimal outRefluxRatio;

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
     * 传统公式：外加碳源相对亲密度
     */
    @ApiModelProperty(value = "传统公式：外加碳源相对亲密度")
    @Column(name = "intimacy ")
    private BigDecimal intimacy ;

    /**
     * 上属水厂编号
     */
    @ApiModelProperty(value = "上属水厂编号")
    @Column(name = "ent_code")
    private String entCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public Craft name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcessPeriod() {
        return processPeriod;
    }
    public Craft processPeriod(String processPeriod) {
        this.processPeriod = processPeriod;
        return this;
    }
    public void setProcessPeriod(String processPeriod) {
        this.processPeriod = processPeriod;
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

    public BigDecimal getCraftCapacity() {
        return craftCapacity;
    }
    public Craft craftCapacity(BigDecimal craftCapacity) {
        this.craftCapacity = craftCapacity;
        return this;
    }
    public void setCraftCapacity(BigDecimal craftCapacity) {
        this.craftCapacity = craftCapacity;
    }

    public BigDecimal getInRefluxRatio() {
        return inRefluxRatio;
    }
    public Craft inRefluxRatio(BigDecimal inRefluxRatio) {
        this.inRefluxRatio = inRefluxRatio;
        return this;
    }
    public void setInRefluxRatio(BigDecimal inRefluxRatio) {
        this.inRefluxRatio = inRefluxRatio;
    }

    public BigDecimal getOutRefluxRatio() {
        return outRefluxRatio;
    }
    public Craft outRefluxRatio(BigDecimal outRefluxRatio) {
        this.outRefluxRatio = outRefluxRatio;
        return this;
    }
    public void setOutRefluxRatio(BigDecimal outRefluxRatio) {
        this.outRefluxRatio = outRefluxRatio;
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
            ", name='" + name + '\'' +
            ", processPeriod='" + processPeriod + '\'' +
            ", craftName='" + craftName + '\'' +
            ", craftCode='" + craftCode + '\'' +
            ", craftCapacity=" + craftCapacity +
            ", inRefluxRatio=" + inRefluxRatio +
            ", outRefluxRatio=" + outRefluxRatio +
            ", aerobioticNitrateConcentration=" + aerobioticNitrateConcentration +
            ", anoxiaNitrateConcentration=" + anoxiaNitrateConcentration +
            ", bodCodRatio=" + bodCodRatio +
            ", codCalibration=" + codCalibration +
            ", bodNRatio=" + bodNRatio +
            ", bodEquivalentWeight=" + bodEquivalentWeight +
            ", intimacy=" + intimacy +
            ", entCode='" + entCode + '\'' +
            '}';
    }
}
