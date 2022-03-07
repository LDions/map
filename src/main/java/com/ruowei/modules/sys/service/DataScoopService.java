package com.ruowei.modules.sys.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberPath;
import com.ruowei.modules.sys.domain.SysRoleDataScope;
import com.ruowei.modules.sys.domain.enumeration.ControlType;
import com.ruowei.modules.sys.domain.enumeration.DataScopeType;
import com.ruowei.modules.sys.domain.table.SysRole;
import com.ruowei.modules.sys.repository.SysRoleDataScopeRepository;
import com.ruowei.modules.sys.repository.SysUserRoleRepository;
import com.ruowei.security.SecurityUtils;
import com.ruowei.security.UserModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DataScoopService {

    private final SysUserRoleRepository sysUserRoleRepository;
    private final SysRoleDataScopeRepository sysRoleDataScopeRepository;

    public DataScoopService(SysUserRoleRepository sysUserRoleRepository, SysRoleDataScopeRepository sysRoleDataScopeRepository) {
        this.sysUserRoleRepository = sysUserRoleRepository;
        this.sysRoleDataScopeRepository = sysRoleDataScopeRepository;
    }

    private Set<SysRoleDataScope> getRoleDataScopes(Set<SysRole> roles) {
        Set<Long> roleIds = roles.stream()
            .filter(sysRole -> sysRole.getDataScope() == DataScopeType.CUSTOM)
            .map(SysRole::getId)
            .collect(Collectors.toSet());
        return sysRoleDataScopeRepository.findAllBySysRoleIdIn(roleIds);
    }

    private Set<Long> getCtrlDatas(Set<SysRoleDataScope> roleDataScopes, ControlType controlType) {
        return roleDataScopes.stream()
            .filter(roleDataScope -> roleDataScope.getCtrlType() == controlType)
            .map(roleDataScope -> Long.parseLong(roleDataScope.getCtrlData()))
            .collect(Collectors.toSet());
    }

    /**
     * 添加数据权限（querydsl）
     *
     * @param companyId  公司ID字段
     * @param officeId   部门ID字段
     * @param employeeId 员工ID字段
     * @return 数据权限查询条件
     */
    public com.querydsl.core.types.Predicate addFilter(NumberPath<Long> companyId,
                                                       NumberPath<Long> officeId,
                                                       NumberPath<Long> employeeId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        Optional<UserModel> userModelOpt = SecurityUtils.getCurrentUserModel();
        if (!userModelOpt.isPresent()) {
            return booleanBuilder;
        }
        Set<SysRole> roles = sysUserRoleRepository.findAllSysRoleByUserId(userModelOpt.get().getId());
        Set<DataScopeType> dataScopes = roles.stream().map(SysRole::getDataScope).collect(Collectors.toSet());
        if (dataScopes.isEmpty() || dataScopes.contains(DataScopeType.ALL)) {
            return booleanBuilder;
        } else if (dataScopes.contains(DataScopeType.CUSTOM)) {
            Set<SysRoleDataScope> roleDataScopes = getRoleDataScopes(roles);
            Set<Long> companyIds = getCtrlDatas(roleDataScopes, ControlType.COMPANY);
            Set<Long> officeIds = getCtrlDatas(roleDataScopes, ControlType.OFFICE);
            booleanBuilder.andAnyOf(companyId.in(companyIds), officeId.in(officeIds));
            return booleanBuilder;
        } else {
            return booleanBuilder.and(employeeId.eq(userModelOpt.get().getId()));
        }
    }

    /**
     * 添加数据权限（jpa specification）
     *
     * @param <E> root类
     * @return 数据权限查询条件
     */
    public <E> Specification<E> addFilter() {
        return (Specification<E>) (root, query, cb) -> {
            Optional<UserModel> userModelOpt = SecurityUtils.getCurrentUserModel();
            if (!userModelOpt.isPresent()) {
                return null;
            }
            Set<SysRole> roles = sysUserRoleRepository.findAllSysRoleByUserId(userModelOpt.get().getId());
            Set<DataScopeType> dataScopes = roles.stream().map(SysRole::getDataScope).collect(Collectors.toSet());
            if (dataScopes.isEmpty() || dataScopes.contains(DataScopeType.ALL)) {
                return null;
            } else if (dataScopes.contains(DataScopeType.CUSTOM)) {
                Set<SysRoleDataScope> roleDataScopes = getRoleDataScopes(roles);
                Set<Long> companyIds = getCtrlDatas(roleDataScopes, ControlType.COMPANY);
                Set<Long> officeIds = getCtrlDatas(roleDataScopes, ControlType.OFFICE);
                List<Predicate> predicates = new ArrayList<>();
                if (!companyIds.isEmpty()) {
                    predicates.add(root.get("companyId").in(companyIds));
                }
                if (!officeIds.isEmpty()) {
                    predicates.add(root.get("officeId").in(officeIds));
                }
                return cb.or(predicates.toArray(new Predicate[0]));
            } else {
                return cb.equal(root.get("employeeId"), userModelOpt.get().getId());
            }
        };
    }
}
