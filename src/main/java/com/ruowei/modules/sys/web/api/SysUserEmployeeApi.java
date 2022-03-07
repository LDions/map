package com.ruowei.modules.sys.web.api;

import com.querydsl.core.types.Predicate;
import com.ruowei.modules.sys.web.vm.AssignRoleVM;
import com.ruowei.modules.sys.web.vm.SysEmployeeDetailVM;
import com.ruowei.modules.sys.web.vm.SysEmployeeListVM;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author 刘东奇
 * @date 2019/11/9
 */
public interface SysUserEmployeeApi {
    /**
     * 通过员工主键获取员工详情
     * @author 刘东奇
     * @date 2019/11/13
     * @param id
     * @return
     */
    ResponseEntity<SysEmployeeDetailVM> getSysUserEmployee(@PathVariable Long id);

    /**
     * 分页查询员工数据
     * @author 刘东奇
     * @date 2019/11/13
     * @param sysUserEmployeeListPredicate
     * @param pageable
     * @return
     */
    ResponseEntity<List<SysEmployeeListVM>> getAllSysUserEmployees(
        @QuerydslPredicate(root = SysEmployeeListVM.class) Predicate sysUserEmployeeListPredicate,
        Pageable pageable);

    /**
     * 创建员工
     * @author 刘东奇
     * @date 2019/11/13
     * @param sysEmployeeDetail
     * @return
     */
    ResponseEntity<SysEmployeeDetailVM> createSysUserEmployee(@Valid @RequestBody SysEmployeeDetailVM sysEmployeeDetail) throws URISyntaxException;

    /**
     * 修改员工
     * @author 刘东奇
     * @date 2019/11/14
     * @param sysEmployee
     * @return
     */
    ResponseEntity<SysEmployeeDetailVM> modifySysUserEmployee(@Valid @RequestBody SysEmployeeDetailVM sysEmployee);

    /**
     * 停用员工
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @return
     */
    ResponseEntity disableSysEmployee(Long sysEmployeeId);

    /**
     * 启用用户
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @return
     */
    ResponseEntity enableSysEmployee(Long sysEmployeeId);

    /**
     * 删除员工
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @return
     */
    ResponseEntity deleteSysEmployee(Long sysEmployeeId);

    /**
     * 重置员工密码
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @return
     */
    ResponseEntity resetSysEmployeePassword(Long sysEmployeeId);

    /**
     * 给员工分配角色
     * @author 刘东奇
     * @date 2019/11/16
     * @param assignRoleVM
     * @return
     */
    ResponseEntity assignRoleToSysEmployee(@Valid @RequestBody AssignRoleVM assignRoleVM);
}
