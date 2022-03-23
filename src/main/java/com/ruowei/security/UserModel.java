package com.ruowei.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserModel extends User {

    // 用户ID
    private Long userId;
    // 用户姓名
    private String nickName;
    // 水厂ID
    private String code;
    // 集团ID
    private String groupCode;
    // 企业名称
    private String enterpriseName;

    public UserModel(String username, String password, Collection<? extends GrantedAuthority> authorities, Long userId, String nickName, String code, String groupCode, String enterpriseName) {
        super(username, password, authorities);
        this.userId = userId;
        this.nickName = nickName;
        this.code = code;
        this.groupCode = groupCode;
        this.enterpriseName = enterpriseName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getcode() {
        return code;
    }

    public void setcode(String code) {
        this.code = code;
    }

    public String getgroupCode() {
        return groupCode;
    }

    public void setgroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}

