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
    <link href='<%=application.getContextPath()%>/resources/customer/css/visible.css' rel='stylesheet'
          type='text/css'/>
    <link href='<%=application.getContextPath()%>/resources/customer/css/hideBlocks.css' rel='stylesheet'
          type='text/css'/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <link href="<%=application.getContextPath()%>/resources/admin/reports/css/popularDriver.css" type="text/css"
          rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/reports/css/popularCar.css" type="text/css"
          rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/reports/css/musicOverall.css" type="text/css"
          rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/reports/css/additionalOptOverall.css" type="text/css"
          rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/reports/css/musicCustomer.css" type="text/css"
          rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/reports/css/additionalOptCustomer.css" type="text/css"
          rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/customer/css/legendRange.css" rel="stylesheet"/>
    <style type="text/css">
        #container {
            height: 400px;
            min-width: 310px;
            max-width: 800px;
            margin: 0 auto;
        }
    </style>

    <script src="<%=application.getContextPath()%>/resources/js/jquery.min.js"></script>
    <script src="<%=application.getContextPath()%>/resources/customer/chart/js/highcharts.js"></script>
    <script src="<%=application.getContextPath()%>/resources/customer/chart/js/highcharts-3d.js"></script>
    <script src="<%=application.getContextPath()%>/resources/customer/chart/js/modules/exporting.js"></script>

    <script src="<%=application.getContextPath()%>/resources/customer/chartHtml5/Chart.js"></script>

        <style>
            /* basic positioning */
            .legend { list-style: none; }
            .legend li { float: left; margin-right: 10px; }
            .legend span { border: 1px solid #ccc; float: left; width: 12px; height: 12px; margin: 2px; }
            /* your colors */
            .legend .none { background-color: rgb(30,144,255); }
            .legend .nightTariff { background-color: rgb(0,255,255); }
            .legend .weekendTariff { background-color: rgb(129,21,133); }
            .legend .nightWeekendTariff { background-color: rgb(255,127,0); }

        </style>




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
        <div style="width: 60%">
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


<script>
    var randomScalingFactor = function(){ return Math.round(Math.random()*100)};

    var barChartData = {
        labels : ["price per km","minimal price(5km) ","price per min","price per hour"],
        datasets : [
            {
                fillColor : "rgba(30,144,255,0.5)",
                strokeColor : "rgba(220,220,220,0.8)",
                highlightFill: "rgba(30,144,255,0.75)",
                highlightStroke: "rgba(220,220,220,1)",
                data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
            },
            {
                fillColor : "rgba(0,255,255,0.5)",
                strokeColor : "rgba(151,187,205,0.8)",
                highlightFill : "rgba(0,255,255,0.75)",
                highlightStroke : "rgba(151,187,205,1)",
                data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
            },
            {
                fillColor : "rgba(129,21,133,0.5)",
                strokeColor : "rgba(151,187,205,0.8)",
                highlightFill : "rgba(129,21,133,0.75)",
                highlightStroke : "rgba(151,187,205,1)",
                data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
            },
            {
                fillColor : "rgba(255,127,0,0.5)",
                strokeColor : "rgba(151,187,205,0.8)",
                highlightFill : "rgba(255,127,0,0.75)",
                highlightStroke : "rgba(151,187,205,1)",
                data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
            }
        ]

    }
    window.onload = function(){
        var ctx = document.getElementById("canvas").getContext("2d");
        window.myBar = new Chart(ctx).Bar(barChartData, {
            responsive : true
        });
    }

</script>
<%@include file="../parts/scripts.jsp" %>
<%@include file="../parts/footer.jsp" %>
<!-- Load jQuery and bootstrap datepicker scripts -->
<%--<script type="text/javascript" src="<%=application.getContextPath()%>/resources/js/jquery-1.8.3.min.js"--%>
        <%--charset="UTF-8"></script>--%>




</body>
</html>