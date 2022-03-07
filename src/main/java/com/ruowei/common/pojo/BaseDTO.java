package com.ruowei.common.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ruowei.common.json.LongJsonDeserializer;
import com.ruowei.common.json.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.time.Instant;

/**
 * 数据传输基类
 * @author 刘东奇
 * @date 2019/9/2
 */
public class BaseDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "主键", example = "0")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    protected Long id;

    /**
     * 数据传输对象构建的时间
     */
    private Instant buildTime= Instant.now();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Instant buildTime) {
        this.buildTime = buildTime;
    }
}
