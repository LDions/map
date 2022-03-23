package com.ruowei.web.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruowei.config.ApplicationProperties;
import com.ruowei.domain.*;
import com.ruowei.domain.enumeration.SysType;
import com.ruowei.repository.EnterpriseRepository;
import com.ruowei.repository.RoleRepository;
import com.ruowei.repository.UserRepository;
import com.ruowei.repository.UserRoleRepository;
import com.ruowei.security.jwt.JWTFilter;
import com.ruowei.security.jwt.TokenProvider;
import com.ruowei.service.MenuService;
import com.ruowei.service.dto.SimpleMenuTreeDTO;
import com.ruowei.web.rest.dto.VerifyTokenDTO;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.LoginVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
@Api(tags = "系统管理")
public class UserJWTController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final ApplicationProperties applicationProperties;
    private final UserRepository userRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final MenuService menuService;

    public UserJWTController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, ApplicationProperties applicationProperties, UserRepository userRepository, EnterpriseRepository enterpriseRepository, UserRoleRepository userRoleRepository, RoleRepository roleRepository, MenuService menuService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.applicationProperties = applicationProperties;
        this.userRepository = userRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.menuService = menuService;
    }

    @PostMapping("/authenticate")
    @ApiOperation(value = "登录")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM) {
        User user = userRepository.findOneByLogin(loginVM.getUsername())
            .orElseThrow(() -> new BadRequestProblem("登录失败", "账号不存在，请重新输入"));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            loginVM.getUsername(),
            loginVM.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, loginVM.isRememberMe());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        JWTToken jwtToken = new JWTToken(jwt);
        jwtToken.setCode(user.getEnterpriseCode());
        jwtToken.setGroupCode(user.getGroupCode());

        List<Long> roleIds = userRoleRepository.findAllByUserId(user.getId()).stream().map(UserRole::getRoleId).collect(Collectors.toList());
        List<String> roleCodes = roleRepository.findAllByIdIn(roleIds).stream().map(Role::getCode).collect(Collectors.toList());
        jwtToken.setRoleCodes(roleCodes);
        List<String> permissions = menuService.getCurrentUserPermissions(SysType.WEB, user.getId())
            .stream().map(Menu::getMenuHref).collect(Collectors.toList());
        jwtToken.setPermissions(permissions);
        List<SimpleMenuTreeDTO> menus = menuService.getCurrentUserSimpleMenus(SysType.WEB, user.getId());
        jwtToken.setMenus(menus);
        return new ResponseEntity<>(jwtToken, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/verifyToken")
    @ApiOperation(value = "SSO验证Token")
    public ResponseEntity<VerifyTokenDTO> verifyToken() {
        return ResponseEntity.ok(new VerifyTokenDTO(applicationProperties.getBlockChain().getAppId()));
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        private String code;

        private String enterpriseName;

        private String groupCode;

        private List<String> roleCodes;

        private List<String> permissions;

        private List<SimpleMenuTreeDTO> menus;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("enterprise_code")
        String getCode() {
            return code;
        }

        void setCode(String code) {
            this.code = code;
        }

        @JsonProperty("group_id")
        String getGroupCode() {
            return groupCode;
        }

        void setGroupCode(String groupCode) {
            this.groupCode = groupCode;
        }

        @JsonProperty("enterprise_name")
        String getEnterpriseName() {
            return enterpriseName;
        }

        void setEnterpriseName(String enterpriseName) {
            this.enterpriseName = enterpriseName;
        }

        @JsonProperty("role_codes")
        List<String> getRoleCodes() {
            return roleCodes;
        }

        void setRoleCodes(List<String> roleCodes) {
            this.roleCodes = roleCodes;
        }

        @JsonProperty("permissions")
        List<String> getPermissions() {
            return permissions;
        }

        void setPermissions(List<String> permissions) {
            this.permissions = permissions;
        }

        @JsonProperty("menus")
        List<SimpleMenuTreeDTO> getMenus() {
            return menus;
        }

        void setMenus(List<SimpleMenuTreeDTO> menus) {
            this.menus = menus;
        }
    }
}
