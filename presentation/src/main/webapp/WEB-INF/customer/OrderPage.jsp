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
<head>

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
  <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="codeAddresses()">Sign up</button>
</form>

<div id="map-canvas" style="width:100%; height:100%"></div>
<p id="total"></p>
<div id="directionsPanel" style="float:right;width:30%;height:100%"></div>

<!-- start: Java Script -->
<!-- Placed at the end of the document so the pages load faster -->
<%@include file="../parts/scripts.jsp" %>
<script src="<%=application.getContextPath()%>/resources/js/custom/google-maps.js"></script>
<!-- end: Java Script -->

</body>
</html>