<%--
  Created by IntelliJ IDEA.
  User: Vadym_Akymov
  Date: 23.04.15
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="index.html">TrackSee</a>
    </div>
    <!-- /.navbar-header -->

    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <li class="sidebar-search">
                    <div class="input-group custom-search-form">
                        <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                    </div>
                    <!-- /input-group -->
                </li>

                <li>
                    <a><i class="fa fa-table fa-fw"></i> Driver Accounts<span class="fa arrow"></span></a>
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
                    <a><i class="fa fa-table fa-fw"></i>Cars<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="<%=application.getContextPath()%>/admin/cars">List of cars</a>
                        </li>
                        <li>
                            <a href="<%=application.getContextPath()%>/admin/createcar">Create car</a>
                        </li>
                    </ul>
                </li>
                <%--block users account--%>
                <li><a href="<%=application.getContextPath()%>/admin/block">Block User</a> </li>
                <%--groups edit--%>
                <li><a href="<%=application.getContextPath()%>/admin/groups">Groups and roles</a> </li>
                <li><a href="<%=application.getContextPath()%>/admin/report/driver-car"><i class="fa fa-sign-out fa-fw"></i> Reports</a></li>
                <%--Logout from page--%>
                <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
</nav>