package com.ruowei.util;


import com.ruowei.service.dto.SewEmiDTO;

import com.ruowei.web.rest.vm.SewEmiAccountVM;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;


/**
 * excel工具类 提供导出excel的功能
 *
 */
public class CarbonAccountingExcel {

    /**
     * 生成excel 碳排放核算结果列表Excel生成
     * @param sewEmiDTO
     * @return
     */
    public static byte[] createExcel(SewEmiDTO sewEmiDTO){
//        try {
//            // 创建一个新的HSSFWorkbook对象
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            // 创建一个Excel的工作表
//            HSSFSheet sheet = workbook.createSheet(sewEmiDTO.getEnterpriseName()+"企业污水处理核算报告"+ sewEmiDTO.getAccYear()+"."+sewEmiDTO.getAccMonth());
//            //解决sheet名中文乱码问题
//            workbook.setSheetName(0,sewEmiDTO.getEnterpriseName()+"企业污水处理核算报告"+ sewEmiDTO.getAccYear()+"."+sewEmiDTO.getAccMonth());
//            // 创建字体，红色、粗体
//            HSSFFont font = workbook.createFont();
//            font.setColor(HSSFFont.COLOR_NORMAL);
//            font.setBold(true);
//            font.setFontHeight((short)5);;
//            font.setFontName("宋体");
//            font.setFontHeightInPoints((short) 20);
//            // 创建单元格的格式
//            HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
//            style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
//            style.setAlignment(HorizontalAlignment.CENTER);// 水平
//            style.setFont(font);
//            //表头字体
//            HSSFFont fontContent = workbook.createFont();
//            fontContent.setColor(HSSFFont.COLOR_NORMAL);
//            fontContent.setFontHeight((short)5);;
//            fontContent.setFontName("宋体");
//            fontContent.setFontHeightInPoints((short) 10);
//            //表格内容字体
//            HSSFFont fontSubstance = workbook.createFont();
//            fontSubstance.setColor(HSSFFont.COLOR_NORMAL);
//            fontSubstance.setBold(true);
//            fontSubstance.setFontHeight((short)5);;
//            fontSubstance.setFontName("宋体");
//            fontSubstance.setFontHeightInPoints((short) 10);
//            //表格外部样式
//            HSSFCellStyle styleContent = workbook.createCellStyle();
//            // 样式对象
//            styleContent.setVerticalAlignment(VerticalAlignment.CENTER);
//            // 垂直
//            styleContent.setAlignment(HorizontalAlignment.CENTER);
//            // 水平
//            styleContent.setFont(fontContent);
//            HSSFCellStyle styleContent2 = workbook.createCellStyle();
//            // 样式对象
//            styleContent2.setVerticalAlignment(VerticalAlignment.CENTER);
//            // 垂直
//            styleContent2.setAlignment(HorizontalAlignment.CENTER);
//            // 水平
//            styleContent2.setFont(fontContent);
//            //表格内容样式
//            HSSFCellStyle styleSubstance = workbook.createCellStyle();
//            // 样式对象
//            styleSubstance.setVerticalAlignment(VerticalAlignment.CENTER);
//            // 垂直
//            styleSubstance.setAlignment(HorizontalAlignment.CENTER);
//            // 水平
//            styleSubstance.setFont(fontSubstance);
//            HSSFRow row = sheet.createRow((short) 0);
//            //合并单元格(前3行10列合并)
//            sheet.addMergedRegion(new CellRangeAddress(0, (short) 2, 0, (short) 9));
//            HSSFCell ce = row.createCell((short) 0);
//
//            ce.setCellValue(sewEmiDTO.getEnterpriseName()+"企业污水处理核算报告"+ sewEmiDTO.getAccYear()+"."+sewEmiDTO.getAccMonth()); // 表格的第一行第一列显示的数据
//            ce.setCellStyle(style);
//            // 样式，居中
//
//            styleContent2.setFillForegroundColor(IndexedColors.WHITE.getIndex());
//            styleContent2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//            /**
//             * 颜色选择
//             */
//            //橙色
//            style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
//            styleSubstance.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
//            styleContent.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
//            //蓝色
////            style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
////            styleSubstance.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
////            styleContent.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
//            //绿色
////            style.setFillForegroundColor(IndexedColors.LIME.getIndex());
////            styleSubstance.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
////            styleContent.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
//            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//            styleSubstance.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//            styleContent.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//
//            /*
//              表头
//             */
//            HSSFRow row2 = sheet.createRow((short) 3);
//            createExcelCell(row2,0, styleSubstance,"*基础信息：");
//            createExcelCell(row2,1, styleSubstance,sewEmiDTO.getEnterpriseName());
//            createExcelCell(row2,2, styleSubstance,"*省份/直辖市：");
//            createExcelCell(row2,3, styleSubstance,sewEmiDTO.getProvinceName());
//            createExcelCell(row2,4, styleSubstance,"*核算时间");
//            createExcelCell(row2,5, styleSubstance,sewEmiDTO.getAccYear() + "年" + sewEmiDTO.getAccMonth() + "月");
//            HSSFRow row3 = sheet.createRow((short) 4);
//            createExcelCell(row3,0, styleSubstance,"污泥处理方式");
//            createExcelCell(row3,1, styleSubstance,"*污泥处理量(kg/mth)");
//            createExcelCell(row3,2, styleSubstance,"*污泥处理前含水率(%)");
//            int a = 0;
//            for (SewEmiAccountVM.SewSluVM sewSluDetailDTO : sewEmiDTO.getSewSlus()) {
//                HSSFRow row4 = sheet.createRow((short) 5+a);
//                createExcelCell(row4, 0, styleSubstance,sewSluDetailDTO.getMethodName());
//                createExcelCell(row4, 1, styleSubstance, sewSluDetailDTO.getSluCapacity() + "kg/mth");
//                createExcelCell(row4, 2, styleSubstance, String.valueOf(sewSluDetailDTO.getSluMoisture()));
//                a++;
//            }
//            HSSFRow row5 = sheet.createRow((short) 6+a);
//            createExcelCell(row5,0, styleSubstance,"水质信息");
//            HSSFRow row6 = sheet.createRow((short) 7+a);
//            createExcelCell(row6,0, styleSubstance,"工艺类型");
//            createExcelCell(row6,1, styleSubstance,"日均规模(m3/d)");
//            createExcelCell(row6,2, styleSubstance,"本月运行天数");
//            createExcelCell(row6,3, styleSubstance,"进水总氮（mg/L）");
//            createExcelCell(row6,4, styleSubstance,"进水COD（mg/L）");
//            createExcelCell(row6,5, styleSubstance,"进水氨氮（mg/L）");
//            createExcelCell(row6,6, styleSubstance,"进水总磷（mg/L）");
//            createExcelCell(row6,7, styleSubstance,"进水BOD（mg/L）选填");
//            createExcelCell(row6,8, styleSubstance,"出水总氮（mg/L）");
//            createExcelCell(row6,9, styleSubstance,"出水COD（mg/L）");
//            createExcelCell(row6,10, styleSubstance,"出水氨氮（mg/L）");
//            createExcelCell(row6,11, styleSubstance,"出水总磷（mg/L）");
//            createExcelCell(row6,12, styleSubstance,"出水BOD（mg/L）选填");
//            int b=0;
//            for (SewEmiAccountVM.SewProcessVM sewProcessVM : sewEmiDTO.getSewProcesss()) {
//                HSSFRow row7 = sheet.createRow((short) 8 + a+b);
//                createExcelCell(row7, 0, styleSubstance, sewProcessVM.getProcessTypeName());
//                createExcelCell(row7, 1, styleSubstance, String.valueOf(sewProcessVM.getDailyScale()));
//                createExcelCell(row7, 2, styleSubstance, String.valueOf(sewProcessVM.getOperatingDays()));
//                createExcelCell(row7, 3, styleSubstance, String.valueOf(sewProcessVM.getInNitrogen()));
//                createExcelCell(row7, 4, styleSubstance, String.valueOf(sewProcessVM.getInCod()));
//                createExcelCell(row7, 5, styleSubstance,  String.valueOf(sewProcessVM.getInAmmonia()));
//                createExcelCell(row7, 6, styleSubstance,  String.valueOf(sewProcessVM.getInPhosphorus()));
//                createExcelCell(row7, 7, styleSubstance,  getValue(sewProcessVM.getInBod()));
//                createExcelCell(row7, 8, styleSubstance, String.valueOf(sewProcessVM.getOutNitrogen()));
//                createExcelCell(row7, 9, styleSubstance, String.valueOf(sewProcessVM.getOutCod()));
//                createExcelCell(row7, 10, styleSubstance, String.valueOf(sewProcessVM.getOutAmmonia()));
//                createExcelCell(row7, 11, styleSubstance, String.valueOf(sewProcessVM.getOutPhosphorus()));
//                createExcelCell(row7, 12, styleSubstance, getValue(sewProcessVM.getOutBod()));
//                b++;
//            }
//            HSSFRow row8 = sheet.createRow((short) 9+a+b);
//            createExcelCell(row8,0, styleSubstance,"电量信息");
//            HSSFRow row9 = sheet.createRow((short) 10+a+b);
//            createExcelCell(row9,0, styleSubstance,"*月度总电量消耗：");
//            createExcelCell(row9,1, styleSubstance,sewEmiDTO.getTotalPow()+"kWh/mth");
//            HSSFRow row10 = sheet.createRow((short) 11+a+b);
//            createExcelCell(row10,0, styleSubstance,"药剂月投加量");
//
//            /*
//              添加内容
//             */
//            int c = 0;
//            for(SewEmiAccountVM.SewPotVM sewSluDetailDTO : sewEmiDTO.getSewPots()){
//                HSSFRow rows11 = sheet.createRow((short) 12+a+b+c);//表格内容是从第5行开始，每循环一次增加一行
//                createExcelCell(rows11,0, styleContent, sewSluDetailDTO.getPotionName());
//                createExcelCell(rows11,1, styleContent, String.valueOf(sewSluDetailDTO.getTotalPot()));
//                createExcelCell(rows11,2, styleContent,getValue(sewSluDetailDTO.getLevel1Pot()));
//                createExcelCell(rows11,3, styleContent,getValue(sewSluDetailDTO.getLevel2Pot()));
//                createExcelCell(rows11,4, styleContent,getValue(sewSluDetailDTO.getLevel3Pot()));
//                createExcelCell(rows11,5, styleContent,getValue(sewSluDetailDTO.getSluTreatPot()));
//
//                c++;
//            }
//            int d = 0;
//            if(sewEmiDTO.getSolarPow()!=null||sewEmiDTO.getHeatPumpHeat()!=null||sewEmiDTO.getThermoElec()!=null){
//                HSSFRow row40 = sheet.createRow((short) 10+a+b);
//                createExcelCell(row40,0, styleSubstance,"电量信息");
//
//                if(sewEmiDTO.getSolarPow()!=null) {
//                    HSSFRow row41 = sheet.createRow((short) 10+a+b);
//                    createExcelCell(row41,0, styleSubstance,"太阳能月发电量：");
//                    createExcelCell(row41,1, styleSubstance,sewEmiDTO.getSolarPow()+"kWh/mth");
//                    d++;
//                }
//                if(sewEmiDTO.getHeatPumpHeat()!=null) {
//                    HSSFRow row41 = sheet.createRow((short) 10+a+b);
//                    createExcelCell(row41,0, styleSubstance,"热泵供热量：");
//                    createExcelCell(row41,1, styleSubstance,sewEmiDTO.getHeatPumpHeat()+"kJ/h");
//                    createExcelCell(row41,2, styleSubstance,"月热泵运行时间：");
//                    createExcelCell(row41,3, styleSubstance,sewEmiDTO.getHeatPumpHours()+"h");
//                    createExcelCell(row41,4, styleSubstance,"热泵制冷量：");
//                    createExcelCell(row41,5, styleSubstance,sewEmiDTO.getHeatPumpRefr()+"kJ/h");
//                    d++;
//
//                }
//                if(sewEmiDTO.getThermoElec()!=null) {
//                    HSSFRow row42 = sheet.createRow((short) 10+a+b);
//                    createExcelCell(row42,4, styleSubstance,"月产电量：");
//                    createExcelCell(row42,5, styleSubstance,sewEmiDTO.getThermoElec()+"kWh/mth");
//                    createExcelCell(row42,4, styleSubstance,"月产热能：");
//                    createExcelCell(row42,5, styleSubstance,sewEmiDTO.getThermoEner()+"kJ/mth");
//                    d++;
//                }
//                d++;
//            }else{
//                d=0;
//            }
//            HSSFRow row12 = sheet.createRow((short) 13+a+b+c+d);
//            createExcelCell(row12,0, styleSubstance,"核算结果报告");
//            sheet.addMergedRegion(new CellRangeAddress(14+a+c+b+d, (short) 18+a+b+c+d, 0, (short) 2));
//
//            HSSFRow row13 = sheet.createRow((short) 14+a+b+c+d);
//            HSSFCell ce1 = row13.createCell((short) 0);
//            ce1.setCellValue("药剂间接碳排");
//            ce1.setCellStyle(styleSubstance);
//            createExcelCell(row13,3, styleSubstance,"一级处理药剂投加产生的间接碳排放");
//            createExcelCell(row13,4, styleSubstance,getValue(sewEmiDTO.getLevel1PotEmi()));
//            createExcelCell(row13,5, styleSubstance,"kg CO2 /mth");
//            HSSFRow row14 = sheet.createRow((short) 15+a+b+c+d);
//            createExcelCell(row14,3, styleSubstance,"二级处理药剂投加产生的间接碳排放");
//            createExcelCell(row14,4, styleSubstance,getValue(sewEmiDTO.getLevel2PotEmi()));
//            createExcelCell(row14,5, styleSubstance,"kg CO2 /mth");
//            HSSFRow row15 = sheet.createRow((short) 16+a+b+c+d);
//            createExcelCell(row15,3, styleSubstance,"三级处理药剂投加产生的间接碳排放");
//            createExcelCell(row15,4, styleSubstance,getValue(sewEmiDTO.getLevel3PotEmi()));
//            createExcelCell(row15,5, styleSubstance,"kg CO2 /mth");
//            HSSFRow row16 = sheet.createRow((short) 17+a+b+c+d);
//            createExcelCell(row16,3, styleSubstance,"污泥处理药剂投加产生的间接碳排放");
//            createExcelCell(row16,4, styleSubstance,getValue(sewEmiDTO.getSluTreatPotEmi()));
//            createExcelCell(row16,5, styleSubstance,"kg CO2 /mth");
//            HSSFRow row17 = sheet.createRow((short) 18+a+b+c+d);
//            createExcelCell(row17,3, styleSubstance,"药剂投加产生的间接碳排放");
//            createExcelCell(row17,4, styleSubstance,getValue(sewEmiDTO.getTotalPotEmi()));
//            createExcelCell(row17,5, styleSubstance,"kg CO2 /mth");
//
//            sheet.addMergedRegion(new CellRangeAddress(19+a+c+b+d, (short) 26+a+b+c+d, 0, (short) 2));
//
//            HSSFRow row18 = sheet.createRow((short) 19+a+b+c+d);
//            HSSFCell ce2 = row13.createCell((short) 0);
//            ce2.setCellValue("耗电间接碳排");
//            ce2.setCellStyle(styleSubstance);
//            createExcelCell(row18,3, styleContent,"进水总泵站耗电产生的间接碳排放");
//            createExcelCell(row18,4, styleContent,sewEmiDTO.getLevel1PotEmi() + "");
//            createExcelCell(row18,5, styleContent,"kg CO2 /mth");
//            HSSFRow row19 = sheet.createRow((short) 20+a+b+c+d);
//            createExcelCell(row19,3, styleContent,"鼓风机房耗电产生的间接碳排放");
//            createExcelCell(row19,4, styleContent,sewEmiDTO.getBlowerPowEmi() + "");
//            createExcelCell(row19,5, styleContent,"kg CO2 /mth");
//            HSSFRow row20 = sheet.createRow((short) 21+a+b+c+d);
//            createExcelCell(row20,3, styleContent,"回流污泥泵房耗电产生的间接碳排放");
//            createExcelCell(row20,4, styleContent,sewEmiDTO.getRetSluPumpPowEmi() + "");
//            createExcelCell(row20,5, styleContent,"kg CO2 /mth");
//            HSSFRow row21 = sheet.createRow((short) 22+a+b+c+d);
//            createExcelCell(row21,3, styleContent,"污泥处理用电耗电产生的间接碳排放");
//            createExcelCell(row21,4, styleContent,sewEmiDTO.getSluTreatPowEmi() + "");
//            createExcelCell(row21,5, styleContent,"kg CO2 /mth");
//            HSSFRow row22 = sheet.createRow((short) 23+a+b+c+d);
//            createExcelCell(row22,3, styleContent,"紫外+氯消毒耗电产生的间接碳排放");
//            createExcelCell(row22,4, styleContent,sewEmiDTO.getFacilityPowEmi() + "");
//            createExcelCell(row22,5, styleContent,"kg CO2 /mth");
//            HSSFRow row23 = sheet.createRow((short) 24+a+b+c+d);
//            createExcelCell(row23,3, styleContent,"附属设施用电耗电产生的间接碳排放");
//            createExcelCell(row23,4, styleContent,sewEmiDTO.getDisinfectPowEmi() + "");
//            createExcelCell(row23,5, styleContent,"kg CO2 /mth");
//            HSSFRow row24 = sheet.createRow((short) 25+a+b+c+d);
//            createExcelCell(row24,3, styleContent,"其他耗电产生的间接碳排放");
//            createExcelCell(row24,4, styleContent,sewEmiDTO.getOtherPowEmi() + "");
//            createExcelCell(row24,5, styleContent,"kg CO2 /mth");
//            HSSFRow row25 = sheet.createRow((short) 26+a+b+c+d);
//            createExcelCell(row25,3, styleContent,"耗电产生的间接碳排放");
//            createExcelCell(row25,4, styleContent,sewEmiDTO.getTotalPowEmi() + "");
//            createExcelCell(row25,5, styleContent,"kg CO2 /mth");
//
//            sheet.addMergedRegion(new CellRangeAddress(27+a+c+b+d, (short) 29+a+b+c+d, 0, (short) 2));
//
//            HSSFRow row26 = sheet.createRow((short) 27+a+b+c+d);
//            HSSFCell ce3 = row26.createCell((short) 0);
//            ce3.setCellValue("污水直接碳排");
//            ce3.setCellStyle(styleSubstance);
//            createExcelCell(row26,3, styleSubstance,"污水处理CH4的CO2当量");
//            createExcelCell(row26,4, styleSubstance,sewEmiDTO.getSewTreatCh4Emi() + "");
//            createExcelCell(row26,5, styleSubstance,"kg CO2 /mth");
//            HSSFRow row27 = sheet.createRow((short) 28+a+b+c+d);
//            createExcelCell(row27,3, styleSubstance,"污水处理N2O的CO2当量");
//            createExcelCell(row27,4, styleSubstance,sewEmiDTO.getSewTreatN2oEmi() + "");
//            createExcelCell(row27,5, styleSubstance,"kg CO2 /mth");
//            HSSFRow row28 = sheet.createRow((short) 29+a+b+c+d);
//            createExcelCell(row28,3, styleSubstance,"污水处理的直接碳排放");
//            createExcelCell(row28,4, styleSubstance,sewEmiDTO.getTotalSewTreatEmi() + "");
//            createExcelCell(row28,5, styleSubstance,"kg CO2 /mth");
//
//            sheet.addMergedRegion(new CellRangeAddress(30+a+c+b+d, (short) 32+a+b+c+d, 0, (short) 2));
//
//
//            HSSFRow row29 = sheet.createRow((short) 30+a+b+c+d);
//            HSSFCell ce4 = row13.createCell((short) 0);
//            ce4.setCellValue("污泥直接碳排");
//            ce4.setCellStyle(styleSubstance);
//            createExcelCell(row29,3, styleContent,"污泥处理CH4的CO2当量");
//            createExcelCell(row29,4, styleContent,sewEmiDTO.getSluTreatCh4Emi() + "");
//            createExcelCell(row29,5, styleContent,"kg CO2 /mth");
//            HSSFRow row30 = sheet.createRow((short) 31+a+b+c+d);
//            createExcelCell(row30,3, styleContent,"污泥处理N2O的CO2当量");
//            createExcelCell(row30,4, styleContent,sewEmiDTO.getSluTreatN2oEmi() + "");
//            createExcelCell(row30,5, styleContent,"kg CO2 /mth");
//            HSSFRow row31 = sheet.createRow((short) 32+a+b+c);
//            createExcelCell(row31,3, styleContent,"污泥处理的直接碳排放");
//            createExcelCell(row31,4, styleContent,sewEmiDTO.getTotalSluTreatEmi() + "");
//            createExcelCell(row31,5, styleContent,"kg CO2 /mth");
//
//            sheet.addMergedRegion(new CellRangeAddress(33+a+c+b+d, (short) 36+a+b+c+d, 0, (short) 2));
//
//
//            HSSFRow row32 = sheet.createRow((short) 33+a+b+c+d);
//            HSSFCell ce5 = row13.createCell((short) 0);
//            ce5.setCellValue("耗电间接碳排");
//            ce5.setCellStyle(styleSubstance);
//            createExcelCell(row32,3, styleSubstance,"太阳能间接减少的碳排放");
//            createExcelCell(row32,4, styleSubstance,sewEmiDTO.getSolarPowRed() + "");
//            createExcelCell(row32,5, styleSubstance,"kg CO2 /mth");
//            HSSFRow row33 = sheet.createRow((short) 34+a+b+c+d);
//            createExcelCell(row33,3, styleSubstance,"热泵间接减少的碳排放");
//            createExcelCell(row33,4, styleSubstance,sewEmiDTO.getHeatPumpRed() + "");
//            createExcelCell(row33,5, styleSubstance,"kg CO2 /mth");
//            HSSFRow row34 = sheet.createRow((short) 35+a+b+c+d);
//            createExcelCell(row34,3, styleSubstance,"热电联产发电间接减少的碳排放");
//            createExcelCell(row34,4, styleSubstance,sewEmiDTO.getThermoEnerRed() + "");
//            createExcelCell(row34,5, styleSubstance,"kg CO2 /mth");
//            HSSFRow row35 = sheet.createRow((short) 36+a+b+c+d);
//            createExcelCell(row35,3, styleSubstance,"热电联产产热间接减少的碳排放");
//            createExcelCell(row35,4, styleSubstance,sewEmiDTO.getThermoElecRed() + "");
//            createExcelCell(row35,5, styleSubstance,"kg CO2 /mth");
//
//            sheet.addMergedRegion(new CellRangeAddress(37+a+c+b+d, (short) 40+a+b+c+d, 0, (short) 2));
//
//
//            HSSFRow row36 = sheet.createRow((short) 37+a+b+c+d);
//            HSSFCell ce6 = row13.createCell((short) 0);
//            ce6.setCellValue("本月合计");
//            ce6.setCellStyle(styleSubstance);
//            createExcelCell(row36,3, styleContent,"污水厂本月的碳排放");
//            createExcelCell(row36,4, styleContent,sewEmiDTO.getCarbonEmi() + "");
//            createExcelCell(row36,5, styleContent,"kg CO2 /mth");
//            HSSFRow row37 = sheet.createRow((short) 38+a+b+c+d);
//            createExcelCell(row37,3, styleContent,"污水厂本月负碳排放");
//            createExcelCell(row37,4, styleContent,sewEmiDTO.getCarbonRed() + "");
//            createExcelCell(row37,5, styleContent,"kg CO2 /mth");
//            HSSFRow row38 = sheet.createRow((short) 39+a+b+c+d);
//            createExcelCell(row38,3, styleContent,"污水厂本月直接碳排放");
//            createExcelCell(row38,4, styleContent,sewEmiDTO.getCarbonDirEmi() + "");
//            createExcelCell(row38,5, styleContent,"kg CO2 /mth");
//            HSSFRow row39 = sheet.createRow((short) 40+a+b+c+d);
//            createExcelCell(row39,3, styleContent,"污水厂本月间接碳排放");
//            createExcelCell(row39,4, styleContent,sewEmiDTO.getCarbonIndirEmi() + "");
//            createExcelCell(row39,5, styleContent,"kg CO2 /mth");
//
//
//
//            sheet.autoSizeColumn((short)0);
//            sheet.autoSizeColumn((short)1);
//            sheet.autoSizeColumn((short)2);
//            sheet.autoSizeColumn((short)3);
//            sheet.autoSizeColumn((short)4);
//            sheet.autoSizeColumn((short)5);
//            sheet.autoSizeColumn((short)6);
//            sheet.autoSizeColumn((short)7);
//            sheet.autoSizeColumn((short)8);
//            sheet.autoSizeColumn((short)9);
//            sheet.autoSizeColumn((short)10);
//            sheet.autoSizeColumn((short)11);
//            sheet.autoSizeColumn((short)12);
//            /*
//              导出
//             */
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            workbook.write(out);
//            return out.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    private static String getValue(BigDecimal decimal) {
        return decimal != null ? String.valueOf(decimal) : "";
    }
    /**
     * 创建excel单元格
     *
     * @param rows
     * @param col
     * @param style
     * @param contents
     */
    public static void createExcelCell(HSSFRow rows, int col,
                                       HSSFCellStyle style, String contents){
        HSSFCell cell1 = rows.createCell((short) col);
        cell1.setCellValue(contents);
        cell1.setCellStyle(style);
    }

}
