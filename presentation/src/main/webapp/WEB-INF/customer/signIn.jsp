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
</head>
<body>
<%@include file="../parts/header.jsp" %>

<!-- start: Page Title -->
<div id="page-title">

    <div id="page-title-inner">

        <!-- start: Container -->
        <div class="container">
            <h2><i class="ico-settings ico-white"></i>Login</h2>
        </div>
        <!-- end: Container  -->
    </div>

</div>
<!-- end: Page Title -->

<!--start: Wrapper-->
<div id="wrapper">

    <!--start: Container -->
    <div class="container">

        <!-- start: Row -->
        <div class="row">
            <form id="form-sign-in" class="form-horizontal" action="javascript:void(null);" method="POST">
                <fieldset>
                    <div id="legend">
                        <legend><h2 class="form-sign-in-heading">Please sign in</h2></legend>
                    </div>

                    <div class="control-group">
                        <%-- Email--%>
                        <label class="control-label" for="email">E-mail</label>

                        <div class="controls">
                            <input type="email" id="email" name="email" placeholder="Email address"
                                   class="input-xlarge" required>
                        </div>
                    </div>

                    <div class="control-group">
                        <%-- Password--%>
                        <label class="control-label" for="password">Password</label>

                        <div class="controls">
                            <input type="password" id="password" name="password" placeholder="Password"
                                   class="input-xlarge" required>
                        </div>
                    </div>


                    <div class="control-group">
                        <%-- Button --%>
                        <div class="controls">
                            <button id="sign-in-submit" class="btn btn-success" type="submit">Login</button>
                            <button class="btn btn-info" type="button" onclick="window.location.replace('signup')">Sign up</button>
                        </div>
                    </div>
                </fieldset>
            </form>

        </div>
        <!-- end: Container  -->

    </div>
</div>

<%-- start: JavaScript --%>
<%@include file="../parts/scripts.jsp" %>
<script src="<%=application.getContextPath()%>/resources/customer/js/sign-in.js"></script>
<script>$('#form-sign-in').validate();</script>
<c:if test="${param.error}"><script>$.notify('Sign up failed!', 'error');</script></c:if>
<%-- end: JavaScript --%>

<%@include file="../parts/footer.jsp" %>

</body>
</html>