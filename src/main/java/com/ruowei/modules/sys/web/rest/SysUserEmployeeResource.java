package com.ruowei.modules.sys.web.rest;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.ruowei.common.response.PaginationUtil;
import com.ruowei.modules.sys.domain.entity.QSysEmployee;
import com.ruowei.modules.sys.domain.entity.SysEmployee;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import com.ruowei.modules.sys.mapper.SysUserEmployeeMapper;
import com.ruowei.modules.sys.repository.entity.SysEmployeeRepository;
import com.ruowei.modules.sys.service.SysUserEmployeeService;
import com.ruowei.modules.sys.service.query.SysEmployeeQueryService;
import com.ruowei.modules.sys.web.api.SysUserEmployeeApi;
import com.ruowei.modules.sys.web.vm.AssignRoleVM;
import com.ruowei.modules.sys.web.vm.SysEmployeeDetailVM;
import com.ruowei.modules.sys.web.vm.SysEmployeeListVM;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for 员工相关
 * @author 刘东奇
 */
@RestController
@RequestMapping("/api/sys")
@Api(value = "员工相关" ,tags = "员工相关")
public class SysUserEmployeeResource implements SysUserEmployeeApi {

    private final Logger log = LoggerFactory.getLogger(SysUserEmployeeResource.class);

    private final SysUserEmployeeService sysUserEmployeeService;
    private final SysEmployeeQueryService sysEmployeeQueryService;

    private final SysEmployeeRepository sysEmployeeRepository;

    private final SysUserEmployeeMapper sysUserEmployeeMapper;

    public SysUserEmployeeResource(SysUserEmployeeService sysUserEmployeeService, SysEmployeeQueryService sysEmployeeQueryService, SysEmployeeRepository sysEmployeeRepository, SysUserEmployeeMapper sysUserEmployeeMapper) {
        this.sysUserEmployeeService = sysUserEmployeeService;
        this.sysEmployeeQueryService = sysEmployeeQueryService;
        this.sysEmployeeRepository = sysEmployeeRepository;
        this.sysUserEmployeeMapper = sysUserEmployeeMapper;
    }

    /**
     * 分页查询员工数据
     * TODO 暂不支持投影查询，存在优化空间
     * @param sysUserEmployeePredicate
     * @param pageable
     * @return
     * @author 刘东奇
     * @date 2019/11/13
     */
    @Override
    @ApiOperation(value = "查询员工分页", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页】提供分页查询员工数据功能")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id", value = "主键", paramType = "query", dataType="Long"),
        @ApiImplicitParam(name="user.loginCode", value = "登录账号", paramType = "query", dataType="String"),
        @ApiImplicitParam(name="user.userName", value = "用户昵称", paramType = "query", dataType="String"),
        @ApiImplicitParam(name="user.email", value = "电子邮箱", paramType = "query", dataType="String"),
        @ApiImplicitParam(name="user.mobile", value = "手机号码", paramType = "query", dataType="String"),
        @ApiImplicitParam(name="user.phone", value = "办公电话", paramType = "query", dataType="String"),
        @ApiImplicitParam(name="user.userType", value = "用户类型", paramType = "query", dataType="UserType"),
        @ApiImplicitParam(name="lastModifiedDate", value = "更新时间", paramType = "query", dataType="Instant"),
        @ApiImplicitParam(name="user.status", value = "用户状态", paramType = "query", dataType="UserStatusType"),
        @ApiImplicitParam(name="empName", value = "员工姓名", paramType = "query", dataType="String"),
        @ApiImplicitParam(name="office.id", value = "机构ID", paramType = "query", dataType="String"),
        @ApiImplicitParam(name="company.id", value = "公司ID", paramType = "query", dataType="String"),
        @ApiImplicitParam(name="postList.id", value = "岗位ID", paramType = "query", dataType="String")
    })
    @GetMapping("/user-employees")
    public ResponseEntity<List<SysEmployeeListVM>> getAllSysUserEmployees(
        @QuerydslPredicate(root = SysEmployee.class) Predicate sysUserEmployeePredicate, Pageable pageable) {
        log.debug("REST request to 查询员工分页 by : {}", sysUserEmployeePredicate);
        BooleanBuilder booleanBuilder = new BooleanBuilder(sysUserEmployeePredicate);
        booleanBuilder.and(QSysEmployee.sysEmployee.user.status.ne(UserStatusType.DELETE));
        List<SysEmployeeListVM> list = sysEmployeeQueryService.findAll(booleanBuilder,pageable)
            .stream().map(sysUserEmployeeMapper::sysEmployeeToSysEmployeeListVM).collect(Collectors.toList());
        long count = sysEmployeeQueryService.count(booleanBuilder);
        QueryResults<SysEmployeeListVM> queryResults = new QueryResults<>(
            list, (long)
            pageable.getPageSize(),pageable.getOffset(),
            count);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(),queryResults);
        return ResponseEntity.ok().headers(headers).body(list);
    }

    /**
     * 通过员工主键获取员工详情
     *
     * @param id
     * @return
     * @author 刘东奇
     * @date 2019/11/13
     */
    @Override
    @ApiOperation(value = "获取单个员工", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页-用户详情页、用户修改页】提供获取单个员工数据功能")
    @GetMapping("/user-employee/{id}")
    public ResponseEntity<SysEmployeeDetailVM> getSysUserEmployee(@PathVariable Long id) {
        log.debug("REST request to 获取单个员工 : {}", id);
        Optional<SysEmployee> one = sysEmployeeRepository.findById(id);
        SysEmployeeDetailVM result = null;
        if(one.isPresent()){
            result = sysUserEmployeeMapper.sysEmployeeToSysEmployeeDetailVM(one.get());
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * 创建员工
     *
     * @param sysEmployeeDetailVM
     * @return
     * @author 刘东奇
     * @date 2019/11/13
     */
    @Override
    @ApiOperation(value = "创建员工", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页-用户新增页】提供保存单个员工数据功能")
    @PostMapping("/user-employees")
    public ResponseEntity<SysEmployeeDetailVM> createSysUserEmployee(@Valid @RequestBody SysEmployeeDetailVM sysEmployeeDetailVM) throws URISyntaxException {
        log.debug("REST request to 创建员工 : {}", sysEmployeeDetailVM);
        SysEmployee sysEmployee = sysUserEmployeeMapper.sysUserEmployeeDetailVMToSysEmployee(sysEmployeeDetailVM);
        sysEmployee = sysUserEmployeeService.createSysUserEmployee(sysEmployee);
        sysEmployeeDetailVM = sysUserEmployeeMapper.sysEmployeeToSysEmployeeDetailVM(sysEmployee);
        return ResponseEntity.created(new URI("/api/sys-user-employees/" + sysEmployeeDetailVM.getId()))
           .body(sysEmployeeDetailVM);
    }

    /**
     * 修改员工
     *
     * @param sysEmployeeDetailVM
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    @ApiOperation(value = "修改员工", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页-用户修改页】提供修改单个员工数据功能")
    @PutMapping("/user-employees")
    public ResponseEntity<SysEmployeeDetailVM> modifySysUserEmployee(@Valid @RequestBody SysEmployeeDetailVM sysEmployeeDetailVM) {
        log.debug("REST request to 修改员工 : {}", sysEmployeeDetailVM);
        SysEmployee sysEmployee = sysUserEmployeeMapper.sysUserEmployeeDetailVMToSysEmployee(sysEmployeeDetailVM);
        sysEmployee = sysUserEmployeeService.modifySysUserEmployee(sysEmployee);
        sysEmployeeDetailVM = sysUserEmployeeMapper.sysEmployeeToSysEmployeeDetailVM(sysEmployee);
        return ResponseEntity.ok()
            .body(sysEmployeeDetailVM);
    }


    /**
     * 停用员工
     * @author 刘东奇
     * @date 2019/11/16
     */
    @Override
    @ApiOperation(value = "停用员工", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页】提供停用单个员工账号功能")
    @PutMapping("/user-employees/disable")
    public ResponseEntity disableSysEmployee(@Valid @RequestBody Long sysEmployeeId) {
        log.debug("REST request to 停用员工 : {}", sysEmployeeId);
        sysUserEmployeeService.disableSysEmployee(sysEmployeeId);
        return ResponseEntity.ok().build();
    }

    /**
     * 启用员工
     * @author 刘东奇
     * @date 2019/11/9
     */
    @Override
    @ApiOperation(value = "启用员工", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页】提供启用单个员工账号功能")
    @PutMapping("/user-employees/enable")
    public ResponseEntity enableSysEmployee(@Valid @RequestBody Long sysEmployeeId) {
        log.debug("REST request to 启用员工 : {}", sysEmployeeId);
        sysUserEmployeeService.enableSysEmployee(sysEmployeeId);
        return ResponseEntity.ok().build();
    }

    /**
     * 删除员工
     * @author 刘东奇
     * @date 2019/11/2
     */
    @Override
    @ApiOperation(value = "删除员工", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页】提供删除单个员工功能")
    @DeleteMapping("/user-employees/delete/{sysEmployeeId}")
    public ResponseEntity deleteSysEmployee(@PathVariable Long sysEmployeeId) {
        log.debug("REST request to 删除员工 : {}", sysEmployeeId);
        sysUserEmployeeService.deleteSysEmployee(sysEmployeeId);
        return ResponseEntity.ok().build();
    }

    /**
     * 重置员工密码
     * @author 刘东奇
     * @date 2019/11/9
     */
    @Override
    @ApiOperation(value = "重置员工密码", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页】提供重置单个员工密码功能")
    @PutMapping("/user-employees/reset-password")
    public ResponseEntity resetSysEmployeePassword(@Valid @RequestBody Long sysEmployeeId) {
        log.debug("REST request to 重置员工密码 : {}", sysEmployeeId);
        sysUserEmployeeService.resetSysEmployeePassword(sysEmployeeId);
        return ResponseEntity.ok().build();
    }

    /**
     * 员工分配角色
     * @author 刘东奇
     * @date 2019/9/22
     */
    @Override
    @ApiOperation(value = "员工分配角色", notes = "作者：刘东奇</br>"+
        "详细描述：该接口为前端的【用户管理页】提供分配单个员工角色功能")
    @PostMapping("/user-employees/assign-role")
    public ResponseEntity assignRoleToSysEmployee(@Valid @RequestBody AssignRoleVM assignRoleVM) {
        log.debug("REST request to 员工分配角色 : {}", assignRoleVM);
        sysUserEmployeeService.assignRoleToSysEmployee(assignRoleVM.getSysEmployeeId(),assignRoleVM.getRoleList());
        return ResponseEntity.ok().build();
    }
}
