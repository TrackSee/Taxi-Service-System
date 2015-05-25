package ua.com.tracksee.entity;

/**
 * Interface used for storage
 * arrays of DataObjectArrays and
 * and titles of columns. Also it
 * is store title of report.
 *
 * @author Sharaban Sasha
 */
public interface Report {
    void addDataObjectArray(DataObjectArray dataObjectArray);
    String getReportTitle();
    void setReportTitle(String reportTitle);
    DataObjectArray getDataObjectArray(int index);
    String getColumnTitle(int index);
    void addColumnTitle(String columnTitle);
    int getTitlesSize();
    int getDataSize();
}
