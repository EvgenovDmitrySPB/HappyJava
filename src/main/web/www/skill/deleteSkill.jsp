<%--
  Created by IntelliJ IDEA.
  User: d.evgenov
  Date: 04.10.2018
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=Cp1251" pageEncoding="Cp1251"%>
<html>
<head>
    <META http-equiv="Content-Type" content="text/html; charset=Cp1251"/>
    <title>Title</title>
</head>
<body>
Do you want to delete skill?
<br><br>
<form action="/skill" method="post">
    <input type="text" name="id" readonly="readonly" value="${param.id}">
    <input type="text" name="name" readonly="readonly"  value="${param.name}">
    <input type="hidden" name="method" value="DELETE">
    <br><br>
        <td><input type="submit" value="Yes"></td>
</form>
<form action="/skill" method="get">
    <td><input type="submit" value="Cancel"></td>
</form>
</body>
</html>
