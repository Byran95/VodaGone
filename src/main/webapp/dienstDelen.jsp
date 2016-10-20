<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anders Egberts
  Date: 18/10/2016
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Oh Hai</title>
    <link rel="stylesheet" href="style/css/bootstrap.min.css">
    <link rel="stylesheet" href="style/css/dimensionsHelper.css">
</head>
<body>
<c:import url="navbar.jsp"></c:import>
<div class="container">
    <h1>Dienst delen</h1>
    <p>U wilt een dienst delen, kies hieronder met wie u het wil delen:</p>
    <table class="table" >
        <tr>
            <th>Naam</th>
            <th>Aanbieder</th>
            <th>Beschrijving</th>
            <th>Status</th>
            <th>Start datum</th>
        </tr>
        <tr>
            <td>${requestScope.abonnement.dienst.naam}</td>
            <td>${requestScope.abonnement.dienst.bedrijf}</td>
            <td>${requestScope.abonnement.dienst.beschrijving}</td>
            <td>${requestScope.abonnement.status}</td>
            <td>${requestScope.abonnement.startDatum}</td>
        </tr>
    </table>
    <form action="shareService" method="post" >
        <table class="table" >
            <tr>
                <td>
                    <select class="form-control" name="targetUserId">
                        <c:forEach items="${requestScope.users}" var="user">
                            <option value="${user.abonneeId}">${user.naam} ${user.achternaam}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="naam" value="${requestScope.abonnement.dienst.naam}" />
                    <input type="hidden" name="bedrijf" value="${requestScope.abonnement.dienst.bedrijf}" />
                </td>
            </tr>
            <tr><td><input class="btn btn-success btn-block" type="submit" value="Delen" /></td></tr>
        </table>
    </form>
</div>
</body>
</html>