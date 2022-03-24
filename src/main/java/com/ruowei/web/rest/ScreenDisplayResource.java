package com.ruowei.web.rest;


import com.ruowei.domain.Craft;
import com.ruowei.repository.CraftRepository;
import com.ruowei.repository.EnterpriseRepository;
import com.ruowei.security.UserModel;
import com.ruowei.web.rest.vm.CraftQM;
import com.ruowei.web.rest.vm.SituationAnalysisQM;
import com.ruowei.web.rest.vm.SituationAnalysisVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "大屏展示")
@Transactional
public class ScreenDisplayResource {

    private EnterpriseRepository enterpriseRepository;
    private CraftRepository craftRepository;

    public ScreenDisplayResource(EnterpriseRepository enterpriseRepository, CraftRepository craftRepository) {
        this.enterpriseRepository = enterpriseRepository;
        this.craftRepository = craftRepository;
    }


    @PostMapping("/situatiddddd")
    @ApiOperation(value = "水厂首页——获取工艺信息", notes = "作者：董玉祥")
    public ResponseEntity<List<CraftQM>> situationAnalysisFirst(@ApiIgnore @AuthenticationPrincipal UserModel userModel) {

        List<CraftQM> craftQMList = new ArrayList<>();
        for (Craft c:craftRepository.findByEntCode(userModel.getcode())) {
            CraftQM craftQM = new CraftQM();
            craftQM.setId(c.getId());
            craftQM.setName(c.getCraftName());
            craftQMList.add(craftQM);
        }
        return ResponseEntity.ok().body(craftQMList);
    }

    @PostMapping("/situati")
    @ApiOperation(value = "水厂首页——数据表信息获取", notes = "作者：董玉祥")
    public ResponseEntity<List<SituationAnalysisVM>> situationAnalysisFirst(@RequestBody List<SituationAnalysisQM> situationAnalysisQMS,
                                                                            @ApiParam(value = "数据所在工艺Id") @RequestParam Long craftId) {

        /*List<BigDecimal> list = new ArrayList<>();
        for (SituationAnalysisQM s:situationAnalysisQMS) {
            list = situationAnalysisUtil.get(beginTime,endTime,subsection,s.getSource(),s.getTarget(),craftId);
        }*/
        List<SituationAnalysisVM> list = new ArrayList<>();
        /*for (SituationAnalysisQM s:situationAnalysisQMS) {
            list = sewProcessRepository.findDateValue(craftId);
        }*/
//
//        System.out.println(userModel.getUserId());
//        System.out.println(userModel.getEnterpriseId());

        return ResponseEntity.ok().body(list);
    }


}
