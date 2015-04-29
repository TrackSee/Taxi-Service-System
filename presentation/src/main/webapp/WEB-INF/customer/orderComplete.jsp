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

    <!--start: Container -->
    <div class="container">

        <!--start: Row -->
        <div class="row">

            <div class="span8">

                <!-- start: About Us -->
                <div id="about">
                    <div class="title"><h3>Extended Booking Taxi</h3></div>

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
                                    name="addressOrigin" data-error="That address is invalid">
                        </div>

                        <div class="form-group">
                            <label>Address to</label>
                            <input  type="text" class="form-control" value="${addressDestination}"
                                   name="addressDestination" data-error="That address is invalid">
                          </div>

                        <div class="form-group">
                            <label>Order price</label>
                            <input  type="text" name="price" class="form-control"
                                   value="${price}"
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

<div class="row">

    <div class="span12">

    <div id="map-canvas" class="googleMap"></div>

        </div>
    </div>

<%-- start: Java Script --%>
<%@include file="../parts/scripts.jsp" %>
<script src="<%=application.getContextPath()%>/resources/customer/js/google-maps.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/order.js"></script>
<%-- end: Java Script --%>

<%@include file="../parts/scripts.jsp" %>
<%@include file="../parts/footer.jsp" %>

</body>
</html>