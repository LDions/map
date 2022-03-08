package com.ruowei.service.enumeration;

@Deprecated
public enum OperationTypeEnum {
    INVOKE("invoke"),
    QUERY("query");

    private String name;

    OperationTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
