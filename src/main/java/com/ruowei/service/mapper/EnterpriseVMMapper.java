package com.ruowei.service.mapper;

import com.ruowei.domain.Enterprise;
import com.ruowei.util.mapper.EntityMapper;
import com.ruowei.web.rest.vm.EnterpriseVM;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EnterpriseVMMapper extends EntityMapper<EnterpriseVM, Enterprise> {
}
