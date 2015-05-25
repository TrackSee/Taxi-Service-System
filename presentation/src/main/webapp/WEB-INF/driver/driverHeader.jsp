  <%--
    Created by IntelliJ IDEA.
    User: maria
    Date: 29.04.2015
    Time: 20:32
    To change this template use File | Settings | File Templates.
  --%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!-- Navigation -->
  <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<c:url value="/"/>">TrackSee</a>
    </div>
    <!-- /.navbar-header -->

    <div class="navbar-default sidebar" role="navigation">
      <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
          <li>
            <a href="<c:url value="/driver/free-orders"/>"><i class="fa fa-cab fa-fw"></i> Taxi orders</a>
          </li>
          <li>
            <a href="<c:url value="/driver/assigned-order"/>"><i class="fa fa-gear fa-fw"></i> Assigned order</a>
          </li>
          <li>
            <a href="<c:url value="/driver/history-of-orders"/>"><i class="fa fa-trophy fa-fw"></i> History of orders</a>
          </li>
          <li>
            <a href="<c:url value="/driver/driver-profile"/>"><i class="fa fa-user fa-fw"></i> Your profile</a>
          </li>
          <%--Logout from page--%>
          <li id="signout">
            <a href="#" id="sign-out-button"><i class="fa fa-sign-out fa-fw"></i> Sign out</a>
          </li>
        </ul>
      </div>
      <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
  </nav>
