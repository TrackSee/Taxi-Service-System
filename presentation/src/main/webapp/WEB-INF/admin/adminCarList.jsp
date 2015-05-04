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
        <table class="table">
            <thead>
            <tr>
                <th>Car Number</th>
                <th>Car Model</th>
                <th>Color</th>
                <th>Car Category</th>
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
                    <td>${car.animalTransportationApplicable==true ? "+" : "-"}</td>
                    <td>${car.freeWifi==true ? "+" : "-"}</td>
                    <td>${car.airConditioner==true ? "+" : "-"}</td>
                    <td><a href="updatecar?carNumber=${car.carNumber}" >Edit</a></td>
                    <td><a href="deletecar?carNumber=${car.carNumber}">Delete</a></td>
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
<script src="<%=application.getContextPath()%>/resources/admin/js/paginator-car.js"></script>


</body>

</html>

