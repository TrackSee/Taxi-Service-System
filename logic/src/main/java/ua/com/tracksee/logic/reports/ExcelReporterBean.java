package ua.com.tracksee.logic.reports;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import ua.com.tracksee.entity.DataObjectArray;
import ua.com.tracksee.entity.ObjectConverter;
import ua.com.tracksee.entity.ObjectConverterImpl;
import ua.com.tracksee.entity.Report;
import org.apache.poi.hssf.usermodel.HSSFFont;

import javax.ejb.Stateless;
import java.util.ArrayList;

/**
* Bean provides  ability to export
* any prepared report into Excel format
*
* @author Sharaban Sasha
*/
@Stateless
public class ExcelReporterBean {
    public HSSFWorkbook  getExcelFile(Report report){
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet worksheet = workbook.createSheet(report.getReportTitle());
        HSSFCellStyle cellStyle;
        HSSFCellStyle cellStyleData;
        HSSFCellStyle cellStyleTitle;

        cellStyle = workbook.createCellStyle();
        cellStyleData = workbook.createCellStyle();
        cellStyleTitle = workbook.createCellStyle();

        cellStyleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyleTitle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        cellStyleTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        HSSFFont hSSFFont = workbook.createFont();
        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
        hSSFFont.setFontHeightInPoints((short) 12);
        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        hSSFFont.setColor(HSSFColor.BLACK.index);

        HSSFFont hSSFFont2 = workbook.createFont();
        hSSFFont2.setFontName(HSSFFont.FONT_ARIAL);
        hSSFFont2.setFontHeightInPoints((short) 14);
        hSSFFont2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        hSSFFont2.setColor(HSSFColor.BLACK.index);
        cellStyle.setFont(hSSFFont);

        cellStyleData.setFont(hSSFFont);

        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        cellStyleTitle.setFont(hSSFFont2);

        HSSFRow rowTitle = worksheet.createRow(1);
        HSSFCell cellTitle = rowTitle.createCell(2);
        cellTitle.setCellValue(report.getReportTitle());
        cellTitle.setCellStyle(cellStyleTitle);

        ObjectConverter objectConverter=new ObjectConverterImpl();
        HSSFRow rowTitles = worksheet.createRow(3);
        for (int k = 0; k < report.getTitlesSize(); k++) {
            HSSFCell cellATitle = rowTitles.createCell(k);
            cellATitle.setCellValue(report.getColumnTitle(k));
            cellATitle.setCellStyle(cellStyle);
        }
        for (int i = 4; i < report.getDataSize()+4; i++) {
            HSSFRow row = worksheet.createRow(i);
            DataObjectArray dataObjectArray=  report.getDataObjectArray(i - 4);
            for (int j = 0; j < dataObjectArray.size(); j++) {
                HSSFCell cellA = row.createCell(j);
                objectConverter.switcher(dataObjectArray.getType(j), dataObjectArray.get(j),cellA,cellStyleData);
                worksheet.autoSizeColumn(j);
            }
        }

        return workbook;
    }
}
