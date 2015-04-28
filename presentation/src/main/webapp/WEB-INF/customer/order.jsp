<%--

  author: Sharaban Sasha
  Date: 19.04.15
  Time: 20:37
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
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

<p id="error-label"></p>

<div id="map-canvas" style="width:75%; height:75%; position: absolute; float: left"></div>

<%-- start: Java Script --%>
<%@include file="../parts/scripts.jsp" %>
<script src="<%=application.getContextPath()%>/resources/customer/js/google-maps.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/order.js"></script>
<%-- end: Java Script --%>
<%@include file="../parts/scripts.jsp" %>
<%@include file="../parts/footer.jsp" %>

</body>
</html>