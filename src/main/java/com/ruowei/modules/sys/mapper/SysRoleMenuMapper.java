package com.ruowei.modules.sys.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.modules.sys.domain.SysRoleMenu;
import com.ruowei.modules.sys.pojo.dto.SysRoleMenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the entity {@link SysRoleMenu} and its DTO {@link SysRoleMenuDTO}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysRoleMenuMapper extends EntityMapper<SysRoleMenuDTO, SysRoleMenu> {



    default SysRoleMenu fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenu.setId(id);
        return sysRoleMenu;
    }
}
