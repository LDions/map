package com.ruowei.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.*;
import com.ruowei.repository.CraftRepository;
import com.ruowei.repository.EnterpriseRepository;
import com.ruowei.repository.GroupRepository;
import com.ruowei.security.UserModel;
import com.ruowei.util.OptionalBooleanBuilder;
import com.ruowei.web.rest.dto.EnterpriseLocationDTO;
import com.ruowei.web.rest.dto.GroupDTO;
import com.ruowei.web.rest.dto.PlatformDTO;
import com.ruowei.web.rest.dto.UserEnterpriseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liquibase.pro.packaged.G;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
@Api(tags = "平台接口管理")
public class PlatformResource {

    private final Logger log = LoggerFactory.getLogger(PlatformResource.class);
    private final ObjectMapper objectMapper;
    private final JPAQueryFactory jpaQueryFactory;
    private final GroupRepository groupRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final CraftRepository craftRepository;
    private QEnterprise qEnterprise = QEnterprise.enterprise;
    private QGroup qGroup = QGroup.group;
    private QUser qUser = QUser.user;

    public PlatformResource(ObjectMapper objectMapper, JPAQueryFactory jpaQueryFactory, GroupRepository groupRepository, EnterpriseRepository enterpriseRepository, CraftRepository craftRepository) {
        this.objectMapper = objectMapper;
        this.jpaQueryFactory = jpaQueryFactory;
        this.groupRepository = groupRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.craftRepository = craftRepository;
    }

    @PostMapping("/group/enterprise-dropdown")
    @ApiOperation(value = "查询集团下属水厂", notes = "作者：郑昊天")
    public ResponseEntity<Map<String, Long>> getOrganizationNumber(@ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        Map<String, Long> numMap = new HashMap<>();
        // 集团 试点水厂 非试点水厂 数量
        numMap.put("集团数量", groupRepository.count());
        numMap.put("试点水厂数量", enterpriseRepository.count());
        numMap.put("非试点水厂数量", enterpriseRepository.count());
        return ResponseEntity.ok().body(numMap);
    }

    @PostMapping("/platform/locations")
    @ApiOperation(value = "查询水厂位置", notes = "作者：郑昊天")
    public ResponseEntity<List<EnterpriseLocationDTO>> getEnterpriseLocations() {
        List<EnterpriseLocationDTO> result = new ArrayList<>();
        List<Enterprise> tempList = enterpriseRepository.findAll();
        for (Enterprise enterprise : tempList) {
            EnterpriseLocationDTO locationDTO = new EnterpriseLocationDTO();
            locationDTO.setName(enterprise.getName());
            locationDTO.setId(enterprise.getId());
            locationDTO.setIsTry(enterprise.getIsTry());
            locationDTO.setLat(enterprise.getEnterpriseLatitude());
            locationDTO.setLng(enterprise.getEnterpriseLongitude());
            locationDTO.setCraftNumber(craftRepository.countByEntCode(enterprise.getCode()));
            if (groupRepository.findByGroupCode(enterprise.getGroupCode()).isPresent()) {
                locationDTO.setGroupName(groupRepository.findByGroupCode(enterprise.getGroupCode()).get().getGroupName());
            }
            result.add(locationDTO);
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/group/model-number")
    @ApiOperation(value = "查询集团下属水厂", notes = "作者：郑昊天")
    public ResponseEntity<Map<String, Long>> getModelNumber(@ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        Map<String, Long> numMap = new HashMap<>();
        // 集团 试点水厂 非试点水厂 数量
        numMap.put("集团数量", groupRepository.count());
        numMap.put("试点水厂数量", enterpriseRepository.count());
        numMap.put("非试点水厂数量", enterpriseRepository.count());
        return ResponseEntity.ok().body(numMap);
    }


    @GetMapping("/platform/list")
    @ApiOperation(value = "获取当前用户对应的企业列表接口", notes = "作者：张锴")
    public ResponseEntity<PlatformDTO> getEnterpriseDropDown(UserEnterpriseDTO userEnterpriseDTO, Pageable pageable) {
        PlatformDTO platformDTO = new PlatformDTO();
        OptionalBooleanBuilder builder = new OptionalBooleanBuilder()
            .notEmptyAnd(qEnterprise.code::contains, userEnterpriseDTO.getCode())
            .notEmptyAnd(qEnterprise.groupCode::contains, userEnterpriseDTO.getGroupCode());
        OptionalBooleanBuilder builder1 = new OptionalBooleanBuilder()
            .notEmptyAnd(qGroup.groupCode::contains, userEnterpriseDTO.getGroupCode());
        JPAQuery<Group> jpaQuery1 = jpaQueryFactory
            .select(qGroup)
            .from(qGroup)
            .where(builder1.build())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize());
        List<Group> groupResult = jpaQuery1.fetch();
        JPAQuery<Enterprise> jpaQuery = jpaQueryFactory
            .select(qEnterprise)
            .from(qEnterprise)
            .where(builder.build())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize());
        List<Enterprise> result = jpaQuery.fetch();
        platformDTO.setEnterprises(result);
        platformDTO.setGroups(groupResult);
        return ResponseEntity.ok(platformDTO);
    }
}
