package com.ruowei.web.rest;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.*;
import com.ruowei.repository.*;
import com.ruowei.service.SewEmiService;
import com.ruowei.util.*;
import com.ruowei.web.rest.dto.*;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api")
@Api(tags = "数据展示")
@Transactional
public class DataDisplayResource {

    private final Logger log = LoggerFactory.getLogger(DataDisplayResource.class);

    private final SewEmiService sewEmiService;
    private final SewProcessRepository sewProcessRepository;
    private final GroupRepository groupRepository;
    private final CraftRepository craftRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final SewSluRepository sewSluRepository;
    private final SewPotRepository sewPotRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final EmiDataRepository emiDataRepository;
    private final SelectUtil selectUtil;
    private QEmiData qEmiData = QEmiData.emiData;
    private QGroup qGroup = QGroup.group;
    private QEnterprise qEnterprise = QEnterprise.enterprise;
    private QCraft qCraft = QCraft.craft;
    private QSewProcess qSewProcess = QSewProcess.sewProcess;
    private QSewPot qSewPot = QSewPot.sewPot;
    private QSewSlu qSewSlu = QSewSlu.sewSlu;
    private QSewMeter qSewMeter = QSewMeter.sewMeter;
    private final JPAQueryFactory queryFactory;

    public DataDisplayResource(SewEmiService sewEmiService, SewProcessRepository sewProcessRepository, GroupRepository groupRepository, CraftRepository craftRepository, EnterpriseRepository enterpriseRepository, SewSluRepository sewSluRepository,
                               SewPotRepository sewPotRepository, JPAQueryFactory jpaQueryFactory, EmiDataRepository emiDataRepository, SelectUtil selectUtil, JPAQueryFactory queryFactory) {
        this.sewEmiService = sewEmiService;
        this.sewProcessRepository = sewProcessRepository;
        this.groupRepository = groupRepository;
        this.craftRepository = craftRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.sewSluRepository = sewSluRepository;
        this.sewPotRepository = sewPotRepository;
        this.jpaQueryFactory = jpaQueryFactory;
        this.emiDataRepository = emiDataRepository;
        this.selectUtil = selectUtil;
        this.queryFactory = queryFactory;
    }

    @GetMapping("/entenrprise")
    @ApiOperation(value = "获取水厂", notes = "作者：董玉祥")
    public ResponseEntity<List<DataDTO>> getEnt() {

        List<DataDTO> dataDTOS = new ArrayList<>();
        List<Group> Groups = groupRepository.findAll();
        for (Group group : Groups) {
            DataDTO.GroupDTO dto = new DataDTO.GroupDTO();
            BeanUtils.copyProperties(group, dto, BeanUtil.getNullPropertyNames(group));
            dataDTOS.add(dto);
        }
        for (Group g : Groups) {
            List<Enterprise> enterprises = enterpriseRepository.findByGroupCode(g.getGroupCode());
            for (Enterprise enterprise : enterprises) {
                DataDTO.EntDTO dto = new DataDTO.EntDTO();
                BeanUtils.copyProperties(enterprise, dto, BeanUtil.getNullPropertyNames(enterprise));
                dto.setCode(enterprise.getCode());
                dto.setName(enterprise.getName());
                dto.setGroupCode(g.getGroupCode());
                dataDTOS.add(dto);
            }
        }
        return ResponseEntity.ok().body(dataDTOS);
    }

    @GetMapping("/process_period")
    @ApiOperation(value = "获取工艺段", notes = "作者：董玉祥")
    public ResponseEntity<List<DataDTO.CraftDTO>> getCraft(String code) {

        List<Craft> crafts = craftRepository.findByEntCode(code);
        List<DataDTO.CraftDTO> craftDTOS = new ArrayList<>();
        for (Craft craft : crafts) {
            DataDTO.CraftDTO dto = new DataDTO.CraftDTO();
            BeanUtils.copyProperties(craft, dto, BeanUtil.getNullPropertyNames(craft));
            craftDTOS.add(dto);
        }
        return ResponseEntity.ok().body(craftDTOS);
    }

    @GetMapping("/getList")
    @ApiOperation(value = "获取数据展示列表——带分页", notes = "作者：董玉祥")
    public ResponseEntity<List<DataDisplayVM>> getList(@ApiParam(value = "水厂code") @RequestParam String code,
                                                       @ApiParam(value = "工艺code") @RequestParam(required = false) String craftCode,
                                                       @ApiParam(value = "数据时间") @RequestParam(required = false) String time,
                                                       @ApiParam(value = "数据来源") @RequestParam String source,
                                                       Pageable pageable) {

        String groupCode = enterpriseRepository.findByCode(code).get().getGroupCode();
        OptionalBooleanBuilder predicate = new OptionalBooleanBuilder()
            .notEmptyAnd(qEnterprise.code::eq, code)
            .notEmptyAnd(qGroup.groupCode::eq, groupCode)
            .notEmptyAnd(qCraft.craftCode::eq, craftCode);
        JPAQuery<DataDisplayVM> jpaQuery;
        long count;
        switch (source) {
            case "meter":
                predicate.notEmptyAnd(qSewProcess.dayTime::eq, sewEmiService.getInstant(time))
                    .notEmptyAnd(qSewProcess.craftCode::eq, craftCode);
                jpaQuery = jpaQueryFactory
                    .select(Projections.bean(DataDisplayVM.class, qSewProcess.id, qGroup.groupName, qEnterprise.name, qCraft.craftName, qSewProcess.dayTime))
                    .from(qSewProcess)
                    .leftJoin(qCraft).on(qCraft.craftCode.eq(qSewProcess.craftCode))
                    .leftJoin(qEnterprise).on(qEnterprise.code.eq(qCraft.entCode))
                    .leftJoin(qGroup).on(qGroup.groupCode.eq(qEnterprise.groupCode))
                    .where(predicate.build());
                count = jpaQuery.fetch().stream().count();
                jpaQuery = jpaQuery.offset(pageable.getOffset())
                    .limit(pageable.getPageSize());
                break;
            case "assay":
                predicate.notEmptyAnd(qSewSlu.dayTime::eq, sewEmiService.getInstant(time))
                    .notEmptyAnd(qSewSlu.craftCode::eq, craftCode);
                jpaQuery = jpaQueryFactory
                    .select(Projections.bean(DataDisplayVM.class, qSewSlu.id, qGroup.groupName, qEnterprise.name, qCraft.craftName, qSewSlu.dayTime))
                    .from(qSewSlu)
                    .leftJoin(qCraft).on(qCraft.craftCode.eq(qSewSlu.craftCode))
                    .leftJoin(qEnterprise).on(qEnterprise.code.eq(qCraft.entCode))
                    .leftJoin(qGroup).on(qGroup.groupCode.eq(qEnterprise.groupCode))
                    .where(predicate.build());
                count = jpaQuery.fetch().stream().count();
                jpaQuery = jpaQuery.offset(pageable.getOffset())
                    .limit(pageable.getPageSize());
                break;
            case "daily":
                predicate.notEmptyAnd(qSewPot.dayTime::eq, sewEmiService.getInstant(time))
                    .notEmptyAnd(qSewPot.craftCode::eq, craftCode);
                jpaQuery = jpaQueryFactory
                    .select(Projections.bean(DataDisplayVM.class, qSewPot.id, qGroup.groupName, qEnterprise.name, qCraft.craftName, qSewPot.dayTime))
                    .from(qSewPot)
                    .leftJoin(qCraft).on(qCraft.craftCode.eq(qSewPot.craftCode))
                    .leftJoin(qEnterprise).on(qEnterprise.code.eq(qCraft.entCode))
                    .leftJoin(qGroup).on(qGroup.groupCode.eq(qEnterprise.groupCode))
                    .where(predicate.build());
                count = jpaQuery.fetch().stream().count();
                jpaQuery = jpaQuery.offset(pageable.getOffset())
                    .limit(pageable.getPageSize());
                break;
            case "verify":
                predicate.notEmptyAnd(qSewMeter.dayTime::eq, sewEmiService.getInstant(time))
                    .notEmptyAnd(qSewMeter.craftCode::eq, craftCode);
                jpaQuery = jpaQueryFactory
                    .select(Projections.bean(DataDisplayVM.class, qSewMeter.id, qGroup.groupName, qEnterprise.name, qCraft.craftName, qSewMeter.dayTime))
                    .from(qSewMeter)
                    .leftJoin(qCraft).on(qCraft.craftCode.eq(qSewMeter.craftCode))
                    .leftJoin(qEnterprise).on(qEnterprise.code.eq(qCraft.entCode))
                    .leftJoin(qGroup).on(qGroup.groupCode.eq(qEnterprise.groupCode))
                    .where(predicate.build());
                count = jpaQuery.fetch().stream().count();
                jpaQuery = jpaQuery.offset(pageable.getOffset())
                    .limit(pageable.getPageSize());
                break;
            default:
                throw new BadRequestProblem("发生异常错误，获取列表失败", "请检查选择的条件是否正确，如有问题请联系管理员！");
        }
        Page<DataDisplayVM> page = new PageImpl<>(jpaQuery.fetch(), pageable, count);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情数据", notes = "作者：董玉祥")
    public ResponseEntity<SewEmiVM> getTargetParticulars(@ApiParam(value = "来源") @RequestParam(required = false) String source,
                                        @RequestParam Long id) {

        SewEmiVM sewEmiVM = sewEmiService.getTarget(id, source);
        return ResponseEntity.ok().body(sewEmiVM);
    }

    @PostMapping("/bulk_edit")
    @ApiOperation(value = "批量编辑", notes = "作者：董玉祥")
    public ResponseEntity<Void> bulkEdit(@ApiParam(value = "需要改的数据id") @RequestParam List<Long> ids,
                                         @ApiParam(value = "数据信息") @RequestBody SewDetailsDTO sewProcessDTO,
                                         @ApiParam(value = "需要改的数据id") @RequestBody String source) {

//        for (Long id : ids) {
//            sewEmiService.modificationBycraftId(id, sewProcessDTO,source);
//        }
        return ResponseEntity.ok().build();
    }

}
