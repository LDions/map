package com.ruowei.modules.sys.pojo.vm;

import com.ruowei.modules.sys.domain.enumeration.PostStatusType;
import com.ruowei.modules.sys.domain.enumeration.PostType;
import io.swagger.annotations.ApiModelProperty;

public class SysPostQM {

    @ApiModelProperty(value = "岗位编码")
    private String postCode;

    @ApiModelProperty(value = "岗位名称")
    private String postName;

    @ApiModelProperty(value = "岗位分类")
    private PostType postType;

    @ApiModelProperty(value = "岗位状态")
    private PostStatusType status;

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public PostStatusType getStatus() {
        return status;
    }

    public void setStatus(PostStatusType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysPostQM{" +
            "postCode='" + postCode + '\'' +
            ", postName='" + postName + '\'' +
            ", postType=" + postType +
            ", status=" + status +
            '}';
    }
}
