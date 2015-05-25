package ua.com.tracksee.entity;

import org.apache.poi.ss.usermodel.Cell;

import java.util.ArrayList;

/**
 * Interface used for convert
 * data and writing it to excel
 * cells in base format.
 *
 * @author Sharaban Sasha
 */
public interface ObjectConverter {
    void switcher(String type, Object object, Cell cell);
}
