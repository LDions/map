package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.QSysUserRole;
import com.ruowei.modules.sys.domain.SysUserRole;
import com.ruowei.modules.sys.domain.table.SysRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


/**
 * Spring Data  repository for the SysUserRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysUserRoleRepository extends BaseRepository<Long, SysUserRole, QSysUserRole> {

    List<SysUserRole> findAllBySysUserId(Long userId);

    List<SysUserRole> findAllBySysRoleId(Long roleId);

    @Query("select r.roleCode from SysUserRole ur left join SysRole r on ur.sysRoleId = r.id where ur.sysUserId = ?1")
    Set<String> getAllRoleCodeByUserId(Long userId);

    @Query("select r from SysUserRole ur left join SysRole r on ur.sysRoleId = r.id where ur.sysUserId = ?1")
    Set<SysRole> findAllSysRoleByUserId(Long userId);
}
