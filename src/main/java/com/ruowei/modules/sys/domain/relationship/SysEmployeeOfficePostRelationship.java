package com.ruowei.modules.sys.domain.relationship;

import com.ruowei.common.entity.PrimaryKeyAutoIncrementEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 员工附属机构关系表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_employee_office")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysEmployeeOfficePostRelationship extends PrimaryKeyAutoIncrementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工ID
     */
    @NotNull
    @Column(name = "sys_employee_id", length = 20, nullable = false)
    private Long sysEmployeeId;

    /**
     * 机构ID
     */
    @NotNull
    @Column(name = "sys_office_id", length = 20, nullable = false)
    private Long sysOfficeId;

    /**
     * 岗位ID
     */
    @NotNull
    @Column(name = "sys_post_id", length = 20, nullable = false)
    private Long sysPostId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getSysEmployeeId() {
        return sysEmployeeId;
    }

    public SysEmployeeOfficePostRelationship sysEmployeeId(Long sysEmployeeId) {
        this.sysEmployeeId = sysEmployeeId;
        return this;
    }

    public void setSysEmployeeId(Long sysEmployeeId) {
        this.sysEmployeeId = sysEmployeeId;
    }

    public Long getSysOfficeId() {
        return sysOfficeId;
    }

    public SysEmployeeOfficePostRelationship sysOfficeId(Long sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
        return this;
    }

    public void setSysOfficeId(Long sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    public Long getSysPostId() {
        return sysPostId;
    }

    public SysEmployeeOfficePostRelationship sysPostId(Long sysPostId) {
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
        if (!(o instanceof SysEmployeeOfficePostRelationship)) {
            return false;
        }
        return id != null && id.equals(((SysEmployeeOfficePostRelationship) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysEmployeeOffice{" +
            "id=" + getId() +
            ", sysEmployeeId='" + getSysEmployeeId() + "'" +
            ", sysOfficeId='" + getSysOfficeId() + "'" +
            ", sysPostId='" + getSysPostId() + "'" +
            "}";
    }
}
