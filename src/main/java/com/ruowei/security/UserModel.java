package com.ruowei.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserModel extends User {

    private Long id;
    private Long employeeId;

    public UserModel(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id, Long employeeId) {
        super(username, password, authorities);
        this.id = id;
        this.employeeId = employeeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
