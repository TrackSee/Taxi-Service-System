<%--

  author: Sharaban Sasha
  Date: 19.04.15
  Time: 20:37
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="../parts/meta.jsp" %>
    <%@include file="../parts/bootstrap2.jsp" %>
    <link href="<%=application.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"
          media="screen">
    <link href="<%=application.getContextPath()%>/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet"
          media="screen">
    <link href="<%=application.getContextPath()%>/resources/customer/css/hideBlocks.css" rel="stylesheet"
          type="text/css"/>
    <link href="<%=application.getContextPath()%>/resources/customer/css/asteriskRed.css" rel="stylesheet"
          type="text/css"/>
    <link href='<%=application.getContextPath()%>/resources/customer/css/visible.css' rel='stylesheet'
          type='text/css'/>
    <link href="<%=application.getContextPath()%>/resources/customer/css/mapRange.css" rel="stylesheet"/>
</head>
<body>
<%@include file="../parts/header.jsp" %>

<!-- start: Page Title -->
<div id="page-title">

    <div id="page-title-inner">

        <!-- start: Container -->
        <div class="container">

            <h2><i class="ico-settings ico-white"></i>Order tracking</h2>

        </div>
        <!-- end: Container  -->

    </div>

</div>
<!-- end: Page Title -->

<!--start: Wrapper-->
<div id="wrapper">
    <!--start: Container -->
    <div id="input-form" class="container">
        <div class="title"><h3>Extended Booking Taxi</h3></div>

            <div class="form-group">
                <label>Phone number</label>
                <input type="text" name="phoneNumber" class="form-control"
                       value="${phoneNumber}"  readonly>
            </div>

            <div class="form-group">
                <label>Email</label>
                <input type="email" class="form-control" name="email"
                       value="${email}" data-error="That email is invalid" readonly>
            </div>

            <div class="form-group">
                <label>Address from</label>
                <input type="text" class="form-control" value="${addressOrigin}"
                       name="addressOrigin" data-error="That address is invalid"
                       readonly >
            </div>

        <div class="form-group">
            <label>Address to</label>
            <input type="text" id="destination" class="form-control" value="${addressDestination}"
                   name="addressDestination" required onblur="updateRoute()">
            <span class="red-star">★</span>
        </div>
        <div class="form-group">
            <label class="control-label">Way of payment</label>
            <select class="form-control order_priority" name="wayOfPayment" disabled>
                <option value="CASH" ${CASH}>Cash</option>
                <option value="VISA_CARD" ${VISA_CARD}>Visa card</option>
            </select>
        </div>
        <div class="form-group">
            <label>Order price</label>
            <input type="text" name="price" class="form-control"
                   value="${price}"
                   data-error="That address is invalid" readonly>
        </div>

        <div id="flip">
            <div class="form-group">
                <p>
                    <button type="button" class="btn btn-info turnButton">Additional options</button>
                </p>
            </div>
        </div>
        <div id="panel">
            <div class="form-group">
                <label class="control-label">Service</label>
                <select class="form-control order_priority" name="service" id="service" disabled>
                    <option value="SIMPLE_TAXI"${SIMPLE_TAXI}>Simple taxi</option>
                    <option value="SOBER_DRIVER"${SOBER_DRIVER}>Service "Sober driver"</option>
                    <option value="GUEST_DELIVERY"${GUEST_DELIVERY}>Service "Guest delivery"</option>
                    <option value="CARGO_TAXI"${CARGO_TAXI}>Service "Cargo taxi"</option>
                    <option value="MEET_MY_GUEST"${MEET_MY_GUEST}>Service "Meet my guest"</option>
                    <option value="CELEBRATION_TAXI"${CELEBRATION_TAXI}> Service "Celebration taxi"</option>
                    <option value="foodStuffDelivery"${FOODSTUFF_DELIVERY}>Service "Foodstuff delivery"</option>
                    <option value="CONVEY_CORPORATION_EMPLOYEES"${CONVEY_CORPORATION_EMPLOYEES}>
                        Service "Convey corporation employees"</option>
                    <option value="TAXI_FOR_LONG_TERM"${TAXI_FOR_LONG_TERM}>
                        Service "Taxi for long term"</option>
                </select>
            </div>
            <label for="arriveDate" class="sr-only">Arrive date</label>
            <input size="16" type="text"  id="arriveDate" name="arriveDate" value="${arriveDate}"
                   disabled>

            <div id="amountOfTripTimeBlock">
                <label>Amount time of trip</label>
                <div>
                    <input type="number" id="amountOfHours" class="form-control" name="amountOfHours"
                           value="${amountOfHours}" disabled>
                </div>
                <div>
                    <input type="number" id="amountOfMinutes" class="form-control" name="amountOfMinutes"
                           value="${amountOfMinutes}" disabled>
                </div>
            </div>

            <%--TODO validation--%>
            <div id="amountOfCarsBlock">
                <label>Amount of cars</label>
                <input type="number" id="amountOfCars" class="form-control" name="amountOfCars"
                       value="${amountOfCars}" disabled>
            </div>
            <div class="form-group" id="carCategoryGroup">
                <label class="control-label">Car category</label>
                <select class="form-control order_priority" name="carCategory" disabled>
                    <option value="ECONOMY_CLASS" ${ECONOMY_CLASS}>Economy class</option>
                    <option value="BUSINESS_CLASS" ${BUSINESS_CLASS}>Business class</option>
                    <option value="VAN" ${VAN}>Van</option>
                </select>
            </div>

            <div class="form-group">
                <label class="control-label">Driver sex</label>
                <select class="form-control order_priority" name="driverSex" disabled>
                    <option value="A" ${A}>Anyone</option>
                    <option value="M" ${M}>Male</option>
                    <option value="F" ${F}>Female</option>
                </select>
            </div>
            <div class="form-group" id="musicStyleGroup">
                <label class="control-label">Music style</label>
                <select class="form-control order_priority" name="musicStyle" disabled>
                    <option value="ANY" ${ANY}>Any</option>
                    <option value="BLUES" ${BLUES}>Blues</option>
                    <option value="CLASSICAL_MUSIC" ${CLASSICAL_MUSIC}>Classical music</option>
                    <option value="ROCK" ${ROCK}>Rock</option>
                    <option value="JAZZ" ${JAZZ}>Jazz</option>
                    <option value="DANCE_MUSIC" ${DANCE_MUSIC}>Dance music</option>
                    <option value="ELECTRONIC_MUSIC" ${ELECTRONIC_MUSIC}>Electronic music</option>
                    <option value="HIP_HOP" ${HIP_HOP}>Hip Hop</option>
                    <option value="OTHER" ${Other}>Other</option>
                </select>
            </div>


            <div class="checkbox" id="animalTransportationCh">
                <label>
                    <input type="checkbox" name="animalTransportation" ${animalTransportation} disabled>
                    Animal transportation
                </label>
            </div>
            <div class="checkbox" id="freeWifiCh">
                <label>
                    <input type="checkbox" name="freeWifi" ${freeWifi} disabled> Free Wi-Fi
                </label>
            </div>
            <div class="checkbox" id="smokingDriverCh">
                <label>
                    <input type="checkbox" name="smokingDriver" ${smokingDriver} disabled> Smoking driver
                </label>
            </div>
            <div class="checkbox" id="airConditionerCh">
                <label>
                    <input type="checkbox" name="airConditioner" ${airConditioner} disabled> Air conditioner
                </label>
            </div>

            Description:<br/>
                <textarea name="description" id="description" rows="4" cols="50" title="" readonly>${description}
                </textarea>
            <br/>
        </div>
</div>
<%-- end:wrapper --%>
</div>

<div class="row">

    <div class="span12">

        <div id="map-canvas" class="googleMap"></div>

    </div>
</div>

<%@include file="../parts/footer.jsp" %>
<%@include file="../parts/scripts.jsp" %>

<!-- Load bootstrap datepicker scripts -->
<script src="<%=application.getContextPath()%>/resources/js/bootstrap-datetimepicker.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/locales/bootstrap-datetimepicker.fr.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/date-picker-order-complete.js"></script>
<%--end bootstrap datepicker scripts--%>

<%--Google maps scripts--%>
<script src="<%=application.getContextPath()%>/resources/js/maps/google-maps-loader.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/order-maps.js"></script>
<%--end google maps scripts--%>

<%-- Order page scripts --%>
<script src="<%=application.getContextPath()%>/resources/customer/js/order.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/slide-panel.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/order-functionality.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/order-functionality-prepared.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/fields-generator.js"></script>
<%--end order page scripts--%>
<script src="http://maps.google.com/maps/api/js?sensor=false&libraries=geometry"></script>
</body>
</html>