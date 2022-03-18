package com.ruowei.web.rest;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.*;
import com.ruowei.domain.enumeration.UserStatusType;
import com.ruowei.repository.*;
import com.ruowei.security.UserModel;
import com.ruowei.service.SewEmiService;
import com.ruowei.util.BeanUtil;
import com.ruowei.util.OptionalBooleanBuilder;
import com.ruowei.util.OrderByUtil;
import com.ruowei.util.SelectUtil;
import com.ruowei.util.excel.ExcelExport;
import com.ruowei.web.rest.dto.*;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;
import tech.jhipster.web.util.PaginationUtil;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

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
    private final CorrelationRepository correlationRepository;
    private final BeAssociatedRepository beAssociatedRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final SelectUtil selectUtil;
    private QEmiData qEmiData = QEmiData.emiData;
    private final QEntCraftData qEntCraftData = QEntCraftData.entCraftData;
    private final JPAQueryFactory queryFactory;

    public EmiDataResource(SewEmiService sewEmiService, SewProcessRepository sewProcessRepository, GroupRepository groupRepository, CraftRepository craftRepository, EnterpriseRepository enterpriseRepository, EntCraftProcessRepository entCraftProcessRepository, SewSluRepository sewSluRepository,
                           SewPotRepository sewPotRepository, CorrelationRepository correlationRepository, BeAssociatedRepository beAssociatedRepository, JPAQueryFactory jpaQueryFactory, SelectUtil selectUtil, JPAQueryFactory queryFactory) {
        this.sewEmiService = sewEmiService;
        this.sewProcessRepository = sewProcessRepository;
        this.groupRepository = groupRepository;
        this.craftRepository = craftRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.entCraftProcessRepository = entCraftProcessRepository;
        this.sewSluRepository = sewSluRepository;
        this.sewPotRepository = sewPotRepository;
        this.correlationRepository = correlationRepository;
        this.beAssociatedRepository = beAssociatedRepository;
        this.jpaQueryFactory = jpaQueryFactory;
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
            List<Enterprise> enterprises = enterpriseRepository.findByGroupId(g.getId());
            for (Enterprise enterprise : enterprises) {
                DataDTO.EntDTO dto = new DataDTO.EntDTO();
                BeanUtils.copyProperties(enterprise, dto, BeanUtil.getNullPropertyNames(enterprise));
                dataDTOS.add(dto);
            }
        }

        return ResponseEntity.ok().body(dataDTOS);
    }

    @GetMapping("/process_period")
    @ApiOperation(value = "获取工艺段", notes = "作者：董玉祥")
    public ResponseEntity<List<DataDTO.CraftDTO>> getCraft(Long entId) {

        List<Craft> crafts = craftRepository.findByEntId(entId);
        List<DataDTO.CraftDTO> craftDTOS = new ArrayList<>();
        for (Craft craft : crafts) {
            DataDTO.CraftDTO dto = new DataDTO.CraftDTO();
            BeanUtils.copyProperties(craft, dto, BeanUtil.getNullPropertyNames(craft));
            craftDTOS.add(dto);
        }
        return ResponseEntity.ok().body(craftDTOS);
    }

    @GetMapping("/getList")
    @ApiOperation(value = "获取数据展示列表", notes = "作者：董玉祥")
    public ResponseEntity<List<EntCraftDataDTO>> getList(@ApiParam(value = "水厂id") @RequestParam Long entId,
                                                         @ApiParam(value = "工艺id") @RequestParam(required = false) Long craftId,
                                                         @ApiParam(value = "数据时间") @RequestParam(required = false) String time,
                                                         @ApiParam(value = "数据来源") @RequestParam String source,
                                                         Pageable pageable) {

        OptionalBooleanBuilder predicate = new OptionalBooleanBuilder()
            .notEmptyAnd(qEntCraftData.entId::eq, entId)
            .notEmptyAnd(qEntCraftData.craftId::eq, craftId)
            .notEmptyAnd(qEntCraftData.dayTime::eq, sewEmiService.getInstant(time))
            .notEmptyAnd(qEntCraftData.messageSource::eq, source);

        JPAQuery<EntCraftDataDTO> jpaQuery = jpaQueryFactory
            .select(Projections.bean(EntCraftDataDTO.class,qEntCraftData.id,qEntCraftData.documentCode,qEntCraftData.groupName,qEntCraftData.entName,qEntCraftData.craftName,qEntCraftData.dayTime))
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

    @GetMapping("/detail")
    @ApiOperation(value = "详情数据", notes = "作者：董玉祥")
    public ResponseEntity<List<SewEmiVM>> get(@ApiParam(value = "单据号") @RequestParam(required = false) String documentCode) {

        List<SewEmiVM> sewEmiVMS= sewEmiService.convertToSewDetailDtoByDocumentCode(documentCode);
        return ResponseEntity.ok().body(sewEmiVMS);
    }

    @PostMapping("/bulk_edit")
    @ApiOperation(value = "批量编辑", notes = "作者：董玉祥")
    public ResponseEntity<Void> bulkEdit(@ApiParam(value = "单据号") @RequestParam(required = false) List<String> documentCodes,
                                         @ApiParam(value = "仪表数据") @RequestBody SewDetailsDTO.SewProcessDTO sewProcessDTO) {

        for (String str:documentCodes) {
            sewEmiService.modificationByDocumentCode(str,sewProcessDTO);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/relevance")
    @ApiOperation(value = "关联信息新增", notes = "作者：董玉祥")
    public ResponseEntity<String> relevance(@RequestBody CollectQM qm) {

        beAssociatedRepository.findByBeAssociatedName(qm.getBeAssociated().getTarget())
            .ifPresent(so -> {
                throw new BadRequestProblem("新增失败", "该数据已经存在关联，请前往编辑进行编辑");
            });
        BeAssociated beAssociated = new BeAssociated();
        beAssociated.setBeAssociatedName(qm.getBeAssociated().getTarget());
        beAssociated.setBeAssociatedSource(qm.getBeAssociated().getSource());
        long id = beAssociatedRepository.save(beAssociated).getId();
        for (SituationAnalysisQM situationAnalysisQM:qm.getRelation()) {
            Correlation correlation = new Correlation();
            correlation.setRelationTarget(situationAnalysisQM.getTarget());
            correlation.setRelationSource(situationAnalysisQM.getSource());
            correlation.setRelevanceId(id);
            correlationRepository.save(correlation);
        }

        return ResponseEntity.ok().body("新增成功");
    }

    /*@PostMapping("/relevance_list")
    @ApiOperation(value = "关联信息列表", notes = "作者：董玉祥")
    public ResponseEntity<List<CollectQM>> relevanceList() {

        List<CollectQM> collectQMS = new ArrayList<>();
        List<BeAssociated> beAssociateds = beAssociatedRepository.findAll();
        for (BeAssociated b:beAssociateds) {
            CollectQM collectQM = new CollectQM();

            collectQM.setBeAssociated();
            collectQM.getBeAssociated().setTarget(b.getBeAssociatedName());
            collectQM.getBeAssociated().setSource(b.getBeAssociatedSource());
            List<Correlation> correlations = correlationRepository.findByRelevanceId(b.getId());
            for (Correlation correlation : correlations){
                int i = 1;
                collectQM.getRelation().get(i).setSource(correlation.getRelationSource());
                collectQM.getRelation().get(i).setTarget(correlation.getRelationTarget());
            }
            collectQMS.add(collectQM);
        }
        return ResponseEntity.ok().body(collectQMS);
    }
*/

    @PostMapping("/situation_analysis")
    @ApiOperation(value = "势态分析", notes = "作者：董玉祥")
    public ResponseEntity<List<List>> select(@RequestBody List<SituationAnalysisQM> situationAnalysisQMS,
                                                   @ApiParam(value = "开始时间") @RequestParam(required = false) String beginTime,
                                                   @ApiParam(value = "结束时间") @RequestParam(required = false) String endTime,
                                                   @ApiParam(value = "分段") @RequestParam(required = false) String subsection) {
        List<List> list = new ArrayList<>();
        for (SituationAnalysisQM qm:situationAnalysisQMS) {
            List<BigDecimal> list1 = selectUtil.getSome(qm.getSource(),qm.getTarget(),beginTime,endTime,subsection);
            list.add(list1);
        }
        return ResponseEntity.ok().body(list);
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
        /*if (1 == 1) {
            SewEmiDetailDTO sewEmiDetailDTO = sewEmiService.convertToSewEmiDetailDtoByDocumentCode(documentCode);
            return ResponseEntity.ok().body(sewEmiDetailDTO);
        } else {
            throw new BadRequestProblem("查询失败", "不存在该行业类型");
        }*/
        return null;
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
