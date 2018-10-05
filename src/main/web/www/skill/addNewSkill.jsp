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
<h1>Add new skill</h1>
<div style="width: 100px; margin-left: 50px; margin-right: auto">
    <form action="/skill" method="POST">
        Name:<br>
        <input type="text" name="name" style="width: 200px">
        <input type="hidden" name="method" value="POST">
        <br><br>
        <input type="submit" value="Submit">
    </form>
    <form action="/skill" method="get">
        <td><input type="submit" value="Cancel"></td>
    </form>
</div>
</body>
</html>
