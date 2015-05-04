package ua.com.tracksee.servlets.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.entities.CarEntity;
import ua.com.tracksee.entities.ServiceUserEntity;
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
 * Created by kstes_000 on 02-May-15.
 */
@WebServlet("/admin/searchdriver")
public class SearchDriver  extends HttpServlet {
    private static Logger logger = LogManager.getLogger();

    @EJB
    private AdministratorBean administratorBean;
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ServiceUserEntity> drivers = administratorBean.getDrivers(1);
        req.setAttribute("drivers", drivers);
        req.setAttribute("pagesCount", administratorBean.getDriverPagesCount());
        req.getRequestDispatcher("/WEB-INF/admin/adminSearchDriver.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageParam = req.getParameter("pageNumber");
        Integer pageNumber = null;
        //check pageNumber
        try {
            pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
            if(pageNumber > administratorBean.getDriverPagesCount()){
                pageNumber = 1;
                logger.warn("wrong page was request on /admin/drivers");
            }
        } catch (NumberFormatException e){
            pageNumber = 1;
            logger.warn("wrong page was request on /admin/drivers");
        }
//        System.out.println("PageNumber" + pageNumber);
        List<ServiceUserEntity> drivers = administratorBean.getDrivers(pageNumber);
        req.setAttribute("drivers", drivers);
        req.setAttribute("pagesCount", administratorBean.getDriverPagesCount());
        resp.getWriter().write(getJsonFromList(drivers));
    }

    private String getJsonFromList(List<ServiceUserEntity> drivers){
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(drivers);
        } catch (IOException e) {
            json = "";
        }
        return json;
    }
}
