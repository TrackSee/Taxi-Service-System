<%--
  Created by IntelliJ IDEA.
  User: exarus
  Date: 4/17/15
  Time: 1:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
  <style type="text/css">
    html { height: 100% }
    body { height: 100%; margin: 0; padding: 0 }
  </style>
</head>
<body onload="initialize()">
<label></label>
<label>
  From:
  <input>
</label>
<label>
  To:
  <input>
</label>

<div id="map-canvas" style="width:100%; height:100%"></div>
<p id="total"></p>
<div id="directionsPanel" style="float:right;width:30%;height:100%"></div>

<!-- start: Java Script -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=application.getContextPath()%>/resources/js/google-maps.js"></script>
<!-- end: Java Script -->

</body>
</html>