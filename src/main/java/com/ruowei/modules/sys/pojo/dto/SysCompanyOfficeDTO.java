package com.ruowei.modules.sys.pojo.dto;

import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.modules.sys.domain.SysCompanyOffice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link SysCompanyOffice} entity.
 */
@ApiModel(description = "公司部门关联表 @author 刘东奇")
public class SysCompanyOfficeDTO extends BaseDTO implements Serializable {

    /**
     * 公司ID
     */
    @NotNull
    @ApiModelProperty(value = "公司ID", required = true)
    private Long sysCompanyId;

    /**
     * 机构ID
     */
    @NotNull
    @ApiModelProperty(value = "机构ID", required = true)
    private Long sysOfficeId;

    public Long getSysCompanyId() {
        return sysCompanyId;
    }

    public void setSysCompanyId(Long sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public Long getSysOfficeId() {
        return sysOfficeId;
    }

    public void setSysOfficeId(Long sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysCompanyOfficeDTO sysCompanyOfficeDTO = (SysCompanyOfficeDTO) o;
        if (sysCompanyOfficeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysCompanyOfficeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysCompanyOfficeDTO{" +
            "id=" + getId() +
            ", sysCompanyId='" + getSysCompanyId() + "'" +
            ", sysOfficeId='" + getSysOfficeId() + "'" +
            "}";
    }
}
