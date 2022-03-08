package com.ruowei.service.mapper;

import com.ruowei.domain.Menu;
import com.ruowei.service.dto.SimpleMenuTreeDTO;
import com.ruowei.util.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SimpleMenuTreeDTOMapper extends EntityMapper<SimpleMenuTreeDTO, Menu> {
}
