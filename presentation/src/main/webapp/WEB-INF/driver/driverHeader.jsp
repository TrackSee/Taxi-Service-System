<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 29.04.2015
  Time: 20:32
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
          <a href="/TaxiService/driver"><i class="fa fa-table fa-fw"></i> Taxi orders</a>
        </li>
        <li>
          <a href="/TaxiService/history-of-orders"><i class="fa fa-table fa-fw"></i> History of orders</a>
        </li>
        <li>
          <a href="/TaxiService/driver-profile"><i class="fa fa-table fa-fw"></i> Your profile</a>
        </li>
      </ul>
        <%--Logout from page--%>
        <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
        </li>
      </ul>
    </div>
    <!-- /.sidebar-collapse -->
  </div>
  <!-- /.navbar-static-side -->
</nav>