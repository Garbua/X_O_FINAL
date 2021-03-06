
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

        <form:hidden path="idGame" value = "${idGame}"></form:hidden>

    <table align="center", border="2", cellspacing="2", cellpadding="10", width="50">
        <tr>
            <td><form:input type = "text" path="g0" value = "${p0}" ></form:input></td>
            <td><form:input type = "text" path="g1" value = "${p1}" ></form:input></td>
            <td><form:input type = "text" path="g2" value = "${p2}" ></form:input></td>
        </tr>
        <tr>
            <td><form:input type = "text" path="g3" value = "${p3}" ></form:input></td>
            <td><form:input type = "text" path="g4" value = "${p4}" ></form:input></td>
            <td><form:input type = "text" path="g5" value = "${p5}" ></form:input></td>
        </tr>
        <tr>
            <td><form:input type = "text" path="g6" value = "${p6}" ></form:input></td>
            <td><form:input type = "text" path="g7" value = "${p7}" ></form:input></td>
            <td><form:input type = "text" path="g8" value = "${p8}" ></form:input></td>
        </tr>
    </table>
    <br>

        <c:if test="${!''.equalsIgnoreCase(win) and win ne null}">
            <b> <spring:message code="${win}"></spring:message></b>
        </c:if>

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