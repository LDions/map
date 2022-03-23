package com.ruowei.web.rest.vm;

import com.ruowei.domain.Inflow;
import com.ruowei.domain.TnData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TotalNitrogenVM {

    @ApiModelProperty(value = "进水流量")
    private List<Inflow> inflowList;

    @ApiModelProperty(value = "总氮参数data")
    private List<TnData> data;
}
