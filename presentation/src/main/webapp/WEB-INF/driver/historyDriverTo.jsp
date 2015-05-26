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

  <!-- Custom Fonts -->
  <link href="<%=application.getContextPath()%>/resources/admin/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

  <%--START JS for pagination--%>
  <%@ include file="../parts/scripts.jsp"%>
  <script src="http://maps.googleapis.com/maps/api/js?v=3.19&key=AIzaSyAtwMePDVDymtf-yC-qk1hdmUMnDtGYbb8&libraries=geometry"></script>
  <script src="<c:url value="/webjars/angularjs/1.3.15/angular.min.js"/>"></script>
  <script src="<c:url value="/webjars/angular-utils-pagination/0.7.0/dirPagination.js"/>"></script>
  <script src="<%=application.getContextPath()%>/resources/driver/js/history-orders-pagination.js"></script>
  <%--END JS for pagination--%>

</head>
<body>
<div id="wrapper" ng-app="driver" ng-controller="ordersHistoryController">
  <jsp:include page="driverHeader.jsp"/>

  <div id="page-wrapper">

    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">History of completed orders</h1>
      </div>
      <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->

    <section id="plans">
      <div class="container">
        <div class="row">

          <!-- item -->
          <div class="col-md-9 text-center"
               dir-paginate="order in orders | itemsPerPage: ordersPerPage"
               total-items="totalOrders"
               current-page="pagination.current"
               pagination-id="ordersPagination">

            <div class="panel panel-success panel-pricing">

              <%-- heading --%>
              <div class="panel-heading">
                <div class="map-canvas">
                  {{ order.map }}
                </div>
              </div>
              <%-- END heading --%>

              <table class="table table-striped table-hover">
                <thead>
                <tr>
                  <th>#</th>
                  <th>Service</th>
                  <th ng-if="order.service == 'CELEBRATION_TAXI' || order.service == 'TAXI_FOR_LONG_TERM'">Duration</th>
                  <th>Order time</th>
                  <th>Car arrive time</th>
                  <th>Price</th>
                  <th>Status</th>
                  <th>User comment</th>
                </tr>
                </thead>
                <tbody>
                <tr class="odd gradeX">
                  <td>{{ order.trackingNumber }}</td>
                  <td>{{ order.service }}</td>
                  <td ng-if="order.service == 'CELEBRATION_TAXI' || order.service == 'TAXI_FOR_LONG_TERM'">
                    {{ order.amountOfHours }}: {{ order.amountOfMinutes }}
                  </td>
                  <td>{{ order.orderDate }}</td>
                  <td>{{ order.arrivalDate }}</td>
                  <td>{{ order.price }}</td>
                  <td>{{ order.status }}</td>
                  <td>{{ order.comment }}</td>
                </tr>
                </tbody>
              </table>

            </div>
          </div>
          <!-- /item -->

        </div>
        <!-- /.row -->


      </div>
      <!-- /.container -->

    </section>
    <!-- /Plans -->

    <%--START pagination--%>
    <div class="text-center">
      <dir-pagination-controls boundary-links="true"
                               pagination-id="ordersPagination"
                               on-page-change="pageChanged(newPageNumber)"
                               template-url="<c:url value="/webjars/angular-utils-pagination/0.7.0/dirPagination.tpl.html"/>">
      </dir-pagination-controls>
    </div>
    <%--END pagination--%>

  </div>
  <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=application.getContextPath()%>/resources/js/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="<%=application.getContextPath()%>/resources/js/raphael-min.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/morris.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/morris-data.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=application.getContextPath()%>/resources/js/sb-admin-2.js"></script>

<%--for pagination--%>
<script src="<%=application.getContextPath()%>/resources/driver/js/paginator-orders.js"></script>

</body>

</html>
