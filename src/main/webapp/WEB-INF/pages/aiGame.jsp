
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

    <form:form method="post" action="/aigame" modelAttribute="gKey">
    <table align="center", border="2", cellspacing="2", cellpadding="10", width="50">
        <tr>
            <td><form:input type = "text" path="g0" ></form:input></td>
            <td><form:input type = "text" path="g1" ></form:input></td>
            <td><form:input type = "text" path="g2" ></form:input></td>
        </tr>
        <tr>
            <td><form:input type = "text" path="g3" ></form:input></td>
            <td><form:input type = "text" path="g4" ></form:input></td>
            <td><form:input type = "text" path="g5" ></form:input></td>
        </tr>
        <tr>
            <td><form:input type = "text" path="g6" ></form:input></td>
            <td><form:input type = "text" path="g7" ></form:input></td>
            <td><form:input type = "text" path="g8" ></form:input></td>
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
    </form:form>

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