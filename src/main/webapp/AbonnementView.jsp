<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Anders Egberts
  Date: 18/10/2016
  Time: 14:30
  To change this template use File | Settings | File Templates.

  Deze pagina bevat geen Java-code (Eis: M1)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="style/css/bootstrap.min.css">
    <link rel="stylesheet" href="style/css/dimensionsHelper.css">
</head>
<body>
    <c:import url="navbar.jsp"></c:import>
    <div class="container">
        <h1>Abonnementen:</h1>
        <c:if test="${not empty requestScope.errorMsg}">
            <p class="alert alert-danger">${requestScope.errorMsg}</p>
        </c:if>
        <c:if test="${not empty requestScope.successMsg}">
            <p class="alert alert-success">${requestScope.successMsg}</p>
        </c:if>
        <c:if test="${fn:length(requestScope.abonnementen) gt 0}">
            <table class="table table-striped">
                <tr>
                    <th>Naam</th>
                    <th>Aanbieder</th>
                    <th>Beschrijving</th>
                    <th>Status</th>
                    <th>Verdubbeld</th>
                    <th>Start datum</th>
                    <th>Actions</th>
                </tr>
                <c:forEach items="${requestScope.abonnementen}" var="abonnement">
                    <tr>
                        <td>${abonnement.dienst.naam}</td>
                        <td>${abonnement.dienst.bedrijf}</td>
                        <td>${abonnement.dienst.beschrijving}</td>
                        <td>${abonnement.status}</td>
                        <td>${abonnement.verdubbeld}</td>
                        <td>${abonnement.startDatum}</td>
                        <td>
                            <c:if test="${abonnement.status != 'OPGEZEGD'}">
                                <c:if test="${abonnement.deelbaar}">
                                    <a href="/startSharingService?naam=${abonnement.dienst.naam}&bedrijf=${abonnement.dienst.bedrijf}">Share</a> -
                                </c:if>
                                <a href="/upgradeSubscription?naam=${abonnement.dienst.naam}&bedrijf=${abonnement.dienst.bedrijf}&verdubbeld=${abonnement.verdubbeld}">Upgrade</a> - <a href="/cancelSubscription?naam=${abonnement.dienst.naam}&bedrijf=${abonnement.dienst.bedrijf}">Cancel</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="7"><a href="/dienstUitproberen" ><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Nieuw abonnement toevoegen.</a></td>
                </tr>
            </table>
        </c:if>
        <c:if test="${fn:length(requestScope.abonnementen) lt 1}">
            <div class="jumbotron">
                <h1>Je hebt nog geen abonnementen</h1>
                <p>Je kan gratis een dienst uitproberen</p>
                <p><a href="/dienstUitproberen" class="btn btn-primary btn-lg" role="button">Get started!</a></p></div>
        </c:if>
    </div>
</body>
</html>
