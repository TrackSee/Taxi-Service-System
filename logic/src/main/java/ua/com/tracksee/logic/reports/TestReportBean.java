package ua.com.tracksee.logic.reports;

import javax.ejb.Stateless;
import java.util.ArrayList;

/**
 * Bean provides prepare data
 * for ExcelReporterBean
 *
 * @author Sharaban Sasha
 */
@Stateless
public class TestReportBean {
    private String reportTitle="simpleReport";

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }
    public ArrayList<String> getTitles(){
       ArrayList<String> titles=new ArrayList<String>();
        titles.add("title1");
        titles.add("title2");
        titles.add("title3");
        titles.add("title4");
        titles.add("title5");
        return titles;
    }
    public ArrayList<ArrayList<String>> getData(){
        ArrayList<String> data1=new ArrayList<String>();
        ArrayList<String> data2=new ArrayList<String>();
        ArrayList<String> data3=new ArrayList<String>();
        ArrayList<String> data4=new ArrayList<String>();
        ArrayList<String> data5=new ArrayList<String>();
        ArrayList<ArrayList<String>> dataArray=new ArrayList<ArrayList<String>>();
        data1.add("data11");
        data1.add("data12");
        data1.add("data13");
        data1.add("data14");
        data1.add("data15");

        data2.add("data21");
        data2.add("data22");
        data2.add("data23");
        data2.add("data24");
        data2.add("data25");

        data3.add("data31");
        data3.add("data32");
        data3.add("data33");
        data3.add("data34");
        data3.add("data35");

        data4.add("data41");
        data4.add("data42");
        data4.add("data43");
        data4.add("data44");
        data4.add("data45");

        data5.add("data41");
        data5.add("data42");
        data5.add("data43");
        data5.add("data44");
        data5.add("data45");

        dataArray.add(data1);
        dataArray.add(data2);
        dataArray.add(data3);
        dataArray.add(data4);
        dataArray.add(data5);

        return dataArray;
    }
}
