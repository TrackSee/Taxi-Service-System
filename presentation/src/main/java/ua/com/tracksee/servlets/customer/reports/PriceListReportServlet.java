package ua.com.tracksee.servlets.customer.reports;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import ua.com.tracksee.logic.reports.PriceListReportBean;
import ua.com.tracksee.logic.reports.ExcelReporterBean;

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
@WebServlet("/reportComplicate")
public class PriceListReportServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    private @EJB
    ExcelReporterBean excelReporterBean;
    private @EJB
    PriceListReportBean reportBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HSSFWorkbook workbook = excelReporterBean.getExcelFile(reportBean);
            resp.setContentType("application/vnd.ms-excel");
            resp.addHeader("content-disposition", "attachment; filename="+reportBean.getReportTitle()+".xls");
            workbook.write(resp.getOutputStream());
            workbook.close();
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/customer/error.jsp").forward(req,resp);
        }
    }
}

