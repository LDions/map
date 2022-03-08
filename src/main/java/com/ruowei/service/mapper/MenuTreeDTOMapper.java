package com.ruowei.service.mapper;

import com.ruowei.domain.Menu;
import com.ruowei.util.mapper.EntityMapper;
import com.ruowei.web.rest.dto.MenuTreeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuTreeDTOMapper extends EntityMapper<MenuTreeDTO, Menu> {
}
