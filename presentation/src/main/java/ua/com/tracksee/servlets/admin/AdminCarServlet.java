package ua.com.tracksee.servlets.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.dao.CarDAO;
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
    @EJB
    private CarDAO carDAO;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<CarEntity> cars = carDAO.getCarsPart(1);
        req.setAttribute("cars", cars);

        req.setAttribute("pagesCount", carDAO.getCarPagesCount());
        req.getRequestDispatcher("/WEB-INF/admin/adminCarList.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageParam = req.getParameter("pageNumber");
        Integer pageNumber = null;
        //check pageNumber
        try {
            pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
            if(pageNumber > carDAO.getCarPagesCount()){
                pageNumber = 1;
                logger.warn("wrong page was request on /admin/cars");
            }
        } catch (NumberFormatException e){
            pageNumber = 1;
            logger.warn("wrong page was request on /admin/drivers");
        }
        List<CarEntity> cars = carDAO.getCarsPart(pageNumber);
        req.setAttribute("cars", cars);
        req.setAttribute("pagesCount", carDAO.getCarPagesCount());
        resp.getWriter().write(getJsonFromList(cars));
    }


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