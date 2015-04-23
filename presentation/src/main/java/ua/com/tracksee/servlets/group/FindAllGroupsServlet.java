package ua.com.tracksee.servlets.group;

import com.google.gson.Gson;
import ua.com.tracksee.entity.Group;
import ua.com.tracksee.logic.GroupBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Igor Gula on 21.04.2015.
 */
@WebServlet(name = "FindAllGroupsServlet", urlPatterns = {"/FindAllGroupsServlet"})
public class FindAllGroupsServlet extends HttpServlet {

    private static final String PAGE_NUMBER_ALIAS = "pageNumber";
    private static final String PAGE_SIZE_ALIAS = "pageSize";

    @EJB
    private GroupBean groupBean;

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER_ALIAS));
        int pageSize = Integer.parseInt(request.getParameter(PAGE_SIZE_ALIAS));

        List<Group> groups = groupBean.getGroups(pageNumber, pageSize);
        groups.add(new Group("", groupBean.getGroupsCount()));

        String json = new Gson().toJson(groups);

        System.out.println(json);
        response.setContentType("application/json");
        response.getWriter().print(json);
    }

}

