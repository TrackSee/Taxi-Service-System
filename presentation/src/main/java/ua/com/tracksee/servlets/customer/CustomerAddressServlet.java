package ua.com.tracksee.servlets.customer;

import ua.com.tracksee.entities.AddressEntity;
import ua.com.tracksee.logic.address.AddressBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created on 30.04.2015
 *
 * @author Oleksandr Kozin
 */
@WebServlet(urlPatterns = "/customer/address", name = "CustomerAddressServlet")
public class CustomerAddressServlet extends HttpServlet {

    @EJB
    private AddressBean addressBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем список пользователей

//        Integer userId = (Integer) request.getSession().getAttribute("user_id");
        Integer usrId = 15;
        List<AddressEntity> allAddresses = addressBean.getAllAddressesByUserId(usrId);

        // добавляем полученный список в request,
        // который отправится на jsp
        request.setAttribute("allAddresses", allAddresses);

        // отправляем request на jsp
        request.getRequestDispatcher("/address.jsp").forward(request, response);
    }
}
