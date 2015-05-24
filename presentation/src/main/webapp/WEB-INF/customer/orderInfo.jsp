<%--

  author: Sharaban Sasha
  Date: 19.04.15
  Time: 20:37
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../parts/meta.jsp" %>
    <%@include file="../parts/bootstrap2.jsp" %>
    <link href='<%=application.getContextPath()%>/resources/customer/css/visible.css' rel='stylesheet'
          type='text/css'/>
    <link href='<%=application.getContextPath()%>/resources/customer/css/hideBlocks.css' rel='stylesheet'
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

        ${updateSuccess}
        ${addCommentsSuccess}
        ${orderSuccess}
        ${orderWarning}
        ${orderDanger}
        ${refuseSuccess}
        ${refuseWarning}
        ${nonExistTrackingNumberDanger}
        ${activeUserOrderWarning}
        ${nonActiveUserOrderWarning}

        <div ${hideOrderTrack}>
            <div class="form-group">
                <form method="post" action="<c:url value="/orderTracking"/>">
                    <label>Tracking number</label>
                    <input type="number" id="trackingNumber" class="form-control" name="trackingNumber"
                           placeholder="Enter your tracking number"
                            title="Only integer number" required>
                    <div class="form-group">
                        <div class="form-group">
                            <button type="submit" class="btn btn-success btn-large">
                                Track your taxi order</button>
                        </div>
                        </div>
                </form>
            </div>
            </div>
        </div>
    </div>


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

        <script src="<%=application.getContextPath()%>http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <%--end jQuery and bootstrap datepicker scripts--%>


</body>
</html>