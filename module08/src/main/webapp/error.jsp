<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file="/WEB-INF/styles/styles.css"%>
</style>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Калькулятор доходности вклада</title>
    <meta charset="UTF-8">
</head>
<body>
<h1>Ошибка</h1>
<h3> <%= request.getAttribute("error")%> </h3>
<hr/>
<div class="center"><a href="javascript:window.history.back();">Назад</a></div>
</body>
</html>
