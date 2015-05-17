package ua.com.tracksee.servlets.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.entities.CarEntity;
import ua.com.tracksee.logic.admin.AdministratorBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Katia Stetsiuk
 */
@WebServlet("/admin/cars")
public class AdminCarServlet extends HttpServlet {

    private static Logger logger = LogManager.getLogger();
    @EJB
    private AdministratorBean administratorBean;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CarEntity> cars = administratorBean.getCarsPart(1);
        req.setAttribute("cars", cars);
        req.setAttribute("pagesCount", administratorBean.getCarPagesCount());
        req.getRequestDispatcher("/WEB-INF/admin/adminCarList.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pageNumber = null;
        try {
            pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
            if(pageNumber > administratorBean.getCarPagesCount()){
                pageNumber = 1;
                logger.warn("wrong page was request on /admin/cars");
            }
        } catch (NumberFormatException e){
            pageNumber = 1;
            logger.warn("wrong page was request on /admin/drivers");
        }
        List<CarEntity> cars = administratorBean.getCarsPart(pageNumber);
        req.setAttribute("cars", cars);
        req.setAttribute("pagesCount", administratorBean.getCarPagesCount());
        resp.getWriter().write(getJsonFromList(cars));
    }

    /**
     *
     * @param cars list of cars to convert into JSON
     * @return String of JSON
     */
    private String getJsonFromList(List<CarEntity> cars){
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(cars);
        } catch (IOException e) {
            json = "";
        }
        return json;
    }
}