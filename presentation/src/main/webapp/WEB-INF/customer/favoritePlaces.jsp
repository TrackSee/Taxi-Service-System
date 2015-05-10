<%--
  Created by Ruslan Gunavardana
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" data-ng-app="ticketApp">
<head>
    <%@include file="../parts/meta.jsp" %>
    <%@include file="../parts/bootstrap2.jsp" %>
    <link href='<%=application.getContextPath()%>/resources/customer/css/visible.css' rel='stylesheet' type='text/css'/>
    <link href='<%=application.getContextPath()%>/resources/customer/css/slide-panel.css' rel='stylesheet' type='text/css'/>
</head>
<body>
<%@include file="../parts/header.jsp" %>
<script src="<%=application.getContextPath()%>/resources/customer/js/favorite-places.js"></script>
<table class="table table-hover table-striped">
    <thead>
    </thead>
    <tbody>
    <tr	data-ng-repeat="seat in seats">
        <td>{{seat.id}}</td>
        <td>{{seat.name}}</td>
        <td>\${{seat.price}}</td>
        <td><span class="glyphicon glyphicon-{{seat.booked	?	'ok' : 'remove'}}"></span></td>
        <td>
            <button	type="button" class="btn btn-primary {{seat.booked? 'disabled'	:''}} btn-xs" data-ng-click="bookTicket(seat)">Book</button>
        </td>
    </tr>
    </tbody>
</table>
<%@include file="../parts/scripts.jsp" %>
<script>
    var obj = {name : 'Home', location : { lat : 50.376425, lng : 30.467116} };
    console.log(JSON.stringify(obj));
    $.ajax({
        type: 'POST',
        url: getContextPath() + 'rest/places',
        contentType: 'application/json',
        data: JSON.stringify(obj),
        success: function (data) {
            console.log('success' == data);
            console.log(data);
            if (data != 'error') {
                window.location.replace('.');
            } else {
                $.notify("The username or password is incorrect. Please try again.", "error");
            }
        },
        error: function (xhr, str) {
            $.notify("Internal server error occurred.", "warn");
        }
    });
</script>
<script src="<c:url value="/webjars/angularjs/1.3.15/angular.min.js"/>"></script>
<script src="<c:url value="/webjars/angularjs/1.3.15/angular-resource.min.js"/>"></script>
<script src="<c:url value="/webjars/angularjs/1.3.15/angular-route.min.js"/>"></script>
<script src="<c:url value="/webjars/angular-ui-bootstrap/0.13.0/ui-bootstrap-tpls.min.js"/>"></script>
</body>
