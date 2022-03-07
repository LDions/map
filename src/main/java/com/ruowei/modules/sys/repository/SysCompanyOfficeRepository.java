package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.QSysCompanyOffice;
import com.ruowei.modules.sys.domain.SysCompanyOffice;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the SysCompanyOffice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysCompanyOfficeRepository extends BaseRepository<Long, SysCompanyOffice, QSysCompanyOffice> {

    void deleteAllBySysCompanyId(Long sysCompanyId);

    List<SysCompanyOffice> findAllBySysCompanyId(Long sysCompanyId);
}
