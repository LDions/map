package com.ruowei.modules.sys.web;

import com.ruowei.common.error.exception.BadRequestAlertException;
import com.ruowei.common.response.PaginationUtil;
import com.ruowei.modules.sys.domain.SysMenu;
import com.ruowei.modules.sys.domain.SysRoleMenu;
import com.ruowei.modules.sys.domain.enumeration.MenuStatusType;
import com.ruowei.modules.sys.domain.enumeration.MenuType;
import com.ruowei.modules.sys.domain.enumeration.SysType;
import com.ruowei.modules.sys.domain.enumeration.UserType;
import com.ruowei.modules.sys.domain.table.SysEmployeeTable;
import com.ruowei.modules.sys.domain.table.SysUserTable;
import com.ruowei.modules.sys.pojo.vm.ResultVM;
import com.ruowei.modules.sys.pojo.vm.UpdateMenuQM;
import com.ruowei.modules.sys.repository.SysMenuRepository;
import com.ruowei.modules.sys.repository.SysRoleMenuRepository;
import com.ruowei.modules.sys.repository.SysUserRoleRepository;
import com.ruowei.modules.sys.repository.table.SysEmployeeTableRepository;
import com.ruowei.modules.sys.repository.table.SysUserTableRepository;
import com.ruowei.modules.sys.service.SysMenuService;
import com.ruowei.modules.sys.utils.MenuUtil;
import com.ruowei.security.CurrentUser;
import com.ruowei.security.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * REST controller for managing {@link SysMenu}.
 */
@RestController
@RequestMapping("/api/SysMenuResource")
@Api(tags = "菜单相关接口")
public class SysMenuResource {

    private final Logger log = LoggerFactory.getLogger(SysMenuResource.class);
    private final SysMenuRepository sysMenuRepository;
    private final SysMenuService sysMenuService;
    private final SysUserRoleRepository sysUserRoleRepository;
    private final SysRoleMenuRepository sysRoleMenuRepository;
    private final SysEmployeeTableRepository sysEmployeeTableRepository;
    private final SysUserTableRepository sysUserTableRepository;

    public SysMenuResource(SysMenuRepository sysMenuRepository,
                           SysMenuService sysMenuService,
                           SysUserRoleRepository sysUserRoleRepository,
                           SysRoleMenuRepository sysRoleMenuRepository,
                           SysEmployeeTableRepository sysEmployeeTableRepository,
                           SysUserTableRepository sysUserTableRepository) {
        this.sysMenuRepository = sysMenuRepository;
        this.sysMenuService = sysMenuService;
        this.sysUserRoleRepository = sysUserRoleRepository;
        this.sysRoleMenuRepository = sysRoleMenuRepository;
        this.sysEmployeeTableRepository = sysEmployeeTableRepository;
        this.sysUserTableRepository = sysUserTableRepository;
    }

    @GetMapping("/findMenuList")
    @ApiOperation(value = "查询菜单列表", notes = "作者：刘路超 \n" +
        "修改者：无 \n" +
        "参数说明： \n" +
        "   menuName 菜单名称 \n" +
        "   parentId 父级菜单ID \n" +
        "返回： \n" +
        "   SysMenu集合")
    public ResponseEntity<List> FindMenuList(@RequestParam(value = "menuName", required = false) String menuName,
                                             @RequestParam(value = "parentId", required = false) Long parentId,

                                             Pageable pageable) throws URISyntaxException {
        log.debug("REST request to FindMenuList: {}");
        // 查询菜单列表
        Page<SysMenu> sysMenus;
        if (menuName != null && !"".equals(menuName)) {
            sysMenus = sysMenuRepository.findAllByMenuNameContainsAndStatusNotOrderByMenuSortAsc(menuName, MenuStatusType.DELETE, pageable);
        } else {
            if (parentId != null) {
                sysMenus = sysMenuRepository.findAllByParentIdAndStatusNotOrderByMenuSortAsc(parentId, MenuStatusType.DELETE, pageable);
            } else {
                sysMenus = sysMenuRepository.findAllByTreeLevelAndStatusNotOrderByMenuSortAsc(1, MenuStatusType.DELETE, pageable);
            }
        }

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(sysMenus, "");
        return ResponseEntity.ok().headers(headers).body(sysMenus.getContent());
    }

    @PutMapping("/saveSort")
    @ApiOperation(value = "保存排序", notes = "作者：刘路超 \n" +
        "修改者：无 \n" +
        "参数说明： \n" +
        "   sysMenus 有排序和id字段的SysMenu集合 \n" +
        "返回： \n" +
        "   ResultVM")
    public ResponseEntity<ResultVM> SaveSort(@RequestBody List<SysMenu> sysMenus) throws URISyntaxException {
        log.debug("REST request to save SysMenu sort : {}", sysMenus);
        List<SysMenu> _sysMenus = new ArrayList<>();
        if (sysMenus != null && sysMenus.size() > 0) {
            for (SysMenu sysMenu : sysMenus) {
                Optional<SysMenu> _sysMenuOpt = sysMenuRepository.findById(sysMenu.getId());
                if (_sysMenuOpt.isPresent()) {
                    SysMenu _sysMenu = _sysMenuOpt.get();
                    _sysMenu.setMenuSort(sysMenu.getMenuSort());
                    _sysMenus.add(_sysMenu);
                }
            }
            sysMenuRepository.saveAll(_sysMenus);
        }
        ResultVM resultVM = new ResultVM();
        resultVM.setTitle("保存成功。");
        return ResponseEntity.ok().body(resultVM);
    }

    @PostMapping("/addMenu")
    @ApiOperation(value = "添加菜单或权限（按钮）", notes = "作者：刘路超 \n" +
        "修改者：无 \n" +
        "参数说明： \n" +
        "   sysMenu 添加后的SysMenu \n" +
        "返回： \n" +
        "   SysMenu")
    public ResponseEntity<SysMenu> addMenu(@RequestBody SysMenu sysMenu) throws URISyntaxException {
        log.debug("REST request to add SysMenu : {}", sysMenu);
        if (sysMenu == null) {
            throw new BadRequestAlertException("无效的菜单参数。", "", "添加或编辑失败。");
        }
        // 校验传递的参数是否合法
        List<String> menuTypes = Arrays.asList("MENU", "PERMISSION", "DEV");
        if (!menuTypes.contains(sysMenu.getMenuType().name())) {
            throw new BadRequestAlertException("请选择正确的菜单类型（MENU，PERMISSION，DEV）。", "", "添加或编辑失败。");
        }

        sysMenu.setMenuCode(MenuUtil.createMenuCode());
        sysMenu.setTreeLeaf(true);
        sysMenu.setStatus(MenuStatusType.NORMAL);
        sysMenu.setSysCode(SysType.WEB);

        // 获取父级菜单ID
        if (sysMenu.getParentId() != null) {
            Optional<SysMenu> pSysMenuOpt = sysMenuRepository.findById(sysMenu.getParentId());
            if (pSysMenuOpt.isPresent()) {
                SysMenu pSysMenu = pSysMenuOpt.get();
                /*if(pSysMenu.isTreeLeaf()) {
                    throw new BadRequestAlertException("父菜单已经是叶子结点，无法添加子节点。", "", "");
                }*/
                if (pSysMenu.getParentId() != null && !"".equals(pSysMenu.getParentId())) {
                    sysMenu.setParentIds(pSysMenu.getParentIds() + "," + pSysMenu.getId());
                } else {
                    sysMenu.setParentIds(pSysMenu.getId() + "");
                }
                pSysMenu.setTreeLeaf(false);
                pSysMenu = sysMenuRepository.save(pSysMenu);
                sysMenu.setTreeLevel(pSysMenu.getTreeLevel() + 1);
            } else {
                throw new BadRequestAlertException("所选父级菜单不存在。", "", "添加失败。");
            }
        } else {
            sysMenu.setTreeLevel(1);
        }
        SysMenu result = sysMenuRepository.save(sysMenu);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/editMenu")
    @ApiOperation(value = "修改菜单或权限（按钮）", notes = "作者：刘路超 \n" +
        "修改者：无 \n" +
        "参数说明： \n" +
        "   sysMenu 修改后的SysMenu \n" +
        "返回： \n" +
        "   SysMenu")
    public ResponseEntity<SysMenu> editMenu(@RequestBody SysMenu sysMenu) throws URISyntaxException {
        log.debug("REST request to edit SysMenu : {}", sysMenu);
        if (sysMenu == null || sysMenu.getId() == null) {
            throw new BadRequestAlertException("无效的菜单参数。", "", "编辑失败。");
        }
        // 无法修改为自己下属或自己子菜单下属
        List<Long> _menuIds = sysMenuRepository.findAllByIdOrParentIdsContains(sysMenu.getId(), sysMenu.getId())
            .stream().map(SysMenu::getId)
            .collect(Collectors.toList());
        if (sysMenu.getParentId() != null && _menuIds.contains(sysMenu.getParentId())) {
            throw new BadRequestAlertException("无法修改成菜单级别。", "", "编辑失败。");
        }

        // 校验传递的参数是否合法
        List<String> menuTypes = Arrays.asList("MENU", "PERMISSION", "DEV");
        if (!menuTypes.contains(sysMenu.getMenuType().name())) {
            throw new BadRequestAlertException("请选择正确的菜单类型（MENU，PERMISSION，DEV）。", "", "编辑失败。");
        }

        Optional<SysMenu> sysMenuOpt = sysMenuRepository.findById(sysMenu.getId());
        if (!sysMenuOpt.isPresent()) {
            throw new BadRequestAlertException("不存在该菜单。", "", "编辑失败。");
        }
        SysMenu oldSysMenu = sysMenuOpt.get();
        sysMenu.setMenuCode(oldSysMenu.getMenuCode());
        sysMenu.setStatus(oldSysMenu.getStatus());
        sysMenu.setSysCode(oldSysMenu.getSysCode());
        sysMenu.setTreeLeaf(oldSysMenu.isTreeLeaf());

        // 如果原先没有父级菜单
        if (oldSysMenu.getParentId() == null) {
            if (sysMenu.getParentId() != null) {
                Optional<SysMenu> pSysMenuOpt = sysMenuRepository.findById(sysMenu.getParentId());
                if (pSysMenuOpt.isPresent()) {
                    SysMenu pSysMenu = pSysMenuOpt.get();
                    if (pSysMenu.getParentId() != null && !"".equals(pSysMenu.getParentId())) {
                        sysMenu.setParentIds(pSysMenu.getParentIds() + "," + pSysMenu.getId());
                    } else {
                        sysMenu.setParentIds(pSysMenu.getId() + "");
                    }
                    pSysMenu.setTreeLeaf(false);
                    pSysMenu = sysMenuRepository.save(pSysMenu);
                    sysMenu.setTreeLevel(pSysMenu.getTreeLevel() + 1);
                    // 下级所有子菜单parentIds改变
                    List<Long> menuIds = sysMenuRepository.findAllByParentIdsContains(sysMenu.getId())
                        .stream().map(SysMenu::getId)
                        .collect(Collectors.toList());
                    List<SysMenu> menuList = new ArrayList<>();
                    for (Long menuId1 : menuIds) {
                        Optional<SysMenu> _sysMenuOpt = sysMenuRepository.findById(menuId1);
                        if (_sysMenuOpt.isPresent()) {
                            SysMenu _sysMenu = _sysMenuOpt.get();
                            if (oldSysMenu.getParentIds() == null) {
                                _sysMenu.setParentIds(sysMenu.getParentIds() + "," + _sysMenu.getParentIds());
                            } else {
                                _sysMenu.setParentIds(_sysMenu.getParentIds().replaceAll(oldSysMenu.getParentIds(), sysMenu.getParentIds()));
                            }
                            _sysMenu.setTreeLevel(_sysMenu.getTreeLevel() - oldSysMenu.getTreeLevel() + sysMenu.getTreeLevel());
                            menuList.add(_sysMenu);
                        }
                    }
                    sysMenuRepository.saveAll(menuList);
                } else {
                    throw new BadRequestAlertException("所选父级菜单不存在。", "", "编辑失败。");
                }
            } else {
                sysMenu.setTreeLevel(1);
            }
        } else { // 如果原先有父级菜单
            SysMenu oldPSysMenu = sysMenuRepository.findById(oldSysMenu.getParentId()).get();
            // 如果编辑后父级菜单不为空且与原先不同
            if (sysMenu.getParentId() != null && !oldSysMenu.getParentId().equals(sysMenu.getParentId())) {
                Optional<SysMenu> pSysMenuOpt = sysMenuRepository.findById(sysMenu.getParentId());
                if (pSysMenuOpt.isPresent()) {
                    SysMenu pSysMenu = pSysMenuOpt.get();
                    if (pSysMenu.getParentId() != null && !"".equals(pSysMenu.getParentId())) {
                        sysMenu.setParentIds(pSysMenu.getParentIds() + "," + pSysMenu.getId());
                    } else {
                        sysMenu.setParentIds(pSysMenu.getId() + "");
                    }
                    pSysMenu.setTreeLeaf(false);
                    pSysMenu = sysMenuRepository.save(pSysMenu);
                    sysMenu.setTreeLevel(pSysMenu.getTreeLevel() + 1);
                    // 下级所有子菜单parentIds改变
                    List<Long> menuIds = sysMenuRepository.findAllByParentIdsContains(sysMenu.getId())
                        .stream().map(SysMenu::getId)
                        .collect(Collectors.toList());
                    List<SysMenu> menuList = new ArrayList<>();
                    for (Long menuId1 : menuIds) {
                        Optional<SysMenu> _sysMenuOpt = sysMenuRepository.findById(menuId1);
                        if (_sysMenuOpt.isPresent()) {
                            SysMenu _sysMenu = _sysMenuOpt.get();
                            if (oldSysMenu.getParentIds() == null) {
                                _sysMenu.setParentIds(sysMenu.getParentIds() + "," + _sysMenu.getParentIds());
                            } else {
                                _sysMenu.setParentIds(_sysMenu.getParentIds().replaceAll(oldSysMenu.getParentIds(), sysMenu.getParentIds()));
                            }
                            _sysMenu.setTreeLevel(_sysMenu.getTreeLevel() - oldSysMenu.getTreeLevel() + sysMenu.getTreeLevel());
                            menuList.add(_sysMenu);
                        }
                    }
                    sysMenuRepository.saveAll(menuList);
                } else {
                    throw new BadRequestAlertException("所选父级菜单不存在。", "", "编辑失败。");
                }
            } else {
                sysMenu.setTreeLevel(1);
                sysMenu.setParentId(null);
                sysMenu.setParentIds(null);
                // 下级所有子菜单parentIds改变
                List<Long> menuIds = sysMenuRepository.findAllByParentIdsContains(sysMenu.getId())
                    .stream().map(SysMenu::getId)
                    .collect(Collectors.toList());
                List<SysMenu> menuList = new ArrayList<>();
                for (Long menuId1 : menuIds) {
                    Optional<SysMenu> _sysMenuOpt = sysMenuRepository.findById(menuId1);
                    if (_sysMenuOpt.isPresent()) {
                        SysMenu _sysMenu = _sysMenuOpt.get();
                        if (oldSysMenu.getParentIds() != null) {
                            _sysMenu.setParentIds(_sysMenu.getParentIds().replaceAll(oldSysMenu.getParentIds(), ""));
                        }
                        if (_sysMenu.getParentIds().startsWith(",")) {
                            _sysMenu.setParentIds(_sysMenu.getParentIds().substring(1));
                        }
                        _sysMenu.setTreeLevel(_sysMenu.getTreeLevel() - oldSysMenu.getTreeLevel() + sysMenu.getTreeLevel());
                        menuList.add(_sysMenu);
                    }
                }
                sysMenuRepository.saveAll(menuList);
            }
            // 处理原先的父级菜单，如果父级菜单没有子菜单，修改父级菜单treeLeaf字段
            List<Long> pMenuIds = sysMenuRepository.findAllByParentIdAndStatus(oldPSysMenu.getId(), MenuStatusType.NORMAL)
                .stream().map(SysMenu::getId)
                .collect(Collectors.toList());
            if (pMenuIds.size() == 0) {
                oldPSysMenu.setTreeLeaf(true);
                sysMenuRepository.save(oldPSysMenu);
            }
        }

        SysMenu result = sysMenuRepository.save(sysMenu);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/deleteMenu")
    @ApiOperation(value = "删除菜单、权限（按钮）", notes = "作者：刘路超 \n" +
        "修改者：无 \n" +
        "参数说明： \n" +
        "   menuId 菜单ID \n" +
        "   status 菜单状态（NORMAL DELETE DISABLE）\n" +
        "返回： \n" +
        "   ResultVM")
    public ResponseEntity<ResultVM> DeleteMenu(@RequestParam Long menuId) throws URISyntaxException {
        log.debug("REST request to deleteMenu : {}", menuId);
        Optional<SysMenu> _sysMenuOpt = sysMenuRepository.findById(menuId);
        if (!_sysMenuOpt.isPresent()) {
            throw new BadRequestAlertException("无效的菜单ID。", "", "删除失败。");
        }
        // 查询所传菜单及其下属所有节点
        List<Long> menuIds = sysMenuRepository.findAllByIdOrParentIdsContains(menuId, menuId)
            .stream().map(SysMenu::getId)
            .collect(Collectors.toList());
        List<SysMenu> menuList = new ArrayList<>();
        for (Long menuId1 : menuIds) {
            Optional<SysMenu> sysMenuOpt = sysMenuRepository.findById(menuId1);
            if (sysMenuOpt.isPresent()) {
                SysMenu sysMenu = sysMenuOpt.get();
                sysMenu.setStatus(MenuStatusType.DELETE);
                menuList.add(sysMenu);
            }
        }
        sysMenuRepository.saveAll(menuList);
        // 如果父级菜单没有子菜单，修改父级菜单treeLeaf字段
        List<Long> pMenuIds = sysMenuRepository.findAllByParentIdAndStatus(_sysMenuOpt.get().getParentId(), MenuStatusType.NORMAL)
            .stream().map(SysMenu::getId)
            .collect(Collectors.toList());
        if (pMenuIds.size() == 0) {
            SysMenu pSysMenu = sysMenuRepository.findById(_sysMenuOpt.get().getParentId()).get();
            pSysMenu.setTreeLeaf(true);
            sysMenuRepository.save(pSysMenu);
        }
        ResultVM resultVM = new ResultVM();
        resultVM.setTitle("删除成功。");
        return ResponseEntity.ok().body(resultVM);
    }

    @PutMapping("/isShowMenu")
    @ApiOperation(value = "是否显示菜单或权限（按钮）", notes = "作者：刘路超 \n" +
        "修改者：无 \n" +
        "参数说明：\n" +
        "   menuIds 菜单ID集合 \n" +
        "   isShow 是否显示（ture false）\n" +
        "返回： \n" +
        "   ResultVM")
    public ResponseEntity<ResultVM> NormalMenu(@RequestParam List<Long> menuIds,
                                               @RequestParam boolean isShow) throws URISyntaxException {
        log.debug("REST request to normalMenu: {}", menuIds);
        List<SysMenu> menuList = new ArrayList<>();
        for (Long menuId : menuIds) {
            Optional<SysMenu> sysMenuOpt = sysMenuRepository.findById(menuId);
            if (sysMenuOpt.isPresent()) {
                SysMenu sysMenu = sysMenuOpt.get();
                sysMenu.setIsShow(isShow);
                menuList.add(sysMenu);
            }
        }
        sysMenuRepository.saveAll(menuList);
        ResultVM resultVM = new ResultVM();
        resultVM.setTitle("修改成功。");
        return ResponseEntity.ok().body(resultVM);
    }

    @GetMapping("/findALLMenu")
    @ApiOperation(value = "查询所有菜单", notes = "作者：刘路超 \n" +
        "修改者：无 \n" +
        "参数说明： \n" +
        "返回： \n" +
        "   菜单树")
    public ResponseEntity<List> FindALLMenu() throws URISyntaxException {
        log.debug("REST request to findALLMenu: {}");
        // 查询所有菜单
        List<SysMenu> sysMenus = sysMenuRepository.findAllByIdIsNotNullAndStatusNotOrderByMenuSortAsc(MenuStatusType.DELETE);
        List result = MenuUtil.createMenuTree(sysMenus);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/findMenuDetail")
    @ApiOperation(value = "查询菜单详情", notes = "作者：刘路超 \n" +
        "修改者：无 \n" +
        "参数说明：\n" +
        "   menuId 菜单ID \n" +
        "返回： \n" +
        "   SysMenu")
    public ResponseEntity<Map> findMenuDetail(@RequestParam("menuId") Long menuId) throws Exception {
        log.debug("REST request to findMenuDetail: {}", menuId);
        // 查询所有菜单
        Optional<SysMenu> sysMenuOpt = sysMenuRepository.findById(menuId);
        if (!sysMenuOpt.isPresent()) {
            throw new BadRequestAlertException("无效的menuId", "", "查询失败。");
        }
        Map result = MenuUtil.bean2map(sysMenuOpt.get());
        result.put("parentId", String.valueOf(sysMenuOpt.get().getParentId()));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/findALLPermission")
    @ApiOperation(value = "查询所有权限（按钮）", notes = "作者：刘路超 \n" +
        "修改者：无 \n" +
        "参数说明：无 \n" +
        "返回： \n" +
        "   按钮类型非删除状态的SysMenu集合")
    public ResponseEntity<List> FindALLPermission() throws URISyntaxException {
        log.debug("REST request to findALLPermission: {}");
        // 查询所有菜单
        List<SysMenu> sysMenus = sysMenuRepository.findAllByMenuTypeAndStatusNotOrderByMenuSortAsc(MenuType.PERMISSION, MenuStatusType.DELETE);
        return ResponseEntity.ok().body(sysMenus);
    }

    @GetMapping("/findRoleMenu")
    @ApiOperation(value = "获取角色菜单", notes = "作者：刘路超 \n" +
        "修改者：无 \n" +
        "参数说明： \n" +
        "   userModel 登录者信息 \n" +
        "返回： \n" +
        "   菜单树")
    public ResponseEntity<List> FindRoleMenu(@ApiIgnore @CurrentUser UserModel userModel) throws URISyntaxException {
        log.debug("REST request to findRoleMenu: {}");
        List<Long> roleIds = sysUserRoleRepository.findAllBySysUserId(userModel.getId())
            .stream().map(SysUserRole -> SysUserRole.getSysRoleId())
            .collect(Collectors.toList());
        List<Long> menuIds = sysRoleMenuRepository.findAllBySysRoleIdIn(roleIds)
            .stream().map(SysRoleMenu::getSysMenuId)
            .collect(Collectors.toList());
        log.debug("REST request to testRoleMenu: {}");
        return ResponseEntity.ok().body(sysMenuService.findSysMenusByMenuIds(menuIds));
    }

    @GetMapping("/findRolePermission")
    @ApiOperation(value = "获取角色权限（按钮）", notes = "作者：刘路超 \n" +
        "修改者：无 \n" +
        "参数说明： \n" +
        "   userModel 登录者信息 \n" +
        "返回： \n" +
        "   按钮类型的SysMenu集合")
    public ResponseEntity<List> FindRolePermission(@ApiIgnore @CurrentUser UserModel userModel) throws URISyntaxException {
        log.debug("REST request to findRolePermission: {}");
        List<Long> roleIds = sysUserRoleRepository.findAllBySysUserId(userModel.getId())
            .stream().map(SysUserRole -> SysUserRole.getSysRoleId())
            .collect(Collectors.toList());
        List<Long> menuIds = sysRoleMenuRepository.findAllBySysRoleIdIn(roleIds)
            .stream().map(SysRoleMenu::getSysMenuId)
            .collect(Collectors.toList());
        log.debug("REST request to testRoleMenu: {}");
        return ResponseEntity.ok().body(sysMenuService.findSysPermissionsByMenuIds(menuIds));
    }

    @GetMapping("/findRoleMenuUse")
    @ApiOperation(value = "获取角色可用菜单", notes = "作者：刘路超 \n" +
        "修改者：无 \n" +
        "参数说明： \n" +
        "   userModel 登录者信息 \n" +
        "返回： \n" +
        "   菜单树")
    public ResponseEntity<List> FindRoleMenuUse(@ApiIgnore @CurrentUser UserModel userModel) throws Exception {
        log.debug("REST request to findRoleMenuUse: {}");
        Optional<SysUserTable> sysUserOpt = sysUserTableRepository.findById(userModel.getId());
        if (sysUserOpt.isPresent()) {
            SysUserTable sysUser = sysUserOpt.get();
            // 如果是雇员
            if (UserType.EMPLOYEE.name().equals(sysUser.getUserType().name())) {
                Optional<SysEmployeeTable> sysEmployeeOpt = sysEmployeeTableRepository.findById(sysUser.getRefCode());
                if (sysEmployeeOpt.isPresent()) {
                    SysEmployeeTable sysEmployee = sysEmployeeOpt.get();
                    // 机构角色IDs
                    List<Long> orgRoleIds = sysUserRoleRepository.findAllBySysUserId(sysUser.getId())
                        .stream().map(SysUserRole -> SysUserRole.getSysRoleId())
                        .collect(Collectors.toList());
                    List<Long> orgMenuIds = sysRoleMenuRepository.findAllBySysRoleIdIn(orgRoleIds)
                        .stream().map(SysRoleMenu::getSysMenuId)
                        .collect(Collectors.toList());
                    // 用户角色IDs
                    List<Long> userRoleIds = sysUserRoleRepository.findAllBySysUserId(userModel.getId())
                        .stream().map(SysUserRole -> SysUserRole.getSysRoleId())
                        .collect(Collectors.toList());
                    List<Long> userMenuIds = sysRoleMenuRepository.findAllBySysRoleIdIn(userRoleIds)
                        .stream().map(SysRoleMenu::getSysMenuId)
                        .collect(Collectors.toList());
                    return ResponseEntity.ok().body(sysMenuService.findSysMenusByMenuIds(orgMenuIds, userMenuIds));
                } else {
                    throw new BadRequestAlertException("登录用户暂无相关联员工信息。", "", "查询失败。");
                }
            } else { // 如果是会员
                // TODO 会员表相关操作
                return ResponseEntity.ok().body(null);
            }
        } else {
            throw new BadRequestAlertException("登录用户错误。", "", "查询失败。");
        }
    }

    @GetMapping("/findRolePermissionUse")
    @ApiOperation(value = "获取角色可用权限（按钮）", notes = "作者：刘路超 \n" +
        "修改者：无 \n" +
        "参数说明： \n" +
        "   userModel 登录者信息 \n" +
        "返回： \n" +
        "   SysMenu属性+isUse属性的map集合")
    public ResponseEntity<List> FindRolePermissionUse(@ApiIgnore @CurrentUser UserModel userModel) throws Exception {
        log.debug("REST request to findRoleMenuUse: {}");
        Optional<SysUserTable> sysUserOpt = sysUserTableRepository.findById(userModel.getId());
        if (sysUserOpt.isPresent()) {
            SysUserTable sysUser = sysUserOpt.get();
            // 如果是雇员
            if (UserType.EMPLOYEE.name().equals(sysUser.getUserType().name())) {
                Optional<SysEmployeeTable> sysEmployeeOpt = sysEmployeeTableRepository.findById(sysUser.getRefCode());
                if (sysEmployeeOpt.isPresent()) {
                    SysEmployeeTable sysEmployee = sysEmployeeOpt.get();
                    // 机构角色IDs
                    List<Long> orgRoleIds = sysUserRoleRepository.findAllBySysUserId(sysEmployee.getSysOfficeId())
                        .stream().map(SysUserRole -> SysUserRole.getSysRoleId())
                        .collect(Collectors.toList());
                    List<Long> orgMenuIds = sysRoleMenuRepository.findAllBySysRoleIdIn(orgRoleIds)
                        .stream().map(SysRoleMenu::getSysMenuId)
                        .collect(Collectors.toList());
                    // 用户角色IDs
                    List<Long> userRoleIds = sysUserRoleRepository.findAllBySysUserId(userModel.getId())
                        .stream().map(SysUserRole -> SysUserRole.getSysRoleId())
                        .collect(Collectors.toList());
                    List<Long> userMenuIds = sysRoleMenuRepository.findAllBySysRoleIdIn(userRoleIds)
                        .stream().map(SysRoleMenu::getSysMenuId)
                        .collect(Collectors.toList());
                    return ResponseEntity.ok().body(sysMenuService.findSysPermissionsByMenuIds(orgMenuIds, userMenuIds));
                } else {
                    throw new BadRequestAlertException("登录用户暂无相关联员工信息。", "", "查询失败。");
                }
            } else { // 如果是会员
                // TODO 会员表相关操作
                return ResponseEntity.ok().body(null);
            }
        } else {
            throw new BadRequestAlertException("登录用户错误。", "", "查询失败。");
        }
    }

    @PutMapping("/updateRoleMenu")
    @ApiOperation(value = "更新角色菜单权限", notes = "作者：刘路超 \n" +
        "修改者：无 \n" +
        "参数：\n" +
        "   updateMenuQM \n" +
        "返回： \n" +
        "   SysMenu属性+isUse属性的map集合")
    public ResponseEntity<ResultVM> FindRoleMenuUse(@RequestBody UpdateMenuQM updateMenuQM) throws URISyntaxException {
        log.debug("REST request to updateRoleMenu: {}");
        sysMenuService.updateSysRoleMenuByRoleId(updateMenuQM.getRoleId(), updateMenuQM.getMenuIds());
        ResultVM resultVM = new ResultVM();
        resultVM.setTitle("更新成功。");
        return ResponseEntity.ok().body(resultVM);
    }
}
