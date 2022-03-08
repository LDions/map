package com.ruowei.web.rest;

import com.github.yitter.idgen.YitIdHelper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.Menu;
import com.ruowei.domain.QMenu;
import com.ruowei.domain.RoleMenu;
import com.ruowei.domain.enumeration.MenuStatusType;
import com.ruowei.domain.enumeration.MenuType;
import com.ruowei.domain.enumeration.SysType;
import com.ruowei.repository.MenuRepository;
import com.ruowei.repository.RoleMenuRepository;
import com.ruowei.security.UserModel;
import com.ruowei.service.MenuService;
import com.ruowei.web.rest.dto.MenuTreeDTO;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.RoleMenuVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tech.jhipster.web.util.ResponseUtil;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(tags = "菜单权限管理")
@Transactional
public class MenuResource {

    private final Logger log = LoggerFactory.getLogger(MenuResource.class);
    private final JPAQueryFactory jpaQueryFactory;
    private final MenuService menuService;
    private final MenuRepository menuRepository;
    private final RoleMenuRepository roleMenuRepository;
    private QMenu qMenu = QMenu.menu;

    public MenuResource(JPAQueryFactory jpaQueryFactory, MenuService menuService, MenuRepository menuRepository, RoleMenuRepository roleMenuRepository) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.menuService = menuService;
        this.menuRepository = menuRepository;
        this.roleMenuRepository = roleMenuRepository;
    }

    @PostMapping("/menu")
    @Transactional
    @ApiOperation(value = "添加菜单或权限（按钮）接口", notes = "作者：林宏栋")
    public ResponseEntity<Menu> addMenu(@RequestBody Menu menu) {
        log.debug("REST request to add SysMenu : {}", menu);
        if (menu == null) {
            throw new BadRequestProblem("添加失败", "无效的菜单参数");
        }
        // 校验传递的参数是否合法
        List<MenuType> menuTypes = Arrays.asList(MenuType.MENU, MenuType.PERMISSION);
        if (!menuTypes.contains(menu.getMenuType())) {
            throw new BadRequestProblem("添加失败", "请选择正确的菜单类型（MENU，PERMISSION）");
        }
        menu.setMenuCode("MENU-".concat(String.valueOf(YitIdHelper.nextId())));
        menu.setTreeLeaf(true);
        menu.setStatus(MenuStatusType.NORMAL);
        menu.setSysCode(SysType.WEB);
        // 获取父级菜单ID
        if (menu.getParentId() != null) {
            Optional<Menu> pMenuOpt = menuRepository.findByIdAndStatus(menu.getParentId(), MenuStatusType.NORMAL);
            if (pMenuOpt.isPresent()) {
                Menu pMenu = pMenuOpt.get();
                if (pMenu.getParentId() != null) {
                    menu.setParentIds(pMenu.getParentIds() + "," + pMenu.getId());
                } else {
                    menu.setParentIds(pMenu.getId() + "");
                }
                pMenu.setTreeLeaf(false);
                pMenu = menuRepository.save(pMenu);
                menu.setTreeLevel(pMenu.getTreeLevel() + 1);
            } else {
                throw new BadRequestProblem("添加失败", "所选父级菜单不存在");
            }
        } else {
            menu.setTreeLevel(1);
        }
        Menu result = menuRepository.save(menu);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/menu")
    @ApiOperation(value = "编辑菜单或权限（按钮）接口", notes = "作者：林宏栋")
    public ResponseEntity<Menu> editMenu(@RequestBody Menu menu) {
        log.debug("REST request to edit SysMenu : {}", menu);
        if (menu == null || menu.getId() == null) {
            throw new BadRequestProblem("编辑失败", "无效的菜单参数");
        }
        // 无法修改为自己下属或自己子菜单下属
        List<Long> menuIds = jpaQueryFactory
            .selectFrom(qMenu)
            .where(qMenu.status.eq(MenuStatusType.NORMAL).and(qMenu.id.eq(menu.getId()).or(qMenu.parentIds.isNotNull())))
            .stream()
            .filter(m -> Arrays.asList(m.getParentIds().split(",")).contains(String.valueOf(menu.getId())) || m.getId().equals(menu.getId()))
            .map(Menu::getId)
            .collect(Collectors.toList());
        if (menu.getParentId() != null && menuIds.contains(menu.getParentId())) {
            throw new BadRequestProblem("编辑失败", "无法修改为自己下属或自己子菜单下属");
        }
        // 校验传递的参数是否合法
        List<MenuType> menuTypes = Arrays.asList(MenuType.MENU, MenuType.PERMISSION);
        if (!menuTypes.contains(menu.getMenuType())) {
            throw new BadRequestProblem("编辑失败", "请选择正确的菜单类型（MENU，PERMISSION）");
        }
        Optional<Menu> menuOpt = menuRepository.findByIdAndStatus(menu.getId(), MenuStatusType.NORMAL);
        if (menuOpt.isEmpty()) {
            throw new BadRequestProblem("编辑失败", "不存在该菜单");
        }
        Menu oldMenu = menuOpt.get();
        menu.setMenuCode(oldMenu.getMenuCode());
        menu.setStatus(oldMenu.getStatus());
        menu.setSysCode(oldMenu.getSysCode());
        menu.setTreeLeaf(oldMenu.getTreeLeaf());
        // 如果原先没有父级菜单
        if (oldMenu.getParentId() == null) {
            if (menu.getParentId() != null) {
                Optional<Menu> pMenuOpt = menuRepository.findByIdAndStatus(menu.getParentId(), MenuStatusType.NORMAL);
                if (pMenuOpt.isPresent()) {
                    Menu pMenu = pMenuOpt.get();
                    if (pMenu.getParentId() != null) {
                        menu.setParentIds(pMenu.getParentIds() + "," + pMenu.getId());
                    } else {
                        menu.setParentIds(pMenu.getId() + "");
                    }
                    pMenu.setTreeLeaf(false);
                    pMenu = menuRepository.save(pMenu);
                    menu.setTreeLevel(pMenu.getTreeLevel() + 1);
                    // 下级所有子菜单parentIds改变
                    List<Menu> childMenus = jpaQueryFactory
                        .selectFrom(qMenu)
                        .where(qMenu.status.eq(MenuStatusType.NORMAL).and(qMenu.parentIds.isNotNull()))
                        .stream()
                        .filter(m -> Arrays.asList(m.getParentIds().split(",")).contains(String.valueOf(menu.getId())))
                        .collect(Collectors.toList());
                    List<Menu> menuList = new ArrayList<>();
                    for (Menu childMenu : childMenus) {
                        if (oldMenu.getParentIds() == null) {
                            childMenu.setParentIds(menu.getParentIds() + "," + childMenu.getParentIds());
                        } else {
                            childMenu.setParentIds(childMenu.getParentIds().replaceAll(oldMenu.getParentIds(), menu.getParentIds()));
                        }
                        childMenu.setTreeLevel(childMenu.getTreeLevel() - oldMenu.getTreeLevel() + menu.getTreeLevel());
                        menuList.add(childMenu);
                    }
                    menuRepository.saveAll(menuList);
                } else {
                    throw new BadRequestProblem("编辑失败", "所选父级菜单不存在");
                }
            } else {
                menu.setTreeLevel(1);
            }
        } else { // 如果原先有父级菜单
            Menu oldParentMenu = menuRepository.findByIdAndStatus(oldMenu.getParentId(), MenuStatusType.NORMAL)
                .orElseThrow(() -> new BadRequestProblem("编辑失败", "原有父级菜单不存在"));
            // 如果编辑后父级菜单不为空且与原先不同
            if (menu.getParentId() != null && !oldMenu.getParentId().equals(menu.getParentId())) {
                Optional<Menu> pMenuOpt = menuRepository.findByIdAndStatus(menu.getParentId(), MenuStatusType.NORMAL);
                if (pMenuOpt.isPresent()) {
                    Menu pMenu = pMenuOpt.get();
                    if (pMenu.getParentId() != null) {
                        menu.setParentIds(pMenu.getParentIds() + "," + pMenu.getId());
                    } else {
                        menu.setParentIds(pMenu.getId() + "");
                    }
                    pMenu.setTreeLeaf(false);
                    pMenu = menuRepository.save(pMenu);
                    menu.setTreeLevel(pMenu.getTreeLevel() + 1);
                    // 下级所有子菜单parentIds改变
                    List<Menu> childMenus = jpaQueryFactory
                        .selectFrom(qMenu)
                        .where(qMenu.status.eq(MenuStatusType.NORMAL).and(qMenu.parentIds.isNotNull()))
                        .stream()
                        .filter(m -> Arrays.asList(m.getParentIds().split(",")).contains(String.valueOf(menu.getId())))
                        .collect(Collectors.toList());
                    List<Menu> menuList = new ArrayList<>();
                    for (Menu childMenu : childMenus) {
                        if (oldMenu.getParentIds() == null) {
                            childMenu.setParentIds(menu.getParentIds() + "," + childMenu.getParentIds());
                        } else {
                            childMenu.setParentIds(childMenu.getParentIds().replaceAll(oldMenu.getParentIds(), menu.getParentIds()));
                        }
                        childMenu.setTreeLevel(childMenu.getTreeLevel() - oldMenu.getTreeLevel() + menu.getTreeLevel());
                        menuList.add(childMenu);
                    }
                    menuRepository.saveAll(menuList);
                } else {
                    throw new BadRequestProblem("编辑失败", "所选父级菜单不存在");
                }
            } else if (menu.getParentId() == null) {
                // 现在没有父级菜单
                menu.setTreeLevel(1);
                menu.setParentId(null);
                menu.setParentIds(null);
                // 下级所有子菜单parentIds改变
                List<Menu> childMenus = jpaQueryFactory
                    .selectFrom(qMenu)
                    .where(qMenu.status.eq(MenuStatusType.NORMAL).and(qMenu.parentIds.isNotNull()))
                    .stream()
                    .filter(m -> Arrays.asList(m.getParentIds().split(",")).contains(String.valueOf(menu.getId())))
                    .collect(Collectors.toList());
                List<Menu> menuList = new ArrayList<>();
                for (Menu childMenu : childMenus) {
                    if (oldMenu.getParentIds() != null) {
                        childMenu.setParentIds(childMenu.getParentIds().replaceAll(oldMenu.getParentIds(), ""));
                    }
                    if (childMenu.getParentIds().startsWith(",")) {
                        childMenu.setParentIds(childMenu.getParentIds().substring(1));
                    }
                    childMenu.setTreeLevel(childMenu.getTreeLevel() - oldMenu.getTreeLevel() + menu.getTreeLevel());
                    menuList.add(childMenu);
                }
                menuRepository.saveAll(menuList);
            } else if (menu.getParentId() != null && oldMenu.getParentId().equals(menu.getParentId())) {
                // 现在有父级菜单且与原父级菜单相同
                menu.setParentIds(oldMenu.getParentIds());
                menu.setTreeLevel(oldMenu.getTreeLevel());
            }
            // 处理原先的父级菜单，如果父级菜单没有子菜单，修改父级菜单treeLeaf字段
            List<Long> pMenuIds = menuRepository.findAllByParentIdAndStatus(oldParentMenu.getId(), MenuStatusType.NORMAL)
                .stream().map(Menu::getId)
                .collect(Collectors.toList());
            if (pMenuIds.size() == 0) {
                oldParentMenu.setTreeLeaf(true);
                menuRepository.save(oldParentMenu);
            }
        }
        Menu result = menuRepository.save(menu);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/menus/all")
    @ApiOperation(value = "查询所有菜单接口", notes = "作者：林宏栋")
    public ResponseEntity<List<MenuTreeDTO>> findAllMenusAsTree(@ApiParam(value = "归属系统WEB|MOBILE", required = true) @RequestParam SysType sysType) {
        List<MenuTreeDTO> result = menuService.findAllMenusAsTree(sysType);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/menu/{id}")
    @ApiOperation(value = "查询菜单详情接口", notes = "作者：林宏栋")
    public ResponseEntity<Menu> getMenu(@PathVariable Long id) {
        log.debug("REST request to get SysMenu : {}", id);
        // 查询所有菜单
        Optional<Menu> menuOpt = menuRepository.findByIdAndStatus(id, MenuStatusType.NORMAL);
        return ResponseUtil.wrapOrNotFound(menuOpt);
    }

    @GetMapping("/menu/current")
    @ApiOperation(value = "获取当前登录用户的菜单接口", notes = "作者：林宏栋")
    public ResponseEntity<List<MenuTreeDTO>> getCurrentUserMenus(@ApiParam(value = "归属系统WEB|MOBILE", required = true) @RequestParam SysType sysType,
                                                                 @ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        List<MenuTreeDTO> result = menuService.getCurrentUserMenus(sysType, userModel.getUserId());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/permission/current")
    @ApiOperation(value = "获取当前登录用户的权限接口", notes = "作者：林宏栋")
    public ResponseEntity<List<String>> getCurrentUserPermissions(@ApiParam(value = "归属系统WEB|MOBILE", required = true) @RequestParam SysType sysType,
                                                                @ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        List<String> result = menuService.getCurrentUserPermissions(sysType, userModel.getUserId())
            .stream().map(Menu::getMenuHref).collect(Collectors.toList());
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/role-menu")
    @ApiOperation(value = "更新角色菜单权限接口", notes = "作者：林宏栋")
    public ResponseEntity<Void> updateRoleMenuRelationship(@RequestBody RoleMenuVM vm) {
        if (vm.getMenuIds() != null) {
            roleMenuRepository.deleteAllByRoleId(vm.getRoleId());
            vm.getMenuIds().forEach(menuId ->
                roleMenuRepository.save(new RoleMenu().roleId(vm.getRoleId()).menuId(Long.valueOf(menuId)))
            );
        }
        return ResponseEntity.ok().build();
    }
}
