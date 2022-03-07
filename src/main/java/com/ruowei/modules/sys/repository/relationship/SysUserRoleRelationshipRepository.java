package com.ruowei.modules.sys.repository.relationship;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.relationship.QSysUserRoleRelationship;
import com.ruowei.modules.sys.domain.relationship.SysUserRoleRelationship;
import com.ruowei.modules.sys.domain.relationship.SysUserRoleRelationship;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysRole entity.
 * @author 刘东奇
 */
@SuppressWarnings("unused")
@Repository
public interface SysUserRoleRelationshipRepository
    extends BaseRepository<Long, SysUserRoleRelationship, QSysUserRoleRelationship> {

}
