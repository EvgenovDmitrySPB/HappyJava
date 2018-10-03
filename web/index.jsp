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
    <title>$Title$</title>
  </head>
  <body>
  <jsp:include page="/www/header.jsp" />
  <%--<div style="width: 1200px; margin-left: auto; margin-right: auto;">--%>
      <%--<table cellpadding="10">--%>
          <%--<tr>--%>
              <%--<th>Id</th>--%>
              <%--<th>Title</th>--%>
              <%--<th>Description</th>--%>
              <%--<th>Detail</th>--%>
              <%--<th>Category</th>--%>
              <%--<th>Date</th>--%>
              <%--<th>Image</th>--%>
              <%--<th></th>--%>
          <%--</tr>--%>
          <%--<c:forEach items="accountList" var="p">--%>
              <%--<tr>--%>
                  <%--<td>${p.id}</td>--%>
                  <%--<td>${p.accountData}</td>--%>
                  <%--<td>--%>
                      <%--<a href="edit?id=${p.id}">Edit</a>--%>
                      <%--<a href="delete?id=${p.id}">Delete</a>--%>
                  <%--</td>--%>
              <%--</tr>--%>
          <%--</c:forEach>--%>
      <%--</table>--%>
  <%--</div>--%>
  <jsp:include page="/www/footer.jsp" />
  </body>
</html>
