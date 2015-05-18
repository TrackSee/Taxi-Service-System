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
            <form id="form-sign-up" class="form-horizontal" method="POST" action="javascript:void(null);" onsubmit="sendForm()">
                <fieldset>
                    <div id="legend">
                        <legend><h2 class="form-sign-in-heading">Please sign up</h2></legend>
                    </div>

                    <div class="control-group">
                        <%-- E-mail --%>
                        <label class="control-label" for="email">
                            E-mail
                            <span class="red-star">★</span>
                        </label>

                        <div class="controls">
                            <input type="email" id="email" name="email" class="input-xlarge" required autofocus
                                   placeholder="user@example.com" >
                        </div>
                    </div>

                    <div class="control-group">
                        <%-- Password --%>
                        <label class="control-label" for="password">
                            Password
                            <span class="red-star">★</span>
                        </label>

                        <div class="controls">
                            <input type="password" id="password" class="input-xlarge" name="password" required
                                   placeholder="6-28 letters and digits">
                        </div>
                    </div>

                    <div class="control-group">
                        <%-- Repeat password --%>
                        <label class="control-label" for="repeat-password">
                            Confirm Password
                            <span class="red-star">★</span>
                        </label>

                        <div class="controls">
                            <input type="password" id="repeat-password" name="repeat-password" required
                                   placeholder="Repeat your password" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <%-- Phone --%>
                        <label class="control-label" for="phone-number">Phone number</label>

                        <div class="controls">
                            <input type="text" id="phone-number" name="phone-number"
                                   placeholder="(+380) 00 000-00-00" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <%-- First name --%>
                        <label class="control-label" for="first-name">First name</label>

                        <div class="controls">
                            <input type="text" id="first-name" name="first-name"
                                   placeholder="Albert" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <%-- Last name --%>
                        <label class="control-label" for="last-name">Last name</label>

                        <div class="controls">
                            <input type="text" id="last-name" name="last-name"
                                   placeholder="Einstein" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                            <button id="sign-up-submit" class="btn btn-success gradient" type="submit">Sign up</button>
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