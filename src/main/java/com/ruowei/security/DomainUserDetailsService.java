package com.ruowei.security;

import com.ruowei.domain.Enterprise;
import com.ruowei.domain.Group;
import com.ruowei.domain.User;
import com.ruowei.repository.EnterpriseRepository;
import com.ruowei.repository.GroupRepository;
import com.ruowei.repository.UserRepository;
import com.ruowei.repository.UserRoleRepository;
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
    private final GroupRepository groupRepository;

    public DomainUserDetailsService(UserRepository userRepository, UserRoleRepository userRoleRepository, EnterpriseRepository enterpriseRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        return userRepository
            .findOneByLoginAndDeletedIsFalse(login)
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
        String code = null;
        String groupCode = null;
        if (user.getEnterpriseCode() != null) {
            Enterprise enterprise = enterpriseRepository
                .findByCode(user.getEnterpriseCode())
                .orElseThrow(() -> new LockedException("未找到水厂信息"));
            code = enterprise.getCode();
        }
        if (user.getGroupCode() != null) {
            Group group = groupRepository
                .findByGroupCode(user.getGroupCode())
                .orElseThrow(() -> new LockedException("未找到集团信息"));
            groupCode = group.getGroupCode();
        }
        return new UserModel(user.getLogin(), user.getPassword(), authorities, user.getId(), user.getNickName(), code, groupCode, null);
    }
}
