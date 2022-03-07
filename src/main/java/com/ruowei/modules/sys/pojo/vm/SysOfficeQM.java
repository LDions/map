package com.ruowei.modules.sys.pojo.vm;

import com.ruowei.modules.sys.domain.enumeration.OfficeStatusType;
import com.ruowei.modules.sys.domain.enumeration.OfficeType;
import io.swagger.annotations.ApiModelProperty;

public class SysOfficeQM {

    @ApiModelProperty("机构ID")
    private Long officeId;

    @ApiModelProperty(value = "机构代码")
    private String officeCode;

    @ApiModelProperty(value = "机构名称")
    private String officeName;

    @ApiModelProperty(value = "机构全称")
    private String fullName;

    @ApiModelProperty(value = "机构类型")
    private OfficeType officeType;

    @ApiModelProperty(value = "状态")
    private OfficeStatusType status;

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

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

    public OfficeType getOfficeType() {
        return officeType;
    }

    public void setOfficeType(OfficeType officeType) {
        this.officeType = officeType;
    }

    public OfficeStatusType getStatus() {
        return status;
    }

    public void setStatus(OfficeStatusType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysOfficeQM{" +
            "officeId=" + officeId +
            ", officeCode='" + officeCode + '\'' +
            ", officeName='" + officeName + '\'' +
            ", fullName='" + fullName + '\'' +
            ", officeType=" + officeType +
            ", status=" + status +
            '}';
    }
}
