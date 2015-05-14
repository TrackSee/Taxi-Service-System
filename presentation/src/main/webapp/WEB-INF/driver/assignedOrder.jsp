<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 30.04.2015
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>SB Admin 2 - Bootstrap Admin Theme</title>

  <!-- Bootstrap Core CSS -->
  <link href="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

  <!-- MetisMenu CSS -->
  <link href="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

  <!-- Custom CSS -->
  <link href="<%=application.getContextPath()%>/resources/admin/dist/css/sb-admin-2.css" rel="stylesheet">

  <!-- Custom Fonts -->
  <link href="<%=application.getContextPath()%>/resources/admin/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

  <!-- Bootstrap editable-->
  <link href="<%=application.getContextPath()%>/resources/css/bootstrap-editable.css" rel="stylesheet">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
<div id="wrapper">
  <jsp:include page="driverHeader.jsp"/>

  <div id="page-wrapper">

    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">Assigned order</h1>
      </div>
      <!-- /.col-lg-12 -->
    </div>

    <!-- /.row -->
    <div class="row">
      <div class="col-lg-12">
        <div class="panel panel-default">
          <div class="panel-heading">
            Assigned order
          </div>
                        <!-- Plans -->
              <c:forEach items="${requestScope.orders}" var="order">
                <section id="plans">
                  <div class="container">
                    <div class="row">

                      <!-- item -->
                      <div class="col-md-9 text-center">
                        <div class="panel panel-success panel-pricing">
                          <div class="panel-heading">
                            <c:set var="startPoint" value="${order.itemList[0].path.getStartPoint()}"/>
                            <c:set var="endPoint" value="${order.itemList[0].path.getEndPoint()}"/>
                            <div class="map-canvas">
                              <iframe frameborder="0" width="100%" height="250"
                                      src="https://www.google.com/maps/embed/v1/directions?key=AIzaSyAtwMePDVDymtf-yC-qk1hdmUMnDtGYbb8&mode=driving&origin=${pageScope.startPoint.getX()},${pageScope.startPoint.getY()}&destination=${pageScope.endPoint.getX()},${pageScope.endPoint.getY()}">
                              </iframe>
                            </div>
                          </div>

                          <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                            <thead>
                            <tr>
                              <th>#</th>
                              <th>Order time</th>
                              <th>Price</th>
                              <th>Status</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="odd gradeX">
                              <td>${order.trackingNumber}</td>
                              <td>
                                <label for="arriveDate" class="sr-only">Arrive date</label>

                                <div class="controls input-append date form_datetime" data-date="2015-04-16T05:25:07Z"
                                     data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                                  <span class="add-on"><i class="icon-th"></i></span>
                                  <span class="add-on"><i class="icon-remove"></i></span>
                                  <input size="16" type="text" value= ${order.orderedDate} id="arriveDate" name="arriveDate" readonly>
                                  <input type="hidden" id="dtp_input1" value=""/><br/>

                                </div>
                              </td>
                              <td>${order.price}</td>
                              <td>${order.status}</td>
                            </tr>
                            </tbody>
                          </table>

                          <div class="panel-footer">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                              <tbody>
                              <tr class="odd gradeX">
                                <td> <form action="assigned-order" method="post">
                                  <a href="javascript:;" onclick="parentNode.submit();">
                                    <button type="button" class="btn btn-success">Refuse</button></a>
                                  <input type="hidden" name="trackingNumber" value=${order.trackingNumber}>
                                  <input type="hidden" name="orderStatus" value="Toqueue">
                                </form></td>
                                <td>
                                  <form action="assigned-order" method="post">
                                    <a href="javascript:;" onclick="parentNode.submit();">
                                      <button type="button" class="btn btn-success">Customer not arrived</button></a>
                                    <input type="hidden" name="trackingNumber" value=${order.trackingNumber}>
                                    <input type="hidden" name="orderStatus" value="Refused">
                                  </form>
                                </td>
                                <td>
                                  <c:set var="inputDisplay" value="IN_PROGRESS" />
                                  <c:choose>
                                    <c:when test="${order.status == 'IN_PROGRESS'}">
                                      <button type="button" class="btn btn-warning">In progress</button>
                                    </c:when>
                                    <c:otherwise>
                                      <form action="assigned-order" method="post">
                                        <a href="javascript:;" onclick="parentNode.submit();">
                                          <button type="button" class="btn btn-success">In progress</button></a>
                                        <input type="hidden" name="trackingNumber" value=${order.trackingNumber}>
                                        <input type="hidden" name="orderStatus" value="In progress">
                                      </form>
                                    </c:otherwise>
                                  </c:choose>
                                </td>
                                <td>
                                  <form action="assigned-order" method="post">
                                    <a href="javascript:;" onclick="parentNode.submit();">
                                      <button type="button" class="btn btn-success">Complete</button></a>
                                    <input type="hidden" name="trackingNumber" value=${order.trackingNumber}>
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
              </c:forEach>
              <!-- /Plans -->

          <!-- Pop up-->

      <c:choose>
        <c:when test="${status == 'true'}">
          <script src="<%=application.getContextPath()%>/resources/driver/js/jquery.min.js"></script>
          <script src="<%=application.getContextPath()%>/resources/driver/js/modalOrderInProgress.js"></script>

          </head>
          <body>
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
                  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
              </div>
            </div>
          </div>
        </c:when>
          </c:choose>
          <!-- end pop up -->

              <div class="text-center">
                <ul class="pagination">
                  <%--not ready
                  <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
                    <li class="pageLi${i}"><a class="pageButton" href="#">${i}</a></li>
                  </c:forEach>
                  --%>
                </ul>
              </div>
            </div>
            <!-- /.table-responsive -->

          </div>
          <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
      </div>
      <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->


  </div>
  <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery Version 1.11.2 -->
<script src="<%=application.getContextPath()%>/resources/js/jquery-1.11.2.min.js"></script>

<!--
<script src="../../../resources/js/jquery-1.11.2.js"></script>
<script src="../../../resources/js/jquery.min.js"></script>
<script type="text/javascript" language="javascript" src="../../../resources/js/jquery.js"></script>
-->

<!-- Bootstrap Core JavaScript -->
<script src="<%=application.getContextPath()%>/resources/js/bootstrap3/bootstrap.min.js"></script>

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
