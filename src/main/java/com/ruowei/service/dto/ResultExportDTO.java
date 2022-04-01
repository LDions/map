package com.ruowei.service.dto;

import com.ruowei.domain.EmiData;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 结果导出
 */
@Data
public class ResultExportDTO {


    /**
     * 工艺编码
     */
    private String craftCode;

    /**
     * 工艺名称
     */
    private String craftName;

    /**
     * 水厂名称
     */
    private String enterpriseName;

    /**
     * 结果列表
     */
    private List<EmiData> results;

    /**
     * 总氮阈值
     */
    private BigDecimal threshold1;

    /**
     * 氨氮阈值
     */
    private BigDecimal threshold2;

    /**
     * 总磷阈值
     */
    private BigDecimal threshold3;

    /**
     * 碳投加阈值
     */
    private BigDecimal threshold4;


}
