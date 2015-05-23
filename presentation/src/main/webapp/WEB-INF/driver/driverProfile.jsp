<%--
  Created by IntelliJ IDEA.
  User: Maria Komar
  Date: 19.04.2015
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
  <%@include file="../parts/meta.jsp"%>

  <!-- Bootstrap Core CSS -->
  <link href="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

  <!-- MetisMenu CSS -->
  <link href="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

  <!-- Custom CSS -->
  <link href="<%=application.getContextPath()%>/resources/admin/dist/css/sb-admin-2.css" rel="stylesheet">

  <%--My resources--%>
  <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/admin/css/admin.css">
  <script href="<%=application.getContextPath()%>/resources/admin/js/admin.js"></script>

  <!-- Custom Fonts -->
  <link href="<%=application.getContextPath()%>/resources/admin/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapper">
  <jsp:include page="driverHeader.jsp"/>

  <div id="page-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">Driver profile</h1>
      </div>
      <!-- /.col-lg-12 -->
    </div>


    <%--Profile container--%>
    <div class="placement">
      <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0
        col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >


          <div class="panel panel-info">
            <div class="panel-heading">
              <h3 class="panel-title">${driver.firstName} ${driver.lastName}</h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-3 col-lg-3 " align="center">  </div>

                <div class=" col-md-9 col-lg-9 ">
                  <table class="table table-user-information">
                    <tbody>
                    <tr>
                      <td>Email</td>
                      <td>${driver.email}</td>
                    </tr>
                    <tr>
                      <td>Sex</td>
                      <td>
                        <c:set var="string1" value="${driver.sex}"/>
                        <c:set var="string2" value="${fn:replace(string1,
                                'M', 'male')}" />
                        <c:set var="string3" value="${fn:replace(string2,
                                'F', 'female')}" />
                        ${string3}
                      </td>
                    </tr>
                    <td>Phone number</td>
                    <td>${driver.phone != null ? driver.phone : "No phone number"} </td>
                    </tr>
                    <tr>
                      <td>Driver license</td>
                      <td>${driver.driverLicense != null ? driver.driverLicense : "No driver license"}</td>
                    </tr>

                    <%--<tr>--%>
                    <tr>
                      <td>Group</td>
                      <td>${driver.groupName != null ? driver.groupName : "No group"}</td>
                    </tr>
                    <tr>
                      <td>Registration date</td>
                      <td><fmt:formatDate pattern="dd-MM-yyyy" value="${driver.registrationDate}"/> </td>
                    </tr>

                    </tbody>
                  </table>

                  <%--<a href="#" class="btn btn-primary">Edit profile</a>--%>
                </div>
              </div>
            </div>

        </div>
      </div>
    </div>

      <div class="placement">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0
        col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >


            <div class="panel panel-primary">
              <div class="panel-heading">
                <h3 class="panel-title">Information about car</h3>
              </div>
              <div class="panel-body">
                <div class="row">
                  <div class="col-md-3 col-lg-3 " align="center">  </div>

                  <div class=" col-md-9 col-lg-9 ">
                    <table class="table table-user-information">
                      <tbody>
                      <tr>
                        <td>Car number: </td>
                        <td>${driver.car.carNumber != null ? driver.car.carNumber : "No car"}</td>
                      </tr>
                      <tr>
                        <td>Color: </td>
                        <td>${driver.car.carNumber != null ? driver.car.color : "No car"}</td>
                      </tr>
                      <tr>
                        <td>Type: </td>
                        <td>${driver.car.carNumber != null ? driver.car.carCategory : "No car"}</td>
                      </tr>
                      <tr>
                        <td>Model: </td>
                        <td>${driver.car.carNumber != null ? driver.car.carModel : "No car"}</td>
                      </tr>
                      <tr>
                        <td>Visa terminal: </td>
                        <td>${order.acceptsVisa==true ? "+" : "-"}</td>
                      </tr>
                      <tr>
                        <td>Air conditioner: </td>
                        <td>${order.airConditioner==true ? "+" : "-"}</td>
                      </tr>
                      <tr>
                        <td>Wi-fi: </td>
                        <td>${order.freeWifi==true ? "+" : "-"}</td>
                      </tr>
                      </tbody>
                    </table>
                    </div>
                  </div>
                </div>

    <%--End profile container--%>


  </div>
  <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery Version 1.11.2 -->
<script src="<%=application.getContextPath()%>/resources/js/jquery-1.11.2.min.js"></script>
<!--
<script src="../../../resources/js/jquery-1.11.2.js"></script>
<script src="../../../resources/js/jquery.min.js"></script>
<script type="text/javascript" language="javascript" src="../../../resources/js/jquery.js"></script>
-->

<!-- Bootstrap Core JavaScript -->
<script src="<%=application.getContextPath()%>/resources/js/bootstrap2/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=application.getContextPath()%>/resources/js/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="<%=application.getContextPath()%>/resources/js/raphael-min.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/morris.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/morris-data.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=application.getContextPath()%>/resources/js/sb-admin-2.js"></script>

</body>

</html>
