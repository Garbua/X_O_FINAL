<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Крестики нолики</title>
</head>
<body>
<center>
<h2>Добро пожаловать в игру: Крестики нолики</h2>
<hr>
<br>
    <h3 align="center">
        <c:if test="${''.equals(form_error)}">
            <b>${form_error} </b>
        </c:if>
    </h3>
    <br>

<sf:form method="POST" action="/check" modelAttribute="user">
    <table align="center">
        <tr>
            <th><label for = "login_name">Пользователь:</label></th>
            <td><sf:input id = "login_name" size="20" maxlength="20" type="text" path="login"/>
                <small>максимум 16 символов</small><br>
                <sf:errors path="login"/>
            </td>
        </tr>
        <br>
        <tr>
            <th><label for = "pass_login">Пароль:</label></th>
            <td><sf:input id="pass_login" size="20" maxlength="20" type="password" path="password"/>
                <small>минимум 6 символов</small><br>
                <sf:errors path="password"/>
            </td>
        </tr>
        <br>
    </table>
    <p></p>
    <div align="center" style="margin-top: 30px">
            <input type="submit" value="Войти"/>
    </div>

</sf:form>

    <p></p>

    <div align="center" style="margin-top: 30px">
        <form action="/registr" method="get">
            <input type="submit" value="Регистрация"/>
        </form>
    </div>

</center>

</body>
</html>