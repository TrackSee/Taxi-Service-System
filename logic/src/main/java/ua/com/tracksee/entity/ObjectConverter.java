package ua.com.tracksee.entity;

import org.apache.poi.ss.usermodel.Cell;

import java.util.ArrayList;

/**
 * @author Sharaban Sasha
 */
public interface ObjectConverter {
    void switcher(String type, Object object, Cell cell);
}
