package com.ruowei.web.rest.vm;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class UserVM {

    @ApiModelProperty(value = "id")
    private Long id;

    @NotNull
    @ApiModelProperty(value = "用户名", required = true)
    private String login;

    @NotNull
    @ApiModelProperty(value = "用户昵称", required = true)
    private String nickName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty("用户角色")
    private List<String> roleIds = new ArrayList<>();

    @ApiModelProperty(value = "水厂ID")
    private Long enterpriseId;

    @ApiModelProperty(value = "水厂ID")
    private Long groupId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserVM)) {
            return false;
        }
        return id != null && id.equals(((UserVM) o).id);
    }
    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
    // prettier-ignore
    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", login='" + login + '\'' +
            ", nickName='" + nickName + '\'' +
            ", remark='" + remark + '\'' +
            ", enterpriseId=" + enterpriseId +
            ", groupId=" + groupId +
            '}';
    }
}
