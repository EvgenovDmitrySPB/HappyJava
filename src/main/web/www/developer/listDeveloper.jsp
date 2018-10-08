<%@ page import="java.util.List" %>
<%@ page import="net.proselyte.crud.model.Developer" %>
<%@ page import="net.proselyte.crud.model.Skill" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: d.evgenov
  Date: 03.10.2018
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=Cp1251" pageEncoding="Cp1251"%>
<html>
<head>
    <META http-equiv="Content-Type" content="text/html; charset=Cp1251"/>
    <title>Skills</title>
</head>
<body>
<div style="width: 1200px; margin-left: 10px; margin-right: auto;">
    <jsp:include page="../header.jsp" />
    <br>
    <button onclick="location.href='www/developer/addNewDeveloper.jsp'">Add new developer</button>
    <br><br>
    <table cellpadding="10" border="1">
        <tr>
            <th width="50" align="center">Id</th>
            <th width="200" align="center">FirstName</th>
            <th width="200" align="center">LastName</th>
            <th width="200" align="center">Specialty</th>
            <th width="200" align="center">Account</th>
            <th width="350" align="center">Skills</th>
            <th width="50">Edit</th>
            <th width="50">Delete</th>
        </tr>
        <c:forEach items="${developerList}" var="p">
            <tr>
                <td width="50" align="center">${p.getId()}</td>
                <td width="300" align="left">${p.getFirstName()}</td>
                <td width="300" align="left">${p.getLastName()}</td>
                <td width="300" align="left">${p.getSpecialty()}</td>
                <td width="300" align="left">[${p.getAccount().getId()}] ${p.getAccount().getAccountData()}</td>
                <td>
                    <select>
                        <c:forEach items="${p.getSkills()}" var="s">
                                 <option value="${s.id}">[${s.id}] ${s.name} (${p.skills.size()})</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <form action="www/developer/editDeveloper.jsp" method="post">
                        <input type="hidden" name="id" value="${p.getId()}">
                        <input type="hidden" name="firstName" value=${p.getFirstName()}>
                        <input type="hidden" name="lastName" value=${p.getLastName()}>
                        <input type="hidden" name="specialty" value=${p.getSpecialty()}>
                        <input type="hidden" name="accountId" value=${p.getAccount().getId()}>
                        <input type="hidden" name="accountName" value=${p.getAccount().getAccountData()}>
                        <input type="hidden" name="skillSet" value="${p.getSkills()}">
                        <input type="submit" value="Edit">
                    </form>
                </td>
                <td>
                    <form action="www/developer/deleteDeveloper.jsp" method="post">
                        <input type="hidden" name="id" value="${p.getId()}">
                        <input type="hidden" name="firstName" value=${p.getFirstName()}>
                        <input type="hidden" name="lastName" value=${p.getLastName()}>
                        <input type="hidden" name="specialty" value=${p.getSpecialty()}>
                        <input type="hidden" name="accountId" value=${p.getAccount().getId()}>
                        <input type="hidden" name="accountName" value=${p.getAccount().getAccountData()}>
                        <input type="hidden" name="skillSet" value="${p.getSkills()}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<br>
<br>
<jsp:include page="../footer.jsp" />
</body>
</html>
