package com.ruowei.security.jwt;

import com.ruowei.modules.sys.repository.SysRoleApiRepository;
import com.ruowei.modules.sys.repository.SysRoleRepository;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final SysRoleRepository sysRoleRepository;
    private final SysRoleApiRepository sysRoleApiRepository;
    private TokenProvider tokenProvider;

    public JWTConfigurer(SysRoleRepository sysRoleRepository, SysRoleApiRepository sysRoleApiRepository, TokenProvider tokenProvider) {
        this.sysRoleRepository = sysRoleRepository;
        this.sysRoleApiRepository = sysRoleApiRepository;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JWTFilter customFilter = new JWTFilter(tokenProvider, sysRoleRepository, sysRoleApiRepository);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
