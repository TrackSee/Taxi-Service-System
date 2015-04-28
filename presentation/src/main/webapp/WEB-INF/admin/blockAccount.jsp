<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: byte
  Date: 4/20/15
  Time: 8:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="header.jsp"/>
<body>
<jsp:include page="menu.jsp"/>
<jsp:useBean id="messages" scope="page" class="ua.com.tracksee.config.manager.been.MessageManagerBean"/>
<div id="page-wrapper">

    <%--error messages--%>
    <c:if test="${empty users}">
        ${messages.getString("users.not.found")}
    </c:if>


    <h3 style="color: red">${errorMessage}</h3>

    <h3 style="color: green"> ${completeMessage} </h3>

    <div class="row">
        <form method="get" action="blockAccount">
            <label for="limit">limit</label>
            <select class="form" id="limit" name="countPerPage">
                <option value="20">20</option>
                <option value="50" ${countPerPage == 50? 'selected' : ''}>50</option>
                <option value="100" ${countPerPage == 100? 'selected' : ''}>100</option>
            </select>
            <input type="submit">
        </form>

        <%--<form method="get" action="blockAccount" id="search">
            <div class="col-lg-6">
                <div class="input-group">
                    <input type="text" name="search" id="param" class="form-control" placeholder="Search for...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="submit" form="search">Go!</button>
                </span>
                </div>
            </div>
            <!-- /input-group -->
            <!-- /.col-lg-6 -->
        </form>--%>
    </div>
    <%--For displaying Previous link except for the 1st page --%>
    <table class="table table-hover">
        <c:if test="${currentPage != 1}">
            <td>
                <c:url var="nextUrl" value="">
                    <c:forEach items="${param}" var="entry">
                        <c:if test="${entry.key != 'page'}">
                            <c:param name="${entry.key}" value="${entry.value}"/>
                        </c:if>
                    </c:forEach>
                    <c:param name="page" value="${currentPage - 1}"/>
                </c:url>
                <a href="blockAccount${nextUrl}">Previous</a>
            </td>
        </c:if>
        <c:if test="${currentPage == 1}">
            <td>
                Previous
            </td>
        </c:if>


        <%--For displaying Next link --%>
        <c:if test="${currentPage lt noOfPages && not empty users}">
            <td>
                <c:url var="nextUrl" value="">
                    <c:forEach items="${param}" var="entry">
                        <c:if test="${entry.key != 'page'}">
                            <c:param name="${entry.key}" value="${entry.value}"/>
                        </c:if>
                    </c:forEach>
                    <c:param name="page" value="${currentPage + 1}"/>
                </c:url>
                <a href="blockAccount${nextUrl}">Next</a>
            </td>

        </c:if>
    </table>

    <c:if test="${not empty users}">
        <c:url var="nextUrl" value="">
            <c:forEach items="${param}" var="entry">
                <c:if test="${entry.key != 'userId'}">
                    <c:param name="${entry.key}" value="${entry.value}"/>
                </c:if>
            </c:forEach>
        </c:url>
        <form action="blockAccount${nextUrl}" method="post">

            <table class="table table-hover">

                <input type="submit" value="block select account"/>
                <tr>
                    <td>user id</td>
                    <td>email</td>
                    <td>ignored times</td>
                </tr>
                <c:forEach items="${users}" var="user">

                    <tr>
                        <td>${user.userId}</td>
                        <td>
                            <a href="#"> ${user.email}</a>
                        </td>
                        <td>
                            <c:if test="${user.ignoredTimes lt 4}">
                                ${user.ignoredTimes}
                            </c:if>
                            <c:if test="${user.ignoredTimes gt 3}">
                                <span style="color: red">blocked</span>
                            </c:if>
                        </td>
                        <td>
                            <input type="checkbox" name="userId" value="${user.userId}"
                                    <c:if test="${user.ignoredTimes gt 3}">
                                        checked
                                        disabled
                                    </c:if>
                                    />
                        </td>
                        <td>

                        </td>
                    </tr>

                </c:forEach>

            </table>
        </form>


    </c:if>

    <%--For displaying Previous link except for the 1st page --%>
    <table class="table table-hover">
        <c:if test="${currentPage != 1}">
            <td>
                <c:url var="nextUrl" value="">
                    <c:forEach items="${param}" var="entry">
                        <c:if test="${entry.key != 'page'}">
                            <c:param name="${entry.key}" value="${entry.value}"/>
                        </c:if>
                    </c:forEach>
                    <c:param name="page" value="${currentPage - 1}"/>
                </c:url>
                <a href="blockAccount${nextUrl}">Previous</a>
            </td>
        </c:if>
        <c:if test="${currentPage == 1}">
            <td>
                Previous
            </td>
        </c:if>


        <%--For displaying Next link --%>
        <c:if test="${currentPage lt noOfPages && not empty users}">
            <td>
                <c:url var="nextUrl" value="">
                    <c:forEach items="${param}" var="entry">
                        <c:if test="${entry.key != 'page'}">
                            <c:param name="${entry.key}" value="${entry.value}"/>
                        </c:if>
                    </c:forEach>
                    <c:param name="page" value="${currentPage + 1}"/>
                </c:url>
                <a href="blockAccount${nextUrl}">Next</a>
            </td>

        </c:if>
    </table>
</div>


<jsp:include page="footer.jsp"/>

</body>
</html>
