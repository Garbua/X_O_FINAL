<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Крестики нолики</title>
</head>
<body>
<center>
<h2>Добро пожаловать в игру: Крестики нолики</h2>
<hr>
<br>
<hr>

<sf:form method="POST" action="/login/check" modelAttribute="user">
    <table align="center">
        <tr>
            <th><label for = "login_name">Пользователь:</label></th>
            <td><sf:input id = "login_name" size="16" type="text" path="login"/>
                <small>не более 16 символов</small>
                <sf:errors path="login"/>
                <br>
            </td>
        </tr>
        <tr>
            <th><label for = "pass_login">Пароль:</label></th>
            <td><sf:input id="pass_login" size="20" type="text" path="password"/>
                <small>не менее 6 символов</small>
                <sf:errors path="password"/>
                <br>
            </td>
        </tr>
        <tr>
            <td align="center">
                <input type="submit" value="Войти"/>
            </td>
        </tr>

    </table>

</sf:form>

    <a href="pages/registrationPage.jsp" target="_blank"> Регистрация </a>

</center>

<%--sf:form - рисует форму по обьекту--%>
<%--sf:input - рисует инпут по полю обьекта--%>
<%--sf:errors - обробаываем и показывает ошибки по поле обьекта если они есть--%>
</body>
</html>