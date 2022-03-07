package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.QSysRoleMenu;
import com.ruowei.modules.sys.domain.SysRoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the SysRoleMenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysRoleMenuRepository
    extends BaseRepository<Long,SysRoleMenu, QSysRoleMenu> {
    List<SysRoleMenu> findAllBySysRoleIdIn(List<Long> roleIds);

    void deleteAllBySysRoleId(Long sysRoleId);
}
