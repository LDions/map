package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class DataDto {
    @ApiModelProperty("碳层名称")
    private String id;

    @ApiModelProperty("碳层面积")
    private BigDecimal layerArea;



//    @ApiModelProperty("碳层经营方式")
//    private String operationMode;
//
//    @ApiModelProperty("碳层下各树种的林木信息")
//    private List<LayerSpecies> layerSpeciesList;
//
//    @Data
//    public static class LayerSpecies {
//
//        @ApiModelProperty("碳层下该树种 编码")
//        private String speciesCode;
//
//        @ApiModelProperty("碳层下该树种 名称")
//        private String speciesName;
//
//        @ApiModelProperty("碳层下该树种 种植年份")
//        private String plantTime;
//
//        @ApiModelProperty("碳层下该树种 起种林龄")
//        private Integer treeAge;
//
//        @ApiModelProperty("碳层下该树种 平均株数")
//        private List<CoverPrediction> coverPredictions;
//
//        @ApiModelProperty("林木的 采伐量-年份 列表")
//        private List<YearCutMap> yearCutMaps;
//    }
//
//    @Data
//    public static class YearCutMap {
//
//        @ApiModelProperty("采伐年份")
//        private String yearCut;
//
//        @ApiModelProperty("采伐蓄积量")
//        private BigDecimal treeCut;
//    }
//
//    @Data
//    public static class CoverPrediction {
//
//        @ApiModelProperty("记录年份")
//        private String year;
//
//        @ApiModelProperty("平均株树")
//        private BigDecimal cover;
//    }
}
