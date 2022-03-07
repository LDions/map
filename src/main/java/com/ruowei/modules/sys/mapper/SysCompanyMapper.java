package com.ruowei.modules.sys.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.modules.sys.domain.table.SysCompany;
import com.ruowei.modules.sys.pojo.dto.SysCompanyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the entity {@link SysCompany} and its DTO {@link SysCompanyDTO}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysCompanyMapper extends EntityMapper<SysCompanyDTO, SysCompany> {


    default SysCompany fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysCompany sysCompany = new SysCompany();
        sysCompany.setId(id);
        return sysCompany;
    }
}
