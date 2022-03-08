package com.ruowei.util;


import com.ruowei.service.dto.SewEmiDTO;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import com.ruowei.web.rest.vm.SewEmiAccountVM;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.IntStream;
import java.io.ByteArrayOutputStream;


/**
 * excel工具类 提供导出excel的功能
 */
public class CarbonAccountingExcel2 {

    /**
     * 生成excel 碳排放核算结果列表Excel生成
     *
     * @param
     * @return
     */
    public static byte[] createExcel(SewEmiDTO sewEmiDTO) {

        try {
            // 创建一个新的HSSFWorkbook对象
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 创建一个Excel的工作表
            HSSFSheet sheet = workbook.createSheet(sewEmiDTO.getEnterpriseName() + "企业污水处理核算报告" + sewEmiDTO.getAccYear() + "." + sewEmiDTO.getAccMonth());
            //解决sheet名中文乱码问题
            workbook.setSheetName(0, sewEmiDTO.getEnterpriseName() + "企业污水处理核算报告" + sewEmiDTO.getAccYear() + "." + sewEmiDTO.getAccMonth());
            // 标题字体
            HSSFFont fontTitle = workbook.createFont();
            fontTitle.setColor(HSSFFont.COLOR_NORMAL);
            fontTitle.setBold(true);
            fontTitle.setFontHeight((short) 5);
            fontTitle.setFontName("宋体");
            fontTitle.setFontHeightInPoints((short) 20);
            HSSFFont fontTitle2 = workbook.createFont();
            fontTitle2.setColor(HSSFFont.COLOR_NORMAL);
            fontTitle2.setBold(true);
            fontTitle2.setFontHeight((short) 5);
            fontTitle2.setFontName("宋体");
            fontTitle2.setFontHeightInPoints((short) 15);
            // 创建单元格的格式
            //表格标题样式
            HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
            style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
            style.setAlignment(HorizontalAlignment.CENTER);// 水平
            style.setFont(fontTitle);
            IntStream.range(0, 13).forEach(value -> sheet.setColumnWidth(value, 15 * 256));
//            sheet.setColumnWidth(0,50*256);


            //内容字体1
            HSSFFont fontContent = workbook.createFont();
            fontContent.setColor(HSSFFont.COLOR_NORMAL);
            fontContent.setFontHeight((short) 5);
            fontContent.setFontName("宋体");
            fontContent.setFontHeightInPoints((short) 10);

            //表头字体
            HSSFFont fontSubstance = workbook.createFont();
            fontSubstance.setColor(HSSFFont.COLOR_NORMAL);
            fontSubstance.setBold(true);
            fontSubstance.setFontHeight((short) 5);
            fontSubstance.setFontName("宋体");
            fontSubstance.setFontHeightInPoints((short) 10);
            //表头字体2
            HSSFFont fontSubstance2 = workbook.createFont();
            fontSubstance2.setColor(HSSFFont.COLOR_NORMAL);
            fontSubstance2.setBold(true);
            fontSubstance2.setFontHeight((short) 5);
            fontSubstance2.setFontName("宋体");
            fontSubstance2.setFontHeightInPoints((short) 10);


            //表头样式
            HSSFCellStyle styleSubstance = workbook.createCellStyle();          //样式对象
            styleSubstance.setVerticalAlignment(VerticalAlignment.CENTER);      //垂直
            styleSubstance.setAlignment(HorizontalAlignment.CENTER);            //水平
            styleSubstance.setFont(fontSubstance);                              //字体

            HSSFCellStyle styleSubstance3 = workbook.createCellStyle();          //样式对象
            styleSubstance3.setVerticalAlignment(VerticalAlignment.CENTER);      //垂直
            styleSubstance3.setAlignment(HorizontalAlignment.CENTER);            //水平
            styleSubstance3.setFont(fontSubstance);                              //字体

            HSSFCellStyle styleSubstance4 = workbook.createCellStyle();          //样式对象
            styleSubstance4.setVerticalAlignment(VerticalAlignment.CENTER);      //垂直
            styleSubstance4.setAlignment(HorizontalAlignment.CENTER);            //水平
            styleSubstance4.setFont(fontSubstance);                              //字体


            //内容样式1
            HSSFCellStyle styleContent1 = workbook.createCellStyle();
            styleContent1.setVerticalAlignment(VerticalAlignment.CENTER);        //垂直
            styleContent1.setAlignment(HorizontalAlignment.CENTER);              //水平
            styleContent1.setFont(fontContent);                                  //字体
            //内容样式2
            HSSFCellStyle styleContent2 = workbook.createCellStyle();
            styleContent2.setVerticalAlignment(VerticalAlignment.CENTER);       //垂直
            styleContent2.setAlignment(HorizontalAlignment.CENTER);             //水平
            styleContent2.setFont(fontContent);                                 //字体
            //内容样式3
            HSSFCellStyle styleSubstance2 = workbook.createCellStyle();
            styleSubstance2.setVerticalAlignment(VerticalAlignment.CENTER);       //垂直
            styleSubstance2.setAlignment(HorizontalAlignment.LEFT);               //水平
            styleSubstance2.setFont(fontTitle2);                              //字体
            //内容样式3
            HSSFCellStyle styleContent3 = workbook.createCellStyle();
            styleContent2.setVerticalAlignment(VerticalAlignment.CENTER);       //垂直
            styleContent2.setAlignment(HorizontalAlignment.CENTER);             //水平
            styleContent2.setFont(fontContent);                                 //字体
            //内容样式4
            HSSFCellStyle styleContent4 = workbook.createCellStyle();
            styleContent2.setVerticalAlignment(VerticalAlignment.CENTER);       //垂直
            styleContent2.setAlignment(HorizontalAlignment.CENTER);             //水平
            styleContent2.setFont(fontContent);                                 //字体
            //内容样式5
            HSSFCellStyle styleContent5 = workbook.createCellStyle();
            styleContent5.setVerticalAlignment(VerticalAlignment.CENTER);       //垂直
            styleContent5.setAlignment(HorizontalAlignment.RIGHT);             //水平
            styleContent5.setFont(fontSubstance);                                 //字体


            HSSFRow row = sheet.createRow((short) 0);
            //合并单元格(前3行13列合并)
            sheet.addMergedRegion(new CellRangeAddress(0, (short) 2, 0, (short) 12));
            HSSFCell ce = row.createCell((short) 0);

            ce.setCellValue(sewEmiDTO.getEnterpriseName() + "企业污水处理核算报告" + sewEmiDTO.getAccYear() + "." + sewEmiDTO.getAccMonth()); // 表格的第一行第一列显示的数据
            ce.setCellStyle(style);                         //标题样式


            //蓝色
            style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            styleSubstance.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
            styleContent1.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
            styleContent2.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            styleContent3.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
            styleContent4.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            styleContent5.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            styleSubstance3.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
            styleSubstance4.setFillForegroundColor(IndexedColors.WHITE.getIndex());


            //填充模式
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleSubstance.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleSubstance3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleSubstance4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleContent1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleContent2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleContent3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleContent4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleContent5.setFillPattern(FillPatternType.SOLID_FOREGROUND);


            int a = 3;

            //基础信息
            HSSFRow blankJichuxinxi = sheet.createRow((short) a);
            IntStream.range(0, 13).forEach(value -> createExcelCell(blankJichuxinxi, value, styleContent2, ""));              //填充空白列
            a++;
            HSSFRow title1baseInformation = sheet.createRow((short) a);
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 0, (short) 12));
            createExcelCell(title1baseInformation, 0, styleSubstance2, "基础信息");
            a++;

            HSSFRow row2 = sheet.createRow((short) a);
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 0, (short) 1));
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 2, (short) 3));
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 4, (short) 5));
            createExcelCell(row2, 0, styleContent2, "*企业名称：" + sewEmiDTO.getEnterpriseName());
            createExcelCell(row2, 2, styleContent2, "*省份/直辖市：" + sewEmiDTO.getProvinceName());
            createExcelCell(row2, 4, styleContent2, "*核算时间:" + sewEmiDTO.getAccYear() + "年" + sewEmiDTO.getAccMonth() + "月");
            IntStream.range(5, 13).forEach(value -> createExcelCell(row2, value, styleContent2, ""));              //填充空白列
            a++;


            //水质信息
            HSSFRow blankShuizhixinxi = sheet.createRow((short) a);
            IntStream.range(0, 13).forEach(value -> createExcelCell(blankShuizhixinxi, value, styleContent2, ""));              //填充空白列
            a++;
            int numShuizhixinxi = a + 1;
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 0, (short) 12));
            HSSFRow shuizhixinix = sheet.createRow((short) a);
            createExcelCell(shuizhixinix, 0, styleSubstance2, "水质信息");
            a++;

            HSSFRow row6 = sheet.createRow((short) a);
            createExcelCell(row6, 0, styleSubstance, "工艺类型");
            createExcelCell(row6, 1, styleSubstance, "日均规模");
            createExcelCell(row6, 2, styleSubstance, "本月运行天数");
            createExcelCell(row6, 3, styleSubstance, "进水总氮");
            createExcelCell(row6, 4, styleSubstance, "进水COD");
            createExcelCell(row6, 5, styleSubstance, "进水氨氮");
            createExcelCell(row6, 6, styleSubstance, "进水总磷");
            createExcelCell(row6, 7, styleSubstance, "进水BOD");
            createExcelCell(row6, 8, styleSubstance, "出水总氮");
            createExcelCell(row6, 9, styleSubstance, "出水COD");
            createExcelCell(row6, 10, styleSubstance, "出水氨氮");
            createExcelCell(row6, 11, styleSubstance, "出水总磷");
            createExcelCell(row6, 12, styleSubstance, "出水BOD");
            a++;
            HSSFRow row62 = sheet.createRow((short) a);
            createExcelCell(row62, 0, styleSubstance, " ");
            createExcelCell(row62, 1, styleSubstance, "(m3/d)");
            createExcelCell(row62, 2, styleSubstance, " ");
            createExcelCell(row62, 3, styleSubstance, "（mg/L）");
            createExcelCell(row62, 4, styleSubstance, "（mg/L）");
            createExcelCell(row62, 5, styleSubstance, "（mg/L）");
            createExcelCell(row62, 6, styleSubstance, "（mg/L）");
            createExcelCell(row62, 7, styleSubstance, "（mg/L）选填");
            createExcelCell(row62, 8, styleSubstance, "（mg/L）");
            createExcelCell(row62, 9, styleSubstance, "（mg/L）");
            createExcelCell(row62, 10, styleSubstance, "（mg/L）");
            createExcelCell(row62, 11, styleSubstance, "（mg/L）");
            createExcelCell(row62, 12, styleSubstance, "（mg/L）选填");
            a++;

            for (SewEmiAccountVM.SewProcessVM sewProcessVM : sewEmiDTO.getSewProcesss()) {                                                       //测试用数据
                HSSFRow row7 = sheet.createRow((short) a);
                createExcelCell(row7, 0, styleContent2, sewProcessVM.getProcessTypeName());
                createExcelCell(row7, 1, styleContent2, String.valueOf(sewProcessVM.getDailyScale()));
                createExcelCell(row7, 2, styleContent2, String.valueOf(sewProcessVM.getOperatingDays()));
                createExcelCell(row7, 3, styleContent2, String.valueOf(sewProcessVM.getInFlow()));
                createExcelCell(row7, 4, styleContent2, String.valueOf(sewProcessVM.getInCod()));
                createExcelCell(row7, 5, styleContent2, String.valueOf(sewProcessVM.getInAmmonia()));
                createExcelCell(row7, 6, styleContent2, String.valueOf(sewProcessVM.getInTp()));
                createExcelCell(row7, 7, styleContent2, getValue(sewProcessVM.getInTn()));
                createExcelCell(row7, 8, styleContent2, String.valueOf(sewProcessVM.getOutFlow()));
                createExcelCell(row7, 9, styleContent2, String.valueOf(sewProcessVM.getOutCod()));
                createExcelCell(row7, 10, styleContent2, String.valueOf(sewProcessVM.getOutAmmonia()));
                createExcelCell(row7, 11, styleContent2, String.valueOf(sewProcessVM.getOutTp()));
                createExcelCell(row7, 12, styleContent2, getValue(sewProcessVM.getOutTn()));
                a++;
            }

            //电量信息
            HSSFRow blankDianliangxinxi = sheet.createRow((short) a);
            IntStream.range(0, 13).forEach(value -> createExcelCell(blankDianliangxinxi, value, styleContent2, ""));              //填充空白列
            a++;
            HSSFRow row8 = sheet.createRow((short) a);
            createExcelCell(row8, 0, styleSubstance2, "电量信息");
            IntStream.range(2, 13).forEach(value -> createExcelCell(row8, value, styleContent2, ""));              //填充空白列
            a++;
            HSSFRow row9 = sheet.createRow((short) a);
            createExcelCell(row9, 0, styleContent2, "*月度总电量消耗：");
            createExcelCell(row9, 1, styleContent2, sewEmiDTO.getTotalPow() + "kWh/mth");
            IntStream.range(3, 13).forEach(value -> createExcelCell(row9, value, styleContent2, ""));              //填充空白列
            a++;


            //药剂月投加量
            HSSFRow blankYaoji = sheet.createRow((short) a);
            IntStream.range(0, 13).forEach(value -> createExcelCell(blankYaoji, value, styleContent2, ""));              //填充空白列
            a++;
            HSSFRow row10 = sheet.createRow((short) a);
            createExcelCell(row10, 0, styleSubstance2, "药剂月投加量");
            IntStream.range(2, 13).forEach(value -> createExcelCell(row10, value, styleContent2, ""));              //填充空白列
            a++;

            HSSFRow rowYaoji = sheet.createRow((short) a);
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 1, (short) 2));
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 3, (short) 4));
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 5, (short) 6));
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 7, (short) 8));
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 9, (short) 10));
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 11, (short) 12));
            createExcelCell(rowYaoji, 0, styleSubstance, "药剂名称");
            createExcelCell(rowYaoji, 1, styleSubstance, "药剂月投加量");
            createExcelCell(rowYaoji, 3, styleSubstance, "一级投加量");
            createExcelCell(rowYaoji, 5, styleSubstance, "二级投加量");
            createExcelCell(rowYaoji, 7, styleSubstance, "三级投加量");
            createExcelCell(rowYaoji, 9, styleSubstance, "污泥处理月投加量");
            createExcelCell(rowYaoji, 11, styleSubstance, "污泥处置月投加量");
            a++;


            for (SewEmiAccountVM.SewPotVM sewSluDetailDTO : sewEmiDTO.getSewPots()) {
                HSSFRow row11 = sheet.createRow((short) a);
                sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 1, (short) 2));
                sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 3, (short) 4));
                sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 5, (short) 6));
                sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 7, (short) 8));
                sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 9, (short) 10));
                sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 11, (short) 12));
                createExcelCell(row11, 0, styleContent2, sewSluDetailDTO.getPotionName());
                createExcelCell(row11, 1, styleContent2, sewSluDetailDTO.getTotalPot() + "kg/mth");
                createExcelCell(row11, 3, styleContent2, sewSluDetailDTO.getLevel1Pot() + "kg/mth");
                createExcelCell(row11, 5, styleContent2, sewSluDetailDTO.getLevel2Pot() + "kg/mth");
                createExcelCell(row11, 7, styleContent2, sewSluDetailDTO.getLevel3Pot() + "kg/mth");
                createExcelCell(row11, 9, styleContent2, sewSluDetailDTO.getSluTreatPot() + "kg/mth");
                createExcelCell(row11, 11, styleContent2, sewSluDetailDTO.getSluHandlePot() + "kg/mth");
                a++;
            }

            //负碳信息

            if (sewEmiDTO.getSolarPow() != null || sewEmiDTO.getHeatPumpHeat() != null || sewEmiDTO.getThermoElec() != null || sewEmiDTO.getThermoElec() != null || sewEmiDTO.getWindPow() != null || sewEmiDTO.getOtherEmiReduction() != null) {
                HSSFRow blankFutan = sheet.createRow((short) a);
                IntStream.range(0, 13).forEach(value -> createExcelCell(blankFutan, value, styleContent2, ""));              //填充空白列
                a++;
                HSSFRow rowFutan = sheet.createRow((short) a);
                createExcelCell(rowFutan, 0, styleSubstance2, "碳减排信息");
                IntStream.range(2, 13).forEach(value -> createExcelCell(rowFutan, value, styleContent2, ""));              //填充空白列
                a++;

                if (sewEmiDTO.getSolarPow() != null) {
                    HSSFRow row41 = sheet.createRow((short) a);
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 0, (short) 2));
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 3, (short) 5));
                    createExcelCell(row41, 0, styleContent4, "太阳能月发电量：");
                    createExcelCell(row41, 3, styleContent4, sewEmiDTO.getSolarPow() + "kWh/mth");
                    IntStream.range(6, 13).forEach(value -> createExcelCell(row41, value, styleContent2, ""));              //填充空白列
                    a++;
                }
                if (sewEmiDTO.getHeatPumpHeat() != null) {
                    HSSFRow row41 = sheet.createRow((short) a);
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 0, (short) 2));
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 3, (short) 5));
                    createExcelCell(row41, 0, styleContent4, "热泵供热量：");
                    createExcelCell(row41, 3, styleContent4, sewEmiDTO.getHeatPumpHeat() + "kJ/h");
                    IntStream.range(6, 13).forEach(value -> createExcelCell(row41, value, styleContent2, ""));              //填充空白列
                    a++;
                    HSSFRow row42 = sheet.createRow((short) a);
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 0, (short) 2));
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 3, (short) 5));
                    createExcelCell(row42, 0, styleContent4, "月热泵供热运行时间：");
                    createExcelCell(row42, 3, styleContent4, sewEmiDTO.getHeatPumpHotHours() + "h");
                    IntStream.range(6, 13).forEach(value -> createExcelCell(row42, value, styleContent2, ""));              //填充空白列
                    a++;
                    HSSFRow row43 = sheet.createRow((short) a);
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 0, (short) 2));
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 3, (short) 5));
                    createExcelCell(row43, 0, styleContent4, "热泵制冷量：");
                    createExcelCell(row43, 3, styleContent4, sewEmiDTO.getHeatPumpRefr() + "kJ/h");
                    IntStream.range(6, 13).forEach(value -> createExcelCell(row43, value, styleContent2, ""));              //填充空白列
                    a++;
                    HSSFRow row44 = sheet.createRow((short) a);
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 0, (short) 2));
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 3, (short) 5));
                    createExcelCell(row44, 0, styleContent4, "月热泵制冷运行时间：");
                    createExcelCell(row44, 3, styleContent4, sewEmiDTO.getHeatPumpColdHours() + "h");
                    IntStream.range(6, 13).forEach(value -> createExcelCell(row44, value, styleContent2, ""));              //填充空白列
                    a++;

                }
                if (sewEmiDTO.getThermoElec() != null) {
                    HSSFRow row42 = sheet.createRow((short) a);
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 0, (short) 2));
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 3, (short) 5));
                    createExcelCell(row42, 0, styleContent4, "月产电量：");
                    createExcelCell(row42, 3, styleContent4, sewEmiDTO.getThermoElec() + "kWh/mth");
                    IntStream.range(6, 13).forEach(value -> createExcelCell(row42, value, styleContent2, ""));              //填充空白列
                    a++;
                    HSSFRow row43 = sheet.createRow((short) a);
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 0, (short) 2));
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 3, (short) 5));
                    createExcelCell(row43, 0, styleContent4, "月产热能：");
                    createExcelCell(row43, 3, styleContent4, sewEmiDTO.getThermoEner() + "kJ/mth");
                    IntStream.range(6, 13).forEach(value -> createExcelCell(row43, value, styleContent2, ""));              //填充空白列

                    a++;
                }
                if (sewEmiDTO.getThermoElec() != null) {
                    HSSFRow row44 = sheet.createRow((short) a);
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 0, (short) 2));
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 3, (short) 5));
                    createExcelCell(row44, 0, styleContent4, "碳减排项目发电输送到电网的电量：");
                    createExcelCell(row44, 3, styleContent4, sewEmiDTO.getThermoElec() + "kWh/mth");
                    IntStream.range(6, 13).forEach(value -> createExcelCell(row44, value, styleContent2, ""));              //填充空白列
                    a++;
                }
                if (sewEmiDTO.getWindPow() != null) {
                    HSSFRow row43 = sheet.createRow((short) a);
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 0, (short) 2));
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 3, (short) 5));
                    createExcelCell(row43, 0, styleContent4, "风能月发电量：");
                    createExcelCell(row43, 3, styleContent4, sewEmiDTO.getWindPow() + "kWh/mth");
                    IntStream.range(6, 13).forEach(value -> createExcelCell(row43, value, styleContent2, ""));              //填充空白列
                    a++;
                }
                if (sewEmiDTO.getEcoComplexReduction() != null) {
                    HSSFRow row43 = sheet.createRow((short) a);
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 0, (short) 2));
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 3, (short) 5));
                    createExcelCell(row43, 0, styleContent4, "生态综合体碳减排量：");
                    createExcelCell(row43, 3, styleContent4, sewEmiDTO.getEcoComplexReduction().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "吨 CO2 /mth");
                    IntStream.range(6, 13).forEach(value -> createExcelCell(row43, value, styleContent2, ""));              //填充空白列
                    a++;
                }
                if (sewEmiDTO.getOtherEmiReduction() != null) {
                    HSSFRow row43 = sheet.createRow((short) a);
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 0, (short) 2));
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 3, (short) 5));
                    createExcelCell(row43, 0, styleContent4, "其他碳减排项目：");
                    createExcelCell(row43, 3, styleContent4, sewEmiDTO.getOtherText() + "");
                    IntStream.range(6, 13).forEach(value -> createExcelCell(row43, value, styleContent2, ""));              //填充空白列
                    a++;
                    HSSFRow row44 = sheet.createRow((short) a);
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 0, (short) 2));
                    sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 3, (short) 5));
                    createExcelCell(row44, 0, styleContent4, "月CO2减排量：");
                    createExcelCell(row44, 3, styleContent4, sewEmiDTO.getOtherEmiReduction().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "吨 CO2 /mth");
                    IntStream.range(6, 13).forEach(value -> createExcelCell(row44, value, styleContent2, ""));              //填充空白列
                    a++;
                }
            }


            //污泥处置信息
            HSSFRow blankWuni = sheet.createRow((short) a);
            IntStream.range(0, 13).forEach(value -> createExcelCell(blankWuni, value, styleContent2, ""));              //填充空白列
            a++;
            HSSFRow rowWuni = sheet.createRow((short) a);
            createExcelCell(rowWuni, 0, styleSubstance2, "污泥处置信息");
            IntStream.range(2, 13).forEach(value -> createExcelCell(rowWuni, value, styleContent2, ""));              //填充空白列
            a++;

            HSSFRow rowWuni2 = sheet.createRow((short) a);
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 0, (short) 2));
            createExcelCell(rowWuni2, 0, styleContent4, "*污泥处理后含水率(%)：" + getValue(sewEmiDTO.getSluMoistureAfterTreat()));
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 3, (short) 5));
            createExcelCell(rowWuni2, 3, styleContent4, "*污泥处理是否为本厂管理：".concat(sewEmiDTO.getManagedBySelf() ? "是" : "否"));
            IntStream.range(4, 13).forEach(value -> createExcelCell(rowWuni2, value, styleContent2, ""));              //填充空白列
            a++;

            HSSFRow blankWuni2 = sheet.createRow((short) a);
            IntStream.range(0, 13).forEach(value -> createExcelCell(blankWuni2, value, styleContent2, ""));              //填充空白列
            a++;

            int i = 0;
            for (SewEmiAccountVM.SewSluVM sewSluDetailDTO : sewEmiDTO.getSewSlus()) {
                HSSFRow rowWuni4 = sheet.createRow((short) a);
                sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 1, (short) 2));
                sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 3, (short) 4));
                sheet.addMergedRegion(new CellRangeAddress(a, (short) a, 5, (short) 6));
                if (i == 0) {
                    createExcelCell(rowWuni4, 0, styleContent2, "污泥处置方法");
                } else {
                    createExcelCell(rowWuni4, 0, styleContent2, "");
                }
                createExcelCell(rowWuni4, 1, styleContent5, sewSluDetailDTO.getMethodName());
                createExcelCell(rowWuni4, 3, styleContent2, "*污泥处置量:" + getValue(sewSluDetailDTO.getSluCapacity()) + " kg/mth");
                createExcelCell(rowWuni4, 5, styleContent2, "*污泥处理前含水率(%):" + getValue(sewSluDetailDTO.getSluMoisture()));
                IntStream.range(7, 13).forEach(value -> createExcelCell(rowWuni4, value, styleContent2, ""));              //填充空白列
                a++;
                i++;
            }


            //核算结果报告
            HSSFRow blankHesuan = sheet.createRow((short) a);
            IntStream.range(0, 13).forEach(value -> createExcelCell(blankHesuan, value, styleContent2, ""));              //填充空白列
            a++;
            HSSFRow rowHesuanjieguo = sheet.createRow((short) a);
            createExcelCell(rowHesuanjieguo, 0, styleSubstance2, "核算结果报告");
            IntStream.range(2, 13).forEach(value -> createExcelCell(rowHesuanjieguo, value, styleContent2, ""));              //填充空白列
            a++;

            //a2记录“核算结果报告”部分长度，用于统一设置样式（合并单元格）
            int a2 = a;

            //用于核算结果报告样式变化
            int colorSlector = 0;

            colorSlector++;
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a + 3, 0, (short) 3));
            HSSFRow row13 = sheet.createRow((short) a);
            HSSFCell ce1 = row13.createCell((short) 0);
            ce1.setCellValue("药剂投加间接碳排放量");
            HSSFCellStyle trueStyleSub;
            HSSFCellStyle trueStyleCont;
            if ((colorSlector & 1) == 1) {
                trueStyleSub = styleSubstance3;
                trueStyleCont = styleContent3;
            } else {
                trueStyleSub = styleSubstance4;
                trueStyleCont = styleContent4;
            }
            ce1.setCellStyle(trueStyleSub);
            createExcelCell(row13, 4, trueStyleCont, "一级处理药剂投加间接碳排放量");
            createExcelCell(row13, 7, trueStyleCont, getValue(sewEmiDTO.getLevel1PotEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP)));
            createExcelCell(row13, 10, trueStyleCont, "吨 CO2 /mth");
            a++;

            HSSFRow row14 = sheet.createRow((short) a);
            createExcelCell(row14, 4, trueStyleCont, "二级处理药剂投加间接碳排放量");
            createExcelCell(row14, 7, trueStyleCont, getValue(sewEmiDTO.getLevel2PotEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP)));
            createExcelCell(row14, 10, trueStyleCont, "吨 CO2 /mth");
            a++;

            HSSFRow row15 = sheet.createRow((short) a);
            createExcelCell(row15, 4, trueStyleCont, "三级处理药剂投加间接碳排放量");
            createExcelCell(row15, 7, trueStyleCont, getValue(sewEmiDTO.getLevel3PotEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP)));
            createExcelCell(row15, 10, trueStyleCont, "吨 CO2 /mth");
            a++;

            HSSFRow row17 = sheet.createRow((short) a);
            createExcelCell(row17, 4, trueStyleCont, "合计间接碳排放量");
            createExcelCell(row17, 7, trueStyleCont, getValue(sewEmiDTO.getTotalPotEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP)));
            createExcelCell(row17, 10, trueStyleCont, "吨 CO2 /mth");
            a++;

            colorSlector++;
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a + 6, 0, (short) 3));
            HSSFRow rowHaodian1 = sheet.createRow((short) a);
            HSSFCell haodian = rowHaodian1.createCell((short) 0);
            haodian.setCellValue("耗电间接碳排放量");
            if ((colorSlector & 1) == 1) {
                trueStyleSub = styleSubstance3;
                trueStyleCont = styleContent3;
            } else {
                trueStyleSub = styleSubstance4;
                trueStyleCont = styleContent4;
            }
            haodian.setCellStyle(trueStyleSub);

            createExcelCell(rowHaodian1, 4, trueStyleCont, "进水总泵站耗电间接碳排放量");
            createExcelCell(rowHaodian1, 7, trueStyleCont, sewEmiDTO.getLevel1PotEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowHaodian1, 10, trueStyleCont, "吨 CO2 /mth");
            a++;

            HSSFRow rowHaodian2 = sheet.createRow((short) a);
            createExcelCell(rowHaodian2, 4, trueStyleCont, "鼓风机房耗电间接碳排放量");
            createExcelCell(rowHaodian2, 7, trueStyleCont, sewEmiDTO.getBlowerPowEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowHaodian2, 10, trueStyleCont, "吨 CO2 /mth");
            a++;

            HSSFRow rowHaodian3 = sheet.createRow((short) a);
            createExcelCell(rowHaodian3, 4, trueStyleCont, "回流污泥泵房耗电间接碳排放量");
            createExcelCell(rowHaodian3, 7, trueStyleCont, sewEmiDTO.getRetSluPumpPowEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowHaodian3, 10, trueStyleCont, "吨 CO2 /mth");
            a++;

            HSSFRow rowHaodian5 = sheet.createRow((short) a);
            createExcelCell(rowHaodian5, 4, trueStyleCont, "紫外+氯消毒耗电间接碳排放量");
            createExcelCell(rowHaodian5, 7, trueStyleCont, sewEmiDTO.getFacilityPowEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowHaodian5, 10, trueStyleCont, "吨 CO2 /mth");
            a++;

            HSSFRow rowHaodian6 = sheet.createRow((short) a);
            createExcelCell(rowHaodian6, 4, trueStyleCont, "附属设施用电耗电间接碳排放量");
            createExcelCell(rowHaodian6, 7, trueStyleCont, sewEmiDTO.getDisinfectPowEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowHaodian6, 10, trueStyleCont, "吨 CO2 /mth");
            a++;

            HSSFRow rowHaodian7 = sheet.createRow((short) a);
            createExcelCell(rowHaodian7, 4, trueStyleCont, "其他耗电间接碳排放量");
            createExcelCell(rowHaodian7, 7, trueStyleCont, sewEmiDTO.getOtherPowEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowHaodian7, 10, trueStyleCont, "吨 CO2 /mth");
            a++;

            HSSFRow rowHaodian8 = sheet.createRow((short) a);
            createExcelCell(rowHaodian8, 4, trueStyleCont, "合计间接碳排放量");
            createExcelCell(rowHaodian8, 7, trueStyleCont, sewEmiDTO.getTotalPowEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowHaodian8, 10, trueStyleCont, "吨 CO2 /mth");
            a++;

            colorSlector++;
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a + 2, 0, (short) 3));
            HSSFRow rowWushuichuli1 = sheet.createRow((short) a);
            HSSFCell wushuichuli = rowWushuichuli1.createCell((short) 0);
            wushuichuli.setCellValue("污水处理直接碳排放量");
            if ((colorSlector & 1) == 1) {
                trueStyleSub = styleSubstance3;
                trueStyleCont = styleContent3;
            } else {
                trueStyleSub = styleSubstance4;
                trueStyleCont = styleContent4;
            }
            wushuichuli.setCellStyle(trueStyleSub);

            createExcelCell(rowWushuichuli1, 4, trueStyleCont, "污水处理CH4的CO2直接碳排放量");
            createExcelCell(rowWushuichuli1, 7, trueStyleCont, sewEmiDTO.getSewTreatCh4Emi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowWushuichuli1, 10, trueStyleCont, "吨 CO2 /mth");
            a++;
            HSSFRow rowWushuichuli2 = sheet.createRow((short) a);
            createExcelCell(rowWushuichuli2, 4, trueStyleCont, "污水处理N2O的CO2直接碳排放量");
            createExcelCell(rowWushuichuli2, 7, trueStyleCont, sewEmiDTO.getSewTreatN2oEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowWushuichuli2, 10, trueStyleCont, "吨 CO2 /mth");
            a++;
            HSSFRow rowWushuichuli3 = sheet.createRow((short) a);
            createExcelCell(rowWushuichuli3, 4, trueStyleCont, "合计直接碳排放量");
            createExcelCell(rowWushuichuli3, 7, trueStyleCont, sewEmiDTO.getTotalSewTreatEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowWushuichuli3, 10, trueStyleCont, "吨 CO2 /mth");
            a++;

            colorSlector++;
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a + 1, 0, (short) 3));
            HSSFRow rowWunichulijianjietanpai = sheet.createRow((short) a);
            HSSFCell cellWunichulijianjietanpai = rowWunichulijianjietanpai.createCell((short) 0);
            cellWunichulijianjietanpai.setCellValue("污泥处理间接碳排放量");
            if ((colorSlector & 1) == 1) {
                trueStyleSub = styleSubstance3;
                trueStyleCont = styleContent3;
            } else {
                trueStyleSub = styleSubstance4;
                trueStyleCont = styleContent4;
            }
            cellWunichulijianjietanpai.setCellStyle(trueStyleSub);
            createExcelCell(rowWunichulijianjietanpai, 4, trueStyleCont, "污泥处理耗电间接碳排放量");
            createExcelCell(rowWunichulijianjietanpai, 7, trueStyleCont, sewEmiDTO.getSluTreatPowEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowWunichulijianjietanpai, 10, trueStyleCont, "吨 CO2 /mth");
            a++;
            HSSFRow rowWunichulijianjietanpai2 = sheet.createRow((short) a);
            createExcelCell(rowWunichulijianjietanpai2, 4, trueStyleCont, "污泥处理药剂投加间接碳排放量");
            createExcelCell(rowWunichulijianjietanpai2, 7, trueStyleCont, getValue(sewEmiDTO.getSluTreatPotEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP)));
            createExcelCell(rowWunichulijianjietanpai2, 10, trueStyleCont, "吨 CO2 /mth");
            a++;


            colorSlector++;
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a + 6, 0, (short) 3));
            HSSFRow rowFutanjianpai1 = sheet.createRow((short) a);
            HSSFCell cellFutanjianpai = rowFutanjianpai1.createCell((short) 0);
            cellFutanjianpai.setCellValue("碳减排项目碳减排量");
            if ((colorSlector & 1) == 1) {
                trueStyleSub = styleSubstance3;
                trueStyleCont = styleContent3;
            } else {
                trueStyleSub = styleSubstance4;
                trueStyleCont = styleContent4;
            }
            cellFutanjianpai.setCellStyle(trueStyleSub);
            createExcelCell(rowFutanjianpai1, 4, trueStyleCont, "太阳能间接碳减排量");
            if (sewEmiDTO.getSolarPowRed().compareTo(BigDecimal.valueOf(0)) == 0) {
                createExcelCell(rowFutanjianpai1, 7, trueStyleCont, "0");
            } else {
                createExcelCell(rowFutanjianpai1, 7, trueStyleCont, "-" + sewEmiDTO.getSolarPowRed().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            }
            createExcelCell(rowFutanjianpai1, 10, trueStyleCont, "吨 CO2 /mth");
            a++;
            HSSFRow rowFutanjianpai2 = sheet.createRow((short) a);
            createExcelCell(rowFutanjianpai2, 4, trueStyleCont, "热泵间接碳减排量");
            if (sewEmiDTO.getHeatPumpRed().compareTo(BigDecimal.valueOf(0)) == 0) {
                createExcelCell(rowFutanjianpai2, 7, trueStyleCont, "0");
            } else {
                createExcelCell(rowFutanjianpai2, 7, trueStyleCont, "-" + sewEmiDTO.getHeatPumpRed().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            }
            createExcelCell(rowFutanjianpai2, 10, trueStyleCont, "吨 CO2 /mth");
            a++;
            HSSFRow rowFutanjianpai4 = sheet.createRow((short) a);
            createExcelCell(rowFutanjianpai4, 4, trueStyleCont, "热电联产产热间接碳减排量");
            if (sewEmiDTO.getThermoEnerRed().compareTo(BigDecimal.valueOf(0)) == 0) {
                createExcelCell(rowFutanjianpai4, 7, trueStyleCont, "0");
            } else {
                createExcelCell(rowFutanjianpai4, 7, trueStyleCont, "-" + sewEmiDTO.getThermoEnerRed().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            }
            createExcelCell(rowFutanjianpai4, 10, trueStyleCont, "吨 CO2 /mth");
            a++;
            HSSFRow rowFutanjianpai3 = sheet.createRow((short) a);
            createExcelCell(rowFutanjianpai3, 4, trueStyleCont, "热电联产发电间接碳减排量");
            if (sewEmiDTO.getThermoElecRed().compareTo(BigDecimal.valueOf(0)) == 0) {
                createExcelCell(rowFutanjianpai3, 7, trueStyleCont, "0");
            } else {
                createExcelCell(rowFutanjianpai3, 7, trueStyleCont, "-" + sewEmiDTO.getThermoElecRed().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            }
            createExcelCell(rowFutanjianpai3, 10, trueStyleCont, "吨 CO2 /mth");
            a++;
            HSSFRow rowFutanjianpai6 = sheet.createRow((short) a);
            createExcelCell(rowFutanjianpai6, 4, trueStyleCont, "风电间接碳减排量");
            if (sewEmiDTO.getWindPowRed().compareTo(BigDecimal.valueOf(0)) == 0) {
                createExcelCell(rowFutanjianpai6, 7, trueStyleCont, "0");
            } else {
                createExcelCell(rowFutanjianpai6, 7, trueStyleCont, "-" + sewEmiDTO.getWindPowRed().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            }
            createExcelCell(rowFutanjianpai6, 10, trueStyleCont, "吨 CO2 /mth");
            a++;
            HSSFRow rowFutanjianpai7 = sheet.createRow((short) a);
            createExcelCell(rowFutanjianpai7, 4, trueStyleCont, "生态综合体间接碳减排量");
            if (sewEmiDTO.getEcoComplexRed().compareTo(BigDecimal.valueOf(0)) == 0) {
                createExcelCell(rowFutanjianpai7, 7, trueStyleCont, "0");
            } else {
                createExcelCell(rowFutanjianpai7, 7, trueStyleCont, "-" + sewEmiDTO.getEcoComplexRed().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            }
            createExcelCell(rowFutanjianpai7, 10, trueStyleCont, "吨 CO2 /mth");
            a++;
            HSSFRow rowFutanjianpai5 = sheet.createRow((short) a);
            createExcelCell(rowFutanjianpai5, 4, trueStyleCont, "其他碳减排项目直接碳减排量");
            if (sewEmiDTO.getOtherEmiRed().compareTo(BigDecimal.valueOf(0)) == 0) {
                createExcelCell(rowFutanjianpai5, 7, trueStyleCont, "0");
            } else {
                createExcelCell(rowFutanjianpai5, 7, trueStyleCont, "-" + sewEmiDTO.getOtherEmiRed().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            }
            createExcelCell(rowFutanjianpai5, 10, trueStyleCont, "吨 CO2 /mth");
            a++;

            colorSlector++;
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a + 4, 0, (short) 3));
            HSSFRow rowWunichuzhi1 = sheet.createRow((short) a);
            HSSFCell wunichuzhi = rowWunichuzhi1.createCell((short) 0);
            wunichuzhi.setCellValue("污泥处置碳排量(".concat(sewEmiDTO.getManagedBySelf() ? "本厂管理)" : "非本厂管理)"));
            if ((colorSlector & 1) == 1) {
                trueStyleSub = styleSubstance3;
                trueStyleCont = styleContent3;
            } else {
                trueStyleSub = styleSubstance4;
                trueStyleCont = styleContent4;
            }
            wunichuzhi.setCellStyle(trueStyleSub);
            createExcelCell(rowWunichuzhi1, 4, trueStyleCont, "污泥处置CH4的CO2直接碳排放量");
            createExcelCell(rowWunichuzhi1, 7, trueStyleCont, sewEmiDTO.getSluHandleCh4Emi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowWunichuzhi1, 10, trueStyleCont, "吨 CO2 /mth");
            a++;
            HSSFRow rowWunichuzhi8 = sheet.createRow((short) a);
            createExcelCell(rowWunichuzhi8, 4, trueStyleCont, "污泥处置NO2的CO2直接碳排放量");
            createExcelCell(rowWunichuzhi8, 7, trueStyleCont, sewEmiDTO.getSluHandleN2oEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowWunichuzhi8, 10, trueStyleCont, "吨 CO2 /mth");
            a++;
            HSSFRow rowWunichuzhi5 = sheet.createRow((short) a);
            createExcelCell(rowWunichuzhi5, 4, trueStyleCont, "污泥处置耗电间接碳排放量");
            createExcelCell(rowWunichuzhi5, 7, trueStyleCont, sewEmiDTO.getSluHandlePowEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowWunichuzhi5, 10, trueStyleCont, "吨 CO2 /mth");
            a++;
            HSSFRow rowWunichuzhi4 = sheet.createRow((short) a);
            createExcelCell(rowWunichuzhi4, 4, trueStyleCont, "污泥处置药剂投加间接碳排放量");
            createExcelCell(rowWunichuzhi4, 7, trueStyleCont, sewEmiDTO.getSluHandlePotEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowWunichuzhi4, 10, trueStyleCont, "吨 CO2 /mth");
            a++;
            HSSFRow rowWunichuzhi6 = sheet.createRow((short) a);
            createExcelCell(rowWunichuzhi6, 4, trueStyleCont, "污泥处置间接碳排放量");
            createExcelCell(rowWunichuzhi6, 7, trueStyleCont, sewEmiDTO.getTotalSluHandleIndirEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowWunichuzhi6, 10, trueStyleCont, "吨 CO2 /mth");
            a++;

            colorSlector++;
            sheet.addMergedRegion(new CellRangeAddress(a, (short) a + 3, 0, (short) 3));
            HSSFRow rowBenyueheji1 = sheet.createRow((short) a);
            HSSFCell cellBenyueheji = rowBenyueheji1.createCell((short) 0);
            cellBenyueheji.setCellValue("本月合计");
            if ((colorSlector & 1) == 1) {
                trueStyleSub = styleSubstance3;
                trueStyleCont = styleContent3;
            } else {
                trueStyleSub = styleSubstance4;
                trueStyleCont = styleContent4;
            }
            cellBenyueheji.setCellStyle(trueStyleSub);
            createExcelCell(rowBenyueheji1, 4, trueStyleCont, "直接碳排放量");
            createExcelCell(rowBenyueheji1, 7, trueStyleCont, sewEmiDTO.getCarbonDirEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowBenyueheji1, 10, trueStyleCont, "吨 CO2 /mth");
            a++;
            HSSFRow rowBenyueheji2 = sheet.createRow((short) a);
            createExcelCell(rowBenyueheji2, 4, trueStyleCont, "间接碳排放量");
            createExcelCell(rowBenyueheji2, 7, trueStyleCont, sewEmiDTO.getCarbonIndirEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowBenyueheji2, 10, trueStyleCont, "吨 CO2 /mth");
            a++;
            HSSFRow rowBenyueheji3 = sheet.createRow((short) a);
            createExcelCell(rowBenyueheji3, 4, trueStyleCont, "碳减排量");
            createExcelCell(rowBenyueheji3, 7, trueStyleCont, sewEmiDTO.getCarbonRed().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowBenyueheji3, 10, trueStyleCont, "吨 CO2 /mth");
            a++;
            HSSFRow rowBenyueheji4 = sheet.createRow((short) a);
            createExcelCell(rowBenyueheji4, 4, trueStyleCont, "合计");
            createExcelCell(rowBenyueheji4, 7, trueStyleCont, sewEmiDTO.getCarbonEmi().divide(BigDecimal.valueOf(1000), 0, RoundingMode.HALF_UP) + "");
            createExcelCell(rowBenyueheji4, 10, trueStyleCont, "吨 CO2 /mth");
            a++;


            IntStream.range(a2, a).forEach(value -> {
                sheet.addMergedRegion(new CellRangeAddress(value, (short) value, 4, (short) 6));
                sheet.addMergedRegion(new CellRangeAddress(value, (short) value, 7, (short) 9));
                sheet.addMergedRegion(new CellRangeAddress(value, (short) value, 10, (short) 12));
            });

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
                                       HSSFCellStyle style, String contents) {
        HSSFCell cell1 = rows.createCell((short) col);
        cell1.setCellValue(contents);
        cell1.setCellStyle(style);
    }

    /**
     * 创建核算结果行
     *
     * @param row
     * @param sheet
     * @param a
     * @param style
     * @param x
     * @param y
     * @param z
     */


}
