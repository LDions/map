package com.ruowei.web.rest;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.*;
import com.ruowei.repository.*;
import com.ruowei.security.UserModel;
import com.ruowei.service.SewEmiService;
import com.ruowei.util.BeanUtil;
import com.ruowei.util.OptionalBooleanBuilder;
import com.ruowei.util.OrderByUtil;
import com.ruowei.util.excel.ExcelExport;
import com.ruowei.web.rest.dto.*;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.CarbonEmiQM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;
import tech.jhipster.web.util.PaginationUtil;

import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
@Api(tags = "数据展示")
@Transactional
public class EmiDataResource {

    private final Logger log = LoggerFactory.getLogger(EmiDataResource.class);

    private final SewEmiService sewEmiService;
    private final SewProcessRepository sewProcessRepository;
    private final GroupRepository groupRepository;
    private final CraftRepository craftRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final EntCraftProcessRepository entCraftProcessRepository;
    private final SewSluRepository sewSluRepository;
    private final SewPotRepository sewPotRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private QEmiData qEmiData = QEmiData.emiData;
    private final QEntCraftData qEntCraftData = QEntCraftData.entCraftData;
    private final JPAQueryFactory queryFactory;

    public EmiDataResource(SewEmiService sewEmiService, SewProcessRepository sewProcessRepository, GroupRepository groupRepository, CraftRepository craftRepository, EnterpriseRepository enterpriseRepository, EntCraftProcessRepository entCraftProcessRepository, SewSluRepository sewSluRepository,
                           SewPotRepository sewPotRepository, JPAQueryFactory jpaQueryFactory, JPAQueryFactory queryFactory) {
        this.sewEmiService = sewEmiService;
        this.sewProcessRepository = sewProcessRepository;
        this.groupRepository = groupRepository;
        this.craftRepository = craftRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.entCraftProcessRepository = entCraftProcessRepository;
        this.sewSluRepository = sewSluRepository;
        this.sewPotRepository = sewPotRepository;
        this.jpaQueryFactory = jpaQueryFactory;
        this.queryFactory = queryFactory;
    }

    /*@GetMapping("/group")
    @ApiOperation(value = "获取集团", notes = "作者：董玉祥")
    public ResponseEntity<List<TestDTO.GroupDTO>> getGroup() {

        List<Group> Groups =groupRepository.findAll();
        List<TestDTO.GroupDTO> groupDTOS = new ArrayList<>();
        for (Group group : Groups) {
            TestDTO.GroupDTO dto = new TestDTO.GroupDTO();
            BeanUtils.copyProperties(group, dto, BeanUtil.getNullPropertyNames(group));
            groupDTOS.add(dto);
        }
        return ResponseEntity.ok().body(groupDTOS);
    }*/

    @GetMapping("/entenrprise")
    @ApiOperation(value = "获取水厂", notes = "作者：董玉祥")
    public ResponseEntity<List<TestDTO>> getEnt() {

        List<TestDTO> testDTOs = new ArrayList<>();
        List<Group> Groups = groupRepository.findAll();
        for (Group group : Groups) {
            TestDTO.GroupDTO dto = new TestDTO.GroupDTO();
            BeanUtils.copyProperties(group, dto, BeanUtil.getNullPropertyNames(group));
            testDTOs.add(dto);
        }
        for (Group g : Groups) {
            List<Enterprise> enterprises = enterpriseRepository.findByGroupCode(g.getGroupCode());
            for (Enterprise enterprise : enterprises) {
                TestDTO.EntDTO dto = new TestDTO.EntDTO();
                BeanUtils.copyProperties(enterprise, dto, BeanUtil.getNullPropertyNames(enterprise));
                testDTOs.add(dto);
            }
        }

        return ResponseEntity.ok().body(testDTOs);
    }

    @GetMapping("/process_period")
    @ApiOperation(value = "获取工艺段", notes = "作者：董玉祥")
    public ResponseEntity<List<TestDTO.CraftDTO>> getCraft(String industryCode) {

        List<Craft> crafts = craftRepository.findByEntCode(industryCode);
        List<TestDTO.CraftDTO> craftDTOS = new ArrayList<>();
        for (Craft craft : crafts) {
            TestDTO.CraftDTO dto = new TestDTO.CraftDTO();
            BeanUtils.copyProperties(craft, dto, BeanUtil.getNullPropertyNames(craft));
            craftDTOS.add(dto);
        }
        return ResponseEntity.ok().body(craftDTOS);
    }

    /*@GetMapping("/meter_data")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiOperation(value = "仪表数据", notes = "作者：董玉祥")
    public ResponseEntity<List<SewProcessDTO>> getCarbonEmiData() {
        List<SewProcess> sewProcessList = sewProcessRepository.findByProcessTypeCodeOrDocumentCodeOrProcessTypeNameOrDayTime(processTypeCode, industryCode, processTypeName,dayTime);
        List<SewProcessDTO> sewProcessDTOS = new ArrayList<>();
        for (SewProcess sewProcess : sewProcessList) {
            SewProcessDTO sewProcessDTO = new SewProcessDTO();
            BeanUtils.copyProperties(sewProcess, sewProcessDTO, BeanUtil.getNullPropertyNames(sewProcess));
            sewProcessDTOS.add(sewProcessDTO);
        }
        return ResponseEntity.ok().body(sewProcessDTOS);
    }*/

    @GetMapping("/getList")
    @ApiOperation(value = "获取列表", notes = "作者：董玉祥")
    public ResponseEntity<List<EntCraftDataDTO>> getList(@ApiParam(value = "水厂编号") @RequestParam String entCode,
                                                         @ApiParam(value = "工艺段编号") @RequestParam(required = false) String craftCode,
                                                         @ApiParam(value = "数据起始时间") @RequestParam(required = false) String time,
                                                         @ApiParam(value = "数据来源") @RequestParam String source,
                                                         Pageable pageable) {

        OptionalBooleanBuilder predicate = new OptionalBooleanBuilder()
            .notEmptyAnd(qEntCraftData.entCode::eq, entCode)
            .notEmptyAnd(qEntCraftData.craftCode::eq, craftCode)
            .notEmptyAnd(qEntCraftData.dayTime::eq, sewEmiService.getInstant(time))
            .notEmptyAnd(qEntCraftData.messageSource::eq, source);

        JPAQuery<EntCraftDataDTO> jpaQuery = jpaQueryFactory
            .select(Projections.bean(EntCraftDataDTO.class,qEntCraftData.id,qEntCraftData.groupName,qEntCraftData.entName,qEntCraftData.craftName,qEntCraftData.dayTime))
            .from(qEntCraftData)
            .where(predicate.build())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize());
        long total = jpaQueryFactory
            .selectFrom(qEntCraftData)
            .where(predicate.build())
            .stream().count();
        List<EntCraftDataDTO> entCraftProcesses = jpaQuery.fetch();
        Page<EntCraftDataDTO> page = new PageImpl<>(jpaQuery.fetch(), pageable, total);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);

        return ResponseEntity.ok().headers(headers).body(entCraftProcesses);
    }

    @GetMapping("/carbon-emission-data/with-pagination")
    @ApiOperation(value = "获取碳排放数据核算列表接口——带分页", notes = "作者：林宏栋")
    public ResponseEntity<List<CarbonEmiDataDTO>> getAllCarbonEmiDataWithPagination(CarbonEmiQM qm,
                                                                                    Pageable pageable,
                                                                                    @ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        BooleanBuilder booleanBuilder = sewEmiService.convertCarbonEmiQuery(qm, qEmiData, userModel);
        JPAQuery<CarbonEmiDataDTO> jpaQuery = jpaQueryFactory
            .select(Projections.bean(CarbonEmiDataDTO.class, qEmiData.documentCode, qEmiData.enterpriseName,
                qEmiData.reporterName, qEmiData.reportTime, qEmiData.accYear, qEmiData.accMonth, qEmiData.industryCode,
                qEmiData.industryName, qEmiData.carbonEmi, qEmiData.carbonDirEmi, qEmiData.carbonIndirEmi, qEmiData.carbonRed))
            .from(qEmiData)
            .where(booleanBuilder)
            .orderBy(OrderByUtil.createOrderSpecifierBy(qEmiData, pageable.getSort()))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize());
        long count = jpaQueryFactory
            .selectFrom(qEmiData)
            .where(booleanBuilder)
            .stream().count();
        Page<CarbonEmiDataDTO> page = new PageImpl<>(jpaQuery.fetch(), pageable, count);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/carbon-emission-data/without-pagination")
    @ApiOperation(value = "获取碳排放数据核算列表接口——不带分页", notes = "作者：林宏栋")
    public ResponseEntity<List<CarbonEmiDataDTO>> getAllCarbonEmiDataWithoutPagination(CarbonEmiQM qm,
                                                                                       Pageable pageable,
                                                                                       @ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        BooleanBuilder booleanBuilder = sewEmiService.convertCarbonEmiQuery(qm, qEmiData, userModel);
        JPAQuery<CarbonEmiDataDTO> jpaQuery = jpaQueryFactory
            .select(Projections.bean(CarbonEmiDataDTO.class, qEmiData.documentCode, qEmiData.enterpriseName,
                qEmiData.reporterName, qEmiData.reportTime, qEmiData.accYear, qEmiData.accMonth, qEmiData.industryCode,
                qEmiData.industryName, qEmiData.carbonEmi, qEmiData.carbonDirEmi, qEmiData.carbonIndirEmi, qEmiData.carbonRed))
            .from(qEmiData)
            .where(booleanBuilder)
            .orderBy(OrderByUtil.createOrderSpecifierBy(qEmiData, pageable.getSort()));
        return ResponseEntity.ok().body(jpaQuery.fetch());
    }

    @GetMapping("/carbon-emission-data/detail")
    @ApiOperation(value = "获取碳排放数据核算详情接口", notes = "作者：林宏栋")
    public ResponseEntity<SewEmiDetailDTO> getCarbonEmiData(@ApiParam(value = "水厂名称", required = true) @RequestParam String documentCode,
                                                            @ApiParam(value = "行业类型") @RequestParam String industryCode) {
        if (1 == 1) {
            SewEmiDetailDTO sewEmiDetailDTO = sewEmiService.convertToSewEmiDetailDtoByDocumentCode(documentCode);
            return ResponseEntity.ok().body(sewEmiDetailDTO);
        } else {
            throw new BadRequestProblem("查询失败", "不存在该行业类型");
        }
    }

    @PostMapping("/carbon-emission-data/exportAll")
    @ApiOperation(value = "碳排放核算数据导出接口", notes = "作者：林宏栋")
    public ResponseEntity<Resource> exportAll(@Valid @RequestBody CarbonEmiQM qm,
                                              Pageable pageable,
                                              @ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        BooleanBuilder booleanBuilder = sewEmiService.convertCarbonEmiQuery(qm, qEmiData, userModel);
        JPAQuery<CarbonEmiDataDTO> jpaQuery = jpaQueryFactory
            .select(Projections.bean(CarbonEmiDataDTO.class, qEmiData.documentCode, qEmiData.enterpriseName,
                qEmiData.reporterName, qEmiData.reportTime, qEmiData.accYear, qEmiData.accMonth, qEmiData.industryCode,
                qEmiData.industryName, qEmiData.carbonEmi, qEmiData.carbonDirEmi, qEmiData.carbonIndirEmi, qEmiData.carbonRed))
            .from(qEmiData)
            .where(booleanBuilder)
            .orderBy(OrderByUtil.createOrderSpecifierBy(qEmiData, pageable.getSort()));
        // 调用生成Excel方法
        byte[] buffer = ExcelExport.createExcel(jpaQuery.fetch());
        if (buffer == null) {
            throw new BadRequestProblem("生成Excel失败", "Excel字节数组为空");
        }
        Resource resource = new ByteArrayResource(buffer);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment;").body(resource);
    }
}
