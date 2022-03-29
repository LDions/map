package com.ruowei.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.Enterprise;
import com.ruowei.repository.CraftRepository;
import com.ruowei.repository.EnterpriseRepository;
import com.ruowei.repository.GroupRepository;
import com.ruowei.security.UserModel;
import com.ruowei.web.rest.dto.EnterpriseLocationDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
            result.add(locationDTO);
        }
        return ResponseEntity.ok().body(result);
    }
}
