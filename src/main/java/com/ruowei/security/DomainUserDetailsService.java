package com.ruowei.security;

import com.ruowei.domain.Enterprise;
import com.ruowei.domain.User;
import com.ruowei.domain.enumeration.EnterpriseStatusType;
import com.ruowei.domain.enumeration.UserStatusType;
import com.ruowei.repository.EnterpriseRepository;
import com.ruowei.repository.UserRepository;
import com.ruowei.repository.UserRoleRepository;
import com.ruowei.web.rest.errors.BadRequestProblem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final EnterpriseRepository enterpriseRepository;

    public DomainUserDetailsService(UserRepository userRepository, UserRoleRepository userRoleRepository, EnterpriseRepository enterpriseRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        return userRepository
            .findOneByLogin(login)
            .map(this::createSpringSecurityUser)
            .orElseThrow(() -> new InternalAuthenticationServiceException("用户名或密码错误！"));
    }

    private UserModel createSpringSecurityUser(User user) {
        List<GrantedAuthority> authorities =
            userRoleRepository.getAllRoleCodeByUserId(user.getId())
                .stream()
                .map(SimpleGrantedAuthority::new)
                .distinct()
                .collect(Collectors.toList());
        if (user.getStatus() != UserStatusType.NORMAL) {
            throw new LockedException("该用户已停用，请联系系统管理员");
        }

        return new UserModel(user.getLogin(), user.getPassword(), authorities, user.getId(), user.getNickName(), null, null);
    }
}
