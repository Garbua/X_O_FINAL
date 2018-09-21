
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<center>
    <h2>Регистрация нового пользователя</h2>
    <hr>
    <br>
    <h3>Введите данные</h3>

    <sf:form method="POST" action="/registr" modelAttribute="user_info">
        <table align="center">
            <tr>
                <th><label for = "login_name">Пользователь:</label></th>
                <td><sf:input id = "login_name" size="30" maxlength="30" type="text" path="login"/><br>
                    <sf:errors path="login"/>
                </td>
            </tr>
            <tr>
                <th><label for = "pass_login">Пароль:</label></th>
                <td><sf:input id="pass_login" size="30" maxlength="30" type="password" path="password"/>
                    <small>минимум 6 символов</small><br>
                    <sf:errors path="password"/>
                </td>
            </tr>
            <tr>
                <th><label for = "email_login">Почта:</label></th>
                <td><sf:input id="email_login" size="30" maxlength="30" type="text" path="email"/><br>
                    <sf:errors path="email"/>
                </td>
            </tr>
            <tr>
                <th><label for = "first_login">Имя:</label></th>
                <td><sf:input id="first_login" size="30" maxlength="30" type="text" path="firstName"/><br>
                    <sf:errors path="firstName"/>
                </td>
            </tr>
            <tr>
                <th><label for = "last_login">Фамилия:</label></th>
                <td><sf:input id="last_login" size="30" maxlength="30" type="text" path="lastName"/><br>
                    <sf:errors path="lastName"/>
                </td>
            </tr>

        </table>
        <p></p>

        <div align="center" style="margin-top: 30px">
            <input type="submit" value="Зарегистрироваться"/>
        </div>

    </sf:form>

    <p></p>

    <div align="center" style="margin-top: 30px">
        <form action="/check" method="get">
            <input type="submit" value="Авторизация"/>
        </form>
    </div>

</center>

</body>
</html>
