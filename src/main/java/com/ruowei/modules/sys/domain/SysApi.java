package com.ruowei.modules.sys.domain;
import com.ruowei.common.entity.PrimaryKeyAutoIncrementEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * 接口表
 */
@ApiModel(description = "接口表")
@Entity
@Table(name = "sys_api")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysApi extends PrimaryKeyAutoIncrementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口名称
     */
    @ApiModelProperty(value = "接口名称")
    @Column(name = "name")
    private String name;

    /**
     * 请求方法
     */
    @ApiModelProperty(value = "请求方法")
    @Column(name = "request_method")
    private String requestMethod;

    /**
     * 路径
     */
    @ApiModelProperty(value = "路径")
    @Column(name = "url")
    private String url;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SysApi name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public SysApi requestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getUrl() {
        return url;
    }

    public SysApi url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysApi)) {
            return false;
        }
        return id != null && id.equals(((SysApi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysApi{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", requestMethod='" + getRequestMethod() + "'" +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
