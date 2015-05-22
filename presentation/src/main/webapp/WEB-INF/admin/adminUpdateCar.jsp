<%--
  Created by IntelliJ IDEA.
  User: Katia Stetsiuk
  Date: 27-Apr-15
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>

<head>
    <%@include file="../parts/meta.jsp" %>

    <!-- Bootstrap Core CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css"
          rel="stylesheet">
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

    <form class="update-car" id="updateItCar" action="<c:url value="/admin/updatecar"/>" method="post">
        <p style="font: 18pt sans-serif;  margin: 0 75px;">Please enter cars data</p><br>

        <div class="form-group">
            <label for="carModel" class="sr-only">Model</label>
            <input type="carModel" id="carModel" name="carModel" class="form-control" value="${car.carModel}"
                   placeholder="Car Model">

        </div>
        <div class="form-group">
            <label for="carColor" class="sr-only">Color</label>
            <input type="carColor" id="carColor" name="carColor" class="form-control"
                   placeholder="Color" value="${car.color}">
        </div>
        <div class="form-group">
                <label>Category</label>
        </div>
        <div class="form-group">
            <select id="carCategory" class="category form-control" name="carCategory">
                <option value="BUSINESS_CLASS">BUSINESS_CLASS</option>
                <option value="ECONOMY_CLASS">ECONOMY_CLASS</option>
                <option value="VAN">VAN</option>
            </select>
        </div>
        <%--<label for="animalTransportationApplicable" class="sr-only">Color</label>--%>
        <div class="form-group">
            <input type="checkbox" id="acceptsVisa" name="acceptsVisa" ${car.acceptsVisa? "checked=\"on\"" : ""} >Way Of
            Payment - VISA<Br>

            <input type="checkbox" id="animalTransportationApplicable"
                   name="animalTransportationApplicable" ${car.animalTransportationApplicable? "checked=\"on\"" : ""} >Animal
            Transportation Applicable<Br>
            <input type="checkbox" id="freeWifi" name="freeWifi" ${car.freeWifi ? "checked=\"on\"" : ""}>Free Wifi<Br>
            <input type="checkbox" id="airConditioner"
                   name="airConditioner" ${car.airConditioner ? "checked=\"on\"" : ""} >Air Conditioner<Br>
        </div>
        <div>
            <button id="updateCar" type="button" class="btn btn-primary">Update</button>
        </div>


    </form>
</div>

<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin//bower_components/jquery/dist/jquery.validate.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/js/updatecar.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/dist/js/sb-admin-2.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>


</body>
</html>
