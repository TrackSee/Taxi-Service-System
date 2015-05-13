<%--
  Created by IntelliJ IDEA.
  User: exarus
  Date: 4/24/15
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:forEach var="i" items="">
    <c:out value="${i}"/>
</c:forEach>
<label for="arriveDate" class="sr-only">Date</label>

<div class="controls input-append date form_datetime"
     data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
    <span class="add-on"><i class="icon-th"></i></span>
    <span class="add-on"><i class="icon-remove"></i></span>
    <input size="16" type="text" value="" id="arriveDate" name="arriveDate" readonly>
    <input type="hidden" id="dtp_input1" value=""/><br/>

</div>
<!-- Load jQuery and bootstrap datepicker scripts -->
<script type="text/javascript" src="<%=application.getContextPath()%>/resources/js/jquery-1.8.3.min.js"
        charset="UTF-8"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/resources/js/bootstrap-datetimepicker.js"
        charset="UTF-8"></script>
<script type="text/javascript"
        src="<%=application.getContextPath()%>/resources/js/locales/bootstrap-datetimepicker.fr.js"
        charset="UTF-8"></script>
<script type="text/javascript"
        src="<%=application.getContextPath()%>/resources/js/locales/bootstrap-datetimepicker.fr.js"
        charset="UTF-8"></script>
<script type="text/javascript"
        src="<%=application.getContextPath()%>/resources/js/date-picker-order-complete.js"
        charset="UTF-8"></script>
<script src="<%=application.getContextPath()%>http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<%--end jQuery and bootstrap datepicker scripts--%>
</body>
</html>
