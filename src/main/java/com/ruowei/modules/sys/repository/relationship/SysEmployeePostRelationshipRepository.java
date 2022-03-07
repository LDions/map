package com.ruowei.modules.sys.repository.relationship;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.relationship.QSysEmployeePostRelationship;
import com.ruowei.modules.sys.domain.relationship.SysEmployeePostRelationship;
import com.ruowei.modules.sys.domain.relationship.SysEmployeePostRelationship;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysRole entity.
 * @author 刘东奇
 */
@SuppressWarnings("unused")
@Repository
public interface SysEmployeePostRelationshipRepository
    extends BaseRepository<Long, SysEmployeePostRelationship, QSysEmployeePostRelationship> {

}
