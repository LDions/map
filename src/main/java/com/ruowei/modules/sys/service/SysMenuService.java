package com.ruowei.modules.sys.service;

import com.ruowei.common.error.exception.BadRequestAlertException;
import com.ruowei.modules.sys.utils.MenuUtil;
import com.ruowei.modules.sys.domain.SysMenu;
import com.ruowei.modules.sys.domain.SysRoleMenu;
import com.ruowei.modules.sys.domain.enumeration.MenuStatusType;
import com.ruowei.modules.sys.domain.enumeration.MenuType;
import com.ruowei.modules.sys.repository.SysRoleMenuRepository;
import com.ruowei.modules.sys.repository.SysMenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
* 详细描述:
* */
@Service
@Transactional
public class SysMenuService {
    private final SysMenuRepository sysMenuRepository;

    private final SysRoleMenuRepository sysRoleMenuRepository;

    public SysMenuService(SysMenuRepository sysMenuRepository,
                          SysRoleMenuRepository sysRoleMenuRepository) {
        this.sysMenuRepository = sysMenuRepository;
        this.sysRoleMenuRepository = sysRoleMenuRepository;
    }

    /**
     * @author 刘路超
     * @修改人 无
     * @详细描述 根据菜单ID列表查询菜单
     * @param menuIds 菜单ID集合
     * @return 菜单树
     */
    public List findSysMenusByMenuIds(List<Long> menuIds) {
        List<SysMenu> sysMenus = sysMenuRepository.findAllByMenuTypeAndIdInAndStatusNotOrderByMenuSortAsc(MenuType.MENU, menuIds, MenuStatusType.DELETE);
        return MenuUtil.createMenuTree(sysMenus);
    }

    /**
     * @author 刘路超
     * @修改人 无
     * @详细描述 根据菜单ID列表查询权限（按钮）
     * @param menuIds 菜单ID集合
     * @return SysMenu集合
     *
     */
    public List findSysPermissionsByMenuIds(List<Long> menuIds) {
        List<SysMenu> sysMenus = sysMenuRepository.findAllByMenuTypeAndIdInAndStatusNotOrderByMenuSortAsc(MenuType.PERMISSION, menuIds, MenuStatusType.DELETE);
        return sysMenus;
    }

    /**
     * @author 刘路超
     * @修改人 无
     * @详细描述 查询某角色使用的菜单列表
     * @param orgMenuIds 所属组织机构可用菜单ID列表
     * @param userMenuIds 用户可用菜单ID列表
     * @return 菜单树
     */
    public List findSysMenusByMenuIds(List<Long> orgMenuIds, List<Long> userMenuIds) {
        List<Long> useIds = new ArrayList<>();
        for(Long menuId : userMenuIds) {
            if(orgMenuIds.contains(menuId)) {
                useIds.add(menuId);
            }
        }
        List<SysMenu> orgMenus = sysMenuRepository.findAllByMenuTypeAndIdInAndStatusNotOrderByMenuSortAsc(MenuType.MENU, orgMenuIds, MenuStatusType.DELETE);
        return MenuUtil.createMenuTree(orgMenus, useIds);
    }

    /**
     * @author 刘路超
     * @修改人 无
     * @详细描述 查询某角色使用的权限（按钮）列表
     * @param orgMenuIds 所属组织机构可用菜单ID列表
     * @param userMenuIds 用户可用菜单ID列表
     * @return SysMenu属性+isUse属性的map集合
     */
    public List findSysPermissionsByMenuIds(List<Long> orgMenuIds, List<Long> userMenuIds) throws Exception {
        List<Long> useIds = new ArrayList<>();
        for(Long menuId : userMenuIds) {
            if(orgMenuIds.contains(menuId)) {
                useIds.add(menuId);
            }
        }
        List<SysMenu> orgMenus = sysMenuRepository.findAllByMenuTypeAndIdInAndStatusNotOrderByMenuSortAsc(MenuType.PERMISSION, orgMenuIds, MenuStatusType.DELETE);
        List<Map> result = new ArrayList<>();
        for(SysMenu sysMenu : orgMenus) {
            Map map = MenuUtil.bean2map(sysMenu);
            if(useIds.contains(sysMenu.getId())) {
                map.put("isUse", 1);
            }else {
                map.put("isUse", 0);
            }
            result.add(map);
        }
        return result;
    }

    /**
     * @author 刘路超
     * @修改人 无
     * @详细描述 更新角色与菜单关联表
     * @param roleId 角色ID
     * @param menuIds 角色菜单ID列表
     * @return
     */
    @Transactional
    public void updateSysRoleMenuByRoleId(String roleId, List<String> menuIds) {
        try {
            Long rId = Long.parseLong(roleId);
            sysRoleMenuRepository.deleteAllBySysRoleId(rId);
            for(String menuId : menuIds) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setSysRoleId(rId);
                sysRoleMenu.setSysMenuId(Long.parseLong(menuId));
                sysRoleMenuRepository.saveAndFlush(sysRoleMenu);
            }
        }catch (Exception e) {
            throw new BadRequestAlertException("错误的参数类型","","更新失败");
        }
    }

    public void Test() {
        System.out.println("Test");
    }
}
