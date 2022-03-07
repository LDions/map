package com.ruowei.modules.sys.repository;

import com.ruowei.modules.sys.domain.SysApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the SysApi entity.
 */
@Repository
public interface SysApiRepository extends JpaRepository<SysApi, Long> {

    @Query("select a from SysRoleApi r left join SysApi a on r.sysApiId = a.id where r.sysRoleId = ?1")
    List<SysApi> getAllByRoleId(Long roleId);
}
