package com.ruowei.web.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DraftNumberDTO {

    @ApiModelProperty(value = "草稿数量")
    private Long draftNumber;
}
