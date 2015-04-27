<%--
  Created by IntelliJ IDEA.
  User: Vadym Akymov
  Date: 15.04.15
  Time: 1:37
  To change this template use File | Settings | File Templates.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../parts/meta.jsp" %>
    <%@include file="../parts/bootstrap2.jsp" %>
</head>

<body>
<!--start: Header -->
<header>

    <!--start: Container -->
    <div class="container">

        <!--start: Row -->
        <div class="row">

            <!--start: Logo -->
            <div class="logo span3">

                <a class="brand" href="#"><img src="<%=application.getContextPath()%>/resources/img/logo.png"
                                               alt="Logo"></a>

            </div>
            <!--end: Logo -->

            <!--start: Navigation -->
            <div class="span9">

                <div class="navbar navbar-inverse">
                    <div class="navbar-inner">
                        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </a>

                        <div class="nav-collapse collapse">
                            <ul class="nav">
                                <li class="active"><a href="">Home</a></li>
                                <li><a href="order">Order</a></li>
                                <li><a href="orderInfo">Order nformation</a></li>
                                <li><a href="signin">Login</a></li>
                                <li><a href="signup">Registration</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
            <!--end: Navigation -->

        </div>
        <!--end: Row -->

    </div>
    <!--end: Container-->

</header>
<!-- start: Slider -->
<div id="myCarousel" class="carousel slide">
    <div class="carousel-inner">
        <div class="item active">
            <img src="<%=application.getContextPath()%>/resources/img/slides/slide1.jpg" alt="slide1">

            <div class="container">
                <div class="carousel-caption">
                    <h1>Cab booking online</h1>

                    <p class="lead" >You can book a taxi right now without delay and red tape. </p>
                    <a class="btn btn-large btn-success" href="#">Book taxi now</a>
                </div>
            </div>
        </div>
        <div class="item">
            <img src="<%=application.getContextPath()%>/resources/img/slides/slide9.jpg" alt="slide2">

            <div class="container">
                <div class="carousel-caption">
                    <h1>My account</h1>

                    <p class="lead">You can create your own account which simplifies and accelerates the
                        process of ordering a taxi</p>
                    <a class="btn btn-large btn-success" href="#">Sign up now</a>
                </div>
            </div>
        </div>
        <div class="item">
            <img src="<%=application.getContextPath()%>/resources/img/slides/slide4.jpg" alt="slide3">

            <div class="container">
                <div class="carousel-caption">
                    <h1>Additional information</h1>

                    <p class="lead" >Our company is young and promising</p>
                    <a class="btn btn-large btn-success" href="#">Read about us</a>
                </div>
            </div>
        </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
</div>
<%--End slider--%>

<%@include file="../parts/scripts.jsp" %>

<%--For slider--%>
<script src="<%=application.getContextPath()%>/resources/js/bootstrap-carousel.js"></script>

</body>
</html>