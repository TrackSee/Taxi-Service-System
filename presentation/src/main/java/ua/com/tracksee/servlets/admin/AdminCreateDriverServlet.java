package ua.com.tracksee.servlets.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.exception.RegistrationException;
import ua.com.tracksee.logic.facade.AdminFacade;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

        import static ua.com.tracksee.logic.encryption.HashGenerator.getHash;

/**
 * @author Katia Stetsiuk
 */
@WebServlet("/admin/createdriver")
public class AdminCreateDriverServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static String warning = "Cannot get json from post /admin/createdriver";

    @EJB
    private AdminFacade adminFacade ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/admin/adminCreateDriver.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = getData(req);
        System.out.println("data:  " + data);
        ObjectMapper mapper = new ObjectMapper();
        UserEntity user = mapper.readValue(data, UserEntity.class);
        user.setDriver(true);
        user.setActivated(true);
        user.setSex(user.getSex().substring(0, 1));
        try {
            adminFacade.createUser(user);
        } catch (RegistrationException e) {
            logger.warn(e.getMessage());
            resp.setStatus(405);
           // resp.getWriter().append(e.getErrorType());
            //return;
        }
    }

    private String getData(HttpServletRequest req){
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
        return sb.toString();

    }
   }
