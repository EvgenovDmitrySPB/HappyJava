<%--
  Created by IntelliJ IDEA.
  User: d.evgenov
  Date: 04.10.2018
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=Cp1251" pageEncoding="Cp1251"%>
<html>
<head>
    <META http-equiv="Content-Type" content="text/html; charset=Cp1251"/>
    <title>Title</title>
</head>
<body>
<h1>Add new developer</h1>
<div style="width: 100px; margin-left: 50px; margin-right: auto">
    <form action="/developer" method="POST">
        <span>firstName:</span> <input type="text" name="firstName">
        <span>lastName:</span> <input type="text" name="lastName" >
        <span>specialty:</span> <input type="text" name="specialty" >
        <span>accountId:</span> <input type="text" name="accountId" >
        <span>skillId:</span> <input type="text" name="accountData" >
        <input type="hidden" name="method" value="POST">
        <br><br>
        <input type="submit" value="Submit">
    </form>
    <form action="/developer" method="get">
        <td><input type="submit" value="Cancel"></td>
    </form>
</div>
</body>
</html>
