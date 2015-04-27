<%--
  Created by IntelliJ IDEA.
  User: kstes_000
  Date: 25-Apr-15
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

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

    <!-- DataTables CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
          rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/datatables-responsive/css/dataTables.responsive.css"
          rel="stylesheet">

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
    <![endif]-->


</head>


<body>

<div id="wrapper">
    <jsp:include page="adminHeader.jsp"/>
</div>
<div class="container" style=" width : 500px; ">

    <form class="create-driver" id="updateItDriver" action="<c:url value="/admin/updatedriver"/>">
        <h2 class="form-sign-up-heading">Update Profile</h2>
        <label for="email" class="sr-only">Email </label>
        <input type="email" placeholder="email" id="email" name="email" class="form-control" value="${driver.email}" >

        <label for="password" class="sr-only">Password</label>
        <input type="password" placeholder="password" id="password" name="password" class="form-control" value="${driver.password}">

        <label for="confirmpassword" class="sr-only">Password</label>
        <input type="password" placeholder="confirm password" id="confirmpassword" name="confirmpassword" class="form-control" value="${driver.password}">

        <label for="phone" class="sr-only">Phone number</label>
        <input type="text" placeholder="phone number" id="phone" name="phone" class="form-control" value="${driver.phone}">

        <div id="updateDriver">
            <button type="button" class="btn btn-primary">Update</button>
        </div>
    </form>
</div>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin//bower_components/jquery/dist/jquery.validate.min.js"></script>


<script src="<%=application.getContextPath()%>/resources/admin/js/updatedriver.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/js/hsps.js"></script>

</body>
</html>
