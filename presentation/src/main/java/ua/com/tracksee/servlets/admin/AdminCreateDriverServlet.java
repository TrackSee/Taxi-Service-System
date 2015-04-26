package ua.com.tracksee.servlets.admin;

        import org.codehaus.jackson.JsonGenerator;
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
        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.URLDecoder;
        import java.util.List;

/**
 * Created by kstes_000 on 24-Apr-15.
 */
@WebServlet("/admin/createdriver")
public class AdminCreateDriverServlet extends HttpServlet {
    @EJB
    private AdministratorBean administratorBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Hello");
        List<CarEntity> cars = administratorBean.getCars();

        req.setAttribute("cars", cars);
        req.getRequestDispatcher("/WEB-INF/admin/adminCreateDriver.jsp").forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("json" + req.getParameter("data"));
        ObjectMapper mapper = new ObjectMapper();
        ServiceUserEntity user = mapper.readValue(req.getParameter("data"), ServiceUserEntity.class);
        System.out.println("USER" + user);
        System.out.println("USERMAIL:" + user.getEmail());
        user.setDriver(true);

        administratorBean.createUser(user);

    }
}
