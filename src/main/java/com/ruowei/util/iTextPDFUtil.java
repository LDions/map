package com.ruowei.util;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Description iTextPDFUtil
 * @author 小帅丶
 * @className iTextPDFUtil
 * @Date 2019/7/18-11:26
 **/
public class iTextPDFUtil {
    /*
     * @Description 蓝色背景色标题内容行添加
     * @Author 小帅丶
     * @Date  2019/7/12 14:56
     * @param table 表格
     * @param cell  列
     * @param text  文本
     * @return void
     **/
    public static void addTableGroupTitle(PdfPTable table, PdfPCell cell, String text) {
        cell = new PdfPCell(new Phrase(text,getColorFont(BaseColor.WHITE)));
        table.addCell(addTitleCell(cell,25,new BaseColor(69,153,241),2,false));
    }
    /**
     * @Description 蓝色背景色标题内容行添加
     * @Author 小帅丶
     * @Date  2019/7/12 14:56
     * @param table 表格
     * @param cell  列
     * @param text  文本
     * @param colspan 需要合并的列
     * @return void
     **/
    public static void addTableGroupTitle(PdfPTable table, PdfPCell cell, String text,int colspan) {
        cell = new PdfPCell(new Phrase(text,getColorFont(BaseColor.WHITE)));
        table.addCell(addTitleCell(cell,25,new BaseColor(69,153,241),colspan,false));
    }
    /**
     * @Description 核查建议
     * @Author 小帅丶
     * @Date  2019/7/12 14:43
     * @param table 表格
     * @param cell 列
     * @param suggestText 核查建议内容
     * @param fontColor 核查建议内容文字颜色
     * @return com.itextpdf.text.Element
     **/
    public static void addSuggestLine(PdfPTable table,PdfPCell cell,String suggestText,BaseColor fontColor) throws Exception {
        addSuggestLine(table, cell, suggestText, fontColor, 0,10f,30f);
    }
    /**
     * @Description 核查建议
     * @Author 小帅丶
     * @Date  2019/7/12 14:43
     * @param table 表格
     * @param cell 列
     * @param suggestText 核查建议内容
     * @param fontColor 核查建议内容文字颜色
     * @param colspan 合并的列
     * @param widths 列所占宽
     * @return com.itextpdf.text.Element
     **/
    public static void addSuggestLine(PdfPTable table,PdfPCell cell,String suggestText,BaseColor fontColor,int colspan,float...widths) throws Exception {
        cell = new PdfPCell(new Phrase("核查建议：",getColorFont()));
        cell.setColspan(1);
        table.addCell(addBaseCell(cell,23,new BaseColor(238,238,238),false));
        cell = new PdfPCell(new Phrase(suggestText,getColorFont(fontColor)));
        if(colspan>0){
            cell.setColspan(colspan);
        }
        table.addCell(addBaseCell(cell,23,new BaseColor(238,238,238),false));
        table.setWidths(getColumnWiths(widths));
    }
    /**
     * @Description 信息分组table
     * @Author 小帅丶
     * @Date  2019/7/12 14:43
     * @param groupText 文本内容
     * @return com.itextpdf.text.Element
     **/
    public static Element addTableGroupLine(String groupText) {
        PdfPTable tableBaseInfoIndex = new PdfPTable(1);
        tableBaseInfoIndex.setWidthPercentage(20);
        PdfPCell cellBaseInfo = new PdfPCell(new Phrase(groupText,getColorFont()));
        cellBaseInfo.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableBaseInfoIndex.addCell(addTitleCell(cellBaseInfo,28,new BaseColor(238,238,238),2,false));
        tableBaseInfoIndex.addCell(addBlankLine(10,1));
        return tableBaseInfoIndex;
    }

    /**
     * @Description 指定颜色字体 默认处理中文显示
     * @Author 小帅丶
     * @Date  2019/7/12 14:05
     * @param color 字体颜色
     * @param fontSize 字体大小
     * @param fontFamily 字体
     * @return com.itextpdf.text.Font
     **/
    public static Font getColorFont(BaseColor color, int fontSize, String fontFamily) {
        Font font = new Font(getFont());
        font.setColor(color);
        if(fontSize>0&&(null!=fontFamily||!"".equals(fontFamily))){
            font.setSize(fontSize);
            font.setFamily(fontFamily);
        }
        return font;
    }
    /**
     * @Description 指定颜色字体 默认处理中文显示
     * @Author 小帅丶
     * @Date  2019/7/12 14:05
     * @param color 字体颜色
     * @return com.itextpdf.text.Font
     **/
    public static Font getColorFont(BaseColor color) {
        return getColorFont(color, 0, null);
    }
    /**
     * @Description  默认处理中文显示
     * @Author 小帅丶
     * @Date  2019/7/12 14:05
     * @return com.itextpdf.text.Font
     **/
    public static Font getColorFont() {
        Font font = new Font(getFont());
        return font;
    }
    /**
     * @Description 指定列宽度
     * @Author 小帅丶
     * @Date  2019/7/12 11:59
     * @param widths 一个或多个
     * @return float[]
     **/
    public static float[] getColumnWiths(float...widths){
        float[] columnWidths = new float[widths.length];
        for (int i = 0; i < widths.length; i++) {
            columnWidths[i]=widths[i];
        }
        return columnWidths;
    }
    /**
     * @Description 添加表头cell
     * @Author 小帅丶
     * @Date  2019/7/12 11:36
     * @param titleCell 要操作的cell
     * @param fixedHeight 行高度
     * @param baseColor 背景色
     * @param colspan  合并的列数
     * @param isBottomBorder 是否有下边框 true 有 fasle 没有
     * @return com.itextpdf.text.pdf.PdfPCell
     **/
    public static PdfPCell addTitleCell(PdfPCell titleCell,int fixedHeight,BaseColor baseColor,int colspan,boolean isBottomBorder){
        titleCell.setColspan(colspan);
        titleCell.setFixedHeight(fixedHeight);
        titleCell.setUseVariableBorders(true);
        titleCell.setUseAscender(true);
        titleCell.setUseDescender(true);
        titleCell.setBackgroundColor(baseColor);
        if(isBottomBorder){
            titleCell.setBorder(Rectangle.NO_BORDER);
            titleCell.setBorderColorBottom(BaseColor.LIGHT_GRAY);
        }else{
            titleCell.setBorder(Rectangle.NO_BORDER);
        }
        titleCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return titleCell;
    }

    /**
     * @Description 添加空行
     * @Author 小帅丶
     * @Date  2019/7/12 11:36
     * @param fixedHeight 空行高度
     * @param colspan  合并的列数
     * @return com.itextpdf.text.pdf.PdfPCell
     **/
    public static PdfPCell addBlankLine(int fixedHeight,int colspan){
        PdfPCell blankLine = new PdfPCell();
        blankLine.setFixedHeight(fixedHeight);
        blankLine.setBorder(Rectangle.NO_BORDER);
        blankLine.setColspan(colspan);
        return blankLine;
    }
    /**
     * @Description 添加默认cell
     * @Author 小帅丶
     * @param baseCell 要操作的cell
     * @Date  2019/7/12 11:36
     * @return com.itextpdf.text.pdf.PdfPCell
     **/
    public static PdfPCell addBaseCell(PdfPCell baseCell){
        baseCell.setFixedHeight(23);
        baseCell.setUseVariableBorders(true);
        baseCell.setUseAscender(true);
        baseCell.setUseDescender(true);
        baseCell.setBorder(Rectangle.BOTTOM);
        baseCell.setBorderColorBottom(BaseColor.LIGHT_GRAY);
        baseCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return baseCell;
    }
    /**
     * @Description 添加cell
     * @Author 小帅丶
     * @param baseCell 要操作的cell
     * @param isBottomBorder 是否有下边框 true 有 fasle 没有
     * @Date  2019/7/12 11:36
     * @return com.itextpdf.text.pdf.PdfPCell
     **/
    public static PdfPCell addBaseCell(PdfPCell baseCell,boolean isBottomBorder){
        baseCell.setFixedHeight(23);
        baseCell.setUseVariableBorders(true);
        baseCell.setUseAscender(true);
        baseCell.setUseDescender(true);
        if(isBottomBorder){
            baseCell.setBorder(Rectangle.BOTTOM);
            baseCell.setBorderColorBottom(BaseColor.LIGHT_GRAY);
        }else{
            baseCell.setBorder(Rectangle.NO_BORDER);
        }
        baseCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return baseCell;
    }
    /**
     * @Description 添加cell
     * @Author 小帅丶
     * @param baseCell 要操作的cell
     * @param fixedHeight 行高
     * @param color 背景色
     * @param isBottomBorder 是否有下边框 true 有 fasle 没有
     * @Date  2019/7/12 11:36
     * @return com.itextpdf.text.pdf.PdfPCell
     **/
    public static PdfPCell addBaseCell(PdfPCell baseCell,int fixedHeight,BaseColor color,boolean isBottomBorder){
        baseCell.setFixedHeight(fixedHeight);
        baseCell.setUseVariableBorders(true);
        baseCell.setUseAscender(true);
        baseCell.setUseDescender(true);
        if(null!=color){
            baseCell.setBackgroundColor(color);
        }
        if(isBottomBorder){
            baseCell.setBorder(Rectangle.BOTTOM);
            baseCell.setBorderColorBottom(BaseColor.LIGHT_GRAY);
        }else{
            baseCell.setBorder(Rectangle.NO_BORDER);
        }
        baseCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        return baseCell;
    }
    /**
     * @Description 设置中文支持
     * @Author 小帅丶
     * @Date  2019/7/11 10:33
     * @Param []
     * @return com.itextpdf.text.pdf.BaseFont
     **/
    public static BaseFont getFont() {
        BaseFont bf = null;
        try {
            ClassPathResource resource = new ClassPathResource("template/SimSun.ttf");
            bf = BaseFont.createFont(resource.getURL().toString(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
        }
        return bf;
    }
    /**
     * 斜角排列、全屏多个重复的花式文字水印
     *
     * @param input             需要加水印的PDF读取输入流
     * @param output            输出生成PDF的输出流
     * @param waterMarkString   水印字符
     * @param xAmout            x轴重复数量
     * @param yAmout            y轴重复数量
     * @param opacity           水印透明度
     * @param rotation          水印文字旋转角度，一般为45度角
     * @param waterMarkFontSize 水印字体大小
     * @param color             水印字体颜色
     */
    public static void stringWaterMark(InputStream input, OutputStream output, String waterMarkString, int xAmout, int yAmout, float opacity, float rotation, int waterMarkFontSize, BaseColor color) {
        try {

            PdfReader reader = new PdfReader(input);
            PdfStamper stamper = new PdfStamper(reader, output);

            // 添加中文字体
            ClassPathResource resource = new ClassPathResource("template/SimSun.ttf");
            BaseFont baseFont = BaseFont.createFont(resource.getURL().toString(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

            int total = reader.getNumberOfPages() + 1;

            PdfContentByte over;
            // 给每一页加水印
            for (int i = 1; i < total; i++) {
                Rectangle pageRect = stamper.getReader().getPageSizeWithRotation(i);
                // 计算水印每个单位步长X,Y
                float x = pageRect.getWidth() / xAmout;
                float y = pageRect.getHeight() / yAmout;

                over = stamper.getOverContent(i);
                PdfGState gs = new PdfGState();
                // 设置透明度为
                gs.setFillOpacity(opacity);

                over.setGState(gs);
                over.saveState();

                over.beginText();
                over.setColorFill(color);
                over.setFontAndSize(baseFont, waterMarkFontSize);

                for (int n = 0; n < xAmout + 1; n++) {
                    for (int m = 0; m < yAmout + 1; m++) {
                        over.showTextAligned(Element.ALIGN_CENTER, waterMarkString, x * n, y * m, rotation);
                    }
                }

                over.endText();
            }
            stamper.close();
        } catch (Exception e) {
            new Exception("NetAnd PDF add Text Watermark error"+e.getMessage());
        }
    }

    /**
     * 图片水印，整张页面平铺
     * @param input     需要加水印的PDF读取输入流
     * @param output    输出生成PDF的输出流
     * @param imageFile 水印图片路径
     */
    public static void imageWaterMark(InputStream input, OutputStream output, String imageFile, float opacity) {
        try {

            PdfReader reader = new PdfReader(input);
            PdfStamper stamper = new PdfStamper(reader, output);
            Rectangle pageRect = stamper.getReader().getPageSize(1);
            float w = pageRect.getWidth();
            float h = pageRect.getHeight();

            int total = reader.getNumberOfPages() + 1;

            Image image = Image.getInstance(imageFile);
            image.setAbsolutePosition(0, 0);// 坐标
            image.scaleAbsolute(w, h);

            PdfGState gs = new PdfGState();
            gs.setFillOpacity(opacity);// 设置透明度

            PdfContentByte over;
            // 给每一页加水印
            float x, y;
            Rectangle pagesize;
            for (int i = 1; i < total; i++) {
                pagesize = reader.getPageSizeWithRotation(i);
                x = (pagesize.getLeft() + pagesize.getRight()) / 2;
                y = (pagesize.getTop() + pagesize.getBottom()) / 2;
                over = stamper.getOverContent(i);
                over.setGState(gs);
                over.saveState();//没这个的话，图片透明度不起作用,必须在beginText之前，否则透明度不起作用，会被图片覆盖了内容而看不到文字了。
                over.beginText();
                // 添加水印图片
                over.addImage(image);
            }
            stamper.close();
        } catch (Exception e) {
            new Exception("NetAnd PDF add image Watermark error" + e.getMessage());
        }
    }
    /**
     * @description 顶部表格卡片形式显示格式数据组装
     * @Author 小帅丶
     * @Date  2019/7/16 15:31
     * @param tableMobileHeader 要操作的表格
     * @param cellMobileHeader 要操作的单元格
     * @param clospan 合并列 不需要合并填写0
     * @param fixedHeight 行高
     * @param padding 间距
     * @param border 边框
     * @param borderColor 边框颜色
     * @param backgroundColor 背景色
     * @param vertical 垂直对齐方式
     * @param horizontal  水平对齐方式
     * @return void
     **/
    public static void addTableHeaderData(PdfPTable tableMobileHeader, PdfPCell cellMobileHeader, int clospan, float fixedHeight, int padding, int border, BaseColor borderColor, BaseColor backgroundColor, int vertical, int horizontal) {
        cellMobileHeader.setUseBorderPadding(true);
        cellMobileHeader.setUseAscender(true);
        if(clospan>0){
            cellMobileHeader.setColspan(clospan);
        }
        cellMobileHeader.setUseDescender(true);
        cellMobileHeader.setFixedHeight(fixedHeight);
        cellMobileHeader.setPadding(padding);
        cellMobileHeader.setVerticalAlignment(vertical);
        cellMobileHeader.setHorizontalAlignment(horizontal);
        if(null!=backgroundColor){
            cellMobileHeader.setBackgroundColor(backgroundColor);
        }
        cellMobileHeader.setBorder(border);
        cellMobileHeader.setBorderColor(borderColor);
        tableMobileHeader.addCell(cellMobileHeader);
    }
    /**
     * @description 顶部表格卡片形式显示格式数据组装
     * @Author 小帅丶
     * @Date  2019/7/16 15:31
     * @param tableMobileHeader 要操作的表格
     * @param cellMobileHeader 要操作的单元格
     * @param clospan 合并列 不需要合并填写0
     * @param backgroundColor 背景色
     * @return void
     **/
    public static void addTableHeaderData(PdfPTable tableMobileHeader, PdfPCell cellMobileHeader, int clospan,BaseColor backgroundColor) {
        addTableHeaderData(tableMobileHeader, cellMobileHeader, clospan, 100, 10, 30, BaseColor.WHITE, backgroundColor, 0, 0);
    }
}
