<%--
  Created by IntelliJ IDEA.
  User: Vadym_Akymov
  Date: 26.04.15
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/customer/css/customer-style.css">
  <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/customer/css/custom.css">
  <%@include file="../parts/meta.jsp" %>
  <%@include file="../parts/bootstrap2.jsp" %>
  <title>profile</title>
</head>
<body>
<%@include file="../parts/header.jsp" %>

<!-- start: Page Title -->
<div id="page-title">

  <div id="page-title-inner">

    <!-- start: Container -->
    <div class="container">
      <h2><i class="ico-settings ico-whiteeclipse"></i>Client Profile</h2>
    </div>
    <!-- end: Container  -->
  </div>

</div>
<!-- end: Page Title -->

<!--start: Wrapper-->
<div id="wrapper">

  <!--start: Container -->
  <div class="container">

    <!-- start: Row -->
    <div class="row">

    <c:forEach items="${requestScope.orders}" var="order">
      <div class="span4">
        <div class="icons-box">
          <i class="ico-shopping-cart circle big"></i>
          <div class="title"><h3>Order № ${order.trackingNumber}</h3></div>
          <p>
            Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
          </p>
          <div class="clear"></div>
        </div>
      </div>
    </c:forEach>

    </div>
    <!-- end: Container  -->
  </div>
</div>

<%-- start: JavaScript --%>

<script src="<%=application.getContextPath()%>/resources/customer/js/customer.js"></script>
<%@include file="../parts/scripts.jsp" %>
<%@include file="../parts/footer.jsp" %>

</body>
</html>
