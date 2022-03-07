package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import com.ruowei.modules.sys.domain.table.QSysUserTable;
import com.ruowei.modules.sys.domain.table.SysUserTable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the SysUser entity.
 *
 * @author 刘东奇
 */
@Repository
public interface SysUserRepository
    extends BaseRepository<Long, SysUserTable, QSysUserTable> {

    /**
     * 通过usercode倒叙找第一个Sysuser
     *
     * @author 刘东奇
     * @date 2019/9/25
     */
    Optional<QSysUserTable> findFirstByUserCodeNotNullOrderByUserCodeDesc();


    Optional<QSysUserTable> findOneByLoginCode(String login);

    List<QSysUserTable> findAllByIdInAndStatusNot(List<Long> ids, UserStatusType userStatusType);
}
