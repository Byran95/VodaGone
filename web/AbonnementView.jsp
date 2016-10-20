<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <p>Alles:</p>
    <p>${requestScope.abonnementen}</p>
    <table>
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Actions</th>
        <th>Fields</th>
    </tr>
    <c:forEach items="${requestScope.abonnementen}" var="abonnement">
        <tr>
            <td>${abonnement}</td>
            <td>Placeholder</td>
            <td><a href="/shareSubscription" >Share</a> - <a href="/upgradeSubscription" >Upgrade</a> - <a href="/cancelSubscription" >Cancel</a></td>
            <td>
                <ul>
                    <c:forEach var="field" items="${abonnement['class'].declaredFields}">
                        <c:catch><li><span>${field.name}: </span>${abonnement[field.name]}</li></c:catch>
                    </c:forEach>
                </ul>
            </td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>
