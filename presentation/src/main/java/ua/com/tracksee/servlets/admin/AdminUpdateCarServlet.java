package ua.com.tracksee.servlets.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import ua.com.tracksee.entities.CarEntity;
import ua.com.tracksee.logic.facade.AdminFacade;

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
@WebServlet("/admin/updatecar")
public class AdminUpdateCarServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static String warning = "Cannot get json from post /admin/updatecar";
    private static String carNumber = "carNumber";
    private static String car = "car";
    private String carNumb;
    @EJB
    private AdminFacade adminFacade ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        carNumb = req.getParameter(carNumber);
        req.setAttribute(car, adminFacade.getCarByNumber(carNumb));
        req.getRequestDispatcher("/WEB-INF/admin/adminUpdateCar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String data = getData(req);
        ObjectMapper mapper = new ObjectMapper();
        CarEntity car = mapper.readValue(data, CarEntity.class);
        car.setCarNumber(carNumb);
        adminFacade.updateCar(car);
    }

    private String getData(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = req.getReader();
            String line;
            do {
                line = reader.readLine();
                sb.append(line).append("\n");
            } while (line != null);
        } catch (IOException e) {
            logger.warn(warning);

        }
        return sb.toString();
    }
}
