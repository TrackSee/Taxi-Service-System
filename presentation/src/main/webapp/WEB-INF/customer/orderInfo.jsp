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

    <%@include file="../parts/footer.jsp" %>


<!-- end: Wrapper  -->
        <%@include file="../parts/scripts.jsp" %>
        <script src="<%=application.getContextPath()%>/resources/customer/js/slide-panel.js"></script>
</body>
</html>