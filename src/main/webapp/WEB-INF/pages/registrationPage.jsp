
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="label.sub.registr"></spring:message></title>
    <style type="text/css">
        span.error {
            color: red;
        }
    </style>
</head>
<body>

<c:if test="${sessionScope.get('userDTO') ne null}">
    ${pageContext.forward("/gamelogin")}
</c:if>

<span style="float: right">
    <a href="?lang=en">en</a>
    |
    <a href="?lang=ru">ru</a>
</span>
<center>
    <h2><spring:message code="label.reg.new"></spring:message></h2>
    <hr>
    <br>
    <h3><spring:message code="label.reg.info"></spring:message></h3>
    <hr>
    <br>
    <h3 align="center">
        <spring:message code="label.reg.eslogin" var="eslogin"></spring:message>
        <spring:message code="label.reg.esemail" var="esemail"></spring:message>
       <span class="error">
           <c:choose>
           <c:when test="${'0'.equals(reg_very)}">
               <b>${eslogin}</b>
           </c:when>
           <c:when test="${'1'.equals(reg_very)}">
               <b>${esemail}</b>
           </c:when>
           <c:otherwise></c:otherwise>
       </c:choose>
       </span>
    </h3>
    <br>

    <form:form method="POST" action="/registration" modelAttribute="user_info">
        <table align="center">

            <tr>
                <spring:message code="label.login" var="login"></spring:message>
                <th><label for = "login_name">${login}</label></th>
                <td><form:input id = "login_name" size="30" type="text" path="login"/></td>
                <td><span class="error"> <form:errors path="login"/></span></td>
            </tr>

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
            <spring:message code="label.sub.registr2" var="reg2"></spring:message>
            <input type="submit" value="${reg2}"/>
        </div>

    </form:form>

    <p></p>

    <div align="center" style="margin-top: 30px">
        <form action="/login" method="get">
            <spring:message code="label.authorization" var="authorization"></spring:message>
            <input type="submit" value="${authorization}"/>
        </form>
    </div>

</center>

</body>
</html>
