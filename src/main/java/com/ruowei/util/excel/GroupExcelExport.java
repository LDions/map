package com.ruowei.util.excel;

import com.ruowei.domain.EmiData;
import com.ruowei.service.dto.ResultExportDTO;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * excel工具类 提供导出excel的功能
 */
public class GroupExcelExport {

    /**
     * 集团首页结果导出
     *
     * @param list
     * @return
     */
    public static byte[] export(List<ResultExportDTO> list) {
        try {
            /*
            创建字体
            */
            // 创建工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 标题字体
            HSSFFont fontSubstance = createFont(workbook, 20, false);
            // 二级标题字体
            HSSFFont fontSubstance2 = createFont(workbook, 15, false);
            // 内容字体
            HSSFFont fontContent = createFont(workbook, 10, false);
            // 内容上标字体
            HSSFFont fontSuperContent = createFont(workbook, 10, false);
            fontSuperContent.setTypeOffset((short) 1);
            // 内容下标字体
            HSSFFont fontSubContent = createFont(workbook, 10, false);
            fontSubContent.setTypeOffset((short) 2);
            // 表头字体
            HSSFFont fontTitle = createFont(workbook, 10, true);
            // 上标表头字体
            HSSFFont fontSuperTitle = createFont(workbook, 10, true);
            fontSuperTitle.setTypeOffset((short) 1);
            // 下标表头字体
            HSSFFont fontSubTitle = createFont(workbook, 10, true);
            fontSubTitle.setTypeOffset((short) 2);

            /*字体和样式设置*/
            // 样式
            // 标题样式
            HSSFCellStyle styleSubstance = createStyle(workbook, IndexedColors.WHITE, fontSubstance, HorizontalAlignment.LEFT);
            // 二级标题样式
            HSSFCellStyle styleSubstance2 = createStyle(workbook, IndexedColors.WHITE, fontSubstance, HorizontalAlignment.LEFT);
            // 表头样式
            HSSFCellStyle styleTitle = createStyle(workbook, IndexedColors.WHITE, fontTitle, HorizontalAlignment.CENTER);
            // 表头样式左对齐
            HSSFCellStyle styleTitleL = createStyle(workbook, IndexedColors.WHITE, fontTitle, HorizontalAlignment.LEFT);
            // 表头上标样式
            HSSFCellStyle styleSuperTitle = createStyle(workbook, IndexedColors.WHITE, fontSuperTitle, HorizontalAlignment.CENTER);
            // 表头下标样式
            HSSFCellStyle styleSubTitle = createStyle(workbook, IndexedColors.WHITE, fontSubTitle, HorizontalAlignment.CENTER);
            // 内容样式
            HSSFCellStyle styleContent = createStyle(workbook, IndexedColors.WHITE, fontContent, HorizontalAlignment.CENTER);
            // 内容样式左对齐
            HSSFCellStyle styleContentL = createStyle(workbook, IndexedColors.WHITE, fontContent, HorizontalAlignment.LEFT);
            // 上标内容样式
            HSSFCellStyle styleSuperContent = createStyle(workbook, IndexedColors.WHITE, fontSuperContent, HorizontalAlignment.CENTER);
            // 下标内容样式
            HSSFCellStyle styleSubContent = createStyle(workbook, IndexedColors.WHITE, fontSubContent, HorizontalAlignment.CENTER);

            HSSFSheet sheet = workbook.createSheet("核算结果");
            for (ResultExportDTO input : list) {
                // 标题
                int currentRow = 0;
                HSSFRow sub = sheet.createRow((short) 0);
                sheet.addMergedRegion(new CellRangeAddress(0, (short) 2, 0, (short) input.getResults().size() + 1));
                createExcelCell(sub, 0, styleSubstance, input.getEnterpriseName() + " - " + input.getCraftName());
                currentRow++;

                // 表头
                {
                    HSSFRow title = sheet.createRow((short) currentRow);
                    int col = 1;
                    // TODO 封装成 createCol 和 createRow?
                    createExcelCell(title, col, styleTitle, "指标");
                    col++;
                    for (EmiData result : input.getResults()) {
                        createExcelCell(title, col, styleTitle, "未来" + col + "小时");
                        col++;
                    }
                }

                // 出水总氮
                {
                    HSSFRow row = sheet.createRow((short) currentRow);
                    int col = 1;
                    createExcelCell(row, col, styleContent, "出水总氮");
                    col++;
                    for (EmiData result : input.getResults()) {
                        createExcelCell(row, col, styleContent, result.getTotalOutN());
                        col++;
                    }
                }
                // 出水氨氮
                {
                    HSSFRow row = sheet.createRow((short) currentRow);
                    int col = 1;
                    createExcelCell(row, col, styleContent, "出水氨氮");
                    col++;
                    for (EmiData result : input.getResults()) {
                        createExcelCell(row, col, styleContent, result.getOutAN());
                        col++;
                    }
                }

                // 碳源药剂
                {
                    HSSFRow row = sheet.createRow((short) currentRow);
                    int col = 1;
                    createExcelCell(row, col, styleContent, "碳源药剂");
                    col++;
                    for (EmiData result : input.getResults()) {
                        createExcelCell(row, col, styleContent, result.getCarbonAdd());
                        col++;
                    }
                }

                // 除磷药剂
                {
                    HSSFRow row = sheet.createRow((short) currentRow);
                    int col = 1;
                    createExcelCell(row, col, styleContent, "除磷药剂");
                    col++;
                    for (EmiData result : input.getResults()) {
                        createExcelCell(row, col, styleContent, result.getPhosphorusremover());
                        col++;
                    }
                }

                // 结尾空行

            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




    //自动对齐
    public static void autoSizeColumn(HSSFSheet sheet, int column) {
        IntStream.range(0, column).forEach(value -> sheet.autoSizeColumn((short) value));
    }

    /**
     * 创建单元格
     * 自定义单位
     * 保留小数
     * @param rows
     * @param col
     * @param style
     * @param data
     * @param unit
     * @param scale
     * @param mode
     */
    public static void createExcelCell(HSSFRow rows, int col, HSSFCellStyle style, BigDecimal data, String unit, int scale, RoundingMode mode) {
        HSSFCell cell1 = rows.createCell(col);
        cell1.setCellValue(data.setScale(scale, mode) + unit);
        cell1.setCellStyle(style);
    }

    /**
     * 创建单元格
     * 自定义单位
     * @param rows
     * @param col
     * @param style
     * @param data
     * @param unit
     */
    public static void createExcelCell(HSSFRow rows, int col, HSSFCellStyle style, BigDecimal data, String unit) {
        HSSFCell cell1 = rows.createCell(col);
        cell1.setCellValue(data.setScale(1, RoundingMode.HALF_UP) + unit);
        cell1.setCellStyle(style);
    }

    /**
     * 创建单元格
     * @param rows
     * @param col
     * @param style
     * @param data
     */
    public static void createExcelCell(HSSFRow rows, int col, HSSFCellStyle style, BigDecimal data) {
        HSSFCell cell1 = rows.createCell(col);
        cell1.setCellValue(data.setScale(1, RoundingMode.HALF_UP) + "");
        cell1.setCellStyle(style);
    }




    /**
     * 创建excel单元格
     *
     * @param rows
     * @param col
     * @param style
     * @param contents
     */
    public static void createExcelCell(HSSFRow rows, int col, HSSFCellStyle style, String contents) {
        if ("-0".equals(contents)) {
            contents = "0";
        }
        if (contents == null) {
            contents = "-";
        } else if ("null".equals(contents)) {
            contents = "-";
        }
        HSSFCell cell1 = rows.createCell(col);
        cell1.setCellValue(contents);
        cell1.setCellStyle(style);
    }

    /**
     * 创建数字带单位和角标的富文本单元格
     *
     * @param rows     行
     * @param col      列
     * @param style    单元格样式
     * @param content1 数据内容
     * @param content2 单位内容
     * @param font     上标或下标字体
     */
    public static void createExcelCell(HSSFRow rows, int col, HSSFCellStyle style, String content1, String content2, HSSFFont font) {
        HSSFRichTextString rt = new HSSFRichTextString(content1 + content2);
        // content2固定长 单位 content1不固定长
        rt.applyFont(content1.length() + 4, content1.length() + 5, font);
        HSSFCell cell1 = rows.createCell(col);
        cell1.setCellStyle(style);
        cell1.setCellValue(rt);
    }

    /**
     * 创建数字带单位和角标的富文本单元格(自定义角标位置)
     *
     * @param rows     行
     * @param col      列
     * @param style    单元格样式
     * @param content1 数据内容
     * @param content2 单位内容
     * @param font     上标或下标字体
     * @param start    角标起始位置
     * @param end      角标结束位置
     */
    public static void createExcelCell(
        HSSFRow rows,
        int col,
        HSSFCellStyle style,
        String content1,
        String content2,
        HSSFFont font,
        int start,
        int end
    ) {
        HSSFRichTextString rt = new HSSFRichTextString(content1 + content2);
        // content2固定长 单位 content1不固定长
        rt.applyFont(content1.length() + start, content1.length() + end, font);
        HSSFCell cell1 = rows.createCell(col);
        cell1.setCellStyle(style);
        cell1.setCellValue(rt);
    }

    /**
     * 创建只有单位的带角标的富文本单元格
     *
     * @param rows     行
     * @param col      列
     * @param style    单元格样式
     * @param content1 单位内容
     * @param font     上标或下标字体
     */
    public static void createExcelCell(HSSFRow rows, int col, HSSFCellStyle style, String content1, HSSFFont font) {
        HSSFRichTextString rt = new HSSFRichTextString(content1);
        // content2固定长 单位 content1不固定长
        rt.applyFont(6, 7, font);
        HSSFCell cell1 = rows.createCell(col);
        cell1.setCellStyle(style);
        cell1.setCellValue(rt);
    }

    /**
     * 创建只有单位的带角标的富文本单元格(自定义位置)
     *
     * @param rows     行
     * @param col      列
     * @param style    单元格样式
     * @param content1 单位内容
     * @param font     上标或下标字体
     * @param start    角标起始位置
     * @param end      角标结束位置
     */
    public static void createExcelCell(HSSFRow rows, int col, HSSFCellStyle style, String content1, HSSFFont font, int start, int end) {
        HSSFRichTextString rt = new HSSFRichTextString(content1);
        // content2固定长 单位 content1不固定长
        rt.applyFont(start, end, font);
        HSSFCell cell1 = rows.createCell(col);
        cell1.setCellStyle(style);
        cell1.setCellValue(rt);
    }

    /**
     * 创建样式
     *
     * @param wb                  工作簿
     * @param color               颜色
     * @param font                字体
     * @param horizontalAlignment 单元格水平对齐方式
     * @return
     */
    public static HSSFCellStyle createStyle(HSSFWorkbook wb, IndexedColors color, HSSFFont font, HorizontalAlignment horizontalAlignment) {
        //对齐方式
        HSSFCellStyle style = wb.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(horizontalAlignment);
        //字体
        style.setFont(font);
        //颜色及填充方式
        style.setFillForegroundColor(color.getIndex());
        style.setFillPattern(FillPatternType.NO_FILL);
        return style;
    }

    /**
     * 创建字体
     *
     * @param wb       工作簿
     * @param fontSize 字号
     * @param isBold   是否加粗
     * @return
     */
    public static HSSFFont createFont(HSSFWorkbook wb, int fontSize, boolean isBold) {
        HSSFFont font = wb.createFont();
        font.setColor(HSSFFont.COLOR_NORMAL);
        font.setBold(isBold);
        font.setFontHeight((short) 5);
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) fontSize);
        return font;
    }


}
