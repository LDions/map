package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CollectQM {

    @ApiModelProperty(value = "被关联的数据")
    private SituationAnalysisQM beAssociated;

    @ApiModelProperty(value = "关联到的数据")
    private List<SituationAnalysisQM> relation;

}
