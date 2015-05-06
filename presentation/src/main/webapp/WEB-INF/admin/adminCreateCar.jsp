<%--
  Created by IntelliJ IDEA.
  User: Katia Stetsiuk
  Date: 26-Apr-15
  Time: 21:00
  To change this template use File | Settings | File Templates.
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

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

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

    <div id="page-wrapper">
        <form class="create-car" id="createCar" action="<c:url value="/admin/createcar"/>" method="post">
            <h2 class="form-sign-up-heading">Please enter cars data</h2>
            <%--<label for="carNumber" class="sr-only">Number</label>--%>
            <%--<input type="carNumber" id="carNumber" name="carNumber" class="form-control" placeholder="Car Number" value="${car.carNumber}">--%>

            <label for="carNumber" class="sr-only">Model</label>
            <input type="carNumber" id="carNumber" name="carNumber" class="form-control">
            <label for="carModel" class="sr-only">Model</label>
            <input type="carModel" id="carModel" name="carModel" class="form-control">

            <label for="carColor" class="sr-only">Color</label>
            <input type="carColor" id="carColor" name="carColor" class="form-control"
                   placeholder="color" >


            <%--TODO send via ajax!!--%>
            <div>
                <label>Category</label>
            </div>
            <select id="category" class="category form-control" name="category">
                <option value="BUSINESS_CLASS">BUSINESS CLASS</option>
                <option value="ECONOMY_CLASS">ECONOMY CLASS</option>
                <option value="VAN">VAN</option>
                <option value="USER_CAR">USER_CAR</option>

            </select>
            <%--<label for="animalTransportationApplicable" class="sr-only">Color</label>--%>
            <input type="checkbox" id="animalTransportationApplicable" name="animalTransportationApplicable"
                    >Animal Transportation Applicable<Br>
            <input type="checkbox" id="freeWifi" name="freeWifi">Free Wifi<Br>
            <input type="checkbox" id="airConditioner" name="airConditioner">Air
            Conditioner<Br>

            <div>
                <button id="addCar" type="button" class="btn btn-primary">Create</button>
            </div>
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=application.getContextPath()%>/resources/admin/dist/js/sb-admin-2.js"></script>
<%--for pagination--%>
<script src="<%=application.getContextPath()%>/resources/admin/js/forcreatecar.js"></script>


</body>

</html>

