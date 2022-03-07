package com.ruowei.modules.sys.pojo.dto;

import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.modules.sys.domain.table.SysCompany;
import com.ruowei.modules.sys.domain.enumeration.CompanyStatusType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link SysCompany} entity.
 *
 * @author 刘东奇
 */
@ApiModel(description = "公司表 @author 刘东奇")
public class SysCompanyDTO extends BaseDTO implements Serializable {

    /**
     * 公司编码
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "公司编码", required = true)
    private String companyCode;

    /**
     * 父级编号
     */
    @Size(max = 100)
    @ApiModelProperty(value = "父级编号")
    private String parentCode;

    /**
     * 所有父级编号
     */
    @Size(max = 1000)
    @ApiModelProperty(value = "所有父级编号")
    private String parentCodes;

    /**
     * 本级排序号（升序）
     */
    @ApiModelProperty(value = "本级排序号（升序）", example = "0")
    private Integer treeSort;

    /**
     * 所有级别排序号
     */
    @ApiModelProperty(value = "所有级别排序号", example = "0")
    private Integer treeSorts;

    /**
     * 是否最末级
     */
    @NotNull
    @ApiModelProperty(value = "是否最末级")
    private Boolean treeLeaf;

    /**
     * 层次级别
     */
    @ApiModelProperty(value = "层次级别", example = "0")
    private Integer treeLevel;

    /**
     * 全节点名
     */
    @Size(max = 1000)
    @ApiModelProperty(value = "全节点名")
    private String treeNames;

    /**
     * 公司代码
     */
    @Size(max = 100)
    @ApiModelProperty(value = "公司代码")
    private String viewCode;

    /**
     * 公司名称
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "公司名称", required = true)
    private String companyName;

    /**
     * 公司全称
     */
    @NotNull
    @Size(max = 200)
    @ApiModelProperty(value = "公司全称", required = true)
    private String fullName;

    /**
     * 区域编码
     */
    @Size(max = 100)
    @ApiModelProperty(value = "区域编码")
    private String areaCode;

    /**
     * 状态
     */
    @NotNull
    @ApiModelProperty(value = "状态", required = true)
    private CompanyStatusType status;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @ApiModelProperty(value = "备注信息")
    private String remarks;

    @ApiModelProperty(value = "包含机构ID")
    private List<String> sysOfficeIds;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentCodes() {
        return parentCodes;
    }

    public void setParentCodes(String parentCodes) {
        this.parentCodes = parentCodes;
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

    public Boolean isTreeLeaf() {
        return treeLeaf;
    }

    public void setTreeLeaf(Boolean treeLeaf) {
        this.treeLeaf = treeLeaf;
    }

    public Integer getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Integer treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getTreeNames() {
        return treeNames;
    }

    public void setTreeNames(String treeNames) {
        this.treeNames = treeNames;
    }

    public String getViewCode() {
        return viewCode;
    }

    public void setViewCode(String viewCode) {
        this.viewCode = viewCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public CompanyStatusType getStatus() {
        return status;
    }

    public void setStatus(CompanyStatusType status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<String> getSysOfficeIds() {
        return sysOfficeIds;
    }

    public void setSysOfficeIds(List<String> sysOfficeIds) {
        this.sysOfficeIds = sysOfficeIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysCompanyDTO sysCompanyDTO = (SysCompanyDTO) o;
        if (sysCompanyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysCompanyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysCompanyDTO{" +
            "companyCode='" + companyCode + '\'' +
            ", parentCode='" + parentCode + '\'' +
            ", parentCodes='" + parentCodes + '\'' +
            ", treeSort=" + treeSort +
            ", treeSorts=" + treeSorts +
            ", treeLeaf=" + treeLeaf +
            ", treeLevel=" + treeLevel +
            ", treeNames='" + treeNames + '\'' +
            ", viewCode='" + viewCode + '\'' +
            ", companyName='" + companyName + '\'' +
            ", fullName='" + fullName + '\'' +
            ", areaCode='" + areaCode + '\'' +
            ", status=" + status +
            ", remarks='" + remarks + '\'' +
            ", sysOfficeIds=" + sysOfficeIds +
            ", id=" + id +
            '}';
    }
}
