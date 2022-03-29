package com.ruowei.web.rest;


import com.ruowei.domain.BeAssociated;
import com.ruowei.domain.Correlation;
import com.ruowei.repository.*;
import com.ruowei.service.SewEmiService;
import com.ruowei.util.SelectUtil;
import com.ruowei.util.SituationAnalysisUtil;
import com.ruowei.web.rest.dto.SituationAnalysisDTO;
import com.ruowei.web.rest.vm.DateTimeRangeVM;
import com.ruowei.web.rest.vm.SituationAnalysisVM;
import com.ruowei.web.rest.vm.SituationVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "态势分析")
@Transactional
public class SituationAnalysisResource {


    private final SituationAnalysisUtil situationAnalysisUtil;
    private final SewProcessRepository sewProcessRepository;
    private final SewSluRepository sewSluRepository;
    private final SewMeterRepository sewMeterRepository;
    private final BeAssociatedRepository beAssociatedRepository;
    private final CorrelationRepository correlationRepository;
    private final SewEmiService sewEmiService;
    private final SelectUtil selectUtil;

    public SituationAnalysisResource(SituationAnalysisUtil situationAnalysisUtil, SewProcessRepository sewProcessRepository, SewSluRepository sewSluRepository, SewMeterRepository sewMeterRepository, CorrelationRepository correlationRepository, SewEmiService sewEmiService, SelectUtil selectUtil, BeAssociatedRepository beAssociatedRepository, SelectUtil selectUtil1) {

        this.situationAnalysisUtil = situationAnalysisUtil;
        this.sewProcessRepository = sewProcessRepository;
        this.sewSluRepository = sewSluRepository;
        this.sewMeterRepository = sewMeterRepository;
        this.correlationRepository = correlationRepository;
        this.sewEmiService = sewEmiService;
        this.beAssociatedRepository = beAssociatedRepository;
        this.selectUtil = selectUtil1;
    }

    @PostMapping("/situation_analysis_first")
    @ApiOperation(value = "态势分析图一", notes = "作者：董玉祥")
    public ResponseEntity<List<SituationVM>> situationAnalysisFirst(@RequestBody SituationAnalysisDTO situationAnalysisDTO) {

        List<SituationVM> situationVMS = new ArrayList<>();
        for (String s : situationAnalysisDTO.getTargets()) {
            situationVMS.add(situationAnalysisUtil.getSituationVM(situationAnalysisDTO.getBeginTime(), situationAnalysisDTO.getEndTime(),situationAnalysisDTO.getSubsection(), s, situationAnalysisDTO.getCraftCode()));
        }
        return ResponseEntity.ok().body(situationVMS);
    }

    @PostMapping("/situation_analysis_second")
    @ApiOperation(value = "态势分析图二", notes = "作者：董玉祥")
    public ResponseEntity<List<List<SituationAnalysisVM>>> situationAnalysisSecond(@ApiParam(value = "开始时间") @RequestBody List<DateTimeRangeVM> datetimeRange,
                                                                             @ApiParam(value = "数据所在工艺Id") @RequestParam String craftCode,
                                                                             @ApiParam(value = "日期分段") @RequestParam String subsection,
                                                                             @ApiParam(value = "指标") @RequestParam String target) {

        List<List<SituationAnalysisVM>> situationAnalysisVMLists = new ArrayList<>();
        for (DateTimeRangeVM dateTimeRangeVM : datetimeRange) {
            situationAnalysisVMLists.add(situationAnalysisUtil.getSituationAnalysisVM(dateTimeRangeVM.getBeginTime(), dateTimeRangeVM.getEndTime(), subsection, target, craftCode));
        }
        return ResponseEntity.ok().body(situationAnalysisVMLists);
    }

    @PostMapping("/situation_analysis_thirdly")
    @ApiOperation(value = "态势分析图三", notes = "作者：董玉祥")
    public ResponseEntity<List<SituationVM>> situationAnalysisThirdly(@RequestBody SituationAnalysisDTO situationAnalysisDTO) {



        List<SituationVM> situationVMS = new ArrayList<>();
        for (String s : situationAnalysisDTO.getTargets()) {
            situationVMS.add(situationAnalysisUtil.getSituationVM(situationAnalysisDTO.getBeginTime(), situationAnalysisDTO.getEndTime(),situationAnalysisDTO.getSubsection(), s, situationAnalysisDTO.getCraftCode()));
        }
        return ResponseEntity.ok().body(situationVMS);

    }

    @PostMapping("/situation_analysis_fourthly")
    @ApiOperation(value = "态势分析图四", notes = "作者：董玉祥")
    public ResponseEntity<List<SituationVM>> situationAnalysisFourthly(@ApiParam(value = "被关联的指标id") @RequestParam Long id,
                                                                       @ApiParam(value = "数据所在工艺Id") @RequestParam String craftCode,
                                                                       @ApiParam(value = "日期分段") @RequestParam String subsection,
                                                                       @ApiParam(value = "开始时间") @RequestParam String beginTime,
                                                                       @ApiParam(value = "结束时间") @RequestParam String endTime) {



        List<SituationVM> situationVMS = new ArrayList<>();
        BeAssociated beAssociated = beAssociatedRepository.findById(id).get();
        List<Correlation> correlations = correlationRepository.findByRelevanceId(id);
        situationVMS.add(situationAnalysisUtil.getSituationVM(beginTime, endTime, subsection, beAssociated.getBeAssociatedName(), craftCode));
        for (Correlation correlationsList : correlations) {
            situationVMS.add(situationAnalysisUtil.getSituationVM(beginTime, endTime, subsection, correlationsList.getRelationTarget(), craftCode));
        }
        return ResponseEntity.ok().body(situationVMS);

    }

}
