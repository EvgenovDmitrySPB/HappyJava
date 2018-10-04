<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: d.evgenov
  Date: 03.10.2018
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Skills</title>
</head>
<body>
<div style="width: 1200px; margin-left: 10px; margin-right: auto;">
    <jsp:include page="../header.jsp" />
    <br>
    <button onclick="location.href='www/skill/addNewSkill.jsp'">Add new skill</button>
    <br><br>
    <table cellpadding="10" border="1">
        <tr>
            <th width="50" align="center">Id</th>
            <th width="300" align="left">accountData</th>
            <th width="50">Edit</th>
            <th width="50">Delete</th>
        </tr>
        <c:forEach items="${skillList}" var="p">
            <tr>
                <td width="50" align="center">${p.getId()}</td>
                <td width="300" align="left">${p.getName()}</td>
                    <td>
                        <form action="www/skill/editSkill.jsp" method="post">
                            <input type="hidden" name="id" value="${p.getId()}">
                            <input type="hidden" name="name" value=${p.getName()}>
                            <input type="submit" value="Edit">
                        </form>
                    </td>
                    <td>
                        <form action="www/skill/deleteSkill.jsp" method="post">
                            <input type="hidden" name="id" value="${p.getId()}">
                            <input type="hidden" name="name" value=${p.getName()}>
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
