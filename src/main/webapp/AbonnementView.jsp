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
        <th>abonneeId</th>
        <th>bedrijf</th>
        <th>naam</th>
        <th>status</th>
        <th>soort</th>
        <th>start datum</th>
        <th>verdubbeld</th>
    </tr>
    <c:forEach items="${requestScope.abonnementen}" var="abonnement">
        <tr>
            <td>${abonnement.abonneeId}</td>
            <td>${abonnement.dienst.bedrijf}</td>
            <td>${abonnement.dienst.naam}</td>
            <td>${abonnement.status}</td>
            <td>${abonnement.soort}</td>
            <td>${abonnement.startDatum}</td>
            <td>${abonnement.verdubbeld}</td>
            <td><a href="/shareSubscription" >Share</a> -
                <a href="/upgradeSubscription?verdubbeld=${abonnement.verdubbeld}&abonneeId=${abonnement.abonneeId}&bedrijf=${abonnement.dienst.bedrijf}&naam=${abonnement.dienst.naam}">Upgrade</a> -
                <a href="/cancelSubscription" >Cancel</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>