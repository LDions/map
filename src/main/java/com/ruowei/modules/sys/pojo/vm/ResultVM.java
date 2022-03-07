package com.ruowei.modules.sys.pojo.vm;

import io.swagger.annotations.ApiModelProperty;

public class ResultVM {

    @ApiModelProperty(value = "返回提示信息")
    private String title;

    @ApiModelProperty(value = "返回数据")
    private Object obj;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "ResultVM{" +
            "title='" + title + '\'' +
            ", obj=" + obj +
            '}';
    }
}
