
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="prf" uri="/WEB-INF/taglib/MyTagProfile" %>
<html>
<head>
    <spring:message code="label.proflogin" var="profil" arguments="${sessionScope.userDTO.login}"></spring:message>
    <title>${profil} </title>
</head>
<body>
<span style="float: right">
    <a href="?lang=en">en</a>
    |
    <a href="?lang=ru">ru</a>
</span>
<br>
<spring:message code="label.login" var="login"></spring:message>
<spring:message code="label.exit" var="exit"></spring:message>
<div align="right">
    <prf:ProfileTag userDTO="${sessionScope.userDTO.login}" login="${login}">
        <div align="right" style="margin-top: 30px">
            <form action="/login/exit" method="get">
                <input type="submit" value= "${exit}"/>
            </form>
        </div>
    </prf:ProfileTag>
</div>
<center>
    <div>
        <spring:message code="label.profile" var="prof"></spring:message>
        <h2>${prof}</h2>

        <spring:message code="label.email" var="emailLabel"></spring:message>
        <spring:message code="label.password" var="passLabel"></spring:message>
        <spring:message code="label.firstName" var="firstLabel"></spring:message>
        <spring:message code="label.lastName" var="lastLabel"></spring:message>


        <table align="center">

            <tr>
                <th><label> ${emailLabel} </label></th>
                <td> ${profile.email} </td>
            </tr>
            <tr>
                <th><label> ${passLabel} </label></th>
                <td> ${profile.password} </td>
            </tr>

            <tr>
                <th><label> ${firstLabel} </label></th>
                <td>${profile.firstName}</td>
            </tr>

            <tr>
                <th><label> ${lastLabel} </label></th>
                <td> ${profile.lastName}</td>
            </tr>

        </table>
    </div>
    <br>

    <form action="/profileedit" method="get">
        <spring:message code="label.profile.edit" var="profileeditLabel"/>
        <input type="submit" value="${profileeditLabel}" />
    </form>
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
