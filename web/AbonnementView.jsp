<%--
  Created by IntelliJ IDEA.
  User: Anders Egberts
  Date: 18/10/2016
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Abonnementen:</h1>
    <table>
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${requestScope.abonnementen}" var="abonnement">
        <tr>
            <td>Abonnement</td>
            <td>Placeholder</td>
            <td>Share - Upgrade - Cancel</td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>
