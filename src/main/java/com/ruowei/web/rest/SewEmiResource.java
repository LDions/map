package com.ruowei.web.rest;

import com.github.yitter.idgen.YitIdHelper;
import com.ruowei.config.ApplicationProperties;
import com.ruowei.security.UserModel;
import com.ruowei.service.SewEmiService;
import com.ruowei.service.dto.*;
import com.ruowei.util.CarbonAccountingExcel2;
import com.ruowei.util.DateUtil;
import com.ruowei.util.WaterCarbonEmissionUtils;
import com.ruowei.web.rest.dto.SewEmiDetailDTO;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.CarbonEmiMonthVM;
import com.ruowei.web.rest.vm.CarbonEmiReportVM;
import com.ruowei.web.rest.dto.SewEmiAccountOutputDTO;
import com.ruowei.web.rest.vm.SewEmiAccountVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import static com.ruowei.config.Constants.SEWAGE;

@RestController
@RequestMapping("/api")
@Api(tags = "污水碳排放核算")
@Transactional
public class SewEmiResource {

    private final Logger log = LoggerFactory.getLogger(SewEmiResource.class);

    private final SewEmiService sewEmiService;

    private final ApplicationProperties applicationProperties;

    public SewEmiResource(SewEmiService sewEmiService, ApplicationProperties applicationProperties) {
        this.sewEmiService = sewEmiService;
        this.applicationProperties = applicationProperties;
    }

    @PostMapping("/carbon-emission/water/accounting")
    @ApiOperation(value = "污水厂碳排放数据核算接口", notes = "作者：林宏栋")
    public ResponseEntity<SewEmiAccountOutputDTO> accounting(@Valid @RequestBody SewEmiAccountVM vm,
                                                             @ApiIgnore @AuthenticationPrincipal UserModel userModel) {
        // 输入数据校验
        // 获取核算时间对应月份的天数
        int days = DateUtil.getMonthHasDays(vm.getAccYear(), vm.getAccMonth());
        for (SewEmiAccountVM.SewProcessVM sewProcessVm : vm.getSewProcesss()) {
            // 月运行天数不能超过核算月份天数
            if (sewProcessVm.getOperatingDays().compareTo(days) > 0) {
                throw new BadRequestProblem("核算失败", "工艺".concat(sewProcessVm.getProcessTypeName()).concat("月运行天数不能超过核算月份天数"));
            }
            // 日均运行规模不能为0
            if (sewProcessVm.getDailyScale().compareTo(new BigDecimal(0)) == 0) {
                throw new BadRequestProblem("核算失败", "工艺".concat(sewProcessVm.getProcessTypeName()).concat("日均运行规模不能为0"));
            }
        }
        // 各个设施耗电量之和不超出“月度总耗电量”
        BigDecimal sumPowerOfEach = sewEmiService.calculateSumOfEachPower(vm);
        if (sumPowerOfEach.compareTo(vm.getTotalPow()) > 0) {
            throw new BadRequestProblem("核算失败", "各设施耗电量之和不能超出月度总耗电量");
        }
        // 各级药剂投加量之和不超出“药剂月投加量”
        for (SewEmiAccountVM.SewPotVM sewPotVm : vm.getSewPots()) {
            BigDecimal sumDosageOfEach = sewEmiService.calculateSumOfEachDosage(sewPotVm);
            if (sumDosageOfEach.compareTo(sewPotVm.getDayAerobicPoolPh()) > 0) {
                throw new BadRequestProblem("核算失败", "药剂".concat(sewPotVm.getDayAnaerobicPoolOrp().toEngineeringString()).concat("各级投加量之和不能超出药剂月投加量"));
            }
        }
        // 输入数据和排放因子参数封装
        WaterCarbonEmissionEnterDTO waterCarbonEmissionEnterDTO = sewEmiService.packageParametersOfAccounting(vm);
        log.info("The input data and emission factor parameters are successfully encapsulated.");
        // 数据核算
        WaterCarbonEmissionOutputDTO waterCarbonEmissionOutputDTO = WaterCarbonEmissionUtils.modelCalculation(waterCarbonEmissionEnterDTO);
        log.info("The data calculation model is successfully calculated.");
        // 调用智能合约保存核算结果
        // 生成单据号
        String documentCode = "HS".concat(new SimpleDateFormat("yyyyMMdd").format(new Date())).concat(String.valueOf(YitIdHelper.nextId()));
        // 获取当前时间
        Instant nowInstant = Instant.now();
        sewEmiService.saveAccountingResultToMySQL(
            documentCode,
            userModel,
            vm,
            waterCarbonEmissionEnterDTO,
            waterCarbonEmissionOutputDTO,
            nowInstant
        );
        log.info("The calculation result is saved successfully.");
        // 生成核算报告，异步处理
        SewEmiDTO sewEmiDTO = sewEmiService.convertToSewEmiDTO(documentCode, userModel, vm, waterCarbonEmissionEnterDTO.getFactorVersionNum(), waterCarbonEmissionOutputDTO, nowInstant);
        sewEmiService.asyncGenerateReport(sewEmiDTO);
        // 返回核算结果
        SewEmiAccountOutputDTO sewEmiAccountOutputDTO = new SewEmiAccountOutputDTO(
            vm.getAccYear(),
            vm.getAccMonth(),
            documentCode,
            vm.getIndustryCode(),
            userModel.getEnterpriseName(),
            waterCarbonEmissionOutputDTO
        );
        // 判断核算数据是否来源于草稿箱

        return ResponseEntity.ok().body(sewEmiAccountOutputDTO);
    }

//    @PostMapping("/carbon-emission-data/water/print")
//    @ApiOperation(value = "污水厂碳排放PDF报告打印接口", notes = "作者：林宏栋")
//    public ResponseEntity<Resource> print(@Valid @RequestBody CarbonEmiReportVM vm) {
//        // 根据核算年份、核算月份、单据号判断待下载的污水厂碳排放报告是否存在，若存在，则直接下载
//        String reportDuringAccountPath = applicationProperties.getReportPath() + vm.getAccYear() + "/" + vm.getAccMonth() + "/" + vm.getDocumentCode() + ".pdf";
//        File reportDuringAccount = new File(reportDuringAccountPath);
//        if (!reportDuringAccount.exists()) {
//            // 若不存在报告，则需要根据单据号和行业类型查询详情
//            if (SEWAGE.equals(vm.getIndustryCode())) {
//                SewEmiDTO sewEmiDTO = sewEmiService.convertToSewEmiDtoByDocumentCode(vm.getDocumentCode());
//                // 生成核算报告
//                sewEmiService.syncGenerateReport(sewEmiDTO);
//            } else {
//                throw new BadRequestProblem("打印失败", "不存在该行业类型");
//            }
//        }
//        File reportNew = new File(reportDuringAccountPath);
//        if (!reportNew.exists()) {
//            throw new BadRequestProblem("打印失败", "污水厂碳排放报告不存在");
//        }
//        // 下载
//        Resource resource = new FileSystemResource(reportNew);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//            "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
//    }
//
//    @PostMapping("/carbon-emission-data/water/download")
//    @ApiOperation(value = "污水厂碳排放Excel报告下载接口", notes = "作者：林宏栋")
//    public ResponseEntity<Resource> download(@Valid @RequestBody CarbonEmiReportVM vm) {
//        byte[] buffer = new byte[1024];
//        if (SEWAGE.equals(vm.getIndustryCode())) {
//            SewEmiDTO sewEmiDTO = sewEmiService.convertToSewEmiDtoByDocumentCode(vm.getDocumentCode());
//            // 调用生成Excel方法
//            buffer = CarbonAccountingExcel2.createExcel(sewEmiDTO);
//        } else {
//            throw new BadRequestProblem("打印失败", "不存在该行业类型");
//        }
//        if (buffer == null) {
//            throw new BadRequestProblem("生成Excel失败", "Excel字节数组为空");
//        }
//        Resource resource = new ByteArrayResource(buffer);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//            "attachment;").body(resource);
//    }
//
//    @PostMapping("/carbon-emission-data/water/month")
//    @ApiOperation(value = "获取上一个月（下一个月）污水厂碳排放详情接口", notes = "作者：林宏栋")
//    public ResponseEntity<SewEmiDetailDTO> getDetailOfPreviousOrNextMonth(@Valid @RequestBody CarbonEmiMonthVM vm) {
//        if (SEWAGE.equals(vm.getIndustryCode())) {
//            SewEmiDetailDTO sewEmiDetailDTO = sewEmiService.getAndConvertDetailOfPreviousOrNextMonth(vm);
//            return ResponseEntity.ok().body(sewEmiDetailDTO);
//        }else {
//            throw new BadRequestProblem("查询失败", "不存在该行业类型");
//        }
//    }

//    @PostMapping("/generate")
//    @ApiOperation(value = "初始化", notes = "作者：林宏栋")
//    public ResponseEntity<EmiFactor> generate() {
//        EmiFactor emiFactor = sewEmiService.create();
//        return ResponseEntity.ok().body(emiFactor);
//    }

}
