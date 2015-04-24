<%--
  Created by IntelliJ IDEA.
  User: kstes_000
  Date: 24-Apr-15
  Time: 09:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

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

<h1>Hello Dima</h1>
<div id="wrapper">
    <jsp:include page="adminHeader.jsp"/>
    </div>
<div class="container" style="margin-left: 20%">

    <form class="form-sign-up" id ="createDriver" action="<c:url value="/admin/createdriver"/>" method="post">
    <h2 class="form-sign-up-heading">Please enter drivers data</h2>

    <input type="email" id="email" name="email" class="form-control" placeholder="Email address" required autofocus>

    <label for="password" class="sr-only">Password</label>
    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>

    <label for="phone-number" class="sr-only">Phone number</label>
    <input type="text" id="phone-number" name="phone-number" class="form-control" placeholder="Phone number">
    <div id="addDriver">
          <button class="btn btn-lg btn-primary btn-block" type="submit">Create</button>
    </div>
    </form>
</div>
<script>
    var my = {numberId: "1", companyId : "531"};
    $.ajax({
        type: 'POST',
        url: '/admin/createdriver',
        // dataType: 'json',
        data : {myVari = my}
    });
//    $(document).ready(function() {
//        $('#addDriver').bind('click', function(){
//            var data = {};
//            data["email"] = $('input[name = email]', '#createDriver').val();
//            data["password"] = $('input[name = password]', '#createDriver').val();
//            data["phone-number"] = $('input[name = phone-number]', '#createDriver').val();
//
//            //data = JSON.stringify(data);
//            $.ajax({
//                type: 'POST',
//                url: '/admin/createdriver',
//               // dataType: 'json',
//                data: data
//            });
//        })
//    });
</script>
</body>
</html>
