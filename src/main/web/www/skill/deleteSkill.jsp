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
<div style="width: 100px; margin-left: 50px; margin-right: auto">
<form action="/skill" method="post">
    <span>Id:</span> <input type="text" name="id" readonly="readonly" value="${param.id}">
    <span>Name:</span> <input type="text" name="name" readonly="readonly"  value="${param.name}">
    <input type="hidden" name="method" value="DELETE">
    <br><br>
        <td><input type="submit" value="Yes"></td>
</form>
<form action="/skill" method="get">
    <td><input type="submit" value="Cancel"></td>
</form>
</div>
</body>
</html>
