<%--
  Created by IntelliJ IDEA.
  User: exarus
  Date: 4/26/15
  Time: 6:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                <ul class="nav">
                                    <li <c:if test="${requestScope.pageName == 'index'}">class="active"</c:if>>
                                        <a href="<c:url value="/"/>">Home</a>
                                    </li>
                                    <li <c:if test="${requestScope.pageName == 'order'}">class="active"</c:if>>
                                        <a href="<c:url value="/order"/>">Order</a>
                                    </li>
                                    <li <c:if test="${requestScope.pageName == 'orderInformation'}">class="active"</c:if>>
                                        <a href="<c:url value="/order/info"/>">Order information</a>
                                    </li>
                                    <li <c:if test="${requestScope.pageName == 'signIn'}">class="active"</c:if>>
                                        <a href="<c:url value="/signin"/>">Login</a>
                                    </li>
                                    <li <c:if test="${requestScope.pageName == 'signUp'}">class="active"</c:if>>
                                        <a href="<c:url value="/signup"/>">Registration</a>
                                    </li>
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
