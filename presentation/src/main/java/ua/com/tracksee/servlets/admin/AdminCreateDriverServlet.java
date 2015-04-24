package ua.com.tracksee.servlets.admin;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.logic.admin.AdministratorBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;

/**
 * Created by kstes_000 on 24-Apr-15.
 */
@WebServlet ("/admin/createdriver")
public class AdminCreateDriverServlet  extends HttpServlet {
    @EJB
    private AdministratorBean administratorBean;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/admin/adminCreateDriver.jsp").forward(req, resp);
//        ServiceUserEntity user = new ServiceUserEntity();
//        user.setPassword("123221321321");
//        user.setPhone("115212");
//        user.setEmail("maata@mail.ru");
      //  administratorBean.createUser(user);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("OOOOUUUT: " + req.getParameter("myVari"));

//        ObjectMapper mapper = new ObjectMapper();
//        ServiceUserEntity user = mapper.readValue(req.getParameter("data"), ServiceUserEntity.class);

       // administratorBean.createUser(user);

    }
}
