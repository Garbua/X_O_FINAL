<%--
  Created by IntelliJ IDEA.
  User: пк
  Date: 16.09.2018
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>

<h2>Страница регистрации пользователя</h2>
<c:forEach items="${messagesCol}" var="m">
    ${m.name} : ${m.text} <br>
</c:forEach>

<hr>
<hr>
<%--modelAttribute тоде самое что и commandName на jsp. только на JSP!!!--%>
<%--commandName используеться в 3 спринге и поддерживаеться в 4 для обратной совместимости--%>
<springform:form method="post" action="/springforum/show" modelAttribute="mKey">
    ник <springform:input type="text" path="name"/>
    <springform:errors path="text"/>
    <br>
    сообщение <springform:input type="text" path="text"/>
    <springform:errors path="name"/>
    <br>
    <input type="submit" value="послать сообщение"/>
</springform:form>

<%--springform:form - рисует форму по обьекту--%>
<%--springform:input - рисует инпут по полю обьекта--%>
<%--springform:errors - обробаываем и показывает ошибки по поле обьекта если они есть--%>
</body>
</html>
