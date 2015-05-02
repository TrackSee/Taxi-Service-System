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

        <!--start: Row -->
        <div class="row">

            <div class="span8">

                <!-- start: About Us -->
                <div id="about">
                    <div class="title"><h3>Information about order</h3></div>
                    <p>
                        ${successAlert}
                    <form method="post" action="<c:url value="/order/info"/>">

                        <div class="form-group">
                            <button type="submit" class="btn btn-danger btn-large">Refuse order</button>
                        </div>
                    </form>
                    </p>
                </div>
                <!-- end: About Us -->
    </div>
</div>
<!-- end: Wrapper  -->

<%@include file="../parts/scripts.jsp" %>
<%@include file="../parts/footer.jsp" %>

</body>
</html>