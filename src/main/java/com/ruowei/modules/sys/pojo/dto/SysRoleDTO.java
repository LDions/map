package com.ruowei.modules.sys.pojo.dto;

import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.modules.sys.domain.enumeration.DataScopeType;
import com.ruowei.modules.sys.domain.enumeration.RoleStatusType;
import com.ruowei.modules.sys.domain.enumeration.RoleType;
import com.ruowei.modules.sys.domain.table.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link SysRole} entity.
 */
@ApiModel(description = "角色表 @author 刘东奇")
public class SysRoleDTO extends BaseDTO implements Serializable {

    public SysRoleDTO(){}

    public SysRoleDTO(Long id){
        this.id = id;
    }

    /**
     * 角色编码 ，该字段不作为表的关联外键，仅供展示
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "角色编码 ，该字段不作为表的关联外键，仅供展示", required = true)
    private String roleCode;

    /**
     * 角色名称
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "角色名称", required = true)
    private String roleName;

    /**
     * 角色类型
     */
    @NotNull
    @ApiModelProperty(value = "角色类型", required = true)
    private RoleType roleType;

    /**
     * 角色排序（升序）
     */
    @ApiModelProperty(value = "角色排序（升序）")
    private Integer roleSort;

    /**
     * 是否系统内置
     */
    @NotNull
    @ApiModelProperty(value = "是否系统内置", required = true)
    private Boolean isSys;

    /**
     * 数据范围设置
     */
    @ApiModelProperty(value = "数据范围设置")
    private DataScopeType dataScope;

    /**
     * 适应业务范围
     */
    @Size(max = 255)
    @ApiModelProperty(value = "适应业务范围")
    private String bizScope;

    /**
     * 状态
     */
    @NotNull
    @ApiModelProperty(value = "状态", required = true)
    private RoleStatusType status;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @ApiModelProperty(value = "备注信息")
    private String remarks;


    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Integer getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(Integer roleSort) {
        this.roleSort = roleSort;
    }

    public Boolean isIsSys() {
        return isSys;
    }

    public void setIsSys(Boolean isSys) {
        this.isSys = isSys;
    }

    public DataScopeType getDataScope() {
        return dataScope;
    }

    public void setDataScope(DataScopeType dataScope) {
        this.dataScope = dataScope;
    }

    public String getBizScope() {
        return bizScope;
    }

    public void setBizScope(String bizScope) {
        this.bizScope = bizScope;
    }

    public RoleStatusType getStatus() {
        return status;
    }

    public void setStatus(RoleStatusType status) {
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

        SysRoleDTO sysRoleDTO = (SysRoleDTO) o;
        if (sysRoleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysRoleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysRoleDTO{" +
            "id=" + getId() +
            ", roleCode='" + getRoleCode() + "'" +
            ", roleName='" + getRoleName() + "'" +
            ", roleType='" + getRoleType() + "'" +
            ", roleSort=" + getRoleSort() +
            ", isSys='" + isIsSys() + "'" +
            ", dataScope='" + getDataScope() + "'" +
            ", bizScope='" + getBizScope() + "'" +
            ", status='" + getStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
