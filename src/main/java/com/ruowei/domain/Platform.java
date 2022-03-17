package com.ruowei.domain;

import com.ruowei.domain.enumeration.MenuStatusType;
import com.ruowei.domain.enumeration.MenuType;
import com.ruowei.domain.enumeration.SysType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 平台表
 */
@ApiModel(description = "平台表")
@Entity
@Table(name = "sys_platform")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Platform implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 平台名称
     */
    @NotNull
    @ApiModelProperty(value = "平台名称", required = true)
    @Column(name = "platform_name", nullable = false, unique = true)
    private String platformName;


    /**
     * 平台地址
     */
    @ApiModelProperty(value = "平台详细地址")
    @Column(name = "platform_address")
    private String platformAddress;


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Platform)) {
            return false;
        }
        return id != null && id.equals(((Platform) o).id);
    }


    public Long getId() {
        return id;
    }
    public Platform id(Long id) {
        this.id = id;
        return this;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPlatformName() {
        return platformName;
    }
    public Platform platformName(String platformName) {
        this.platformName = platformName;
        return this;
    }
    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
    public String getPlatformAddress() {
        return platformAddress;
    }
    public Platform platformAddress(String platformAddress) {
        this.platformAddress = platformAddress;
        return this;
    }
    public void setPlatformAddress(String platformAddress) {
        this.platformAddress = platformAddress;
    }
    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }


    @Override
    public String toString() {
        return "Platform{" +
            "id=" + id +
            ", platformName='" + platformName + '\'' +
            ", platformAddress=" + platformAddress +
            '}';
    }
}

