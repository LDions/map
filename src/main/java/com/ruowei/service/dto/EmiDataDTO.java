package com.ruowei.service.dto;

import com.ruowei.service.enumeration.BusinessEnum;
import com.ruowei.web.rest.vm.SewEmiAccountVM;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Deprecated
@Data
public class EmiDataDTO {

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
     * 行业类型编码
     */
    private String industryCode;

    /**
     * 行业类型名称
     */
    private String industryName;

    /**
     * 本月碳排放
     */
    private BigDecimal carbonEmi;

    /**
     * 本月直接碳排放
     */
    private BigDecimal carbonDirEmi;

    /**
     * 本月间接碳排放
     */
    private BigDecimal carbonIndirEmi;

    /**
     * 本月负碳排放
     */
    private BigDecimal carbonRed;

    public EmiDataDTO() {
    }

    public EmiDataDTO(String documentCode, Long enterpriseId, String enterpriseName, Long reporterId, String reporterName, Instant reportTime, SewEmiAccountVM vm, WaterCarbonEmissionOutputDTO outputDTO) {
        this.key = BusinessEnum.EMIDATA.getCode() + documentCode;
        this.docType = BusinessEnum.EMIDATA.getCode();
        this.documentCode = documentCode;
        this.enterpriseId = enterpriseId;
        this.enterpriseName = enterpriseName;
        this.reporterId = reporterId;
        this.reporterName = reporterName;
        this.reportTime = reportTime;
        this.accYear = vm.getAccYear();
        this.accMonth = vm.getAccMonth();
        this.accTime = vm.getAccYear().concat(vm.getAccMonth());
        this.industryName = vm.getIndustryName();
        this.carbonEmi = outputDTO.getC();
        this.carbonDirEmi = outputDTO.getCDirect();
        this.carbonIndirEmi = outputDTO.getCIndirect();
        this.carbonRed = outputDTO.getCReduction();
    }
}
