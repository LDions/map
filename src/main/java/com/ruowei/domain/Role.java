package com.ruowei.domain;

import com.ruowei.domain.enumeration.RoleStatusType;
import com.ruowei.domain.enumeration.RoleType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 角色
 */
@ApiModel(description = "角色")
@Entity
@Table(name = "sys_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色编码
     */
    @NotNull
    @ApiModelProperty(value = "角色编码", required = true)
    @Column(name = "code", nullable = false)
    private String code;

    /**
     * 角色名称
     */
    @NotNull
    @ApiModelProperty(value = "角色名称", required = true)
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 角色类型
     */
    @NotNull
    @ApiModelProperty(value = "角色类型", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private RoleType type;

    /**
     * 是否内置角色
     */
    @NotNull
    @ApiModelProperty(value = "是否内置角色", required = true)
    @Column(name = "is_sys", nullable = false)
    private Boolean isSys;

    /**
     * 状态
     */
    @NotNull
    @ApiModelProperty(value = "状态", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RoleStatusType status;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role id(Long id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public Role code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public Role name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleType getType() {
        return this.type;
    }

    public Role type(RoleType type) {
        this.type = type;
        return this;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    public Boolean getIsSys() {
        return this.isSys;
    }

    public Role isSys(Boolean isSys) {
        this.isSys = isSys;
        return this;
    }

    public void setIsSys(Boolean isSys) {
        this.isSys = isSys;
    }

    public RoleStatusType getStatus() {
        return this.status;
    }

    public Role status(RoleStatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(RoleStatusType status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        return id != null && id.equals(((Role) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Role{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", isSys='" + getIsSys() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
