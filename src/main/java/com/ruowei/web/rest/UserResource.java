package com.ruowei.web.rest;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.config.ApplicationProperties;
import com.ruowei.config.Constants;
import com.ruowei.domain.*;

import com.ruowei.domain.enumeration.RoleStatusType;
import com.ruowei.domain.enumeration.RoleType;
import com.ruowei.domain.enumeration.SendStatusType;
import com.ruowei.repository.EnterpriseRepository;
import com.ruowei.repository.UserRepository;
import com.ruowei.repository.UserRoleRepository;
import com.ruowei.security.UserModel;
import com.ruowei.service.PushService;
import com.ruowei.service.mapper.UserVMMapper;
import com.ruowei.util.IDUtils;
import com.ruowei.util.ObjectUtils;
import com.ruowei.util.OptionalBooleanBuilder;
import com.ruowei.web.rest.enumeration.PushApi;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.EnterpriseUserVM;
import com.ruowei.web.rest.vm.UserQM;
import com.ruowei.web.rest.vm.UserVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liquibase.pro.packaged.E;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ruowei.config.Constants.DEFAULT_PASSWORD;

@RestController
@RequestMapping("/api")
@Api(tags = "用户管理")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);
    private final ApplicationProperties applicationProperties;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JPAQueryFactory jpaQueryFactory;
    private final UserRoleRepository userRoleRepository;
    private final UserVMMapper userVMMapper;
    private final PushService pushService;
    private final EnterpriseRepository enterpriseRepository;
    private QUser qUser = QUser.user;
    private QRole qRole = QRole.role;
    private QUserRole qUserRole = QUserRole.userRole;

    public UserResource(ApplicationProperties applicationProperties, UserRepository userRepository, PasswordEncoder passwordEncoder, JPAQueryFactory jpaQueryFactory, UserRoleRepository userRoleRepository, UserVMMapper userVMMapper, PushService pushService, EnterpriseRepository enterpriseRepository) {
        this.applicationProperties = applicationProperties;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jpaQueryFactory = jpaQueryFactory;
        this.userRoleRepository = userRoleRepository;
        this.userVMMapper = userVMMapper;
        this.pushService = pushService;
        this.enterpriseRepository = enterpriseRepository;
    }

    @PostMapping("/user")
    @Transactional
    @ApiOperation(value = "新增用户接口", notes = "作者：孙小楠")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserVM vm) throws URISyntaxException {
        log.debug("REST request to save SysUser : {}", vm);
        if (vm.getId() != null) {
            throw new BadRequestProblem("新增失败", "新增时ID必须为空");
        }
        //在未删除的用户中判断是否用户名重复
        userRepository.findOneByLoginAndDeletedIsFalse(vm.getLogin())
            .ifPresent(so -> {
                throw new BadRequestProblem("新增失败", "用户名已存在");
            });
        User user = userVMMapper.toEntity(vm);
        user.setEnterpriseCode(vm.getEnterpriseCode());
        user.setGroupCode(vm.getGroupCode());
        user.setUserCode(IDUtils.codeGenerator());
        user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        user.setDeleted(false);
        user.setStatus(SendStatusType.FAILED);
        user.setPlateStatus(SendStatusType.FAILED);
        user.setDeleted(false);
        User result = userRepository.save(user);
        vm.getRoleIds().forEach(roleId ->
            userRoleRepository.save(new UserRole().userId(result.getId()).roleId(Long.valueOf(roleId)))
        );
        //推送数据
        EnterpriseUserVM userVM = new EnterpriseUserVM();
        ObjectUtils.copyPropertiesIgnoreNull(result, userVM);
        userVM.setOperate(0);
        //只传水厂编码是水厂用户新增，只传集团编码是集团用户新增，两者都不传是平台用户新增
        if (StringUtils.isNotEmpty(vm.getEnterpriseCode())) {
            //水厂新增用户给集团和平台推送一下数据
            try {
                String groupResult = pushService.postForData(applicationProperties.getHost(), PushApi.ADDANDALTER_ENTERPRISEUSER.getUrl(), userVM);
                if (groupResult.equals(Constants.PUSH_RESULT)) {
                    result.setStatus(SendStatusType.SUCCESS);
                }
            } catch (Exception e) {
                result.setStatus(SendStatusType.FAILED);
            }
            //给平台推送水厂用户数据需要提供集团编码以便确定是哪个集团下的水厂新增用户
            userVM.setGroupCode(enterpriseRepository.findByCode(vm.getEnterpriseCode()).map(Enterprise::getGroupCode).orElse(""));
            try {
                String plateResult = pushService.postForData(applicationProperties.getPlateHost(), PushApi.PLATE_ADDANDALTER_ENTERPRISEUSER.getUrl(), userVM);
                if (plateResult.equals(Constants.PUSH_RESULT)) {
                    result.setPlateStatus(SendStatusType.SUCCESS);
                }
            } catch (Exception e) {
                result.setPlateStatus(SendStatusType.FAILED);
            }
        } else if (StringUtils.isNotEmpty(vm.getGroupCode()) && StringUtils.isEmpty(vm.getEnterpriseCode())) {
            //集团新增用户给平台推送一下数据
            try {
                String groupPlateResult = pushService.postForData(applicationProperties.getPlateHost(), PushApi.PLATE_ADDANDALTER_GROUPUSER.getUrl(), userVM);
                if (groupPlateResult.equals(Constants.PUSH_RESULT)) {
                    result.setPlateStatus(SendStatusType.SUCCESS);
                }
            } catch (Exception e) {
                result.setPlateStatus(SendStatusType.FAILED);
            }
        }
        //更改推送状态重新保存一下
        userRepository.save(result);
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
        userRepository.findFirstByLoginAndIdNotAndDeletedIsFalse(vm.getLogin(), vm.getId())
            .ifPresent(so -> {
                throw new BadRequestProblem("编辑失败", "用户已存在");
            });
        User user = userRepository.findByIdAndDeletedIsFalse(vm.getId())
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
        //编辑用户信息把推送状态重置为false
        user.setStatus(SendStatusType.FAILED);
        user.setPlateStatus(SendStatusType.FAILED);
        User result = userRepository.save(user);
        if (vm.getRoleIds() != null) {
            userRoleRepository.deleteAllByUserId(vm.getId());
            vm.getRoleIds().forEach(roleId ->
                userRoleRepository.save(new UserRole().userId(vm.getId()).roleId(Long.valueOf(roleId)))
            );
        }
        //推送数据
        EnterpriseUserVM userVM = new EnterpriseUserVM();
        ObjectUtils.copyPropertiesIgnoreNull(result, userVM);
        userVM.setOperate(1);
        //只传水厂编码是水厂用户新增，只传集团编码是集团用户新增，两者都不传是平台用户新增
        if (StringUtils.isNotEmpty(result.getEnterpriseCode())) {
            //水厂新增用户给集团和平台推送一下数据
            try {
                String groupResult = pushService.postForData(applicationProperties.getHost(), PushApi.ADDANDALTER_ENTERPRISEUSER.getUrl(), userVM);
                if (groupResult.equals(Constants.PUSH_RESULT)) {
                    result.setStatus(SendStatusType.SUCCESS);
                }
            } catch (Exception e) {
                result.setStatus(SendStatusType.FAILED);
            }
            //给平台推送水厂用户数据需要提供集团编码以便确定是哪个集团下的水厂新增用户
            userVM.setGroupCode(enterpriseRepository.findByCode(vm.getEnterpriseCode()).map(Enterprise::getGroupCode).orElse(""));
            try {
                String plateResult = pushService.postForData(applicationProperties.getPlateHost(), PushApi.PLATE_ADDANDALTER_ENTERPRISEUSER.getUrl(), userVM);
                if (plateResult.equals(Constants.PUSH_RESULT)) {
                    result.setPlateStatus(SendStatusType.SUCCESS);
                }
            } catch (Exception e) {
                result.setPlateStatus(SendStatusType.FAILED);
            }
        } else if (result.getGroupCode() != null && result.getEnterpriseCode() == null) {
            //集团新增用户给平台推送一下数据
            try {
                String groupPlateResult = pushService.postForData(applicationProperties.getPlateHost(), PushApi.PLATE_ADDANDALTER_GROUPUSER.getUrl(), userVM);
                if (groupPlateResult.equals(Constants.PUSH_RESULT)) {
                    result.setPlateStatus(SendStatusType.SUCCESS);
                }
            } catch (Exception e) {
                result.setPlateStatus(SendStatusType.FAILED);
            }
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
            .notEmptyAnd(qUser.enterpriseCode::contains, qm.getEnterpriseCode())
            .notEmptyAnd(qUser.groupCode::contains, qm.getGroupCode())
            .notEmptyAnd(qUser.nickName::contains, qm.getNickname());

        List<User> list = jpaQueryFactory
            .select(Projections.bean(User.class, qUser.id, qUser.login, qUser.nickName, qUser.remark, qUser.status, qUser.enterpriseCode, qUser.groupCode))
            .from(qUser)
            .where(predicate.build().and(qUser.deleted.eq(false)))
            .orderBy(qUser.id.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
        long count = jpaQueryFactory
            .select(qUser)
            .from(qUser)
            .where(predicate.build().and(qUser.deleted.eq(false)))
            .fetch()
            .size();
        Page<User> page = new PageImpl<>(list, pageable, count);
        List<User> result = page.getContent();
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(result);
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询用户详情接口", notes = "作者：孙小楠")
    public ResponseEntity<UserVM> getUser(@PathVariable Long id, @ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        log.debug("REST request to get SysUser : {}", id);
        Optional<UserVM> optional = userRepository.findByIdAndDeletedIsFalse(id)
            .map(sysUser -> {
                List<String> roleIds = userRoleRepository.findAllByUserId(id)
                    .stream().map(userRole -> String.valueOf(userRole.getRoleId()))
                    .collect(Collectors.toList());
                UserVM vm = userVMMapper.toDto(sysUser);
                vm.setRoleIds(roleIds);
                return vm;
            });
        log.info(userModel.getcode() + userModel.getgroupCode());
        return ResponseUtil.wrapOrNotFound(optional);
    }

    @DeleteMapping("/user/{id}")
    @Transactional
    @ApiOperation(value = "删除用户接口", notes = "作者：孙小楠")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("REST request to delete SysUser : {}", id);
        Optional<User> userOpt = userRepository.findByIdAndDeletedIsFalse(id);
        if (!userOpt.isPresent()) {
            throw new BadRequestAlertException("删除的用户不存在", "", "删除失败");
        }
        userRoleRepository.deleteAllByUserId(id);
        //软删除，将用户删除状态置为true
        User user = userOpt.get();
        user.setDeleted(true);
        user.setStatus(SendStatusType.FAILED);
        user.setPlateStatus(SendStatusType.FAILED);
        User result = userRepository.save(user);
        //推送数据
        LinkedMultiValueMap<String, String> urlParams = new LinkedMultiValueMap<>();
        urlParams.add("userCode", result.getUserCode());
        if (StringUtils.isNotEmpty(result.getEnterpriseCode())) {
            try {
                urlParams.add("enterpriseCode", result.getEnterpriseCode());
                String groupResult = pushService.postForData(applicationProperties.getHost(), PushApi.DELETE_ENTERPRISEUSER.getUrl(), urlParams);
                if (groupResult.equals(Constants.PUSH_RESULT)) {
                    result.setStatus(SendStatusType.SUCCESS);
                }
            } catch (Exception e) {
                result.setStatus(SendStatusType.FAILED);
            }
            try {
                urlParams.add("groupCode", result.getGroupCode());
                String plateResult = pushService.postForData(applicationProperties.getPlateHost(), PushApi.PLATE_DELETE_ENTERPRISEUSER.getUrl(), urlParams);
                if (plateResult.equals(Constants.PUSH_RESULT)) {
                    result.setPlateStatus(SendStatusType.SUCCESS);
                }
            } catch (Exception e) {
                result.setPlateStatus(SendStatusType.FAILED);
            }
        } else if (StringUtils.isNotEmpty(result.getGroupCode()) && result.getEnterpriseCode().isEmpty()) {
            //删除集团用户推给平台
            try {
                urlParams.add("groupCode", result.getGroupCode());
                String plateGroupResult = pushService.postForData(applicationProperties.getPlateHost(), PushApi.PLATE_DELETE_GROUPUSER.getUrl(), urlParams);
                if (plateGroupResult.equals(Constants.PUSH_RESULT)) {
                    result.setPlateStatus(SendStatusType.SUCCESS);
                }
            } catch (Exception e) {
                result.setPlateStatus(SendStatusType.FAILED);
            }
        }
        userRepository.save(result);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user/reset/{id}")
    @ApiOperation(value = "重置用户密码接口", notes = "作者：张锴")
    public ResponseEntity<String> resetUserPassword(
        @PathVariable Long id
    ) {
        User user = userRepository.findByIdAndDeletedIsFalse(id).orElseThrow(() -> new BadRequestProblem("重置失败", "未找到该用户"));
        user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        userRepository.save(user);
        return ResponseEntity.ok().body("重置成功");
    }
}
