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
            <h2><i class="ico-stats ico-white"></i>Test export to excel page</h2>
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

            <form method="post" action="<c:url value="/report"/>">
            <label>
            </label>
            <div class="form-group">
            <button type="submit" class="btn btn-info btn-large">Get  simple report</button>
            </div>
            </form>

        <form method="post" action="<c:url value="/reportComplicate"/>">
            <label>
            </label>
            <div class="form-group">
                <button type="submit" class="btn btn-info btn-large">Get  complicate report</button>
            </div>
        </form>
</div>
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
    <script src="<%=application.getContextPath()%>/resources/customer/js/maps/google-maps-loader.js"></script>
    <script src="<%=application.getContextPath()%>/resources/customer/js/maps/order-maps.js"></script>
    <%--end google maps scripts--%>

    <%-- Order page scripts --%>
    <script src="<%=application.getContextPath()%>/resources/customer/js/order.js"></script>
    <script src="<%=application.getContextPath()%>/resources/customer/js/slide-panel.js"></script>
    <%--end order oage scripts--%>

</body>
</html>