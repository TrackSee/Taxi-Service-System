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
                            <label>Phone number</label>
                            <input type="text" name="phoneNumber" class="form-control"  placeholder="Enter phone number:"
                                   required>
                        </div>

                        <div class="form-group">
                            <label>Email</label>
                            <input type="email"  class="form-control" name="email" placeholder="Enter email"
                                   data-error="That email is invalid" required>
                        </div>

                        <div class="form-group">
                            <label>Address from</label>
                            <input   type="text"  id="origin" class="form-control" value="${addressOrigin}"
                                    name="addressOrigin" data-error="That address is invalid"
                                    required onblur="updateRoute()">
                        </div>

                        <div class="form-group">
                            <label>Address to</label>
                            <input  type="text" id="destination" class="form-control" value="${addressDestination}"
                                   name="addressDestination" data-error="That address is invalid"
                                   required onblur="updateRoute()">
                          </div>

                        <div class="form-group">
                            <label>Order price</label>
                            <input  type="text" name="price" class="form-control"
                                   value="${price}"
                                   data-error="That address is invalid" readonly>
                        </div>
                        <p>
                        <div>
                                <label>Time of arrival Taxi </label>
                                <input name="arriveDate" id="dt1" class='form-control'>

                                  </div>
                        </p>
                        <p>
                        <div>
                            <label>End Time travel </label>
                            <input  name="endDate" id="dt2" class='form-control'>

                        </div>
                        </p>
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
                                    <option value="anyone">Anyone</option>
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
                                <label for="animalTransportation">
                                    <input type="checkbox" name="animalTransportation" id="animalTransportation">
                                    Animal transportation
                                </label>
                            </div>
                            <div class="checkbox">
                                <label for="freeWifi">
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

                        Description:<br />
                        <textarea name="description" id="description" rows="4" cols="50"></textarea>

                        </textarea></p><br/>



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



    <script src='<%=application.getContextPath()%>/resources/dist/rome.js'></script>
    <script src='<%=application.getContextPath()%>/resources/example/example.js'></script>
    <link href='<%=application.getContextPath()%>/resources/dist/rome.css' rel='stylesheet' type='text/css' />
    <link href='<%=application.getContextPath()%>/resources/example/example.css' rel='stylesheet' type='text/css' />


<!-- Load jQuery and bootstrap datepicker scripts -->
<script src="<%=application.getContextPath()%>/resources/js/jquery-1.9.1.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/bootstrap-datepicker.js"></script>


<%-- start: Java Script --%>
<%@include file="../parts/scripts.jsp" %>

<script src="<%=application.getContextPath()%>/resources/customer/js/google-maps.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/order.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/soberDriver.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/meetMyGuest.js"></script>
<%-- end: Java Script --%>
<script src="<%=application.getContextPath()%>http://code.jquery.com/jquery-1.9.1.js"></script>

<%@include file="../parts/scripts.jsp" %>
<%@include file="../parts/footer.jsp" %>


</body>
</html>