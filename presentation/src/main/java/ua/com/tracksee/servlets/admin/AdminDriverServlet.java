package ua.com.tracksee.servlets.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.entities.UserEntity;
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
 * Created by Vadym_Akymov on 21.04.15.
 */
@WebServlet("/admin/drivers")
public class AdminDriverServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();

    @EJB
    private AdministratorBean administratorBean;

    /*
     * Get admin page
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        List<UserEntity> drivers = administratorBean.getDrivers(1);
        req.setAttribute("drivers", drivers);
        req.setAttribute("pagesCount", administratorBean.getDriverPagesCount());
        req.getRequestDispatcher("/WEB-INF/admin/adminDriverList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
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
        List<UserEntity> drivers = administratorBean.getDrivers(pageNumber);
        req.setAttribute("drivers", drivers);
        req.setAttribute("pagesCount", administratorBean.getDriverPagesCount());
//        System.out.println("json: " + getJsonFromList(drivers));
        resp.getWriter().write(getJsonFromList(drivers));

    }

    /**
     *@author Vadym Akymov
     * @param drivers - list of drivers
     * @return json representation of drivers list
     */
    private String getJsonFromList(List<UserEntity> drivers){
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
