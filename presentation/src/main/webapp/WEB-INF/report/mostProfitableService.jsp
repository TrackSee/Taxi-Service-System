<%--
  Created by Katia Stetsiuk
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.css"
          rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/dist/css/sb-admin-2.css" rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/css/chart.css" rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css">
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <script type="text/javascript" src="http://www.amcharts.com/lib/3/amcharts.js"></script>
    <script type="text/javascript" src="http://www.amcharts.com/lib/3/serial.js"></script>
    <script type="text/javascript" src="http://www.amcharts.com/lib/3/themes/light.js"></script>
    <script type="text/javascript" src="http://www.amcharts.com/lib/3/plugins/export/export.js"></script>
</head>

<body>
<div id="wrapper">
    <jsp:include page="adminHeader.jsp"/>
    <div id="page-wrapper">
        <form class="serviceProfit" id="serviceProfit" action="<c:url value="/admin/report/profitableservice"/>"
              method="post">
            <h2 class="form-sign-up-heading">Please enter period to get result be service profitable</h2>

            <p>Start Date</p>
            <input type="startDate" id="startDate" name="startDate" class="form-control" placeholder="year-month-day">

            <p>End Date</p>
            <input type="endDate" id="endDate" name="endDate" class="form-control" placeholder="year-month-day">

            <div>
                <button id="getResult" type="button" class="btn btn-primary">Get Result</button>
            </div>
        </form>
        <p>Report</p>

        <div id="chartdiv"></div>
    </div>
</div>

<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="http://www.amcharts.com/lib/3/amcharts.js"></script>
<script type="text/javascript" src="http://www.amcharts.com/lib/3/pie.js"></script>
<script type="text/javascript" src="http://www.amcharts.com/lib/3/themes/light.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/reports/js/serviceProfit.js"/>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js">
</script>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.js">
</script>
<script src="<%=application.getContextPath()%>/resources/admin/dist/js/sb-admin-2.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>
</body>
</html>


