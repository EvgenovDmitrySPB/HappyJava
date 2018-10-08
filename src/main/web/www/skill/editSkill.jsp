<%--
  Created by IntelliJ IDEA.
  User: d.evgenov
  Date: 04.10.2018
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=Cp1251" pageEncoding="Cp1251"%>
<html>
<head>
    <META http-equiv="Content-Type" content="text/html; charset=Cp1251"/>
    <title>Edit account</title>
</head>
<body>
<p>Edit skill</p>
<div style="width: 100px; margin-left: 50px; margin-right: auto">
    <form action="/skill" method="post">
        <span>Id:</span> <input type="hidden" readonly="readonly" name="id" value="${param.id}">
        <span>Name:</span><input type="text" name="name" value="${param.name}">
        <input type="hidden" name="method" value="PUT">
        <br><br>
        <td><input type="submit" value="Update"></td>
    </form>
    <form action="/skill" method="get">
        <td><input type="submit" value="Cancel"></td>
    </form>
</div>
</body>
</html>
