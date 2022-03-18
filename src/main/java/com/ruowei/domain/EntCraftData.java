package com.ruowei.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;

@ApiModel(description = "仪表数据与工艺、水厂关联表")
@Entity
@Table(name = "sys_ent_craft_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EntCraftData {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 集团Id
     */
    @ApiModelProperty(value = "集团Id")
    @Column(name = "group_Id")
    private Long groupId;

    /**
     * 集团名称
     */

    @ApiModelProperty(value = "集团名称")
    @Column(name = "group_name")
    private String groupName;

    /**
     * 水厂Id
     */

    @ApiModelProperty(value = "水厂Id")
    @Column(name = "ent_Id")
    private Long entId;

    /**
     * 水厂名称
     */

    @ApiModelProperty(value = "水厂名称")
    @Column(name = "ent_name")
    private String entName;


    /**
     * 工艺段Id
     */

    @ApiModelProperty(value = "工艺段Id")
    @Column(name = "craft_Id")
    private Long craftId;


    /**
     * 工艺段名称
     */

    @ApiModelProperty(value = "工艺段名称")
    @Column(name = "craft_name")
    private String craftName;


    /**
     * 单据号
     */

    @ApiModelProperty(value = "单据号")
    @Column(name = "document_code")
    private String documentCode;


    /**
     * 数据时间
     */

    @ApiModelProperty(value = "数据时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "day_time")
    private Instant dayTime;

    /**
     * 数据来源
     */

    @ApiModelProperty(value = "数据来源")
    @Column(name = "message_source")
    private String messageSource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }
    public EntCraftData groupName(Long groupId) {
        this.groupId = groupId;
        return this;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }
    public EntCraftData groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getEntId() {
        return entId;
    }
    public EntCraftData entId(Long entId) {
        this.entId = entId;
        return this;
    }
    public void setEntId(Long entId) {
        this.entId = entId;
    }

    public String getEntName() {
        return entName;
    }
    public EntCraftData entName(String entName) {
        this.entName = entName;
        return this;
    }
    public void setEntName(String entName) {
        this.entName = entName;
    }

    public Long getCraftId() {
        return craftId;
    }
    public EntCraftData craftId(Long craftId) {
        this.craftId = craftId;
        return this;
    }
    public void setCraftId(Long craftId) {
        this.craftId = craftId;
    }

    public String getCraftName() {
        return craftName;
    }
    public EntCraftData craftName(String craftName) {
        this.craftName = craftName;
        return this;
    }
    public void setCraftName(String craftName) {
        this.craftName = craftName;
    }

    public String getDocumentCode() {
        return documentCode;
    }
    public EntCraftData documentCode(String documentCode) {
        this.documentCode = documentCode;
        return this;
    }
    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public Instant getDayTime() {
        return dayTime;
    }
    public EntCraftData dayTime(Instant dayTime) {
        this.dayTime = dayTime;
        return this;
    }
    public void setDayTime(Instant dayTime) {
        this.dayTime = dayTime;
    }

    public String getMessageSource() {
        return messageSource;
    }
    public EntCraftData messageSource(String messageSource) {
        this.messageSource = messageSource;
        return this;
    }
    public void setMessageSource(String messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntCraftData)) {
            return false;
        }
        return id != null && id.equals(((EntCraftData) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "EntCraftData{" +
            "id=" + id +
            ", groupId=" + groupId +
            ", groupName='" + groupName + '\'' +
            ", entId=" + entId +
            ", entName='" + entName + '\'' +
            ", craftId=" + craftId +
            ", craftName='" + craftName + '\'' +
            ", documentCode='" + documentCode + '\'' +
            ", dayTime=" + dayTime +
            ", messageSource='" + messageSource + '\'' +
            '}';
    }
}
