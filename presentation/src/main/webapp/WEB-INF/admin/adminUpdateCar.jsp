<%--
  Created by IntelliJ IDEA.
  User: Katia Stetsiuk
  Date: 27-Apr-15
  Time: 19:28
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
</div>
<div class="container" style=" width : 500px; ">

    <form class="update-car" id="updateItCar" action="<c:url value="/admin/updatecar"/>" method="post">
        <h2 class="form-sign-up-heading">Please enter cars data</h2>
        <%--<label for="carNumber" class="sr-only">Number</label>--%>
        <%--<input type="carNumber" id="carNumber" name="carNumber" class="form-control" placeholder="Car Number" value="${car.carNumber}">--%>

        <label for="carModel" class="sr-only">Model</label>
        <input type="carModel" id="carModel" name="carModel" class="form-control" value="${car.carModel}">

        <label for="carColor" class="sr-only">Color</label>
        <input type="carColor" id="carColor" name="carColor" class="form-control"
               placeholder="color" value="${car.color}">


        <%--TODO send via ajax!!--%>
        <div>
            <label>Category</label>
        </div>
        <select id="category" class="category form-control" name="category">
            <option value="BUSINESS_CLASS">BUSINESS CLASS</option>
            <option value="ECONOMY_CLASS">ECONOMY CLASS</option>
            <option value="VAN">VAN</option>
        </select>
        <%--<label for="animalTransportationApplicable" class="sr-only">Color</label>--%>
        <input type="checkbox" id="animalTransportationApplicable" name="animalTransportationApplicable"
               value="${car.animalTransportationApplicable}">Animal Transportation Applicable<Br>
        <input type="checkbox" id="freeWifi" name="freeWifi" value="${car.freeWifi}">Free Wifi<Br>
        <input type="checkbox" id="airConditioner" name="airConditioner" value="${car.airConditioner}">Air
        Conditioner<Br>

        <div>
            <button id="updateCar" type="button" class="btn btn-primary">Create</button>
        </div>


    </form>
</div>

<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin//bower_components/jquery/dist/jquery.validate.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<script src="<%=application.getContextPath()%>/resources/admin/js/updatecar.js"></script>


</body>
</html>
