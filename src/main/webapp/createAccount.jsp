<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anders Egberts
  Date: 20/10/2016
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registreren</title>
    <!-- <link rel="stylesheet" href="style/css/bootstrap.css"> -->
    <!--<link rel="stylesheet" href="style/css/bootstrap-theme.min.css"> -->
    <link rel="stylesheet" href="style/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Nieuwe abonnee</h1>
    <c:if test="${not empty requestScope.errorMsg}">
        <p class="alert alert-danger">${requestScope.errorMsg}</p>
    </c:if>
    <form action="register" method="post" >
        <table class="table" >
            <tr><td>Email adress:</td></tr>
            <tr><td><input class="form-control" type="text" name="email" /></td></tr>
            <tr><td>Voornaam:</td></tr>
            <tr><td><input class="form-control" type="text" name="firstName" /></td></tr>
            <tr><td>Achternaam:</td></tr>
            <tr><td><input class="form-control" type="text" name="lastName" /></td></tr>
            <tr><td><input class="btn btn-success btn-block" type="submit" value="Registreren" /></td></tr>
        </table>
    </form>
</div>
</body>
</html>