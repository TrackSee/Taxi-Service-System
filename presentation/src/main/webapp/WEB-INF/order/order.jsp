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
                        <div class="container">
                            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </a>
                            <div class="nav-collapse collapse">
                                <ul class="nav">
                                    <ul class="nav">
                                        <li><a href="">Home</a></li>
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
<div id="wrapper">

    <!--start: Container -->
    <div class="container">

        <!--start: Row -->
        <div class="row">

            <div class="span8">

                <!-- start: About Us -->
                <div id="about" style="padding-left: 50%">
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

            <!-- start: Team -->
            <%--<div id="team">--%>

                <%--<div class="title"><h3>Extended Booking Taxi </h3></div>--%>
                <%--<p>--%>
                <%--<form method="POST" action="extendedOrder">--%>
                    <%--<div class="form-group">--%>
                        <%--<input type="text" name="phoneNumber" class="form-control" id="phoneNumber" placeholder="Phone number:"--%>
                               <%--required>--%>
                    <%--</div>--%>


                    <%--<div class="form-group">--%>
                        <%--<input type="email"  class="form-control" id="email" name="email" placeholder="Email"--%>
                               <%--data-error="That email is invalid" required>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<input type="text" class="form-control" id="addressFrom" name="addressFrom" placeholder="Address from:"--%>
                               <%--data-error="That address is invalid" required>--%>

                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<input type="text" class="form-control" id="addressTo" name="addressTo" placeholder="Address to:"--%>
                               <%--data-error="That address is invalid" required>--%>

                    <%--</div>--%>

                <%--<div class="form-group">--%>
                    <%--<p><b></b></p>--%>
                    <%--<p><input type="checkbox" name="option1" value="a1" checked>Possibility to transport animals<Br>--%>
                        <%--<input type="checkbox" name="option2" value="a2">Free wi-fi in taxi<Br>--%>
                        <%--<input type="checkbox" name="option3" value="a3">Smoking driver<Br>--%>
                        <%--<input type="checkbox" name="option4" value="a4">Air conditioning<Br>--%>
                        <%--<input type="checkbox" name="option5" value="a5">Meet at the airport</p>--%>
                    <%--<input type="checkbox" name="option5" value="a5">"Sober driver" service</p>--%>
                <%--<input type="checkbox" name="option5" value="a5">Meet at the airport</p>--%>
                <%--<input type="checkbox" name="option5" value="a5">Convey corporation employees</p>--%>
                <%--...--%>

            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                    <%--<div class="form-group">--%>
                        <%--<button type="submit" class="btn btn-success btn-large">Submit</button>--%>
                    <%--</div>--%>
                <%--</form>--%>
                <%--</p>--%>
                <!-- end: Team -->
            <%--</div>--%>

        </div>
        <!-- end: Row -->
    </div>
    <!-- end: Team -->
</div>

<%@include file="../parts/scripts.jsp" %>

</body>
</html>