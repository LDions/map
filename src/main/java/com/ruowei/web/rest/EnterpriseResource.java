package com.ruowei.web.rest;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.Enterprise;
import com.ruowei.domain.QEnterprise;
import com.ruowei.domain.QUser;
import com.ruowei.domain.User;
import com.ruowei.repository.*;
import com.ruowei.security.UserModel;
import com.ruowei.service.mapper.EnterpriseVMMapper;
import com.ruowei.service.mapper.UserVMMapper;
import com.ruowei.web.rest.dto.DropDownDTO;
import com.ruowei.web.rest.dto.EnterpriseDTO;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.EnterpriseQM;
import com.ruowei.web.rest.vm.EnterpriseVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @ApiOperation(value = "新增企业接口", notes = "作者：孙小楠")
    public ResponseEntity<Enterprise> createEnterprise(@Valid @RequestBody EnterpriseVM vm) {
        log.debug("REST request to save Enterprise : {}", vm);

        Enterprise enterprise = new Enterprise();
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

        Enterprise result = enterpriseRepository.save(enterprise);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/enterprises")
    @Transactional
    @ApiOperation(value = "获取带分页的企业列表接口", notes = "作者：孙小楠")
    public ResponseEntity<List<EnterpriseDTO>> getAllEnterprises(EnterpriseQM qm, Pageable pageable) {
        log.debug("REST request to get a page of Enterprises : {}", qm);

//        OptionalBooleanBuilder predicate = new OptionalBooleanBuilder()
//            .notEmptyAnd(qEnterprise.code::ne, Constants.SYS_ADMIN);
        List<Enterprise> all = enterpriseRepository.findAll();
        List<EnterpriseDTO> list =
            all.stream().map(enterprise -> {
                EnterpriseDTO enterpriseDTO = new EnterpriseDTO();
                BeanUtils.copyProperties(enterprise, enterpriseDTO);
                return enterpriseDTO;
            }).collect(Collectors.toList());
        Page<EnterpriseDTO> page = new PageImpl<>(list, pageable, list.size());

        List<EnterpriseDTO> content = page.getContent();

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(content);

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

    @GetMapping("/enterprise/drop-down")
    @ApiOperation(value = "获取当前用户对应的企业下拉列表接口", notes = "作者：孙小楠")
    public ResponseEntity<List<DropDownDTO>> getEnterpriseDropDown(@ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        List<DropDownDTO> result = new ArrayList<>();
        if (userModel.getcode() != null) {
            DropDownDTO dto = new DropDownDTO();
            dto.setCode(userModel.getcode());
            dto.setName(userModel.getEnterpriseName());
            result.add(dto);
        }
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
}

