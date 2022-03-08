package com.ruowei.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.EmiFactor;
import com.ruowei.domain.QEmiFactor;
import com.ruowei.repository.EmiFactorRepository;
import com.ruowei.security.UserModel;
import com.ruowei.service.dto.SewEmiFactorDTO;
import com.ruowei.util.excel.ExcelExport;
import com.ruowei.web.rest.dto.DropDownDTO;
import com.ruowei.web.rest.dto.EmiFactorDTO;
import com.ruowei.web.rest.dto.EmiFactorDetailDTO;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.EmiFactorQM;
import com.ruowei.web.rest.vm.EmiFactorVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api")
@Api(tags = "碳排放因子管理")
public class EmiFactorResource {

    private final Logger log = LoggerFactory.getLogger(EmiFactorResource.class);
    private final EmiFactorRepository emiFactorRepository;
    private final ObjectMapper objectMapper;
    private final JPAQueryFactory jpaQueryFactory;
    private QEmiFactor qEmiFactor = QEmiFactor.emiFactor;

    public EmiFactorResource(EmiFactorRepository emiFactorRepository, ObjectMapper objectMapper, JPAQueryFactory jpaQueryFactory) {
        this.emiFactorRepository = emiFactorRepository;
        this.objectMapper = objectMapper;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @PostMapping("/emi-factor")
    @ApiOperation(value = "保存碳排放因子接口", notes = "作者：林宏栋")
    public ResponseEntity<EmiFactor> upsertEmiFactor(@Valid @RequestBody EmiFactorVM vm, @ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        // 判断当天是否已经修改过碳排放因子
        if (emiFactorRepository.existsByProjectCodeAndModifyDate(vm.getProjectCode(), LocalDate.now())) {
            throw new BadRequestProblem("保存失败", "碳排放因子当天只能修改一次");
        }
        try {
            SewEmiFactorDTO sewEmiFactorDTO = objectMapper.readValue(vm.getContent(), SewEmiFactorDTO.class);
            String content = objectMapper.writeValueAsString(sewEmiFactorDTO);
            EmiFactor emiFactor = new EmiFactor()
                .versionNum(vm.getProjectName() + "-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .historicalId(vm.getHistoricalId())
                .projectCode(vm.getProjectCode())
                .projectName(vm.getProjectName())
                .operatorId(userModel.getUserId())
                .operatorName(userModel.getNickName())
                .modifyDate(LocalDate.now())
                .remark(vm.getRemark())
                .content(content);
            emiFactor = emiFactorRepository.save(emiFactor);
            return ResponseEntity.ok().body(emiFactor);
        } catch (JsonProcessingException e) {
            throw new BadRequestProblem("保存失败", "碳排放因子解析失败");
        }
    }

    @GetMapping("/emi-factors")
    @ApiOperation(value = "获取历史排放因子列表接口", notes = "作者：林宏栋")
    public ResponseEntity<List<EmiFactorDTO>> getAllEmiFactors(EmiFactorQM qm, Pageable pageable) {
        // 查询条件组装
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qEmiFactor.projectCode.eq(qm.getProjectCode()));
        if (StringUtils.isNotBlank(qm.getModifyDateFrom())) {
            booleanBuilder.and(qEmiFactor.modifyDate.goe(LocalDate.parse(qm.getModifyDateFrom(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        }
        if (StringUtils.isNotBlank(qm.getModifyDateTo())) {
            booleanBuilder.and(qEmiFactor.modifyDate.loe(LocalDate.parse(qm.getModifyDateTo(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        }
        List<EmiFactorDTO> list = jpaQueryFactory
            .select(Projections.bean(EmiFactorDTO.class, qEmiFactor.id, qEmiFactor.versionNum, qEmiFactor.operatorName, qEmiFactor.modifyDate, qEmiFactor.remark))
            .from(qEmiFactor)
            .where(booleanBuilder)
            .orderBy(qEmiFactor.modifyDate.desc())
            .fetch();
        PagedListHolder<EmiFactorDTO> pagedListHolder = new PagedListHolder<>(list);
        pagedListHolder.setPageSize(pageable.getPageSize());
        pagedListHolder.setPage(pageable.getPageNumber());
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", Long.toString(list.size()));
        return ResponseEntity.ok().headers(headers).body(pagedListHolder.getPageList());
    }

    @GetMapping("/emi-factor/current")
    @ApiOperation(value = "获取当前生效排放因子接口", notes = "作者：林宏栋")
    public ResponseEntity<EmiFactor> getCurrentEffectiveEmiFactor(@ApiParam(value = "项目编码", required = true) @RequestParam String projectCode) {
        // 获取当前生效碳排放因子
        EmiFactor emiFactor = emiFactorRepository.findFirstByProjectCodeOrderByModifyDateDesc(projectCode).orElseThrow(() -> new BadRequestProblem("获取失败", "未找到碳排放因子"));
        if (StringUtils.isBlank(emiFactor.getContent())) {
            throw new BadRequestProblem("获取失败", "未找到碳排放因子");
        }
        return ResponseEntity.ok().body(emiFactor);
    }

    @PostMapping("/emi-factor/export")
    @ApiOperation(value = "导出历史排放因子列表接口", notes = "作者：林宏栋")
    public ResponseEntity<Resource> exportHistoricalEmiFactor(@Valid @RequestBody EmiFactorQM qm) {
        // 查询条件组装
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qEmiFactor.projectCode.eq(qm.getProjectCode()));
        if (StringUtils.isNotBlank(qm.getModifyDateFrom())) {
            booleanBuilder.and(qEmiFactor.modifyDate.after(LocalDate.parse(qm.getModifyDateFrom(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        }
        if (StringUtils.isNotBlank(qm.getModifyDateTo())) {
            booleanBuilder.and(qEmiFactor.modifyDate.before(LocalDate.parse(qm.getModifyDateTo(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        }
        List<EmiFactorDTO> list = jpaQueryFactory
            .select(Projections.bean(EmiFactorDTO.class, qEmiFactor.id, qEmiFactor.versionNum, qEmiFactor.operatorName, qEmiFactor.modifyDate, qEmiFactor.remark))
            .from(qEmiFactor)
            .where(booleanBuilder)
            .orderBy(qEmiFactor.modifyDate.desc())
            .fetch();
        // 调用生成Excel方法
        byte[] buffer = ExcelExport.createExcelSewageEmiFactor(list);
        if (buffer == null) {
            throw new BadRequestProblem("导出Excel失败", "Excel字节数组为空");
        }
        Resource resource = new ByteArrayResource(buffer);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment;").body(resource);
    }

    @PostMapping("/emi-factor/export/{id}")
    @ApiOperation(value = "导出历史排放因子详情接口", notes = "作者：郑昊天")
    public ResponseEntity<Resource> getEmiFactorHistoricalDetail(@PathVariable Long id) {
        EmiFactor currentEmiFactor = emiFactorRepository.findById(id).orElseThrow(() -> new BadRequestProblem("查询失败", "未找到碳排放因子"));
        try {
            SewEmiFactorDTO sewEmiFactorDTO = objectMapper.readValue(currentEmiFactor.getContent(), SewEmiFactorDTO.class);
            //药剂
            LinkedHashMap<String, SewEmiFactorDTO.Info> p1 = sewEmiFactorDTO.getChemicalsEmiFactor();
            //工艺
            LinkedHashMap<String, SewEmiFactorDTO.Info> p2 = sewEmiFactorDTO.getProcessTypeNi();
            //气体
            SewEmiFactorDTO.GasEmiFactor p3 = sewEmiFactorDTO.getGasEmiFactor();
            //省电网
            LinkedHashMap<String, SewEmiFactorDTO.InfoPro> p4 = sewEmiFactorDTO.getProElecEmiFactor();
            //污泥处理
            SewEmiFactorDTO.SluTreatFactor p5 = sewEmiFactorDTO.getSluTreatFactor();
            //污水处理
            SewEmiFactorDTO.SewTreatFactor p6 = sewEmiFactorDTO.getSewTreatFactor();
            //热泵
            SewEmiFactorDTO.HeatPumpFactor p7 = sewEmiFactorDTO.getHeatPumpFactor();
            byte[] buffer = ExcelExport.createExcelSewageEmi(p1, p2, p3, p4, p5, p6, p7);
            if (buffer == null) {
                throw new BadRequestProblem("导出Excel失败", "Excel字节数组为空");
            }
            Resource resource = new ByteArrayResource(buffer);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;").body(resource);
        } catch (JsonProcessingException e) {
            throw new BadRequestProblem("获取失败", "解析碳排放因子失败");
        }
    }

    @GetMapping("/emi-factor/{id}")
    @ApiOperation(value = "获取历史排放因子详情接口", notes = "作者：林宏栋")
    public ResponseEntity<EmiFactorDetailDTO> getEmiFactorDetail(@PathVariable Long id) {
        EmiFactor currentEmiFactor = emiFactorRepository.findById(id).orElseThrow(() -> new BadRequestProblem("查询失败", "未找到碳排放因子"));
        EmiFactorDetailDTO emiFactorDetailDTO = new EmiFactorDetailDTO();
        emiFactorDetailDTO.setId(currentEmiFactor.getId());
        emiFactorDetailDTO.setVersionNum(currentEmiFactor.getVersionNum());
        emiFactorDetailDTO.setRemark(currentEmiFactor.getRemark());
        emiFactorDetailDTO.setContent(currentEmiFactor.getContent());
        if (currentEmiFactor.getHistoricalId() != null) {
            EmiFactor historicalEmiFactor = emiFactorRepository.findById(currentEmiFactor.getHistoricalId()).orElseThrow(() -> new BadRequestProblem("查询失败", "未找到历史碳排放因子"));
            emiFactorDetailDTO.setOldContent(historicalEmiFactor.getContent());
        }
        return ResponseEntity.ok().body(emiFactorDetailDTO);
    }

    @GetMapping("/emi-factor/medicine")
    @ApiOperation(value = "获取药剂下拉列表接口", notes = "作者：林宏栋")
    public ResponseEntity<List<DropDownDTO>> getMedicineDropDownList(@ApiParam(value = "项目编码", required = true) @RequestParam String projectCode) {
        // 获取当前生效碳排放因子
        EmiFactor emiFactor = emiFactorRepository.findFirstByProjectCodeOrderByModifyDateDesc(projectCode).orElseThrow(() -> new BadRequestProblem("获取失败", "未找到碳排放因子"));
        if (StringUtils.isBlank(emiFactor.getContent())) {
            throw new BadRequestProblem("获取失败", "未找到碳排放因子");
        }
        // 解析碳排放因子数据信息
        try {
            SewEmiFactorDTO sewEmiFactorDTO = objectMapper.readValue(emiFactor.getContent(), SewEmiFactorDTO.class);
            List<DropDownDTO> result = new ArrayList<>();
            LinkedHashMap<String, SewEmiFactorDTO.Info> map = sewEmiFactorDTO.getChemicalsEmiFactor();
            for (Map.Entry<String, SewEmiFactorDTO.Info> next : map.entrySet()) {
                DropDownDTO dto = new DropDownDTO();
                dto.setCode(next.getKey());
                dto.setName(next.getValue().getName());
                result.add(dto);
            }
            return ResponseEntity.ok().body(result);
        } catch (JsonProcessingException e) {
            throw new BadRequestProblem("获取失败", "解析碳排放因子失败");
        }
    }

    @GetMapping("/emi-factor/process")
    @ApiOperation(value = "获取工艺下拉列表接口", notes = "作者：林宏栋")
    public ResponseEntity<List<DropDownDTO>> getProcessTypeDropDownList(@ApiParam(value = "项目编码", required = true) @RequestParam String projectCode) {
        // 获取当前生效碳排放因子
        EmiFactor emiFactor = emiFactorRepository.findFirstByProjectCodeOrderByModifyDateDesc(projectCode).orElseThrow(() -> new BadRequestProblem("获取失败", "未找到碳排放因子"));
        if (StringUtils.isBlank(emiFactor.getContent())) {
            throw new BadRequestProblem("获取失败", "未找到碳排放因子");
        }
        // 解析碳排放因子数据信息
        try {
            SewEmiFactorDTO sewEmiFactorDTO = objectMapper.readValue(emiFactor.getContent(), SewEmiFactorDTO.class);
            List<DropDownDTO> result = new ArrayList<>();
            LinkedHashMap<String, SewEmiFactorDTO.Info> map = sewEmiFactorDTO.getProcessTypeNi();
            for (Map.Entry<String, SewEmiFactorDTO.Info> next : map.entrySet()) {
                DropDownDTO dto = new DropDownDTO();
                dto.setCode(next.getKey());
                dto.setName(next.getValue().getName());
                result.add(dto);
            }
            return ResponseEntity.ok().body(result);
        } catch (JsonProcessingException e) {
            throw new BadRequestProblem("获取失败", "解析碳排放因子失败");
        }
    }

}
