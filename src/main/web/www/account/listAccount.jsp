<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: d.evgenov
  Date: 03.10.2018
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
</head>
<body>
<div style="width: 1200px; margin-left: 10px; margin-right: auto;">
    <jsp:include page="../header.jsp" />
    <table cellpadding="10" border="1">
        <tr>
            <th width="50" align="center">Id</th>
            <th width="300" align="left">accountData</th>
            <th width="180">Menu</th>
        </tr>
        <c:forEach items="${accountList}" var="p">
            <tr>
                <td width="50" align="center">${p.getId()}</td>
                <td width="300" align="left">${p.accountData}...</td>
                <td>
                    <%--<a href="edit?id=${p.id}">Edit</a>--%>
                    <%--<a href="delete?id=${p.id}">Delete</a>--%>
                    <button style="width: 50px" onclick="location.href='edit?id=${p.id}'">Edit</button>
                    <button style="width: 60px" onclick="location.href='delete?id=${p.id}'">Delete</button>
                    <button style="width: 70px" onclick="location.href='update?id=${p.id}'">Update</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<br>
<button onclick="location.href='save?id=${p.id}'">Add new</button>
<br>
<jsp:include page="../footer.jsp" />
</body>
</html>
