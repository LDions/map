package com.ruowei.modules.sys.domain;

import com.ruowei.common.entity.PrimaryKeyAutoIncrementEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 员工与岗位关联表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_employee_post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysEmployeePost extends PrimaryKeyAutoIncrementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "sys_employee_id", length = 20, nullable = false)
    private Long sysEmployeeId;

    /**
     * 岗位ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "sys_post_id", length = 20, nullable = false)
    private Long sysPostId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getSysEmployeeId() {
        return sysEmployeeId;
    }

    public SysEmployeePost sysEmployeeId(Long sysEmployeeId) {
        this.sysEmployeeId = sysEmployeeId;
        return this;
    }

    public void setSysEmployeeId(Long sysEmployeeId) {
        this.sysEmployeeId = sysEmployeeId;
    }

    public Long getSysPostId() {
        return sysPostId;
    }

    public SysEmployeePost sysPostId(Long sysPostId) {
        this.sysPostId = sysPostId;
        return this;
    }

    public void setSysPostId(Long sysPostId) {
        this.sysPostId = sysPostId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysEmployeePost)) {
            return false;
        }
        return id != null && id.equals(((SysEmployeePost) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysEmployeePost{" +
            "id=" + getId() +
            ", sysEmployeeId='" + getSysEmployeeId() + "'" +
            ", sysPostId='" + getSysPostId() + "'" +
            "}";
    }
}
