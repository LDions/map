package com.ruowei.service.enumeration;

@Deprecated
public enum FunctionEnum {
    UPSERT("upsert"),
    QUERYBYKEY("queryByKey"),
    QUERYBYSTRING("queryByString"),
    QUERYBYSTRINGWITHPAGINATION("queryByStringWithPagination");

    private String name;

    FunctionEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
