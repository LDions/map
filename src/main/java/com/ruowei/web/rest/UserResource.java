package com.ruowei.web.rest;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.config.Constants;
import com.ruowei.domain.*;

import com.ruowei.repository.UserRepository;
import com.ruowei.repository.UserRoleRepository;
import com.ruowei.security.UserModel;
import com.ruowei.service.mapper.UserVMMapper;
import com.ruowei.util.OptionalBooleanBuilder;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.UserQM;
import com.ruowei.web.rest.vm.UserVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(tags = "用户管理")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JPAQueryFactory jpaQueryFactory;
    private final UserRoleRepository userRoleRepository;
    private final UserVMMapper userVMMapper;
    private QUser qUser = QUser.user;
    public UserResource(UserRepository userRepository, PasswordEncoder passwordEncoder, JPAQueryFactory jpaQueryFactory, UserRoleRepository userRoleRepository, UserVMMapper userVMMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jpaQueryFactory = jpaQueryFactory;
        this.userRoleRepository = userRoleRepository;
        this.userVMMapper = userVMMapper;
    }

    @PostMapping("/user")
    @Transactional
    @ApiOperation(value = "新增用户接口", notes = "作者：孙小楠")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserVM vm) throws URISyntaxException {
        log.debug("REST request to save SysUser : {}", vm);
        if (vm.getId() != null) {
            throw new BadRequestProblem("新增失败", "新增时ID必须为空");
        }
        userRepository.findOneByLogin(vm.getLogin())
            .ifPresent(so -> {
                throw new BadRequestProblem("新增失败", "用户名已存在");
            });
        User user = userVMMapper.toEntity(vm);
        user.setEnterpriseCode(vm.getEnterpriseCode());
        user.setGroupCode(vm.getGroupCode());
        user.setPassword(passwordEncoder.encode(Constants.DEFAULT_PASSWORD));
        User result = userRepository.save(user);
        vm.getRoleIds().forEach(roleId ->
            userRoleRepository.save(new UserRole().userId(result.getId()).roleId(Long.valueOf(roleId)))
        );
        return ResponseEntity.created(new URI("/api/user/" + result.getId()))
            .body(result);
    }

    @PutMapping("/user")
    @Transactional
    @ApiOperation(value = "编辑用户接口", notes = "作者：孙小楠")
    public ResponseEntity<User> editUser(@Valid @RequestBody UserVM vm) {
        log.debug("REST request to update SysUser : {}", vm);
        if (vm.getId() == null) {
            throw new BadRequestProblem("编辑失败", "id不能为空");
        }
        userRepository.findFirstByLoginAndIdNot(vm.getLogin(), vm.getId())
            .ifPresent(so -> {
                throw new BadRequestProblem("编辑失败", "用户已存在");
            });
        User user = userRepository.findById(vm.getId())
            .orElseThrow(() -> new BadRequestProblem("编辑失败", "该用户不存在"));
        if (StringUtils.isNotEmpty(vm.getLogin())) {
            user.setLogin(vm.getLogin());
        }
        if (StringUtils.isNotEmpty(vm.getNickName())) {
            user.setNickName(vm.getNickName());
        }
        if (vm.getRemark() != null) {
            user.setRemark(vm.getRemark());
        }
        User result = userRepository.save(user);
        if (vm.getRoleIds() != null) {
            userRoleRepository.deleteAllByUserId(vm.getId());
            vm.getRoleIds().forEach(roleId ->
                userRoleRepository.save(new UserRole().userId(vm.getId()).roleId(Long.valueOf(roleId)))
            );
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/user")
    @ApiOperation(value = "获取带分页的用户列表接口", notes = "作者：孙小楠")
    public ResponseEntity<List<User>> getAllUsers(UserQM qm, Pageable pageable) {
        log.debug("REST request to get a page of SysUsers : {}", qm);
        OptionalBooleanBuilder predicate = new OptionalBooleanBuilder()
            .notEmptyAnd(qUser.login::ne, Constants.ADMIN)
            .notEmptyAnd(qUser.login::contains, qm.getLogin())
            .notEmptyAnd(qUser.nickName::contains, qm.getNickname());
        Page<User> page = userRepository.findAll(predicate.build(), pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询用户详情接口", notes = "作者：孙小楠")
    public ResponseEntity<UserVM> getUser(@PathVariable Long id,@ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        log.debug("REST request to get SysUser : {}", id);
        Optional<UserVM> optional = userRepository.findById(id)
            .map(sysUser -> {
                List<String> roleIds = userRoleRepository.findAllByUserId(id)
                    .stream().map(userRole -> String.valueOf(userRole.getRoleId()))
                    .collect(Collectors.toList());
                UserVM vm = userVMMapper.toDto(sysUser);
                vm.setRoleIds(roleIds);
                return vm;
            });
        log.info(userModel.getcode()+userModel.getgroupCode());
        return ResponseUtil.wrapOrNotFound(optional);
    }

    @DeleteMapping("/user/{id}")
    @Transactional
    @ApiOperation(value = "删除用户接口", notes = "作者：孙小楠")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("REST request to delete SysUser : {}", id);
        //TODO 无法删除进行过核算的用户
//        if (sewEmiRepository.existsByReporterId(id)) {
//            throw new BadRequestProblem("删除失败", "该用户进行过水务碳排放核算");
//        }

        userRoleRepository.deleteAllByUserId(id);
        jpaQueryFactory
            .update(qUser)
            .where(qUser.id.eq(id))
            .execute();
        return ResponseEntity.noContent().build();
    }

}
