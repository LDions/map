package com.ruowei.modules.sys.repository.table;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.table.QSysUserTable;
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
public interface SysUserTableRepository
    extends BaseRepository<Long, SysUserTable, QSysUserTable> {

    String USERS_BY_LOGIN_CODE_CACHE = "usersByLoginCode";
    String USERS_BY_EMAIL_CACHE = "usersByEmail";

    /**
     * 根据激活密钥寻找用户
     * @param activationKey
     * @return
     */
    Optional<SysUserTable> findOneByActivationKey(String activationKey);

    /**
     * 根据重置密钥寻找用户
     * @param resetKey
     * @return
     */
    Optional<SysUserTable> findOneByResetKey(String resetKey);

    /**
     * 根据邮箱寻找用户
     * @param email
     * @return
     */
    Optional<SysUserTable> findOneByEmailIgnoreCase(String email);

    /**
     * 根据登录ID寻找用户
     * @param loginCode
     * @return
     */
    Optional<SysUserTable> findOneByLoginCode(String loginCode);

    /**
     * 根据登录ID寻找用户及其权限
     * TODO 有必要替换SysUserTable为SysUser，把关系从table中剔除
     * @param loginCode
     * @return
     */
    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_LOGIN_CODE_CACHE)
    Optional<SysUserTable> findOneWithAuthoritiesByLoginCode(String loginCode);

    /**
     * 根据邮箱寻找用户及其权限
     * TODO 有必要替换SysUserTable为SysUser，把关系从table中剔除
     * @param email
     * @return
     */
    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
    Optional<SysUserTable> findOneWithAuthoritiesByEmailIgnoreCase(String email);

    /**
     * 查询登录ID不是XX的用户
     * @param pageable
     * @param loginCode
     * @return
     */
    Page<SysUserTable> findAllByLoginCodeNot(Pageable pageable, String loginCode);
}
