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
Do you want to delete developer?
<br><br>
<div style="width: 100px; margin-left: 50px; margin-right: auto">
    <form action="/developer" method="post">
        <span>Id:</span> <input type="text" name="id" readonly="readonly" value="${param.id}">
        <span>FirstName:</span> <input type="text" name="firstName" readonly="readonly"  value="${param.firstName}">
        <span>LastName:</span> <input type="text" name="lastName" readonly="readonly" value="${param.lastName}">
        <span>Specialty:</span> <input type="text" name="specialty" readonly="readonly"  value="${param.specialty}">
        <span>Account Id:</span> <input type="text" name="accountId" readonly="readonly"  value="${param.accountId}">
        <span>Skills:</span><textarea rows="10" cols="30" name="skillSet" >${param.skillSet}</textarea>
        <%--<select>--%>
        <%--<c:forEach items="${param.developerList.getSkills()}" var="s">--%>
        <%--&lt;%&ndash;<c:if test="s.  ${param.id}">&ndash;%&gt;--%>
        <%--<option value = "${s.id}">[${s.id}] ${s.name}</option>--%>
        <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
        <%--</c:forEach>--%>
        <%--</select>--%>
        <input type="hidden" name="method" readonly="readonly"  value="DELETE">
        <br><br>
        <td><input type="submit" value="Yes"></td>
    </form>
    <form action="/developer" method="get">
        <td><input type="submit" value="Cancel"></td>
    </form>
</div>
</body>
</html>
