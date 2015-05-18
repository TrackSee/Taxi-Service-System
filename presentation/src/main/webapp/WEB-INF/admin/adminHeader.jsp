<%--
  Created by IntelliJ IDEA.
  User: Vadym_Akymov
  Date: 23.04.15
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/TaxiService">TrackSee</a>
    </div>
    <ul class="nav navbar-top-links navbar-right">
        <!-- /.dropdown -->
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="<%=application.getContextPath()%>/admin/searchdriver">
                    <i class="fa fa-tasks fa-fw"></i> Driver dashboard</a>
                </li>
                <li><a href="<%=application.getContextPath()%>/admin/customerbysearch">
                    <i class="fa fa-tasks fa-fw"></i> Customer dashboard</a>
                </li>
                <li class="divider"></li>
                <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                </li>
            </ul>
            <!-- /.dropdown-user -->
        </li>
        <!-- /.dropdown -->
    </ul>
    <!-- /.navbar-header -->

    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <%--<li class="sidebar-search">--%>
                <%--<div class="input-group custom-search-form">--%>
                <%--<input type="text" class="form-control" placeholder="Search...">--%>
                <%--<span class="input-group-btn">--%>
                <%--<button class="btn btn-default" type="button">--%>
                <%--<i class="fa fa-search"></i>--%>
                <%--</button>--%>
                <%--</span>--%>
                <%--</div>--%>
                <%--<!-- /input-group -->--%>
                <%--</li>--%>

                <li>
                    <a><i class="fa fa-user fa-fw"></i> Driver Accounts<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="<%=application.getContextPath()%>/admin/drivers">List of drivers</a>
                        </li>
                        <li>
                            <a href="<%=application.getContextPath()%>/admin/createdriver">Create driver account</a>
                        </li>
                    </ul>
                </li>
                <%--Car menu--%>
                <li>
                    <a><i class="fa fa-car fa-fw"></i> Cars<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="<%=application.getContextPath()%>/admin/cars">List of cars</a>
                        </li>
                        <li>
                            <a href="<%=application.getContextPath()%>/admin/createcar">Create car</a>
                        </li>
                    </ul>
                </li>
                <%--<li>--%>
                    <%--<a><i class="fa fa-tasks fa-fw"></i> Review Dashboards<span class="fa arrow"></span></a>--%>
                    <%--<ul class="nav nav-second-level">--%>
                        <%--<li>--%>
                            <%--<a href="<%=application.getContextPath()%>/admin/searchdriver">Go to driver dashboard</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<a href="<%=application.getContextPath()%>/admin/customerbysearch">Go to customer--%>
                                <%--dashboard</a>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
                <li>
                    <a><i class="fa fa-bar-chart-o fa-fw"></i> Reports<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="<%=application.getContextPath()%>/admin/report/all">All services reports</a>
                        </li>
                        <li>
                            <a href="<%=application.getContextPath()%>/admin/report/driver-car">Most popular car and
                                driver category</a>
                        </li>
                        <li>
                            <a href="<%=application.getContextPath()%>/admin/report/mostpopularopt">Most popular
                                additional car options</a>
                        </li>
                        <li>
                            <a href="<%=application.getContextPath()%>/admin/report/profitableservice">Most profitable
                                service</a>
                        </li>
                    </ul>
                </li>
                <%--Change Tariff--%>
                <li><a href="<%=application.getContextPath()%>/admin/tariff"><i class="fa fa-dollar fa-fw"></i> Tariffs</a>
                </li>
                <%--block users account--%>
                <li><a href="<%=application.getContextPath()%>/admin/block"><i class="fa fa-lock fa-fw"></i> Block User</a>
                </li>
                <%--groups edit--%>
                <li><a href="<%=application.getContextPath()%>/admin/groups"><i class="fa fa-group fa-fw"></i> Groups
                    and roles</a></li>

                <%--<li><a href="<%=application.getContextPath()%>/"><i class="fa fa-rocket fa-fw"></i> Main Page</a></li>--%>
                <%--Logout from page--%>
                <%--<li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>--%>
            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
</nav>