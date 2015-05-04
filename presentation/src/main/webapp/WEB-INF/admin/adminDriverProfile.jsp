<%--
  Created by IntelliJ IDEA.
  User: Vadym_Akymov
  Date: 25.04.15
  Time: 0:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.css"
          rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/dist/css/sb-admin-2.css" rel="stylesheet">

    <%--My resources--%>
    <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/admin/css/admin.css">

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
        <%--Profile container--%>
        <div class="placement">
            <div class="row">
                <%--<div class="col-md-5  toppad  pull-right col-md-offset-3 ">--%>
                <%--<A href="edit.html" >Edit Profile</A>--%>

                <%--<A href="edit.html" >Logout</A>--%>
                <%--<br>--%>
                <%--<p class=" text-info">May 05,2014,03:00 pm </p>--%>
                <%--</div>--%>
                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">


                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">${driver.email}</h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-3 col-lg-3 " align="center"><img alt="User Pic"
                                                                                    src="<%=application.getContextPath()%>/resources/admin/img/profile.png?sz=100"
                                                                                    class="img-circle"></div>

                                    <!--<div class="col-xs-10 col-sm-10 hidden-md hidden-lg"> <br>
                                      <dl>
                                        <dt>DEPARTMENT:</dt>
                                        <dd>Administrator</dd>
                                        <dt>HIRE DATE</dt>
                                        <dd>11/12/2013</dd>
                                        <dt>DATE OF BIRTH</dt>
                                           <dd>11/12/2013</dd>
                                        <dt>GENDER</dt>
                                        <dd>Male</dd>
                                      </dl>
                                    </div>-->
                                    <div class=" col-md-9 col-lg-9 ">
                                        <table class="table table-user-information">
                                            <tbody>
                                            <tr>
                                                <td>Email</td>
                                                <td>${driver.email}</td>
                                            </tr>
                                            <tr>
                                                <td>Sex</td>
                                                <td>${driver.sex == "F" ? "Female" : "Male"}</td>
                                            </tr>
                                                <td>Phone Number</td>
                                                <td>${driver.phone != null ? driver.phone : "No phone number"} </td>
                                            </tr>
                                            <tr>
                                                <td>License</td>
                                                <td>${driver.driverLicense != null ? driver.driverLicense : "No license"}</td>
                                            </tr>
                                            <tr>
                                                <td>Car number</td>
                                                <td class="carNumb">${driver.car.carNumber != null ? driver.car.carNumber : "No car"}</td>
                                            </tr>

                                            <%--<tr>--%>
                                            <tr>
                                                <td>Group</td>
                                                <td>${driver.groupName != null ? driver.groupName : "No group"}</td>
                                            </tr>
                                            <tr>
                                                <td>Registration Date</td>
                                                <td><fmt:formatDate pattern="dd-MM-yyyy" value="${driver.registrationDate}"/> </td>
                                            </tr>

                                            </tbody>
                                        </table>

                                        <%--<a href="#" class="btn btn-primary">Edit profile</a>--%>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <a data-toggle="tooltip" title="choose car" type="button" class="btn icon-cars btn-primary carChooseButton"><i class="glyphicon glyphicon-road"></i></a>
                        <span class="pull-right">
                            <a href="updatedriver?userId=${driver.userId}" title="update" data-original-title="Edit this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
                            <a data-original-title="Remove this user" title="delete" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger removeBtn"><i class="glyphicon glyphicon-remove"></i></a>
                            <input id="driverId" type="hidden" value="${driver.userId}">
                        </span>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        <%--End profile container--%>
            <%--Car choosing menu--%>
            <div class="carChoose">
                <div>
                    <label>Choose car for driver</label>
                </div>
                    <select class="make form-control taxiCar" name="taxiCar">
                        <c:forEach items="${requestScope.cars}" var="car">
                            <option value="${car.carNumber}">${car.carNumber}</option>
                        </c:forEach>
                    </select>
                <div class="carButtons">
                    <input type="button" class="btn btn-success okBtn" value="OK"/>
                    <input type="button" class="btn btn-danger cancelBtn" value="Cancel"/>
                </div>
            </div>
            <%--end of car choosing menu--%>
    </div>
</div>
<!-- jQuery -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=application.getContextPath()%>/resources/admin/dist/js/sb-admin-2.js"></script>
<%--for pagination--%>
<script src="<%=application.getContextPath()%>/resources/admin/js/pagination.js"></script>
<%--For removing drivers--%>
<script src="<%=application.getContextPath()%>/resources/admin/js/driver-remove.js"></script>
<%--For car choosing--%>
<script src="<%=application.getContextPath()%>/resources/admin/js/admin.js"></script>

</body>
</html>