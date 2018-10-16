<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="label.authorization"></spring:message> </title>
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
    <spring:message code="label.authorization" var="authoriz"></spring:message>
<h2>${authoriz}</h2>
<hr>
<br>
    <spring:message code="label.error0" var="errror0"></spring:message>
    <spring:message code="label.error1" var="errror1"></spring:message>
    <spring:message code="label.error2" var="errror2"></spring:message>
    <h3 align="center">
       <span class="error">
               <c:choose>
                   <c:when test="${'0'.equals(form_error)}">
                       <b>${errror0}</b>
                   </c:when>
                   <c:when test="${'1'.equals(form_error)}">
                       <b>${errror1}</b>
                   </c:when>
                   <c:when test="${'2'.equals(form_error)}">
                       <b>${errror2}</b>
                   </c:when>
                   <c:otherwise></c:otherwise>
               </c:choose>
       </span>
    </h3>
    <br>
    <h3 align="center">
        <spring:message code="label.addnewuser" var="succes"></spring:message>
       <span class="error"> <c:if test="${'success'.equals(reg_very)}">
           <b>${succes} </b>
       </c:if>
       </span>
    </h3>

        <form:form method="POST" action="/login" modelAttribute="userDTO">
    <table align="center">

        <tr>
            <spring:message code="label.login" var="login"></spring:message>
            <th><label for = "login_name">${login}</label></th>
            <td><form:input id = "login_name" type="text" path="login"/></td>
            <td><span class="error"> <form:errors path="login"/></span></td>
        </tr>

        <tr>
            <spring:message code="label.password" var="pass"></spring:message>
            <th><label for = "pass_login">${pass}</label></th>
            <td><form:input id="pass_login" type="password" path="password"/></td>
            <td><span class="error"> <form:errors path="password"/></span></td>
        </tr>

    </table>

    <p></p>

    <div align="center" style="margin-top: 30px">
        <spring:message code="label.submit.in" var="in"></spring:message>
        <input type="submit" value= "${in}"/>
    </div>


</form:form>

    <p></p>

    <div align="center" style="margin-top: 30px">
        <form action="/registration" method="get">
            <spring:message code="label.sub.registr" var="reg"></spring:message>
            <input type="submit" value= "${reg}"/>
        </form>
    </div>

</center>

</body>
</html>