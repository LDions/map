package com.ruowei.web.rest.dto;

import com.ruowei.web.rest.vm.SituationAnalysisQM;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.List;

@Data
public class ScreenDisplayConformityDTO {

    @ApiParam(value = "工艺编号")
    private String craftCode;

    @ApiParam(value = "数据信息")
    private List<SituationAnalysisQM> targets;


}
