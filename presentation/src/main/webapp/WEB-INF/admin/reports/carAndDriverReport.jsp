<%--
  Created by IntelliJ IDEA.
  User: Vadym Akymov
  Date: 04.05.15
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
  <title>Reports</title>
  <!-- Bootstrap Core CSS -->
  <link href="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css"
        rel="stylesheet">

  <!-- MetisMenu CSS -->
  <link href="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.css"
        rel="stylesheet">

  <!-- Custom CSS -->
  <link href="<%=application.getContextPath()%>/resources/admin/dist/css/sb-admin-2.css" rel="stylesheet">

  <!-- Custom Fonts -->
  <link href="<%=application.getContextPath()%>/resources/admin/bower_components/font-awesome/css/font-awesome.min.css"
        rel="stylesheet" type="text/css">
  <link href="<%=application.getContextPath()%>/resources/admin/reports/css/report.css" type="text/css" rel="stylesheet">

  <link href="http://amcharts.com/lib/3/plugins/export/export.css" rel="stylesheet" type="text/css">
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
<div id="wrapper">
  <jsp:include page="/WEB-INF/admin/adminHeader.jsp"/>

  <div id="page-wrapper">
    <h3>Driver sex popularity</h3>
    <div id="driverDiv"></div>
    <h3>Car category popularity</h3>
    <div id="carDiv"></div>
  </div>
</div>


<!-- jQuery -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="http://www.amcharts.com/lib/3/amcharts.js"></script>
<script type="text/javascript" src="http://www.amcharts.com/lib/3/pie.js"></script>
<script type="text/javascript" src="http://www.amcharts.com/lib/3/themes/light.js"></script>
<script src="http://amcharts.com/lib/3/plugins/export/export.js" type="text/javascript"></script>
<script src="<%=application.getContextPath()%>/resources/admin/reports/js/driverAndCarReport.js"/>

<!-- Bootstrap Core JavaScript -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=application.getContextPath()%>/resources/admin/dist/js/sb-admin-2.js"></script>
<%--<script src="<%=application.getContextPath()%>/resources/admin/reports/amcharts/themes/light.js"/>--%>

<%--<script src="<%=application.getContextPath()%>/resources/admin/reports/amcharts/pie.js"/>--%>
</body>
</html>
