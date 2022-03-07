package com.ruowei.modules.sys.mapper;

import com.ruowei.modules.sys.domain.entity.SysEmployee;
import com.ruowei.modules.sys.domain.entity.SysEmployeeOfficePost;
import com.ruowei.modules.sys.domain.entity.SysUser;
import com.ruowei.modules.sys.domain.table.*;
import com.ruowei.modules.sys.web.vm.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapper for the entity {@link SysUserTable} and its DTO {@link SysUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysUserEmployeeMapper {

    @Mappings({
        @Mapping(source = "sysEmployeeDetailVM.userInfo", target = "user"),
        @Mapping(source = "sysEmployeeDetailVM.officeInfo", target = "office"),
        @Mapping(source = "sysEmployeeDetailVM.companyInfo", target = "company"),
        @Mapping(source = "sysEmployeeDetailVM.postInfoList", target = "postList"),
        @Mapping(source = "sysEmployeeDetailVM.officePostInfoList", target = "officePostList")
    })
    SysEmployee sysUserEmployeeDetailVMToSysEmployee(SysEmployeeDetailVM sysEmployeeDetailVM);

    @Mappings({
        @Mapping(source = "sysEmployee.user", target = "userInfo"),
        @Mapping(source = "sysEmployee.office", target = "officeInfo"),
        @Mapping(source = "sysEmployee.company", target = "companyInfo"),
        @Mapping(source = "sysEmployee.postList", target = "postInfoList"),
        @Mapping(source = "sysEmployee.officePostList", target = "officePostInfoList")
    })
    SysEmployeeDetailVM sysEmployeeToSysEmployeeDetailVM(SysEmployee sysEmployee);

    SysCompany sysEmployeeCompanyVMToSysCompany(SysEmployeeCompanyVM sysEmployeeCompanyVM);

    SysOffice sysEmployeeOfficeVMToSysOffice(SysEmployeeOfficeVM sysEmployeeOfficeVM);

    SysPost sysEmployeePostVMToSysPost(SysEmployeePostVM sysEmployeePostVM);

    SysUser sysEmployeeUserVMToSysUser(SysEmployeeUserVM sysEmployeeUserVM);

    SysRole sysUserRoleVMToSysRole(SysUserRoleVM sysUserRoleVM);

    SysEmployeeOfficePost sysEmployeeOfficePostVMToSysEmployeeOfficePost(SysEmployeeOfficePostVM sysEmployeeOfficePostVM);

    SysEmployeeUserVM sysUserToSysEmployeeUserVM(SysUser sysUser);

    SysEmployeeOfficeVM sysOfficeToSysEmployeeOfficeVM(SysOffice sysOffice);

    SysEmployeeCompanyVM sysCompanyToSysEmployeeCompanyVM(SysCompany sysCompany);

    List<SysEmployeePostVM> sysPostToSysEmployeePostVM(List<SysPost> sysPostList);

    @Mappings({
        @Mapping(source = "sysEmployee.user", target = "userInfo"),
        @Mapping(source = "sysEmployee.user.lastModifiedDate", target = "lastModifiedDate"),
        @Mapping(source = "sysEmployee.office", target = "officeInfo"),
        @Mapping(source = "sysEmployee.company", target = "companyInfo"),
        @Mapping(source = "sysEmployee.postList", target = "postInfoList")
    })
    SysEmployeeListVM sysEmployeeToSysEmployeeListVM(SysEmployee sysEmployee);
}
