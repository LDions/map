package com.ruowei.modules.sys.repository.relationship;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.relationship.QSysEmployeeOfficePostRelationship;
import com.ruowei.modules.sys.domain.relationship.SysEmployeeOfficePostRelationship;
import com.ruowei.modules.sys.domain.relationship.SysEmployeeOfficePostRelationship;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysRole entity.
 * @author 刘东奇
 */
@SuppressWarnings("unused")
@Repository
public interface SysEmployeeOfficePostRelationshipRepository
    extends BaseRepository<Long, SysEmployeeOfficePostRelationship, QSysEmployeeOfficePostRelationship> {

}
