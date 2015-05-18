<%--
  Created by IntelliJ IDEA.
  User: Vadym Akymov
  Date: 15.04.15
  Time: 1:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="parts/meta.jsp" %>
    <link href="<%=application.getContextPath()%>/resources/css/bootstrap2/bootstrap.min.css" rel="stylesheet">
    <%@include file="parts/bootstrap2.jsp" %>

    <%--For slider--%>
    <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/carousel.css">
    <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/customer/css/textOutline.css">
    <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/agency.css">
    <link rel="shortcut icon" href="<%=application.getContextPath()%>/resources/img/icons/cab.png" type="image/png">

</head>

<body>
<%@include file="parts/header.jsp" %>
<%-- start: Slider --%>
<div id="myCarousel" class="carousel slide">
    <div class="carousel-inner">
        <div class="item active">
            <img src="<%=application.getContextPath()%>/resources/img/slides/slide1.jpg" alt="slide1">

            <div class="container">
                <div class="carousel-caption">
                    <div><h1 class="outline">Cab booking online</h1>

                    <p class="lead"><h3 class="outline">You can book a taxi right now without delay and red tape.</h3></p>
                    <a class="btn btn-large btn-success" href="order"><h4 class="outline">Book taxi now</h4></a></div>
                </div>
            </div>
        </div>
        <div class="item">
            <img src="<%=application.getContextPath()%>/resources/img/slides/slide9.jpg" alt="slide2">

            <div class="container">
                <div class="carousel-caption">
                    <h1 class="outline">My account</h1>

                    <p class="lead"><h3 class="outline">You can create your own account which simplifies and accelerates
                    the process of ordering a taxi</h3></p>
                    <a class="btn btn-large btn-success" href="signup"><h4 class="outline">Sign up now</h4></a>
                </div>
            </div>
        </div>
        <div class="item">
            <img src="<%=application.getContextPath()%>/resources/img/slides/slide4.jpg" alt="slide3">

            <div class="container">
                <div class="carousel-caption">
                    <h1 class="outline">Additional information</h1>

                    <p class="lead" ><h3 class="outline">You can track your order and see all information about it</h3></p>
                    <a class="btn btn-large btn-success" href="orderInfo"><h4 class="outline">Track your taxi order</h4></a>
                </div>
            </div>
        </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
</div>
<%--End slider--%>
<!-- Services Section -->
<div>
    <h2 align="center">Most popular services</h2>

    <div align="center" class="float-left">
         <span class="fa-stack fa-4x">
                        <i class="fa fa-circle fa-stack-2x text-primary"></i>
                        <i class="fa fa-automobile fa-stack-1x fa-inverse"></i>
                    </span>
        <h4 class="service-heading">Sober driver</h4>
        <p class="text-muted">The term "designated driver" refers to the selection of a person who remains sober as the responsible driver of a vehicle whilst others have been allowed to drink alcoholic beverages.</p>

    </div>
    <div align="center" class="float-left">
         <span class="fa-stack fa-4x">
                        <i class="fa fa-circle fa-stack-2x text-primary"></i>
                        <i class="fa fa-plane fa-stack-1x fa-inverse"></i>
                    </span>
        <h4 class="service-heading">Guest delivery</h4>
        <p class="text-muted">Sometimes you can’t be there when your guests arrive at the airport. But with our company, you can be sure that your guests are getting the VIP welcoming treatment you want to provide.</p>

    </div>
    <div align="center" class="float-left">
         <span class="fa-stack fa-4x">
                        <i class="fa fa-circle fa-stack-2x text-primary"></i>
                        <i class="fa fa-birthday-cake fa-stack-1x fa-inverse"></i>
                    </span>
        <h4 class="service-heading">Celebration taxi</h4>
        <p class="text-muted">We provide cars for any occasions: weddings, events, party nights, birthdays, special occasions. Our drivers are well dressed and safe drivers.</p>

    </div>
</div>


<%-- start: Java Script --%>
<script src="<%=application.getContextPath()%>/resources/js/jquery-1.8.2.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/bootstrap2/bootstrap.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/flexslider.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/jquery.cslider.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/slider.js"></script>
<script defer="defer" src="<%=application.getContextPath()%>/resources/js/custom.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/notify-combined.min.js"></script>

<%--For slider--%>
<script src="<%=application.getContextPath()%>/resources/js/bootstrap-carousel.js"></script>
<%-- end: Java Script --%>

<%@include file="parts/footer.jsp" %>
</body>
</html>