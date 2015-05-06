<%--
  Created by IntelliJ IDEA.
  User: exarus
  Date: 4/26/15
  Time: 6:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>function getContextPath() { return '${pageContext.request.contextPath}/'; }</script>
<script src="<%=application.getContextPath()%>/resources/customer/js/sign-out.js"></script>
<!--start: Header -->
<header>

    <!--start: Container -->
    <div class="container">

        <!--start: Row -->
        <div class="row">

            <!--start: Logo -->
            <div class="logo span3">
                <a class="brand" href="<c:url value="/"/>">
                    <img src="<%=application.getContextPath()%>/resources/img/logo.png" alt="Logo">
                </a>
            </div>
            <!--end: Logo -->

            <!--start: Navigation -->
            <div class="span9">

                <div class="navbar navbar-inverse">
                    <div class="navbar-inner">
                        <div class="container">
                            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </a>

                            <div class="nav-collapse collapse">
                                <ul class="nav" id="button-list">
                                    <li <c:if test="${requestScope.pageName == 'index'}">class="active"</c:if>>
                                        <a href="<c:url value="/"/>">Home</a>
                                    </li>
                                    <li <c:if test="${requestScope.pageName == 'order'}">class="active"</c:if>>
                                        <a href="<c:url value="/order"/>">Fast Order</a>
                                    </li>
                                    <%--TODO make link to client dashboard--%>
                                    <%--<li <c:if test="${requestScope.pageName == 'customerProfile'}">class="active"</c:if>>--%>
                                        <%--<a href="<c:url value="/customer/dashboard?type=old"/>">Client Dashboard</a>--%>
                                    <%--</li>--%>
                                    <li <c:if test="${requestScope.pageName == 'orderComplete'}">class="active"</c:if>>
                                        <a href="<c:url value="/orderComplete"/>">Order</a>
                                    </li>
                                    <li <c:if test="${requestScope.pageName == 'orderInformation'}">class="active"</c:if>>
                                        <a href="<c:url value="/orderInfo"/>">Order information</a>
                                    </li>
                                    <c:if test="${sessionScope.userId == null}">
                                        <li <c:if test="${requestScope.pageName == 'signIn'}">class="active"</c:if>>
                                            <a href="<c:url value="/signin"/>">Sign in</a>
                                        </li>
                                        <li <c:if test="${requestScope.pageName == 'signUp'}">class="active"</c:if>>
                                            <a href="<c:url value="/signup"/>">Sign up</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${sessionScope.userId != null}">
                                        <li <c:if test="${requestScope.pageName == 'signOut'}">class="active"</c:if>
                                                id="signout">
                                            <a href="<c:url value="#"/>" onclick="signOut()">
                                                Sign out
                                            </a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <!--end: Navigation -->

        </div>
        <!--end: Row -->

    </div>
</header>
<!--end: Header-->
