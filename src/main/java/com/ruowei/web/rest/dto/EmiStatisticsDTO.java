package com.ruowei.web.rest.dto;

import com.ruowei.service.dto.SewEmiQueryDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class EmiStatisticsDTO {

    @ApiModelProperty(value = "碳排放/减排量占比")
    private Map<String, Object> percentageOfEmiData;

    @ApiModelProperty(value = "碳排放/减排量表格")
    private SewEmiTableDTO tableOfEmiData;

    @ApiModelProperty(value = "折线图X轴日期参数")
    private List<String> dateList;

    @ApiModelProperty(value = "碳排放总量变化趋势")
    private List<Line> changeOfTotalEmi;

    @ApiModelProperty(value = "碳排放总量占比")
    private List<Block> percentageOfTotalEmi;

    @ApiModelProperty(value = "各类碳排放量变化趋势")
    private List<Line> changeInVariousTypesOfEmi;

    @ApiModelProperty(value = "各类碳排放量占比")
    private List<Block> percentageInVariousTypesOfEmi;

    @ApiModelProperty(value = "耗电间接碳排放量变化趋势")
    private List<Line> changeOfIndirectEmiFromElec;

    @ApiModelProperty(value = "药剂投加间接碳排放量变化趋势")
    private List<Line> changeOfIndirectEmiFromPot;

    @ApiModelProperty(value = "污水处理直接碳排放量变化趋势")
    private List<Line> changeOfDirectEmiFromSewTreat;

    @ApiModelProperty(value = "污泥处理间接碳排放量变化趋势")
    private List<Line> changeOfIndirectEmiFromSluTreat;

    @ApiModelProperty(value = "碳减排项目碳减排量变化趋势")
    private List<Line> changeOfCarbonReduction;

    @ApiModelProperty(value = "污泥处置碳排放量变化趋势")
    private List<Line> changeOfEmiFromSluHandle;

    @Data
    public static class Line {

        private String name;

        private List<BigDecimal> data;

        public Line(String name, List<BigDecimal> data) {
            this.name = name;
            this.data = data;
        }
    }

    @Data
    public static class Block {

        private String name;

        private BigDecimal value;

        public Block(String name, BigDecimal value) {
            this.name = name;
            this.value = value;
        }
    }
}
