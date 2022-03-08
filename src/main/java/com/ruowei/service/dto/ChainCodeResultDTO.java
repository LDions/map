package com.ruowei.service.dto;

import com.fasterxml.jackson.annotation.JsonSetter;

@Deprecated
public class ChainCodeResultDTO {

    private String key;

    private EmiDataDTO record;

    public String getKey() {
        return key;
    }

    @JsonSetter("Key")
    public void setKey(String key) {
        this.key = key;
    }

    public EmiDataDTO getRecord() {
        return record;
    }

    @JsonSetter("Record")
    public void setRecord(EmiDataDTO record) {
        this.record = record;
    }
}
