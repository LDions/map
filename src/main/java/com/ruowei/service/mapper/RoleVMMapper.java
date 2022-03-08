package com.ruowei.service.mapper;

import com.ruowei.domain.Role;
import com.ruowei.util.mapper.EntityMapper;
import com.ruowei.web.rest.vm.RoleVM;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleVMMapper extends EntityMapper<RoleVM, Role> {
}
