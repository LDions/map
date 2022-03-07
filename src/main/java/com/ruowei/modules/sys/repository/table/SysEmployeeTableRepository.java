package com.ruowei.modules.sys.repository.table;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.table.QSysEmployeeTable;
import com.ruowei.modules.sys.domain.table.QSysUserTable;
import com.ruowei.modules.sys.domain.table.SysEmployeeTable;
import com.ruowei.modules.sys.domain.table.SysUserTable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the SysUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysEmployeeTableRepository
    extends BaseRepository<Long, SysEmployeeTable, QSysEmployeeTable> {
}
