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
 * 员工的所属机构信息
 * @author 刘东奇
 */
@ApiModel(description = "员工的所属机构信息 @author 刘东奇")
public class SysEmployeeOfficeVM implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    /**
     * 机构编码
     */
    @ApiModelProperty(value = "机构编码")
    private String officeCode;

    /**
     * 机构代码
     */
    @ApiModelProperty(value = "机构代码")
    private String viewCode;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
    private String officeName;

    /**
     * 机构全称
     */
    @ApiModelProperty(value = "机构全称")
    private String fullName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getOfficeCode() {
        return officeCode;
    }

    public SysEmployeeOfficeVM officeCode(String officeCode) {
        this.officeCode = officeCode;
        return this;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getViewCode() {
        return viewCode;
    }

    public SysEmployeeOfficeVM viewCode(String viewCode) {
        this.viewCode = viewCode;
        return this;
    }

    public void setViewCode(String viewCode) {
        this.viewCode = viewCode;
    }

    public String getOfficeName() {
        return officeName;
    }

    public SysEmployeeOfficeVM officeName(String officeName) {
        this.officeName = officeName;
        return this;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getFullName() {
        return fullName;
    }

    public SysEmployeeOfficeVM fullName(String fullName) {
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
        SysEmployeeOfficeVM that = (SysEmployeeOfficeVM) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(officeCode, that.officeCode) &&
            Objects.equals(viewCode, that.viewCode) &&
            Objects.equals(officeName, that.officeName) &&
            Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, officeCode, viewCode, officeName, fullName);
    }
}
