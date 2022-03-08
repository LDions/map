package com.ruowei.service.dto;

import com.ruowei.web.rest.vm.SewEmiAccountVM;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class SewEmiDTO {

    private String key;

    private String docType;

    /**
     * 单据号
     */
    private String documentCode;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 录入员ID
     */
    private Long reporterId;

    /**
     * 录入员姓名
     */
    private String reporterName;

    /**
     * 填报时间
     */
    private Instant reportTime;

    /**
     * 省份编码
     */
    private String provinceCode;

    /**
     * 省份名称
     */
    private String provinceName;

    /**
     * 核算年份
     */
    private String accYear;

    /**
     * 核算月份
     */
    private String accMonth;

    /**
     * 核算时间
     */
    private String accTime;

    /**
     * 工艺水质信息
     */
    private List<SewEmiAccountVM.SewProcessVM> sewProcesss;

    /**
     * 月度总电量消耗（kWh/m）
     */
    private BigDecimal totalPow;

    /**
     * 进水总泵站耗电（kWh/m）
     */
    private BigDecimal inPumpPow;

    /**
     * 鼓风机房耗电（kWh/m）
     */
    private BigDecimal blowerPow;

    /**
     * 回流污泥泵房耗电（kWh）
     */
    private BigDecimal retSluPumpPow;

    /**
     * 污泥处理耗电（kWh/m）
     */
    private BigDecimal sluTreatPow;

    /**
     * 紫外+氯消毒耗电（kWh/m）
     */
    private BigDecimal disinfectPow;

    /**
     * 附属设施耗电（kWh/m）
     */
    private BigDecimal facilityPow;

    /**
     * 其他耗电（kWh/m）
     */
    private BigDecimal otherPow;

    /**
     * 污泥处置用电（kWh/m）
     */
    private BigDecimal sluHandlePow;

    /**
     * 污水处理药剂月投加情况
     */
    private List<SewEmiAccountVM.SewPotVM> sewPots;

    /**
     * 太阳能月发电量（kWh/m）
     */
    private BigDecimal solarPow;

    /**
     * 热泵月供热量（GJ/m）
     */
    private BigDecimal heatPumpHeat;

    /**
     * 热泵月制冷量（GJ/m）
     */
    private BigDecimal heatPumpRefr;

    /**
     * 热泵供热运行时间（h）
     */
    private BigDecimal heatPumpHotHours;

    /**
     * 热泵制冷运行时间（h）
     */
    private BigDecimal heatPumpColdHours;

    /**
     * 热电联产月产电量（kWh/m）
     */
    private BigDecimal thermoElec;

    /**
     * 热电联产月产热量（GJ/m）
     */
    private BigDecimal thermoEner;

    /**
     * 碳减排发电输送到电网的电量
     */
    private BigDecimal toGirdPow;

    /**
     * 其他碳减排项目文字说明
     */
    private String otherText;

    /**
     * 其他碳减排项目减排量
     */
    private BigDecimal otherEmiReduction;

    /**
     * 风能月发电量
     */
    private BigDecimal windPow;

    /**
     * 生态综合体碳减排量
     */
    private BigDecimal ecoComplexReduction;

    /**
     * 污泥处置是否为本厂管理
     */
    private Boolean managedBySelf;

    /**
     * 污泥处理后含水率
     */
    private BigDecimal sluMoistureAfterTreat;

    /**
     * 污泥处置情况
     */
    private List<SewEmiAccountVM.SewSluVM> sewSlus;

    /**
     * 其他指标
     */
    private List<SewEmiAccountVM.OtherIndexVM> otherIndexs;

    /**
     * 碳排放因子版本号
     */
    private String factorVersionNum;

    /**
     * 一级处理药剂投加产生的间接碳排放（kg/m）
     */
    private BigDecimal level1PotEmi;

    /**
     * 二级处理药剂投加产生的间接碳排放（kg/m）
     */
    private BigDecimal level2PotEmi;

    /**
     * 三级处理药剂投加产生的间接碳排放（kg/m）
     */
    private BigDecimal level3PotEmi;

    /**
     * 污泥处理药剂投加产生的间接碳排放（kg/m）
     */
    private BigDecimal sluTreatPotEmi;

    /**
     * 药剂投加产生的间接碳排放（kg/m）
     */
    private BigDecimal totalPotEmi;

    /**
     * 进水总泵站耗电产生的间接碳排放（kg/m）
     */
    private BigDecimal inletPumpPowEmi;

    /**
     * 鼓风机房耗电产生的间接碳排放（kg/m）
     */
    private BigDecimal blowerPowEmi;

    /**
     * 回流污泥泵房耗电产生的间接碳排放（kg/m）
     */
    private BigDecimal retSluPumpPowEmi;

    /**
     * 污泥处理用电耗电产生的间接碳排放（kg/m）
     */
    private BigDecimal sluTreatPowEmi;

    /**
     * 紫外+氯消毒耗电产生的间接碳排放（kg/m）
     */
    private BigDecimal facilityPowEmi;

    /**
     * 附属设施用电耗电产生的间接碳排放（kg/m）
     */
    private BigDecimal disinfectPowEmi;

    /**
     * 其他耗电产生的间接碳排放（kg/m）
     */
    private BigDecimal otherPowEmi;

    /**
     * 耗电产生的间接碳排放（kg/m）
     */
    private BigDecimal totalPowEmi;

    /**
     * 污水处理CH4的CO2当量（kg/m）
     */
    private BigDecimal sewTreatCh4Emi;

    /**
     * 污水处理N2O的CO2当量（kg/m）
     */
    private BigDecimal sewTreatN2oEmi;

    /**
     * 污水处理的直接碳排放（kg/m）
     */
    private BigDecimal totalSewTreatEmi;

    /**
     * 污泥处置CH4的CO2当量（kg/m）
     */
    private BigDecimal sluHandleCh4Emi;

    /**
     * 污泥处置N2O的CO2当量（kg/m）
     */
    private BigDecimal sluHandleN2oEmi;

    /**
     * 污泥处置的直接碳排放（kg/m）
     */
    private BigDecimal totalSluHandleDirEmi;

    /**
     * 污泥处置药剂投加产生的间接碳排放
     */
    private BigDecimal sluHandlePotEmi;

    /**
     * 污泥处置用电产生的间接碳排放
     */
    private BigDecimal sluHandlePowEmi;

    /**
     * 污泥处置间接碳排放
     */
    private BigDecimal totalSluHandleIndirEmi;

    /**
     * 太阳能间接减少的碳排放（kg/m）
     */
    private BigDecimal solarPowRed;

    /**
     * 热泵间接减少的碳排放（kg/m）
     */
    private BigDecimal heatPumpRed;

    /**
     * 热电联产发电间接减少的碳排放（kg/m）
     */
    private BigDecimal thermoElecRed;

    /**
     * 热电联产产热间接减少的碳排放（kg/m）
     */
    private BigDecimal thermoEnerRed;

    /**
     * 其他碳减排项目减少的碳排放
     */
    private BigDecimal otherEmiRed;

    /**
     * 风电间接减少的碳排放
     */
    private BigDecimal windPowRed;

    /**
     * 生态综合体减少的碳排放
     */
    private BigDecimal ecoComplexRed;

    /**
     * 污水厂本月碳排放（kg/m）
     */
    private BigDecimal carbonEmi;

    /**
     * 污水厂本月碳减排排放（kg/m）
     */
    private BigDecimal carbonRed;

    /**
     * 污水厂本月直接碳排放（kg/m）
     */
    private BigDecimal carbonDirEmi;

    /**
     * 污水厂本月间接碳排放（kg/m）
     */
    private BigDecimal carbonIndirEmi;

    public SewEmiDTO() {
    }

    public SewEmiDTO(String documentCode, Long enterpriseId, String enterpriseName, Long reporterId, String reporterName, Instant reportTime, String provinceCode, String provinceName, SewEmiAccountVM vm, String factorVersionNum, WaterCarbonEmissionOutputDTO outputDTO) {
        this.key = vm.getIndustryCode() + "-" + documentCode;
        this.docType = vm.getIndustryCode();
        this.documentCode = documentCode;
        this.enterpriseId = enterpriseId;
        this.enterpriseName = enterpriseName;
        this.reporterId = reporterId;
        this.reporterName = reporterName;
        this.reportTime = reportTime;
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
        this.accYear = vm.getAccYear();
        this.accMonth = vm.getAccMonth();
        this.accTime = vm.getAccYear().concat(vm.getAccMonth());
        this.sewProcesss = vm.getSewProcesss();
        this.totalPow = vm.getTotalPow();
        this.inPumpPow = vm.getInPumpPow();
        this.blowerPow = vm.getBlowerPow();
        this.retSluPumpPow = vm.getRetSluPumpPow();
        this.sluTreatPow = vm.getSluTreatPow();
        this.disinfectPow = vm.getDisinfectPow();
        this.facilityPow = vm.getFacilityPow();
        this.otherPow = vm.getOtherPow();
        this.sluHandlePow = vm.getSluHandlePow();
        this.sewPots = vm.getSewPots();
        this.solarPow = vm.getSolarPow();
        this.heatPumpHeat = vm.getHeatPumpHeat();
        this.heatPumpRefr = vm.getHeatPumpRefr();
        this.heatPumpHotHours = vm.getHeatPumpHotHours();
        this.heatPumpColdHours = vm.getHeatPumpColdHours();
        this.thermoElec = vm.getThermoElec();
        this.thermoEner = vm.getThermoEner();
        this.toGirdPow = vm.getToGirdPow();
        this.otherText = vm.getOtherText();
        this.otherEmiReduction = vm.getOtherEmiReduction();
        this.windPow = vm.getWindPow();
        this.ecoComplexReduction = vm.getEcoComplexReduction();
        this.managedBySelf = vm.getManagedBySelf();
        this.sluMoistureAfterTreat = vm.getSluMoistureAfterTreat();
        this.sewSlus = vm.getSewSlus();
        this.otherIndexs = vm.getOtherIndexs();
        this.factorVersionNum = factorVersionNum;
        this.level1PotEmi = outputDTO.getC11();
        this.level2PotEmi = outputDTO.getC12();
        this.level3PotEmi = outputDTO.getC13();
        this.sluTreatPotEmi = outputDTO.getC14();
        this.totalPotEmi = outputDTO.getTotalC1();
        this.inletPumpPowEmi = outputDTO.getC21();
        this.blowerPowEmi = outputDTO.getC22();
        this.retSluPumpPowEmi = outputDTO.getC23();
        this.sluTreatPowEmi = outputDTO.getC24();
        this.facilityPowEmi = outputDTO.getC25();
        this.disinfectPowEmi = outputDTO.getC26();
        this.otherPowEmi = outputDTO.getC27();
        this.totalPowEmi = outputDTO.getTotalC2();
        this.sewTreatCh4Emi = outputDTO.getC31();
        this.sewTreatN2oEmi = outputDTO.getC32();
        this.totalSewTreatEmi = outputDTO.getTotalC3();
        this.sluHandleCh4Emi = outputDTO.getC41();
        this.sluHandleN2oEmi = outputDTO.getC42();
        this.totalSluHandleDirEmi = outputDTO.getTotalC4();
        this.sluHandlePotEmi = outputDTO.getC91();
        this.sluHandlePowEmi = outputDTO.getC92();
        this.totalSluHandleIndirEmi = outputDTO.getTotalC9();
        this.solarPowRed = outputDTO.getTotalC5();
        this.heatPumpRed = outputDTO.getTotalC6();
        this.thermoElecRed = outputDTO.getTotalC7();
        this.thermoEnerRed = outputDTO.getTotalC8();
        this.otherEmiRed = outputDTO.getTotalC11();
        this.windPowRed = outputDTO.getTotalC12();
        this.ecoComplexRed = outputDTO.getTotalC13();
        this.carbonEmi = outputDTO.getC();
        this.carbonRed = outputDTO.getCReduction();
        this.carbonDirEmi = outputDTO.getCDirect();
        this.carbonIndirEmi = outputDTO.getCIndirect();
    }
}
