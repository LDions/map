package com.ruowei.modules.sys.pojo.dto;

import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.modules.sys.domain.SysEmployeePost;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link SysEmployeePost} entity.
 */
@ApiModel(description = "员工与岗位关联表 @author 刘东奇")
public class SysEmployeePostDTO extends BaseDTO implements Serializable {

    /**
     * 员工ID
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "员工ID", required = true)
    private Long sysEmployeeId;

    /**
     * 岗位ID
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "岗位ID", required = true)
    private Long sysPostId;

    public Long getSysEmployeeId() {
        return sysEmployeeId;
    }

    public void setSysEmployeeId(Long sysEmployeeId) {
        this.sysEmployeeId = sysEmployeeId;
    }

    public Long getSysPostId() {
        return sysPostId;
    }

    public void setSysPostId(Long sysPostId) {
        this.sysPostId = sysPostId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysEmployeePostDTO sysEmployeePostDTO = (SysEmployeePostDTO) o;
        if (sysEmployeePostDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysEmployeePostDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysEmployeePostDTO{" +
            "id=" + getId() +
            ", sysEmployeeId='" + getSysEmployeeId() + "'" +
            ", sysPostId='" + getSysPostId() + "'" +
            "}";
    }
}
