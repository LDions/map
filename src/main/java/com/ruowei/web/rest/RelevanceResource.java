package com.ruowei.web.rest;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.BeAssociated;
import com.ruowei.domain.Correlation;

import com.ruowei.domain.QBeAssociated;
import com.ruowei.repository.BeAssociatedRepository;
import com.ruowei.repository.CorrelationRepository;
import com.ruowei.util.PageUtil;
import com.ruowei.web.rest.dto.EntCraftDataDTO;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.CollectQM;
import com.ruowei.web.rest.vm.SituationAnalysisQM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "关联数据管理")
@Transactional
public class RelevanceResource {

    private final Logger log = LoggerFactory.getLogger(RelevanceResource.class);


    private final BeAssociatedRepository beAssociatedRepository;
    private final CorrelationRepository correlationRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final QBeAssociated qBeAssociated = QBeAssociated.beAssociated;

    public RelevanceResource(BeAssociatedRepository beAssociatedRepository, CorrelationRepository correlationRepository, JPAQueryFactory jpaQueryFactory) {

        this.beAssociatedRepository = beAssociatedRepository;
        this.correlationRepository = correlationRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @PostMapping("/relevance")
    @ApiOperation(value = "关联信息新增", notes = "作者：董玉祥")
    public ResponseEntity<String> relevance(@RequestBody CollectQM qm) {

        beAssociatedRepository.findByBeAssociatedName(qm.getBeAssociated().getTarget())
            .ifPresent(x -> {
                throw new BadRequestProblem("新增失败", "该数据已经存在关联，请前往编辑进行编辑");
            });
        BeAssociated beAssociated = new BeAssociated();
        beAssociated.setBeAssociatedName(qm.getBeAssociated().getTarget());
        beAssociated.setBeAssociatedSource(qm.getBeAssociated().getSource());
        long id = beAssociatedRepository.save(beAssociated).getId();
        for (SituationAnalysisQM situationAnalysisQM : qm.getRelation()) {
            Correlation correlation = new Correlation();
            correlation.setRelationTarget(situationAnalysisQM.getTarget());
            correlation.setRelationSource(situationAnalysisQM.getSource());
            correlation.setRelevanceId(id);
            correlationRepository.save(correlation);
        }

        return ResponseEntity.ok().body("新增成功");
    }

    @PostMapping("/relevance_modify")
    @ApiOperation(value = "编辑关联信息", notes = "作者：董玉祥")
    public ResponseEntity<String> relevanceModify(@RequestBody CollectQM qm) {

        BeAssociated beAssociated = beAssociatedRepository.findById(qm.getId())
            .orElseThrow(() -> {
                throw new BadRequestProblem("修改失败", "该数据无关联信息，请先创建再进行修改");
            });
        beAssociated.setBeAssociatedName(qm.getBeAssociated().getTarget());
        beAssociated.setBeAssociatedSource(qm.getBeAssociated().getSource());
        beAssociatedRepository.save(beAssociated);
        correlationRepository.deleteAllByRelevanceId(qm.getId());
        for (SituationAnalysisQM situationAnalysisQM : qm.getRelation()) {
            Correlation correlation = new Correlation();
            correlation.setRelationTarget(situationAnalysisQM.getTarget());
            correlation.setRelationSource(situationAnalysisQM.getSource());
            correlation.setRelevanceId(qm.getId());
            correlationRepository.save(correlation);
        }
        return ResponseEntity.ok().body("修改成功");
    }

    @PostMapping("/relevance_modify_details")
    @ApiOperation(value = "编辑关联信息前获取详情", notes = "作者：董玉祥")
    public ResponseEntity<CollectQM> relevanceModify(@RequestParam Long id) {


        BeAssociated beAssociated = beAssociatedRepository.findById(id).orElseThrow(() -> {
            throw new BadRequestProblem("删除失败", "该数据无关联信息，请先创建再进行删除");
        });
        CollectQM collectQM = new CollectQM();
        SituationAnalysisQM situationAnalysisQM = new SituationAnalysisQM();
        collectQM.setId(id);
        situationAnalysisQM.setTarget(beAssociated.getBeAssociatedName());
        situationAnalysisQM.setSource(beAssociated.getBeAssociatedSource());
        collectQM.setBeAssociated(situationAnalysisQM);
        List<Correlation> correlations = correlationRepository.findByRelevanceId(beAssociated.getId());
        List<SituationAnalysisQM> situationAnalysisQMS = new ArrayList<>();
        for (Correlation correlation : correlations) {
            SituationAnalysisQM situationAnalysisQM1 = new SituationAnalysisQM();
            situationAnalysisQM1.setTarget(correlation.getRelationTarget());
            situationAnalysisQM1.setSource(correlation.getRelationSource());
            situationAnalysisQMS.add(situationAnalysisQM1);
            collectQM.setRelation(situationAnalysisQMS);
        }

        return ResponseEntity.ok().body(collectQM);
    }

    @PostMapping("/relevance_list")
    @ApiOperation(value = "关联信息列表 —— 带分页", notes = "作者：董玉祥")
    public ResponseEntity<List<CollectQM>> relevanceList(@RequestParam String page, @RequestParam String size) {

        long total = jpaQueryFactory
            .selectFrom(qBeAssociated)
            .stream().count();
        List<CollectQM> collectQMS = new ArrayList<>();
        //获取被关联数据
        List<BeAssociated> beAssociateds = beAssociatedRepository.findAll();
        for (BeAssociated b : beAssociateds) {
            CollectQM collectQM = new CollectQM();
            SituationAnalysisQM situationAnalysisQM = new SituationAnalysisQM();
            //被关联数据id
            collectQM.setId(b.getId());
            collectQM.setTotal(total);
            //被关联数据信息
            situationAnalysisQM.setTarget(b.getBeAssociatedName());
            situationAnalysisQM.setSource(b.getBeAssociatedSource());
            //存储
            collectQM.setBeAssociated(situationAnalysisQM);
            //通过被关联数据id查关联数据
            List<Correlation> correlations = correlationRepository.findByRelevanceId(b.getId());
            List<SituationAnalysisQM> situationAnalysisQMS = new ArrayList<>();
            for (Correlation correlation : correlations) {
                SituationAnalysisQM situationAnalysisQM1 = new SituationAnalysisQM();
                situationAnalysisQM1.setTarget(correlation.getRelationTarget());
                situationAnalysisQM1.setSource(correlation.getRelationSource());
                situationAnalysisQMS.add(situationAnalysisQM1);
                collectQM.setRelation(situationAnalysisQMS);
            }
            collectQMS.add(collectQM);
        }

        return ResponseEntity.ok().body(PageUtil.startPage(collectQMS, Integer.valueOf(page), Integer.valueOf(size)));
    }

    @PostMapping("/relevance_delete")
    @ApiOperation(value = "关联信息删除", notes = "作者：董玉祥")
    public ResponseEntity<String> relevanceDelete(Long id) {

        beAssociatedRepository.delete(beAssociatedRepository.findById(id)
            .orElseThrow(() -> {
                throw new BadRequestProblem("删除失败", "该数据无关联信息，请先创建再进行删除");
            }));
        correlationRepository.deleteAllByRelevanceId(id);
        return ResponseEntity.ok().body("删除成功");
    }

}
