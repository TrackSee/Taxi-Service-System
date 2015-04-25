package ua.com.tracksee.servlets.admin;


import ua.com.tracksee.config.manager.been.ConfigManagerBean;
import ua.com.tracksee.config.manager.been.MessageManagerBean;
import ua.com.tracksee.dao.postrgresql.ServiceUserDaoBeen;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.error.PersistError;
import ua.com.tracksee.logic.admin.AdministratorBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by byte on 4/18/15.
 *
 */

@WebServlet(urlPatterns = "/blockAccount")
public class BlockAccountServlet extends HttpServlet {


    public static int COUNT_PER_PAGE = 20;
    public static int BLOCK_ACCOUNT_VALUE = 10;


    @EJB
    private ConfigManagerBean configManager;

    @EJB
    private AdministratorBean administratorBean;



    @EJB
    private MessageManagerBean messageManagerBean;


    /**
     * method which redirect to blockAccount.jsp
     * @param req - request
     * @param resp - responce
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ServiceUserEntity> list = new LinkedList<>();

        int countPerPage = getIntParam(req, "countPerPage", COUNT_PER_PAGE);
        int firstRecord = getIntParam(req, "page", 1) - 1;
        long usersCount = administratorBean.getUsersCount();
        long pageCount = usersCount/countPerPage;
        if (usersCount % countPerPage > 0) pageCount ++;

        list.addAll(administratorBean.getAll(firstRecord * countPerPage, countPerPage));

        req.setAttribute("currentPage", firstRecord + 1);
        req.setAttribute("countPerPage", countPerPage);
        req.setAttribute("noOfPages", pageCount);
        req.setAttribute("users", list);

        forwardPage("path.page.block.account", req, resp);


    }

    /**
     * method which block account from request
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Integer> userId = getUsersIdFromReq(req);

        if (userId == null || userId.size() == 0){
            req.setAttribute("errorMessage", messageManagerBean.getString("select.users"));
            doGet(req, resp);
        }



        try {
            administratorBean.blockAllById(userId, BLOCK_ACCOUNT_VALUE);
        } catch (PersistError persistError) {
            req.setAttribute("errorMessage", messageManagerBean.getString("block.account.error"));
        }


        doGet(req, resp);

    }


    /**
     * method return int param from request, if param not exist return defaultVal
     * @param req - HttpServletRequest
     * @param strId - parameter name
     * @param defaultVal - default value of parametr
     * @return - parameter value
     */
    private int getIntParam(HttpServletRequest req, String strId, int defaultVal) {

        String strCountPerPage = req.getParameter(strId);

        int countPerPage;

        try {
            if (checkNotNullAndEmpty(strCountPerPage)){
                countPerPage = Integer.parseInt(strCountPerPage);
            } else countPerPage = defaultVal;
        } catch (Exception e){
            countPerPage = defaultVal;
        }

        return countPerPage;
    }

    /**
     * method checking String
     * @param strCountPege - string param
     * @return - true if string not null and not empty
     */
    private boolean checkNotNullAndEmpty(String strCountPege) {
        return strCountPege != null && !strCountPege.isEmpty();
    }

    private void forwardPage(String conf_page, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        String page = configManager.getString(conf_page);


        RequestDispatcher dispatcher = req.getRequestDispatcher(page);

        dispatcher.forward(req, resp);

    }

    /**
     * method return users id from request
     * @param req - HttpServletRequest
     * @return - list of user ids
     */
    private List<Integer> getUsersIdFromReq(HttpServletRequest req) {
        List<Integer> result = new LinkedList<>();

        String[] userIds = req.getParameterValues("userId");

        StringBuilder errorUserIds = new StringBuilder();

        if (userIds != null)
            for (String userIdStr:userIds){
                if (checkNotNullAndEmpty(userIdStr)){
                    try {
                        Integer userId = Integer.parseInt(userIdStr);
                        result.add(userId);
                    } catch (Exception e){
                        errorUserIds.append(userIdStr).append(", ");
                    }
                }
            }

        if (errorUserIds.length() != 0){
            req.setAttribute("errorUserId", errorUserIds);
        }

        return result;

    }
}