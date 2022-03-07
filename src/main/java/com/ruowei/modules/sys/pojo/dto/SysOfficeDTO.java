package com.ruowei.modules.sys.pojo.dto;

import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.modules.sys.domain.enumeration.OfficeType;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.domain.enumeration.OfficeStatusType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link SysOffice} entity.
 */
@ApiModel(description = "组织机构表 @author 刘东奇")
public class SysOfficeDTO extends BaseDTO implements Serializable {

    public SysOfficeDTO(){}

    public SysOfficeDTO(Long id){
        this.id = id;
    }
    /**
     * 机构编码
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "机构编码", required = true)
    private String officeCode;

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
    @ApiModelProperty(value = "本级排序号（升序）")
    private Integer treeSort;

    /**
     * 所有级别排序号
     */
    @ApiModelProperty(value = "所有级别排序号")
    private Integer treeSorts;

    /**
     * 是否最末级
     */
    @NotNull
    @ApiModelProperty(value = "是否最末级", required = true)
    private Boolean treeLeaf;

    /**
     * 层次级别
     */
    @NotNull
    @ApiModelProperty(value = "层次级别", required = true)
    private Integer treeLevel;

    /**
     * 全节点名
     */
    @NotNull
    @Size(max = 1000)
    @ApiModelProperty(value = "全节点名", required = true)
    private String treeNames;

    /**
     * 机构代码
     */
    @Size(max = 100)
    @ApiModelProperty(value = "机构代码")
    private String viewCode;

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
     * 机构类型
     */
    @NotNull
    @ApiModelProperty(value = "机构类型", required = true)
    private OfficeType officeType;

    /**
     * 负责人
     */
    @Size(max = 100)
    @ApiModelProperty(value = "负责人")
    private String leader;

    /**
     * 办公电话
     */
    @Size(max = 100)
    @ApiModelProperty(value = "办公电话")
    private String phone;

    /**
     * 联系地址
     */
    @Size(max = 255)
    @ApiModelProperty(value = "联系地址")
    private String address;

    /**
     * 邮政编码
     */
    @Size(max = 100)
    @ApiModelProperty(value = "邮政编码")
    private String zipCode;

    /**
     * 电子邮箱
     */
    @Size(max = 300)
    @ApiModelProperty(value = "电子邮箱")
    private String email;

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

    private List<SysPostDTO> sysPostDTOList;

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
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

    public OfficeType getOfficeType() {
        return officeType;
    }

    public void setOfficeType(OfficeType officeType) {
        this.officeType = officeType;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<SysPostDTO> getSysPostDTOList() {
        return sysPostDTOList;
    }

    public void setSysPostDTOList(List<SysPostDTO> sysPostDTOList) {
        this.sysPostDTOList = sysPostDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysOfficeDTO sysOfficeDTO = (SysOfficeDTO) o;
        if (sysOfficeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysOfficeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysOfficeDTO{" +
            "id=" + getId() +
            ", officeCode='" + getOfficeCode() + "'" +
            ", parentCode='" + getParentCode() + "'" +
            ", parentCodes='" + getParentCodes() + "'" +
            ", treeSort=" + getTreeSort() +
            ", treeSorts=" + getTreeSorts() +
            ", treeLeaf='" + isTreeLeaf() + "'" +
            ", treeLevel=" + getTreeLevel() +
            ", treeNames='" + getTreeNames() + "'" +
            ", viewCode='" + getViewCode() + "'" +
            ", officeName='" + getOfficeName() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", officeType='" + getOfficeType() + "'" +
            ", leader='" + getLeader() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", email='" + getEmail() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
