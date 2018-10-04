<%--
  Created by IntelliJ IDEA.
  User: d.evgenov
  Date: 04.10.2018
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit account</title>
</head>
<body>
<p>Edit skill</p>
<div style="width: 100px; margin-left: 50px; margin-right: auto">
    <form action="/skill" method="post">
        <input type="hidden" name="id" value="${param.id}">
        <input type="text" name="name" value="${param.name}">
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
