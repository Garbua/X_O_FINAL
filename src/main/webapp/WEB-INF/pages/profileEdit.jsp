
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="prf" uri="/WEB-INF/taglib/MyTagProfile" %>
<html>
<head>
    <spring:message code="label.proflogin" var="profil" arguments="${sessionScope.userDTO.login}"></spring:message>
    <title>${profil} </title>

    <style type="text/css">
        span.error {
            color: red;
        }
    </style>

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
        <spring:message code="label.reg.info" var="redactinfo"></spring:message>
        <h2>${redactinfo}</h2>

        <spring:message code="label.email" var="emailLabel"></spring:message>
        <spring:message code="label.password" var="passLabel"></spring:message>
        <spring:message code="label.firstName" var="firstLabel"></spring:message>
        <spring:message code="label.lastName" var="lastLabel"></spring:message>

        <hr>
        <br>
        <br>
            <form:form method="POST" action="/profileedit" modelAttribute="profileedit">
            <table align="center">

                <form:hidden path="id"/>
                <form:hidden path="login" value = "${sessionScope.userDTO.login}"/>

                <tr>
                    <spring:message code="label.password" var="pass"></spring:message>
                    <th><label for = "pass_login">${pass}</label></th>
                    <td><form:input id="pass_login" size="30" type="password" path="password"/></td>
                    <td><span class="error"> <form:errors path="password"/></span></td>
                </tr>

                <tr>
                    <spring:message code="label.email" var="email"></spring:message>
                    <th><label for = "email_login">${email}</label></th>
                    <td><form:input id="email_login" size="30" type="text" path="email"/></td>
                    <td><span class="error"> <form:errors path="email"/></span></td>
                </tr>

                <tr>
                    <spring:message code="label.firstName" var="first"></spring:message>
                    <th><label for = "first_login">${first}</label></th>
                    <td><form:input id="first_login" size="30" type="text" path="firstName"/></td>
                    <td><span class="error"> <form:errors path="firstName"/></span></td>
                </tr>

                <tr>
                    <spring:message code="label.lastName" var="last"></spring:message>
                    <th><label for = "last_login">${last}</label></th>
                    <td><form:input id="last_login" size="30" type="text" path="lastName"/></td>
                    <td><span class="error"><form:errors path="lastName"/></span> </td>
                </tr>

            </table>

            <p></p>

            <div align="center" style="margin-top: 30px">
                <spring:message code="label.save" var="saveLabel"></spring:message>
                <input type="submit" value="${saveLabel}"/>
            </div>

            </form:form>

    <br>
            <div align="center" style="margin-top: 30px">
                <form action="/deleteuser" method="get">
                    <spring:message code="label.delete" var="deleteLabel"></spring:message>
                    <input type="submit" value="${deleteLabel}"/>
                </form>
            </div>
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
