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

<h1>Калькулятор доходности вклада</h1>

<form action="finance" method="post">
    <label>Сумма на момент открытия</label> <input type="number" min="0" step="1" name="sum"/><br/>
    <label>Процентная ставка</label> <input type="number" min="1" max="100" step="1" name="percent"/><br/>
    <label>Количество лет</label> <input type="number" min="0" max="30" step="1" name="years"/><br/>
    <input type="submit" value="Посчитать"/>
</form>

</body>
</html>
