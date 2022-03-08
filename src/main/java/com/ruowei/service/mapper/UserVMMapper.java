package com.ruowei.service.mapper;

import com.ruowei.domain.User;
import com.ruowei.util.mapper.EntityMapper;
import com.ruowei.web.rest.vm.UserVM;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserVMMapper extends EntityMapper<UserVM, User> {
}

