<%@ page import="ua.com.tracksee.entities.TaxiOrderEntity" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Vadym_Akymov
  Date: 26.04.15
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <%!int i = 0;%>
    <c:forEach items="${requestScope.orders}" var="order">
      <div class="span4">
        <div class="icons-box orderBody">
          <i class="ico-shopping-cart circle big"></i>
          <div class="title"><h3 class="order<%=i%>">Order № ${order.trackingNumber}</h3></div>
          <div>
            <p class="service<%=i%>"><b>SERVICE:</b> ${order.service}</p>
          </div>
          <div>
            <p class="status<%=i%>"><b>STATUS:</b> ${order.status}</p>
          </div>
          <div>
            <p class="price<%=i%>"><b>PRICE:</b> ${order.price} grn</p>
          </div>
          <div>
            <p class="date<%=i%>"><b>DATE:</b> <fmt:formatDate pattern="dd-MM-yyyy" value="${order.orderedDate}"/></p>
          </div>
          <div class="clear"></div>
        </div>
      </div>
      <%i = (++i % ((List<TaxiOrderEntity>)request.getAttribute("orders")).size());%>
    </c:forEach>
    </div>
    <div class="pointers">
      <input class="pageNumber" type="hidden" value="${requestScope.pageNumber}">
      <input class="pagesCount" type="hidden" value="${requestScope.pagesCount}">
      <button disabled="disabled" type="button" class="btn btn-default prevButton">Prev</button>
      <button type="button" class="btn btn-default nextButton">Next</button>
    </div>
    <!-- end: Container  -->
  </div>
</div>

<%-- start: JavaScript --%>
<%@include file="../parts/scripts.jsp" %>
<%--<%@include file="../parts/footer.jsp" %>--%>
<script src="<%=application.getContextPath()%>/resources/customer/js/customer.js"></script>

</body>
</html>
