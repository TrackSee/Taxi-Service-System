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
    <link href="<%=application.getContextPath()%>/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet"
          media="screen">
    <link href="<%=application.getContextPath()%>/resources/customer/css/hideBlocks.css" rel="stylesheet"/>
    <link href="<%=application.getContextPath()%>/resources/customer/css/asteriskRed.css" rel="stylesheet"/>
    <link href="<%=application.getContextPath()%>/resources/customer/css/mapRange.css" rel="stylesheet"/>
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
    <div id="input-form" class="container">
        <div class="title"><h3>Extended Booking Taxi</h3></div>
        <form id="order-form" method="post" action="javascript:void(null);">
            <div class="form-group">
                <label>Phone number</label>
                <input type="text" pattern="^\+?[0-9 ()-]{5,27}$"
                       title="Please enter in this format (+3063) 696-77-00"
                       name="phoneNumber" class="form-control"
                       placeholder="Enter phone number" value="${phoneNumber}"
                       required>
                <span class="red-star">★</span>
            </div>

            <div class="form-group">
                <label>Email</label>
                <input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
                       class="form-control" name="email" placeholder="Enter email"
                       title="That email is invalid" value="${email}" required>
                <span class="red-star">★</span>
            </div>


            <div class="form-group">
                <label>Address from </label>
                <input type="text" id="origin" class="form-control" value=""
                       name="addressOrigin" title="That address is invalid"
                       required onblur="updateRoute()">
                <span class="red-star">★</span>
            </div>


            <div class="form-group">
                <label>Address to</label>
                <input type="text" id="destination" class="form-control" value=""
                       name="addressDestination" title="That address is invalid"
                       required onblur="updateRoute()">
                <span class="red-star" id="importanceAddressDestination">★</span>
            </div>

            <div class="form-group">
                <label class="control-label">Way of payment</label>
                <select class="form-control order_priority" name="wayOfPayment">
                    <option value="CASH">Cash</option>
                    <option value="VISA_CARD">Visa card</option>
                </select>
            </div>

            <div class="form-group">
                <label>Order price</label>
                <input type="text" name="price" class="form-control"
                       value="" id="price"
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
                    <select class="form-control order_priority" name="service" id="service">
                        <option value="SIMPLE_TAXI">Simple taxi</option>
                        <option value="SOBER_DRIVER">Service "Sober driver"</option>
                        <option value="GUEST_DELIVERY">Service "Guest delivery"</option>
                        <option value="CARGO_TAXI">Service "Cargo taxi"</option>
                        <option value="MEET_MY_GUEST">Service "Meet my guest"</option>
                        <option value="CELEBRATION_TAXI"> Service "Celebration taxi"</option>
                        <option value="FOODSTUFF_DELIVERY">Service "Foodstuff delivery"</option>
                        <option value="CONVEY_CORPORATION_EMPLOYEES">Service "Convey corporation employees"</option>
                        <option value="TAXI_FOR_LONG_TERM">Service "Taxi for long term"</option>
                    </select>
                </div>
                    <div class="alert alert-info" role="alert" hidden="hidden" id="addExtraAddresses" style="width:25%">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span></button><h4>
                        To add more addresses from, make double-click on a point on the
                        route where you want to get an extra address.</h4>
                    </div>
                <label for="arriveDate" class="sr-only">Arrive date</label>

                <div class="controls input-append date form_datetime"
                     data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                    <span class="add-on"><i class="icon-th"></i></span>
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <input size="16" type="text" value="" id="arriveDate" name="arriveDate" readonly>
                    <input type="hidden" id="dtp_input1" value=""/><br/>
                </div>

                <div id="amountOfTripTimeBlock">
                    <label>Amount time of trip</label>
                    <div>
                        <input type="number" pattern="[8-9 ()-]{1,25}$" id="amountOfHours"
                               class="form-control" name="amountOfHours"
                               placeholder="Amount of hours 8+"
                               title="Amount of hours 8+">
                        <span class="red-star">★</span>
                    </div>
                    <div>
                        <select class="form-control order_priority" name="amountOfMinutes" id="amountOfMinutes">
                            <option value="0">0  minutes</option>
                            <option value="5">5  minutes</option>
                            <option value="10">10 minutes</option>
                            <option value="15">15 minutes</option>
                            <option value="20">20 minutes</option>
                            <option value="25">25 minutes</option>
                            <option value="30">30 minutes</option>
                            <option value="35">35 minutes</option>
                            <option value="40">40 minutes</option>
                            <option value="45">45 minutes</option>
                            <option value="50">50 minutes</option>
                            <option value="55">55 minutes</option>
                            <option value="60">60 minutes</option>

                        </select>
                    </div>

                </div>
                <%--<div>--%>
                    <%--<input type="number" pattern="[8-9 ()-]{1,25}$" id="amountOfMinutes" class="form-control" --%>
                           <%--name="amountOfMinutes"--%>
                           <%--placeholder="Amount minutes [0:60]"--%>
                           <%--title="Amount of minutes [0:60]" value="${amountOfMinutes}">--%>
                    <%--<span class="red-star">★</span>--%>
                <%--</div>--%>

                <div id="amountOfCarsBlock">
                    <label>Amount of cars</label>
                    <input type="number" pattern="[5-9 ()-]{1,25}$"
                           id="amountOfCars" class="form-control" name="amountOfCars"
                           placeholder="Amount of cars 5+"
                           title="Amount of cars 5+">
                    <span class="red-star">★</span>
                </div>
                <div class="form-group" id="carCategoryGroup">
                    <label class="control-label" for="carCategory">Car category</label>
                    <select class="form-control order_priority" name="carCategory" id="carCategory">
                        <option value="ECONOMY_CLASS">Economy class</option>
                        <option value="BUSINESS_CLASS">Business class</option>
                        <option value="VAN">Van</option>
                    </select>
                </div>

                <div class="form-group">
                    <label class="control-label">Driver sex</label>
                    <select class="form-control order_priority" name="driverSex">
                        <option value="A">Anyone</option>
                        <option value="M">Male</option>
                        <option value="F">Female</option>
                    </select>
                </div>
                <div class="form-group" id="musicStyleGroup">
                    <label class="control-label">Music style</label>
                    <select class="form-control order_priority" name="musicStyle">
                        <option value="ANY">Any</option>
                        <option value="BLUES">Blues</option>
                        <option value="CLASSICAL_MUSIC">Classical music</option>
                        <option value="ROCK">Rock</option>
                        <option value="JAZZ">Jazz</option>
                        <option value="DANCE_MUSIC">Dance music</option>
                        <option value="ELECTRONIC_MUSIC">Electronic music</option>
                        <option value="HIP_HOP">Hip Hop</option>
                        <option value="OTHER">Other</option>
                    </select>
                </div>


                <div class="checkbox" id="animalTransportationCh">
                    <label>
                        <input type="checkbox" name="animalTransportation">
                        Animal transportation
                    </label>
                </div>
                <div class="checkbox" id="freeWifiCh">
                    <label>
                        <input type="checkbox" name="freeWifi"> Free Wi-Fi
                    </label>
                </div>
                <div class="checkbox" id="nonSmokingDriverCh">
                    <label>
                        <input type="checkbox" name="nonSmokingDriver"> Non smoking driver
                    </label>
                </div>
                <div class="checkbox" id="airConditionerCh">
                    <label>
                        <input type="checkbox" name="airConditioner"> Air conditioner
                    </label>
                </div>

                Description:<br/>
                <textarea name="description" id="description" rows="4" cols="50" title=""></textarea>
                <br/>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-success btn-large">Submit</button>
            </div>

        </form>
    </div>
</div>
<%-- end:wrapper --%>

<div class="row">

    <div class="span12">

        <div id="map-canvas" class="googleMap"></div>

    </div>
</div>

<%@include file="../parts/footer.jsp" %>
<%@include file="../parts/scripts.jsp" %>

<%-- Load bootstrap datepicker scripts --%>
<script src="<%=application.getContextPath()%>/resources/js/bootstrap-datetimepicker.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/date-picker-order-complete.js"></script>
<%--end bootstrap datepicker scripts--%>

<%-- price list JS injection --%>
<script>
    function getPriceList() { return ${requestScope.priceList}; }
    function getMinDistance() { return ${requestScope.minimalOrderDistance}; }
</script>
<%-- END price list JS injection --%>
<%--Google maps scripts--%>
<script src="<%=application.getContextPath()%>/resources/js/maps/google-maps-loader.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/order-maps.js"></script>
<%--end google maps scripts--%>

<%-- Order page scripts --%>
<script src="<%=application.getContextPath()%>/resources/customer/js/order.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/slide-panel.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/order-functionality.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/fields-generator.js"></script>
<%--end order page scripts--%>
</body>
</html>