<%--
  Created by IntelliJ IDEA.
  User: Vitalii Diravka
  Date: 29.04.2015
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SB Admin 2 - Bootstrap Admin Theme. Edited by Vetal</title>

  <!-- Bootstrap Core CSS -->
  <link href="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

  <!-- MetisMenu CSS -->
  <link href="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

  <!-- Custom CSS -->
  <link href="<%=application.getContextPath()%>/resources/admin/dist/css/sb-admin-2.css" rel="stylesheet">

  <!-- Custom Fonts -->
  <link href="<%=application.getContextPath()%>/resources/admin/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <%--test format--%>
  <link href="<%=application.getContextPath()%>/resources/admin/css/admin.css"  rel="stylesheet" type="text/css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9] -->
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <script src="<%=application.getContextPath()%>/resources/admin/js/update_tariff.js"></script>
  <script src="<%=application.getContextPath()%>/resources/admin/js/tariff_galochka.js"></script>
  <script type="text/javascript" src="<%=application.getContextPath()%>/resources/js/jquery-2.1.3.min.js"></script>

</head>

<body>

<div id="wrapper">
  <jsp:include page="adminHeader.jsp"/>
  <div id="page-wrapper">
    <table class="table" id="tablePrice">
      <thead>
      <tr>
        <th bgcolor="#f5f5f5">№</th>
        <th bgcolor="#f5f5f5">Car category</th>
        <th bgcolor="#f5f5f5">Weekend</th>
        <th bgcolor="#f5f5f5">Night tariff</th>
        <th>Price per km, UAH</th>
        <th>Price per min, UAH</th>
      </tr>
      </thead>
      <tbody id="table-body" class="tboddy">
      <c:forEach items="${requestScope.tariff}" var="tariff" varStatus="myIndex">
        <tr>
          <td class="No" bgcolor="#f5f5f5">${myIndex.index+1}</td>
          <td class="car" bgcolor="#f5f5f5">${tariff.carCategory}</td>
          <td bgcolor="#f5f5f5">
            <input id="ch_weeke${myIndex.index}" type="checkbox" <c:if test="${tariff.weekend}">checked</c:if> disabled>
          </td>
          <td bgcolor="#f5f5f5">
            <input id="ch_nt${myIndex.index}" type="checkbox" <c:if test="${tariff.nightTariff}">checked</c:if> disabled>
          </td>
          <td>
            <input id = "perKm${myIndex.index}" type="number" step="0.01" min="0" maxlength="2" value="${tariff.pricePerKm}"
                   onfocus="check(${myIndex.index}, 'perKm', 'buttonKm')">
            <input id="buttonKm${myIndex.index}" type="submit" value="Save" onclick="save(${myIndex.index}, 'perKm')">
            <input id="err_perKm${myIndex.index}" style="border-color:white; border-width:0;" class="error" size="6" readonly>
            <input id="text_perKm${myIndex.index}" style="border-color:white; border-width:0;" class="msgText" readonly>
          </td>
          <td>
            <input id = "perMin${myIndex.index}" type="number" step="0.01" min="0" maxlength="14" value="${tariff.pricePerMin}"
                   onfocus="check(${myIndex.index}, 'perMin', 'buttonMin')">
            <input id="buttonMin${myIndex.index}" type="submit" value="Save" onclick="save(${myIndex.index}, 'perMin')">
            <input id="err_perMin${myIndex.index}" style="border-color:white; border-width:0;" class="error" size="6" readonly>
            <input id="text_perMin${myIndex.index}" style="border-color:white; border-width:0;" class="msgText" readonly>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <div id="ajaxGetUserServletResponse"></div>
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
<script src="<%=application.getContextPath()%>/resources/admin/js/pagination.js"></script>
<%--input value validation--%>
<script src="<%=application.getContextPath()%>/resources/admin/js/input_tariff_validate.js"></script>
</body>

</html>
