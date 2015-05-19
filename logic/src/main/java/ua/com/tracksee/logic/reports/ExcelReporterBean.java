package ua.com.tracksee.logic.reports;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import ua.com.tracksee.entity.Report;

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
        ArrayList<String> titles=report.getTitles();
        ArrayList<ArrayList<String>> dataArray=report.getData();
        String reportTitle=report.getReportTitle();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet(reportTitle);
        HSSFCellStyle cellStyle;

        cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        HSSFRow rowTitle = worksheet.createRow(0);
        for (int k = 0; k < titles.size(); k++) {
            HSSFCell cellATitle = rowTitle.createCell(k);
            cellATitle.setCellValue(titles.get(k));
            cellATitle.setCellStyle(cellStyle);
        }
        for (int i = 1; i < dataArray.size()+1; i++) {
            HSSFRow row = worksheet.createRow(i);
            ArrayList<String> data=dataArray.get(i - 1);
            for (int j = 0; j < data.size(); j++) {
                HSSFCell cellA = row.createCell(j);
                cellA.setCellValue(data.get(j));
                worksheet.autoSizeColumn(j);
            }
        }

        return workbook;
    }
}
