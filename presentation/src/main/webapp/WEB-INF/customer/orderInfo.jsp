<%--

  author: Sharaban Sasha
  Date: 19.04.15
  Time: 20:37
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../parts/meta.jsp" %>
    <%@include file="../parts/bootstrap2.jsp" %>
    <link href='<%=application.getContextPath()%>/resources/customer/css/visible.css' rel='stylesheet'
          type='text/css'/>
    <link href='<%=application.getContextPath()%>/resources/customer/css/slide-panel.css' rel='stylesheet'
          type='text/css'/>
</head>
<body>
<%@include file="../parts/header.jsp" %>
<!-- start: Page Title -->
<div id="page-title">
    <div id="page-title-inner">
        <!-- start: Container -->
        <div class="container">
            <h2><i class="ico-stats ico-white"></i>Order information</h2>
        </div>
        <!-- end: Container  -->
    </div>
</div>
<!-- end: Page Title -->
<!--start: Wrapper-->
<div id="wrapper">
    <!--start: Container -->
    <div class="container">

        <div class="title"><h3>Information about order</h3></div>

    <div id="hideSuccessUpdate${successUpdate}">
        <div class="alert alert-success" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span></button><h3>
            Your order has been updated successfully!
        </h3></div>
    </div>
    <div id="hideSuccessAddComments${successAddComments}">
        <div class="alert alert-success" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span></button><h3>
            Comments for yours order has been added successfully
        </h3></div>
    </div>
        <div id="hideUnregisteredSuccess${showUnregisteredSuccess}">
            <div class="alert alert-success" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button><h3>
                Your order accepted for further processing successfully and you was assigned to such tracking number:
                ${trackingNumber}
            </h3>
                <a class="btn btn-large btn-success" href="orderInfo"><h4 >Track your taxi order</h4></a>
            </div>
        </div>
       ${showSuccess}

        <div id="hideError${showError}">
            <div class="alert alert-danger" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button><h3>
                Your order has been rejected because you refused three orders that you have made and so you been put
                in the black list:
            </h3></div>
        </div>
        <div id="hideRefuseSuccess${showRefuseSuccess}">
            <div class="alert alert-success" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button><h3>
                Your order has been refused!
            </h3></div>
        </div>
        <div id="hideRefuseError${showRefuseError}">
            <div class="alert alert-danger" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button><h3>
                Your order with tracking number: ${trackingNumber} has not been canceled,
                if you do not change your mind try again.
            </h3></div>
            <a class="btn btn-large btn-success" href="orderInfo"><h4 class="outline">Track your taxi order</h4></a>
        </div>
        <div ${hideOrderTrack}>
            <div class="form-group">
                <form method="post" action="<c:url value="/orderTracking"/>">
                    <label>Tracking number</label>
                    <input type="number" id="origin" class="form-control" name="orderTrackingNumber"
                           placeholder="Enter your tracking number" required>
                    <div class="form-group">
                        <button type="submit" class="btn btn-danger btn-large">Track your order</button>
                    </div>
                </form>
            </div>
            </div>
        </div>
    </div>
            <%--<form method="post" action="<c:url value="/report"/>">--%>
            <%--<label>--%>
            <%--</label>--%>
            <%--<div class="form-group">--%>
            <%--<button type="submit" class="btn btn-danger btn-large">Get report</button>--%>
            <%--</div>--%>
            <%--</form>--%>

        <p></p>
        <!-- end: Wrapper  -->
        <script src="<%=application.getContextPath()%>/resources/customer/js/slide-panel.js"></script>
        <script type="text/javascript" src="<%=application.getContextPath()%>/resources/js/jquery-1.8.3.min.js"
                charset="UTF-8"></script>

        <%@include file="../parts/scripts.jsp" %>
        <%@include file="../parts/footer.jsp" %>
        <!-- Load jQuery and bootstrap datepicker scripts -->
        <script type="text/javascript" src="<%=application.getContextPath()%>/resources/js/jquery-1.8.3.min.js"
                charset="UTF-8"></script>
        <script type="text/javascript" src="<%=application.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="<%=application.getContextPath()%>/resources/js/bootstrap-datetimepicker.js"
                charset="UTF-8"></script>
        <script type="text/javascript"
                src="<%=application.getContextPath()%>/resources/js/locales/bootstrap-datetimepicker.fr.js"
                charset="UTF-8"></script>
        <script type="text/javascript"
                src="<%=application.getContextPath()%>/resources/js/locales/bootstrap-datetimepicker.fr.js"
                charset="UTF-8"></script>
        <script type="text/javascript"
                src="<%=application.getContextPath()%>/resources/js/date-picker-order-complete.js"
                charset="UTF-8"></script>
        <script src="<%=application.getContextPath()%>http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <%--end jQuery and bootstrap datepicker scripts--%>

        <%--Google maps scripts--%>
        <script src="<%=application.getContextPath()%>/resources/customer/js/google-maps.js"></script>
        <%--end google maps scripts--%>

        <%-- Order page scripts --%>
        <script src="<%=application.getContextPath()%>/resources/customer/js/order.js"></script>
        <script src="<%=application.getContextPath()%>/resources/customer/js/slide-panel.js"></script>
        <%--end order oage scripts--%>

</body>
</html>