package com.ruowei.modules.sys.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.lang.ObjectUtils;
import com.ruowei.common.lang.StringUtils;
import com.ruowei.common.service.BaseService;
import com.ruowei.modules.sys.domain.entity.SysEmployee;
import com.ruowei.modules.sys.domain.entity.SysEmployeeOfficePost;
import com.ruowei.modules.sys.domain.entity.SysUser;
import com.ruowei.modules.sys.domain.enumeration.EmployeeStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserType;
import com.ruowei.modules.sys.domain.relationship.*;
import com.ruowei.modules.sys.domain.table.*;
import com.ruowei.modules.sys.repository.entity.SysEmployeeRepository;
import com.ruowei.modules.sys.repository.relationship.SysEmployeeOfficePostRelationshipRepository;
import com.ruowei.modules.sys.repository.relationship.SysEmployeePostRelationshipRepository;
import com.ruowei.modules.sys.repository.relationship.SysUserRoleRelationshipRepository;
import com.ruowei.modules.sys.repository.table.SysEmployeeTableRepository;
import com.ruowei.modules.sys.repository.table.SysUserTableRepository;
import com.ruowei.modules.sys.service.api.SysUserEmployeeApi;
import com.ruowei.modules.sys.service.util.SysEmployeeUtil;
import com.ruowei.modules.sys.service.util.SysUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;

/**
 * @author 刘东奇
 * @date 2019/11/8
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserEmployeeService extends BaseService implements SysUserEmployeeApi {

    private final Logger log = LoggerFactory.getLogger(SysUserEmployeeService.class);

    private final SysCompanyService sysCompanyService;
    private final SysOfficeService sysOfficeService;

    private final SysUserTableRepository sysUserTableRepository;
    private final SysUserRoleRelationshipRepository sysUserRoleRelationshipRepository;
    private final SysEmployeePostRelationshipRepository sysEmployeePostRelationshipRepository;
    private final SysEmployeeOfficePostRelationshipRepository sysEmployeeOfficePostRelationshipRepository;
    private final SysEmployeeRepository sysEmployeeRepository;
    /**
     * 密码加密器
     */
    private final PasswordEncoder passwordEncoder;

    private final static String DEFAULT_PASSWORD = "123456";

    public SysUserEmployeeService(SysCompanyService sysCompanyService,
                                  SysOfficeService sysOfficeService,
                                  SysUserTableRepository sysUserTableRepository,
                                  SysUserRoleRelationshipRepository sysUserRoleRelationshipRepository,
                                  SysEmployeePostRelationshipRepository sysEmployeePostRelationshipRepository,
                                  SysEmployeeOfficePostRelationshipRepository sysEmployeeOfficePostRelationshipRepository,
                                  SysEmployeeRepository sysEmployeeRepository,
                                  PasswordEncoder passwordEncoder) {
        this.sysCompanyService = sysCompanyService;
        this.sysOfficeService = sysOfficeService;
        this.sysUserTableRepository = sysUserTableRepository;
        this.sysUserRoleRelationshipRepository = sysUserRoleRelationshipRepository;
        this.sysEmployeePostRelationshipRepository = sysEmployeePostRelationshipRepository;
        this.sysEmployeeOfficePostRelationshipRepository = sysEmployeeOfficePostRelationshipRepository;
        this.sysEmployeeRepository = sysEmployeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 新增员工
     *
     * @param sysEmployee
     * @return
     * @author 刘东奇
     * @date 2019/11/8
     */
    @Override
    public SysEmployee createSysUserEmployee(SysEmployee sysEmployee) {
        // 判断机构是否有效存在
        SysOffice sysOffice = sysOfficeService.checkOfficeExistsById(sysEmployee.getOffice().getId());
        sysEmployee.setOffice(sysOffice);
        // 判断公司是否有效存在
        SysCompany sysCompany = sysCompanyService.checkCompanyExistsById(sysEmployee.getCompany().getId());
        sysEmployee.setCompany(sysCompany);
        // 判断登录ID、手机号是否重复
        this.checkSysUserExists(
            null,
            sysEmployee.getUser().getLoginCode(),
            sysEmployee.getUser().getMobile(),
            sysEmployee.getUser().getEmail()
            );

        //员工信息
        sysEmployee.setEmpCode(SysEmployeeUtil.generateEmpCode(sysEmployee.getOffice().getId().toString(),sysEmployee.getOffice().getOfficeCode()));
        sysEmployee.setStatus(EmployeeStatusType.NORMAL);
        sysEmployee.setCompanyName(sysEmployee.getCompany().getCompanyName());
        sysEmployee.setOfficeName(sysEmployee.getOffice().getOfficeName());
        //用户信息
        sysEmployee.getUser().setPassword((passwordEncoder.encode(DEFAULT_PASSWORD)));
        sysEmployee.getUser().setUserCode(SysUserUtil.generateUserCode());
        sysEmployee.getUser().setUserType(UserType.EMPLOYEE);
        sysEmployee.getUser().setStatus(UserStatusType.NORMAL);
        sysEmployee.getUser().setRefName(sysEmployee.getEmpName());
        sysEmployee.getUser().setEmployee(sysEmployee);
        //员工机构职务信息
        for(SysEmployeeOfficePost info: sysEmployee.getOfficePostList()){
            info.setEmployee(sysEmployee);
        }
        sysEmployeeRepository.saveAndFlush(sysEmployee);
        return sysEmployee;
    }

    /**
     * 修改员工
     *
     * @param newSysEmployee
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    public SysEmployee modifySysUserEmployee(SysEmployee newSysEmployee) {
        // 判断登录ID、手机号、电子邮箱、员工编号是否重复
        this.checkSysUserExists(
            newSysEmployee.getId(),
            newSysEmployee.getUser().getLoginCode(),
            newSysEmployee.getUser().getMobile(),
            newSysEmployee.getUser().getEmail());
        // 判断员工是否存在
        Assert.isTrue(sysEmployeeRepository.existsById(newSysEmployee.getId()),
            ErrorMessageUtils.getNotFoundMessage(
                "员工", ObjectUtils.toString(newSysEmployee.getId()))
        );

        //1、更新员工表数据
        updateSysEmployeeTable(newSysEmployee);
        //2、更新用户表数据
        if(newSysEmployee.getUser()!=null){
            //不支持修改密码
            newSysEmployee.getUser().setPassword(null);
            //自动查询主键
            Long sysUserId = this.getSysUserIdBySysEmployeeId(newSysEmployee.getId());
            newSysEmployee.getUser().setId(sysUserId);
            updateSysUserTable(newSysEmployee.getUser(),newSysEmployee.getEmpName());
            //3、更新用户角色关系表数据
            updateSysUserRoleRelationship(sysUserId,newSysEmployee.getUser().getRoleList());
        }
        //4、更新员工所在岗位关系表数据
        updateSysEmployeePostRelationship(newSysEmployee.getId(),newSysEmployee.getPostList());
        //5、更新员工附属机构及岗位关系表数据
        updateSysEmployeeOfficePostRelationship(newSysEmployee.getId(),newSysEmployee.getOfficePostList());
        return sysEmployeeRepository.findById(newSysEmployee.getId()).get();
    }

    /**
     * 更新员工表
     * @author 刘东奇
     * @date 2020/1/2
     * @param newSysEmployee
     * @return
     */
    @Override
    public void updateSysEmployeeTable(SysEmployee newSysEmployee){
        QSysEmployeeTable qSysEmployeeTable = QSysEmployeeTable.sysEmployeeTable;
        JPAUpdateClause sysEmployeeTableUpdateClause = this.queryFactory.update(qSysEmployeeTable);
        // 判断机构是否有效存在
        if(newSysEmployee.getOffice() !=null){
            SysOffice sysOffice = sysOfficeService.checkOfficeExistsById(newSysEmployee.getOffice().getId());
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.sysOfficeId,sysOffice.getId());
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.officeName,sysOffice.getOfficeName());
        }
        // 判断公司是否有效存在
        if(newSysEmployee.getCompany() != null){
            SysCompany sysCompany = sysCompanyService.checkCompanyExistsById(newSysEmployee.getCompany().getId());
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.sysCompanyId,sysCompany.getId());
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.companyName,sysCompany.getCompanyName());
        }
        //判断员工编号
        if(StringUtils.isNotEmpty(newSysEmployee.getEmpCode())){
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.empCode,newSysEmployee.getEmpCode());
        }
        //判断员工姓名
        if(StringUtils.isNotEmpty(newSysEmployee.getEmpName())){
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.empName,newSysEmployee.getEmpName());
        }
        //判断员工英文名
        if(StringUtils.isNotEmpty(newSysEmployee.getEmpNameEn())){
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.empNameEn,newSysEmployee.getEmpNameEn());
        }
        //判断备注信息
        if(StringUtils.isNotEmpty(newSysEmployee.getRemarks())){
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.remarks,newSysEmployee.getRemarks());
        }
        //判断状态
        if(newSysEmployee.getStatus()!=null){
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.status,newSysEmployee.getStatus());
        }
        if(!sysEmployeeTableUpdateClause.isEmpty()){
            sysEmployeeTableUpdateClause.where(qSysEmployeeTable.id.eq(newSysEmployee.getId()));
            sysEmployeeTableUpdateClause.execute();
        }
    }

    /**
     * 更新用户表
     * @author 刘东奇
     * @date 2020/1/2
     * @param newSysUser
     * @param empName
     */
    @Override
    public void updateSysUserTable(SysUser newSysUser, String empName){
        QSysUserTable qSysUserTable = QSysUserTable.sysUserTable;
        JPAUpdateClause sysUserTableUpdateClause = this.queryFactory.update(qSysUserTable);
        //用户编号
        if(StringUtils.isNotEmpty(newSysUser.getUserCode())){
            sysUserTableUpdateClause.set(qSysUserTable.userCode,newSysUser.getUserCode());
        }
        //登录账号
        if(StringUtils.isNotEmpty(newSysUser.getLoginCode())){
            sysUserTableUpdateClause.set(qSysUserTable.loginCode,newSysUser.getLoginCode());
        }
        //用户昵称
        if(StringUtils.isNotEmpty(newSysUser.getUserName())){
            sysUserTableUpdateClause.set(qSysUserTable.userName,newSysUser.getUserName());
        }
        //密码
        if(StringUtils.isNotEmpty(newSysUser.getPassword())){
            sysUserTableUpdateClause.set(qSysUserTable.password,passwordEncoder.encode(newSysUser.getPassword()));
        }
        //电子邮箱
        if(StringUtils.isNotEmpty(newSysUser.getEmail())){
            sysUserTableUpdateClause.set(qSysUserTable.email,newSysUser.getEmail());
        }
        //手机号
        if(StringUtils.isNotEmpty(newSysUser.getMobile())){
            sysUserTableUpdateClause.set(qSysUserTable.mobile,newSysUser.getMobile());
        }
        //办公电话
        if(StringUtils.isNotEmpty(newSysUser.getPhone())){
            sysUserTableUpdateClause.set(qSysUserTable.phone,newSysUser.getPhone());
        }
        //用户性别
        if(newSysUser.getSex()!=null){
            sysUserTableUpdateClause.set(qSysUserTable.sex,newSysUser.getSex());
        }
        //头像相对路径
        if(StringUtils.isNotEmpty(newSysUser.getAvatar())){
            sysUserTableUpdateClause.set(qSysUserTable.avatar,newSysUser.getAvatar());
        }
        //个性签名
        if(StringUtils.isNotEmpty(newSysUser.getSign())){
            sysUserTableUpdateClause.set(qSysUserTable.sign,newSysUser.getSign());
        }
        //绑定的微信号
        if(StringUtils.isNotEmpty(newSysUser.getSign())){
            sysUserTableUpdateClause.set(qSysUserTable.wxOpenid,newSysUser.getWxOpenid());
        }
        //绑定的手机MID
        if(StringUtils.isNotEmpty(newSysUser.getMobileImei())){
            sysUserTableUpdateClause.set(qSysUserTable.mobileImei,newSysUser.getMobileImei());
        }
        //用户类型
        if(newSysUser.getUserType()!=null){
            sysUserTableUpdateClause.set(qSysUserTable.userType,newSysUser.getUserType());
        }
        //用户类型引用姓名
        if(StringUtils.isNotEmpty(empName)){
            sysUserTableUpdateClause.set(qSysUserTable.refName,empName);
        }else if(StringUtils.isNotEmpty(newSysUser.getRefName())) {
            sysUserTableUpdateClause.set(qSysUserTable.refName,newSysUser.getRefName());
        }
        //最后登录IP
        if(StringUtils.isNotEmpty(newSysUser.getLastLoginIp())){
            sysUserTableUpdateClause.set(qSysUserTable.lastLoginIp,newSysUser.getLastLoginIp());
        }
        //最后登录时间
        if(newSysUser.getLastLoginDate()!=null){
            sysUserTableUpdateClause.set(qSysUserTable.lastLoginDate,newSysUser.getLastLoginDate());
        }
        //冻结时间
        if(newSysUser.getFreezeDate()!=null){
            sysUserTableUpdateClause.set(qSysUserTable.freezeDate,newSysUser.getFreezeDate());
        }
        //冻结原因
        if(StringUtils.isNotEmpty(newSysUser.getFreezeCause())){
            sysUserTableUpdateClause.set(qSysUserTable.freezeCause,newSysUser.getFreezeCause());
        }
        //用户权重，用于排序（降序）
        if(newSysUser.getUserSort()!=null){
            sysUserTableUpdateClause.set(qSysUserTable.userSort,newSysUser.getUserSort());
        }
        //用户状态
        if(newSysUser.getStatus()!=null){
            sysUserTableUpdateClause.set(qSysUserTable.status,newSysUser.getStatus());
        }
        //备注信息
        if(StringUtils.isNotEmpty(newSysUser.getRemarks())){
            sysUserTableUpdateClause.set(qSysUserTable.remarks,newSysUser.getRemarks());
        }
        //是否激活
        if(newSysUser.getActivated()!=null){
            sysUserTableUpdateClause.set(qSysUserTable.activated,newSysUser.getActivated());
        }
        //激活秘钥
        if(StringUtils.isNotEmpty(newSysUser.getActivationKey())){
            sysUserTableUpdateClause.set(qSysUserTable.activationKey,newSysUser.getActivationKey());
        }
        //重置秘钥
        if(StringUtils.isNotEmpty(newSysUser.getResetKey())){
            sysUserTableUpdateClause.set(qSysUserTable.resetKey,newSysUser.getResetKey());
        }
        //重置时间
        if(newSysUser.getResetDate()!=null){
            sysUserTableUpdateClause.set(qSysUserTable.resetDate,newSysUser.getResetDate());
        }
        if(!sysUserTableUpdateClause.isEmpty()){
            sysUserTableUpdateClause.where(qSysUserTable.id.eq(newSysUser.getId()));
            sysUserTableUpdateClause. execute();
        }
    }

    /**
     * 更新员工职位关系表
     * @author 刘东奇
     * @date 2020/1/2
     * @param sysEmployeeId
     * @param postList
     */
    @Override
    public void updateSysEmployeePostRelationship(Long sysEmployeeId, List<SysPost> postList){
        if(postList!=null){
            //先删除
            QSysEmployeePostRelationship qSysEmployeePostRelationship = QSysEmployeePostRelationship.sysEmployeePostRelationship;
            sysEmployeePostRelationshipRepository.deleteAll(qSysEmployeePostRelationship.sysEmployeeId.eq(sysEmployeeId));
            //后新增
            for(SysPost post:postList){
                SysEmployeePostRelationship employeePostRelationship = new SysEmployeePostRelationship();
                employeePostRelationship.setSysPostId(post.getId());
                employeePostRelationship.setSysEmployeeId(sysEmployeeId);
                sysEmployeePostRelationshipRepository.saveAndFlush(employeePostRelationship);
            }

        }
    }

    /**
     * 更新员工机构职位关系表
     * @author 刘东奇
     * @date 2020/1/2
     * @param sysEmployeeId
     * @param employeeOfficePostList
     */
    @Override
    public void updateSysEmployeeOfficePostRelationship(Long sysEmployeeId, List<SysEmployeeOfficePost> employeeOfficePostList){
        if(employeeOfficePostList!=null){
            //先删除
            QSysEmployeeOfficePostRelationship qSysEmployeeOfficePostRelationship = QSysEmployeeOfficePostRelationship.sysEmployeeOfficePostRelationship;
            sysEmployeeOfficePostRelationshipRepository.deleteAll(qSysEmployeeOfficePostRelationship.sysEmployeeId.eq(sysEmployeeId));
            //后新增
            for(SysEmployeeOfficePost employeeOfficePost:employeeOfficePostList){
                SysEmployeeOfficePostRelationship employeeOfficePostRelationship = new SysEmployeeOfficePostRelationship();
                employeeOfficePostRelationship.setSysEmployeeId(sysEmployeeId);
                employeeOfficePostRelationship.setSysOfficeId(employeeOfficePost.getOffice().getId());
                employeeOfficePostRelationship.setSysPostId(employeeOfficePost.getPost().getId());
                sysEmployeeOfficePostRelationshipRepository.saveAndFlush(employeeOfficePostRelationship);
            }
        }
    }

    /**
     * 判断用户是否已存在（登录ID、手机号、邮箱是否被占用）
     * 如果存在，抛异常
     *
     * @param employeeId 所更新员工的主键，如果传表示是更新操作，否则是新增操作
     * @param loginCode
     * @param mobile
     * @param email
     *
     * @return
     * @author 刘东奇
     * @date 2019/11/8
     */
    @Override
    public void checkSysUserExists(Long employeeId,String loginCode, String mobile, String email) {
        // 创建用户
        // 判断登录id、电话是否重复
        QSysUserTable qSysUserTable = QSysUserTable.sysUserTable;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        HashMap<String,String> map= new HashMap<String,String>(3);
        if(StringUtils.isNotEmpty(loginCode)){
            booleanBuilder.or( qSysUserTable.loginCode.eq(loginCode));
            map.put("登录ID", loginCode);
        }
        if(StringUtils.isNotEmpty(mobile)){
            booleanBuilder.or(qSysUserTable.mobile.eq(mobile));
            map.put("手机号", mobile);
        }
        if(StringUtils.isNotEmpty(email)){
            booleanBuilder.or(qSysUserTable.email.eq(email));
            map.put("电子邮箱", email);
        }
        if(map.size()==0){
            return;
        }else if(employeeId != null){
            //curId不为空，表示是更新用户操作，且curId表示更新用户的主键
            booleanBuilder.and(qSysUserTable.refCode.castToNum(Long.class).ne(employeeId));
        }
        Assert.isTrue(!this.sysUserTableRepository.exists(booleanBuilder),
            ErrorMessageUtils.getAlreadyExistMessageByOrMap("用户",map));
    }

    /**
     * 根据员工ID获取用户ID
     *
     * @param id
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    public Long getSysUserIdBySysEmployeeId(Long id) {
        QSysUserTable qSysUser = QSysUserTable.sysUserTable;
        JPAQuery<Long> jpaQuery = this.queryFactory.select(qSysUser.id).from(qSysUser)
            .where(qSysUser.refCode.castToNum(Long.class).eq(id));
        return jpaQuery.fetchOne();
    }
    /**
     * 停用员工
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     */
    @Override
    public void disableSysEmployee(Long sysEmployeeId) {
        QSysUserTable qSysUserTable = QSysUserTable.sysUserTable;
        this.queryFactory.update(qSysUserTable).set(qSysUserTable.status, UserStatusType.DISABLE)
            .where(qSysUserTable.refCode.castToNum(Long.class).eq(sysEmployeeId)).execute();
    }

    /**
     * 启用用户
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @return
     */
    @Override
    public void enableSysEmployee(Long sysEmployeeId) {
        QSysUserTable qSysUserTable = QSysUserTable.sysUserTable;
        this.queryFactory.update(qSysUserTable).set(qSysUserTable.status, UserStatusType.NORMAL)
            .where(qSysUserTable.refCode.castToNum(Long.class).eq(sysEmployeeId)).execute();
    }

    /**
     * 删除员工
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @return
     */
    @Override
    public void deleteSysEmployee(Long sysEmployeeId) {
        QSysUserTable qSysUserTable = QSysUserTable.sysUserTable;
        this.queryFactory.update(qSysUserTable).set(qSysUserTable.status, UserStatusType.DELETE)
            .where(qSysUserTable.refCode.castToNum(Long.class).eq(sysEmployeeId)).execute();

        QSysEmployeeTable qSysEmployeeTable = QSysEmployeeTable.sysEmployeeTable;
        this.queryFactory.update(qSysEmployeeTable).set(qSysEmployeeTable.status, EmployeeStatusType.DELETE)
            .where(qSysEmployeeTable.id.eq(sysEmployeeId)).execute();
    }

    /**
     * 重置员工密码
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @return
     */
    @Override
    public void resetSysEmployeePassword(Long sysEmployeeId) {
        QSysUserTable qSysUserTable = QSysUserTable.sysUserTable;
        this.queryFactory.update(qSysUserTable).set(qSysUserTable.password,passwordEncoder.encode(DEFAULT_PASSWORD))
            .where(qSysUserTable.refCode.castToNum(Long.class).eq(sysEmployeeId)).execute();
    }

    /**
     * 给员工分配角色
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @param roleList
     * @return
     */
    @Override
    public void assignRoleToSysEmployee(Long sysEmployeeId, List<SysRole> roleList) {
        Long sysUserId = this.getSysUserIdBySysEmployeeId(sysEmployeeId);
        this.updateSysUserRoleRelationship(sysUserId,roleList);
    }

    /**
     * 更新用户角色关系表
     * @author 刘东奇
     * @date 2020/1/2
     * @param sysUserId
     * @param roleList
     */
    @Override
    public void updateSysUserRoleRelationship(Long sysUserId, List<SysRole> roleList){
        if(sysUserId!=null && roleList!=null){
            //先删除
            QSysUserRoleRelationship qSysUserRoleRelationship = QSysUserRoleRelationship.sysUserRoleRelationship;
            sysUserRoleRelationshipRepository.deleteAll(qSysUserRoleRelationship.sysUserId.eq(sysUserId));
            //后新增
            for(SysRole role:roleList){
                SysUserRoleRelationship userRoleRelationship = new SysUserRoleRelationship();
                userRoleRelationship.setSysRoleId(role.getId());
                userRoleRelationship.setSysUserId(sysUserId);
                sysUserRoleRelationshipRepository.saveAndFlush(userRoleRelationship);
            }
        }
    }
}
