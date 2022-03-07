package com.ruowei.modules.sys.utils;

import com.ruowei.modules.sys.domain.SysMenu;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.*;

public class MenuUtil {
    /**
     * @author 刘路超
     * @修改人 无
     * @详细描述 菜单编码生成规则
     */
    public static String createMenuCode() {
        return "MENU-" + UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    /**
     * 菜单树
     *
     * @param sysMenus 菜单集合
     */
    public static List createMenuTree(List<SysMenu> sysMenus) {
        return createMenuTree(sysMenus, null);
    }

    /**
     * @param sysMenus 包含菜单属性的层级结构集合，下级菜单在children中
     * @param useIds   使用的菜单ID列表
     * @return 菜单属性及是否可用属性的层级集合
     * @author 刘路超
     * @修改人 无
     * @详细描述 菜单树
     */
    public static List createMenuTree(List<SysMenu> sysMenus, List<Long> useIds) {
        // 先构建每个层次的菜单集合
        List<List<SysMenu>> lsits = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            boolean flag = false;
            List<SysMenu> _sysMenus = new ArrayList<>();
            for (SysMenu sysMenu : sysMenus) {
                if (sysMenu.getTreeLevel() == i) {
                    _sysMenus.add(sysMenu);
                    flag = true;
                }
            }
            if (flag) {
                lsits.add(_sysMenus);
            } else {
                break;
            }
        }
        List resultList = new ArrayList();
        Map resultMap;
        // 如果只有一层菜单
        if (lsits.size() == 0) {
            return resultList;
        } else if (lsits.size() == 1) {
            List<SysMenu> sysMenuList = lsits.get(0);
            for (SysMenu sysMenu : sysMenuList) {
                resultMap = new HashMap();
                resultMap.put("name", sysMenu.getMenuName());
                resultMap.put("path", sysMenu.getMenuHref());
                resultMap.put("icon", sysMenu.getMenuIcon());
                resultMap.put("title", sysMenu.getMenuTitle());
                resultMap.put("component", sysMenu.getTreeNames());
                resultMap.put("id", sysMenu.getId().toString());
                resultMap.put("parentId", sysMenu.getParentId() == null ? null : sysMenu.getParentId().toString());
                resultMap.put("status", sysMenu.getStatus());
                resultMap.put("isShow", sysMenu.isIsShow());

                resultMap.put("menuSort", sysMenu.getMenuSort());
                resultMap.put("sysCode", sysMenu.getSysCode());
                resultMap.put("permission", sysMenu.getPermission());
                resultMap.put("menuType", sysMenu.getMenuType());
                resultMap.put("menuCode", sysMenu.getMenuCode());
                resultMap.put("parentIds", sysMenu.getParentIds());
                resultMap.put("treeSort", sysMenu.getTreeSort());
                resultMap.put("treeSorts", sysMenu.getTreeSorts());
                resultMap.put("treeLeaf", sysMenu.isTreeLeaf());
                resultMap.put("treeLevel", sysMenu.getTreeLevel());
                resultMap.put("remarks", sysMenu.getRemarks());

                if (useIds != null) {
                    if (useIds.contains(sysMenu.getId())) {
                        resultMap.put("isUse", 1);
                    } else {
                        resultMap.put("isUse", 0);
                    }
                }
                resultList.add(resultMap);
            }
        } else if (lsits.size() == 2) { // 如果有两层菜单
            List<SysMenu> sysMenuList1 = lsits.get(0);
            List<SysMenu> sysMenuList2 = lsits.get(1);
            for (SysMenu sysMenu1 : sysMenuList1) {
                resultMap = new HashMap();
                resultMap.put("id", sysMenu1.getId().toString());
                resultMap.put("parentId", sysMenu1.getParentId() == null ? null : sysMenu1.getParentId().toString());
                resultMap.put("status", sysMenu1.getStatus());
                resultMap.put("isShow", sysMenu1.isIsShow());
                resultMap.put("name", sysMenu1.getMenuName());
                resultMap.put("path", sysMenu1.getMenuHref());
                resultMap.put("icon", sysMenu1.getMenuIcon());

                resultMap.put("menuSort", sysMenu1.getMenuSort());
                resultMap.put("sysCode", sysMenu1.getSysCode());
                resultMap.put("permission", sysMenu1.getPermission());
                resultMap.put("menuType", sysMenu1.getMenuType());
                resultMap.put("menuCode", sysMenu1.getMenuCode());
                resultMap.put("parentIds", sysMenu1.getParentIds());
                resultMap.put("treeSort", sysMenu1.getTreeSort());
                resultMap.put("treeSorts", sysMenu1.getTreeSorts());
                resultMap.put("treeLeaf", sysMenu1.isTreeLeaf());
                resultMap.put("treeLevel", sysMenu1.getTreeLevel());
                resultMap.put("remarks", sysMenu1.getRemarks());
                if (sysMenu1.isTreeLeaf()) {
                    resultMap.put("title", sysMenu1.getMenuTitle());
                    resultMap.put("component", sysMenu1.getTreeNames());
                }
                List routes = new ArrayList();
                for (SysMenu sysMenu2 : sysMenuList2) {
                    if (sysMenu2.getParentId().equals(sysMenu1.getId())) {
                        Map tMap = new HashMap();
                        tMap.put("name", sysMenu2.getMenuName());
                        tMap.put("path", sysMenu2.getMenuHref());
                        tMap.put("icon", sysMenu2.getMenuIcon());
                        tMap.put("title", sysMenu2.getMenuTitle());
                        tMap.put("component", sysMenu2.getTreeNames());
                        tMap.put("id", sysMenu2.getId().toString());
                        tMap.put("parentId", sysMenu2.getParentId() == null ? null : sysMenu2.getParentId().toString());
                        tMap.put("status", sysMenu2.getStatus());
                        tMap.put("isShow", sysMenu2.isIsShow());

                        tMap.put("menuSort", sysMenu2.getMenuSort());
                        tMap.put("sysCode", sysMenu2.getSysCode());
                        tMap.put("permission", sysMenu2.getPermission());
                        tMap.put("menuType", sysMenu2.getMenuType());
                        tMap.put("menuCode", sysMenu2.getMenuCode());
                        tMap.put("parentIds", sysMenu2.getParentIds());
                        tMap.put("treeSort", sysMenu2.getTreeSort());
                        tMap.put("treeSorts", sysMenu2.getTreeSorts());
                        tMap.put("treeLeaf", sysMenu2.isTreeLeaf());
                        tMap.put("treeLevel", sysMenu2.getTreeLevel());
                        tMap.put("remarks", sysMenu2.getRemarks());
                        if (useIds != null) {
                            if (useIds.contains(sysMenu2.getId())) {
                                tMap.put("isUse", 1);
                            } else {
                                tMap.put("isUse", 0);
                            }
                        }
                        routes.add(tMap);
                    }
                }
                if (!sysMenu1.isTreeLeaf()) {
                    resultMap.put("children", routes);
                }
                if (useIds != null) {
                    if (useIds.contains(sysMenu1.getId())) {
                        resultMap.put("isUse", 1);
                    } else {
                        resultMap.put("isUse", 0);
                    }
                }
                resultList.add(resultMap);
            }
        } else { // 如果有三层及以上菜单
            // 构建返回map
            Map<Long, List> flagMap = new HashMap();
            for (int i = lsits.size() - 1; i >= 0; i--) {
                List<SysMenu> sysMenuList = lsits.get(i);
                for (SysMenu sysMenu : sysMenuList) {
                    Map map = new HashMap();
                    if (i == 0) {
                        // 构建上层routes
                        map.put("name", sysMenu.getMenuName());
                        map.put("path", sysMenu.getMenuHref());
                        map.put("icon", sysMenu.getMenuIcon());
                        if (sysMenu.isTreeLeaf()) {
                            map.put("title", sysMenu.getMenuTitle());
                            map.put("component", sysMenu.getTreeNames());
                        }/*else {
                            map.put("children", flagMap.get(sysMenu.getId()));
                        }*/
                        map.put("children", flagMap.get(sysMenu.getId()));

                        map.put("id", sysMenu.getId().toString());
                        map.put("parentId", sysMenu.getParentId() == null ? null : sysMenu.getParentId().toString());
                        map.put("status", sysMenu.getStatus());
                        map.put("isShow", sysMenu.isIsShow());

                        map.put("menuSort", sysMenu.getMenuSort());
                        map.put("sysCode", sysMenu.getSysCode());
                        map.put("permission", sysMenu.getPermission());
                        map.put("menuType", sysMenu.getMenuType());
                        map.put("menuCode", sysMenu.getMenuCode());
                        map.put("parentIds", sysMenu.getParentIds());
                        map.put("treeSort", sysMenu.getTreeSort());
                        map.put("treeSorts", sysMenu.getTreeSorts());
                        map.put("treeLeaf", sysMenu.isTreeLeaf());
                        map.put("treeLevel", sysMenu.getTreeLevel());
                        map.put("remarks", sysMenu.getRemarks());
                        if (useIds != null) {
                            if (useIds.contains(sysMenu.getId())) {
                                map.put("isUse", 1);
                            } else {
                                map.put("isUse", 0);
                            }
                        }
                        resultList.add(map);
                    } else {
                        // 构建上层routes
                        map.put("name", sysMenu.getMenuName());
                        map.put("path", sysMenu.getMenuHref());
                        map.put("icon", sysMenu.getMenuIcon());
                        if (sysMenu.isTreeLeaf()) {
                            map.put("title", sysMenu.getMenuTitle());
                            map.put("component", sysMenu.getTreeNames());
                        }/*else {
                            map.put("children", flagMap.get(sysMenu.getId()));
                        }*/
                        map.put("children", flagMap.get(sysMenu.getId()));

                        map.put("id", sysMenu.getId().toString());
                        map.put("parentId", sysMenu.getParentId() == null ? null : sysMenu.getParentId().toString());
                        map.put("status", sysMenu.getStatus());
                        map.put("isShow", sysMenu.isIsShow());

                        map.put("menuSort", sysMenu.getMenuSort());
                        map.put("sysCode", sysMenu.getSysCode());
                        map.put("permission", sysMenu.getPermission());
                        map.put("menuType", sysMenu.getMenuType());
                        map.put("menuCode", sysMenu.getMenuCode());
                        map.put("parentIds", sysMenu.getParentIds());
                        map.put("treeSort", sysMenu.getTreeSort());
                        map.put("treeSorts", sysMenu.getTreeSorts());
                        map.put("treeLeaf", sysMenu.isTreeLeaf());
                        map.put("treeLevel", sysMenu.getTreeLevel());
                        map.put("remarks", sysMenu.getRemarks());
                        if (useIds != null) {
                            if (useIds.contains(sysMenu.getId())) {
                                map.put("isUse", 1);
                            } else {
                                map.put("isUse", 0);
                            }
                        }

                        if (sysMenu.getParentId() != null) {
                            if (flagMap.get(sysMenu.getParentId()) != null) {
                                List routes = flagMap.get(sysMenu.getParentId());
                                routes.add(map);
                                flagMap.put(sysMenu.getParentId(), routes);
                            } else {
                                List routes = new ArrayList();
                                routes.add(map);
                                flagMap.put(sysMenu.getParentId(), routes);
                            }
                        }
                    }
                }
            }
        }
        return resultList;
    }

    /**
     * @param bean
     * @return
     * @throws Exception
     * @author 刘路超
     * @修改人 无
     * @详细描述 javaBean转map
     */
    public static Map<Object, Object> bean2map(Object bean) throws Exception {
        Map<Object, Object> map = new HashMap<>();
        BeanInfo info = Introspector.getBeanInfo(bean.getClass(), Object.class);
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            Object key = pd.getName();
            Object value = pd.getReadMethod().invoke(bean);
            map.put(key, value);
        }
        return map;
    }
}
