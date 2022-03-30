package com.ruowei.web.rest.enumeration;

public enum PushApi {
    //推送API

    //用户相关
    ADDANDALTER_ENTERPRISEUSER("集团接收水厂新增编辑用户数据", "/api/push/enterprise_user"),
    DELETE_ENTERPRISEUSER("集团接收水厂删除用户数据", "/api/push/delete_enterprise_user"),
    PLATE_DELETE_ENTERPRISEUSER("平台接收水厂删除用户数据", "/api/push/plate/delete_enterprise_user"),
    PLATE_DELETE_GROUPUSER("平台接收集团删除用户数据", "/api/push/plate/delete_group_user"),
    PLATE_ADDANDALTER_GROUPUSER("平台接收集团新增编辑用户数据", "/api/push/plate/group_user"),
    PLATE_ADDANDALTER_ENTERPRISEUSER("平台接收水厂新增编辑用户数据", "/api/push/plate/enterprise_user"),

    //设定数据
    ADDANDALTER_SEWEMITHRESHOLD("集团接收试点水厂新增编辑设定数据", "/api/push/sewEmithreshold"),
    PLATE_ADDANDALTER_SEWEMITHRESHOLD("平台接收试点水厂(或集团非试点)新增编辑设定数据", "/api/push/plate/sewEmithreshold"),

    //校表数据
    UPDATE_SEWMETER("集团接收水厂更新校表数据", "/api/push/alter_sewMeter"),
    PLATE_UPDATE_SEWMETER("平台接收水厂（或集团）更新校表数据", "/api/push/plate/alter_sewMeter"),

    //仪表，化验，日报数据
    ADD_COMPREHENSIVE("集团接收水厂推送仪表，化验，日报，校表数据", "/api/push/comprehensive"),
    PLATE_ADD_COMPREHENSIVE("平台接收水厂（或集团非试点水厂）推送新增仪表，化验，日报，校表数据", "/api/push/plate/comprehensive"),
    UPDATE_SEWPOT("集团接收水厂更新日报数据", "/api/push/alter_sewPot"),
    PLATE_UPDATE_SEWPOT("平台接收水厂(或集团)更新日报数据", "/api/push/plate/alter_sewPot"),
    UPDATE_SEWSLU("集团接收水厂更新化验数据", "/api/push/alter_sewSlu"),
    PLATE_UPDATE_SEWSLU("平台接收水厂(或集团)更新化验数据", "/api/push/plate/alter_sewSlu"),
    UPDATE_SEWPROCESS("集团接收水厂更新仪表数据", "/api/push/alter_sewProcess"),
    PLATE_UPDATE_SEWPROCESS("平台接收水厂（或集团）更新仪表数据", "/api/push/plate/alter_sewProcess"),

    //决策预测相关
    ADDANDALTER_EMIDATA("集团接收试点水厂决策预测计算结果数据", "/api/push/decision_resultData"),
    PLATE_ADDANDALTER_GROUPEMIDATA("平台接收集团（非试点）手动决策预测计算结果数据", "/api/push/plate/group_decision_resultData"),
    PLATE_ADDANDALTER_EMIDATA("平台接收试点水厂决策预测计算结果数据", "/api/push/plate/decision_resultData"),

    //数据源关联数据相关
    ADDANDALTER_ASSOCIATE("集团接收试点水厂新增编辑数据源关联数据", "/api/push/associate"),
    ADDANDALTER_DROUPASSOCIATE("平台接收集团（非试点水厂）新增编辑数据源关联数据", "/api/push/group_associate"),
    PLATE_ADDANDALTER_ASSOCIATE("平台接收试点水厂新增编辑数据源关联数据", "/api/push/plate/associate");


    private String name;
    private String url;

    PushApi(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
