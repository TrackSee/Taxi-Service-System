package ua.com.tracksee.servlets.admin;

import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.dao.UserDAO;
import ua.com.tracksee.entities.ServiceUserEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kstes_000 on 25-Apr-15.
 */
@WebServlet("/admin/updatedriver")
public class AdminUpdateDriverServlet extends HttpServlet {

    private  Integer id;
    @EJB
    private UserDAO userDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = Integer.parseInt(req.getParameter("userId"));
        req.setAttribute("driver", userDAO.getDriverByID(id));
        System.out.println("USER Id" + userDAO.getDriverByID(id).getEmail());
        req.getRequestDispatcher("/WEB-INF/admin/adminUpdateDriver.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        ServiceUserEntity user = mapper.readValue(req.getParameter("data"), ServiceUserEntity.class);
        System.out.println("ID:    " + id);
        user.setUserId(id);
        System.out.println("USERMAIL:" + user.getEmail());
        user.setDriver(true);
        userDAO.updateUser(user);

    }
}
