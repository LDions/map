package com.ruowei.security.jwt;

import com.ruowei.common.error.exception.NoApiAccessException;
import com.ruowei.modules.sys.repository.SysRoleApiRepository;
import com.ruowei.modules.sys.repository.SysRoleRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Filters incoming requests and installs a Spring Security principal if a header corresponding to a valid user is
 * found.
 */
public class JWTFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final SysRoleRepository sysRoleRepository;
    private final SysRoleApiRepository sysRoleApiRepository;
    private TokenProvider tokenProvider;

    public JWTFilter(TokenProvider tokenProvider, SysRoleRepository sysRoleRepository, SysRoleApiRepository sysRoleApiRepository) {
        this.tokenProvider = tokenProvider;
        this.sysRoleRepository = sysRoleRepository;
        this.sysRoleApiRepository = sysRoleApiRepository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest);
        if (StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) {
            Authentication authentication = this.tokenProvider.getAuthentication(jwt);
            // 判断api权限
//            Set<Long> roleIds = sysRoleRepository.getRoleIdsByRoleCodes(
//                authentication.getAuthorities()
//                    .stream().map(GrantedAuthority::getAuthority)
//                    .collect(Collectors.toSet())
//            );
//            Set<String> roleApis = sysRoleApiRepository.getAllApiByRoleIds(roleIds);
//            String api = httpServletRequest.getMethod() + " " + httpServletRequest.getRequestURI();
//            if (!roleApis.contains(api)) {
//                throw new NoApiAccessException();
//            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
