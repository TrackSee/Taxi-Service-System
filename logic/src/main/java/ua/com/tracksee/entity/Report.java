package ua.com.tracksee.entity;

/**
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
