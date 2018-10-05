<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: d.evgenov
  Date: 03.10.2018
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Happy java application</title>
  </head>
  <body>
  <jsp:include page="www/header.jsp" />
  <p>Welcome to my first CRUDE application.</p>
  <p>Today is </p>
  <%
    java.util.Date date = new java.util.Date();
  %>

  <jsp:include page="www/footer.jsp" />
  </body>
</html>
