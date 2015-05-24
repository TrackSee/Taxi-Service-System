<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%--<%@include file="/WEB-INF/parts/meta.jsp"%>--%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <link href="<%=application.getContextPath()%>/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet"
          media="screen">
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.css"
          rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/dist/css/sb-admin-2.css" rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/css/formStyle.css" rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <script type="text/javascript" src="http://www.amcharts.com/lib/3/amcharts.js"></script>
    <script type="text/javascript" src="http://www.amcharts.com/lib/3/serial.js"></script>
    <script type="text/javascript" src="http://www.amcharts.com/lib/3/themes/light.js"></script>
    <script type="text/javascript" src="http://amcharts.com/lib/3/plugins/export/export.js"></script>
    <link href="http://amcharts.com/lib/3/plugins/export/export.css" rel="stylesheet" type="text/css">
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <jsp:include page="/WEB-INF/admin/adminHeader.jsp"/>
    <div id="page-wrapper">
        <form class="serviceProfit" id="serviceProfit" action="<c:url value="/admin/report/profitableservice"/>"
              method="post">
            <h3 class="form-sign-up-heading">Please enter period to get result be service profitable</h3>

            <h4 id="start"> Start Date
                <input type="text" class="date start" id="startDate" name="startDate"/>
            </h4>

            <h4 id="end"> End Date
                <input type="text" class="date end" id="endDate" name="endDate"/>
            </h4>

            <div>
                <button id="getResult" type="button" class="btn btn-primary">Get Result</button>
            </div>
        </form>

        <div id="chartdiv"></div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<script src="https://jonthornton.github.io/jquery-timepicker/jquery.timepicker.js"></script>
<link rel="stylesheet" type="text/css" href="https://jonthornton.github.io/jquery-timepicker/jquery.timepicker.css"/>

<script src="<%=application.getContextPath()%>/resources/datepicker/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/resources/datepicker/css/bootstrap-datepicker.css"/>
<script src="<%=application.getContextPath()%>/resources/datepicker/js/Datepair.js"></script>
<script src="<%=application.getContextPath()%>/resources/datepicker/js/jquery.datepair.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/reports/js/dataPicker.js"/>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="http://www.amcharts.com/lib/3/amcharts.js"></script>
<script type="text/javascript" src="http://www.amcharts.com/lib/3/pie.js"></script>
<script type="text/javascript" src="http://www.amcharts.com/lib/3/themes/light.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/reports/js/serviceProfitReport.js"/>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/dist/js/sb-admin-2.js"></script>
<script type="text/javascript" src="http://amcharts.com/lib/3/plugins/export/export.js"></script>
<link href="http://amcharts.com/lib/3/plugins/export/export.css" rel="stylesheet" type="text/css">

</body>

</html>