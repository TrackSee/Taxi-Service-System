<%--
  Created by IntelliJ IDEA.
  User: Maria Komar
  Date: 19.04.2015
  Time: 21:06
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

  <%--My resources--%>
  <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/admin/css/admin.css">
  <script href="<%=application.getContextPath()%>/resources/admin/js/admin.js"></script>

  <!-- Custom Fonts -->
  <link href="<%=application.getContextPath()%>/resources/admin/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

  <link href="<%=application.getContextPath()%>/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet"
        media="screen">

  <link href="<%=application.getContextPath()%>/resources/driver/css/map-canvas.css" rel="stylesheet"
        media="screen">

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
        <h1 class="page-header">Free orders</h1>
      </div>
      <!-- /.col-lg-12 -->
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
                            <th>Car arrive time</th>
                            <th>Price</th>
                            <th>Non smoking driver</th>
                            <th>Music style</th>
                            <th>Status</th>
                          </tr>
                          </thead>
                          <tbody>
                            <tr class="odd gradeX">
                              <td>${order.trackingNumber}</td>
                              <td> <fmt:formatDate value="${order.orderedDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                              <td>
                                <fmt:formatDate value="${order.arriveDate}" pattern="yyyy" var="testDate" />

                                <c:choose>
                                  <c:when test="${testDate > 1900}">
                                  <fmt:formatDate value="${order.arriveDate}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </c:when>

                                <c:otherwise>
                                  <label for="arriveDate" class="sr-only">Arrive date</label>
                                  <div class="controls input-append date form_datetime"
                                       data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                                    <span class="add-on"><i class="icon-th"></i></span>
                                    <span class="add-on"><i class="icon-remove"></i></span>
                                    <input size="16" type="text" id="arriveDate" name="arriveDate" readonly>
                                    <input type="hidden" id="dtp_input1" /><br/>

                                  </div>
                                </c:otherwise>
                                </c:choose>
                              </td>
                              <td>${order.price}</td>
                              <td>${order.nonSmokingDriver==true ? "+" : "-"}</td>
                              <td>${order.musicStyle}</td>
                              <td>${order.status}</td>
                            </tr>
                          </tbody>
                        </table>

                        <div class="panel-footer">
                          <form action="assigned-order" method="post">
                            <a href="javascript:;" onclick="parentNode.submit();"><button type="button" class="btn btn-success btn-lg btn-block">Assign order</button></a>
                            <input type="hidden" name="trackingNumber" value=${order.trackingNumber}>
                            <input type="hidden" name="arriveDateCustomer" value=${order.arriveDate}">
                            <input type="hidden" name="orderStatus" value="Assign">
                          </form>

                        </div>
                      </div>
                    </div>
                    <!-- /item -->

                  </div>
                </div>
              </section>
            </c:forEach>
              <!-- /Plans -->

              <div class="text-center">
                <ul class="pagination">
                  <%--<li class="active"><a href="1">1</a></li>--%>
                  <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
                    <li class="pageLi${i}"><a class="pageButton" href="#">${i}</a></li>
                  </c:forEach>
                  <%--<li><a class="pageButton" href="#">2</a></li>--%>
                  <%--<li><a href="#">3</a></li>--%>
                  <%--<li><a href="#">4</a></li>--%>
                  <%--<li><a href="#">5</a></li>--%>
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

<!-- jQuery Version 1.11.2
<script src="<%=application.getContextPath()%>/resources/js/jquery-1.11.2.min.js"></script> -->
<!--
<script src="../../../resources/js/jquery-1.11.2.js"></script>
<script src="../../../resources/js/jquery.min.js"></script>
<script type="text/javascript" language="javascript" src="../../../resources/js/jquery.js"></script>
-->

<!-- jQuery -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%=application.getContextPath()%>/resources/js/bootstrap2/bootstrap.min.js"></script>

<script type="text/javascript" src="<%=application.getContextPath()%>/resources/js/bootstrap-datetimepicker.js"
        charset="UTF-8"></script>
<script type="text/javascript"
        src="<%=application.getContextPath()%>/resources/js/date-picker-order-complete.js"
        charset="UTF-8"></script>

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


</body>

</html>
