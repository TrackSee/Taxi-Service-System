<%--
  Created by IntelliJ IDEA.
  User: Oleksandr Kozin
  Date: 18.05.2015
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>All reports</title>
  <!-- Bootstrap Core CSS -->
  <link href="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css"
        rel="stylesheet">

  <!-- MetisMenu CSS -->
  <link href="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.css"
        rel="stylesheet">

  <!-- Custom CSS -->
  <link href="<%=application.getContextPath()%>/resources/admin/dist/css/sb-admin-2.css" rel="stylesheet">

  <!-- Custom Fonts -->
  <link href="<%=application.getContextPath()%>/resources/admin/bower_components/font-awesome/css/font-awesome.min.css"
        rel="stylesheet" type="text/css">

  <!-- Datepicker CSS -->
  <link href="<%=application.getContextPath()%>/resources/bootstrap-datepicker-1.4.0/css/bootstrap-datepicker.min.css"
        rel="stylesheet" media="screen">

  <%--Reports CSS--%>
  <link href="<%=application.getContextPath()%>/resources/admin/reports/css/popularDriver.css" type="text/css"
        rel="stylesheet">
  <link href="<%=application.getContextPath()%>/resources/admin/reports/css/popularCar.css" type="text/css"
        rel="stylesheet">
  <link href="<%=application.getContextPath()%>/resources/admin/reports/css/musicOverall.css" type="text/css"
        rel="stylesheet">
  <link href="<%=application.getContextPath()%>/resources/admin/reports/css/additionalOptOverall.css" type="text/css"
        rel="stylesheet">
  <link href="<%=application.getContextPath()%>/resources/admin/reports/css/musicCustomer.css" type="text/css"
        rel="stylesheet">
  <link href="<%=application.getContextPath()%>/resources/admin/reports/css/additionalOptCustomer.css" type="text/css"
        rel="stylesheet">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
<div id="wrapper">
  <jsp:include page="../adminHeader.jsp"/>

  <div id="page-wrapper">

    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">Services reports</h1>
      </div>
    </div>

    <div class="row">
      <div class="col-lg-6 col-md-6">
        <div class="panel panel-green">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-3">
                <i class="fa fa-taxi fa-5x"></i>
              </div>
              <div class="col-xs-9 text-right">
                <div class="huge">514</div>
                <div>New orders per period</div>
              </div>
            </div>
          </div>
          <div class="panel-footer">
            <div class="input-daterange input-group" id="datepicker">
              <input type="text" class="input-sm form-control" name="start"/>
              <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
              <span class="input-group-addon">to</span>
              <input type="text" class="input-sm form-control" name="end"/>
              <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
            </div>
            <br>
            <button type="button" class="btn btn-primary btn-block">Get Result</button>
          </div>
        </div>
      </div>

      <div class="col-lg-6 col-md-6">
        <div class="panel panel-yellow">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-3">
                <i class="fa fa-money fa-5x"></i>
              </div>
              <div class="col-xs-9 text-right">
                <div class="huge">1257</div>
                <div>Service profitability by month</div>
              </div>
            </div>
          </div>
          <div class="panel-footer">
            <div class="input-group date">
              <input type="text" class="form-control">
              <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
            </div>
            <br>
            <button type="button" class="btn btn-primary btn-block">Get Result</button>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-lg-12">
        <div class="panel panel-default">
          <div class="panel-heading">
            <i class="fa fa-table fa-fw"></i> Most profitable taxi service
          </div>
          <div class="table-responsive">
            <table class="table table-bordered table-hover table-striped">
              <thead>
              <tr>
                <th></th>
                <th>Simple taxi</th>
                <th>Sober driver</th>
                <th>Convey employees</th>
                <th>Guest delivery</th>
                <th>Cargo taxi</th>
                <th>Taxi for long term</th>
                <th>Meet my guest</th>
                <th>Celebration taxi</th>
                <th>Foodstuff delivery</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <th>Week</th>
                <td>250</td>
                <td>100</td>
                <td>150</td>
                <td>125</td>
                <td>300</td>
                <td>180</td>
                <td>269</td>
                <td>470</td>
                <td>90</td>
              </tr>
              <tr>
                <th>Month</th>
                <td>900</td>
                <td>840</td>
                <td>1120</td>
                <td>1301</td>
                <td>786</td>
                <td>1455</td>
                <td>954</td>
                <td>1420</td>
                <td>650</td>
              </tr>
              <tr>
                <th>Year</th>
                <td>1020</td>
                <td>1150</td>
                <td>1465</td>
                <td>1245</td>
                <td>1025</td>
                <td>954</td>
                <td>1360</td>
                <td>1820</td>
                <td>856</td>
              </tr>
              <tr>
                <th>Decade</th>
                <td>10200</td>
                <td>11500</td>
                <td>14650</td>
                <td>12455</td>
                <td>10250</td>
                <td>9540</td>
                <td>13600</td>
                <td>18250</td>
                <td>8560</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-lg-12">
        <div class="panel panel-default">
          <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i> Most popular driver and car category
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-lg-6">
                <div class="panel-body">
                  <div id="popularDriverDiv"></div>
                </div>
              </div>
              <div class="col-lg-6">
                <div class="panel-body">
                  <div id="popularCarDiv"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-lg-12">
        <div class="panel panel-default">
          <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i>
            Most popular music style and additional car options overall
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-lg-7">
                <div class="panel-body">
                  <div id="musicOverallDiv"></div>
                </div>
              </div>
              <div class="col-lg-5">
                <div class="panel-body">
                  <div id="additionalOptOverallDiv"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-lg-12">
        <div class="panel panel-default">
          <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i>
            Most popular music style and additional car options for current customer
          </div>
          <div class="panel-body">
            <form class="popularOpt" id="popularOpt" action="<c:url value="admin/report/all"/>"
                  method="post">
              <div class="userChoose">
                <div>
                  Choose customer
                </div>
                <div class="row">
                  <div class="col-lg-10">
                    <select class="make form-control taxiCar" name="user">
                      <c:forEach items="${requestScope.users}" var="user">
                        <option value="${user.userId}">${user.email}</option>
                      </c:forEach>
                    </select>
                  </div>
                  <div class="col-lg-2">
                    <button id="getResult" type="button" class="btn btn-primary">Get Result</button>
                  </div>
                </div>
              </div>
            </form>
            <div class="row">
              <div class="col-lg-7">
                <div class="panel-body">
                  <div id="musicCustomerDiv"></div>
                </div>
              </div>
              <div class="col-lg-5">
                <div class="panel-body">
                  <div id="additionalOptCustomerDiv"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>


  <!-- jQuery -->
  <script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>
  <script type="text/javascript" src="http://www.amcharts.com/lib/3/amcharts.js"></script>
  <script type="text/javascript" src="http://www.amcharts.com/lib/3/pie.js"></script>
  <script type="text/javascript" src="http://www.amcharts.com/lib/3/serial.js"></script>
  <script type="text/javascript" src="http://www.amcharts.com/lib/3/themes/light.js"></script>
  <script type="text/javascript" src="http://www.amcharts.com/lib/3/themes/dark.js"></script>
  <script type="text/javascript" src="http://amcharts.com/lib/3/plugins/export/export.js"></script>
  <link href="http://amcharts.com/lib/3/plugins/export/export.css" rel="stylesheet" type="text/css">

  <!-- Bootstrap Datepicker -->
  <script type="text/javascript"
          src="<%=application.getContextPath()%>/resources/bootstrap-datepicker-1.4.0/js/bootstrap-datepicker.min.js"
          charset="UTF-8"></script>
  <script type="text/javascript"
          src="<%=application.getContextPath()%>/resources/bootstrap-datepicker-1.4.0/js/newOrders-datepicker.js"
          charset="UTF-8"></script>
  <script type="text/javascript"
          src="<%=application.getContextPath()%>/resources/bootstrap-datepicker-1.4.0/js/monthProfit-datepicker.js"
          charset="UTF-8"></script>

  <%--Reports JS--%>
  <script src="<%=application.getContextPath()%>/resources/admin/reports/js/driverAndCarReport.js"></script>
  <script src="<%=application.getContextPath()%>/resources/admin/reports/js/popularDriver.js"></script>
  <script src="<%=application.getContextPath()%>/resources/admin/reports/js/popularCar.js"></script>
  <script src="<%=application.getContextPath()%>/resources/admin/reports/js/musicOverall.js"></script>
  <script src="<%=application.getContextPath()%>/resources/admin/reports/js/additionalOptOverall.js"></script>
  <script src="<%=application.getContextPath()%>/resources/admin/reports/js/musicCustomer.js"></script>
  <script src="<%=application.getContextPath()%>/resources/admin/reports/js/additionalOptCustomer.js"></script>

  <!-- Bootstrap Core JavaScript -->
  <script src="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

  <!-- Metis Menu Plugin JavaScript -->
  <script src="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>

  <!-- Custom Theme JavaScript -->
  <script src="<%=application.getContextPath()%>/resources/admin/dist/js/sb-admin-2.js"></script>

</body>
</html>
