package com.ruowei.web.rest;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.config.Constants;
import com.ruowei.domain.*;
import com.ruowei.domain.enumeration.EnterpriseStatusType;
import com.ruowei.domain.enumeration.RoleStatusType;
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
    @ApiOperation(value = "新增企业接口", notes = "作者：孙小楠")
    public ResponseEntity<Enterprise> createEnterprise(@Valid @RequestBody EnterpriseVM vm) throws URISyntaxException {
        log.debug("REST request to save Enterprise : {}", vm);

        return ResponseEntity.ok().build();
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

        return ResponseEntity.ok().build();
    }

    @GetMapping("/enterprise/{id}")
    @ApiOperation(value = "查询企业详情接口", notes = "作者:孙小楠")
    public ResponseEntity<Enterprise> getEnterprise(@PathVariable Long id) {

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/enterprise/{id}")
    @Transactional
    @ApiOperation(value = "删除企业接口", notes = "作者：孙小楠")
    public ResponseEntity<Void> deleteEnterprise(@PathVariable Long id) {
        log.debug("REST request to delete Enterprise : {}", id);
        User user = userRepository.findByEnterpriseId(id)
            .orElseThrow(() -> new BadRequestProblem("删除失败", "企业用户不存在"));

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/enterprise/drop-down")
    @ApiOperation(value = "获取当前用户对应的企业下拉列表接口", notes = "作者：孙小楠")
    public ResponseEntity<List<DropDownDTO>> getEnterpriseDropDown(@ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        List<DropDownDTO> result = new ArrayList<>();
        if (userModel.getEnterpriseId() != null) {
            DropDownDTO dto = new DropDownDTO();
            dto.setId(userModel.getEnterpriseId());
            dto.setName(userModel.getEnterpriseName());
            result.add(dto);
        }else {

            }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/enterprise/province/{id}")
    @ApiOperation(value = "获取企业所在省份接口", notes = "作者：孙小楠")
    public ResponseEntity<DropDownDTO> getProvinceNameOfEnterprise(@PathVariable Long id) {

        DropDownDTO result = new DropDownDTO();

        return ResponseEntity.ok().body(result);
    }
}
