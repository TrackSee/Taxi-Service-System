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

        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFFont hSSFFont = workbook.createFont();
        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
        hSSFFont.setFontHeightInPoints((short) 16);
        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        hSSFFont.setColor(HSSFColor.BLACK.index);
        cellStyle.setFont(hSSFFont);

        ObjectConverter objectConverter=new ObjectConverterImpl();
        HSSFRow rowTitle = worksheet.createRow(0);
        for (int k = 3; k < report.getTitlesSize(); k++) {
            HSSFCell cellATitle = rowTitle.createCell(k);
            cellATitle.setCellValue(report.getColumnTitle(k));
            cellATitle.setCellStyle(cellStyle);
        }
        for (int i = 4; i < report.getDataSize()+1; i++) {
            HSSFRow row = worksheet.createRow(i);
            DataObjectArray dataObjectArray=  report.getDataObjectArray(i - 1);
            for (int j = 0; j < dataObjectArray.size(); j++) {
                HSSFCell cellA = row.createCell(j);
                objectConverter.switcher(dataObjectArray.getType(j), dataObjectArray.get(j),cellA);
                worksheet.autoSizeColumn(j);
            }
        }

        return workbook;
    }
}
