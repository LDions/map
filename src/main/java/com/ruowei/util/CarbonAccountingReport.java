package com.ruowei.util;

import java.io.*;
import java.math.BigDecimal;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.ruowei.config.ApplicationProperties;
import com.ruowei.service.dto.SewEmiDTO;
import com.ruowei.web.rest.vm.SewEmiAccountVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.apache.pdfbox.util.PDFMergerUtility;

@Slf4j
@Component
public class CarbonAccountingReport {
    private static ApplicationProperties applicationProperties;

    public CarbonAccountingReport(ApplicationProperties applicationProperties) {
        CarbonAccountingReport.applicationProperties = applicationProperties;
    }

    public static void MergePdf(SewEmiDTO sewEmiDTO) throws Exception {
        initPDF(sewEmiDTO);
        fill(sewEmiDTO);
        //pdf合并工具类
        PDFMergerUtility mergePdf = new PDFMergerUtility();
        //合并pdf生成的文件名
        String destinationFileName = sewEmiDTO.getDocumentCode() + ".pdf";
        //这是需要合并的PDF文件
        String filePath = applicationProperties.getReportPath() + sewEmiDTO.getAccYear() + "/" + sewEmiDTO.getAccMonth() + "/上.pdf";
        // 合并后pdf存放路径
        String bothPath = applicationProperties.getReportPath() + sewEmiDTO.getAccYear() + "/" + sewEmiDTO.getAccMonth() + "/";
        File file3 = new File(bothPath);
        if (!file3.exists()) {
            file3.mkdirs();
        }
        mergePdf.addSource(filePath);
        mergePdf.addSource(applicationProperties.getReportPath() + sewEmiDTO.getAccYear() + "/" + sewEmiDTO.getAccMonth() + "/下.pdf");
        //设置合并生成pdf文件名称
        mergePdf.setDestinationFileName(bothPath + destinationFileName);
        //合并pdf
        mergePdf.mergeDocuments();
        deleteFile(sewEmiDTO);
    }

    /**
     * 初始化PDF
     *
     * @param
     */
    public static void initPDF(SewEmiDTO sewEmiDTO) throws Exception {
        // 生成PDF后的存放路径
        String path = applicationProperties.getReportPath() + sewEmiDTO.getAccYear() + "/" + sewEmiDTO.getAccMonth() + "/";
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs(); // 创建目录
        }
        Document doc = null;
        // 中文字体，要有itext-asian.jar支持(默认的itext.jar是不支持中文的)
        ClassPathResource resource = new ClassPathResource("template/SimSun.ttf");
        BaseFont bfchinese = BaseFont.createFont(resource.getURL().toString(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 页面大小设置为A4
        Rectangle pageSize = new Rectangle(PageSize.A4);
        // 创建doc对象并设置边距
        doc = new Document(pageSize, 10, 20, 40, 40);
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(folder.getAbsolutePath() + File.separator + "上.pdf"));
        writer.setPageEvent(new SdkPdfPageEvent());
        doc.open();
        doc.addSubject("污水处理厂碳排放核算数据");
        BaseColor borderColor = new BaseColor(90, 140, 200);
        BaseColor bgColor = new BaseColor(80, 130, 180);
        //标题图片
        PdfPTable table3 = new PdfPTable(1);
        table3.setWidthPercentage(100);
        // 设置表格的宽度
        table3.setTotalWidth(500);
        // 锁住宽度
        table3.setLockedWidth(true);
        // 设置表格上面空白宽度
        table3.setSpacingBefore(10f);
        // 设置表格下面空白宽度
        table3.setSpacingAfter(10f);
        // 设置表格默认为无边框
        table3.getDefaultCell().setBorder(0);
        PdfPCell cell = new PdfPCell();
        // 设置CellEvent
        Paragraph paragraph1 = new Paragraph(sewEmiDTO.getEnterpriseName() + "企业污水处理核算报告 " + sewEmiDTO.getAccYear() + "." + sewEmiDTO.getAccMonth(), iTextPDFUtil.getColorFont(new BaseColor(51, 51, 51), 18, "宋体"));
        paragraph1.setAlignment(1);
        cell.setPhrase(paragraph1);
        doc.add(paragraph1);
        // 按比例设置cell高度
        cell.setBorder(Rectangle.NO_BORDER);
        table3.addCell(iTextPDFUtil.addBlankLine(8, 1));
        doc.add(table3);
        //基础信息表
        PdfPTable table2 = new PdfPTable(new float[]{0.25f, 0.25f, 0.25f, 0.25f, 0.25f, 0.25f});
        doc.add(new Paragraph("          基础信息", iTextPDFUtil.getColorFont(new BaseColor(51, 51, 51), 12, "宋体")));
        table2.addCell(iTextPDFUtil.addBlankLine(8, 6));
        table2.getDefaultCell().setBorder(0);
        table2.addCell(createCell1("*企业名称：", 8, bfchinese, 1, null, borderColor));
        table2.addCell(createCell1(sewEmiDTO.getEnterpriseName(), 8, bfchinese, 1, null, borderColor));
        table2.addCell(createCell1("*省份/直辖市：", 8, bfchinese, 1, null, borderColor));
        table2.addCell(createCell1(sewEmiDTO.getProvinceName(), 8, bfchinese, 1, null, borderColor));
        table2.addCell(createCell1("*核算时间", 8, bfchinese, 1, null, borderColor));
        table2.addCell(createCell1(sewEmiDTO.getAccYear() + "年" + sewEmiDTO.getAccMonth() + "月", 8, bfchinese, 1, null, borderColor));
        table2.getDefaultCell().setBorder(0);
        table2.addCell(cell);
        doc.add(table2);
        //水质信息表
        //进水信息表
        PdfPTable table4 = new PdfPTable(new float[]{0.25f, 0.25f, 0.25f, 0.25f, 0.25f, 0.25f, 0.25f, 0.35f, 0.25f, 0.25f, 0.25f, 0.25f, 0.35f});
        doc.add(new Paragraph("          水质信息", iTextPDFUtil.getColorFont(new BaseColor(51, 51, 51), 12, "宋体")));
        table4.addCell(iTextPDFUtil.addBlankLine(8, 13));
        table4.addCell(createCell("工艺类型", 6, bfchinese, 1, null, borderColor));
        table4.addCell(createCell("日均规模(m3/d)", 6, bfchinese, 1, null, borderColor));
        table4.addCell(createCell("本月运行天数", 6, bfchinese, 1, null, borderColor));
        table4.addCell(createCell("进水总氮(mg/L)", 6, bfchinese, 1, null, borderColor));
        table4.addCell(createCell("进水COD(mg/L)", 6, bfchinese, 1, null, borderColor));
        table4.addCell(createCell("进水氨氮(mg/L)", 6, bfchinese, 1, null, borderColor));
        table4.addCell(createCell("进水总磷(mg/L)", 6, bfchinese, 1, null, borderColor));
        table4.addCell(createCell("进水BOD(mg/L)选填", 6, bfchinese, 1, null, borderColor));
        table4.addCell(createCell("出水总氮(mg/L)", 6, bfchinese, 1, null, borderColor));
        table4.addCell(createCell("出水COD(mg/L)", 6, bfchinese, 1, null, borderColor));
        table4.addCell(createCell("出水氨氮(mg/L)", 6, bfchinese, 1, null, borderColor));
        table4.addCell(createCell("出水总磷(mg/L)", 6, bfchinese, 1, null, borderColor));
        table4.addCell(createCell("出水BOD(mg/L)选填", 6, bfchinese, 1, null, borderColor));
        table4.addCell(cell);
        doc.add(table4);
        for (SewEmiAccountVM.SewProcessVM sewProcessVM : sewEmiDTO.getSewProcesss()) {
            PdfPTable table = new PdfPTable(new float[]{0.25f, 0.25f, 0.25f, 0.25f, 0.25f, 0.25f, 0.25f, 0.35f, 0.25f, 0.25f, 0.25f, 0.25f, 0.35f});
            table.setSplitLate(false);
            table.setSplitRows(true);
            table.addCell(createCell(sewProcessVM.getProcessTypeName(), 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(String.valueOf(sewProcessVM.getDailyScale()), 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(String.valueOf(sewProcessVM.getOperatingDays()), 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(String.valueOf(sewProcessVM.getInNitrogen()), 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(String.valueOf(sewProcessVM.getInCod()), 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(String.valueOf(sewProcessVM.getInAmmonia()), 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(String.valueOf(sewProcessVM.getInPhosphorus()), 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(getValue(sewProcessVM.getInBod()), 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(String.valueOf(sewProcessVM.getOutNitrogen()), 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(String.valueOf(sewProcessVM.getOutCod()), 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(String.valueOf(sewProcessVM.getOutAmmonia()), 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(String.valueOf(sewProcessVM.getOutPhosphorus()), 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(getValue(sewProcessVM.getOutBod()), 6, bfchinese, 1, null, borderColor));
            table.addCell(cell);
            doc.add(table);
        }
        //电量信息表
        PdfPTable table6 = new PdfPTable(new float[]{0.25f, 0.25f, 0.25f, 0.25f});
        doc.add(new Paragraph("          电量信息", iTextPDFUtil.getColorFont(new BaseColor(51, 51, 51), 12, "宋体")));
        table6.getDefaultCell().setBorder(0);
        table6.addCell(iTextPDFUtil.addBlankLine(8, 4));
        table6.addCell(createCell1("*月度总耗电量：", 8, bfchinese, 1, null, borderColor));
        table6.addCell(createCell1(sewEmiDTO.getTotalPow() + " kWh/mth", 8, bfchinese, 1, null, borderColor));
        table6.addCell(createCell1("", 6, bfchinese, 1, null, borderColor));
        table6.addCell(createCell1("", 6, bfchinese, 1, null, borderColor));
        table6.getDefaultCell().setBorder(0);
        table6.addCell(cell);
        doc.add(table6);
        //药剂月投加量表
        PdfPTable table7 = new PdfPTable(new float[]{0.25f, 0.25f, 0.25f, 0.25f, 0.25f, 0.25f, 0.25f});
        doc.add(new Paragraph("          药剂月投加量", iTextPDFUtil.getColorFont(new BaseColor(51, 51, 51), 12, "宋体")));
        table7.addCell(iTextPDFUtil.addBlankLine(8, 7));
        table7.addCell(createCell("药剂名称", 6, bfchinese, 1, null, borderColor));
        table7.addCell(createCell("药剂月投加量", 6, bfchinese, 1, null, borderColor));
        table7.addCell(createCell("一级投加量", 6, bfchinese, 1, null, borderColor));
        table7.addCell(createCell("二级投加量", 6, bfchinese, 1, null, borderColor));
        table7.addCell(createCell("三级投加量", 6, bfchinese, 1, null, borderColor));
        table7.addCell(createCell("污泥处理月投加量", 6, bfchinese, 1, null, borderColor));
        table7.addCell(createCell("污泥处置月投加量", 6, bfchinese, 1, null, borderColor));
        table7.addCell(cell);
        doc.add(table7);
        for (SewEmiAccountVM.SewPotVM sewSluDetailDTO : sewEmiDTO.getSewPots()) {
            PdfPTable table = new PdfPTable(new float[]{0.25f, 0.25f, 0.25f, 0.25f, 0.25f, 0.25f, 0.25f});
            table.setSplitLate(false);
            table.setSplitRows(true);
            // table.setWidthPercentage(100); // 设置table宽度为100%
            // table.setHorizontalAlignment(PdfPTable.ALIGN_CENTER); // 设置table居中显示
//                  for (int i = 0; i < sewPotDetailDTO.getParams2().size(); i++) {
            table.addCell(createCell(sewSluDetailDTO.getPotionName(), 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(getValue(sewSluDetailDTO.getTotalPot()) + " kg/mth", 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(getValue(sewSluDetailDTO.getLevel1Pot()) + " kg/mth", 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(getValue(sewSluDetailDTO.getLevel2Pot()) + " kg/mth", 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(getValue(sewSluDetailDTO.getLevel3Pot()) + " kg/mth", 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(getValue(sewSluDetailDTO.getSluTreatPot()) + " kg/mth", 6, bfchinese, 1, null, borderColor));
            table.addCell(createCell(getValue(sewSluDetailDTO.getSluHandlePot()) + " kg/mth", 6, bfchinese, 1, null, borderColor));
            doc.add(table);
        }
        if (sewEmiDTO.getSolarPow() != null || sewEmiDTO.getHeatPumpHeat() != null || sewEmiDTO.getThermoElec() != null
            || sewEmiDTO.getWindPow() != null || sewEmiDTO.getEcoComplexReduction() != null || sewEmiDTO.getOtherEmiReduction() != null
            || sewEmiDTO.getToGirdPow() != null) {
            //碳减排信息
            PdfPTable table8 = new PdfPTable(new float[]{0.25f, 0.25f, 0.25f, 0.25f});
            doc.add(new Paragraph("          碳减排信息", iTextPDFUtil.getColorFont(new BaseColor(51, 51, 51), 12, "宋体")));
            table8.getDefaultCell().setBorder(0);
            if (sewEmiDTO.getSolarPow() != null) {
                table8.addCell(iTextPDFUtil.addBlankLine(8, 4));
                table8.addCell(createCell1("太阳能发电", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell("太阳能月发电量：", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell(sewEmiDTO.getSolarPow() + " kWh/mth", 8, bfchinese, 2, null, borderColor));
            }
            if (sewEmiDTO.getHeatPumpHeat() != null) {
                table8.addCell(iTextPDFUtil.addBlankLine(8, 4));
                table8.addCell(createCell1("热泵", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell("热泵供热量：", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell(sewEmiDTO.getHeatPumpHeat() + " kJ/h", 8, bfchinese, 2, null, borderColor));
                table8.addCell(createCell1("", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell("热泵供热运行时间：", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell(sewEmiDTO.getHeatPumpHotHours() + " h", 8, bfchinese, 2, null, borderColor));
                table8.addCell(createCell1("", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell("热泵制冷量：", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell(sewEmiDTO.getHeatPumpRefr() + " kJ/h", 8, bfchinese, 2, null, borderColor));
                table8.addCell(createCell1("", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell("热泵制冷运行时间：", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell(sewEmiDTO.getHeatPumpColdHours() + " h", 8, bfchinese, 2, null, borderColor));
            }
            if (sewEmiDTO.getThermoElec() != null) {
                table8.addCell(iTextPDFUtil.addBlankLine(8, 4));
                table8.addCell(createCell1("热电联产", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell("月产电量：", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell(sewEmiDTO.getThermoElec() + " kWh/mth", 8, bfchinese, 2, null, borderColor));
                table8.addCell(createCell1("", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell("月产热能：", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell(sewEmiDTO.getThermoEner() + " kJ/mth", 8, bfchinese, 2, null, borderColor));
            }
            if (sewEmiDTO.getWindPow() != null) {
                table8.addCell(iTextPDFUtil.addBlankLine(8, 4));
                table8.addCell(createCell1("风能发电", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell("风能月发电量：", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell(sewEmiDTO.getWindPow() + " kWh/mth", 8, bfchinese, 2, null, borderColor));
            }
            if (sewEmiDTO.getEcoComplexReduction() != null) {
                table8.addCell(iTextPDFUtil.addBlankLine(8, 4));
                table8.addCell(createCell1("生态综合体", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell("生态综合体碳减排量：", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell(sewEmiDTO.getEcoComplexReduction() + " kg CO2 eq/mth", 8, bfchinese, 2, null, borderColor));
            }
            if (sewEmiDTO.getOtherEmiReduction() != null) {
                table8.addCell(iTextPDFUtil.addBlankLine(8, 4));
                table8.addCell(createCell1("其他碳减排项目：", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell1(sewEmiDTO.getOtherText(), 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell1("其他碳减排项目减排量：", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell1(sewEmiDTO.getOtherEmiReduction() + " kg CO2 eq/mth", 8, bfchinese, 1, null, borderColor));
            }
            if (sewEmiDTO.getToGirdPow() != null) {
                table8.addCell(iTextPDFUtil.addBlankLine(8, 4));
                table8.addCell(createCell1("碳减排项目发电输送到电网的电量：", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell1(sewEmiDTO.getToGirdPow() + " kWh", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell1("", 8, bfchinese, 1, null, borderColor));
                table8.addCell(createCell1("", 8, bfchinese, 1, null, borderColor));
            }
            table8.addCell(cell);
            doc.add(table8);
        }
        PdfPTable table14 = new PdfPTable(new float[]{0.5f, 0.25f, 0.5f, 0.25f});
        doc.add(new Paragraph("         污泥处置信息", iTextPDFUtil.getColorFont(new BaseColor(51, 51, 51), 12, "宋体")));
        table14.addCell(iTextPDFUtil.addBlankLine(8, 13));
        table14.addCell(createCell1("*污泥处理后含水率(%):", 8, bfchinese, 1, null, borderColor));
        table14.addCell(createCell1(getValue(sewEmiDTO.getSluMoistureAfterTreat()), 8, bfchinese, 1, null, borderColor));
        table14.addCell(createCell1("*污泥处置是否为本厂管理？：", 8, bfchinese, 1, null, borderColor));
        table14.addCell(createCell1(sewEmiDTO.getManagedBySelf() ? "是" : "否", 8, bfchinese, 1, null, borderColor));
        table14.addCell(cell);
        doc.add(table14);
        int n = 0;
        PdfPTable table15 = new PdfPTable(new float[]{0.25f, 0.25f, 0.25f, 0.25f});
        for (SewEmiAccountVM.SewSluVM sewSluDetailDTO : sewEmiDTO.getSewSlus()) {
            table15.setSplitLate(false);
            table15.setSplitRows(true);
            // table.setWidthPercentage(100); // 设置table宽度为100%
            // table.setHorizontalAlignment(PdfPTable.ALIGN_CENTER); // 设置table居中显示
//                    for (int i = 0; i < sewSluDetailDTO.getParams().size(); i++) {
            if (n == 0) {
                table15.addCell(createCell1("污泥处置方法", 8, bfchinese, 1, null, borderColor));
            } else {
                table15.addCell(createCell1("", 8, bfchinese, 1, null, borderColor));
            }
            table15.addCell(createCell(sewSluDetailDTO.getMethodName(), 8, bfchinese, 1, null, borderColor));
            table15.addCell(createCell("*污泥处置量:" + getValue(sewSluDetailDTO.getSluCapacity()) + " kg/mth", 8, bfchinese, 1, null, borderColor));
            table15.addCell(createCell("*污泥处理前含水率(%):" + getValue(sewSluDetailDTO.getSluMoisture()), 8, bfchinese, 1, null, borderColor));
            n++;
        }
        table15.addCell(cell);
        doc.add(table15);
        doc.close();
        log.info("init pdf over.");
    }

    private static String getValue1(BigDecimal decimal) {
        //单位换算 吨 取整
        decimal=decimal.divide(new BigDecimal(1000),0, RoundingMode.HALF_UP);
        return decimal != null ? String.valueOf(decimal) : "";
    }
    private static String getValue(BigDecimal decimal) {
        return decimal != null ? String.valueOf(decimal) : "";
    }
    /**
     * @param text        Cell文字内容
     * @param fontsize    字体大小
     * @param font        字体
     * @param colspan     合并列数量
     * @param align       显示位置(左中右，Paragraph对象)
     * @param borderColor Cell边框颜色
     * @return
     */
    // 生成cell
    private static PdfPCell createCell1(String text, int fontsize, BaseFont font, Integer colspan, Integer align, BaseColor borderColor) throws Exception {
        return createCell1(text, fontsize, font, colspan, align, borderColor, null);
    }

    private static PdfPCell createCell2(String text, int fontsize, BaseFont font, Integer colspan, Integer align, BaseColor borderColor) {
        return createCell2(text, fontsize, font, colspan, align, borderColor, null);
    }

    // 生成cell
    private static PdfPCell createCell(String text, int fontsize, BaseFont font, Integer colspan, Integer align, BaseColor borderColor) {
        return createCell(text, fontsize, font, colspan, align, borderColor, null);
    }

    private static byte[] File2byte(InputStream fis) {
        byte[] buffer = null;
        try {
            com.itextpdf.io.source.ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (com.itextpdf.io.IOException | IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 用於生成cell
     *
     * @param text        Cell文字内容
     * @param fontsize    字体大小
     * @param font        字体
     * @param colspan     合并列数量
     * @param align       显示位置(左中右，Paragraph对象)
     * @param borderColor Cell边框颜色
     * @param bgColor     Cell背景色
     * @return
     */
    private static PdfPCell createCell(String text, int fontsize, BaseFont font, Integer colspan, Integer align, BaseColor borderColor, BaseColor bgColor) {
        Paragraph pagragraph = new Paragraph(text, new Font(font, fontsize));
        PdfPCell cell = new PdfPCell(pagragraph);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(20);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        // 上中下，Element对象
        if (align != null) {
            cell.setHorizontalAlignment(align);
        }
        if (colspan != null && colspan > 1) {
            cell.setColspan(colspan);
        }
        if (borderColor != null) {
            cell.setBorderColor(borderColor);
        }
        if (bgColor != null) {
            cell.setBackgroundColor(new BaseColor(242, 251, 255));
        }
        return cell;
    }

    private static PdfPCell createCell1(String text, int fontsize, BaseFont font, Integer colspan, Integer align, BaseColor borderColor, BaseColor bgColor) {
        Paragraph pagragraph = new Paragraph(text, new Font(font, fontsize));
        PdfPCell cell = new PdfPCell(pagragraph);
        cell.setFixedHeight(20);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        // 上中下，Element对象
        if (align != null) {
            cell.setHorizontalAlignment(align);
        }
        if (colspan != null && colspan > 1) {
            cell.setColspan(colspan);
        }
        if (borderColor != null) {
            cell.setBorderColor(borderColor);
        }
        if (bgColor != null) {
            cell.setBackgroundColor(new BaseColor(253, 253, 229));
        }
        return cell;
    }

    private static PdfPCell createCell2(String text, int fontsize, BaseFont font, Integer colspan, Integer align, BaseColor borderColor, BaseColor bgColor) {
        Paragraph pagragraph = new Paragraph(text, new Font(font, fontsize));
        PdfPCell cell = new PdfPCell(pagragraph);
        cell.setFixedHeight(20);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        // 上中下，Element对象
        if (align != null) {
            cell.setHorizontalAlignment(align);
        }
        if (colspan != null && colspan > 1) {
            cell.setColspan(colspan);
        }
        if (borderColor != null) {
            cell.setBorderColor(borderColor);
        }
        if (bgColor != null) {
            cell.setBackgroundColor(new BaseColor(253, 253, 229));
        }
        return cell;
    }

    /**
     * SDK中PDF相关的PageEvent
     */
    static class SdkPdfPageEvent extends PdfPageEventHelper {
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            // 水印(water mark)
            PdfContentByte pcb = writer.getDirectContent();
            pcb.saveState();
            BaseFont bf;
            try {
                ClassPathResource resource = new ClassPathResource("template/SimSun.ttf");
                bf = BaseFont.createFont(resource.getURL().toString(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
                pcb.setFontAndSize(bf, 36);
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
            // 透明度设置
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.2f);
            pcb.setGState(gs);
            pcb.beginText();
            pcb.setTextMatrix(60, 90);
            //           pcb.showTextAligned(Element.ALIGN_LEFT, "XX公司有限公司", 200, 300, 45);
            pcb.endText();
            pcb.restoreState();
        }
    }

    /**
     * 初始化PDF
     *
     * @param
     */
    public static final String number = "下";
    public static final String newPDFPathname = number + ".pdf";

    public static void creatFile(String filePath, String fileName) {
        File folder = new File(filePath);
        //文件夹路径不存在
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
        }
        // 如果文件不存在就创建
        File file = new File(filePath + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void fill(SewEmiDTO sewEmiDTO) throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("template/核算结果报告模板.pdf");
        byte[] by = File2byte(classPathResource.getInputStream());
        String newPDFPathp = applicationProperties.getReportPath() + sewEmiDTO.getAccYear() + "/" + sewEmiDTO.getAccMonth() + "/";
        creatFile(newPDFPathp, newPDFPathname);
        String newPDFPath = newPDFPathp + newPDFPathname;
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        // 页面大小设置为A4
        Rectangle pageSize = new Rectangle(PageSize.A4);
        // 创建doc对象并设置边距
        out = new FileOutputStream(newPDFPath);
        // 输出流
        reader = new PdfReader(by);
        // 读取pdf模板
        bos = new ByteArrayOutputStream();
        stamper = new PdfStamper(reader, bos);
        AcroFields form = stamper.getAcroFields();
        String solar = "-" + getValue1(sewEmiDTO.getSolarPowRed());
        String HeatPumpRed = "-" + getValue1(sewEmiDTO.getHeatPumpRed());
        String ThermoEnerRed = "-" + getValue1(sewEmiDTO.getThermoEnerRed());
        String ThermoElecRed = "-" + getValue1(sewEmiDTO.getThermoElecRed());
        String OtherEmiRed = "-" + getValue1(sewEmiDTO.getOtherEmiRed());
        String ManagedBySelf = sewEmiDTO.getManagedBySelf() ? "(本厂管理)" : "(非本厂管理)";
        //新增 风能 生态
        String WindPowRed = "-" + getValue1(sewEmiDTO.getWindPowRed());
        String EcoComplexRed = "-" + getValue1(sewEmiDTO.getEcoComplexRed());
        if (sewEmiDTO.getSolarPowRed().compareTo(BigDecimal.valueOf(0)) == 0) {
            solar = getValue1(sewEmiDTO.getSolarPowRed());
        }
        if (sewEmiDTO.getHeatPumpRed().compareTo(BigDecimal.valueOf(0)) == 0) {
            HeatPumpRed = getValue1(sewEmiDTO.getHeatPumpRed());
        }
        if (sewEmiDTO.getThermoEnerRed().compareTo(BigDecimal.valueOf(0)) == 0) {
            ThermoEnerRed = getValue1(sewEmiDTO.getThermoEnerRed());
        }
        if (sewEmiDTO.getThermoElecRed().compareTo(BigDecimal.valueOf(0)) == 0) {
            ThermoElecRed = getValue1(sewEmiDTO.getThermoElecRed());
        }
        //新增 风能 生态
        if (sewEmiDTO.getWindPowRed().compareTo(BigDecimal.valueOf(0)) == 0) {
            WindPowRed = getValue1(sewEmiDTO.getWindPowRed());
        }
        if (sewEmiDTO.getEcoComplexRed().compareTo(BigDecimal.valueOf(0)) == 0) {
            EcoComplexRed = getValue1(sewEmiDTO.getEcoComplexRed());
        }
        if (sewEmiDTO.getOtherEmiRed().compareTo(BigDecimal.valueOf(0)) == 0) {
            OtherEmiRed = getValue1(sewEmiDTO.getOtherEmiRed());
        }

        String[] str = {
            //药剂间接碳排
            getValue1(sewEmiDTO.getLevel1PotEmi()),
            getValue1(sewEmiDTO.getLevel2PotEmi()),
            getValue1(sewEmiDTO.getLevel3PotEmi()),
            getValue1(sewEmiDTO.getTotalPotEmi()),
            //耗电间接碳排
            getValue1(sewEmiDTO.getInletPumpPowEmi()),
            getValue1(sewEmiDTO.getBlowerPowEmi()),
            getValue1(sewEmiDTO.getRetSluPumpPowEmi()),
            getValue1(sewEmiDTO.getFacilityPowEmi()),
            getValue1(sewEmiDTO.getDisinfectPowEmi()),
            getValue1(sewEmiDTO.getOtherPowEmi()),
            getValue1(sewEmiDTO.getTotalPowEmi()),
            //污水处理直接碳排
            getValue1(sewEmiDTO.getSewTreatCh4Emi()),
            getValue1(sewEmiDTO.getSewTreatN2oEmi()),
            getValue1(sewEmiDTO.getTotalSewTreatEmi()),
            //污泥处理间接碳排
            getValue1(sewEmiDTO.getSluTreatPotEmi()),
            getValue1(sewEmiDTO.getSluTreatPowEmi()),
            //污泥处置碳排
            getValue1(sewEmiDTO.getSluHandleCh4Emi()),
            getValue1(sewEmiDTO.getSluHandleN2oEmi()),
            getValue1(sewEmiDTO.getTotalSluHandleDirEmi()),
            getValue1(sewEmiDTO.getSluHandlePotEmi()),
            getValue1(sewEmiDTO.getSluHandlePowEmi()),
            getValue1(sewEmiDTO.getTotalSluHandleIndirEmi()),
            //碳减排
            solar,
            HeatPumpRed,
            ThermoEnerRed,
            ThermoElecRed,
            WindPowRed,
            EcoComplexRed,
            OtherEmiRed,
            //本月合计
            getValue1(sewEmiDTO.getCarbonEmi()),
            getValue1(sewEmiDTO.getCarbonRed()),
            getValue1(sewEmiDTO.getCarbonDirEmi()),
            getValue1(sewEmiDTO.getCarbonIndirEmi()),
            //本厂处理
            ManagedBySelf
        };
        int i = 0;
        java.util.Iterator<String> it = form.getFields().keySet().iterator();
        while (it.hasNext()) {
            String name = it.next().toString();
            form.setField(name, str[i++]);
        }
        stamper.setFormFlattening(true);
        // 如果为false那么生成的PDF文件还能编辑，一定要设为true
        stamper.close();
        Document doc = new Document(pageSize, 20, 20, 40, 40);
        PdfCopy copy = new PdfCopy(doc, out);
        doc.open();
        PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
        copy.addPage(importPage);
        doc.close();
    }

    /**
     * 删除单个文件
     *
     * @param sewEmiDTO 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(SewEmiDTO sewEmiDTO) {
        boolean flag = false;
        File file = new File(applicationProperties.getReportPath() + sewEmiDTO.getAccYear() + "/" + sewEmiDTO.getAccMonth() + "/上.pdf");
        File file1 = new File(applicationProperties.getReportPath() + sewEmiDTO.getAccYear() + "/" + sewEmiDTO.getAccMonth() + "/下.pdf");
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists() || file1.isFile() && file1.exists()) {
            file.delete();
            file1.delete();
            flag = true;
        }
        return flag;
    }
}
