package com.ruowei.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 污水处理碳排放数据
 */
@ApiModel(description = "污水处理碳排放数据")
@Entity
@Table(name = "sew_emi")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SewEmi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 单据号
     */
    @NotNull
    @ApiModelProperty(value = "单据号", required = true)
    @Column(name = "document_code", nullable = false)
    private String documentCode;

    /**
     * 企业ID
     */
    @NotNull
    @ApiModelProperty(value = "企业ID", required = true)
    @Column(name = "enterprise_id", nullable = false)
    private Long enterpriseId;

    /**
     * 企业名称
     */
    @NotNull
    @ApiModelProperty(value = "企业名称", required = true)
    @Column(name = "enterprise_name", nullable = false)
    private String enterpriseName;

    /**
     * 录入员ID
     */
    @NotNull
    @ApiModelProperty(value = "录入员ID", required = true)
    @Column(name = "reporter_id", nullable = false)
    private Long reporterId;

    /**
     * 录入员姓名
     */
    @NotNull
    @ApiModelProperty(value = "录入员姓名", required = true)
    @Column(name = "reporter_name", nullable = false)
    private String reporterName;

    /**
     * 填报时间
     */
    @NotNull
    @ApiModelProperty(value = "填报时间", required = true)
    @Column(name = "report_time", nullable = false)
    private Instant reportTime;

    /**
     * 省份编码
     */
    @NotNull
    @ApiModelProperty(value = "省份编码", required = true)
    @Column(name = "province_code", nullable = false)
    private String provinceCode;

    /**
     * 省份名称
     */
    @NotNull
    @ApiModelProperty(value = "省份名称", required = true)
    @Column(name = "province_name", nullable = false)
    private String provinceName;

    /**
     * 核算年份
     */
    @NotNull
    @ApiModelProperty(value = "核算年份", required = true)
    @Column(name = "acc_year", nullable = false)
    private String accYear;

    /**
     * 核算月份
     */
    @NotNull
    @ApiModelProperty(value = "核算月份", required = true)
    @Column(name = "acc_month", nullable = false)
    private String accMonth;

    /**
     * 核算时间
     */
    @NotNull
    @ApiModelProperty(value = "核算时间", required = true)
    @Column(name = "acc_time", nullable = false)
    private String accTime;

    /**
     * 月度总电量消耗（kWh/m）
     */
    @NotNull
    @ApiModelProperty(value = "月度总电量消耗（kWh/m）", required = true)
    @Column(name = "total_pow", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalPow;

    /**
     * 进水总泵站耗电（kWh/m）
     */
    @ApiModelProperty(value = "进水总泵站耗电（kWh/m）")
    @Column(name = "in_pump_pow", precision = 21, scale = 2)
    private BigDecimal inPumpPow;

    /**
     * 鼓风机房耗电（kWh/m）
     */
    @ApiModelProperty(value = "鼓风机房耗电（kWh/m）")
    @Column(name = "blower_pow", precision = 21, scale = 2)
    private BigDecimal blowerPow;

    /**
     * 回流污泥泵房耗电（kWh）
     */
    @ApiModelProperty(value = "回流污泥泵房耗电（kWh）")
    @Column(name = "ret_slu_pump_pow", precision = 21, scale = 2)
    private BigDecimal retSluPumpPow;

    /**
     * 污泥处理耗电（kWh/m）
     */
    @ApiModelProperty(value = "污泥处理耗电（kWh/m）")
    @Column(name = "slu_treat_pow", precision = 21, scale = 2)
    private BigDecimal sluTreatPow;

    /**
     * 紫外+氯消毒耗电（kWh/m）
     */
    @ApiModelProperty(value = "紫外+氯消毒耗电（kWh/m）")
    @Column(name = "disinfect_pow", precision = 21, scale = 2)
    private BigDecimal disinfectPow;

    /**
     * 附属设施耗电（kWh/m）
     */
    @ApiModelProperty(value = "附属设施耗电（kWh/m）")
    @Column(name = "facility_pow", precision = 21, scale = 2)
    private BigDecimal facilityPow;

    /**
     * 其他耗电（kWh/m）
     */
    @ApiModelProperty(value = "其他耗电（kWh/m）")
    @Column(name = "other_pow", precision = 21, scale = 2)
    private BigDecimal otherPow;

    /**
     * 污泥处置用电（kWh/m）
     */
    @ApiModelProperty(value = "污泥处置用电（kWh/m）")
    @Column(name = "slu_handle_pow", precision = 21, scale = 2)
    private BigDecimal sluHandlePow;

    /**
     * 太阳能月发电量（kWh/m）
     */
    @ApiModelProperty(value = "太阳能月发电量（kWh/m）")
    @Column(name = "solar_pow", precision = 21, scale = 2)
    private BigDecimal solarPow;

    /**
     * 热泵月供热量（GJ/m）
     */
    @ApiModelProperty(value = "热泵月供热量（GJ/m）")
    @Column(name = "heat_pump_heat", precision = 21, scale = 2)
    private BigDecimal heatPumpHeat;

    /**
     * 热泵月制冷量（GJ/m）
     */
    @ApiModelProperty(value = "热泵月制冷量（GJ/m）")
    @Column(name = "heat_pump_refr", precision = 21, scale = 2)
    private BigDecimal heatPumpRefr;

    /**
     * 热泵供热运行时间（h）
     */
    @ApiModelProperty(value = "热泵供热运行时间（h）")
    @Column(name = "heat_pump_hot_hours", precision = 21, scale = 2)
    private BigDecimal heatPumpHotHours;

    /**
     * 热泵制冷运行时间（h）
     */
    @ApiModelProperty(value = "热泵制冷运行时间（h）")
    @Column(name = "heat_pump_cold_hours", precision = 21, scale = 2)
    private BigDecimal heatPumpColdHours;

    /**
     * 热电联产月产电量（kWh/m）
     */
    @ApiModelProperty(value = "热电联产月产电量（kWh/m）")
    @Column(name = "thermo_elec", precision = 21, scale = 2)
    private BigDecimal thermoElec;

    /**
     * 热电联产月产热量（GJ/m）
     */
    @ApiModelProperty(value = "热电联产月产热量（GJ/m）")
    @Column(name = "thermo_ener", precision = 21, scale = 2)
    private BigDecimal thermoEner;

    /**
     * 负碳发电输送到电网的电量
     */
    @ApiModelProperty(value = "负碳发电输送到电网的电量")
    @Column(name = "to_gird_pow", precision = 21, scale = 2)
    private BigDecimal toGirdPow;

    /**
     * 其他负碳项目文字说明
     */
    @ApiModelProperty(value = "其他负碳项目文字说明")
    @Column(name = "other_text")
    private String otherText;

    /**
     * 其他负碳项目减排量
     */
    @ApiModelProperty(value = "其他负碳项目减排量")
    @Column(name = "other_emi_reduction", precision = 21, scale = 2)
    private BigDecimal otherEmiReduction;

    /**
     * 风能月发电量
     */
    @ApiModelProperty(value = "风能月发电量")
    @Column(name = "wind_pow", precision = 21, scale = 2)
    private BigDecimal windPow;

    /**
     * 生态综合体碳减排量
     */
    @ApiModelProperty(value = "生态综合体碳减排量")
    @Column(name = "eco_complex_reduction", precision = 21, scale = 2)
    private BigDecimal ecoComplexReduction;

    /**
     * 污泥处置是否为本厂管理
     */
    @ApiModelProperty(value = "污泥处置是否为本厂管理")
    @Column(name = "managed_by_self")
    private Boolean managedBySelf;

    /**
     * 污泥处理后含水率
     */
    @ApiModelProperty(value = "污泥处理后含水率")
    @Column(name = "slu_moisture_after_treat", precision = 21, scale = 2)
    private BigDecimal sluMoistureAfterTreat;

    /**
     * 碳排放因子版本号
     */
    @NotNull
    @ApiModelProperty(value = "碳排放因子版本号", required = true)
    @Column(name = "factor_version_num", nullable = false)
    private String factorVersionNum;

    /**
     * 一级处理药剂投加产生的间接碳排放（kg/m）
     */
    @ApiModelProperty(value = "一级处理药剂投加产生的间接碳排放（kg/m）")
    @Column(name = "level_1_pot_emi", precision = 21, scale = 2)
    private BigDecimal level1PotEmi;

    /**
     * 二级处理药剂投加产生的间接碳排放（kg/m）
     */
    @ApiModelProperty(value = "二级处理药剂投加产生的间接碳排放（kg/m）")
    @Column(name = "level_2_pot_emi", precision = 21, scale = 2)
    private BigDecimal level2PotEmi;

    /**
     * 三级处理药剂投加产生的间接碳排放（kg/m）
     */
    @ApiModelProperty(value = "三级处理药剂投加产生的间接碳排放（kg/m）")
    @Column(name = "level_3_pot_emi", precision = 21, scale = 2)
    private BigDecimal level3PotEmi;

    /**
     * 污泥处理药剂投加产生的间接碳排放（kg/m）
     */
    @ApiModelProperty(value = "污泥处理药剂投加产生的间接碳排放（kg/m）")
    @Column(name = "slu_treat_pot_emi", precision = 21, scale = 2)
    private BigDecimal sluTreatPotEmi;

    /**
     * 药剂投加产生的间接碳排放（kg/m）
     */
    @NotNull
    @ApiModelProperty(value = "药剂投加产生的间接碳排放（kg/m）", required = true)
    @Column(name = "total_pot_emi", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalPotEmi;

    /**
     * 进水总泵站耗电产生的间接碳排放（kg/m）
     */
    @ApiModelProperty(value = "进水总泵站耗电产生的间接碳排放（kg/m）")
    @Column(name = "inlet_pump_pow_emi", precision = 21, scale = 2)
    private BigDecimal inletPumpPowEmi;

    /**
     * 鼓风机房耗电产生的间接碳排放（kg/m）
     */
    @ApiModelProperty(value = "鼓风机房耗电产生的间接碳排放（kg/m）")
    @Column(name = "blower_pow_emi", precision = 21, scale = 2)
    private BigDecimal blowerPowEmi;

    /**
     * 回流污泥泵房耗电产生的间接碳排放（kg/m）
     */
    @ApiModelProperty(value = "回流污泥泵房耗电产生的间接碳排放（kg/m）")
    @Column(name = "ret_slu_pump_pow_emi", precision = 21, scale = 2)
    private BigDecimal retSluPumpPowEmi;

    /**
     * 污泥处理用电耗电产生的间接碳排放（kg/m）
     */
    @ApiModelProperty(value = "污泥处理用电耗电产生的间接碳排放（kg/m）")
    @Column(name = "slu_treat_pow_emi", precision = 21, scale = 2)
    private BigDecimal sluTreatPowEmi;

    /**
     * 紫外+氯消毒耗电产生的间接碳排放（kg/m）
     */
    @ApiModelProperty(value = "紫外+氯消毒耗电产生的间接碳排放（kg/m）")
    @Column(name = "facility_pow_emi", precision = 21, scale = 2)
    private BigDecimal facilityPowEmi;

    /**
     * 附属设施用电耗电产生的间接碳排放（kg/m）
     */
    @ApiModelProperty(value = "附属设施用电耗电产生的间接碳排放（kg/m）")
    @Column(name = "disinfect_pow_emi", precision = 21, scale = 2)
    private BigDecimal disinfectPowEmi;

    /**
     * 其他耗电产生的间接碳排放（kg/m）
     */
    @ApiModelProperty(value = "其他耗电产生的间接碳排放（kg/m）")
    @Column(name = "other_pow_emi", precision = 21, scale = 2)
    private BigDecimal otherPowEmi;

    /**
     * 耗电产生的间接碳排放（kg/m）
     */
    @NotNull
    @ApiModelProperty(value = "耗电产生的间接碳排放（kg/m）", required = true)
    @Column(name = "total_pow_emi", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalPowEmi;

    /**
     * 污水处理CH4的CO2当量（kg/m）
     */
    @ApiModelProperty(value = "污水处理CH4的CO2当量（kg/m）")
    @Column(name = "sew_treat_ch_4_emi", precision = 21, scale = 2)
    private BigDecimal sewTreatCh4Emi;

    /**
     * 污水处理N2O的CO2当量（kg/m）
     */
    @ApiModelProperty(value = "污水处理N2O的CO2当量（kg/m）")
    @Column(name = "sew_treat_n_2_o_emi", precision = 21, scale = 2)
    private BigDecimal sewTreatN2oEmi;

    /**
     * 污水处理的直接碳排放（kg/m）
     */
    @NotNull
    @ApiModelProperty(value = "污水处理的直接碳排放（kg/m）", required = true)
    @Column(name = "total_sew_treat_emi", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalSewTreatEmi;

    /**
     * 污泥处置CH4的CO2当量（kg/m）
     */
    @ApiModelProperty(value = "污泥处置CH4的CO2当量（kg/m）")
    @Column(name = "slu_handle_ch_4_emi", precision = 21, scale = 2)
    private BigDecimal sluHandleCh4Emi;

    /**
     * 污泥处置N2O的CO2当量（kg/m）
     */
    @ApiModelProperty(value = "污泥处置N2O的CO2当量（kg/m）")
    @Column(name = "slu_handle_n_2_o_emi", precision = 21, scale = 2)
    private BigDecimal sluHandleN2oEmi;

    /**
     * 污泥处置的直接碳排放（kg/m）
     */
    @NotNull
    @ApiModelProperty(value = "污泥处置的直接碳排放（kg/m）", required = true)
    @Column(name = "total_slu_handle_dir_emi", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalSluHandleDirEmi;

    /**
     * 污泥处置药剂投加产生的间接碳排放
     */
    @NotNull
    @ApiModelProperty(value = "污泥处置药剂投加产生的间接碳排放", required = true)
    @Column(name = "slu_handle_pot_emi", precision = 21, scale = 2, nullable = false)
    private BigDecimal sluHandlePotEmi;

    /**
     * 污泥处置用电产生的间接碳排放
     */
    @NotNull
    @ApiModelProperty(value = "污泥处置用电产生的间接碳排放", required = true)
    @Column(name = "slu_handle_pow_emi", precision = 21, scale = 2, nullable = false)
    private BigDecimal sluHandlePowEmi;

    /**
     * 污泥处置间接碳排放
     */
    @NotNull
    @ApiModelProperty(value = "污泥处置间接碳排放", required = true)
    @Column(name = "total_slu_handle_indir_emi", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalSluHandleIndirEmi;

    /**
     * 太阳能间接减少的碳排放（kg/m）
     */
    @ApiModelProperty(value = "太阳能间接减少的碳排放（kg/m）")
    @Column(name = "solar_pow_red", precision = 21, scale = 2)
    private BigDecimal solarPowRed;

    /**
     * 热泵间接减少的碳排放（kg/m）
     */
    @ApiModelProperty(value = "热泵间接减少的碳排放（kg/m）")
    @Column(name = "heat_pump_red", precision = 21, scale = 2)
    private BigDecimal heatPumpRed;

    /**
     * 热电联产发电间接减少的碳排放（kg/m）
     */
    @ApiModelProperty(value = "热电联产发电间接减少的碳排放（kg/m）")
    @Column(name = "thermo_elec_red", precision = 21, scale = 2)
    private BigDecimal thermoElecRed;

    /**
     * 热电联产产热间接减少的碳排放（kg/m）
     */
    @ApiModelProperty(value = "热电联产产热间接减少的碳排放（kg/m）")
    @Column(name = "thermo_ener_red", precision = 21, scale = 2)
    private BigDecimal thermoEnerRed;

    /**
     * 其他负碳项目减少的碳排放
     */
    @ApiModelProperty(value = "其他负碳项目减少的碳排放")
    @Column(name = "other_emi_red", precision = 21, scale = 2)
    private BigDecimal otherEmiRed;

    /**
     * 风电间接减少的碳排放
     */
    @ApiModelProperty(value = "风电间接减少的碳排放")
    @Column(name = "wind_pow_red", precision = 21, scale = 2)
    private BigDecimal windPowRed;

    /**
     * 生态综合体减少的碳排放
     */
    @ApiModelProperty(value = "生态综合体减少的碳排放")
    @Column(name = "eco_complex_red", precision = 21, scale = 2)
    private BigDecimal ecoComplexRed;

    /**
     * 污水厂本月负碳排放（kg/m）
     */
    @NotNull
    @ApiModelProperty(value = "污水厂本月负碳排放（kg/m）", required = true)
    @Column(name = "carbon_red", precision = 21, scale = 2, nullable = false)
    private BigDecimal carbonRed;

    /**
     * 污水厂本月直接碳排放（kg/m）
     */
    @NotNull
    @ApiModelProperty(value = "污水厂本月直接碳排放（kg/m）", required = true)
    @Column(name = "carbon_dir_emi", precision = 21, scale = 2, nullable = false)
    private BigDecimal carbonDirEmi;

    /**
     * 污水厂本月间接碳排放（kg/m）
     */
    @NotNull
    @ApiModelProperty(value = "污水厂本月间接碳排放（kg/m）", required = true)
    @Column(name = "carbon_indir_emi", precision = 21, scale = 2, nullable = false)
    private BigDecimal carbonIndirEmi;

    /**
     * 污水厂本月碳排放（kg/m）
     */
    @NotNull
    @ApiModelProperty(value = "污水厂本月碳排放（kg/m）", required = true)
    @Column(name = "carbon_emi", precision = 21, scale = 2, nullable = false)
    private BigDecimal carbonEmi;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SewEmi id(Long id) {
        this.id = id;
        return this;
    }

    public String getDocumentCode() {
        return this.documentCode;
    }

    public SewEmi documentCode(String documentCode) {
        this.documentCode = documentCode;
        return this;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public Long getEnterpriseId() {
        return this.enterpriseId;
    }

    public SewEmi enterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
        return this;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return this.enterpriseName;
    }

    public SewEmi enterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
        return this;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Long getReporterId() {
        return this.reporterId;
    }

    public SewEmi reporterId(Long reporterId) {
        this.reporterId = reporterId;
        return this;
    }

    public void setReporterId(Long reporterId) {
        this.reporterId = reporterId;
    }

    public String getReporterName() {
        return this.reporterName;
    }

    public SewEmi reporterName(String reporterName) {
        this.reporterName = reporterName;
        return this;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public Instant getReportTime() {
        return this.reportTime;
    }

    public SewEmi reportTime(Instant reportTime) {
        this.reportTime = reportTime;
        return this;
    }

    public void setReportTime(Instant reportTime) {
        this.reportTime = reportTime;
    }

    public String getProvinceCode() {
        return this.provinceCode;
    }

    public SewEmi provinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
        return this;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return this.provinceName;
    }

    public SewEmi provinceName(String provinceName) {
        this.provinceName = provinceName;
        return this;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getAccYear() {
        return this.accYear;
    }

    public SewEmi accYear(String accYear) {
        this.accYear = accYear;
        return this;
    }

    public void setAccYear(String accYear) {
        this.accYear = accYear;
    }

    public String getAccMonth() {
        return this.accMonth;
    }

    public SewEmi accMonth(String accMonth) {
        this.accMonth = accMonth;
        return this;
    }

    public void setAccMonth(String accMonth) {
        this.accMonth = accMonth;
    }

    public String getAccTime() {
        return accTime;
    }

    public SewEmi accTime(String accTime) {
        this.accTime = accTime;
        return this;
    }

    public void setAccTime(String accTime) {
        this.accTime = accTime;
    }

    public BigDecimal getTotalPow() {
        return this.totalPow;
    }

    public SewEmi totalPow(BigDecimal totalPow) {
        this.totalPow = totalPow;
        return this;
    }

    public void setTotalPow(BigDecimal totalPow) {
        this.totalPow = totalPow;
    }

    public BigDecimal getInPumpPow() {
        return this.inPumpPow;
    }

    public SewEmi inPumpPow(BigDecimal inPumpPow) {
        this.inPumpPow = inPumpPow;
        return this;
    }

    public void setInPumpPow(BigDecimal inPumpPow) {
        this.inPumpPow = inPumpPow;
    }

    public BigDecimal getBlowerPow() {
        return this.blowerPow;
    }

    public SewEmi blowerPow(BigDecimal blowerPow) {
        this.blowerPow = blowerPow;
        return this;
    }

    public void setBlowerPow(BigDecimal blowerPow) {
        this.blowerPow = blowerPow;
    }

    public BigDecimal getRetSluPumpPow() {
        return this.retSluPumpPow;
    }

    public SewEmi retSluPumpPow(BigDecimal retSluPumpPow) {
        this.retSluPumpPow = retSluPumpPow;
        return this;
    }

    public void setRetSluPumpPow(BigDecimal retSluPumpPow) {
        this.retSluPumpPow = retSluPumpPow;
    }

    public BigDecimal getSluTreatPow() {
        return this.sluTreatPow;
    }

    public SewEmi sluTreatPow(BigDecimal sluTreatPow) {
        this.sluTreatPow = sluTreatPow;
        return this;
    }

    public void setSluTreatPow(BigDecimal sluTreatPow) {
        this.sluTreatPow = sluTreatPow;
    }

    public BigDecimal getDisinfectPow() {
        return this.disinfectPow;
    }

    public SewEmi disinfectPow(BigDecimal disinfectPow) {
        this.disinfectPow = disinfectPow;
        return this;
    }

    public void setDisinfectPow(BigDecimal disinfectPow) {
        this.disinfectPow = disinfectPow;
    }

    public BigDecimal getFacilityPow() {
        return this.facilityPow;
    }

    public SewEmi facilityPow(BigDecimal facilityPow) {
        this.facilityPow = facilityPow;
        return this;
    }

    public void setFacilityPow(BigDecimal facilityPow) {
        this.facilityPow = facilityPow;
    }

    public BigDecimal getOtherPow() {
        return this.otherPow;
    }

    public SewEmi otherPow(BigDecimal otherPow) {
        this.otherPow = otherPow;
        return this;
    }

    public void setOtherPow(BigDecimal otherPow) {
        this.otherPow = otherPow;
    }

    public BigDecimal getSluHandlePow() {
        return this.sluHandlePow;
    }

    public SewEmi sluHandlePow(BigDecimal sluHandlePow) {
        this.sluHandlePow = sluHandlePow;
        return this;
    }

    public void setSluHandlePow(BigDecimal sluHandlePow) {
        this.sluHandlePow = sluHandlePow;
    }

    public BigDecimal getSolarPow() {
        return this.solarPow;
    }

    public SewEmi solarPow(BigDecimal solarPow) {
        this.solarPow = solarPow;
        return this;
    }

    public void setSolarPow(BigDecimal solarPow) {
        this.solarPow = solarPow;
    }

    public BigDecimal getHeatPumpHeat() {
        return this.heatPumpHeat;
    }

    public SewEmi heatPumpHeat(BigDecimal heatPumpHeat) {
        this.heatPumpHeat = heatPumpHeat;
        return this;
    }

    public void setHeatPumpHeat(BigDecimal heatPumpHeat) {
        this.heatPumpHeat = heatPumpHeat;
    }

    public BigDecimal getHeatPumpRefr() {
        return this.heatPumpRefr;
    }

    public SewEmi heatPumpRefr(BigDecimal heatPumpRefr) {
        this.heatPumpRefr = heatPumpRefr;
        return this;
    }

    public void setHeatPumpRefr(BigDecimal heatPumpRefr) {
        this.heatPumpRefr = heatPumpRefr;
    }

    public BigDecimal getHeatPumpHotHours() {
        return this.heatPumpHotHours;
    }

    public SewEmi heatPumpHotHours(BigDecimal heatPumpHotHours) {
        this.heatPumpHotHours = heatPumpHotHours;
        return this;
    }

    public void setHeatPumpHotHours(BigDecimal heatPumpHotHours) {
        this.heatPumpHotHours = heatPumpHotHours;
    }

    public BigDecimal getHeatPumpColdHours() {
        return this.heatPumpColdHours;
    }

    public SewEmi heatPumpColdHours(BigDecimal heatPumpColdHours) {
        this.heatPumpColdHours = heatPumpColdHours;
        return this;
    }

    public void setHeatPumpColdHours(BigDecimal heatPumpColdHours) {
        this.heatPumpColdHours = heatPumpColdHours;
    }

    public BigDecimal getThermoElec() {
        return this.thermoElec;
    }

    public SewEmi thermoElec(BigDecimal thermoElec) {
        this.thermoElec = thermoElec;
        return this;
    }

    public void setThermoElec(BigDecimal thermoElec) {
        this.thermoElec = thermoElec;
    }

    public BigDecimal getThermoEner() {
        return this.thermoEner;
    }

    public SewEmi thermoEner(BigDecimal thermoEner) {
        this.thermoEner = thermoEner;
        return this;
    }

    public void setThermoEner(BigDecimal thermoEner) {
        this.thermoEner = thermoEner;
    }

    public BigDecimal getToGirdPow() {
        return this.toGirdPow;
    }

    public SewEmi toGirdPow(BigDecimal toGirdPow) {
        this.toGirdPow = toGirdPow;
        return this;
    }

    public void setToGirdPow(BigDecimal toGirdPow) {
        this.toGirdPow = toGirdPow;
    }

    public String getOtherText() {
        return this.otherText;
    }

    public SewEmi otherText(String otherText) {
        this.otherText = otherText;
        return this;
    }

    public void setOtherText(String otherText) {
        this.otherText = otherText;
    }

    public BigDecimal getOtherEmiReduction() {
        return this.otherEmiReduction;
    }

    public SewEmi otherEmiReduction(BigDecimal otherEmiReduction) {
        this.otherEmiReduction = otherEmiReduction;
        return this;
    }

    public void setOtherEmiReduction(BigDecimal otherEmiReduction) {
        this.otherEmiReduction = otherEmiReduction;
    }

    public BigDecimal getWindPow() {
        return this.windPow;
    }

    public SewEmi windPow(BigDecimal windPow) {
        this.windPow = windPow;
        return this;
    }

    public void setWindPow(BigDecimal windPow) {
        this.windPow = windPow;
    }

    public BigDecimal getEcoComplexReduction() {
        return this.ecoComplexReduction;
    }

    public SewEmi ecoComplexReduction(BigDecimal ecoComplexReduction) {
        this.ecoComplexReduction = ecoComplexReduction;
        return this;
    }

    public void setEcoComplexReduction(BigDecimal ecoComplexReduction) {
        this.ecoComplexReduction = ecoComplexReduction;
    }

    public Boolean getManagedBySelf() {
        return this.managedBySelf;
    }

    public SewEmi managedBySelf(Boolean managedBySelf) {
        this.managedBySelf = managedBySelf;
        return this;
    }

    public void setManagedBySelf(Boolean managedBySelf) {
        this.managedBySelf = managedBySelf;
    }

    public BigDecimal getSluMoistureAfterTreat() {
        return this.sluMoistureAfterTreat;
    }

    public SewEmi sluMoistureAfterTreat(BigDecimal sluMoistureAfterTreat) {
        this.sluMoistureAfterTreat = sluMoistureAfterTreat;
        return this;
    }

    public void setSluMoistureAfterTreat(BigDecimal sluMoistureAfterTreat) {
        this.sluMoistureAfterTreat = sluMoistureAfterTreat;
    }

    public String getFactorVersionNum() {
        return this.factorVersionNum;
    }

    public SewEmi factorVersionNum(String factorVersionNum) {
        this.factorVersionNum = factorVersionNum;
        return this;
    }

    public void setFactorVersionNum(String factorVersionNum) {
        this.factorVersionNum = factorVersionNum;
    }

    public BigDecimal getLevel1PotEmi() {
        return this.level1PotEmi;
    }

    public SewEmi level1PotEmi(BigDecimal level1PotEmi) {
        this.level1PotEmi = level1PotEmi;
        return this;
    }

    public void setLevel1PotEmi(BigDecimal level1PotEmi) {
        this.level1PotEmi = level1PotEmi;
    }

    public BigDecimal getLevel2PotEmi() {
        return this.level2PotEmi;
    }

    public SewEmi level2PotEmi(BigDecimal level2PotEmi) {
        this.level2PotEmi = level2PotEmi;
        return this;
    }

    public void setLevel2PotEmi(BigDecimal level2PotEmi) {
        this.level2PotEmi = level2PotEmi;
    }

    public BigDecimal getLevel3PotEmi() {
        return this.level3PotEmi;
    }

    public SewEmi level3PotEmi(BigDecimal level3PotEmi) {
        this.level3PotEmi = level3PotEmi;
        return this;
    }

    public void setLevel3PotEmi(BigDecimal level3PotEmi) {
        this.level3PotEmi = level3PotEmi;
    }

    public BigDecimal getSluTreatPotEmi() {
        return this.sluTreatPotEmi;
    }

    public SewEmi sluTreatPotEmi(BigDecimal sluTreatPotEmi) {
        this.sluTreatPotEmi = sluTreatPotEmi;
        return this;
    }

    public void setSluTreatPotEmi(BigDecimal sluTreatPotEmi) {
        this.sluTreatPotEmi = sluTreatPotEmi;
    }

    public BigDecimal getTotalPotEmi() {
        return this.totalPotEmi;
    }

    public SewEmi totalPotEmi(BigDecimal totalPotEmi) {
        this.totalPotEmi = totalPotEmi;
        return this;
    }

    public void setTotalPotEmi(BigDecimal totalPotEmi) {
        this.totalPotEmi = totalPotEmi;
    }

    public BigDecimal getInletPumpPowEmi() {
        return this.inletPumpPowEmi;
    }

    public SewEmi inletPumpPowEmi(BigDecimal inletPumpPowEmi) {
        this.inletPumpPowEmi = inletPumpPowEmi;
        return this;
    }

    public void setInletPumpPowEmi(BigDecimal inletPumpPowEmi) {
        this.inletPumpPowEmi = inletPumpPowEmi;
    }

    public BigDecimal getBlowerPowEmi() {
        return this.blowerPowEmi;
    }

    public SewEmi blowerPowEmi(BigDecimal blowerPowEmi) {
        this.blowerPowEmi = blowerPowEmi;
        return this;
    }

    public void setBlowerPowEmi(BigDecimal blowerPowEmi) {
        this.blowerPowEmi = blowerPowEmi;
    }

    public BigDecimal getRetSluPumpPowEmi() {
        return this.retSluPumpPowEmi;
    }

    public SewEmi retSluPumpPowEmi(BigDecimal retSluPumpPowEmi) {
        this.retSluPumpPowEmi = retSluPumpPowEmi;
        return this;
    }

    public void setRetSluPumpPowEmi(BigDecimal retSluPumpPowEmi) {
        this.retSluPumpPowEmi = retSluPumpPowEmi;
    }

    public BigDecimal getSluTreatPowEmi() {
        return this.sluTreatPowEmi;
    }

    public SewEmi sluTreatPowEmi(BigDecimal sluTreatPowEmi) {
        this.sluTreatPowEmi = sluTreatPowEmi;
        return this;
    }

    public void setSluTreatPowEmi(BigDecimal sluTreatPowEmi) {
        this.sluTreatPowEmi = sluTreatPowEmi;
    }

    public BigDecimal getFacilityPowEmi() {
        return this.facilityPowEmi;
    }

    public SewEmi facilityPowEmi(BigDecimal facilityPowEmi) {
        this.facilityPowEmi = facilityPowEmi;
        return this;
    }

    public void setFacilityPowEmi(BigDecimal facilityPowEmi) {
        this.facilityPowEmi = facilityPowEmi;
    }

    public BigDecimal getDisinfectPowEmi() {
        return this.disinfectPowEmi;
    }

    public SewEmi disinfectPowEmi(BigDecimal disinfectPowEmi) {
        this.disinfectPowEmi = disinfectPowEmi;
        return this;
    }

    public void setDisinfectPowEmi(BigDecimal disinfectPowEmi) {
        this.disinfectPowEmi = disinfectPowEmi;
    }

    public BigDecimal getOtherPowEmi() {
        return this.otherPowEmi;
    }

    public SewEmi otherPowEmi(BigDecimal otherPowEmi) {
        this.otherPowEmi = otherPowEmi;
        return this;
    }

    public void setOtherPowEmi(BigDecimal otherPowEmi) {
        this.otherPowEmi = otherPowEmi;
    }

    public BigDecimal getTotalPowEmi() {
        return this.totalPowEmi;
    }

    public SewEmi totalPowEmi(BigDecimal totalPowEmi) {
        this.totalPowEmi = totalPowEmi;
        return this;
    }

    public void setTotalPowEmi(BigDecimal totalPowEmi) {
        this.totalPowEmi = totalPowEmi;
    }

    public BigDecimal getSewTreatCh4Emi() {
        return this.sewTreatCh4Emi;
    }

    public SewEmi sewTreatCh4Emi(BigDecimal sewTreatCh4Emi) {
        this.sewTreatCh4Emi = sewTreatCh4Emi;
        return this;
    }

    public void setSewTreatCh4Emi(BigDecimal sewTreatCh4Emi) {
        this.sewTreatCh4Emi = sewTreatCh4Emi;
    }

    public BigDecimal getSewTreatN2oEmi() {
        return this.sewTreatN2oEmi;
    }

    public SewEmi sewTreatN2oEmi(BigDecimal sewTreatN2oEmi) {
        this.sewTreatN2oEmi = sewTreatN2oEmi;
        return this;
    }

    public void setSewTreatN2oEmi(BigDecimal sewTreatN2oEmi) {
        this.sewTreatN2oEmi = sewTreatN2oEmi;
    }

    public BigDecimal getTotalSewTreatEmi() {
        return this.totalSewTreatEmi;
    }

    public SewEmi totalSewTreatEmi(BigDecimal totalSewTreatEmi) {
        this.totalSewTreatEmi = totalSewTreatEmi;
        return this;
    }

    public void setTotalSewTreatEmi(BigDecimal totalSewTreatEmi) {
        this.totalSewTreatEmi = totalSewTreatEmi;
    }

    public BigDecimal getSluHandleCh4Emi() {
        return this.sluHandleCh4Emi;
    }

    public SewEmi sluHandleCh4Emi(BigDecimal sluHandleCh4Emi) {
        this.sluHandleCh4Emi = sluHandleCh4Emi;
        return this;
    }

    public void setSluHandleCh4Emi(BigDecimal sluHandleCh4Emi) {
        this.sluHandleCh4Emi = sluHandleCh4Emi;
    }

    public BigDecimal getSluHandleN2oEmi() {
        return this.sluHandleN2oEmi;
    }

    public SewEmi sluHandleN2oEmi(BigDecimal sluHandleN2oEmi) {
        this.sluHandleN2oEmi = sluHandleN2oEmi;
        return this;
    }

    public void setSluHandleN2oEmi(BigDecimal sluHandleN2oEmi) {
        this.sluHandleN2oEmi = sluHandleN2oEmi;
    }

    public BigDecimal getTotalSluHandleDirEmi() {
        return this.totalSluHandleDirEmi;
    }

    public SewEmi totalSluHandleDirEmi(BigDecimal totalSluHandleDirEmi) {
        this.totalSluHandleDirEmi = totalSluHandleDirEmi;
        return this;
    }

    public void setTotalSluHandleDirEmi(BigDecimal totalSluHandleDirEmi) {
        this.totalSluHandleDirEmi = totalSluHandleDirEmi;
    }

    public BigDecimal getSluHandlePotEmi() {
        return this.sluHandlePotEmi;
    }

    public SewEmi sluHandlePotEmi(BigDecimal sluHandlePotEmi) {
        this.sluHandlePotEmi = sluHandlePotEmi;
        return this;
    }

    public void setSluHandlePotEmi(BigDecimal sluHandlePotEmi) {
        this.sluHandlePotEmi = sluHandlePotEmi;
    }

    public BigDecimal getSluHandlePowEmi() {
        return this.sluHandlePowEmi;
    }

    public SewEmi sluHandlePowEmi(BigDecimal sluHandlePowEmi) {
        this.sluHandlePowEmi = sluHandlePowEmi;
        return this;
    }

    public void setSluHandlePowEmi(BigDecimal sluHandlePowEmi) {
        this.sluHandlePowEmi = sluHandlePowEmi;
    }

    public BigDecimal getTotalSluHandleIndirEmi() {
        return this.totalSluHandleIndirEmi;
    }

    public SewEmi totalSluHandleIndirEmi(BigDecimal totalSluHandleIndirEmi) {
        this.totalSluHandleIndirEmi = totalSluHandleIndirEmi;
        return this;
    }

    public void setTotalSluHandleIndirEmi(BigDecimal totalSluHandleIndirEmi) {
        this.totalSluHandleIndirEmi = totalSluHandleIndirEmi;
    }

    public BigDecimal getSolarPowRed() {
        return this.solarPowRed;
    }

    public SewEmi solarPowRed(BigDecimal solarPowRed) {
        this.solarPowRed = solarPowRed;
        return this;
    }

    public void setSolarPowRed(BigDecimal solarPowRed) {
        this.solarPowRed = solarPowRed;
    }

    public BigDecimal getHeatPumpRed() {
        return this.heatPumpRed;
    }

    public SewEmi heatPumpRed(BigDecimal heatPumpRed) {
        this.heatPumpRed = heatPumpRed;
        return this;
    }

    public void setHeatPumpRed(BigDecimal heatPumpRed) {
        this.heatPumpRed = heatPumpRed;
    }

    public BigDecimal getThermoElecRed() {
        return this.thermoElecRed;
    }

    public SewEmi thermoElecRed(BigDecimal thermoElecRed) {
        this.thermoElecRed = thermoElecRed;
        return this;
    }

    public void setThermoElecRed(BigDecimal thermoElecRed) {
        this.thermoElecRed = thermoElecRed;
    }

    public BigDecimal getThermoEnerRed() {
        return this.thermoEnerRed;
    }

    public SewEmi thermoEnerRed(BigDecimal thermoEnerRed) {
        this.thermoEnerRed = thermoEnerRed;
        return this;
    }

    public void setThermoEnerRed(BigDecimal thermoEnerRed) {
        this.thermoEnerRed = thermoEnerRed;
    }

    public BigDecimal getOtherEmiRed() {
        return this.otherEmiRed;
    }

    public SewEmi otherEmiRed(BigDecimal otherEmiRed) {
        this.otherEmiRed = otherEmiRed;
        return this;
    }

    public void setOtherEmiRed(BigDecimal otherEmiRed) {
        this.otherEmiRed = otherEmiRed;
    }

    public BigDecimal getWindPowRed() {
        return this.windPowRed;
    }

    public SewEmi windPowRed(BigDecimal windPowRed) {
        this.windPowRed = windPowRed;
        return this;
    }

    public void setWindPowRed(BigDecimal windPowRed) {
        this.windPowRed = windPowRed;
    }

    public BigDecimal getEcoComplexRed() {
        return this.ecoComplexRed;
    }

    public SewEmi ecoComplexRed(BigDecimal ecoComplexRed) {
        this.ecoComplexRed = ecoComplexRed;
        return this;
    }

    public void setEcoComplexRed(BigDecimal ecoComplexRed) {
        this.ecoComplexRed = ecoComplexRed;
    }

    public BigDecimal getCarbonRed() {
        return this.carbonRed;
    }

    public SewEmi carbonRed(BigDecimal carbonRed) {
        this.carbonRed = carbonRed;
        return this;
    }

    public void setCarbonRed(BigDecimal carbonRed) {
        this.carbonRed = carbonRed;
    }

    public BigDecimal getCarbonDirEmi() {
        return this.carbonDirEmi;
    }

    public SewEmi carbonDirEmi(BigDecimal carbonDirEmi) {
        this.carbonDirEmi = carbonDirEmi;
        return this;
    }

    public void setCarbonDirEmi(BigDecimal carbonDirEmi) {
        this.carbonDirEmi = carbonDirEmi;
    }

    public BigDecimal getCarbonIndirEmi() {
        return this.carbonIndirEmi;
    }

    public SewEmi carbonIndirEmi(BigDecimal carbonIndirEmi) {
        this.carbonIndirEmi = carbonIndirEmi;
        return this;
    }

    public void setCarbonIndirEmi(BigDecimal carbonIndirEmi) {
        this.carbonIndirEmi = carbonIndirEmi;
    }

    public BigDecimal getCarbonEmi() {
        return this.carbonEmi;
    }

    public SewEmi carbonEmi(BigDecimal carbonEmi) {
        this.carbonEmi = carbonEmi;
        return this;
    }

    public void setCarbonEmi(BigDecimal carbonEmi) {
        this.carbonEmi = carbonEmi;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SewEmi)) {
            return false;
        }
        return id != null && id.equals(((SewEmi) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SewEmi{" +
            "id=" + getId() +
            ", documentCode='" + getDocumentCode() + "'" +
            ", enterpriseId=" + getEnterpriseId() +
            ", enterpriseName='" + getEnterpriseName() + "'" +
            ", reporterId=" + getReporterId() +
            ", reporterName='" + getReporterName() + "'" +
            ", reportTime='" + getReportTime() + "'" +
            ", provinceCode='" + getProvinceCode() + "'" +
            ", provinceName='" + getProvinceName() + "'" +
            ", accYear='" + getAccYear() + "'" +
            ", accMonth='" + getAccMonth() + "'" +
            ", accTime='" + getAccTime() + "'" +
            ", totalPow=" + getTotalPow() +
            ", inPumpPow=" + getInPumpPow() +
            ", blowerPow=" + getBlowerPow() +
            ", retSluPumpPow=" + getRetSluPumpPow() +
            ", sluTreatPow=" + getSluTreatPow() +
            ", disinfectPow=" + getDisinfectPow() +
            ", facilityPow=" + getFacilityPow() +
            ", otherPow=" + getOtherPow() +
            ", sluHandlePow=" + getSluHandlePow() +
            ", solarPow=" + getSolarPow() +
            ", heatPumpHeat=" + getHeatPumpHeat() +
            ", heatPumpRefr=" + getHeatPumpRefr() +
            ", heatPumpHotHours=" + getHeatPumpHotHours() +
            ", heatPumpColdHours=" + getHeatPumpColdHours() +
            ", thermoElec=" + getThermoElec() +
            ", thermoEner=" + getThermoEner() +
            ", toGirdPow=" + getToGirdPow() +
            ", otherText='" + getOtherText() + "'" +
            ", otherEmiReduction=" + getOtherEmiReduction() +
            ", windPow=" + getWindPow() +
            ", ecoComplexReduction=" + getEcoComplexReduction() +
            ", managedBySelf='" + getManagedBySelf() + "'" +
            ", sluMoistureAfterTreat=" + getSluMoistureAfterTreat() +
            ", factorVersionNum='" + getFactorVersionNum() + "'" +
            ", level1PotEmi=" + getLevel1PotEmi() +
            ", level2PotEmi=" + getLevel2PotEmi() +
            ", level3PotEmi=" + getLevel3PotEmi() +
            ", sluTreatPotEmi=" + getSluTreatPotEmi() +
            ", totalPotEmi=" + getTotalPotEmi() +
            ", inletPumpPowEmi=" + getInletPumpPowEmi() +
            ", blowerPowEmi=" + getBlowerPowEmi() +
            ", retSluPumpPowEmi=" + getRetSluPumpPowEmi() +
            ", sluTreatPowEmi=" + getSluTreatPowEmi() +
            ", facilityPowEmi=" + getFacilityPowEmi() +
            ", disinfectPowEmi=" + getDisinfectPowEmi() +
            ", otherPowEmi=" + getOtherPowEmi() +
            ", totalPowEmi=" + getTotalPowEmi() +
            ", sewTreatCh4Emi=" + getSewTreatCh4Emi() +
            ", sewTreatN2oEmi=" + getSewTreatN2oEmi() +
            ", totalSewTreatEmi=" + getTotalSewTreatEmi() +
            ", sluHandleCh4Emi=" + getSluHandleCh4Emi() +
            ", sluHandleN2oEmi=" + getSluHandleN2oEmi() +
            ", totalSluHandleDirEmi=" + getTotalSluHandleDirEmi() +
            ", sluHandlePotEmi=" + getSluHandlePotEmi() +
            ", sluHandlePowEmi=" + getSluHandlePowEmi() +
            ", totalSluHandleIndirEmi=" + getTotalSluHandleIndirEmi() +
            ", solarPowRed=" + getSolarPowRed() +
            ", heatPumpRed=" + getHeatPumpRed() +
            ", thermoElecRed=" + getThermoElecRed() +
            ", thermoEnerRed=" + getThermoEnerRed() +
            ", otherEmiRed=" + getOtherEmiRed() +
            ", windPowRed=" + getWindPowRed() +
            ", ecoComplexRed=" + getEcoComplexRed() +
            ", carbonRed=" + getCarbonRed() +
            ", carbonDirEmi=" + getCarbonDirEmi() +
            ", carbonIndirEmi=" + getCarbonIndirEmi() +
            ", carbonEmi=" + getCarbonEmi() +
            "}";
    }
}
