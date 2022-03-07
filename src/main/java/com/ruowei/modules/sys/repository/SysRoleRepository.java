package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.table.QSysRole;
import com.ruowei.modules.sys.domain.table.SysRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


/**
 * Spring Data  repository for the SysRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysRoleRepository
    extends BaseRepository<Long, SysRole, QSysRole> {

    Optional<SysRole> getOneByRoleCode(String roleName);

    Optional<SysRole> getOneByRoleName(String roleName);

    Optional<SysRole> getOneByRoleNameAndIdNot(String roleName, Long id);

    Optional<SysRole> getOneByRoleCodeAndIdNot(String roleName, Long id);

    @Query("select r.id from SysRole r where r.roleCode in ?1")
    Set<Long> getRoleIdsByRoleCodes(Set<String> roleCodes);
}
