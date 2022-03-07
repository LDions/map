package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.QSysRoleDataScope;
import com.ruowei.modules.sys.domain.SysRoleDataScope;
import org.springframework.stereotype.Repository;

import java.util.Set;


/**
 * Spring Data  repository for the SysRoleDataScope entity.
 */
@Repository
public interface SysRoleDataScopeRepository extends BaseRepository<Long, SysRoleDataScope, QSysRoleDataScope> {

    Set<SysRoleDataScope> findAllBySysRoleIdIn(Set<Long> roleIds);
}
