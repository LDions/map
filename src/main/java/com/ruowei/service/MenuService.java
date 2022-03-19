package com.ruowei.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.*;
import com.ruowei.domain.enumeration.MenuStatusType;
import com.ruowei.domain.enumeration.MenuType;
import com.ruowei.domain.enumeration.RoleStatusType;
import com.ruowei.domain.enumeration.SysType;
import com.ruowei.service.dto.SimpleMenuTreeDTO;
import com.ruowei.service.mapper.MenuTreeDTOMapper;
import com.ruowei.service.mapper.SimpleMenuTreeDTOMapper;
import com.ruowei.web.rest.dto.MenuTreeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MenuService {

    private final JPAQueryFactory jpaQueryFactory;
    private final MenuTreeDTOMapper menuTreeDTOMapper;
    private final SimpleMenuTreeDTOMapper simpleMenuTreeDTOMapper;
    private QMenu qMenu = QMenu.menu;
    private QUserRole qUserRole = QUserRole.userRole;
    private QRole qRole = QRole.role;
    private QRoleMenu qRoleMenu = QRoleMenu.roleMenu;

    public MenuService(JPAQueryFactory jpaQueryFactory, MenuTreeDTOMapper menuTreeDTOMapper, SimpleMenuTreeDTOMapper simpleMenuTreeDTOMapper) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.menuTreeDTOMapper = menuTreeDTOMapper;
        this.simpleMenuTreeDTOMapper = simpleMenuTreeDTOMapper;
    }

    /**
     * 查询归属于某类系统的完整菜单树
     * @param sysType
     * @return
     */
    public List<MenuTreeDTO> findAllMenusAsTree(SysType sysType) {
        return jpaQueryFactory
            .selectFrom(qMenu)
            .where(qMenu.treeLevel.eq(1)
                .and(qMenu.status.eq(MenuStatusType.NORMAL))
                .and(qMenu.sysCode.eq(sysType))
            )
            .orderBy(qMenu.menuSort.asc())
            .stream()
            .map(this::getMenuNodesByRoot)
            .collect(Collectors.toList());
    }

    private MenuTreeDTO getMenuNodesByRoot(Menu menu) {
        MenuTreeDTO menuTreeDTO = menuTreeDTOMapper.toDto(menu);
        // 找到该节点下的所有节点
        List<Menu> menuList = jpaQueryFactory
            .selectFrom(qMenu)
            .where(qMenu.status.eq(MenuStatusType.NORMAL).and(qMenu.parentIds.isNotNull()))
            .stream()
            .filter(m -> Arrays.asList(m.getParentIds().split(",")).contains(String.valueOf(menu.getId())))
            .collect(Collectors.toList());
        HashMap<Long, List<MenuTreeDTO>> childrenMap = new HashMap<>();
        // 遍历节点，给每个节点找到父节点
        for (Menu childMenu : menuList) {
            List<MenuTreeDTO> list = childrenMap.get(childMenu.getParentId());
            if(list == null){
                list = new ArrayList<MenuTreeDTO>();
                childrenMap.put(childMenu.getParentId(), list);
            }
            MenuTreeDTO childMenuTreeDTO = menuTreeDTOMapper.toDto(childMenu);
            list.add(childMenuTreeDTO);
        }
        recursiveTree(menuTreeDTO, childrenMap);
        return menuTreeDTO;
    }

    private void recursiveTree(MenuTreeDTO menuTreeDTO, HashMap<Long ,List<MenuTreeDTO>> childrenMap){
        List<MenuTreeDTO> list = childrenMap.get(menuTreeDTO.getId());
        menuTreeDTO.setChildren(list);
        if(list != null){
            // 说明本节点不是叶子节点
            for(MenuTreeDTO childMenuTreeDTO : list){
                recursiveTree(childMenuTreeDTO, childrenMap);
            }
        }
    }

    public List<MenuTreeDTO> getCurrentUserMenus(SysType sysType, Long userId) {
        return jpaQueryFactory
            .select(qMenu)
            .from(qMenu)
            .leftJoin(qRoleMenu)
            .on(qMenu.id.eq(qRoleMenu.menuId))
            .leftJoin(qUserRole)
            .on(qRoleMenu.roleId.eq(qUserRole.roleId))
            .leftJoin(qRole)
            .on(qUserRole.roleId.eq(qRole.id))
            .where(qUserRole.userId.eq(userId)
                .and(qMenu.status.eq(MenuStatusType.NORMAL))
                .and(qMenu.sysCode.eq(sysType))
                .and(qMenu.treeLevel.eq(1))
                .and(qMenu.menuType.eq(MenuType.MENU))
            )
            .distinct()
            .stream()
            .sorted(Comparator.comparing(Menu::getMenuSort))
            .map(menu -> getMenuNodesByRootAndUserId(menu, userId))
            .collect(Collectors.toList());
    }

    private MenuTreeDTO getMenuNodesByRootAndUserId(Menu menu, Long userId) {
        MenuTreeDTO menuTreeDTO = menuTreeDTOMapper.toDto(menu);
        // 找到该节点下的所有节点
        List<Menu> menuList = jpaQueryFactory
            .select(qMenu)
            .from(qMenu)
            .leftJoin(qRoleMenu)
            .on(qMenu.id.eq(qRoleMenu.menuId))
            .leftJoin(qUserRole)
            .on(qRoleMenu.roleId.eq(qUserRole.roleId))
            .leftJoin(qRole)
            .on(qUserRole.roleId.eq(qRole.id))
            .where(qUserRole.userId.eq(userId)
                .and(qMenu.status.eq(MenuStatusType.NORMAL))
                .and(qMenu.parentIds.isNotNull())
                .and(qMenu.menuType.eq(MenuType.MENU))
            )
            .stream()
            .distinct()
            .filter(m -> Arrays.asList(m.getParentIds().split(",")).contains(String.valueOf(menu.getId())))
            .sorted(Comparator.comparing(Menu::getMenuSort))
            .collect(Collectors.toList());
        HashMap<Long, List<MenuTreeDTO>> childrenMap = new HashMap<>();
        // 遍历节点，给每个节点找到父节点
        for (Menu childMenu : menuList) {
            List<MenuTreeDTO> list = childrenMap.get(childMenu.getParentId());
            if(list == null){
                list = new ArrayList<MenuTreeDTO>();
                childrenMap.put(childMenu.getParentId(), list);
            }
            MenuTreeDTO childMenuTreeDTO = menuTreeDTOMapper.toDto(childMenu);
            list.add(childMenuTreeDTO);
        }
        recursiveTree(menuTreeDTO, childrenMap);
        return menuTreeDTO;
    }

    public List<Menu> getCurrentUserPermissions(SysType sysType, Long userId) {
        return jpaQueryFactory
            .select(qMenu)
            .from(qMenu)
            .leftJoin(qRoleMenu)
            .on(qMenu.id.eq(qRoleMenu.menuId))
            .leftJoin(qUserRole)
            .on(qRoleMenu.roleId.eq(qUserRole.roleId))
            .leftJoin(qRole)
            .on(qUserRole.roleId.eq(qRole.id))
            .where((qUserRole.userId.eq(userId))
                .and(qMenu.status.eq(MenuStatusType.NORMAL))
                .and(qMenu.sysCode.eq(sysType))
                .and(qMenu.menuType.eq(MenuType.PERMISSION))
            )
            .distinct()
            .fetch();
    }

    @Transactional
    public List<SimpleMenuTreeDTO> getCurrentUserSimpleMenus(SysType sysType, Long userId) {
        return jpaQueryFactory
            .select(qMenu)
            .from(qMenu)
            .leftJoin(qRoleMenu)
            .on(qMenu.id.eq(qRoleMenu.menuId))
            .leftJoin(qUserRole)
            .on(qRoleMenu.roleId.eq(qUserRole.roleId))
            .leftJoin(qRole)
            .on(qUserRole.roleId.eq(qRole.id))
            .where((qUserRole.userId.eq(userId))
                .and(qMenu.status.eq(MenuStatusType.NORMAL))
                .and(qMenu.sysCode.eq(sysType))
                .and(qMenu.treeLevel.eq(1))
                .and(qMenu.menuType.eq(MenuType.MENU))
            )
            .distinct()
            .stream()
            .sorted(Comparator.comparing(Menu::getMenuSort))
            .map(menu -> getSimpleMenuNodesByRootAndUserId(menu, userId))
            .collect(Collectors.toList());
    }

    private SimpleMenuTreeDTO getSimpleMenuNodesByRootAndUserId(Menu menu, Long userId) {
        SimpleMenuTreeDTO menuTreeDTO = simpleMenuTreeDTOMapper.toDto(menu);
        // 找到该节点下的所有节点
        List<Menu> menuList = jpaQueryFactory
            .select(qMenu)
            .from(qMenu)
            .leftJoin(qRoleMenu)
            .on(qMenu.id.eq(qRoleMenu.menuId))
            .leftJoin(qUserRole)
            .on(qRoleMenu.roleId.eq(qUserRole.roleId))
            .leftJoin(qRole)
            .on(qUserRole.roleId.eq(qRole.id))
            .where((qUserRole.userId.eq(userId))
                .and(qMenu.status.eq(MenuStatusType.NORMAL))
                .and(qMenu.parentIds.isNotNull())
                .and(qMenu.menuType.eq(MenuType.MENU))
            )
            .stream()
            .distinct()
            .filter(m -> Arrays.asList(m.getParentIds().split(",")).contains(String.valueOf(menu.getId())))
            .sorted(Comparator.comparing(Menu::getMenuSort))
            .collect(Collectors.toList());
        HashMap<Long, List<SimpleMenuTreeDTO>> childrenMap = new HashMap<>();
        // 遍历节点，给每个节点找到父节点
        for (Menu childMenu : menuList) {
            List<SimpleMenuTreeDTO> list = childrenMap.get(childMenu.getParentId());
            if(list == null){
                list = new ArrayList<SimpleMenuTreeDTO>();
                childrenMap.put(childMenu.getParentId(), list);
            }
            SimpleMenuTreeDTO childMenuTreeDTO = simpleMenuTreeDTOMapper.toDto(childMenu);
            list.add(childMenuTreeDTO);
        }
        recursiveSimpleTree(menuTreeDTO, childrenMap);
        return menuTreeDTO;
    }

    private void recursiveSimpleTree(SimpleMenuTreeDTO menuTreeDTO, HashMap<Long ,List<SimpleMenuTreeDTO>> childrenMap){
        List<SimpleMenuTreeDTO> list = childrenMap.get(menuTreeDTO.getId());
        menuTreeDTO.setChildren(list);
        if(list != null){
            // 说明本节点不是叶子节点
            for(SimpleMenuTreeDTO childMenuTreeDTO : list){
                recursiveSimpleTree(childMenuTreeDTO, childrenMap);
            }
        }
    }

}
