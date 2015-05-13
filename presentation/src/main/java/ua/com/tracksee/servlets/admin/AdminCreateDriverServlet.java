package ua.com.tracksee.servlets.admin;

        import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.logic.admin.AdministratorBean;
        import ua.com.tracksee.exception.CreateException;

        import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Katia Stetsiuk
 */
@WebServlet("/admin/createdriver")
public class AdminCreateDriverServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static String warning = "Cannot get json from post /admin/createdriver";

    @EJB
    private AdministratorBean administratorBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/admin/adminCreateDriver.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = req.getReader();
            String line;
            do {
                line = reader.readLine();
                sb.append(line).append("\n");
            } while (line != null);
        } catch (IOException e){
            logger.warn(warning);
        }
        ObjectMapper mapper = new ObjectMapper();
        UserEntity user = mapper.readValue(sb.toString(), UserEntity.class);
        user.setDriver(true);
        user.setActivated(true);
        user.setSex(user.getSex().substring(0, 1));
        try {
            administratorBean.createUser(user);
        } catch (CreateException e) {
            logger.warn(e.getMessage());
            resp.getWriter().append(e.getErrorType());
            return;
        }
    }
}
