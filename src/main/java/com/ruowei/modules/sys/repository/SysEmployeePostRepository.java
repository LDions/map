package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.QSysEmployeePost;
import com.ruowei.modules.sys.domain.SysEmployeePost;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysEmployeePost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysEmployeePostRepository
    extends BaseRepository<Long, SysEmployeePost, QSysEmployeePost> {

}
