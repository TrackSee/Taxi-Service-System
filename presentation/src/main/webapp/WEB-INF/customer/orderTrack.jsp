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
    <div class="container">
        <div class="title"><h3>Extended Booking Taxi</h3></div>
        <form method="post" action="<c:url value="/updateOrder"/>">
            <label id="hideTrackingNumberSecond">
                <input type="text" name="trackingNumber" value="${trackingNumber}" >
            </label>
            <div class="form-group">
                <label>Phone number</label>
                <input type="text" name="phoneNumber" class="form-control" placeholder="Enter phone number:"
                       value="${phoneNumber}"  readonly>
            </div>

            <div class="form-group">
                <label>Email</label>
                <input type="email" class="form-control" name="email" placeholder="Enter email"
                       value="${email}" data-error="That email is invalid" readonly>
            </div>

            <div class="form-group">
                <label>Address from</label>
                <input type="text" id="origin" class="form-control" value="${addressOrigin}"
                       name="addressOrigin"  required onblur="updateRoute()">
                <span class="red-star">★</span>
            </div>

            <div class="form-group">
                <label>Address to</label>
                <input type="text" id="destination" class="form-control" value="${addressDestination}"
                       name="addressDestination" required onblur="updateRoute()">
                <span class="red-star">★</span>
            </div>
            <div class="form-group">
                <label class="control-label">Way of payment</label>
                <select class="form-control order_priority" name="wayOfPayment">
                    <option value="CASH" ${CASH}>Cash</option>
                    <option value="VISA_CARD" ${VISA_CARD}>Visa card</option>
                </select>
            </div>
            <div class="form-group">
                <label>Order price</label>
                <input type="text" name="price" class="form-control" value="${price}" readonly>
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

                <div class="controls input-append date form_datetime"
                     data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                    <span class="add-on"><i class="icon-th"></i></span>
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <input size="16" type="text" value="${arriveDate}" id="arriveDate" name="arriveDate" readonly>
                    <input type="hidden" id="dtp_input1" value=""/><br/>

                </div>
                <div id="endDateBlock">
                    <label  class="sr-only">End date</label>

                    <div class="controls input-append date form_datetime"
                         data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1">
                        <span class="add-on"><i class="icon-th"></i></span>
                        <span class="add-on"><i class="icon-remove"></i></span>
                        <input size="16" type="text" value="${endDate}" id="endDate" name="endDate" readonly>
                        <input type="hidden" id="dtp_input2" value=""/><br/>
                    </div>
                    </div>
                <div class="form-group" id="carCategoryGroup">
                    <label class="control-label">Car category</label>
                    <select class="form-control order_priority" name="carCategory">
                        <option value="ECONOMY_CLASS" ${ECONOMY_CLASS}>Economy class</option>
                        <option value="BUSINESS_CLASS" ${BUSINESS_CLASS}}>Business class</option>
                        <option value="VAN" ${VAN}>Van</option>
                    </select>
                </div>

                <div class="form-group">
                    <label class="control-label">Driver sex</label>
                    <select class="form-control order_priority" name="driverSex" >
                        <option value="A" ${A}>Anyone</option>
                        <option value="M" ${M}>Male</option>
                        <option value="F" ${F}>Female</option>
                    </select>
                </div>
                <div class="form-group" id="musicStyleGroup">
                    <label class="control-label">Music style</label>
                    <select class="form-control order_priority" name="musicStyle" >
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
                        <input type="checkbox" name="animalTransportation" ${animalTransportation} >
                        Animal transportation
                    </label>
                </div>
                <div class="checkbox" id="freeWifiCh">
                    <label>
                        <input type="checkbox" name="freeWifi" ${freeWifi} > Free wi-fi
                    </label>
                </div>
                <div class="checkbox" id="smokingDriverCh">
                    <label>
                        <input type="checkbox" name="smokingDriver" ${smokingDriver}> Smoking driver
                    </label>
                </div>
                <div class="checkbox" id="airConditionerCh">
                    <label>
                        <input type="checkbox" name="airConditioner" ${airConditioner}> Air conditioner
                    </label>
                </div>

                Description:<br/>
                <textarea name="description" id="description" rows="4" cols="50" title="">${description}
                </textarea>
                <br/>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-success btn-large">Change order</button>
            </div>
        </form>
            <form method="post" action="<c:url value="/orderRefuse"/>">
                <label id="hideTrackingNumber">
                    <input type="text" name="trackingNumber" value="${trackingNumber}" >
                </label>
                <div class="form-group">
                    <button type="submit" class="btn btn-danger btn-large">Refuse order</button>
                </div>
            </form>
    </div>
    </p>
</div>
<%-- end:wrapper --%>
</div>

<div class="row">

    <div class="span12">

        <div id="map-canvas" class="googleMap"></div>

    </div>
</div>
<p>d</p>
<!-- Load jQuery and bootstrap datepicker scripts -->
<script type="text/javascript" src="<%=application.getContextPath()%>/resources/js/jquery-1.8.3.min.js"
        charset="UTF-8"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/resources/js/bootstrap-datetimepicker.js"
        charset="UTF-8"></script>
<script type="text/javascript"
        src="<%=application.getContextPath()%>/resources/js/locales/bootstrap-datetimepicker.fr.js"
        charset="UTF-8"></script>
<script type="text/javascript"
        src="<%=application.getContextPath()%>/resources/js/locales/bootstrap-datetimepicker.fr.js"
        charset="UTF-8"></script>
<script type="text/javascript"
        src="<%=application.getContextPath()%>/resources/js/date-picker-order-complete.js"
        charset="UTF-8"></script>
<script src="<%=application.getContextPath()%>http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<%--end jQuery and bootstrap datepicker scripts--%>

<%--Google maps scripts--%>
<script src="<%=application.getContextPath()%>/resources/customer/js/maps/google-maps-loader.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/maps/order-maps.js"></script>
<%--end google maps scripts--%>

<%-- Order page scripts --%>
<script src="<%=application.getContextPath()%>/resources/customer/js/order.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/cargoTaxi.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/sober-driver.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/meet-guest.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/food-delivery.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/slide-panel.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/other-services.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/longTerm.js"></script>
<%--end order oage scripts--%>

<%@include file="../parts/scripts.jsp" %>
<%@include file="../parts/footer.jsp" %>
</body>
</html>