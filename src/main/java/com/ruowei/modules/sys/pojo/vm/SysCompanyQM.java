package com.ruowei.modules.sys.pojo.vm;

import com.ruowei.modules.sys.domain.enumeration.CompanyStatusType;
import io.swagger.annotations.ApiModelProperty;

public class SysCompanyQM {

    @ApiModelProperty(value = "公司代码")
    private String companyCode;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "公司全称")
    private String fullName;

    @ApiModelProperty(value = "状态")
    private CompanyStatusType status;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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

    public CompanyStatusType getStatus() {
        return status;
    }

    public void setStatus(CompanyStatusType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysCompanyVM{" +
            "companyCode='" + companyCode + '\'' +
            ", companyName='" + companyName + '\'' +
            ", fullName='" + fullName + '\'' +
            ", status=" + status +
            '}';
    }
}
