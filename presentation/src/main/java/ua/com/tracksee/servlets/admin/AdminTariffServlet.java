package ua.com.tracksee.servlets.admin;

import ua.com.tracksee.dao.TaxiPriceDAO;
import ua.com.tracksee.entities.TaxiPriceEntity;
import ua.com.tracksee.enumartion.CarCategory;
import ua.com.tracksee.logic.admin.AdministratorBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Vitalii Diravka on 29.04.15.
 */
@WebServlet("/admin/tariff")
public class AdminTariffServlet extends HttpServlet {

    @EJB
    private AdministratorBean administratorBean;
    //TODO make all operations via logic beans
    @EJB
    private TaxiPriceDAO taxiPriceDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TaxiPriceEntity> tariff = administratorBean.getAllPrices();
        req.setAttribute("tariff", tariff);
        req.getRequestDispatcher("/WEB-INF/admin/AdminTariffList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BigDecimal newPrice = new BigDecimal(req.getParameter("newPriceVal").trim());
        CarCategory carCategory = CarCategory.valueOf(req.getParameter("carCategory").trim());
        Boolean weekend = Boolean.valueOf(req.getParameter("weekend").trim());
        Boolean nightTariff = Boolean.valueOf(req.getParameter("nightTariff").trim());
        String priceType = req.getParameter("priceType").trim();
        if(newPrice == null || "".equals(newPrice)){
            String errMsg = "Server log: invalid data type";
            resp.setContentType("text/plain");
            resp.getWriter().write(errMsg);
        }
        resp.setContentType("text/plain");
        resp.getWriter().write("Data have been accept");
        administratorBean.updateTariff(newPrice, carCategory, weekend, nightTariff, priceType);
    }
}
