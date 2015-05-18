package ua.com.tracksee.servlets.admin;

import ua.com.tracksee.logic.admin.AdministratorBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by root on 28.04.15.
 */
@WebServlet("/admin/assigncar")
public class AssignCarToDriverServlet extends HttpServlet {

    @EJB
    private AdministratorBean administratorBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carNumber = request.getParameter("car");
        if("no car".equals(carNumber)){
            carNumber = null;
        }
        Integer driverID = Integer.parseInt(request.getParameter("driver"));
        administratorBean.assignCar(carNumber, driverID);
        response.setStatus(200);
    }
}
