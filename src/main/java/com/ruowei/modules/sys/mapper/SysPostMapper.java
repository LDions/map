package com.ruowei.modules.sys.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.modules.sys.domain.table.SysPost;
import com.ruowei.modules.sys.pojo.dto.SysPostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the entity {@link SysPost} and its DTO {@link SysPostDTO}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysPostMapper extends EntityMapper<SysPostDTO, SysPost> {



    default SysPost fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysPost sysPost = new SysPost();
        sysPost.setId(id);
        return sysPost;
    }
}
