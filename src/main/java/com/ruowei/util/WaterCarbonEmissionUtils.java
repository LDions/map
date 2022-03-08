package com.ruowei.util;

import com.ruowei.service.dto.WaterCarbonEmissionEnterDTO;
import com.ruowei.service.dto.WaterCarbonEmissionOutputDTO;
import liquibase.pro.packaged.B;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WaterCarbonEmissionUtils {

    public static WaterCarbonEmissionOutputDTO modelCalculation(WaterCarbonEmissionEnterDTO wceEnterDTO) {
        // N2O_1 = ΣN2O_1_i
        BigDecimal n2o1 = new BigDecimal(0);
        // CH4_1 = ΣCH4_1_i
        BigDecimal ch41 = new BigDecimal(0);
        for (WaterCarbonEmissionEnterDTO.WaterQualityDTO wqDto : wceEnterDTO.getWaterQualityDTOList()) {
            // N2O_1_i = Qi*(TN_I-TN_O)*Ni*44/28*10^(-3)*Dayi
            n2o1 = n2o1.add(
                wqDto.getQ()
                    .multiply(wqDto.getTnI()
                        .subtract(wqDto.getTnO()))
                    .multiply(wqDto.getNi())
                    .multiply(new BigDecimal(44))
                    .divide(new BigDecimal(28), 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(Math.pow(10, -3)))
                    .multiply(wqDto.getDay())
            );
            // CH4_1_i = Qi*(COD_I-COD_O)*B0_COD*MCF*10^(-3)*Dayi
            ch41 = ch41.add(
                wqDto.getQ()
                    .multiply(wqDto.getCodI()
                        .subtract(wqDto.getCodO()))
                    .multiply(wceEnterDTO.getB0Cod())
                    .multiply(wceEnterDTO.getMcf())
                    .multiply(BigDecimal.valueOf(Math.pow(10,-3)))
                    .multiply(wqDto.getDay())
            );
        }
        // CH4_2_1 = M_sludge_1*wc/wc_k*EFS_1
        BigDecimal ch421 = wceEnterDTO.getMSludge1()
            .multiply(wceEnterDTO.getWc())
            .divide(wceEnterDTO.getWc1(), 4, RoundingMode.HALF_UP)
            .multiply(wceEnterDTO.getEfs1());
        // CH4_2_2 = M_sludge_2*wc/wc_k*EFS_2
        BigDecimal ch422 = wceEnterDTO.getMSludge2()
            .multiply(wceEnterDTO.getWc())
            .divide(wceEnterDTO.getWc2(), 4, RoundingMode.HALF_UP)
            .multiply(wceEnterDTO.getEfs2());
        // CH4_2_3 = M_sludge_3*DOC*DOCf*MCF*F*16/12*(1-OX)
        BigDecimal ch423 = wceEnterDTO.getMSludge3()
            .multiply(wceEnterDTO.getSTech3Doc())
            .multiply(wceEnterDTO.getSTech3Docf())
            .multiply(wceEnterDTO.getSTech3Mcf())
            .multiply(wceEnterDTO.getSTech3F())
            .multiply(new BigDecimal(16))
            .divide(new BigDecimal(12), 4, RoundingMode.HALF_UP)
            .multiply(new BigDecimal(1).subtract(wceEnterDTO.getSTech3Ox()));
        // CH4_2 = CH4_2_1+CH4_2_2+CH4_2_3
        BigDecimal ch42 = ch421.add(ch422).add(ch423);
        // N2O_2_1 = M_sludge_1*wc/wc_k*EFS_3
        BigDecimal n2o21 = wceEnterDTO.getMSludge1()
            .multiply(wceEnterDTO.getWc())
            .divide(wceEnterDTO.getWc1(), 4, RoundingMode.HALF_UP)
            .multiply(wceEnterDTO.getEfs3());
        // N2O_2_2 = M_sludge_2*wc/wc_k*EFS_4
        BigDecimal n2o22 = wceEnterDTO.getMSludge2()
            .multiply(wceEnterDTO.getWc())
            .divide(wceEnterDTO.getWc2(), 4, RoundingMode.HALF_UP)
            .multiply(wceEnterDTO.getEfs4());
        // N2O_2 = N2O_2_1+N2O_2_2
        BigDecimal n2o2 = n2o21.add(n2o22);
        // CO2_1 = AH*t*CF_HP/10^6
        BigDecimal co21 = wceEnterDTO.getCarbonNegativeDTO().getAh()
            .multiply(wceEnterDTO.getCarbonNegativeDTO().getThot())
            .multiply(wceEnterDTO.getCfHp())
            .divide(BigDecimal.valueOf(Math.pow(10, 6)), 4, RoundingMode.HALF_UP);
        // CO2_2 = AC*t/3600*CF_E_i
        BigDecimal co22 = wceEnterDTO.getCarbonNegativeDTO().getAc()
            .multiply(wceEnterDTO.getCarbonNegativeDTO().getTcold())
            .divide(new BigDecimal(3600), 4, RoundingMode.HALF_UP)
            .multiply(wceEnterDTO.getCfE());
        // CO2_3 = E9*CF_E_i
        BigDecimal co23 = wceEnterDTO.getCarbonNegativeDTO().getE9().multiply(wceEnterDTO.getCfE());
        // CO2_4 = E10/γ*CF_E_i
        BigDecimal co24 = new BigDecimal(0);
        if (wceEnterDTO.getGama().compareTo(new BigDecimal(0)) != 0) {
            co24 = wceEnterDTO.getCarbonNegativeDTO().getE10()
                .divide(wceEnterDTO.getGama(), 4, RoundingMode.HALF_UP)
                .multiply(wceEnterDTO.getCfE());
        }
        // CO2_5 = E8*CF_E_i
        BigDecimal co25 = wceEnterDTO.getCarbonNegativeDTO().getE8().multiply(wceEnterDTO.getCfE());
        // CO2_6 = E11*CF_E_i
        BigDecimal co26 = wceEnterDTO.getCarbonNegativeDTO().getE11().multiply(wceEnterDTO.getCfE());
        // CO2_9 = E12*CF_E_i
        BigDecimal co29 = wceEnterDTO.getCarbonNegativeDTO().getE12().multiply(wceEnterDTO.getCfE());

        // C1_1 = Σ(M1_j*CF_M_j)
        BigDecimal c11 = new BigDecimal(0);
        // C1_2 = Σ(M2_j*CF_M_j)
        BigDecimal c12 = new BigDecimal(0);
        // C1_3 = Σ(M3_j*CF_M_j)
        BigDecimal c13 = new BigDecimal(0);
        // C1_4 = Σ(M4_j*CF_M_j)
        BigDecimal c14 = new BigDecimal(0);
        // C1 = Σ(M_j*CF_M_j)
        BigDecimal totalC1 = new BigDecimal(0);
        // C9_1 = Σ(M5_j*CF_M_j)
        BigDecimal c91 = new BigDecimal(0);
        for (WaterCarbonEmissionEnterDTO.PharmacyDTO pDTO : wceEnterDTO.getPharmacyDTOList()) {
            c11 = c11.add(pDTO.getM1().multiply(pDTO.getCfM()));
            c12 = c12.add(pDTO.getM2().multiply(pDTO.getCfM()));
            c13 = c13.add(pDTO.getM3().multiply(pDTO.getCfM()));
            c14 = c14.add(pDTO.getM4().multiply(pDTO.getCfM()));
            c91 = c91.add(pDTO.getM5().multiply(pDTO.getCfM()));
            totalC1 = totalC1.add(pDTO.getM().multiply(pDTO.getCfM()));
        }
        // C2_1 = E1*CF_E_j
        BigDecimal c21 = wceEnterDTO.getElectricityDTO().getE1().multiply(wceEnterDTO.getCfE());
        // C2_2 = E2*CF_E_j
        BigDecimal c22 = wceEnterDTO.getElectricityDTO().getE2().multiply(wceEnterDTO.getCfE());
        // C2_3 = E3*CF_E_j
        BigDecimal c23 = wceEnterDTO.getElectricityDTO().getE3().multiply(wceEnterDTO.getCfE());
        // C2_4 = E4*CF_E_j
        BigDecimal c24 = wceEnterDTO.getElectricityDTO().getE4().multiply(wceEnterDTO.getCfE());
        // C2_5 = E5*CF_E_j
        BigDecimal c25 = wceEnterDTO.getElectricityDTO().getE5().multiply(wceEnterDTO.getCfE());
        // C2_6 = E6*CF_E_j
        BigDecimal c26 = wceEnterDTO.getElectricityDTO().getE6().multiply(wceEnterDTO.getCfE());
        // C2_7 = E7*CF_E_j
        BigDecimal c27 = wceEnterDTO.getElectricityDTO().getE7().multiply(wceEnterDTO.getCfE());
        // C2 = E*CF_E_j
        BigDecimal totalC2 = wceEnterDTO.getElectricityDTO().getE().multiply(wceEnterDTO.getCfE());
        // C3_1 = CH4_1*CF_CH4
        BigDecimal c31 = ch41.multiply(wceEnterDTO.getCfCh4());
        // C3_2 = N2O_1*CF_N2O
        BigDecimal c32 = n2o1.multiply(wceEnterDTO.getCfN2o());
        // C3 = C3_1+C3_2
        BigDecimal totalC3 = c31.add(c32);
        // C4_1 = CH4_2*CF_CH4
        BigDecimal c41 = ch42.multiply(wceEnterDTO.getCfCh4());
        // C4_2 = N2O_2*CF_N2O
        BigDecimal c42 = n2o2.multiply(wceEnterDTO.getCfN2o());
        BigDecimal totalC4 = new BigDecimal(0);
        if (wceEnterDTO.getManagedBySelf()) {
            // C4 = C4_1+C4_2
            totalC4 = c41.add(c42);
        }
        // C9_2 = E8*CF_E_j
        BigDecimal c92 = wceEnterDTO.getElectricityDTO().getE8().multiply(wceEnterDTO.getCfE());
        BigDecimal totalC9 = new BigDecimal(0);
        if (wceEnterDTO.getManagedBySelf()) {
            // C9 = C9_1+C9_2
            totalC9 = c91.add(c92);
        }
        // C5 = CO2_5*CF_CO2
        BigDecimal totalC5 = co25.multiply(wceEnterDTO.getCfCo2());
        // C6 = (CO2_1+CO2_2)*CF_CO2
        BigDecimal totalC6 = wceEnterDTO.getCfCo2().multiply(co21.add(co22));
        // C7 = CO2_3*CF_CO2
        BigDecimal totalC7 = co23.multiply(wceEnterDTO.getCfCo2());
        // C8 = CO2_4*CF_CO2
        BigDecimal totalC8 = co24.multiply(wceEnterDTO.getCfCo2());
        // C10 = CO2_6*CF_CO2
        BigDecimal totalC10 = co26.multiply(wceEnterDTO.getCfCo2());
        // C11 = CO2_7*CF_CO2
        BigDecimal totalC11 = wceEnterDTO.getCarbonNegativeDTO().getCo27().multiply(wceEnterDTO.getCfCo2());
        // C12 = CO2_9*CF_CO2
        BigDecimal totalC12 = co29.multiply(wceEnterDTO.getCfCo2());
        // C13 = CO2_8*CF_CO2
        BigDecimal totalC13 = wceEnterDTO.getCarbonNegativeDTO().getCo28().multiply(wceEnterDTO.getCfCo2());

        // C = C1+C2-C10+C3+C4+C9-C6-C8-C11-C12-C13
        BigDecimal c = totalC1.add(totalC2).subtract(totalC10).add(totalC3).add(totalC4).add(totalC9).subtract(totalC6)
            .subtract(totalC8).subtract(totalC11).subtract(totalC12).subtract(totalC13);
        // Creduction = C5+C6+C7+C8+C11+C12+C13
        BigDecimal cReduction = totalC5.add(totalC6).add(totalC7).add(totalC8).add(totalC11).add(totalC12).add(totalC13);
        // Cdirect = C3+C4
        BigDecimal cDirect = totalC3.add(totalC4);
        // Cindirect = C1+C2+C9
        BigDecimal cIndirect = totalC1.add(totalC2).add(totalC9);

        WaterCarbonEmissionOutputDTO resultDTO = new WaterCarbonEmissionOutputDTO();
        resultDTO.setC11(c11.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setC12(c12.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setC13(c13.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setC14(c14.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setTotalC1(totalC1.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setC21(c21.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setC22(c22.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setC23(c23.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setC24(c24.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setC25(c25.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setC26(c26.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setC27(c27.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setTotalC2(totalC2.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setC31(c31.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setC32(c32.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setTotalC3(totalC3.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setC41(c41.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setC42(c42.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setTotalC4(totalC4.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setC91(c91.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setC92(c92.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setTotalC9(totalC9.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setTotalC5(totalC5.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setTotalC6(totalC6.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setTotalC7(totalC7.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setTotalC8(totalC8.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setTotalC10(totalC10.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setTotalC11(totalC11.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setTotalC12(totalC12.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setTotalC13(totalC13.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setC(c.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setCReduction(cReduction.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setCDirect(cDirect.setScale(2, RoundingMode.HALF_UP));
        resultDTO.setCIndirect(cIndirect.setScale(2, RoundingMode.HALF_UP));

        return resultDTO;
    }
}
