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
                                    <li><a href="<c:url value="/"/>">Home</a></li>
                                    <li  class="active"><a href="">Order</a></li>
                                    <li><a href="orderInfo">Order nformation</a></li>
                                    <li><a href="signin">Login</a></li>
                                    <li><a href="signup">Registration</a></li>
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

                    <form method="post" action="<c:url value="/orderComplete"/>">
                        <div class="form-group">
                            <input type="text" name="phoneNumber" class="form-control"  placeholder="Phone number:"
                                   required>
                        </div>

                        <div class="form-group">
                            <input type="email"  class="form-control" name="email" placeholder="Email"
                                   data-error="That email is invalid" required>
                        </div>

                        <div class="form-group">
                            <label>Address from</label>
                            <input   type="text"  class="form-control" value="${addressOrigin}"
                                    name="addressOrigin" data-error="That address is invalid" readonly>
                        </div>

                        <div class="form-group">
                            <label>Address to</label>
                            <input  type="text" class="form-control" value="${addressDestination}"
                                   name="addressDestination" data-error="That address is invalid" readonly>
                          </div>

                        <div class="form-group">
                            <label>Order price</label>
                            <input  type="text" name="price" class="form-control"
                                   value="${price}"  placeholder="Address to:"
                                   data-error="That address is invalid" readonly>
                        </div>

                        <div class="form-group">
                            <label class="control-label">Car category</label>
                            <select class="form-control order_priority" name="carCategory">
                                <option value="economyClass">Economy class</option>
                                <option value="businessClass"> selected>Business class</option>
                                <option value="van">Van</option>
                            </select>
                        </div>

                            <div class="form-group">
                                <label class="control-label">Way of payment</label>
                                <select class="form-control order_priority" name="wayOfPayment">
                                    <option value="cash">Cash</option>
                                    <option value="visaCard">Visa card</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label class="control-label">Driver sex</label>
                                <select class="form-control order_priority" name="driverSex">
                                    <option value="male">Male</option>
                                    <option value="female">Female</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label class="control-label">Service</label>
                                <select class="form-control order_priority" name="service">
                                    <option value="default">Default</option>
                                    <option value="soberDriver">Service "Sober driver"</option>
                                    <option value="guestDelivery">Service "Guest delivery"</option>
                                    <option value="cargoTaxi">Service "Cargo taxi"</option>
                                    <option value="meetMyGuest">Service "Meet my guest"</option>
                                    <option value="celebrationTaxi">Service "Celebration taxi"</option>
                                    <option value="foodStuffDelivery">Service "Foodstuff delivery"</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label class="control-label">Music style</label>
                                <select class="form-control order_priority" name="musicStyle">
                                    <option value="default">Default</option>
                                    <option value="blues">Blues</option>
                                    <option value="classicalMusic">Classical music</option>
                                    <option value="rock">Rock</option>
                                    <option value="jazz">Jazz</option>
                                    <option value="danceMusic">Dance music</option>
                                    <option value="electronicMusic">Electronic music</option>
                                    <option value="hipHop">Hip Hop</option>
                                </select>
                            </div>


                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="animalTransportation"> Animal transportation
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="freeWifi"> Free wi-fi
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="smokingDriver"> Smoking driver
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="airConditioner"> Air conditioner
                                </label>
                            </div>

                                Description:<br />
                                  <textarea name="description" >

                                  </textarea><br />



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
    <%-- end:wrapper --%>
</div>
<%-- end:padding  --%>


<%@include file="../parts/scripts.jsp" %>

</body>
</html>