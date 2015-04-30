<%--

  author: Sharaban Sasha
  Date: 19.04.15
  Time: 20:37
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<%-- start: Java Script --%>
<%@include file="../parts/scripts.jsp" %>
<%-- end: Java Script --%>

<head>
    <%@include file="../parts/meta.jsp" %>
    <%@include file="../parts/bootstrap2.jsp" %>
</head>
<body>
<%@include file="../parts/header.jsp" %>

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
<div id="wrapper">

    <%--start: Container --%>
    <div class="container">

        <%--start: Row --%>
        <div class="row">

            <div class="span8">

                <%-- start: About Us --%>
                <div id="about">

                    <div class="title"><h3>Extended Booking Taxi</h3></div>

                    <form method="post" action="<c:url value="/order/complete"/>">
                        <div class="form-group">
                            <input type="text" name="phoneNumber" class="form-control" placeholder="Phone number:"
                                   required>
                        </div>

                        <div class="form-group">
                            <input type="email" class="form-control" name="email" placeholder="Email"
                                   data-error="That email is invalid" required>
                        </div>

                        <div class="form-group">
                            <label for="origin">Address from</label>
                            <input type="text" id="origin" class="form-control" value="${addressOrigin}"
                                   name="addressOrigin" data-error="That address is invalid"
                                   onblur="updateRoute()">
                        </div>

                        <div class="form-group">
                            <label for="destination">Address to</label>
                            <input type="text" id="destination" class="form-control" value="${addressDestination}"
                                   name="addressDestination" data-error="That address is invalid"
                                   onblur="updateRoute()">
                        </div>

                        <div class="form-group">
                            <label for="price">Order price</label>
                            <input type="text" name="price" id="price" class="form-control"
                                   value="0"
                                   data-error="That address is invalid" readonly>
                        </div>

                        <div class="form-group">
                            <label class="control-label">Car category</label>
                            <select class="form-control order_priority" name="carCategory" id="carCategory">
                                <option value="economyClass">Economy class</option>
                                <option value="businessClass">Business class</option>
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
                            <select class="form-control order_priority" name="service" id="service">
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
                            <select class="form-control order_priority" name="musicStyle" id="musicStyle">
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
                                <input type="checkbox" name="animalTransportation" id="animalTransportation">
                                Animal transportation
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="freeWifi" id="freeWifi"> Free wi-fi
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="smokingDriver" id="smokingDriver"> Smoking driver
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="airConditioner" id="airConditioner"> Air conditioner
                            </label>
                        </div>

                        <label>Description:</label>
                        <textarea name="description" id="description" rows="4" cols="50"></textarea><br/>

                        <div class="form-group">
                            <button type="submit" class="btn btn-success btn-large">Submit</button>
                        </div>

                    </form>
                </div>

            </div>
            <%-- end: About Us --%>
        </div>
        <%-- end: Row --%>

    </div>
    <%-- end: Team --%>

</div>
<%-- end:wrapper --%>

<div class="row">
    <div class="span12">
        <div id="map-canvas" class="googleMap"></div>
    </div>
</div>

<%-- start: Java Script --%>
<%@include file="../parts/scripts.jsp" %>
<script src="<%=application.getContextPath()%>/resources/customer/js/google-maps.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/order.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/soberDriver.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/meetMyGuest.js"></script>
<%-- end: Java Script --%>

<%@include file="../parts/footer.jsp" %>

</body>
</html>