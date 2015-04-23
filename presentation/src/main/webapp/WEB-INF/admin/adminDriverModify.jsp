<%@ page import="ua.com.tracksee.entities.ServiceUserEntity" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Vadym Akymov
  Date: 15.04.15
  Time: 1:37
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=application.getContextPath()%>/resources/admin/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=application.getContextPath()%>/resources/admin/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">
    <jsp:include page="adminHeader.jsp"/>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Driver Accounts</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        DataTables Advanced Tables
                    </div>




                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <%
                                List<ServiceUserEntity> drivers = (List<ServiceUserEntity>) request.getAttribute("drivers");
                            %>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                <tr>
                                    <th>email</th>
                                    <th>sex</th>
                                    <th>phone</th>
                                    <th>driver license</th>
                                    <th>registration date</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    for(int i = 0; i < drivers.size(); i++){
                                %>
                                <tr class="odd gradeX">
                                    <td><%=drivers.get(i).getEmail()%></td>
                                    <td>M</td>
                                    <td><%=drivers.get(i).getPhone()%></td>
                                    <td class="center">A</td>
                                    <td class="center">2<%=i%10%>.04.2015</td>
                                </tr>
                                <%
                                    }
                                %>
                                <%--<tr class="even gradeC">--%>
                                    <%--<td>Trident</td>--%>
                                    <%--<td>Internet Explorer 5.0</td>--%>
                                    <%--<td>Win 95+</td>--%>
                                    <%--<td class="center">5</td>--%>
                                    <%--<td class="center">C</td>--%>
                                <%--</tr>--%>
                                <%--<tr class="odd gradeA">--%>
                                    <%--<td>Trident</td>--%>
                                    <%--<td>Internet Explorer 5.5</td>--%>
                                    <%--<td>Win 95+</td>--%>
                                    <%--<td class="center">5.5</td>--%>
                                    <%--<td class="center">A</td>--%>
                                <%--</tr>--%>
                                <%--<tr class="gradeU">--%>
                                    <%--<td>Other browsers</td>--%>
                                    <%--<td>All others</td>--%>
                                    <%--<td>-</td>--%>
                                    <%--<td class="center">-</td>--%>
                                    <%--<td class="center">U</td>--%>
                                <%--</tr>--%>
                                </tbody>
                            </table>
                        </div>



                        <!-- /.table-responsive -->
                        <!--                             <div class="well">
                                                        <h4>DataTables Usage Information</h4>
                                                        <p>DataTables is a very flexible, advanced tables plugin for jQuery. In SB Admin, we are using a specialized version of DataTables built for Bootstrap 3. We have also customized the table headings to use Font Awesome icons in place of images. For complete documentation on DataTables, visit their website at <a target="_blank" href="https://datatables.net/">https://datatables.net/</a>.</p>
                                                        <a class="btn btn-default btn-lg btn-block" target="_blank" href="https://datatables.net/">View DataTables Documentation</a>
                                                    </div> -->





                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>


    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- DataTables JavaScript -->
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="<%=application.getContextPath()%>/resources/admin/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=application.getContextPath()%>/resources/admin/dist/js/sb-admin-2.js"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true,
            serverSide: true,
            ajax: {
                url: '/admin/drivers',
                type: 'POST'
            }
        });
    });
</script>

</body>

</html>
