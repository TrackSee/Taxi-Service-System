<%--
  Created by IntelliJ IDEA.
  User: Vadym Akymov
  Date: 15.04.15
  Time: 1:37
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.css"
          rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/css/chart.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
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
        <form class="popularOpt" id="popularOpt"  action="<c:url value="/admin/report/mostpopularopt"/>" method="post">
            <div class="userChoose">
                <div>
                    <label>Choose user for get result</label>
                </div>
                <select class="make form-control taxiCar" name="user">
                    <c:forEach items="${requestScope.users}" var="user">
                        <option value="${user.userId}">${user.email}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <button id="getResult" type="button" class="btn btn-primary">Get Result</button>
            </div>
        </form>
        <p>Report</p>
        <div id="chartdiv"></div>
    </div>
</div>


<script src="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>
<script type="text/javascript" src="http://www.amcharts.com/lib/3/amcharts.js"></script>
<script type="text/javascript" src="http://www.amcharts.com/lib/3/pie.js"></script>
<script type="text/javascript" src="http://www.amcharts.com/lib/3/themes/light.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/dist/js/sb-admin-2.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/reports/js/popularOptions.js"/><!-- Bootstrap Core JavaScript -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://amcharts.com/lib/3/plugins/export/export.js"></script>
<link href="http://amcharts.com/lib/3/plugins/export/export.css" rel="stylesheet" type="text/css">

<%--<script src="<%=application.getContextPath()%>/resources/admin/dist/js/sb-admin-2.js"></script>--%>
<%--<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>--%>
<%--<script src="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>--%>
<%--<script type="text/javascript" src="http://www.amcharts.com/lib/3/amcharts.js"></script>--%>
<%--<script type="text/javascript" src="http://www.amcharts.com/lib/3/pie.js"></script>--%>
<%--<script type="text/javascript" src="http://www.amcharts.com/lib/3/themes/light.js"></script>--%>
<%--<script src="<%=application.getContextPath()%>/resources/admin/reports/js/popularOptions.js"/><!-- Bootstrap Core JavaScript -->--%>
<%--<script src="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>--%>

<%--<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>--%>
<%--<script type="text/javascript" src="http://www.amcharts.com/lib/3/amcharts.js"></script>--%>
<%--<script type="text/javascript" src="http://www.amcharts.com/lib/3/pie.js"></script>--%>
<%--<script type="text/javascript" src="http://www.amcharts.com/lib/3/themes/light.js"></script>--%>
<%--<script src="<%=application.getContextPath()%>/resources/admin/reports/js/serviceProfit.js"/><!-- Bootstrap Core JavaScript -->--%>
<%--<script src="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>--%>
<%--<script src="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>--%>
<%--<script src="<%=application.getContextPath()%>/resources/admin/dist/js/sb-admin-2.js"></script>--%>

<%--<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>--%>
<%--<script src="<%=application.getContextPath()%>/resources/admin/reports/js/serviceProfit.js"/><!-- Bootstrap Core JavaScript -->--%>
<%--<script src="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>--%>
<%--<script src="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>--%>
<%--<script src="<%=application.getContextPath()%>/resources/admin/dist/js/sb-admin-2.js"></script>--%>
<%--<script type="text/javascript" src="http://www.amcharts.com/lib/3/amcharts.js"></script>--%>
<%--<script type="text/javascript" src="http://www.amcharts.com/lib/3/pie.js"></script>--%>
<%--<script type="text/javascript" src="http://www.amcharts.com/lib/3/themes/light.js"></script>--%>

</body>

</html>
