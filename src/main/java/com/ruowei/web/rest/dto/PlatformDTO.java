package com.ruowei.web.rest.dto;

import com.ruowei.domain.Enterprise;
import com.ruowei.domain.Group;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PlatformDTO {

    /**
     * 下属集团
     */
    @ApiModelProperty(value = "下属集团")
    private List<Group> groups;

    /**
     * 下属水厂
     */
    @ApiModelProperty(value = "下属水厂")
    private List<Enterprise> enterprises;
}
