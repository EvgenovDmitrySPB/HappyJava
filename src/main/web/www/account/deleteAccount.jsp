<%--
  Created by IntelliJ IDEA.
  User: d.evgenov
  Date: 04.10.2018
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Do you want to delete account?
<br><br>
<form action="/account" method="post">
    <input type="text" name="id" readonly="readonly" value="${param.id}">
    <input type="text" name="accountData" readonly="readonly"  value="${param.accountData}">
    <input type="hidden" name="method" value="DELETE">
    <br><br>
        <td><input type="submit" value="Yes"></td>
</form>
<form action="/account" method="get">
    <td><input type="submit" value="Cancel"></td>
</form>
</body>
</html>
