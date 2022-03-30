package com.ruowei.web.rest;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.BeAssociated;

import com.ruowei.domain.QBeAssociated;
import com.ruowei.repository.BeAssociatedRepository;
import com.ruowei.security.UserModel;
import com.ruowei.util.PageUtil;
import com.ruowei.web.rest.errors.BadRequestProblem;
import com.ruowei.web.rest.vm.CollectQM;
import com.ruowei.web.rest.vm.RelevanceBeVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(tags = "关联数据管理")
@Transactional
public class RelevanceResource {

    private final Logger log = LoggerFactory.getLogger(RelevanceResource.class);


    private final BeAssociatedRepository beAssociatedRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final QBeAssociated qBeAssociated = QBeAssociated.beAssociated;

    public RelevanceResource(BeAssociatedRepository beAssociatedRepository, JPAQueryFactory jpaQueryFactory) {

        this.beAssociatedRepository = beAssociatedRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @PostMapping("/relevance")
    @ApiOperation(value = "关联信息新增", notes = "作者：董玉祥")
    public ResponseEntity<String> relevance(@RequestBody CollectQM qm, @ApiIgnore @AuthenticationPrincipal UserModel userModel) {

        if (userModel.getcode() == null) {
            throw new BadRequestProblem("新增失败", "您不能进行关联数据的的新增操作");
        }

        beAssociatedRepository.findByBeAssociatedName(qm.getBeAssociated())
            .ifPresent(x -> {
                throw new BadRequestProblem("新增失败", "该数据已经存在关联，请前往编辑进行编辑");
            });
        BeAssociated beAssociated = new BeAssociated();
        beAssociated.setBeAssociatedName(qm.getBeAssociated());
        beAssociated.setRelationTarget(qm.getRelation().stream()
            .filter(string -> !string.isEmpty()).collect(Collectors.joining(",")));
        beAssociated.setBeAssociatedEnterpriseCode(userModel.getcode());
        beAssociatedRepository.save(beAssociated);
        return ResponseEntity.ok().body("新增成功");
    }

    @PostMapping("/relevance_modify")
    @ApiOperation(value = "编辑关联信息", notes = "作者：董玉祥")
    public ResponseEntity<String> relevanceModify(@RequestBody CollectQM qm, @ApiIgnore @AuthenticationPrincipal UserModel userModel) {

        if (StringUtils.isBlank(userModel.getcode())) {
            throw new BadRequestProblem("编辑失败", "您不能进行关联数据的的编辑操作");
        }
        BeAssociated beAssociated = beAssociatedRepository.findById(qm.getId())
            .orElseThrow(() -> {
                throw new BadRequestProblem("修改失败", "该数据无关联信息，请先创建再进行修改");
            });
        beAssociated.setBeAssociatedName(qm.getBeAssociated());
        beAssociated.setRelationTarget(qm.getRelation().stream()
            .filter(string -> !string.isEmpty()).collect(Collectors.joining(",")));
        beAssociated.setBeAssociatedEnterpriseCode(userModel.getcode());
        beAssociatedRepository.save(beAssociated);

        return ResponseEntity.ok().body("修改成功");
    }

    @PostMapping("/relevance_modify_details")
    @ApiOperation(value = "编辑关联信息前获取详情", notes = "作者：董玉祥")
    public ResponseEntity<CollectQM> relevanceModify(@RequestParam Long id) {

        BeAssociated beAssociated = beAssociatedRepository.findById(id).orElseThrow(() -> {
            throw new BadRequestProblem("编辑失败", "该数据无关联信息，请先创建再进行编辑");
        });
        CollectQM collectQM = new CollectQM();
        collectQM.setBeAssociated(beAssociated.getBeAssociatedName());
        collectQM.setRelation(Arrays.asList(beAssociated.getRelationTarget().split(",")));
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
            //被关联数据id
            collectQM.setId(b.getId());
            collectQM.setTotal(total);
            //存储被关联数据信息
            collectQM.setBeAssociated(b.getBeAssociatedName());
            //关联数据
            collectQM.setRelation(Arrays.asList(b.getRelationTarget().split(",")));
            collectQMS.add(collectQM);
        }

        if (collectQMS.size() == 0) {
            return ResponseEntity.ok().body(collectQMS);
        } else {
            return ResponseEntity.ok().body(PageUtil.startPage(collectQMS, Integer.valueOf(page), Integer.valueOf(size)));
        }

    }

    @PostMapping("/relevance_delete")
    @ApiOperation(value = "关联信息删除", notes = "作者：董玉祥")
    public ResponseEntity<String> relevanceDelete(Long id, @ApiIgnore @AuthenticationPrincipal UserModel userModel) {

        if (StringUtils.isBlank(userModel.getcode())) {
            throw new BadRequestProblem("删除失败", "您不能进行关联数据的的删除操作");
        }
        beAssociatedRepository.delete(beAssociatedRepository.findById(id)
            .orElseThrow(() -> {
                throw new BadRequestProblem("删除失败", "该数据无关联信息，请先创建再进行删除");
            }));
        return ResponseEntity.ok().body("删除成功");
    }

    @PostMapping("/relevance_be_list")
    @ApiOperation(value = "获取被关联的数据", notes = "作者：董玉祥")
    public ResponseEntity<List<RelevanceBeVM>> relevanceName() {

        List<RelevanceBeVM> relevanceBeVMS = new ArrayList<>();
        for (BeAssociated beAssociated : beAssociatedRepository.findAll()) {
            RelevanceBeVM relevanceBeVM = new RelevanceBeVM();
            relevanceBeVM.setId(beAssociated.getId());
            relevanceBeVM.setTarget(beAssociated.getBeAssociatedName());
            relevanceBeVMS.add(relevanceBeVM);
        }
        return ResponseEntity.ok().body(relevanceBeVMS);
    }

}
