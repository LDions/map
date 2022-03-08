package com.ruowei.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserModel extends User {

    // 用户ID
    private Long userId;
    // 用户姓名
    private String nickName;
    // 企业ID
    private Long enterpriseId;
    // 企业名称
    private String enterpriseName;

    public UserModel(String username, String password, Collection<? extends GrantedAuthority> authorities, Long userId, String nickName, Long enterpriseId, String enterpriseName) {
        super(username, password, authorities);
        this.userId = userId;
        this.nickName = nickName;
        this.enterpriseId = enterpriseId;
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

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}

