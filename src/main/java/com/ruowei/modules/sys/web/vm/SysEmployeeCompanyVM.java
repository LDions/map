package com.ruowei.modules.sys.web.vm;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ruowei.common.json.LongJsonDeserializer;
import com.ruowei.common.json.LongJsonSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;


/**
 * 员工的所属公司信息
 * @author 刘东奇
 */
@ApiModel(description = "员工的所属公司信息 @author 刘东奇")
public class SysEmployeeCompanyVM implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    /**
     * 公司编码
     */
    @ApiModelProperty(value = "公司编码")
    private String companyCode;

    /**
     * 公司代码
     */
    @ApiModelProperty(value = "公司代码")
    private String viewCode;

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    private String companyName;

    /**
     * 公司全称
     */
    @ApiModelProperty(value = "公司全称")
    private String fullName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getCompanyCode() {
        return companyCode;
    }

    public SysEmployeeCompanyVM companyCode(String companyCode) {
        this.companyCode = companyCode;
        return this;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getViewCode() {
        return viewCode;
    }

    public SysEmployeeCompanyVM viewCode(String viewCode) {
        this.viewCode = viewCode;
        return this;
    }

    public void setViewCode(String viewCode) {
        this.viewCode = viewCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public SysEmployeeCompanyVM companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFullName() {
        return fullName;
    }

    public SysEmployeeCompanyVM fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysEmployeeCompanyVM that = (SysEmployeeCompanyVM) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(companyCode, that.companyCode) &&
            Objects.equals(viewCode, that.viewCode) &&
            Objects.equals(companyName, that.companyName) &&
            Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyCode, viewCode, companyName, fullName);
    }
}
