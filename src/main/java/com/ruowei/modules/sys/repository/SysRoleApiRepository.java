package com.ruowei.modules.sys.repository;

import com.ruowei.modules.sys.domain.SysRoleApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;


/**
 * Spring Data  repository for the SysRoleApi entity.
 */
@Repository
public interface SysRoleApiRepository extends JpaRepository<SysRoleApi, Long> {

    void deleteAllBySysRoleId(Long sysRoleId);

    @Query("select concat(a.requestMethod, ' ', a.url) from SysRoleApi ra left join SysApi a on ra.sysApiId = a.id where ra.sysRoleId in ?1")
    Set<String> getAllApiByRoleIds(Set<Long> roleIds);
}
