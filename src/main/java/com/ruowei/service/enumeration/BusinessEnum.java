package com.ruowei.service.enumeration;

@Deprecated
public enum BusinessEnum {
    EMIDATA("碳排放数据", "emidata");

    private String name;
    private String code;

    BusinessEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
