<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="../parts/meta.jsp"%>

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
</head>

<body>

<div id="wrapper">
    <jsp:include page="adminHeader.jsp"/>

    <div id="page-wrapper">
        <table class="table">
            <thead>
            <tr>
                <th>Car Number</th>
                <th>Car Model</th>
                <th>Color</th>
                <th>Car Category</th>
                <th>Way Of Payment</th>
                <th>Animal Transportation</th>
                <th>Free WiFi</th>
                <th>Air Conditioner</th>
            </tr>
            </thead>
            <tbody id="table-body">
            <c:forEach items="${requestScope.cars}" var="car">
                <tr>
                    <td><a href="updatecar?carNumber=${car.carNumber}">${car.carNumber}</a></td>
                    <td>${car.carModel}</td>
                    <td>${car.color}</td>
                    <td>${car.carCategory}</td>
                    <td>${car.acceptsVisa==true ? "+" : "-"}</td>
                    <td>${car.animalTransportationApplicable==true ? "+" : "-"}</td>
                    <td>${car.freeWifi==true ? "+" : "-"}</td>
                    <td>${car.airConditioner==true ? "+" : "-"}</td>
                    <td><a href="updatecar?carNumber=${car.carNumber}">Edit</a></td>
                    <td><button class="${car.carNumber}" type="button"><a href="deletecar?carNumber=${car.carNumber}">Delete</a>
                        </button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="text-center">
            <ul class="pagination">
                <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
                    <li class="pageLi${i}"><a class="pageButton" href="#">${i}</a></li>
                </c:forEach>
            </ul>
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
<script src="<%=application.getContextPath()%>/resources/admin/js/paginationCar.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/js/removeCar.js"></script>


</body>

</html>
