<%-- Created by Maria Komar and Ruslan Gunavardana --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
  <%@ include file="../parts/meta.jsp"%>

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

  <link href="<%=application.getContextPath()%>/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet"
        media="screen">

  <link href="<%=application.getContextPath()%>/resources/driver/css/map-canvas.css" rel="stylesheet"
        media="screen">
  <link href="<%=application.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"
        media="screen">

  <script src="http://maps.googleapis.com/maps/api/js?v=3.19&key=AIzaSyAtwMePDVDymtf-yC-qk1hdmUMnDtGYbb8&libraries=geometry"></script>
  <%--START JS for pagination--%>
  <%@ include file="../parts/scripts.jsp"%>
  <script src="<%=application.getContextPath()%>/resources/js/bootstrap-datetimepicker.js"></script>
  <script src="<%=application.getContextPath()%>/resources/js/date-picker-order-complete.js"></script>
  <script src="<c:url value="/webjars/angularjs/1.3.15/angular.min.js"/>"></script>
  <script src="<c:url value="/webjars/angular-utils-pagination/0.7.0/dirPagination.js"/>"></script>
  <script src="<%=application.getContextPath()%>/resources/driver/js/available-orders-pagination.js"></script>
  <%--END JS for pagination--%>
</head>
<body>
<div id="wrapper" ng-app="driver" ng-controller="availableOrdersController">
  <jsp:include page="driverHeader.jsp"/>

  <div id="page-wrapper">

    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">Free orders</h1>
      </div>
    </div>

    <!-- Plans -->
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
                  <iframe id="map-frame" frameborder="0" width="825" height="250" ng-src="{{ order.map }}"></iframe>'
                </div>
              </div>
              <%-- END heading --%>

              <table class="table table-striped table-hover">
                <thead>
                <tr>
                  <th>#</th>
                  <th>Service</th>
                  <th ng-if="order.service == 'Celebration taxi' || order.service == 'Taxi for long term'">Duration</th>
                  <th>Order time</th>
                  <th>Car arrive time</th>
                  <th>Price</th>
                  <th>Non smoking</th>
                  <th>Music</th>
                  <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <tr class="odd gradeX">
                  <td>{{ order.trackingNumber }}</td>
                  <td>{{ order.service }}</td>
                  <td ng-if="order.service == 'Celebration taxi' || order.service == 'Taxi for long term'">
                    {{ order.amountOfHours }}: {{ order.amountOfMinutes }}
                  </td>
                  <td>{{ order.orderDate }}</td>
                  <td>{{ order.arrivalDate }}</td>
                  <td>{{ order.price }}</td>
                  <td>{{ order.nonSmokingDriver? '+' : '-' }}</td>
                  <td>{{ order.musicStyle }}</td>
                  <td>{{ order.status }}</td>
                </tr>
                </tbody>
              </table>
              <div class="panel-footer">
                <form action="<c:url value="/driver/assigned-order"/>" method="post">
                  <div id="choose-arrive-date" ng-if="order.arrivalDate == null">
                    <label for="arriveDate" class="sr-only">Arrive date</label>
                    <div class="controls input-append date form_datetime"
                         data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                      <b>Enter the time of arrival to the client: </b>
                      <span class="add-on"><i class="icon-th"></i></span>
                      <span class="add-on"><i class="icon-remove"></i></span>
                      <input size="16" type="text" value= "" id="arriveDate" name="arriveDate" required>
                      <input type="hidden" id="dtp_input1" value=""/><br/>
                  </div>
                  </div>
                  <a href="javascript:" onclick="parentNode.submit();"><button type="button" class="btn btn-success btn-lg btn-block">Assign order</button></a>

                  <div hidden>
                    <label for="arriveDateCustomer" class="sr-only">Arrive date</label>
                    <div class="controls input-append date form_datetime"
                         data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                      <span class="add-on"><i class="icon-th"></i></span>
                      <span class="add-on"><i class="icon-remove"></i></span>
                      <input size="16" type="text" value= "{{ order.arrivalDate }}" id="arriveDateCustomer"
                             name="arriveDateCustomer" readonly>
                      <input type="hidden" id="dtp_input2" value=""/><br/>
                    </div>
                  </div>
                  <input type="hidden" name="trackingNumber" value="{{ order.trackingNumber }}">
                  <input type="hidden" name="orderStatus" value="Assign">
                </form>

              </div>
            </div>
          </div>
          <!-- /item -->

        </div>
      </div>
    </section>
    <!-- /Plans -->

    <div class="text-center">
      <dir-pagination-controls boundary-links="true"
                               pagination-id="ordersPagination"
                               on-page-change="pageChanged(newPageNumber)"
                               template-url="<c:url value="/webjars/angular-utils-pagination/0.7.0/dirPagination.tpl.html"/>">
      </dir-pagination-controls>
    </div>
  </div>
  <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<script src="<%=application.getContextPath()%>/resources/admin/js/admin.js"></script>
<script src="<%=application.getContextPath()%>/resources/driver/js/modalOrderInProgress.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=application.getContextPath()%>/resources/js/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="<%=application.getContextPath()%>/resources/js/raphael-min.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/morris.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/morris-data.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=application.getContextPath()%>/resources/js/sb-admin-2.js"></script>

<!-- Bootstrap editable JavaScript-->
<script src="<%=application.getContextPath()%>/resources/js/bootstrap-editable.js"></script>

<script src="<%=application.getContextPath()%>/resources/js/moment.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/js/combodate.js"></script>
<%--<script src="<%=application.getContextPath()%>/resources/js/maps/google-maps-loader.js"></script>--%>

</body>
</html>