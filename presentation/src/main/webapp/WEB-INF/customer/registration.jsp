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
    <link href="<%=application.getContextPath()%>/resources/customer/css/asteriskRed.css" rel="stylesheet"
          type="text/css"/>
</head>
<body>
<%@include file="../parts/header.jsp" %>

<!-- start: Page Title -->
<div id="page-title">

    <div id="page-title-inner">

        <!-- start: Container -->
        <div class="container">

            <h2><i class="ico-settings ico-white"></i>Registration</h2>

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
            <form id="form-sign-up" class="form-horizontal" action="javascript:void(null);" method="POST"
                  onsubmit="sendForm()">
                <fieldset>
                    <div id="legend">
                        <legend><h2 class="form-sign-in-heading">Please sign up</h2></legend>
                    </div>

                    <div class="control-group">
                        <!-- E-mail -->
                        <label class="control-label" for="email">E-mail</label>

                        <div class="controls">
                            <input type="email" id="email" name="email" class="input-xlarge" required autofocus
                                   placeholder="user@example.com" >
                            <span class="red-star">★</span>
                            <%--<p class="help-block">Please provide your E-mail</p>--%>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Password-->
                        <label class="control-label" for="password">Password</label>

                        <div class="controls">
                            <input type="password" id="password" class="input-xlarge" name="password" required
                                   placeholder="6-28 letters and digits">
                            <span class="red-star">★</span>
                            <%--<p class="help-block">Password should be at least 4 characters</p>--%>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Password -->
                        <label class="control-label" for="repeat-password">Confirm Password</label>

                        <div class="controls">
                            <input type="password" id="repeat-password" name="repeat-password"
                                   placeholder="Repeat your password" class="input-xlarge" required>
                            <span class="red-star">★</span>
                            <%--<p class="help-block">Please confirm password</p>--%>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Username -->
                        <label class="control-label" for="phone-number">Phone number</label>

                        <div class="controls">
                            <input type="text" id="phone-number" name="phone-number"
                                   placeholder="" class="input-xlarge">
                            <%--<p class="help-block">Username can contain any letters or numbers, without spaces</p>--%>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Button -->
                        <div class="controls">
                            <button class="btn btn-success gradient" type="submit">Sign up</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>

    </div>
    <!-- end: Container  -->

</div>
<!-- end: Copyright -->

<%-- start: JavaScript --%>
<%@include file="../parts/scripts.jsp" %>
<script src="<%=application.getContextPath()%>/resources/customer/js/sign-up.js"></script>
<%-- end: JavaScript --%>

<%@include file="../parts/footer.jsp" %>
</body>
</html>