package com.ruowei.util;

import com.ruowei.repository.SewMeterRepository;
import com.ruowei.repository.SewProcessRepository;
import com.ruowei.repository.SewSluRepository;
import com.ruowei.service.SewEmiService;
import com.ruowei.web.rest.vm.SituationAnalysisVM;
import com.ruowei.web.rest.vm.SituationVM;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class SituationAnalysisUtil {

    private final SelectUtil selectUtil;
    private final SewEmiService sewEmiService;
    private final SewProcessRepository sewProcessRepository;
    private final SewSluRepository sewSluRepository;
    private final SewMeterRepository sewMeterRepository;

    public SituationAnalysisUtil(SelectUtil selectUtil, SewEmiService sewEmiService, SewProcessRepository sewProcessRepository, SewSluRepository sewSluRepository, SewMeterRepository sewMeterRepository) {
        this.selectUtil = selectUtil;
        this.sewEmiService = sewEmiService;
        this.sewProcessRepository = sewProcessRepository;
        this.sewSluRepository = sewSluRepository;
        this.sewMeterRepository = sewMeterRepository;
    }


    //根据传入的时间段的长度不同，算不同时间段的平均数,封装到SituationVM
    public SituationVM getSituationVM(String beginTime, String endTime, String subsection, String target, String craftCode) {

        SituationVM situationVM = new SituationVM();
        List<SituationAnalysisVM> situationAnalysisVMS = new ArrayList<>();
        List<SituationAnalysisVM> situationAnalysisVMList = new ArrayList<>();
        situationAnalysisVMS = selectUtil.getSome(target, beginTime, endTime, craftCode);

        Double avg;
        Double sum = Double.valueOf(0);
        Double sumValue = Double.valueOf(0);
        Calendar calendar = situationAnalysisVMS.get(0).getTime();
        switch (subsection) {
            case "date":
                situationVM.setTarget(target);
                situationVM.setValues(situationAnalysisVMS);
                break;
            case "week":
                situationVM.setTarget(target);
                for (SituationAnalysisVM s : situationAnalysisVMS) {
                    if (calendar.after(s.getTime())) {
                        sum = sum + 1;
                        sumValue = sumValue + s.getValue().toBigInteger().doubleValue();
                    } else {
                        sum = sum + 1;
                        sumValue = sumValue + s.getValue().toBigInteger().doubleValue();
                        SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                        avg = sumValue/sum;
                        situationAnalysisVM.setValue(BigDecimal.valueOf(avg));
                        situationAnalysisVM.setTime(s.getTime());
                        situationAnalysisVMList.add(situationAnalysisVM);
                        sum = Double.valueOf(0);
                        sumValue = Double.valueOf(0);
                        calendar.add(Calendar.HOUR, 1);
                    }
                }
                //calendar是单例模式，降低一个数据的时间还原
                calendar = GregorianCalendar.from(ZonedDateTime.ofInstant(sewEmiService.getInstant(beginTime), ZoneId.systemDefault()));
                situationVM.setValues(situationAnalysisVMList);
                break;
           /* case "month":
                for (SituationAnalysisVM s : situationAnalysisVMS) {
                    calendar = situationAnalysisVMS.get(0).getTime();
                    calendar.add(Calendar.DATE, 1);
                    if (s.getTime().before(calendar)) {
                        sum.add(BigDecimal.valueOf(1));
                        sumValue.add(s.getValue());
                    } else {
                        situationVM.setSource(source);
                        situationVM.setTarget(target);
                        SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                        avg = sum.divide(sumValue);
                        situationAnalysisVM.setValue(avg);
                        situationAnalysisVM.setTime(s.getTime());
                        situationVM.setValues(situationAnalysisVMS);
                        sum = new BigDecimal("1");
                        sumValue = new BigDecimal("0.000001");
                        calendar.add(Calendar.DATE, 1);
                    }
                }
                break;
            case "quarter":
                for (SituationAnalysisVM s : situationAnalysisVMS) {
                    calendar = situationAnalysisVMS.get(0).getTime();
                    calendar.add(Calendar.DATE, 7);
                    if (s.getTime().before(calendar)) {
                        sum.add(BigDecimal.valueOf(1));
                        sumValue.add(s.getValue());
                    } else {
                        situationVM.setSource(source);
                        situationVM.setTarget(target);
                        SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                        avg = sum.divide(sumValue);
                        situationAnalysisVM.setValue(avg);
                        situationAnalysisVM.setTime(s.getTime());
                        situationVM.setValues(situationAnalysisVMS);
                        sum = new BigDecimal("1");
                        sumValue = new BigDecimal("0.000001");
                        calendar.add(Calendar.DATE, 7);
                    }
                }
                break;
            case "year":
                for (SituationAnalysisVM s : situationAnalysisVMS) {
                    calendar = situationAnalysisVMS.get(0).getTime();
                    calendar.add(Calendar.DATE, 7);
                    if (s.getTime().before(calendar)) {
                        sum.add(BigDecimal.valueOf(1));
                        sumValue.add(s.getValue());
                    } else {
                        situationVM.setSource(source);
                        situationVM.setTarget(target);
                        SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                        avg = sum.divide(sumValue);
                        situationAnalysisVM.setValue(avg);
                        situationAnalysisVM.setTime(s.getTime());
                        situationVM.setValues(situationAnalysisVMS);
                        sum = new BigDecimal("1");
                        sumValue = new BigDecimal("0.000001");
                        calendar.add(Calendar.DATE, 7);
                    }
                }
                break;*/
            default:
                throw new IllegalStateException("Unexpected value: " + subsection);
        }
        return situationVM;

    }

    //根据传入的时间段的长度不同，算不同时间段的平均数,封装到SituationAnalysisVM
    public List<SituationAnalysisVM> getSituationAnalysisVM(String beginTime, String endTime, String subsection, String target, String craftCode) {

        SituationVM situationVM = new SituationVM();
        List<SituationAnalysisVM> situationAnalysisVMS = new ArrayList<>();
        List<SituationAnalysisVM> situationAnalysisVMList = new ArrayList<>();
        situationAnalysisVMS = selectUtil.getSome(target, beginTime, endTime, craftCode);

        Double avg = Double.valueOf(0);
        Double sum = Double.valueOf(0);
        Double sumValue = Double.valueOf(0);
        Calendar calendar = situationAnalysisVMS.get(0).getTime();
        calendar.add(Calendar.HOUR, 1);
        int time = -1;
        switch (subsection) {
            case "date":
                situationAnalysisVMList = situationAnalysisVMS;
                break;
            case "week":
                situationVM.setTarget(target);
                for (SituationAnalysisVM s : situationAnalysisVMS) {
                    if (s.getTime().before(calendar)) {
                        sum = sum + 1;
                        sumValue = sumValue + s.getValue().toBigInteger().doubleValue();
                    } else {
                        calendar.add(Calendar.HOUR, 1);
                        time=time-1;
                        sum = sum + 1;
                        sumValue = sumValue + s.getValue().toBigInteger().doubleValue();
                        SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                        avg = sumValue/sum;
                        situationAnalysisVM.setValue(BigDecimal.valueOf(avg));
                        situationAnalysisVM.setTime(s.getTime());
                        situationAnalysisVMList.add(situationAnalysisVM);
                        sum = Double.valueOf(0);
                        sumValue = Double.valueOf(0);
                        calendar.add(Calendar.HOUR, 1);
                    }
                }
                calendar.add(Calendar.HOUR, time);
                break;
           /* case "month":
                for (SituationAnalysisVM s : situationAnalysisVMS) {
                    calendar = situationAnalysisVMS.get(0).getTime();
                    calendar.add(Calendar.DATE, 1);
                    if (s.getTime().before(calendar)) {
                        sum.add(BigDecimal.valueOf(1));
                        sumValue.add(s.getValue());
                    } else {
                        situationVM.setSource(source);
                        situationVM.setTarget(target);
                        SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                        avg = sum.divide(sumValue);
                        situationAnalysisVM.setValue(avg);
                        situationAnalysisVM.setTime(s.getTime());
                        situationVM.setValues(situationAnalysisVMS);
                        sum = new BigDecimal("1");
                        sumValue = new BigDecimal("0.000001");
                        calendar.add(Calendar.DATE, 1);
                    }
                }
                break;
            case "quarter":
                for (SituationAnalysisVM s : situationAnalysisVMS) {
                    calendar = situationAnalysisVMS.get(0).getTime();
                    calendar.add(Calendar.DATE, 7);
                    if (s.getTime().before(calendar)) {
                        sum.add(BigDecimal.valueOf(1));
                        sumValue.add(s.getValue());
                    } else {
                        situationVM.setSource(source);
                        situationVM.setTarget(target);
                        SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                        avg = sum.divide(sumValue);
                        situationAnalysisVM.setValue(avg);
                        situationAnalysisVM.setTime(s.getTime());
                        situationVM.setValues(situationAnalysisVMS);
                        sum = new BigDecimal("1");
                        sumValue = new BigDecimal("0.000001");
                        calendar.add(Calendar.DATE, 7);
                    }
                }
                break;
            case "year":
                for (SituationAnalysisVM s : situationAnalysisVMS) {
                    calendar = situationAnalysisVMS.get(0).getTime();
                    calendar.add(Calendar.DATE, 7);
                    if (s.getTime().before(calendar)) {
                        sum.add(BigDecimal.valueOf(1));
                        sumValue.add(s.getValue());
                    } else {
                        situationVM.setSource(source);
                        situationVM.setTarget(target);
                        SituationAnalysisVM situationAnalysisVM = new SituationAnalysisVM();
                        avg = sum.divide(sumValue);
                        situationAnalysisVM.setValue(avg);
                        situationAnalysisVM.setTime(s.getTime());
                        situationVM.setValues(situationAnalysisVMS);
                        sum = new BigDecimal("1");
                        sumValue = new BigDecimal("0.000001");
                        calendar.add(Calendar.DATE, 7);
                    }
                }
                break;*/
            default:
                throw new IllegalStateException("Unexpected value: " + subsection);
        }
        return situationAnalysisVMList;

    }
}
