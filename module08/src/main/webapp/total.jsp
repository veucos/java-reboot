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

<h1>Результат</h1>
<h3> Итоговая сумма <%= request.getAttribute("total")%> руб</h3>
<hr/>
<div class="center"><a href="javascript:window.history.back();">Назад</a></div>
</body>
</html>
