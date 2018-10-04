<%--
  Created by IntelliJ IDEA.
  User: d.evgenov
  Date: 04.10.2018
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Add new account</h1>
<div style="width: 100px; margin-left: 50px; margin-right: auto">
    <form action="/account" method="POST">
        Name:<br>
        <input type="text" name="accountData" style="width: 200px">
        <input type="hidden" name="method" value="POST">
        <br><br>
        <input type="submit" value="Submit">
    </form>
    <form action="/account" method="get">
        <td><input type="submit" value="Cancel"></td>
    </form>
</div>
</body>
</html>
