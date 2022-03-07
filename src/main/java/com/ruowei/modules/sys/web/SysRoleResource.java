package com.ruowei.modules.sys.web;

import com.ruowei.common.error.exception.BadRequestAlertException;
import com.ruowei.common.querydsl.OptionalBooleanBuilder;
import com.ruowei.modules.sys.domain.SysRoleMenu;
import com.ruowei.modules.sys.domain.SysUserRole;
import com.ruowei.modules.sys.domain.enumeration.RoleStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import com.ruowei.modules.sys.domain.table.QSysRole;
import com.ruowei.modules.sys.domain.table.QSysUserTable;
import com.ruowei.modules.sys.domain.table.SysRole;
import com.ruowei.modules.sys.mapper.SysRoleVMMapper;
import com.ruowei.modules.sys.pojo.vm.SysRoleQM;
import com.ruowei.modules.sys.repository.SysRoleMenuRepository;
import com.ruowei.modules.sys.repository.SysRoleRepository;
import com.ruowei.modules.sys.repository.SysUserRepository;
import com.ruowei.modules.sys.repository.SysUserRoleRepository;
import com.ruowei.modules.sys.web.vm.SysRoleVM;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(tags = "角色信息")
public class SysRoleResource {

    private static final String ENTITY_NAME = "sysRole";
    private final Logger log = LoggerFactory.getLogger(SysRoleResource.class);
    private final SysUserRepository sysUserRepository;
    private final SysRoleRepository sysRoleRepository;
    private final SysUserRoleRepository sysUserRoleRepository;
    private final SysRoleVMMapper sysRoleVMMapper;
    private final SysRoleMenuRepository sysRoleMenuRepository;
    private final QSysRole SYS_ROLE = QSysRole.sysRole;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public SysRoleResource(SysUserRepository sysUserRepository, SysRoleRepository sysRoleRepository, SysUserRoleRepository sysUserRoleRepository, SysRoleVMMapper sysRoleVMMapper, SysRoleMenuRepository sysRoleMenuRepository) {
        this.sysUserRepository = sysUserRepository;
        this.sysRoleRepository = sysRoleRepository;
        this.sysUserRoleRepository = sysUserRoleRepository;
        this.sysRoleVMMapper = sysRoleVMMapper;
        this.sysRoleMenuRepository = sysRoleMenuRepository;
    }

    @PostMapping("/sys-roles")
    @Transactional
    @ApiOperation(value = "新增角色", notes = "作者：李春浩")
    public ResponseEntity<SysRole> createSysRole(@Valid @RequestBody SysRoleVM sysRoleVM) throws URISyntaxException {
        log.debug("REST request to save SysRole : {}", sysRoleVM);
        if (sysRoleVM.getId() != null) {
            throw new BadRequestAlertException("A new sysRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        sysRoleRepository.getOneByRoleName(sysRoleVM.getRoleName())
            .ifPresent(so -> {
                throw new BadRequestAlertException("角色名称已存在", ENTITY_NAME, "roleName.exist");
            });
        sysRoleRepository.getOneByRoleCode(sysRoleVM.getRoleCode())
            .ifPresent(so -> {
                throw new BadRequestAlertException("角色编码已存在", ENTITY_NAME, "roleCode.exist");
            });
        SysRole result = sysRoleRepository.save(sysRoleVMMapper.toEntity(sysRoleVM));
        sysRoleVM.getSysMenuIds().forEach(sysMenuId ->
            sysRoleMenuRepository.save(new SysRoleMenu().sysRoleId(result.getId()).sysMenuId(Long.valueOf(sysMenuId)))
        );
        return ResponseEntity.created(new URI("/api/sys-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/sys-roles")
    @ApiOperation(value = "修改角色", notes = "作者：李春浩")
    public ResponseEntity<SysRole> updateSysRole(@Valid @RequestBody SysRole sysRole) throws URISyntaxException {
        log.debug("REST request to update SysRole : {}", sysRole);
        if (sysRole.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        sysRoleRepository.getOneByRoleNameAndIdNot(sysRole.getRoleName(), sysRole.getId())
            .ifPresent(sp -> {
                throw new BadRequestAlertException("角色名称已存在", ENTITY_NAME, "roleName.exist");
            });
        sysRoleRepository.getOneByRoleCodeAndIdNot(sysRole.getRoleCode(), sysRole.getId())
            .ifPresent(so -> {
                throw new BadRequestAlertException("角色代码已存在", ENTITY_NAME, "roleCode.exist");
            });
        SysRole result = sysRoleRepository.save(sysRole);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysRole.getId().toString()))
            .body(result);
    }

    @GetMapping("/sys-roles")
    @ApiOperation(value = "角色列表", notes = "作者：李春浩")
    public ResponseEntity<List<SysRole>> getAllSysRoles(SysRoleQM qm, Pageable pageable) {
        log.debug("REST request to get a page of SysRoles : {}", qm);
        OptionalBooleanBuilder predicate = new OptionalBooleanBuilder()
            .notEmptyAnd(SYS_ROLE.roleName::contains, qm.getRoleName())
            .notEmptyAnd(SYS_ROLE.roleCode::contains, qm.getRoleCode())
            .notEmptyAnd(SYS_ROLE.roleType::eq, qm.getRoleType())
            .notEmptyAnd(SYS_ROLE.isSys::eq, qm.getSys())
            .notEmptyAnd(SYS_ROLE.status::eq, qm.getRoleStatusType())
            .notEmptyAnd(SYS_ROLE.status::ne, RoleStatusType.DELETE);
        Page<SysRole> page = sysRoleRepository.findAll(predicate.build(), pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/sys-roles/{id}")
    @ApiOperation(value = "角色详情", notes = "作者：李春浩")
    public ResponseEntity<SysRoleVM> getSysRole(@PathVariable Long id) {
        log.debug("REST request to get SysRole : {}", id);
        Optional<SysRoleVM> sysRoleVMOpt = sysRoleRepository.findById(id)
            .map(sysRole -> {
                List<String> menuIds = sysRoleMenuRepository.findAllBySysRoleIdIn(Collections.singletonList(id))
                    .stream().map(sysRoleMenu -> String.valueOf(sysRoleMenu.getSysMenuId()))
                    .collect(Collectors.toList());
                SysRoleVM sysRoleVM = sysRoleVMMapper.toDto(sysRole);
                sysRoleVM.setSysMenuIds(menuIds);
                return sysRoleVM;
            });

        return ResponseUtil.wrapOrNotFound(sysRoleVMOpt);
    }

    @DeleteMapping("/sys-roles/{id}")
    @Transactional
    @ApiOperation(value = "删除角色", notes = "作者：李春浩")
    public ResponseEntity<Void> deleteSysRole(@PathVariable Long id) {
        log.debug("REST request to delete SysRole : {}", id);
        List<Long> sysUserIds = sysUserRoleRepository.findAllBySysRoleId(id).stream().map(SysUserRole::getSysUserId).collect(Collectors.toList());
        List<QSysUserTable> usersInRole = sysUserRepository.findAllByIdInAndStatusNot(sysUserIds, UserStatusType.DELETE);
        if (!usersInRole.isEmpty()) {
            throw new BadRequestAlertException("存在使用此角色的用户，不可删除", ENTITY_NAME, "role.inUse");
        }
        sysRoleRepository.findById(id).ifPresent(sysRole -> {
            sysRole.setStatus(RoleStatusType.DELETE);
            sysRoleRepository.save(sysRole);
        });
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
