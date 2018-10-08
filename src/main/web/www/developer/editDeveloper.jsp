<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<p>Edit developer</p>
<div style="width: 100px; margin-left: 50px; margin-right: auto">
    <form action="/developer" method="post">
        <span>Id:</span> <input type="text" readonly="readonly"  name="id" value="${param.id}">
        <span>firstName:</span> <input type="text" name="firstName" value="${param.firstName}">
        <span>lastName:</span> <input type="text" name="lastName" value="${param.lastName}">
        <span>specialty:</span> <input type="text" name="specialty" value="${param.specialty}">
        <span>accountId:</span> <input type="text" name="accountId" value="${param.accountId}">
        <span>accountData:</span> <input type="text" name="accountData" value="${param.accountName}">
        <span>Skills:</span><textarea readonly="readonly" rows="10" cols="30" name="skillSet" >${param.skillSet}</textarea>
        <%--<select>--%>
            <%--<c:forEach items="${param.developerList.getSkills()}" var="s">--%>
                <%--&lt;%&ndash;<c:if test="s.  ${param.id}">&ndash;%&gt;--%>
                    <%--<option value = "${s.id}">[${s.id}] ${s.name}</option>--%>
                <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
            <%--</c:forEach>--%>
        <%--</select>--%>
        <input type="hidden" name="method" value="PUT">
        <br><br>
        <td><input type="submit" value="Update"></td>
    </form>
    <form action="/developer" method="get">
        <td><input type="submit" value="Cancel"></td>
    </form>
</div>
</body>
</html>
