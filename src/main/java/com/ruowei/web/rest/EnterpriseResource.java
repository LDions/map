package com.ruowei.web.rest;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.config.Constants;
import com.ruowei.domain.*;
import com.ruowei.domain.enumeration.EnterpriseStatusType;
import com.ruowei.domain.enumeration.RoleStatusType;
import com.ruowei.domain.enumeration.UserStatusType;
import com.ruowei.repository.*;
import com.ruowei.security.UserModel;
import com.ruowei.service.mapper.EnterpriseVMMapper;
import com.ruowei.service.mapper.UserVMMapper;
import com.ruowei.util.OptionalBooleanBuilder;
import com.ruowei.util.OrderByUtil;
import com.ruowei.web.rest.dto.DropDownDTO;
import com.ruowei.web.rest.dto.EnterpriseDTO;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.EnterpriseQM;
import com.ruowei.web.rest.vm.EnterpriseVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(tags = "企业管理")
public class EnterpriseResource {

    private final Logger log = LoggerFactory.getLogger(EnterpriseResource.class);
    private final EnterpriseRepository enterpriseRepository;
    private final DistrictRepository districtRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final EnterpriseVMMapper enterpriseVMMapper;
    private final UserRepository userRepository;
    private final UserVMMapper userVMMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private QEnterprise qEnterprise = QEnterprise.enterprise;
    private QUser qUser = QUser.user;

    public EnterpriseResource(EnterpriseRepository enterpriseRepository, DistrictRepository districtRepository, EnterpriseVMMapper enterpriseVMMapper, JPAQueryFactory jpaQueryFactory, UserRepository userRepository, UserVMMapper userVMMapper, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, RoleRepository roleRepository) {
        this.enterpriseRepository = enterpriseRepository;
        this.districtRepository = districtRepository;
        this.enterpriseVMMapper = enterpriseVMMapper;
        this.jpaQueryFactory = jpaQueryFactory;
        this.userRepository = userRepository;
        this.userVMMapper = userVMMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/enterprise")
    @Transactional
    @ApiOperation(value = "新增企业接口", notes = "作者：郑昊天")
    public ResponseEntity<Enterprise> createEnterprise(@Valid @RequestBody EnterpriseVM vm) throws URISyntaxException {
        log.debug("REST request to save Enterprise : {}", vm);
        enterpriseRepository.getFirstByUniCreditCodeAndStatus(vm.getUniCreditCode(), EnterpriseStatusType.NORMAL)
            .ifPresent(so -> {
                throw new BadRequestProblem("新增失败", "统一信用代码已存在");
            });
        Enterprise enterprise = enterpriseRepository.save(enterpriseVMMapper.toEntity(vm));
        if (vm.getUserInfo().getId() != null) {
            throw new BadRequestProblem("新增失败", "新增时ID必须为空");
        }
        userRepository.findOneByLoginAndStatusNot(vm.getUserInfo().getLogin(), UserStatusType.DELETE)
            .ifPresent(so -> {
                throw new BadRequestProblem("新增失败", "用户名已存在");
            });
        User user = userVMMapper.toEntity(vm.getUserInfo());
        user.setPassword(passwordEncoder.encode(Constants.DEFAULT_PASSWORD));
        user.setEnterpriseId(enterprise.getId());
        User saveUser = userRepository.save(user);
        vm.getUserInfo().getRoleIds().forEach(roleId -> {
            Role role = roleRepository.getByCodeAndStatus(roleId, RoleStatusType.NORMAL)
                .orElseThrow(() -> new BadRequestProblem("新增失败", "未找到污水处理厂角色"));
            userRoleRepository.save(new UserRole().userId(saveUser.getId()).roleId(role.getId()));
        });
        return ResponseEntity.created(new URI("/api/enterprise/" + enterprise.getId()))
            .body(enterprise);
    }

    @PutMapping("/enterprise")
    @ApiOperation(value = "编辑企业接口", notes = "作者：郑昊天")
    public ResponseEntity<Enterprise> editEnterprise(@Valid @RequestBody Enterprise enterprise) {
        log.debug("REST request to update Enterprise : {}", enterprise);
        if (enterprise.getId() == null) {
            throw new BadRequestProblem("编辑失败", "id不能为空");
        }
        enterpriseRepository.getFirstByUniCreditCodeAndIdNotAndStatus(enterprise.getUniCreditCode(), enterprise.getId(), EnterpriseStatusType.NORMAL)
            .ifPresent(so -> {
                throw new BadRequestProblem("编辑失败", "统一社会信用代码已存在");
            });
        Enterprise result = enterpriseRepository.save(enterprise);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/enterprises")
    @Transactional
    @ApiOperation(value = "获取带分页的企业列表接口", notes = "作者：郑昊天")
    public ResponseEntity<List<EnterpriseDTO>> getAllEnterprises(EnterpriseQM qm, Pageable pageable) {
        log.debug("REST request to get a page of Enterprises : {}", qm);
        OptionalBooleanBuilder predicate = new OptionalBooleanBuilder()
            .notEmptyAnd(qEnterprise.name::contains, qm.getName())
            .notEmptyAnd(qEnterprise.uniCreditCode::contains, qm.getUniCreditCode())
            .notEmptyAnd(qEnterprise.nature::contains, qm.getNature())
            .notEmptyAnd(qEnterprise.status::ne, EnterpriseStatusType.DELETE);
        List<EnterpriseDTO> list = jpaQueryFactory
            .select(Projections.bean(EnterpriseDTO.class, qEnterprise.id, qEnterprise.name, qEnterprise.nature,
                qEnterprise.uniCreditCode, qEnterprise.legalRepresentative, qEnterprise.businessProvince,
                qEnterprise.businessCity, qEnterprise.businessArea, qEnterprise.businessAddress,
                qEnterprise.contactName, qEnterprise.contactPhone, qEnterprise.remark, qEnterprise.status))
            .from(qEnterprise)
            .where(predicate.build())
            .orderBy(OrderByUtil.createOrderSpecifierBy(qEnterprise, pageable.getSort()))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
        long count = jpaQueryFactory
            .selectFrom(qEnterprise)
            .where(predicate.build())
            .stream().count();
        Page<EnterpriseDTO> page = new PageImpl<>(list, pageable, count);
        List<EnterpriseDTO> content = page.getContent();
        for (EnterpriseDTO enterpriseDTO : content) {
            if (StringUtils.isNotBlank(enterpriseDTO.getBusinessProvince())) {
                districtRepository.findById(Long.valueOf(enterpriseDTO.getBusinessProvince()))
                    .ifPresent(province -> {
                        enterpriseDTO.setBusinessProvince(province.getName());
                    });
            }
            if (StringUtils.isNotBlank(enterpriseDTO.getBusinessCity())) {
                districtRepository.findById(Long.valueOf(enterpriseDTO.getBusinessCity()))
                    .ifPresent(city -> {
                        enterpriseDTO.setBusinessCity(city.getName());
                    });
            }
            if (StringUtils.isNotBlank(enterpriseDTO.getBusinessArea())) {
                districtRepository.findById(Long.valueOf(enterpriseDTO.getBusinessArea()))
                    .ifPresent(area -> {
                        enterpriseDTO.setBusinessArea(area.getName());
                    });
            }
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(content);
    }

    @GetMapping("/enterprise/{id}")
    @ApiOperation(value = "查询企业详情接口", notes = "作者:郑昊天")
    public ResponseEntity<Enterprise> getEnterprise(@PathVariable Long id) {
        Optional<Enterprise> enterprise = enterpriseRepository.findByIdAndStatus(id, EnterpriseStatusType.NORMAL);
        return ResponseUtil.wrapOrNotFound(enterprise);
    }

    @DeleteMapping("/enterprise/{id}")
    @Transactional
    @ApiOperation(value = "删除企业接口", notes = "作者：郑昊天")
    public ResponseEntity<Void> deleteEnterprise(@PathVariable Long id) {
        log.debug("REST request to delete Enterprise : {}", id);
        User user = userRepository.findByEnterpriseId(id)
            .orElseThrow(() -> new BadRequestProblem("删除失败", "企业用户不存在"));
        jpaQueryFactory
            .update(qEnterprise)
            .set(qEnterprise.status, EnterpriseStatusType.DELETE)
            .where(qEnterprise.id.eq(id))
            .execute();
        userRoleRepository.deleteAllByUserId(user.getId());
        jpaQueryFactory
            .update(qUser)
            .set(qUser.status, UserStatusType.DELETE)
            .where(qUser.id.eq(user.getId()))
            .execute();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/enterprise/drop-down")
    @ApiOperation(value = "获取当前用户对应的企业下拉列表接口", notes = "作者：林宏栋")
    public ResponseEntity<List<DropDownDTO>> getEnterpriseDropDown(@ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        List<DropDownDTO> result = new ArrayList<>();
        if (userModel.getEnterpriseId() != null) {
            DropDownDTO dto = new DropDownDTO();
            dto.setId(userModel.getEnterpriseId());
            dto.setName(userModel.getEnterpriseName());
            result.add(dto);
        }else {
            enterpriseRepository.findAllByStatus(EnterpriseStatusType.NORMAL).forEach(enterprise -> {
                DropDownDTO dto = new DropDownDTO();
                dto.setId(enterprise.getId());
                dto.setName(enterprise.getName());
                result.add(dto);
            });
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/enterprise/province/{id}")
    @ApiOperation(value = "获取企业所在省份接口", notes = "作者：林宏栋")
    public ResponseEntity<DropDownDTO> getProvinceNameOfEnterprise(@PathVariable Long id) {
        Enterprise enterprise = enterpriseRepository.findByIdAndStatus(id, EnterpriseStatusType.NORMAL)
            .orElseThrow(() -> new BadRequestProblem("查询失败", "企业不存在"));
        DropDownDTO result = new DropDownDTO();
        result.setCode(enterprise.getBusinessProvince());
        districtRepository.findById(Long.valueOf(enterprise.getBusinessProvince()))
            .ifPresent(province -> {
                result.setName(province.getName());
            });
        return ResponseEntity.ok().body(result);
    }
}
