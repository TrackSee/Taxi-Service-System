<%--

  author: Sharaban Sasha
  Date: 19.04.15
  Time: 20:37
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <!-- start: Meta -->
    <meta charset="utf-8">
    <title>Taxi service</title>
    <meta name="description" content="GotYa Free Bootstrap Theme"/>
    <meta name="keywords" content="Template, Theme, web, html5, css3, Bootstrap" />
    <!-- end: Meta -->

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

                <a class="brand" href="#"><img src="<%=application.getContextPath()%>/resources/img/logo.png"
                                               alt="Logo"></a>

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
                                    <ul class="nav">
                                        <li><a href="/">Home</a></li>
                                        <li  class="active"><a href="order">Order</a></li>
                                        <li><a href="orderInfo">Order nformation</a></li>
                                        <li><a href="signin">Login</a></li>
                                        <li><a href="signup">Registration</a></li>
                                    </ul>
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

<!-- start: Page Title -->
<div id="page-title">

    <div id="page-title-inner">

        <!-- start: Container -->
        <div class="container">

            <h2><i class="ico-settings ico-white"></i>Order</h2>

        </div>
        <!-- end: Container  -->

    </div>

</div>
<!-- end: Page Title -->

<%--
   Fast taxi order form with validation.

  author: Sharaban Sasha
  Date: 19.04.15
  Time: 20:37
--%>
<!--start: Wrapper-->
<div class="padding">
<div id="wrapper">

    <!--start: Container -->
    <div class="container">

        <!--start: Row -->
        <div class="row">

            <div class="span8">

                <!-- start: About Us -->
                <div id="about">
                    <div class="title"><h3>Fast Booking Taxi</h3></div>

                    <form method="post" action="<c:url value="/order"/>">

                        <div class="form-group">
                            <input type="text" class="form-control"  name="addressOrigin" placeholder="Address from:"
                                   data-error="That address is invalid" required>

                        </div>

                        <div class="form-group">
                            <input type="text" class="form-control"  name="addressDestination" placeholder="Address to:"
                                   data-error="That address is invalid" required>

                        </div>


                        <div class="form-group">
                            <button type="submit" class="btn btn-success btn-large">Submit</button>
                        </div>
                    </form>
                </div>
                </p>
            </div>
            <!-- end: About Us -->
        </div>
        <!-- end: Row -->
    </div>
    <!-- end: Team -->
   </div>
    <!-- end: wrapper -->
</div>
    <%-- end:padding  --%>
    <!-- start: Java Script -->
    <!-- Placed at the end of the document so the pages load faster -->
<script src="<%=application.getContextPath()%>/resources/js/jquery-1.8.2.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/bootstrap2/bootstrap.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/flexslider.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/jquery.cslider.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/slider.js"></script>
<script defer="defer" src="<%=application.getContextPath()%>/resources/js/custom.js"></script>
    <!-- end: Java Script -->

</body>
</html>