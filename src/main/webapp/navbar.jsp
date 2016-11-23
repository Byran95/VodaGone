<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anders Egberts
  Date: 20/10/2016
  Time: 13:10
  To change this template use File | Settings | File Templates.

  Deze pagina bevat geen Java-code (Eis: M1)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Vodagone</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/abonnementen">Mijn Abonnementen</a></li>
                <li><a href="/dienstUitproberen">Diensten</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${not empty sessionScope.loggedInUser}">
                    <li>
                        <img src="img/userImage.png" class="navbar-image" />
                    </li>
                </c:if>
                <li>
                    <c:if test="${not empty sessionScope.loggedInUser}">
                        <a href="/logout" >Log out</a>
                    </c:if>
                    <c:if test="${empty sessionScope.loggedInUser}">
                        <a href="/login" >Log in</a>
                    </c:if>
                </li>
            </ul>
        </div>
    </div>
</nav>