package ua.com.tracksee.entity;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import java.util.ArrayList;

/**
 * @author Sharaban Sasha
 * @see ua.com.tracksee.entity.ObjectConverter
 */
public class ObjectConverterImpl implements ObjectConverter {
    ArrayList<String> possibleTypes = new ArrayList<>();

    private String getStringValue(Object object) {
        return (String) object;
    }

    private Integer getIntegerValue(Object object) {
        return (Integer) object;
    }

    private Double getDoubleValue(Object object) {
        return (Double) object;
    }

    public void switcher(String type,Object object,Cell cell,CellStyle cellStyle){
        switch(type){
            case "String":
                cell.setCellValue(getStringValue(object));
                cell.setCellStyle(cellStyle);
                break;
            case "Integer":
                cell.setCellValue(getIntegerValue(object));
                cell.setCellStyle(cellStyle);
                break;
            case "Double":
                cell.setCellValue(getDoubleValue(object));
                cell.setCellStyle(cellStyle);
                break;
        }
    }
}
