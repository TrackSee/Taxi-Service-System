<%--
  Created by IntelliJ IDEA.
  User: Katia Stetsiuk
  Date: 24-Apr-15
  Time: 09:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>

<head>
    <%@include file="../parts/meta.jsp"%>

    <!-- Bootstrap Core CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="<%=application.getContextPath()%>/resources/admin/css/formStyle.css" rel="stylesheet">


    <!-- MetisMenu CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.css"
          rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css">
</head>


<body>

<div id="wrapper">
    <jsp:include page="adminHeader.jsp"/>
</div>
    <div id="page-wrapper">
        <form class="create-car" id="createCar" action="<c:url value="/admin/createcar"/>" method="post">
            <h2 class="form-sign-up-heading">Please enter data</h2>
            <label for="carNumber" class="sr-only">Model</label>
            <input type="text" id="carNumber" name="carNumber" class="form-control"
                   placeholder="Car Number" required>
            <label for="carModel" class="sr-only">Model</label>
            <input type="text" id="carModel" name="carModel" class="form-control"
                   placeholder="Car Model" required>

            <label for="carColor" class="sr-only">Color</label>
            <input type="text" id="carColor" name="carColor" class="form-control"
                   placeholder="Color">

            <div>
                <label>Category</label>
            </div>
            <select id="category" class="category form-control" name="category">
                <option value="BUSINESS_CLASS">BUSINESS CLASS</option>
                <option value="ECONOMY_CLASS">ECONOMY CLASS</option>
                <option value="VAN">VAN</option>
                <option value="USER_CAR">USER_CAR</option>

            </select>
            <input type="checkbox" id="acceptsVisa" name="acceptsVisa">Way Of Payment - VISA<Br>
            <%--<label for="animalTransportationApplicable" class="sr-only">Color</label>--%>
            <input type="checkbox" id="animalTransportationApplicable" name="animalTransportationApplicable"
                    >Animal Transportation Applicable<Br>
            <input type="checkbox" id="freeWifi" name="freeWifi">Free Wifi<Br>
            <input type="checkbox" id="airConditioner" name="airConditioner">Air
            Conditioner<Br>

            <div>
                <button id="addCar" type="button" class="btn btn-primary">Create</button>
            </div>
            </form>

    </div>

<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin//bower_components/jquery/dist/jquery.validate.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/js/carCreate.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/js/hsps.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/dist/js/sb-admin-2.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>

</body>
</html>
