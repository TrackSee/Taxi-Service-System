<%@ page import="ua.com.tracksee.entities.TaxiOrderEntity" %>
<%@ page import="java.util.List" %>
<%--
  User: Vadym_Akymov
  Date: 26.04.15
  Time: 14:01
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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
      <h2><i class="ico-settings ico-whiteeclipse"></i>Client Dashboard</h2>
      <div class="btn-group btn-group-lg group" role="group" aria-label="Large button group">
        <button type="button" class="btn btn-default completed <%=request.getAttribute("type").equals("completed") ? "active datatype" : ""%>" value="old">Old TOs</button>
        <button type="button" class="btn btn-default act <%=request.getAttribute("type").equals("active") ? "active datatype" : ""%>" value="active">Active TOs</button>
      </div>
    </div>
    <!-- end: Container  -->
  </div>
</div>
<!-- end: Page Title -->

<!--start: Wrapper-->
<div id="wrapper">

  <!--start: Container -->
  <div class="container">
      <%--<div class="form-group">--%>
          <%--<form method="post" action="<c:url value="/orderTracking"/>">--%>
              <%--<label>Tracking number</label>--%>
              <%--<input type="number"  class="form-control" name="trackingNumber"--%>
                     <%--placeholder="Enter your tracking number"--%>
                     <%--title="Only integer number" required>--%>
              <%--<div class="form-group">--%>
                  <%--<div class="form-group">--%>
                      <%--<button type="submit" class="btn btn-success btn-large">--%>
                          <%--Track your taxi order</button>--%>
                  <%--</div>--%>
              <%--</div>--%>
          <%--</form>--%>
      <%--</div>--%>
    <!-- start: Row -->
    <div class="row orderRow">
    <%!int i = 0;%>
    <c:forEach items="${requestScope.orders}" var="order">
      <div class="span4 orderSpan<%=i%>">
        <div class="icons-box orderBody">
          <i class="ico-shopping-cart circle big"></i>
          <a class="track" href="<%=application.getContextPath()%>/orderTracking"><div class="title">
              <h3 class="order<%=i%>">Order № ${order.trackingNumber}</h3></div></a>

            <form method="post" action="<c:url value="/orderTracking"/>">

                <input type="hidden"  name="trackingNumber" id="trackingNumber" value="${order.trackingNumber}">
          <%--<div>--%>
            <%--<p class="service<%=i%>"><b>SERVICE:</b> ${order.userId}</p>--%>
          <%--</div>--%>
          <div>
            <p class="status<%=i%>"><b>STATUS:</b> ${order.status}</p>
          </div>
          <div>
            <p class="price<%=i%>"><b>PRICE:</b> ${order.price} grn</p>
          </div>
          <div>
            <p class="date<%=i%>"><b>DATE:</b> <fmt:formatDate pattern="dd-MM-yyyy" value="${order.orderedDate}"/></p>
          </div>

                <div class="form-group">
                    <div class="form-group">
                        <button type="submit" id="trackOrder<%=i%>" class="btn btn-success btn-large">
                            Detailed description</button>
                    </div>
                </div>
            </form>
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
<script src="<%=application.getContextPath()%>/resources/customer/js/excelReports.js"></script>

</body>
</html>
