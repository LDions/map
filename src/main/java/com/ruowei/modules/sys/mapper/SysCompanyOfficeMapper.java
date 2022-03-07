package com.ruowei.modules.sys.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.modules.sys.domain.SysCompanyOffice;
import com.ruowei.modules.sys.pojo.dto.SysCompanyOfficeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the entity {@link SysCompanyOffice} and its DTO {@link SysCompanyOfficeDTO}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysCompanyOfficeMapper extends EntityMapper<SysCompanyOfficeDTO, SysCompanyOffice> {



    default SysCompanyOffice fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysCompanyOffice sysCompanyOffice = new SysCompanyOffice();
        sysCompanyOffice.setId(id);
        return sysCompanyOffice;
    }
}
