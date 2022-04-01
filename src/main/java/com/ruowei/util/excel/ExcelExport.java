package com.ruowei.util.excel;


import org.apache.poi.hssf.usermodel.*;
import java.util.stream.IntStream;

/**
 * excel工具类 提供导出excel的功能
 */
public class ExcelExport {

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
