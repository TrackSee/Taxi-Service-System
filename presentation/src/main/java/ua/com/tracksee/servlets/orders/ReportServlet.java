package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import ua.com.tracksee.logic.reports.ExcelReporterBean;
import ua.com.tracksee.logic.reports.TestReportBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sharaban Sasha
 */
@WebServlet("/report")
public class ReportServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    private @EJB
    ExcelReporterBean excelReporterBean;
    private @EJB
    TestReportBean testReportBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageName", "order");
        req.getRequestDispatcher("/WEB-INF/report/testReportPage.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HSSFWorkbook workbook = excelReporterBean.getExcelFile(testReportBean.getTitles(), testReportBean.getData(),
                    testReportBean.getReportTitle());
            resp.setContentType("application/vnd.ms-excel");
            resp.addHeader("content-disposition", "attachment; filename=SimpleReport.xls");
            workbook.write(resp.getOutputStream());
            workbook.close();
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/customer/error.jsp").forward(req,resp);
        }
    }
}

