<%--
  Created by IntelliJ IDEA.
  User: Vadym Akymov
  Date: 15.04.15
  Time: 1:37
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>TrackSee</title>
    <!-- start: Mobile Specific -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- end: Mobile Specific -->

    <!-- start: Facebook Open Graph -->
    <meta property="og:title" content=""/>
    <meta property="og:description" content=""/>
    <meta property="og:type" content=""/>
    <meta property="og:url" content=""/>
    <meta property="og:image" content=""/>
    <!-- end: Facebook Open Graph -->

    <!-- start: CSS -->
    <link href="<%=application.getContextPath()%>/resources/css/bootstrap2/bootstrap.css" rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/css/bootstrap2/bootstrap-responsive.css" rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/css/style.css" rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/css/corrections/position.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Droid+Sans:400,700">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Droid+Serif">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Boogaloo">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Economica:700,400italic">
    <!-- end: CSS -->
    <%--For slider--%>
    <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/carousel.css">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

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

                <a class="brand" href="#"><img src="<%=application.getContextPath()%>/resources/img/logo.png" alt="Logo"></a>

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
                                <li class="active"><a href="">Order</a></li>
                                <li><a href="order/info">Driver information</a></li>
                                <li><a href="login.html">Login</a></li>
                                <li><a href="registration.html">Registration</a></li>
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
<!--end: Header-->
<!-- start: Slider -->
<div id="myCarousel" class="carousel slide">
    <div class="carousel-inner">
        <div class="item active">
            <img src="resources/img/slides/slide1.jpg" alt="slide1">
            <div class="container">
                <div class="carousel-caption">
                    <h1>Example headline.</h1>
                    <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                    <a class="btn btn-large btn-primary" href="#">Sign up today</a>
                </div>
            </div>
        </div>
        <div class="item">
            <img src="resources/img/slides/slide9.jpg" alt="slide2">
            <div class="container">
                <div class="carousel-caption">
                    <h1>Another example headline.</h1>
                    <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                    <a class="btn btn-large btn-primary" href="#">Learn more</a>
                </div>
            </div>
        </div>
        <div class="item">
            <img src="resources/img/slides/slide4.jpg" alt="slide3">
            <div class="container">
                <div class="carousel-caption">
                    <h1>One more for good measure.</h1>
                    <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                    <a class="btn btn-large btn-primary" href="#">Browse gallery</a>
                </div>
            </div>
        </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
</div>
<%--End slider--%>


<!-- start: Page Title -->
<%--<div id="page-title" class="position">--%>

    <%--<div id="page-title-inner">--%>

        <%--<!-- start: Container -->--%>
        <%--<div class="container">--%>

            <%--<h2><i class="ico-stats ico-white"></i>Order</h2>--%>

        <%--</div>--%>
        <%--<!-- end: Container  -->--%>

    <%--</div>--%>

<%--</div>--%>
<!-- end: Page Title -->


<!--start: Wrapper-->
<%--<div id="wrapper">--%>

    <%--<!--start: Container -->--%>
    <%--<div class="container">--%>

        <%--<!--start: Row -->--%>
        <%--<div class="row">--%>

            <%--<div class="span8">--%>

                <%--<!-- start: About Us -->--%>
                <%--<div id="about">--%>
                    <%--<div class="title"><h3>Order taxi</h3></div>--%>
                    <%--<p>--%>

                    <%--<div class="nav-collapse collapse">--%>
                        <%--<div class="form-group">--%>
                            <%--<input type="email" class="form-control"--%>
                                   <%--placeholder="Enter phone number">--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--<input type="password" class="form-control"--%>
                                   <%--placeholder="Enter your address">--%>
                        <%--</div>--%>

                        <%--<p><a class="btn btn-success btn-large">Order taxi &raquo;</a></p>--%>

                    <%--</div>--%>

                <%--</div>--%>
                <%--</p>--%>
            <%--</div><!-- end: Footer -->--%>
            <%--<!-- end: About Us -->--%>

            <%--<!-- start: Team -->--%>
            <%--<div id="team">--%>

                <%--<div class="title"><h3>Login and order taxi</h3></div>--%>

                <%--<!-- start: Row -->--%>
                <%--<div class="row">--%>
                    <%--<div class="form-group">--%>
                        <%--<input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter your login">--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<input type="password" class="form-control" id="exampleInputPassword1"--%>
                               <%--placeholder="Enter your password">--%>
                    <%--</div>--%>

                    <%--<p><a class="btn btn-success btn-large">Login &raquo;</a>--%>
                        <%--<a class="btn btn-info btn-large">Sign up &raquo;</a></p>--%>

                    <%--<!-- start: Team -->--%>
                <%--</div>--%>

            <%--</div>--%>
            <%--<!-- end: Row -->--%>
        <%--</div>--%>
        <%--<!-- end: Team -->--%>
    <%--</div>--%>
<%--</div>--%>

<!-- start: Java Script -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=application.getContextPath()%>/resources/js/jquery-1.8.2.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/bootstrap2/bootstrap.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/flexslider.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/jquery.cslider.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/slider.js"></script>
<script defer="defer" src="<%=application.getContextPath()%>/resources/js/custom.js"></script>

<%--For slider--%>
<script src="<%=application.getContextPath()%>/resources/js/bootstrap-carousel.js"></script>
<!-- end: Java Script -->

</body>
</html>