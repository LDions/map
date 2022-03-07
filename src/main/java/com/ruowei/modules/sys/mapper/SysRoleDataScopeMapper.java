package com.ruowei.modules.sys.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.modules.sys.domain.SysRoleDataScope;
import com.ruowei.modules.sys.pojo.dto.SysRoleDataScopeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the entity {@link SysRoleDataScope} and its DTO {@link SysRoleDataScopeDTO}.
 * @author 刘东奇
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysRoleDataScopeMapper extends EntityMapper<SysRoleDataScopeDTO, SysRoleDataScope> {



    default SysRoleDataScope fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysRoleDataScope sysRoleDataScope = new SysRoleDataScope();
        sysRoleDataScope.setId(id);
        return sysRoleDataScope;
    }
}
