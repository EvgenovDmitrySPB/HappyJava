<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: d.evgenov
  Date: 04.10.2018
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=Cp1251" language="java" %>
<html contentType="text/html;charset=Cp1251">
<head>
    <title>Title</title>
</head>
<body>
Do you want to delete account?
<br><br>
<div style="width: 100px; margin-left: 50px; margin-right: auto">
<form action="/account" method="post">
    <span>Id:</span> <input type="text" name="id" readonly="readonly" value="${param.id}">
    <span>AccountData:</span> <input type="text" name="accountData" readonly="readonly"  value="${param.accountData}">
    <input type="hidden" name="method" value="DELETE">
    <br><br>
        <td><input type="submit" value="Yes"></td>
</form>
<form action="/account" method="get">
    <td><input type="submit" value="Cancel"></td>
</form>
</div>
</body>
</html>
