package com.ruowei.common.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ruowei.common.json.LongJsonDeserializer;
import com.ruowei.common.json.LongJsonSerializer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/*
 * 基础实体
 * @author 刘东奇
 * @date 2019/9/5
 */
@MappedSuperclass
public abstract class PrimaryKeyAutoIncrementEntity implements Serializable {

    @Id
    @GenericGenerator(name="idGenerator", strategy = "com.ruowei.common.entity.idgen.IdGenerator")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGenerator")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
