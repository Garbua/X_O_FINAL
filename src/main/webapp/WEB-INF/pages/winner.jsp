
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="prf" uri="/WEB-INF/taglib/MyTagProfile" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Game X_O </title>
</head>

<body>
<span style="float: right">
    <a href="?lang=en">en</a>
    <a href="?lang=ru">ru</a>
    </span>
<br>

<center>
        <c:if test="${!''.equalsIgnoreCase(win) and win ne null}">
            <h1 align="center"><strong><spring:message code="${win}"></spring:message></strong> </h1>
        </c:if>

    <a href="/aigame"><spring:message code="label.restart"/></a>
    <br>
    <div align="center" style="margin-top: 30px">
        <form action="/gamelogin" method="get">
            <spring:message code="label.home" var="homeLabel"></spring:message>
            <input type="submit" value="${homeLabel}"/>
        </form>
    </div>
</center>
</body>
</html>