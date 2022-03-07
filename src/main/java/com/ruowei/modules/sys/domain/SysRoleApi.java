package com.ruowei.modules.sys.domain;
import com.ruowei.common.entity.PrimaryKeyAutoIncrementEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * 角色接口关联表
 */
@ApiModel(description = "角色接口关联表")
@Entity
@Table(name = "sys_role_api")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysRoleApi extends PrimaryKeyAutoIncrementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色外键
     */
    @ApiModelProperty(value = "角色外键")
    @Column(name = "sys_role_id")
    private Long sysRoleId;

    /**
     * 接口外键
     */
    @ApiModelProperty(value = "接口外键")
    @Column(name = "sys_api_id")
    private Long sysApiId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSysRoleId() {
        return sysRoleId;
    }

    public SysRoleApi sysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
        return this;
    }

    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Long getSysApiId() {
        return sysApiId;
    }

    public SysRoleApi sysApiId(Long sysApiId) {
        this.sysApiId = sysApiId;
        return this;
    }

    public void setSysApiId(Long sysApiId) {
        this.sysApiId = sysApiId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysRoleApi)) {
            return false;
        }
        return id != null && id.equals(((SysRoleApi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysRoleApi{" +
            "id=" + getId() +
            ", sysRoleId='" + getSysRoleId() + "'" +
            ", sysApiId='" + getSysApiId() + "'" +
            "}";
    }
}
