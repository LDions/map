package com.ruowei.web.rest;


import com.ruowei.domain.BeAssociated;
import com.ruowei.domain.Correlation;
import com.ruowei.repository.*;
import com.ruowei.service.SewEmiService;
import com.ruowei.util.SelectUtil;
import com.ruowei.util.SituationAnalysisUtil;
import com.ruowei.web.rest.vm.DateTimeRangeVM;
import com.ruowei.web.rest.vm.SituationAnalysisQM;
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
    public ResponseEntity<List<SituationVM>> situationAnalysisFirst(@RequestBody List<SituationAnalysisQM> situationAnalysisQMS,
                                                                    @ApiParam(value = "数据所在工艺Id") @RequestParam String craftCode,
                                                                    @ApiParam(value = "日期分段") @RequestParam String subsection,
                                                                    @ApiParam(value = "开始时间") @RequestParam String beginTime,
                                                                    @ApiParam(value = "结束时间") @RequestParam String endTime) {

        List<SituationVM> situationVMS = new ArrayList<>();
        for (SituationAnalysisQM s : situationAnalysisQMS) {
            situationVMS.add(situationAnalysisUtil.get(beginTime, endTime, subsection, s.getTarget(), craftCode));
        }
        return ResponseEntity.ok().body(situationVMS);
    }

    @PostMapping("/situation_analysis_second")
    @ApiOperation(value = "态势分析图二", notes = "作者：董玉祥")
    public ResponseEntity<List<SituationVM>> situationAnalysisSecond(@RequestBody SituationAnalysisQM situationAnalysisQM,
                                                                     @ApiParam(value = "数据所在工艺Id") @RequestParam String craftCode,
                                                                     @ApiParam(value = "日期分段") @RequestParam String subsection,
                                                                     @ApiParam(value = "开始时间") @RequestParam List<DateTimeRangeVM> datetimeRange) {

        List<SituationVM> situationVMS = new ArrayList<>();
        for (DateTimeRangeVM dateTimeRangeVM : datetimeRange) {
            situationVMS.add(situationAnalysisUtil.get(dateTimeRangeVM.getBeginTime(), dateTimeRangeVM.getEndTime(), subsection, situationAnalysisQM.getTarget(), craftCode));
        }
        return ResponseEntity.ok().body(situationVMS);
    }

    @PostMapping("/situation_analysis_thirdly")
    @ApiOperation(value = "态势分析图三", notes = "作者：董玉祥")
    public ResponseEntity<List<SituationVM>> situationAnalysisThirdly(@RequestBody List<SituationAnalysisQM> situationAnalysisQMS,
                                                                      @ApiParam(value = "数据所在工艺Id") @RequestParam String craftCode,
                                                                      @ApiParam(value = "日期分段") @RequestParam String subsection,
                                                                      @ApiParam(value = "开始时间") @RequestParam String beginTime,
                                                                      @ApiParam(value = "结束时间") @RequestParam String endTime) {

        List<SituationVM> situationVMS = new ArrayList<>();
        for (SituationAnalysisQM s : situationAnalysisQMS) {
            situationVMS.add(situationAnalysisUtil.get(beginTime, endTime, subsection, s.getTarget(), craftCode));
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
        situationVMS.add(situationAnalysisUtil.get(beginTime, endTime, subsection, beAssociated.getBeAssociatedName(), craftCode));
        for (Correlation correlationsList : correlations) {
            situationVMS.add(situationAnalysisUtil.get(beginTime, endTime, subsection, correlationsList.getRelationTarget(), craftCode));
        }
        return ResponseEntity.ok().body(situationVMS);

    }

}
