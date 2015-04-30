package ua.com.tracksee.servlets.customer;

import ua.com.tracksee.logic.address.AddressBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 30.04.2015
 *
 * @author Oleksandr Kozin
 */
@WebServlet(urlPatterns = "/customer/address/add", name = "AddAndEditAddressServlet")
public class AddAndEditAddressServlet extends HttpServlet {

    @EJB
    private AddressBean addressBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        // если параметр null, то пользователь
        // пришел на страницу, чтобы создать нового, иначе
        // будет выполнятся редактирование существующего пользователя
        if(request.getParameter("edit") != null){
            long id = Long.valueOf(request.getParameter("edit"));
//            User user = userBean.get(id);

//            request.setAttribute("user", user);
        }

        request.getRequestDispatcher("/add.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        int age = Integer.valueOf(request.getParameter("age"));

        // если id есть, то выполняется редактирование
        // а если нет id, то - это значит, что создается новый пользователь
        if(request.getParameter("id") != ""){
            long id = Long.valueOf(request.getParameter("id"));
//            User user = userBean.get(id);
//            user.setAge(age);
//            user.setLastName(lastName);
//            user.setName(name);
//
//            // обновляем пользователя
//            userBean.update(user);
        } else{
            // добавляем нового
//            userBean.add(new User(name, lastName, age));
        }

        // перенаправляем на сервлет, который выводит все пользователей
        response.sendRedirect("list");
    }
}
