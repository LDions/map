
package com.ruowei.web.rest;

import com.ruowei.domain.Craft;
import com.ruowei.domain.Enterprise;
import com.ruowei.domain.SewMeter;
import com.ruowei.domain.SewProcess;
import com.ruowei.repository.CraftRepository;
import com.ruowei.repository.EnterpriseRepository;
import com.ruowei.repository.SewMeterRepository;
import com.ruowei.repository.SewProcessRepository;
import com.ruowei.service.Calculation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(tags = "手动计算")
public class CalculationResource {

    private final EnterpriseRepository enterpriseRepository;
    private final ForecastResource forecastResource;
    private final SewMeterRepository sewMeterRepository;
    private final SewProcessRepository sewProcessRepository;
    private final CraftRepository craftRepository;
    BigDecimal A = null;
    BigDecimal B = null;
    BigDecimal Q = null;
    //    缺氧区需去除硝酸盐量
    BigDecimal N = null;
    BigDecimal D = null;
    //    外投加碳源量
    BigDecimal T = null;
    //    加药泵流量
    BigDecimal I = null;

    public CalculationResource(EnterpriseRepository enterpriseRepository,
                               ForecastResource forecastResource,
                               SewMeterRepository sewMeterRepository,
                               SewProcessRepository sewProcessRepository,
                               CraftRepository craftRepository) {
        this.enterpriseRepository = enterpriseRepository;
        this.forecastResource = forecastResource;
        this.sewMeterRepository = sewMeterRepository;
        this.sewProcessRepository = sewProcessRepository;
        this.craftRepository = craftRepository;
    }

    @GetMapping("/calculation")
    @ApiOperation(value = "手动碳源计算", notes = "作者：孙小楠")
    public ResponseEntity<List> calculation(Long id, @RequestParam(value = "hours", required = false) List<Instant> hours, String craftCode) {
        List<String> list = forecastResource.forecastTn(id, hours, craftCode);
        List<Calculation> calculations = new ArrayList<>();
        Craft craft = craftRepository.findFirstByOrderByIdDesc();
        BigDecimal A2 = craft.getAerobioticNitrateConcentration();
        BigDecimal B2 = craft.getAnoxiaNitrateConcentration();
        BigDecimal C = craft.getNitrateRefluxRatio();
        BigDecimal E = craft.getBodCodRatio();
        BigDecimal K = craft.getCodCalibration();
        BigDecimal F = craft.getBodNRatio();
        BigDecimal G = craft.getBodEquivalentWeight();
        BigDecimal H = craft.getIntimacy();
        BigDecimal J = craft.getDilutionRatio();
        for (String s : list) {
            BigDecimal big = new BigDecimal(s);
            Calculation calculation = new Calculation();
            //      判断是否试点
            Optional<Enterprise> enterprise = enterpriseRepository.findById(id);
            Boolean isTry;
            if (enterprise.isPresent()) {
                isTry = enterprise.get().getIsTry();
                if (isTry.equals(true)) {
                    //        试点（校表）
                    SewMeter sewMeter = sewMeterRepository.findFirstByOrderByIdDesc();
                    SewProcess sewProcess = sewProcessRepository.findFirstByOrderByIdDesc();
                    A = big.multiply(new BigDecimal(0.8));
                    B = B2;
                    Q = sewProcess.getInFlow();
                    D = sewMeter.getCorInCod();
                    N = ((((A.multiply(new BigDecimal(2)).subtract(A2))
                        .multiply(C.subtract(new BigDecimal(1))))
                        .add(((B.subtract(B2)).multiply(C.add(new BigDecimal(1)))))).multiply(Q))
                        .divide(new BigDecimal(1000),2, RoundingMode.HALF_UP);
                    T = (N.multiply(F)
                        .subtract(D.multiply(E).multiply(Q).multiply(K).divide(new BigDecimal(1000),2, RoundingMode.HALF_UP)))
                        .divide(G,2, RoundingMode.HALF_UP);
                    I = T.divide(H,2, RoundingMode.HALF_UP).divide(new BigDecimal(1000),2, RoundingMode.HALF_UP).multiply(J);
                    calculation.setNitrateToBeRemoved(N);
                    calculation.setAdditionOfCarbonSource(T);
                    calculation.setDosingPumpFlow(I);
                } else {
                    //      非试点（仪表）
                    SewProcess sewProcess = sewProcessRepository.findFirstByOrderByIdDesc();
                    A = big.multiply(new BigDecimal(0.8));
                    B = B2;
                    Q = sewProcess.getInFlow();
                    D = sewProcess.getInCod();
                    N = ((((A.multiply(new BigDecimal(2)).subtract(A2))
                        .multiply(C.subtract(new BigDecimal(1))))
                        .add(((B.subtract(B2)).multiply(C.add(new BigDecimal(1)))))).multiply(Q))
                        .divide(new BigDecimal(1000),2, RoundingMode.HALF_UP);
                    T = (N.multiply(F)
                        .subtract(D.multiply(E).multiply(Q).multiply(K).divide(new BigDecimal(1000),2, RoundingMode.HALF_UP)))
                        .divide(G,2, RoundingMode.HALF_UP);
                    I = T.divide(H,2, RoundingMode.HALF_UP).divide(new BigDecimal(1000),2, RoundingMode.HALF_UP).multiply(J);
                    calculation.setNitrateToBeRemoved(N);
                    calculation.setAdditionOfCarbonSource(T);
                    calculation.setDosingPumpFlow(I);
                }
            }
            calculations.add(calculation);
        }
        return ResponseEntity.ok(calculations);
    }

}
