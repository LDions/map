package com.ruowei.domain;

/**
 * 集团信息
 */
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;


@ApiModel(description = "集团信息")
@Entity
@Table(name = "sew_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class Group {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 集团编码
     */
    @ApiModelProperty(value = "集团编码")
    @Column(name = "group_code")
    private String groupCode;

    /**
     * 集团名称
     */
    @ApiModelProperty(value = "集团名称")
    @Column(name = "group_name")
    private String groupName;

    /**
     * 集团位置
     */
    @ApiModelProperty(value = "集团位置")
    @Column(name = "group_address")
    private String groupAddress;


    /**
     * 联系人
     */
    @ApiModelProperty(value = "集团联系人")
    @Column(name = "group_contact_name")
    private String groupContactName;

    /**
     * 电话
     */
    @ApiModelProperty(value = "联系人电话")
    @Column(name = "group_contact_phone")
    private String groupContactPhone;

    public Group id(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group groupCode(String groupCode) {
        this.groupCode = groupCode;
        return this;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public Group groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupAddress() {
        return groupAddress;
    }
    public Group groupAddress(String groupAddress) {
        this.groupAddress = groupAddress;
        return this;
    }
    public void setGroupAddress(String groupAddress) {
        this.groupAddress = groupAddress;
    }

    public String getGroupContactName() {
        return groupContactName;
    }
    public Group groupContactName(String groupContactName) {
        this.groupContactName = groupContactName;
        return this;
    }
    public void setGroupContactName(String groupContactName) {
        this.groupContactName = groupContactName;
    }

    public String getGroupContactPhone() {
        return groupContactPhone;
    }
    public Group groupContactPhone(String groupContactPhone) {
        this.groupContactPhone = groupContactPhone;
        return this;
    }
    public void setGroupContactPhone(String groupContactPhone) {
        this.groupContactPhone = groupContactPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Group)) {
            return false;
        }
        return id != null && id.equals(((Group) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Group{" +
            "id=" + id +
            ", groupName='" + groupName + '\'' +
            ", groupAddress='" + groupAddress + '\'' +
            ", groupContactName='" + groupContactName + '\'' +
            ", groupContactPhone='" + groupContactPhone + '\'' +
            ", groupCode='" + groupCode + '\'' +
            '}';
    }
}
