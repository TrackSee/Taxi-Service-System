<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 30.04.2015
  Time: 11:25
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

  <%--<!-- Bootstrap editable-->--%>
  <%--<link href="<%=application.getContextPath()%>/resources/css/bootstrap-editable.css" rel="stylesheet">--%>

  <%--START JS for pagination--%>
  <%@ include file="../parts/scripts.jsp"%>
  <script src="http://maps.googleapis.com/maps/api/js?v=3.19&key=AIzaSyAtwMePDVDymtf-yC-qk1hdmUMnDtGYbb8&libraries=geometry"></script>
  <script src="<c:url value="/webjars/angularjs/1.3.15/angular.min.js"/>"></script>
  <script src="<c:url value="/webjars/angular-utils-pagination/0.7.0/dirPagination.js"/>"></script>
  <script src="<%=application.getContextPath()%>/resources/driver/js/assigned-orders-pagination.js"></script>
  <%--END JS for pagination--%>
  
  <script>
    window.onbeforeunload = close;
    function close(){
      $.ajax({
        type: 'POST',
        async: false,
        url: '../admin/userdash',
        success: function() {

        }
      });
    }
  </script>
  
</head>
<body>
<div id="wrapper" ng-app="driver" ng-controller="assignedOrdersController">

  <c:set var="ID_USER_VALUE" value="${sessionScope.get('isAdmin')}" />
  <c:if test="${ID_USER_VALUE == null}" >
    <jsp:include page="driverHeader.jsp"/>
  </c:if>

  <div id="page-wrapper">

    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">Assigned orders</h1>
      </div>
      <!-- /.col-lg-12 -->
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
              <div class="panel-heading">
                <div class="map-canvas">
                  <iframe frameborder="0" width="825" height="250" ng-src="{{ order.map }}"></iframe>'
                </div>
              </div>

              <table class="table table-striped table-bordered table-hover">
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
                  <td ng-bind="order.trackingNumber"></td>
                  <td ng-bind="order.service"></td>
                  <td ng-if="order.service == 'Celebration taxi' || order.service == 'Taxi for long term'"
                      ng-bind="order.amountOfHours + ':' + order.amountOfMinutes">
                  </td>
                  <td ng-bind="order.orderDate"></td>
                  <td ng-bind="order.arrivalDate"></td>
                  <td ng-bind="order.price"></td>
                  <td ng-bind="order.nonSmokingDriver? '+' : '-'"></td>
                  <td ng-bind="order.musicStyle"></td>
                  <td ng-bind="order.status"></td>
                </tr>
                </tbody>
              </table>

              <div class="panel-footer">
                <table class="table table-striped table-bordered table-hover">
                  <tbody>
                  <tr class="even gradeX">
                    <td> <form action="<c:url value="/driver/assigned-order"/>" method="post">
                      <a href="javascript:" onclick="parentNode.submit();">
                        <button type="button" class="btn btn-danger">Refuse</button>
                      </a>
                      <input type="hidden" name="trackingNumber" value="{{ order.trackingNumber }}">
                      <input type="hidden" name="orderStatus" value="Toqueue">
                    </form></td>
                    <td>
                      <form action="<c:url value="/driver/assigned-order"/>" method="post">
                        <a href="javascript:" onclick="parentNode.submit();">
                          <button type="button" class="btn btn-info">Customer not arrived</button></a>
                        <input type="hidden" name="trackingNumber" value="{{ order.trackingNumber }}">
                        <input type="hidden" name="orderStatus" value="Refused">
                      </form>
                    </td>
                    <td>
                      <button ng-if="order.status == 'In progress'" type="button" class="btn btn-warning">In progress</button>
                      <form ng-if="order.status != 'In progress'" action="<c:url value="/driver/assigned-order"/>" method="post">
                        <a href="javascript:" onclick="parentNode.submit();">
                          <button type="button" class="btn btn-primary">In progress</button></a>
                        <input type="hidden" name="trackingNumber" value="{{ order.trackingNumber }}">
                        <input type="hidden" name="orderStatus" value="In progress">
                      </form>
                    </td>
                    <td ng-if="order.status == 'In progress'">
                      <form action="<c:url value="/driver/assigned-order"/>" method="post">
                        <a href="javascript:" onclick="parentNode.submit();">
                          <button type="button" class="btn btn-success">Complete</button></a>
                        <input type="hidden" name="trackingNumber" value="{{ order.trackingNumber }}">
                        <input type="hidden" name="orderStatus" value="Completed">
                      </form>
                    </td>
                    </tr>
                  </tbody>
                  </table>
              </div>
            </div>
          </div>
          <!-- /item -->

        </div>
      </div>
    </section>
    <!-- /Plans -->
    
    <c:if test="${ID_USER_VALUE != null}" >
      <button type="button" class="btn btn-default" onclick="closeAndGo();">Back</button>
    </c:if>

    <%--START Pagination--%>
    <div class="text-center">
      <dir-pagination-controls boundary-links="true"
                               pagination-id="ordersPagination"
                               on-page-change="pageChanged(newPageNumber)"
                               template-url="<c:url value="/webjars/angular-utils-pagination/0.7.0/dirPagination.tpl.html"/>">
      </dir-pagination-controls>
    </div>
    <%--END Pagination--%>


    <!-- Pop up-->
    <c:choose>
      <c:when test="${status == 'true'}">
    <div id="myModal" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title">Warning</h4>
          </div>
          <div class="modal-body">
            <p>Please be careful, you can not have two orders in "in progress" state!</p>
          </div>
          <div class="modal-footer">
            <form action="#" method="post">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <input type="hidden" name="status" value="false">
            </form>
          </div>
        </div>
      </div>
    </div>
      </c:when>
    </c:choose>
    <!-- end pop up -->

  </div>
  <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<%@ include file="../parts/scripts.jsp"%>
<c:if test="${ID_USER_VALUE != null}" >
  <script>
    $("form").hide();
  </script>
</c:if>

<script>  
  function closeAndGo() {
    close();
    window.location.replace('../admin/searchdriver');
  }
</script>

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

</body>

</html>
