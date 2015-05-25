package ua.com.tracksee.entity;

import org.apache.poi.ss.usermodel.Cell;

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

    private Long getLongValue(Object object) {
        return (Long) object;
    }

    public void switcher(String type,Object object,Cell cell){
        switch(type){
            case "String":
                cell.setCellValue(getStringValue(object));
                break;
            case "Integer":
                cell.setCellValue(getIntegerValue(object));
                break;
            case "Long":
                cell.setCellValue(getLongValue(object));
                break;
        }
    }
}
