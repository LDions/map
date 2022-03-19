package com.ruowei.web.rest;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.config.Constants;
import com.ruowei.domain.*;
import com.ruowei.repository.RoleMenuRepository;
import com.ruowei.repository.RoleRepository;
import com.ruowei.repository.UserRepository;
import com.ruowei.repository.UserRoleRepository;
import com.ruowei.service.mapper.RoleVMMapper;
import com.ruowei.util.OptionalBooleanBuilder;
import com.ruowei.web.rest.dto.DropDownDTO;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.RoleQM;
import com.ruowei.web.rest.vm.RoleVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(tags = "角色管理")
public class RoleResource {

    private final Logger log = LoggerFactory.getLogger(RoleResource.class);
    private final JPAQueryFactory jpaQueryFactory;
    private final RoleRepository roleRepository;
    private final RoleMenuRepository roleMenuRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleVMMapper roleVMMapper;
    private QRole qRole = QRole.role;

    public RoleResource(JPAQueryFactory jpaQueryFactory, RoleRepository roleRepository, RoleMenuRepository roleMenuRepository, UserRoleRepository userRoleRepository, UserRepository userRepository, RoleVMMapper roleVMMapper) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.roleRepository = roleRepository;
        this.roleMenuRepository = roleMenuRepository;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.roleVMMapper = roleVMMapper;
    }

    @PostMapping("/role")
    @Transactional
    @ApiOperation(value = "新增角色接口", notes = "作者：孙小楠")
    public ResponseEntity<Role> createRole(@Valid @RequestBody RoleVM vm) throws URISyntaxException {
        log.debug("REST request to save SysRole : {}", vm);
        if (vm.getId() != null) {
            throw new BadRequestProblem("新增失败", "新增时ID必须为空");
        }
        roleRepository.getFirstByName(vm.getName())
            .ifPresent(so -> {
                throw new BadRequestProblem("新增失败", "角色名称已存在");
            });
        roleRepository.getFirstByCode(vm.getCode())
            .ifPresent(so -> {
                throw new BadRequestProblem("新增失败", "角色编码已存在");
            });
        Role result = roleRepository.save(roleVMMapper.toEntity(vm));
        vm.getMenuIds().forEach(menuId ->
            roleMenuRepository.save(new RoleMenu().roleId(result.getId()).menuId(Long.valueOf(menuId)))
        );
        return ResponseEntity.created(new URI("/api/role/" + result.getId()))
            .body(result);
    }

    @PutMapping("/role")
    @ApiOperation(value = "编辑角色接口", notes = "作者：孙小楠")
    public ResponseEntity<Role> editRole(@Valid @RequestBody Role role) {
        log.debug("REST request to update SysRole : {}", role);
        if (role.getId() == null) {
            throw new BadRequestProblem("编辑失败", "id不能为空");
        }
        roleRepository.getFirstByNameAndIdNot(role.getName(), role.getId())
            .ifPresent(so -> {
                throw new BadRequestProblem("编辑失败", "角色名称已存在");
            });
        roleRepository.getFirstByCodeAndIdNot(role.getCode(), role.getId())
            .ifPresent(so -> {
                throw new BadRequestProblem("编辑失败", "角色代码已存在");
            });
        Role result = roleRepository.save(role);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/roles")
    @ApiOperation(value = "获取带分页的角色列表接口", notes = "作者：孙小楠")
    public ResponseEntity<List<Role>> getAllRoles(RoleQM qm, Pageable pageable) {
        log.debug("REST request to get a page of SysRoles : {}", qm);
        OptionalBooleanBuilder predicate = new OptionalBooleanBuilder()
            .notEmptyAnd(qRole.code::ne, Constants.SYS_ADMIN)
            .notEmptyAnd(qRole.name::contains, qm.getName())
            .notEmptyAnd(qRole.code::contains, qm.getCode())
            .notEmptyAnd(qRole.isSys::eq, qm.getIsSys());
        Page<Role> page = roleRepository.findAll(predicate.build(), pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/role/{id}")
    @ApiOperation(value = "查询角色详情接口", notes = "作者：孙小楠")
    public ResponseEntity<RoleVM> getRole(@PathVariable Long id) {
        log.debug("REST request to get SysRole : {}", id);
        Optional<RoleVM> optional = roleRepository.findById(id)
            .map(sysRole -> {
                List<String> menuIds = roleMenuRepository.findAllByRoleIdIn(Collections.singletonList(id))
                    .stream().map(roleMenu -> String.valueOf(roleMenu.getMenuId()))
                    .collect(Collectors.toList());
                RoleVM vm = roleVMMapper.toDto(sysRole);
                vm.setMenuIds(menuIds);
                return vm;
            });
        return ResponseUtil.wrapOrNotFound(optional);
    }

    @DeleteMapping("/role/{id}")
    @Transactional
    @ApiOperation(value = "删除角色接口", notes = "作者：孙小楠")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        log.debug("REST request to delete SysRole : {}", id);
        List<Long> userIds = userRoleRepository.findAllByRoleId(id).stream().map(UserRole::getUserId).collect(Collectors.toList());
        List<User> usersInRole = userRepository.findAllByIdIn(userIds);
        if (!usersInRole.isEmpty()) {
            throw new BadRequestProblem("删除失败", "存在使用此角色的用户");
        }
        // 先删除用户角色表数据
        userRoleRepository.deleteAllByRoleId(id);
        jpaQueryFactory
            .update(qRole)
            .where(qRole.id.eq(id))
            .execute();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/role/using/{id}")
    @Transactional
    @ApiOperation(value = "停用/启用角色接口", notes = "作者：孙小楠")
    public ResponseEntity<Void> changeUsingOfRole(@PathVariable Long id,
                                                  @ApiParam(value = "TRUE为启用，FALSE为停用", required = true) @RequestParam Boolean using) {
        List<Long> userIds = userRoleRepository.findAllByRoleId(id).stream().map(UserRole::getUserId).collect(Collectors.toList());
        List<User> usersInRole = userRepository.findAllByIdIn(userIds);
        if (!usersInRole.isEmpty()) {
            throw new BadRequestProblem("操作失败", "存在使用此角色的用户");
        }
        if (using) {
            jpaQueryFactory
                .update(qRole)
                .where(qRole.id.eq(id))
                .execute();
        }else {
            jpaQueryFactory
                .update(qRole)
                .where(qRole.id.eq(id))
                .execute();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/role/drop-down")
    @Transactional
    @ApiOperation(value = "获取角色下拉列表接口", notes = "作者：孙小楠")
    public ResponseEntity<List<DropDownDTO>> getDropDownOfRole(@ApiParam(value = "TRUE为带有污水处理厂，FALSE为不带有污水处理厂", required = true) @RequestParam Boolean haveSewTreat) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qRole.code.ne(Constants.SYS_ADMIN));
        if (!haveSewTreat) {
            booleanBuilder.and(qRole.code.ne(Constants.SEW_TREAT));
        }
        List<DropDownDTO> result = jpaQueryFactory
            .selectFrom(qRole)
            .where(booleanBuilder)
            .stream()
            .map(role -> {
                DropDownDTO dto = new DropDownDTO();
                dto.setId(role.getId());
                dto.setCode(role.getCode());
                dto.setName(role.getName());
                return dto;
            })
            .collect(Collectors.toList());
        return ResponseEntity.ok().body(result);
    }
}
