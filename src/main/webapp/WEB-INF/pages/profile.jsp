
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title></title>
</head>
<body>
<span style="float: right">
    <a href="?lang=en">en</a>
    |
    <a href="?lang=ru">ru</a>
</span>
<center>

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
