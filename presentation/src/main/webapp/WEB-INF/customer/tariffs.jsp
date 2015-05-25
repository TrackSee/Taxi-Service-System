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
    <link href="<%=application.getContextPath()%>/resources/customer/css/chart.css" rel="stylesheet"/>
</head>
<body>
<%@include file="../parts/header.jsp" %>
<!-- start: Page Title -->
<div id="page-title">
    <div id="page-title-inner">
        <!-- start: Container -->
        <div class="container">
            <h2><i class="ico-stats ico-white"></i>Tariffs</h2>
        </div>
        <!-- end: Container  -->
    </div>
</div>
<!-- end: Page Title -->
<!--start: Wrapper-->
<div id="wrapper">
    <!--start: Container -->
    <div class="container">

        <div class="title"><h3>Tariffs</h3></div>

        <dt >Price<dt>
        <div id="canvasBlock">
            <canvas id="canvas" height="450" width="600"></canvas>
        </div>
        <ul class="legend">
            <li><span class="none"></span> Without additional tariffs</li><br>
            <li><span class="nightTariff"></span> With night tariff</li><br>
            <li><span class="weekendTariff"></span> With weekend tariff</li><br>
            <li><span class="nightWeekendTariff"></span> With weekend and night tariff</li>
            <div style="padding-top: 30%">
            <form method="post" action="<c:url value="/tariffsCarCategory"/>">
                <div hidden="hidden">
                    <input type="text" name="carCategory" value="economyClass" />
                </div>
                <button type="submit" class="btn btn-info btn-large">Get chart for economy class</button>
            </form>
            <form method="post" action="<c:url value="/tariffsCarCategory"/>">
                <div hidden="hidden">
                    <input type="text" name="carCategory" value="businessClass" />
                </div>
                <button type="submit" class="btn btn-info btn-large">Get chart for business class</button>
            </form>
            <form method="post" action="<c:url value="/tariffsCarCategory"/>">
                <div hidden="hidden">
                    <input type="text" name="carCategory" value="van" />
                </div>
                <button type="submit" class="btn btn-info btn-large">Get chart for van class</button>
            </form>
                </div>
        </ul>

        <form method="post" action="<c:url value="/reportComplicate"/>">
            <button type="submit" class="btn btn-success btn-large">Tariffs in Excel</button>
        </form>


        </div>
</div>

<div hidden="hidden">

    <input type="text" id="pricePerKmNight" value="${pricePerKmNight}" />
    <input type="text" id="minPriceNight" value="${minPriceNight}" />
    <input type="text" id="pricePerMinNight" value="${pricePerMinNight}" />
    <input type="text" id="pricePerHourNight" value="${pricePerHourNight}" />

    <input type="text" id="pricePerKmWeekend" value="${pricePerKmWeekend}" />
    <input type="text" id="minPriceWeekend" value="${minPriceWeekend}" />
    <input type="text" id="pricePerMinWeekend" value="${pricePerMinWeekend}" />
    <input type="text" id="pricePerHourWeekend" value="${pricePerHourWeekend}" />

    <input type="text" id="pricePerKmNone" value="${pricePerKmNone}" />
    <input type="text" id="minPriceNone" value="${minPriceNone}" />
    <input type="text" id="pricePerMinNone" value="${pricePerMinNone}" />
    <input type="text" id="pricePerHourNone" value="${pricePerHourNone}" />

    <input type="text" id="pricePerKmNightWeekend" value="${pricePerKmNightWeekend}" />
    <input type="text" id="minPriceNightWeekend" value="${minPriceNightWeekend}" />
    <input type="text" id="pricePerMinNightWeekend" value="${pricePerMinNightWeekend}" />
    <input type="text" id="pricePerHourNightWeekend" value="${pricePerHourNightWeekend}" />


</div>

<script src="<%=application.getContextPath()%>/resources/js/jquery.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/chartHtml5/Chart.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/priceChart.js"></script>
<%@include file="../parts/scripts.jsp" %>
<%@include file="../parts/footer.jsp" %>

</body>
</html>