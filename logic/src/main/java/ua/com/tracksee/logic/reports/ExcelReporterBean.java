package ua.com.tracksee.logic.reports;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

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
    public HSSFWorkbook  getExcelFile(ArrayList<String> titles,ArrayList<ArrayList<String>> dataArray){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("Report");
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
