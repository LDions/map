package com.ruowei.modules.sys.pojo;

import com.ruowei.common.pojo.AuditingTreeDTO;
import com.ruowei.modules.sys.domain.enumeration.OfficeType;
import com.ruowei.modules.sys.domain.enumeration.OfficeStatusType;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author 刘东奇
 */
public class SysOfficeTreeDTO extends AuditingTreeDTO<SysOfficeTreeDTO> {
    /**
     * 机构编码
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "机构编码", required = true)
    private String officeCode;

    /**
     * 机构名称
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "机构名称", required = true)
    private String officeName;

    /**
     * 机构全称
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "机构全称", required = true)
    private String fullName;

    /**
     * 本级排序号（升序）
     */
    @ApiModelProperty(value = "本级排序号（升序）")
    private Integer treeSort;

    /**
     * 所有级别排序号
     */
    @ApiModelProperty(value = "所有级别排序号")
    private Integer treeSorts;

    /**
     * 机构类型
     */
    @NotNull
    @ApiModelProperty(value = "机构类型", required = true)
    private OfficeType officeType;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @ApiModelProperty(value = "备注信息")
    private String remarks;

    /**
     * 状态
     */
    @NotNull
    @ApiModelProperty(value = "状态", required = true)
    private OfficeStatusType status;

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getTreeSort() {
        return treeSort;
    }

    public void setTreeSort(Integer treeSort) {
        this.treeSort = treeSort;
    }

    public Integer getTreeSorts() {
        return treeSorts;
    }

    public void setTreeSorts(Integer treeSorts) {
        this.treeSorts = treeSorts;
    }

    public OfficeType getOfficeType() {
        return officeType;
    }

    public void setOfficeType(OfficeType officeType) {
        this.officeType = officeType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public OfficeStatusType getStatus() {
        return status;
    }

    public void setStatus(OfficeStatusType status) {
        this.status = status;
    }
}
