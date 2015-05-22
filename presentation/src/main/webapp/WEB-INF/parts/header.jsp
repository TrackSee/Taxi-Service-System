<%--
  Created by IntelliJ IDEA.
  User: exarus
  Date: 4/26/15
  Time: 6:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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
                                <ul class="nav" id="button-list">
                                    <c:set var="uri" value="${pageContext.request.requestURI}" scope="page"/>
                                    <li <c:if test="${pageScope.uri.endsWith('/index.jsp')}">class="active"</c:if>>
                                        <a href="<c:url value="/"/>">Home</a>
                                    </li>

                                    <li <c:if test="${pageScope.uri.endsWith('/order.jsp')}">class="active"</c:if>>
                                        <a href="<c:url value="/order"/>">Order</a>
                                    </li>

                                    <c:if test="${sessionScope.userId == null}">
                                        <li <c:if test="${pageScope.uri.endsWith('/orderInfo.jsp')}">class="active"</c:if>>
                                            <a href="<c:url value="/orderInfo"/>">Order information</a>
                                        </li>
                                        <li <c:if test="${pageScope.uri.endsWith('/tariffs.jsp')}">class="active"</c:if>>
                                            <a href="<c:url value="/tariffs"/>">Tariffs</a>
                                        </li>
                                        <li <c:if test="${pageScope.uri.endsWith('/signIn.jsp')}">
                                                    class="active"</c:if>>
                                            <a href="<c:url value="/signin"/>">Sign in</a>
                                        </li>
                                        <li <c:if test="${pageScope.uri.endsWith('/signUp.jsp')}">class="active"</c:if>>
                                            <a href="<c:url value="/signup"/>">Sign up</a>
                                        </li>
                                    </c:if>

                                    <c:if test="${sessionScope.userId != null}">
                                        <li id="customerProfile"
                                                <c:if test="${pageScope.uri.endsWith('/customerProfile.jsp')}">
                                                    class="active"
                                                </c:if>
                                                >
                                            <a href="<c:url value="/customer"/>">Client Dashboard</a>
                                        </li>

                                        <c:if test="${sessionScope.role == 'admin'}">
                                        <li class="header-user-link">
                                            <a href="<c:url value="/admin"/>">Admin dashboard</a>
                                        </li>
                                        </c:if>

                                        <c:if test="${sessionScope.role == 'driver'}">
                                        <li class="header-user-link">
                                            <a href="<c:url value="/driver/free-orders"/>">Driver profile</a>
                                        </li>
                                        </c:if>

                                        <li id="signout">
                                            <a id="sign-out-button" href=".">Sign out</a>
                                        </li>

                                    </c:if>


                                </ul>

                                        <%--<c:if test="${sessionScope.role == 'admin'}">--%>
                                            <%--<li>--%>
                                            <%--<span class="greeting">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hello,&nbsp;--%>
                                            <%--&lt;%&ndash;<span id="header_user_menu_parent" name="splash-button" class="header-user-title">&ndash;%&gt;--%>
                                            <%--<a href="<c:url value="/admin"/>" name="profile" class="header-user-link">${sessionScope.name}</a></span>--%>
                                            <%--</li>--%>
                                        <%--</c:if>--%>
                                        <%--<c:if test="${sessionScope.role == 'driver'}">--%>
                                            <%--<li>--%>
                                            <%--<span class="greeting">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hello,&nbsp;--%>
                                            <%--&lt;%&ndash;<span id="header_user_menu_parent" name="splash-button" class="header-user-title">&ndash;%&gt;--%>
                                            <%--<a href="<c:url value="/driver/free-orders"/>" name="profile" class="header-user-link">${sessionScope.name}</a></span>--%>
                                            <%--</li>--%>
                                         <%--</c:if>--%>
                                        <%--<c:if test="${sessionScope.role == 'register_customer'}">--%>
                                            <%--<li>--%>
                                            <%--<span class="greeting">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hello,&nbsp;--%>
                                            <%--&lt;%&ndash;<span id="header_user_menu_parent" name="splash-button" class="header-user-title">&ndash;%&gt;--%>
                                            <%--<a href="<c:url value="/customer"/>" name="profile" class="header-user-link">${sessionScope.name}</a></span>--%>
                                            <%--</li>--%>
                                        <%--</c:if>--%>

                                <%--<span font-size="18" color="black">Hello,&nbsp;--%>
                                <%--<a href="/customerProfile" >${sessionScope.name}</a></span>--%>
                                <%--<span style='padding-left:40px;'> </span>--%>
                                <%--<span>Hello,&nbsp;</span>--%>
                                <%--&lt;%&ndash;<span id="header_user_menu_parent" name="splash-button" class="header-user-title">&ndash;%&gt;--%>
                                <%--<a href="/admin" name="profile" class="header-user-link">${sessionScope.name}</a>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--end: Navigation -->

            <%--<div class="logo span3_my">--%>
                <%--<a class="brand_my" href="<c:url value="/"/>">--%>
                    <%--<img src="<%=application.getContextPath()%>/resources/img/logo.png" alt="Logo">--%>
                <%--</a>--%>
            <%--</div>--%>
        </div>
        <!--end: Row -->
    </div>
</header>
<!--end: Header-->

