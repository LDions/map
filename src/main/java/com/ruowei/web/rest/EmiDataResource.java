package com.ruowei.web.rest;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.QEmiData;
import com.ruowei.security.UserModel;
import com.ruowei.service.SewEmiService;
import com.ruowei.util.OrderByUtil;
import com.ruowei.util.excel.ExcelExport;
import com.ruowei.web.rest.dto.CarbonEmiDataDTO;
import com.ruowei.web.rest.dto.SewEmiDetailDTO;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.CarbonEmiQM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.*;

import static com.ruowei.config.Constants.SEWAGE;

@RestController
@RequestMapping("/api")
@Api(tags = "碳排放数据管理")
@Transactional
public class EmiDataResource {

    private final Logger log = LoggerFactory.getLogger(EmiDataResource.class);

    private final SewEmiService sewEmiService;
    private final JPAQueryFactory jpaQueryFactory;
    private QEmiData qEmiData = QEmiData.emiData;

    public EmiDataResource(SewEmiService sewEmiService, JPAQueryFactory jpaQueryFactory) {
        this.sewEmiService = sewEmiService;
        this.jpaQueryFactory = jpaQueryFactory;
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
    public ResponseEntity<SewEmiDetailDTO> getCarbonEmiData(@ApiParam(value = "单据号", required = true) @RequestParam String documentCode,
                                                            @ApiParam(value = "行业类型", required = true) @RequestParam String industryCode) {
        if (SEWAGE.equals(industryCode)) {
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
