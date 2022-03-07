package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.table.QSysPost;
import com.ruowei.modules.sys.domain.table.SysPost;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the SysPost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysPostRepository
    extends BaseRepository<Long,SysPost, QSysPost> {
    Optional<SysPost> getOneByPostCodeOrPostName(String postCode, String postName);

    Optional<SysPost> getOneByPostCode(String postCode);

    Optional<SysPost> getOneByPostName(String postName);

    Optional<SysPost> getOneByPostNameAndIdNot(String postName, Long id);
}
