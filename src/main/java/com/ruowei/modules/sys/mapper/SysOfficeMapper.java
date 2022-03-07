package com.ruowei.modules.sys.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.common.pojo.TreeDTO;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.pojo.SysOfficeTreeDTO;
import com.ruowei.modules.sys.pojo.dto.SysOfficeDTO;

import java.util.List;

/**
 * Mapper for the entity {@link SysOffice} and its DTO {@link SysOfficeDTO}.
 *
 * @author 刘东奇
 */
public interface SysOfficeMapper extends EntityMapper<SysOfficeDTO, SysOffice> {

    default SysOffice fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysOffice sysOffice = new SysOffice();
        sysOffice.setId(id);
        return sysOffice;
    }

    /**
     * 组装TreeDTO
     *
     * @param sysOffice
     * @return TreeDTO
     */
    TreeDTO toTreeDTO(SysOffice sysOffice);

    /**
     * 组装TreeDTO
     *
     * @param sysOffice
     * @return TreeDTO
     */
    SysOfficeTreeDTO toSysOfficeTree(SysOffice sysOffice);

    List<TreeDTO> toTreeDTOs(List<SysOffice> sysOfficeList);
}
