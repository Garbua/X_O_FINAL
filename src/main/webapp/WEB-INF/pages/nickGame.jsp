
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="prf" uri="/WEB-INF/taglib/MyTagProfile" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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

<spring:message code="label.login" var="login"></spring:message>
<spring:message code="label.profile" var="prof"></spring:message>
<spring:message code="label.exit" var="exit"></spring:message>

<div align="right">
    <prf:ProfileTag userDTO="${sessionScope.userDTO.login}" login="${login}">
        <a href="/profile"> ${prof} <br> </a>
        <div align="right" style="margin-top: 30px">
            <form action="/login/exit" method="get">
                <input type="submit" value= "${exit}"/>
            </form>
        </div>
    </prf:ProfileTag>
</div>
<br>

<center>

<h1 align="center"><strong><spring:message code="label.gameAititle"/></strong> </h1>

<form action="/displaygame" method="post">
    <table align="center", border="2", cellspacing="2", cellpadding="10", width="50">
        <tr>
            <td><input type="text" name="0" value="${p0}"></td>
            <td><input type="text" name="1" value="${p1}"></td>
            <td><input type="text" name="2" value="${p2}"></td>
        </tr>
        <tr>
            <td><input type="text" name="3" value="${p3}"></td>
            <td><input type="text" name="4" value="${p4}"></td>
            <td><input type="text" name="5" value="${p5}"></td>
        </tr>
        <tr>
            <td><input type="text" name="6" value="${p6}"></td>
            <td><input type="text" name="7" value="${p7}"></td>
            <td><input type="text" name="8" value="${p8}"></td>
        </tr>

    </table>
    <br>
    <c:choose>
        <c:when test="${win.equals('x')}">
            <spring:message code="label.winPlayer"/>
        </c:when>
        <c:when test="${win.equals('o')}">
            <spring:message code="label.winAI"/>
        </c:when>
        <c:when test="${win.equals('n')}">
            <spring:message code="label.winNO"/>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>

    <br>
    <input type="submit" align="center" value="<spring:message code="label.submit"/>" >
</form>
    <a href="/displaygame"><spring:message code="label.restart"/></a>
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