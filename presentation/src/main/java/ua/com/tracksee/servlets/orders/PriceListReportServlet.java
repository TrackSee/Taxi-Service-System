package ua.com.tracksee.servlets.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import ua.com.tracksee.entity.PriceListReportImpl;
import ua.com.tracksee.entity.Report;
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
    Report reportBean;



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            HSSFWorkbook workbook = excelReporterBean.getExcelFile(reportBean);
            resp.setContentType("application/vnd.ms-excel");
            resp.addHeader("content-disposition", "attachment; filename=ComplicateReport.xls");
            workbook.write(resp.getOutputStream());
            workbook.close();
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            req.getRequestDispatcher("/WEB-INF/customer/error.jsp").forward(req,resp);
        }
    }
}

