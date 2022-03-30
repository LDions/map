package com.ruowei.util.excel;

import com.ruowei.service.dto.SewEmiFactorDTO;
import com.ruowei.web.rest.dto.CarbonEmiDataDTO;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * excel工具类 提供导出excel的功能
 */
public class ExcelExport {

    /**
     * 生成excel 碳排放核算结果列表Excel生成
     *
     * @param list
     * @return
     */
    public static byte[] createExcel(List<CarbonEmiDataDTO> list) {
        try {
            // 创建一个新的HSSFWorkbook对象
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 创建一个Excel的工作表
            HSSFSheet sheet = workbook.createSheet("碳排放核算数据列表");
            workbook.setSheetName(0, "碳排放核算数据列表");//解决sheet名中文乱码问题
            // 创建字体，红色、粗体
            HSSFFont font = workbook.createFont();
            font.setColor(HSSFFont.COLOR_NORMAL);
            font.setBold(true);
            font.setFontHeight((short) 5);
            font.setFontName("宋体");
            font.setFontHeightInPoints((short) 20);
            // 创建单元格的格式
            HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
            style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
            style.setAlignment(HorizontalAlignment.CENTER);// 水平
            style.setFont(font);
            //表头字体
            HSSFFont fontContent = workbook.createFont();
            fontContent.setColor(HSSFFont.COLOR_NORMAL);
            fontContent.setFontHeight((short) 5);
            fontContent.setFontName("宋体");
            fontContent.setFontHeightInPoints((short) 10);
            //表格内容字体
            HSSFFont fontSubstance = workbook.createFont();
            fontSubstance.setColor(HSSFFont.COLOR_NORMAL);
            fontSubstance.setBold(true);
            fontSubstance.setFontHeight((short) 5);
            fontSubstance.setFontName("宋体");
            fontSubstance.setFontHeightInPoints((short) 10);
            //表格外部样式
            HSSFCellStyle styleContent = workbook.createCellStyle(); // 样式对象
            styleContent.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
            styleContent.setAlignment(HorizontalAlignment.CENTER);// 水平
            styleContent.setFont(fontContent);
            HSSFCellStyle styleContent2 = workbook.createCellStyle(); // 样式对象
            styleContent2.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
            styleContent2.setAlignment(HorizontalAlignment.CENTER);// 水平
            styleContent2.setFont(fontContent);
            //表格内容样式
            HSSFCellStyle styleSubstance = workbook.createCellStyle(); // 样式对象
            styleSubstance.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
            styleSubstance.setAlignment(HorizontalAlignment.CENTER);// 水平
            styleSubstance.setFont(fontSubstance);
            HSSFRow row = sheet.createRow((short) 0);
            //合并单元格(前3行10列合并)
            sheet.addMergedRegion(new CellRangeAddress(0, (short) 2, 0, (short) 9));
            HSSFCell ce = row.createCell((short) 0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            String date = "_" + sdf.format(new Date());
            ce.setCellValue("碳排放核算数据列表" + date); // 表格的第一行第一列显示的数据
            ce.setCellStyle(style); // 样式，居中

/*
            颜色选择
*/

            styleContent2.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            styleContent2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //橙色
            style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
            styleSubstance.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
            styleContent.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
            //蓝色
//            style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
//            styleSubstance.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
//            styleContent.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
            //绿色
//            style.setFillForegroundColor(IndexedColors.LIME.getIndex());
//            styleSubstance.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
//            styleContent.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleSubstance.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleContent.setFillPattern(FillPatternType.SOLID_FOREGROUND);
/*
            表头
*/
            HSSFRow row2 = sheet.createRow((short) 3); //表格第4行
            createExcelCell(row2, 0, styleSubstance, "序号");
            createExcelCell(row2, 1, styleSubstance, "单据号");
            createExcelCell(row2, 2, styleSubstance, "企业名称");
            createExcelCell(row2, 3, styleSubstance, "行业类型");
            createExcelCell(row2, 4, styleSubstance, "核算时间");
            createExcelCell(row2, 5, styleSubstance, "本月负碳排放");
            createExcelCell(row2, 6, styleSubstance, "本月直接碳排放");
            createExcelCell(row2, 7, styleSubstance, "本月间接碳排放");
            createExcelCell(row2, 8, styleSubstance, "录入员");
            createExcelCell(row2, 9, styleSubstance, "填报时间");
/*
            添加内容
*/
            for (int i = 0; i < list.size(); i += 2) {
                HSSFRow rows = sheet.createRow((short) i + 4);//表格内容是从第5行开始，每循环一次增加一行
                createExcelCell(rows, 0, styleContent, String.valueOf(i + 1));
                createExcelCell(rows, 1, styleContent, list.get(i).getDocumentCode());
                createExcelCell(rows, 2, styleContent, list.get(i).getEnterpriseName());
                createExcelCell(rows, 3, styleContent, list.get(i).getIndustryName());
                createExcelCell(rows, 4, styleContent, list.get(i).getAccYear() + list.get(i).getAccMonth());
                createExcelCell(rows, 5, styleContent, list.get(i).getCarbonRed() + "kg CO2/mth");
                createExcelCell(rows, 6, styleContent, list.get(i).getCarbonDirEmi() + "kg CO2/mth");
                createExcelCell(rows, 7, styleContent, list.get(i).getCarbonIndirEmi() + "kg CO2/mth");
                createExcelCell(rows, 8, styleContent, list.get(i).getReporterName());
                createExcelCell(rows, 9, styleContent, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date.from(list.get(i).getReportTime())));
            }
            for (int i = 1; i < list.size(); i += 2) {
                HSSFRow rows = sheet.createRow((short) i + 4);//表格内容是从第5行开始，每循环一次增加一行
                createExcelCell(rows, 0, styleContent2, String.valueOf(i + 1));
                createExcelCell(rows, 1, styleContent2, list.get(i).getDocumentCode());
                createExcelCell(rows, 2, styleContent2, list.get(i).getEnterpriseName());
                createExcelCell(rows, 3, styleContent2, list.get(i).getIndustryName());
                createExcelCell(rows, 4, styleContent2, list.get(i).getAccYear() + list.get(i).getAccMonth());
                createExcelCell(rows, 5, styleContent2, list.get(i).getCarbonRed() + "kg CO2/mth");
                createExcelCell(rows, 6, styleContent2, list.get(i).getCarbonDirEmi() + "kg CO2/mth");
                createExcelCell(rows, 7, styleContent2, list.get(i).getCarbonIndirEmi() + "kg CO2/mth");
                createExcelCell(rows, 8, styleContent2, list.get(i).getReporterName());
                createExcelCell(rows, 9, styleContent2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date.from(list.get(i).getReportTime())));
            }
            sheet.autoSizeColumn((short) 0);
            sheet.autoSizeColumn((short) 1);
            sheet.autoSizeColumn((short) 2);
            sheet.autoSizeColumn((short) 3);
            sheet.autoSizeColumn((short) 4);
            sheet.autoSizeColumn((short) 5);
            sheet.autoSizeColumn((short) 6);
            sheet.autoSizeColumn((short) 7);
            sheet.autoSizeColumn((short) 8);
            sheet.autoSizeColumn((short) 9);
/*
            导出
*/

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 水务碳排放因子管理Excel
     *
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @param p5
     * @param p6
     * @param p7
     * @return
     */
    public static byte[] createExcelSewageEmi(LinkedHashMap<String, SewEmiFactorDTO.Info> p1, LinkedHashMap<String, SewEmiFactorDTO.Info> p2, SewEmiFactorDTO.GasEmiFactor p3, LinkedHashMap<String, SewEmiFactorDTO.InfoPro> p4, SewEmiFactorDTO.SluTreatFactor p5, SewEmiFactorDTO.SewTreatFactor p6, SewEmiFactorDTO.HeatPumpFactor p7) {
        try {
            // 创建一个新的HSSFWorkbook对象
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 创建字体，红色、粗体
            HSSFFont font = workbook.createFont();
            font.setColor(HSSFFont.COLOR_NORMAL);
            font.setBold(true);
            font.setFontHeight((short) 5);
            font.setFontName("宋体");
            font.setFontHeightInPoints((short) 20);
            // 创建单元格的格式
            HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
            style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
            style.setAlignment(HorizontalAlignment.CENTER);// 水平
            style.setFont(font);
            //表头字体
            HSSFFont fontContent = workbook.createFont();
            fontContent.setColor(HSSFFont.COLOR_NORMAL);
            fontContent.setFontHeight((short) 5);
            fontContent.setFontName("宋体");
            fontContent.setFontHeightInPoints((short) 10);
            //表格内容字体
            HSSFFont fontSubstance = workbook.createFont();
            fontSubstance.setColor(HSSFFont.COLOR_NORMAL);
            fontSubstance.setBold(true);
            fontSubstance.setFontHeight((short) 5);
            fontSubstance.setFontName("宋体");
            fontSubstance.setFontHeightInPoints((short) 10);
            //表格外部样式
            HSSFCellStyle styleContent = workbook.createCellStyle(); // 样式对象
            styleContent.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
            styleContent.setAlignment(HorizontalAlignment.CENTER);// 水平
            styleContent.setFont(fontContent);
            HSSFCellStyle styleContent2 = workbook.createCellStyle(); // 样式对象
            styleContent2.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
            styleContent2.setAlignment(HorizontalAlignment.CENTER);// 水平
            styleContent2.setFont(fontContent);
            //表格内容样式
            HSSFCellStyle styleSubstance = workbook.createCellStyle(); // 样式对象
            styleSubstance.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
            styleSubstance.setAlignment(HorizontalAlignment.CENTER);// 水平
            styleSubstance.setFont(fontSubstance);

/*
            颜色选择
*/
            //橙色
//            style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
//            styleSubstance.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
//            styleContent.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
            //蓝色
//            style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
//            styleSubstance.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
//            styleContent.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
            //绿色
            style.setFillForegroundColor(IndexedColors.LIME.getIndex());
            styleSubstance.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
            styleContent.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleSubstance.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleContent.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            styleContent2.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            styleContent2.setFillPattern(FillPatternType.SOLID_FOREGROUND);


            //药剂排放参数
            HSSFSheet sheet = workbook.createSheet("药剂排放参数");
            workbook.setSheetName(0, "药剂排放参数");
            HSSFRow row = sheet.createRow((short) 0);
            sheet.addMergedRegion(new CellRangeAddress(0, (short) 2, 0, (short) 4));
            HSSFCell ce = row.createCell((short) 0);
            ce.setCellValue("药剂排放参数");
            ce.setCellStyle(style);
            HSSFRow row2 = sheet.createRow((short) 3);
            createExcelCell(row2, 0, styleSubstance, "序号");
            createExcelCell(row2, 1, styleSubstance, "NOTE");
            createExcelCell(row2, 2, styleSubstance, "NAME");
            createExcelCell(row2, 3, styleSubstance, "DATA");
            createExcelCell(row2, 4, styleSubstance, "UNIT");
            int i = 0;
            for (Map.Entry<String, SewEmiFactorDTO.Info> next : p1.entrySet()) {
                HSSFCellStyle trueStyle;
                if (i % 2 != 0) {
                    trueStyle = styleContent;
                } else {
                    trueStyle = styleContent2;
                }
                HSSFRow rows = sheet.createRow((short) i + 4);
                createExcelCell(rows, 0, trueStyle, String.valueOf(i + 1));
                createExcelCell(rows, 1, trueStyle, next.getValue().getName());
                createExcelCell(rows, 2, trueStyle, next.getValue().getTitle());
                createExcelCell(rows, 3, trueStyle, String.valueOf(next.getValue().getValue()));
                createExcelCell(rows, 4, trueStyle, next.getValue().getUnit());
                i++;
            }
            autoSizeColumn(sheet, 5);
            //工艺排放参数
            HSSFSheet sheet2 = workbook.createSheet("工艺排放参数");
            workbook.setSheetName(1, "工艺排放参数");
            HSSFRow rowGongyi = sheet2.createRow((short) 0);
            sheet2.addMergedRegion(new CellRangeAddress(0, (short) 2, 0, (short) 4));
            HSSFCell ceGongyi = rowGongyi.createCell((short) 0);
            ceGongyi.setCellValue("工艺排放参数");
            ceGongyi.setCellStyle(style);
            HSSFRow rowGongyi2 = sheet2.createRow((short) 3);
            createExcelCell(rowGongyi2, 0, styleSubstance, "序号");
            createExcelCell(rowGongyi2, 1, styleSubstance, "NOTE");
            createExcelCell(rowGongyi2, 2, styleSubstance, "NAME");
            createExcelCell(rowGongyi2, 3, styleSubstance, "DATA");
            createExcelCell(rowGongyi2, 4, styleSubstance, "UNIT");
            i = 0;
            for (Map.Entry<String, SewEmiFactorDTO.Info> next : p2.entrySet()) {
                HSSFCellStyle trueStyle;
                if (i % 2 != 0) {
                    trueStyle = styleContent;
                } else {
                    trueStyle = styleContent2;
                }
                HSSFRow rows = sheet2.createRow((short) i + 4);
                createExcelCell(rows, 0, trueStyle, String.valueOf(i + 1));
                createExcelCell(rows, 1, trueStyle, next.getValue().getName());
                createExcelCell(rows, 2, trueStyle, next.getValue().getTitle());
                createExcelCell(rows, 3, trueStyle, String.valueOf(next.getValue().getValue()));
                createExcelCell(rows, 4, trueStyle, next.getValue().getUnit());
                i++;
            }
            autoSizeColumn(sheet2, 5);
            //气体排放参数
            HSSFSheet sheet3 = workbook.createSheet("气体排放参数");
            workbook.setSheetName(2, "气体排放参数");
            HSSFRow rowGas = sheet3.createRow((short) 0);
            sheet3.addMergedRegion(new CellRangeAddress(0, (short) 2, 0, (short) 4));
            HSSFCell ceGas = rowGas.createCell((short) 0);
            ceGas.setCellValue("气体排放参数");
            ceGas.setCellStyle(style);
            HSSFRow rowGas2 = sheet3.createRow((short) 3);
            createExcelCell(rowGas2, 0, styleSubstance, "序号");
            createExcelCell(rowGas2, 1, styleSubstance, "NOTE");
            createExcelCell(rowGas2, 2, styleSubstance, "NAME");
            createExcelCell(rowGas2, 3, styleSubstance, "DATA");
            createExcelCell(rowGas2, 4, styleSubstance, "UNIT");
            HSSFRow rowCfhp = sheet3.createRow((short) 4);
            createExcelCell(rowCfhp, 0, styleContent2, String.valueOf(1));
            createExcelCell(rowCfhp, 1, styleContent2, p3.getCfhp().getName());
            createExcelCell(rowCfhp, 2, styleContent2, p3.getCfhp().getTitle());
            createExcelCell(rowCfhp, 3, styleContent2, String.valueOf(p3.getCfhp().getValue()));
            createExcelCell(rowCfhp, 4, styleContent2, p3.getCfhp().getUnit());
            HSSFRow rowCfco2 = sheet3.createRow((short) 5);
            createExcelCell(rowCfco2, 0, styleContent, String.valueOf(2));
            createExcelCell(rowCfco2, 1, styleContent, p3.getCfco2().getName());
            createExcelCell(rowCfco2, 2, styleContent, p3.getCfco2().getTitle());
            createExcelCell(rowCfco2, 3, styleContent, String.valueOf(p3.getCfco2().getValue()));
            createExcelCell(rowCfco2, 4, styleContent, p3.getCfco2().getUnit());
            HSSFRow rowCfch4 = sheet3.createRow((short) 6);
            createExcelCell(rowCfch4, 0, styleContent2, String.valueOf(3));
            createExcelCell(rowCfch4, 1, styleContent2, p3.getCfch4().getName());
            createExcelCell(rowCfch4, 2, styleContent2, p3.getCfch4().getTitle());
            createExcelCell(rowCfch4, 3, styleContent2, String.valueOf(p3.getCfch4().getValue()));
            createExcelCell(rowCfch4, 4, styleContent2, p3.getCfch4().getUnit());
            HSSFRow rowCfn2o = sheet3.createRow((short) 7);
            createExcelCell(rowCfn2o, 0, styleContent, String.valueOf(4));
            createExcelCell(rowCfn2o, 1, styleContent, p3.getCfn2o().getName());
            createExcelCell(rowCfn2o, 2, styleContent, p3.getCfn2o().getTitle());
            createExcelCell(rowCfn2o, 3, styleContent, String.valueOf(p3.getCfn2o().getValue()));
            createExcelCell(rowCfn2o, 4, styleContent, p3.getCfn2o().getUnit());
            autoSizeColumn(sheet3, 5);
            //省电网排放参数
            HSSFSheet sheet4 = workbook.createSheet("省电网排放参数");
            workbook.setSheetName(3, "省电网排放参数");
            HSSFRow rowProElec = sheet4.createRow((short) 0);
            sheet4.addMergedRegion(new CellRangeAddress(0, (short) 2, 0, (short) 4));
            HSSFCell ceProElec = rowProElec.createCell((short) 0);
            ceProElec.setCellValue("省电网排放参数");
            ceProElec.setCellStyle(style);
            HSSFRow rowProElec2 = sheet4.createRow((short) 3);
            createExcelCell(rowProElec2, 0, styleSubstance, "序号");
            createExcelCell(rowProElec2, 1, styleSubstance, "NOTE");
            createExcelCell(rowProElec2, 2, styleSubstance, "NAME");
            createExcelCell(rowProElec2, 3, styleSubstance, "DATA");
            createExcelCell(rowProElec2, 4, styleSubstance, "UNIT");

            i = 0;
            for (Map.Entry<String, SewEmiFactorDTO.InfoPro> next : p4.entrySet()) {
                HSSFCellStyle trueStyle;
                if (i % 2 != 0) {
                    trueStyle = styleContent;
                } else {
                    trueStyle = styleContent2;
                }
                HSSFRow rows = sheet4.createRow((short) i + 4);
                createExcelCell(rows, 0, trueStyle, String.valueOf(i + 1));
                createExcelCell(rows, 1, trueStyle, next.getValue().getName());
                createExcelCell(rows, 2, trueStyle, next.getValue().getTitle());
                createExcelCell(rows, 3, trueStyle, String.valueOf(next.getValue().getValue()));
                createExcelCell(rows, 4, trueStyle, next.getValue().getUnit());
                i++;
            }
            autoSizeColumn(sheet4, 5);
            //污泥处理排放参数
            HSSFSheet sheet5 = workbook.createSheet("污泥处理排放参数");
            workbook.setSheetName(4, "污泥处理排放参数");
            HSSFRow rowSluTreat = sheet5.createRow((short) 0);
            sheet5.addMergedRegion(new CellRangeAddress(0, (short) 2, 0, (short) 4));
            HSSFCell ceSluTreat = rowSluTreat.createCell((short) 0);
            ceSluTreat.setCellValue("污泥处理排放参数");
            ceSluTreat.setCellStyle(style);
            HSSFRow rowSluTreat2 = sheet5.createRow((short) 3);
            createExcelCell(rowSluTreat2, 0, styleSubstance, "序号");
            createExcelCell(rowSluTreat2, 1, styleSubstance, "NOTE");
            createExcelCell(rowSluTreat2, 2, styleSubstance, "NAME");
            createExcelCell(rowSluTreat2, 3, styleSubstance, "DATA");
            createExcelCell(rowSluTreat2, 4, styleSubstance, "UNIT");
            HSSFRow rowWc = sheet5.createRow((short) 4);
            createExcelCell(rowWc, 0, styleContent, String.valueOf(1));
            createExcelCell(rowWc, 1, styleContent, p5.getWc().getName());
            createExcelCell(rowWc, 2, styleContent, p5.getWc().getTitle());
            createExcelCell(rowWc, 3, styleContent, String.valueOf(p5.getWc().getValue()));
            createExcelCell(rowWc, 4, styleContent, p5.getWc().getUnit());
            HSSFRow rowDoc = sheet5.createRow((short) 5);
            createExcelCell(rowDoc, 0, styleContent2, String.valueOf(2));
            createExcelCell(rowDoc, 1, styleContent2, p5.getDoc().getName());
            createExcelCell(rowDoc, 2, styleContent2, p5.getDoc().getTitle());
            createExcelCell(rowDoc, 3, styleContent2, String.valueOf(p5.getDoc().getValue()));
            createExcelCell(rowDoc, 4, styleContent2, p5.getDoc().getUnit());
            HSSFRow rowEfs1 = sheet5.createRow((short) 6);
            createExcelCell(rowEfs1, 0, styleContent, String.valueOf(3));
            createExcelCell(rowEfs1, 1, styleContent, p5.getEfs1().getName());
            createExcelCell(rowEfs1, 2, styleContent, p5.getEfs1().getTitle());
            createExcelCell(rowEfs1, 3, styleContent, String.valueOf(p5.getEfs1().getValue()));
            createExcelCell(rowEfs1, 4, styleContent, p5.getEfs1().getUnit());
            HSSFRow rowEfs2 = sheet5.createRow((short) 7);
            createExcelCell(rowEfs2, 0, styleContent2, String.valueOf(4));
            createExcelCell(rowEfs2, 1, styleContent2, p5.getEfs2().getName());
            createExcelCell(rowEfs2, 2, styleContent2, p5.getEfs2().getTitle());
            createExcelCell(rowEfs2, 3, styleContent2, String.valueOf(p5.getEfs2().getValue()));
            createExcelCell(rowEfs2, 4, styleContent2, p5.getEfs2().getUnit());
            HSSFRow rowDocf = sheet5.createRow((short) 8);
            createExcelCell(rowDocf, 0, styleContent, String.valueOf(5));
            createExcelCell(rowDocf, 1, styleContent, p5.getDocf().getName());
            createExcelCell(rowDocf, 2, styleContent, p5.getDocf().getTitle());
            createExcelCell(rowDocf, 3, styleContent, String.valueOf(p5.getDocf().getValue()));
            createExcelCell(rowDocf, 4, styleContent, p5.getDocf().getUnit());
            HSSFRow rowMcf = sheet5.createRow((short) 9);
            createExcelCell(rowMcf, 0, styleContent2, String.valueOf(6));
            createExcelCell(rowMcf, 1, styleContent2, p5.getMcf().getName());
            createExcelCell(rowMcf, 2, styleContent2, p5.getMcf().getTitle());
            createExcelCell(rowMcf, 3, styleContent2, String.valueOf(p5.getMcf().getValue()));
            createExcelCell(rowMcf, 4, styleContent2, p5.getMcf().getUnit());
            HSSFRow rowF = sheet5.createRow((short) 10);
            createExcelCell(rowF, 0, styleContent, String.valueOf(7));
            createExcelCell(rowF, 1, styleContent, p5.getF().getName());
            createExcelCell(rowF, 2, styleContent, p5.getF().getTitle());
            createExcelCell(rowF, 3, styleContent, String.valueOf(p5.getF().getValue()));
            createExcelCell(rowF, 4, styleContent, p5.getF().getUnit());
            HSSFRow rowOx = sheet5.createRow((short) 11);
            createExcelCell(rowOx, 0, styleContent2, String.valueOf(8));
            createExcelCell(rowOx, 1, styleContent2, p5.getOx().getName());
            createExcelCell(rowOx, 2, styleContent2, p5.getOx().getTitle());
            createExcelCell(rowOx, 3, styleContent2, String.valueOf(p5.getOx().getValue()));
            createExcelCell(rowOx, 4, styleContent2, p5.getOx().getUnit());
            HSSFRow rowEfs3 = sheet5.createRow((short) 12);
            createExcelCell(rowEfs3, 0, styleContent, String.valueOf(9));
            createExcelCell(rowEfs3, 1, styleContent, p5.getEfs3().getName());
            createExcelCell(rowEfs3, 2, styleContent, p5.getEfs3().getTitle());
            createExcelCell(rowEfs3, 3, styleContent, String.valueOf(p5.getEfs3().getValue()));
            createExcelCell(rowEfs3, 4, styleContent, p5.getEfs3().getUnit());
            HSSFRow rowEfs4 = sheet5.createRow((short) 13);
            createExcelCell(rowEfs4, 0, styleContent2, String.valueOf(10));
            createExcelCell(rowEfs4, 1, styleContent2, p5.getEfs4().getName());
            createExcelCell(rowEfs4, 2, styleContent2, p5.getEfs4().getTitle());
            createExcelCell(rowEfs4, 3, styleContent2, String.valueOf(p5.getEfs4().getValue()));
            createExcelCell(rowEfs4, 4, styleContent2, p5.getEfs4().getUnit());
            autoSizeColumn(sheet5, 5);
            //污水处理排放参数
            HSSFSheet sheet6 = workbook.createSheet("污水处理排放参数");
            workbook.setSheetName(5, "污水处理排放参数");
            HSSFRow rowSewTreat = sheet6.createRow((short) 0);
            sheet6.addMergedRegion(new CellRangeAddress(0, (short) 2, 0, (short) 4));
            HSSFCell ceSewTreat = rowSewTreat.createCell((short) 0);
            ceSewTreat.setCellValue("污水处理排放参数");
            ceSewTreat.setCellStyle(style);
            HSSFRow rowSewTreat2 = sheet6.createRow((short) 3);
            createExcelCell(rowSewTreat2, 0, styleSubstance, "序号");
            createExcelCell(rowSewTreat2, 1, styleSubstance, "NOTE");
            createExcelCell(rowSewTreat2, 2, styleSubstance, "NAME");
            createExcelCell(rowSewTreat2, 3, styleSubstance, "DATA");
            createExcelCell(rowSewTreat2, 4, styleSubstance, "UNIT");

            HSSFRow rowB0bod = sheet6.createRow((short) 4);
            createExcelCell(rowB0bod, 0, styleContent, String.valueOf(1));
            createExcelCell(rowB0bod, 1, styleContent, p6.getB0bod().getName());
            createExcelCell(rowB0bod, 2, styleContent, p6.getB0bod().getTitle());
            createExcelCell(rowB0bod, 3, styleContent, String.valueOf(p6.getB0bod().getValue()));
            createExcelCell(rowB0bod, 4, styleContent, p6.getB0bod().getUnit());

            HSSFRow rowB0cod = sheet6.createRow((short) 5);
            createExcelCell(rowB0cod, 0, styleContent2, String.valueOf(2));
            createExcelCell(rowB0cod, 1, styleContent2, p6.getB0cod().getName());
            createExcelCell(rowB0cod, 2, styleContent2, p6.getB0cod().getTitle());
            createExcelCell(rowB0cod, 3, styleContent2, String.valueOf(p6.getB0cod().getValue()));
            createExcelCell(rowB0cod, 4, styleContent2, p6.getB0cod().getUnit());

            HSSFRow rowp6Mcf = sheet6.createRow((short) 6);
            createExcelCell(rowp6Mcf, 0, styleContent, String.valueOf(3));
            createExcelCell(rowp6Mcf, 1, styleContent, p6.getMcf().getName());
            createExcelCell(rowp6Mcf, 2, styleContent, p6.getMcf().getTitle());
            createExcelCell(rowp6Mcf, 3, styleContent, String.valueOf(p6.getMcf().getValue()));
            createExcelCell(rowp6Mcf, 4, styleContent, p6.getMcf().getUnit());
            autoSizeColumn(sheet6, 5);
            //热泵对应参数
            HSSFSheet sheet7 = workbook.createSheet("热泵对应参数");
            workbook.setSheetName(6, "热泵对应参数");
            HSSFRow rowHeatPump = sheet7.createRow((short) 0);
            sheet7.addMergedRegion(new CellRangeAddress(0, (short) 2, 0, (short) 4));
            HSSFCell ceHeatPump = rowHeatPump.createCell((short) 0);
            ceHeatPump.setCellValue("热泵对应参数");
            ceHeatPump.setCellStyle(style);
            HSSFRow rowHeatPump2 = sheet7.createRow((short) 3); //表格第4行
            createExcelCell(rowHeatPump2, 0, styleSubstance, "序号");
            createExcelCell(rowHeatPump2, 1, styleSubstance, "NOTE");
            createExcelCell(rowHeatPump2, 2, styleSubstance, "NAME");
            createExcelCell(rowHeatPump2, 3, styleSubstance, "DATA");
            createExcelCell(rowHeatPump2, 4, styleSubstance, "UNIT");
            HSSFRow rowY = sheet7.createRow((short) 4);
            createExcelCell(rowY, 0, styleContent, String.valueOf(1));
            createExcelCell(rowY, 1, styleContent, p7.getY().getName());
            createExcelCell(rowY, 2, styleContent, p7.getY().getTitle());
            createExcelCell(rowY, 3, styleContent, String.valueOf(p7.getY().getValue()));
            createExcelCell(rowY, 4, styleContent, p7.getY().getUnit());
            autoSizeColumn(sheet7, 5);

            //输出
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    //自动对齐
    public static void autoSizeColumn(HSSFSheet sheet, int column) {
        IntStream.range(0, column).forEach(value -> sheet.autoSizeColumn((short) value));
    }

}
