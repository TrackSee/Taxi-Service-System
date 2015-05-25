package ua.com.tracksee.servlets.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.tracksee.dao.implementation.ServiceUserDaoBeen;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.UserEntity;
import ua.com.tracksee.logic.admin.AdministratorBean;
import ua.com.tracksee.logic.facade.CustomerFacade;
import ua.com.tracksee.logic.facade.OrderStatusBO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("admin/changeId")
public class ChangeIdServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private @EJB
    AdministratorBean administratorBean;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s=request.getSession();
        System.out.println("WOrK");
        Integer userID = (Integer) s.getAttribute("userId");
        System.out.println("us id1  " + userID);
        UserEntity curUser = administratorBean.getUserById(userID);
        System.out.println("EMAIL " + curUser.getEmail());
        s.setAttribute("userId", 2);
        System.out.println("usId2  " + s.getAttribute("userId"));
    }
}
