<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="prf" uri="/WEB-INF/taglib/MyTagProfile" %>
<html>
<head>
    <title>Game X_O </title>
</head>

<body>
<span style="float: right">
    <a href="?lang=en">en</a>
    |
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

<center>
    <h1>Добро пожаловать в игру КРЕСТИКИ НОЛИКИ</h1>
</center>

</body>
</html>
