package com.ruowei.modules.sys.pojo.dto;

import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.modules.sys.domain.enumeration.PostStatusType;
import com.ruowei.modules.sys.domain.table.SysPost;
import com.ruowei.modules.sys.domain.enumeration.PostType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link SysPost} entity.
 */
@ApiModel(description = "员工岗位 @author 刘东奇")
public class SysPostDTO  extends BaseDTO implements Serializable {

    public SysPostDTO(){}

    public SysPostDTO(Long id){
        this.id = id;
    }

    /**
     * 岗位编码
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "岗位编码", required = true)
    private String postCode;

    /**
     * 岗位名称
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "岗位名称", required = true)
    private String postName;

    /**
     * 岗位分类（高管、中层、基层）
     */
    @ApiModelProperty(value = "岗位分类（高管、中层、基层）")
    private PostType postType;

    /**
     * 状态
     */
    @NotNull
    @ApiModelProperty(value = "状态", required = true)
    private PostStatusType status;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @ApiModelProperty(value = "备注信息")
    private String remarks;

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public PostStatusType getStatus() {
        return status;
    }

    public void setStatus(PostStatusType status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysPostDTO sysPostDTO = (SysPostDTO) o;
        if (sysPostDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysPostDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysPostDTO{" +
            "id=" + getId() +
            ", postCode='" + getPostCode() + "'" +
            ", postName='" + getPostName() + "'" +
            ", postType='" + getPostType() + "'" +
            ", status='" + getStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
