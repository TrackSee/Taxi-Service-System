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
        <form method="post" action="<c:url value="/reportComplicate"/>">
            <button type="submit" class="btn btn-info btn-large">Get price list report</button>
        </form>
        <dt >Price<dt>
        <div id="canvasBlock">
            <canvas id="canvas" height="450" width="600"></canvas>
        </div>
        <ul class="legend">
            <li><span class="none"></span> Without additional tariffs</li><br>
            <li><span class="nightTariff"></span> With night tariff</li><br>
            <li><span class="weekendTariff"></span> With weekend tariff</li><br>
            <li><span class="nightWeekendTariff"></span> With weekend and night tariff</li>
        </ul>
        </div>
</div>

<script src="<%=application.getContextPath()%>/resources/js/jquery.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/chartHtml5/Chart.js"></script>
<script src="<%=application.getContextPath()%>/resources/customer/js/priceChart.js"></script>
<%@include file="../parts/scripts.jsp" %>
<%@include file="../parts/footer.jsp" %>

</body>
</html>