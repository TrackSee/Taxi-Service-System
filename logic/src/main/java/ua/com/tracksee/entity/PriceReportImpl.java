package ua.com.tracksee.entity;

import java.util.ArrayList;

/**
 * @author Sharaban Sasha
 */
public class PriceReportImpl implements Report {
    private String reportTitle;
    ArrayList<DataObjectArray> dataObjectListOfArrays =new ArrayList<DataObjectArray>();
    ArrayList<String> columnTitlesArray =new ArrayList<String>();
    @Override
    public void addDataObjectArray(DataObjectArray dataObjectArray){
        dataObjectListOfArrays.add(dataObjectArray);
    }
    @Override
    public DataObjectArray getDataObjectArray(int index){
        return dataObjectListOfArrays.get(index);
    }
    @Override
    public String getReportTitle() {
        return reportTitle;
    }
    @Override
    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }


    @Override
    public String getColumnTitle(int index) {
        return columnTitlesArray.get(index);
    }
    @Override
    public void addColumnTitle(String columnTitle) {
        columnTitlesArray.add(columnTitle);
    }

    @Override
    public int getDataSize() {
        return dataObjectListOfArrays.size();
    }

    @Override
    public int getTitlesSize() {
        return columnTitlesArray.size();
    }
}
