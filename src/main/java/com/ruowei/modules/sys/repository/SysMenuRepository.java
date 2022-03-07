package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.QSysMenu;
import com.ruowei.modules.sys.domain.SysMenu;
import com.ruowei.modules.sys.domain.enumeration.MenuStatusType;
import com.ruowei.modules.sys.domain.enumeration.MenuType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the SysMenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysMenuRepository
    extends BaseRepository<Long, SysMenu, QSysMenu> {

    List<SysMenu> findAllByMenuTypeAndStatusNotOrderByMenuSortAsc(MenuType menuType, MenuStatusType menuStatusType);
    List<SysMenu> findAllByIdIsNotNullAndStatusNotOrderByMenuSortAsc(MenuStatusType menuStatusType);

    List<SysMenu> findAllByIdOrParentIdsContains(Long menuId1, Long menuId2);

    List<SysMenu> findAllByMenuTypeAndIdInAndStatusNotOrderByMenuSortAsc(MenuType menuType, List<Long> menuIds, MenuStatusType menuStatusType);

    Page<SysMenu> findAllByTreeLevelAndStatusNotOrderByMenuSortAsc(Integer level, MenuStatusType menuStatusType, Pageable pageable);

    Page<SysMenu> findAllByParentIdAndStatusNotOrderByMenuSortAsc(Long parentId, MenuStatusType menuStatusType, Pageable pageable);

    Page<SysMenu> findAllByMenuNameContainsAndStatusNotOrderByMenuSortAsc(String menuName, MenuStatusType menuStatusType, Pageable pageable);

    List<SysMenu> findAllByParentIdAndStatus(Long pMenuId, MenuStatusType menuStatusType);

    List<SysMenu> findAllByParentIdsContains(Long menuId2);

}
