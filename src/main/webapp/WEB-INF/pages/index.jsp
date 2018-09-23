<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
<span style="float: right">
    <a href="?lang=en">en</a>
    |
    <a href="?lang=ru">ru</a>
</span>
<center>
<h2><spring:message code="label.authorization"></spring:message></h2>
<hr>
<br>
    <h3 align="center">
       <span class="error">
           <c:if test="${!''.equals(form_error)}">
               <b>${form_error} </b>
           </c:if>
       </span>
    </h3>
    <br>
    <h3 align="center">
       <span class="error"> <c:if test="${!''.equals(reg_very)}">
           <b>${reg_very} </b>
       </c:if>
       </span>
    </h3>

<sf:form method="POST" action="/login" modelAttribute="userDTO">
    <table align="center">

        <tr>
            <spring:message code="label.login" var="login"></spring:message>
            <th><label for = "login_name">${login}</label></th>
            <td><sf:input id = "login_name" type="text" path="login"/></td>
            <td><span class="error"> <sf:errors path="login"/></span></td>
        </tr>

        <tr>
            <spring:message code="label.password" var="pass"></spring:message>
            <th><label for = "pass_login">${pass}</label></th>
            <td><sf:input id="pass_login" type="password" path="password"/></td>
            <td><span class="error"> <sf:errors path="password"/></span></td>
        </tr>

    </table>

    <p></p>

    <div align="center" style="margin-top: 30px">
        <spring:message code="label.submit.in" var="in"></spring:message>
        <input type="submit" value= "${in}"/>
    </div>


</sf:form>

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