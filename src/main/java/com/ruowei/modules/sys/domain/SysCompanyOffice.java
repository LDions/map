package com.ruowei.modules.sys.domain;
import com.ruowei.common.entity.PrimaryKeyAutoIncrementEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * 公司部门关联表
 * @author JeeSite
 */
@ApiModel(description = "公司部门关联表 @author JeeSite")
@Entity
@Table(name = "sys_company_office")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysCompanyOffice extends PrimaryKeyAutoIncrementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司ID
     */
    @ApiModelProperty(value = "公司ID")
    @Column(name = "sys_company_id")
    private Long sysCompanyId;

    /**
     * 机构ID
     */
    @ApiModelProperty(value = "机构ID")
    @Column(name = "sys_office_id")
    private Long sysOfficeId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getSysCompanyId() {
        return sysCompanyId;
    }

    public SysCompanyOffice sysCompanyId(Long sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
        return this;
    }

    public void setSysCompanyId(Long sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public Long getSysOfficeId() {
        return sysOfficeId;
    }

    public SysCompanyOffice sysOfficeId(Long sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
        return this;
    }

    public void setSysOfficeId(Long sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysCompanyOffice)) {
            return false;
        }
        return id != null && id.equals(((SysCompanyOffice) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysCompanyOffice{" +
            "id=" + getId() +
            ", sysCompanyId='" + getSysCompanyId() + "'" +
            ", sysOfficeId='" + getSysOfficeId() + "'" +
            "}";
    }
}
