package com.ruowei.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruowei.domain.enumeration.SendStatusType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户
 */
@ApiModel(description = "用户")
@Entity
@Table(name = "sys_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ApiModelProperty(value = "用户名", required = true)
    @Column(name = "login", nullable = false)
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @ApiModelProperty(value = "密码", required = true)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @NotNull
    @ApiModelProperty(value = "用户昵称", required = true)
    @Column(name = "nick_name", nullable = false)
    private String nickName;

    @ApiModelProperty(value = "备注")
    @Column(name = "remark")
    private String remark;

    @ApiModelProperty(value = "水厂编码")
    @Column(name = "enterprise_code")
    private String enterpriseCode;

    @ApiModelProperty(value = "水厂名称")
    @Column(name = "enterprise_name")
    private String enterpriseName;

    @ApiModelProperty(value = "集团编码")
    @Column(name = "group_code")
    private String groupCode;

    @ApiModelProperty(value = "集团名称")
    @Column(name = "group_name")
    private String groupName;

    @ApiModelProperty(value = "用户编码")
    @Column(name = "user_code")
    private String userCode;

    @ApiModelProperty(value = "是否删除")
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    /**
     * 集团数据推送状态
     */
    @ApiModelProperty(value = "集团数据推送状态")
    @Column(name = "status")
    private SendStatusType status;

    /**
     * 平台数据推送状态
     */
    @ApiModelProperty(value = "平台数据推送状态")
    @Column(name = "plate_status")
    private SendStatusType plateStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User id(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User login(String login) {
        this.login = login;
        return this;
    }

    // Lowercase the login before saving it in database
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public User nickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRemark() {
        return remark;
    }

    public User remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public User enterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
        return this;
    }

    public User groupCode(String groupCode) {
        this.groupCode = groupCode;
        return this;
    }

    public String getUserCode() {
        return userCode;
    }

    public User userCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public SendStatusType getStatus() {
        return status;
    }

    public void setStatus(SendStatusType status) {
        this.status = status;
    }

    public SendStatusType getPlateStatus() {
        return plateStatus;
    }

    public void setPlateStatus(SendStatusType plateStatus) {
        this.plateStatus = plateStatus;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public User enterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
        return this;
    }
    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getGroupName() {
        return groupName;
    }

    public User groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        return id != null && id.equals(((User) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", nickName='" + nickName + '\'' +
            ", remark='" + remark + '\'' +
            ", enterpriseCode='" + enterpriseCode + '\'' +
            ", enterpriseName='" + enterpriseName + '\'' +
            ", groupCode='" + groupCode + '\'' +
            ", groupName='" + groupName + '\'' +
            ", userCode='" + userCode + '\'' +
            ", status=" + status +
            ", plateStatus=" + plateStatus +
            '}';
    }
}
