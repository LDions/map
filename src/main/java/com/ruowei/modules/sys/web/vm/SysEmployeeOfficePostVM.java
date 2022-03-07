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
 * 员工的附属机构及岗位信息
 * @author 刘东奇
 */
@ApiModel(description = "员工的附属机构及岗位信息 @author 刘东奇")
public class SysEmployeeOfficePostVM  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    /**
     * 机构
     */
    @ApiModelProperty(value = "机构")
    private SysEmployeeOfficeVM office;

    /**
     * 岗位
     */
    @ApiModelProperty(value = "岗位")
    private SysEmployeePostVM post;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SysEmployeeOfficeVM getOffice() {
        return office;
    }

    public void setOffice(SysEmployeeOfficeVM office) {
        this.office = office;
    }

    public SysEmployeePostVM getPost() {
        return post;
    }

    public void setPost(SysEmployeePostVM post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysEmployeeOfficePostVM that = (SysEmployeeOfficePostVM) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(office, that.office) &&
            Objects.equals(post, that.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, office, post);
    }

    @Override
    public String toString() {
        return "SysEmployeeOfficePostVM{" +
            "id=" + id +
            ", office=" + office +
            ", post=" + post +
            '}';
    }
}
