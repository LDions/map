package com.ruowei.security;

import com.ruowei.modules.sys.domain.table.SysUserTable;
import com.ruowei.modules.sys.repository.SysUserRoleRepository;
import com.ruowei.modules.sys.repository.table.SysUserTableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);
    private final SysUserTableRepository sysUserTableRepository;
    private final SysUserRoleRepository sysUserRoleRepository;

    public DomainUserDetailsService(SysUserTableRepository sysUserTableRepository, SysUserRoleRepository sysUserRoleRepository) {
        this.sysUserTableRepository = sysUserTableRepository;
        this.sysUserRoleRepository = sysUserRoleRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        return sysUserTableRepository.findOneByLoginCode(login)
            .map(this::createSpringSecurityUser)
            .orElseThrow(() -> new UsernameNotFoundException("User " + login + " was not found in the database"));
    }

    private UserModel createSpringSecurityUser(SysUserTable sysUser) {
        List<GrantedAuthority> grantedAuthorities =
            sysUserRoleRepository.getAllRoleCodeByUserId(sysUser.getId())
                .stream().map(SimpleGrantedAuthority::new)
                .distinct().collect(Collectors.toList());
        return new UserModel(sysUser.getLoginCode(), sysUser.getPassword(),
            grantedAuthorities, sysUser.getId(), sysUser.getRefCode());
    }
}
