<%--
  Created by Ruslan Gunavardana.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <%@include file="../parts/head.jsp"%>
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
  <style type="text/css">
    html { height: 100% }
    body { height: 100%; margin: 0; padding: 0 }
  </style>
</head>
<body>
<form>
  <label>
    From:
    <input id="origin" type="text">
  </label>
  <label>
    To:
    <input id="destination" type="text">
  </label>
  <p id="total"></p>
  <p id="error-label"></p>
  <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="codeAddresses()">Sign up</button>
</form>

<div id="map-canvas" style="width:75%; height:75%"></div>
<div id="directions-panel" style="float:right;width:20%;height:20%"></div>

<%-- start: Java Script --%>
<%@include file="../parts/scripts.jsp" %>
<script src="<%=application.getContextPath()%>/resources/customer/js/google-maps.js"></script>
<%-- end: Java Script --%>
</body>
</html>