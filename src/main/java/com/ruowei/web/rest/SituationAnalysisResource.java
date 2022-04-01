package com.ruowei.web.rest;


import com.ruowei.domain.BeAssociated;
import com.ruowei.repository.*;
import com.ruowei.service.SewEmiService;
import com.ruowei.util.SelectUtil;
import com.ruowei.util.SituationAnalysisUtil;
import com.ruowei.web.rest.dto.SituationAnalysisFirstDTO;
import com.ruowei.web.rest.dto.SituationAnalysisFourthlyDTO;
import com.ruowei.web.rest.dto.SituationAnalysisSecondDTO;
import com.ruowei.web.rest.vm.DateTimeRangeVM;
import com.ruowei.web.rest.vm.SituationAnalysisVM;
import com.ruowei.web.rest.vm.SituationVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
    private final SewEmiService sewEmiService;
    private final SelectUtil selectUtil;

    public SituationAnalysisResource(SituationAnalysisUtil situationAnalysisUtil, SewProcessRepository sewProcessRepository, SewSluRepository sewSluRepository, SewMeterRepository sewMeterRepository, SewEmiService sewEmiService, SelectUtil selectUtil, BeAssociatedRepository beAssociatedRepository, SelectUtil selectUtil1) {

        this.situationAnalysisUtil = situationAnalysisUtil;
        this.sewProcessRepository = sewProcessRepository;
        this.sewSluRepository = sewSluRepository;
        this.sewMeterRepository = sewMeterRepository;
        this.sewEmiService = sewEmiService;
        this.beAssociatedRepository = beAssociatedRepository;
        this.selectUtil = selectUtil1;
    }

    @PostMapping("/situation_analysis_first")
    @ApiOperation(value = "态势分析图一", notes = "作者：董玉祥")
    public ResponseEntity<List<SituationVM>> situationAnalysisFirst(@RequestBody SituationAnalysisFirstDTO situationAnalysisDTO) {

        List<SituationVM> situationVMS = new ArrayList<>();
        for (String s : situationAnalysisDTO.getTargets()) {
            situationVMS.add(situationAnalysisUtil.getSituationVM(situationAnalysisDTO.getBeginTime(), situationAnalysisDTO.getEndTime(),situationAnalysisDTO.getSubsection(), s, situationAnalysisDTO.getCraftCode()));
        }
        return ResponseEntity.ok().body(situationVMS);
    }

    @PostMapping("/situation_analysis_second")
    @ApiOperation(value = "态势分析图二", notes = "作者：董玉祥")
    public ResponseEntity<List<List<SituationAnalysisVM>>> situationAnalysisSecond(@RequestBody SituationAnalysisSecondDTO situationAnalysisTwoDTO) {

        List<List<SituationAnalysisVM>> situationAnalysisVMLists = new ArrayList<>();
        for (DateTimeRangeVM dateTimeRangeVM : situationAnalysisTwoDTO.getDatetimeRange()) {
            situationAnalysisVMLists.add(situationAnalysisUtil.getSituationAnalysisVM(dateTimeRangeVM.getBeginTime(), dateTimeRangeVM.getEndTime(), situationAnalysisTwoDTO.getSubsection(), situationAnalysisTwoDTO.getTarget(), situationAnalysisTwoDTO.getCraftCode()));
        }
        return ResponseEntity.ok().body(situationAnalysisVMLists);
    }

    @PostMapping("/situation_analysis_thirdly")
    @ApiOperation(value = "态势分析图三", notes = "作者：董玉祥")
    public ResponseEntity<List<SituationVM>> situationAnalysisThirdly(@RequestBody SituationAnalysisFirstDTO situationAnalysisDTO) {



        List<SituationVM> situationVMS = new ArrayList<>();
        for (String s : situationAnalysisDTO.getTargets()) {
            situationVMS.add(situationAnalysisUtil.getSituationVM(situationAnalysisDTO.getBeginTime(), situationAnalysisDTO.getEndTime(),situationAnalysisDTO.getSubsection(), s, situationAnalysisDTO.getCraftCode()));
        }
        return ResponseEntity.ok().body(situationVMS);

    }

    @PostMapping("/situation_analysis_fourthly")
    @ApiOperation(value = "态势分析图四", notes = "作者：董玉祥")
    public ResponseEntity<List<SituationVM>> situationAnalysisFourthly(@RequestBody SituationAnalysisFourthlyDTO dto) {

        List<SituationVM> situationVMS = new ArrayList<>();
        BeAssociated beAssociated = beAssociatedRepository.findById(dto.getId()).get();
        situationVMS.add(situationAnalysisUtil.getSituationVM(dto.getBeginTime(), dto.getEndTime(), dto.getSubsection(), beAssociated.getBeAssociatedName(), dto.getCraftCode()));
        for (String target : Arrays.asList(beAssociated.getRelationTarget().split(","))) {
            situationVMS.add(situationAnalysisUtil.getSituationVM(dto.getBeginTime(), dto.getEndTime(), dto.getSubsection(), target, dto.getCraftCode()));
        }
        return ResponseEntity.ok().body(situationVMS);

    }

}
