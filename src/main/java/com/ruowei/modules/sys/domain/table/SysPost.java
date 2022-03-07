package com.ruowei.modules.sys.domain.table;

import com.ruowei.common.entity.PrimaryKeyAutoIncrementEntity;
import com.ruowei.modules.sys.domain.enumeration.PostStatusType;
import com.ruowei.modules.sys.domain.enumeration.PostType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 员工岗位
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysPost extends PrimaryKeyAutoIncrementEntity implements Serializable {

    public SysPost(){}

    public SysPost(Long id) {
        this.id = id;
    }

    public SysPost(String id) {
        this.id = (id == null ? null : Long.parseLong(id));
    }

    private static final long serialVersionUID = 1L;

    /**
     * 岗位编码
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "post_code", length = 100, nullable = false, unique = true)
    private String postCode;

    /**
     * 岗位名称
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "post_name", length = 100, nullable = false)
    private String postName;

    /**
     * 岗位分类（高管、中层、基层）
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "post_type")
    private PostType postType;

    /**
     * 状态
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PostStatusType status;

    /**
     * 备注信息
     */
    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getPostCode() {
        return postCode;
    }

    public SysPost postCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostName() {
        return postName;
    }

    public SysPost postName(String postName) {
        this.postName = postName;
        return this;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public PostType getPostType() {
        return postType;
    }

    public SysPost postType(PostType postType) {
        this.postType = postType;
        return this;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public PostStatusType getStatus() {
        return status;
    }

    public SysPost status(PostStatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(PostStatusType status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public SysPost remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysPost)) {
            return false;
        }
        return id != null && id.equals(((SysPost) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysPost{" +
            "id=" + getId() +
            ", postCode='" + getPostCode() + "'" +
            ", postName='" + getPostName() + "'" +
            ", postType='" + getPostType() + "'" +
            ", status='" + getStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }


}
