package com.ruowei.web.rest;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.config.ApplicationProperties;
import com.ruowei.config.Constants;
import com.ruowei.domain.*;

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
import org.springframework.util.LinkedMultiValueMap;
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
        userRepository.findOneByLogin(vm.getLogin())
            .ifPresent(so -> {
                throw new BadRequestProblem("新增失败", "用户名已存在");
            });
        User user = userVMMapper.toEntity(vm);
        user.setEnterpriseCode(vm.getEnterpriseCode());
        user.setGroupCode(vm.getGroupCode());
        user.setUserCode(IDUtils.codeGenerator());
        user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        user.setStatus(SendStatusType.FAILED);
        user.setPlateStatus(SendStatusType.FAILED);
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
        } else if (StringUtils.isNotEmpty(result.getGroupCode()) && result.getEnterpriseCode().isEmpty()) {
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
            .notEmptyAnd(qUser.nickName::contains, qm.getNickname());
        Page<User> page = userRepository.findAll(predicate.build(), pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询用户详情接口", notes = "作者：孙小楠")
    public ResponseEntity<UserVM> getUser(@PathVariable Long id, @ApiIgnore @AuthenticationPrincipal UserModel userModel) {
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
        log.info(userModel.getcode() + userModel.getgroupCode());
        return ResponseUtil.wrapOrNotFound(optional);
    }

    @DeleteMapping("/user/{id}")
    @Transactional
    @ApiOperation(value = "删除用户接口", notes = "作者：孙小楠")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("REST request to delete SysUser : {}", id);
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new BadRequestAlertException("删除的用户不存在", "", "删除失败");
        }
        userRoleRepository.deleteAllByUserId(id);
        jpaQueryFactory
            .delete(qUser)
            .where(qUser.id.eq(id))
            .execute();
        //推送数据
        LinkedMultiValueMap<String, String> urlParams = new LinkedMultiValueMap<>();
        urlParams.add("userCode", user.get().getUserCode());
        if (StringUtils.isNotEmpty(user.get().getEnterpriseCode())) {
            urlParams.add("enterpriseCode", user.get().getEnterpriseCode());
            pushService.postForData(applicationProperties.getHost(), PushApi.DELETE_ENTERPRISEUSER.getUrl(), urlParams);
            urlParams.add("groupCode", user.get().getGroupCode());
            pushService.postForData(applicationProperties.getPlateHost(), PushApi.PLATE_DELETE_ENTERPRISEUSER.getUrl(), urlParams);
        } else if (StringUtils.isNotEmpty(user.get().getGroupCode()) && user.get().getEnterpriseCode().isEmpty()) {
            //删除集团用户推给平台
            urlParams.add("groupCode", user.get().getGroupCode());
            pushService.postForData(applicationProperties.getPlateHost(), PushApi.PLATE_DELETE_GROUPUSER.getUrl(), urlParams);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user/reset/{id}")
    @ApiOperation(value = "重置用户密码接口", notes = "作者：张锴")
    public ResponseEntity<String> resetUserPassword(
        @PathVariable Long id
    ) {
        User user = userRepository.findById(id).orElseThrow(() -> new BadRequestProblem("重置失败", "未找到该用户"));
        user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        userRepository.save(user);
        return ResponseEntity.ok().body("重置成功");
    }
}
