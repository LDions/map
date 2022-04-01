package com.ruowei.web.rest;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.domain.Craft;
import com.ruowei.domain.EmiData;
import com.ruowei.domain.QSewProcess;
import com.ruowei.domain.SewProcess;
import com.ruowei.repository.CraftRepository;
import com.ruowei.repository.EmiDataRepository;
import com.ruowei.repository.EnterpriseRepository;
import com.ruowei.repository.SewProcessRepository;
import com.ruowei.security.UserModel;
import com.ruowei.util.SelectUtil;
import com.ruowei.web.rest.dto.ScreenDisplayConformityDTO;
import com.ruowei.web.rest.dto.ScreenDisplayDTO;
import com.ruowei.web.rest.vm.CraftQM;
import com.ruowei.web.rest.vm.DateTimeRangeQM;
import com.ruowei.web.rest.vm.DateTimeRangeVM;
import com.ruowei.web.rest.vm.LineChartVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "大屏展示")
@Transactional
public class ScreenDisplayResource {

    private final EnterpriseRepository enterpriseRepository;
    private final SewProcessRepository sewProcessRepository;
    private final CraftRepository craftRepository;
    private final EmiDataRepository emiDataRepository;
    private final SelectUtil selectUtil;
    private final JPAQueryFactory jpaQueryFactory;
    private final QSewProcess qSewProcess = QSewProcess.sewProcess;

    public ScreenDisplayResource(EnterpriseRepository enterpriseRepository, SewProcessRepository sewProcessRepository, CraftRepository craftRepository, EmiDataRepository emiDataRepository, SelectUtil selectUtil, JPAQueryFactory jpaQueryFactory) {
        this.enterpriseRepository = enterpriseRepository;
        this.sewProcessRepository = sewProcessRepository;
        this.craftRepository = craftRepository;
        this.emiDataRepository = emiDataRepository;
        this.selectUtil = selectUtil;
        this.jpaQueryFactory = jpaQueryFactory;
    }


    @GetMapping("/water_plant_craft")
    @ApiOperation(value = "水厂首页——获取工艺信息", notes = "作者：董玉祥")
    public ResponseEntity<List<CraftQM>> waterPlantCraft(@ApiIgnore @AuthenticationPrincipal UserModel userModel) {

        List<CraftQM> craftQMList = new ArrayList<>();
        for (Craft c : craftRepository.findByEntCode(userModel.getcode())) {
            CraftQM craftQM = new CraftQM();
            craftQM.setCraftCode(c.getCraftCode());
            craftQM.setCraftName(c.getCraftName());
            craftQMList.add(craftQM);
        }
        return ResponseEntity.ok().body(craftQMList);
    }

    @PostMapping("/water_plant_target")
    @ApiOperation(value = "水厂首页——数据表信息获取", notes = "作者：董玉祥")
    public ResponseEntity<List<ScreenDisplayConformityDTO>> waterPlantTarget(@RequestBody List<ScreenDisplayDTO> targets) {

        List<ScreenDisplayConformityDTO> screenDisplayConformityDTOList = new ArrayList<>();
        for (ScreenDisplayDTO s : targets) {
            ScreenDisplayConformityDTO screenDisplayConformityDTO = new ScreenDisplayConformityDTO();
            screenDisplayConformityDTO.setCraftCode(s.getCraftCode());
            screenDisplayConformityDTO.setTargets(selectUtil.getSome(s.getTargets(), s.getCraftCode()));
            screenDisplayConformityDTOList.add(screenDisplayConformityDTO);
        }
        return ResponseEntity.ok().body(screenDisplayConformityDTOList);
    }

    @GetMapping("/water_plant_line_chart")
    @ApiOperation(value = "水厂首页——折线图", notes = "作者：董玉祥")
    public ResponseEntity<List<LineChartVM>> waterPlantLineChart(@ApiIgnore @AuthenticationPrincipal UserModel userModel) {

        List<LineChartVM> lineChartVMList = new ArrayList<>();
        for (Craft c : craftRepository.findByEntCode(userModel.getcode())) {
            LineChartVM lineChartVM = new LineChartVM();
            //获取最后一次提交的时间
            SewProcess sewProcess = jpaQueryFactory.selectFrom(qSewProcess)
                .where(qSewProcess.craftCode.eq(c.getCraftCode()))
                .orderBy(qSewProcess.dayTime.desc()).fetchFirst();

            lineChartVM.setCraftCode(c.getCraftCode());
            lineChartVM.setSplitPoint(sewProcess.getDayTime());

            //计算最后一次提交时间的前后六小时
            Calendar calendar = GregorianCalendar.from(ZonedDateTime.ofInstant(sewProcess.getDayTime(), ZoneId.systemDefault()));
            calendar.add(Calendar.HOUR, -6);
            Instant sixHoursAgo = calendar.toInstant();
            calendar.add(Calendar.HOUR, 12);
            Instant sixHoursLater = calendar.toInstant();

            //将时间封装为DateTimeRangeQM集合
            List<DateTimeRangeQM> dateTimeRangeQMList = new ArrayList<>();
            DateTimeRangeQM dateTimeRangeQM = new DateTimeRangeQM();
            dateTimeRangeQM.setBeginTime(sixHoursAgo);
            dateTimeRangeQM.setEndTime(sewProcess.getDayTime());
            dateTimeRangeQMList.add(dateTimeRangeQM);
            DateTimeRangeQM dateTimeRangeQM1 = new DateTimeRangeQM();
            dateTimeRangeQM1.setBeginTime(sewProcess.getDayTime());
            dateTimeRangeQM1.setEndTime(sixHoursLater);
            dateTimeRangeQMList.add(dateTimeRangeQM1);

            List<List<List<Object>>> objectss = new ArrayList<>();
            for (DateTimeRangeQM timeRangeQM : dateTimeRangeQMList) {
                List<List<Object>> objects = new ArrayList<>();
                for (EmiData emiData : emiDataRepository.findByPredictTimeIsGreaterThanEqualAndPredictTimeIsLessThanEqualAndCraftCode(timeRangeQM.getBeginTime(), timeRangeQM.getEndTime(), c.getCraftCode())) {
                    List<Object> list = new ArrayList<>();
                    list.add(emiData.getPredictTime());
                    list.add(emiData.getCarbonAdd());
                    list.add(emiData.getOutAN());
                    list.add(emiData.getTotalOutN());
                    objects.add(list);
                }

                objectss.add(objects);
            }
            lineChartVM.setChartData(objectss);
            lineChartVMList.add(lineChartVM);
        }
        return ResponseEntity.ok().body(lineChartVMList);
    }
}
