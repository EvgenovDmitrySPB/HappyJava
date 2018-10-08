<%@ page import="net.proselyte.crud.util.SelectConnection" %><%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
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
  <p>Welcome to the CRUDE application.</p>
  <%
    java.util.Date date = new java.util.Date();
    out.println("Today is :" + date + "\n");
  %>
<% if (SelectConnection.getInstance().getConnectType() == null){
  out.println("  <form action=\"/SelectConnection\" method=\"post\">\n" +
          "    <p><b>Выберите тип соединения с БД?</b></p>\n" +
          "    <p><input name=\"jdbc\" type=\"radio\" value=\"JDBC\">JDBC</p>\n" +
          "    <p><input name=\"hibernate\" type=\"radio\" value=\"Hibernate\">Hibernate</p>\n" +
          "    <p><input type=\"submit\" value=\"Выбрать\"></p>\n" +
          "  </form>");
}else {
  out.println("You choose " + SelectConnection.getInstance().getConnectType().name());
}
%>
  <br>
  <jsp:include page="www/footer.jsp" />
  </body>
</html>
