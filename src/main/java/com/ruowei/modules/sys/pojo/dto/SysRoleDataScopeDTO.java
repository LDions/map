package com.ruowei.modules.sys.pojo.dto;

import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.modules.sys.domain.SysRoleDataScope;
import com.ruowei.modules.sys.domain.enumeration.ControlType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link SysRoleDataScope} entity.
 */
@ApiModel(description = "角色数据权限表 @author 刘东奇")
public class SysRoleDataScopeDTO extends BaseDTO implements Serializable {

    /**
     * 控制角色ID
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "控制角色ID", required = true)
    private String sysRoleId;

    /**
     * 控制类型
     */
    @NotNull
    @ApiModelProperty(value = "控制类型", required = true)
    private ControlType ctrlType;

    /**
     * 控制数据，如控制类型为公司，那么这里就是公司编号
     */
    @Size(max = 100)
    @ApiModelProperty(value = "控制数据，如控制类型为公司，那么这里就是公司编号")
    private String ctrlData;

    /**
     * 控制权限 ???????????????????
     */
    @Size(max = 100)
    @ApiModelProperty(value = "控制权限 ???????????????????")
    private String ctrlPermi;


    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public ControlType getCtrlType() {
        return ctrlType;
    }

    public void setCtrlType(ControlType ctrlType) {
        this.ctrlType = ctrlType;
    }

    public String getCtrlData() {
        return ctrlData;
    }

    public void setCtrlData(String ctrlData) {
        this.ctrlData = ctrlData;
    }

    public String getCtrlPermi() {
        return ctrlPermi;
    }

    public void setCtrlPermi(String ctrlPermi) {
        this.ctrlPermi = ctrlPermi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysRoleDataScopeDTO sysRoleDataScopeDTO = (SysRoleDataScopeDTO) o;
        if (sysRoleDataScopeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysRoleDataScopeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysRoleDataScopeDTO{" +
            "id=" + getId() +
            ", sysRoleId='" + getSysRoleId() + "'" +
            ", ctrlType='" + getCtrlType() + "'" +
            ", ctrlData='" + getCtrlData() + "'" +
            ", ctrlPermi='" + getCtrlPermi() + "'" +
            "}";
    }
}
