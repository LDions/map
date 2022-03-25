package com.ruowei.web.rest.dto;

import com.ruowei.domain.Enterprise;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
public class GroupDTO {

    @ApiModelProperty(value = "集团id")
    private Long id;

    /**
     * 集团编码
     */
    @ApiModelProperty(value = "集团编码")
    private String groupCode;

    /**
     * 集团名称
     */
    @ApiModelProperty(value = "集团名称")
    private String groupName;

    /**
     * 集团位置
     */
    @ApiModelProperty(value = "集团位置")
    private String groupAddress;


    /**
     * 联系人
     */
    @ApiModelProperty(value = "集团联系人")
    private String groupContactName;

    /**
     * 电话
     */
    @ApiModelProperty(value = "联系人电话")
    private String groupContactPhone;

    @ApiModelProperty(value = "下属水厂")
    private List<Enterprise> enterprises;
}
