package com.ruowei.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;


@ApiModel(description = "多边形信息")
@Entity
@Table(name = "geometries")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Geometries  {
//    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "图形名称")
    @Column(name = "name")
    private String name;

//    @Type(type = "jts_geometry")
    @ApiModelProperty(value = "")
    @Column(name = "shape")
    private String shape;

    @ApiModelProperty(value = "类型")
    @Column(name = "type")
    private String type;

    @ApiModelProperty(value = "")
    @Column(name = "site")
    private String site;

    @ApiModelProperty(value = "locationId")
    @Column(name = "location_id")
    private Long locationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Polygon getShape() {
//        return shape;
//    }
//
//    public void setShape(Polygon shape) {
//        this.shape = shape;
//    }


    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Geometries)) {
            return false;
        }
        return id != null && id.equals(((Geometries) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Geometries{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", shape='" + getShape() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
