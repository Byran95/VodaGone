<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anders Egberts
  Date: 20/10/2016
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dienst uitproberen</title>
    <link rel="stylesheet" href="style/css/bootstrap.min.css">
    <link rel="stylesheet" href="style/css/dimensionsHelper.css">
</head>
<body>
<c:import url="navbar.jsp"></c:import>
<div class="container">
    <h1>Diensten:</h1>
    <div class="col-lg-12" >
        <div class="col-md-8">
            <p>Probeer gratis een van de onderstaande diensten:</p>
        </div>
        <div class="col-md-4">
            <form align="right" action="dienstUitproberen" method="get" width="128">
                <div class="col-md-8">
                    <input class="form-control" type="text" name="searchTerm" />
                </div>
                <div class="col-md-4">
                    <input class="btn btn-success btn-block" type="submit" value="Zoeken" />
                </div>
            </form>
        </div>
    </div>
    <hr/>
    <table class="table table-striped">
        <tr>
            <th>Naam</th>
            <th>Aanbieder</th>
            <th>Beschrijving</th>
            <th>Maand prijs</th>
            <th>Halfjaar prijs</th>
            <th>Jaar prijs</th>
            <th>Action:</th>
        </tr>
        <c:forEach items="${requestScope.diensten}" var="dienst">
            <tr>
                <td>${dienst.naam}</td>
                <td>${dienst.bedrijf}</td>
                <td>${dienst.beschrijving}</td>
                <td>${dienst.maandPrijs}</td>
                <td>${dienst.halfJaarPrijs}</td>
                <td>${dienst.jaarPrijs}</td>
                <td><a href="/tryService?cn=${dienst.bedrijf}&sn=${dienst.naam}">Uitproberen</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>