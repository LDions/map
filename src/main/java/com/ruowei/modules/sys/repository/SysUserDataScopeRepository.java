package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.QSysUserDataScope;
import com.ruowei.modules.sys.domain.SysUserDataScope;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysUserDataScope entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysUserDataScopeRepository
    extends BaseRepository<Long, SysUserDataScope, QSysUserDataScope> {

}
