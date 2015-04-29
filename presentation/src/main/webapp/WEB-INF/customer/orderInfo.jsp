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

                    </p>
                </div>
                <!-- end: About Us -->

                <!-- start: Team -->
                <div id="team">

                    <div class="title"><h3>Information about assigned driver</h3></div>

                    <!-- start: Row -->
                    <div class="row">

                        <!-- start: About Member -->
                        <div class="span4">
                            <div class="avatar view-team">
                                <img src="<%=application.getContextPath()%>/resources/img/team_member.jpg"
                                     alt="team member">

                                <div class="mask">
                                    <h2>About driver</h2>

                                    <p>
                                        Information about driver.Information about driver.Information about
                                        driver.Information about driver.Information about driver.Information about
                                        driver.Information about driver.Information about driver.Information about
                                        driver.Information about driver.Information about driver.Information about
                                        driver.
                                    </p>
                                    <a href="#" class="info">Some additional information on another page</a>
                                </div>
                            </div>
                            <div class="clear"></div>
                            <div class="team-name">Bill Budrov <span> / Taxi drover</span></div>
                        </div>
                        <!-- end: About Member -->
                        <!-- end: About Member -->

                    </div>
                    <!-- end: Row -->

                </div>
                <!-- end: Team -->


            </div>

            <div class="span4">

                <!-- start: Sidebar -->
                <div id="sidebar">

                    <!-- start: Skills -->
                    <div class="title"><h3>Information about ....</h3></div>
                    <ul class="progress-bar">
                        <li>
                            <h5>Some information ( 40% )</h5>

                            <div class="meter"><span style="width: 40%"></span></div>
                            <!-- Edite width here -->
                        </li>
                        <li>
                            <h5>Some information ( 80% )</h5>

                            <div class="meter"><span style="width: 80%"></span></div>
                            <!-- Edite width here -->
                        </li>
                        <li>
                            <h5>Some information ( 100% )</h5>

                            <div class="meter"><span style="width: 100%"></span></div>
                            <!-- Edite width here -->
                        </li>
                        <li>
                            <h5>Some information ( 60% )</h5>

                            <div class="meter"><span style="width: 60%"></span></div>
                            <!-- Edite width here -->
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- end: Wrapper  -->

<%@include file="../parts/scripts.jsp" %>
<%@include file="../parts/footer.jsp" %>

</body>
</html>