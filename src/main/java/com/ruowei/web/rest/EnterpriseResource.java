package com.ruowei.web.rest;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.*;
import com.ruowei.repository.*;
import com.ruowei.security.UserModel;
import com.ruowei.service.mapper.EnterpriseVMMapper;
import com.ruowei.service.mapper.UserVMMapper;
import com.ruowei.util.OptionalBooleanBuilder;
import com.ruowei.web.rest.dto.DropDownDTO;
import com.ruowei.web.rest.dto.UserEnterpriseDTO;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.EnterpriseVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ruowei.config.Constants.DEFAULT_PASSWORD;

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
    private final CraftRepository craftRepository;

    public EnterpriseResource(EnterpriseRepository enterpriseRepository, DistrictRepository districtRepository, EnterpriseVMMapper enterpriseVMMapper, JPAQueryFactory jpaQueryFactory, UserRepository userRepository, UserVMMapper userVMMapper, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, RoleRepository roleRepository, CraftRepository craftRepository) {
        this.enterpriseRepository = enterpriseRepository;
        this.districtRepository = districtRepository;
        this.enterpriseVMMapper = enterpriseVMMapper;
        this.jpaQueryFactory = jpaQueryFactory;
        this.userRepository = userRepository;
        this.userVMMapper = userVMMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.craftRepository = craftRepository;
    }

    @PostMapping("/enterprise")
    @Transactional
    @ApiOperation(value = "新增企业接口", notes = "作者：孙小楠")
    public ResponseEntity<Enterprise> createEnterprise(@Valid @RequestBody EnterpriseVM vm) {
        log.debug("REST request to save Enterprise : {}", vm);
        Enterprise enterprise = enterpriseRepository.save(enterpriseVMMapper.toEntity(vm));
        BeanUtils.copyProperties(vm, enterprise);
        Enterprise save = enterpriseRepository.save(enterprise);
        return ResponseEntity.ok(save);
    }

    @PutMapping("/enterprise")
    @ApiOperation(value = "编辑企业接口", notes = "作者：孙小楠")
    public ResponseEntity<Enterprise> editEnterprise(@Valid @RequestBody Enterprise enterprise) {
        log.debug("REST request to update Enterprise : {}", enterprise);
        if (enterprise.getId() == null) {
            throw new BadRequestProblem("编辑失败", "id不能为空");
        }
        enterpriseRepository
            .getFirstByCodeAndId(enterprise.getCode(), enterprise.getId())
            .ifPresent(so -> {
                throw new BadRequestProblem("编辑失败", "企业编码已存在");
            });
        Enterprise result = enterpriseRepository.save(enterprise);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/enterprise/{id}")
    @ApiOperation(value = "查询企业详情接口", notes = "作者:孙小楠")
    public ResponseEntity<Enterprise> getEnterprise(@PathVariable Long id) {
        log.debug("REST request to get enterprise : {}", id);

        Optional<Enterprise> optional = enterpriseRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(optional);
    }

    @DeleteMapping("/enterprise/{code}")
    @Transactional
    @ApiOperation(value = "删除企业接口", notes = "作者：孙小楠")
    public ResponseEntity<Void> deleteEnterprise(@PathVariable String code) {
        log.debug("REST request to delete Enterprise : {}", code);

        if (userRepository.findByEnterpriseCode(code).isPresent()) {
            throw new BadRequestProblem("删除失败", "企业下存在用户");
        }
        enterpriseRepository.deleteByCode(code);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/enterprise/list")
    @ApiOperation(value = "获取当前用户对应的企业列表接口", notes = "作者：孙小楠")
    public ResponseEntity<List<Enterprise>> getEnterpriseDropDown(UserEnterpriseDTO userEnterpriseDTO, Pageable pageable) {

        OptionalBooleanBuilder builder = new OptionalBooleanBuilder()
            .notEmptyAnd(qEnterprise.code::eq, userEnterpriseDTO.getCode())
            .notEmptyAnd(qEnterprise.groupCode::eq,userEnterpriseDTO.getGroupCode());
        JPAQuery<Enterprise> jpaQuery = jpaQueryFactory
            .select(qEnterprise)
            .from(qEnterprise)
            .where(builder.build())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize());
        List<Enterprise> result = jpaQuery.fetch();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/enterprise/reset/{code}")
    @ApiOperation(value = "重置企业密码接口", notes = "作者：张锴")
    public ResponseEntity<String> resetEnterprisePassword(@PathVariable String code) {
        enterpriseRepository
            .findByCode(code)
            .orElseThrow(() -> {
                throw new BadRequestProblem("重置失败", "该企业不存在");
            });
        User user = userRepository.findByEnterpriseCode(code).orElseThrow(() -> new BadRequestProblem("重置失败", "未找到业主用户"));
        user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        userRepository.save(user);
        return ResponseEntity.ok().body("重置成功");
    }

    @PostMapping("/enterprise/craft-dropdown")
    @ApiOperation(value = "查询水厂下属工艺", notes = "作者：郑昊天")
    public ResponseEntity<List<DropDownDTO>> getCraftDropdown(String enterpriseCode) {
        List<Craft> crafts = craftRepository.findAllByEntCode(enterpriseCode);
        List<DropDownDTO> dropDownList = new ArrayList<>();
        for (Craft craft : crafts) {
            DropDownDTO dropDownDTO = new DropDownDTO();
            dropDownDTO.setId(craft.getId());
            dropDownDTO.setName(craft.getCraftName());
            dropDownList.add(dropDownDTO);
        }
        return ResponseEntity.ok().body(dropDownList);
    }

}

