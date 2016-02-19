<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/style.css" var="mainCss" />
<spring:url value="/resources/jQuery.js" var="jQuery" />
<spring:url value="/resources/script.js" var="mainJs" />
<html>
<head>
    <title>Решение типовых задачь по Теории принятия решений</title>
    <link href="${mainCss}" rel="stylesheet" />
    <script src="${jQuery}"></script>
    <script type="text/javascript" src="${mainJs}"></script>
</head>
<body>
	<h1>Типовые задачи по «Теории принятия решений»</h1>
    <div class="navigation">
        <h2>Выберите интересующую вас задачу:</h2>
        <ul>
            <li data-number="1">Задача №1</li>
            <li data-number="2">Задача №2</li>
            <li data-number="3">Задача №3</li>
            <li data-number="4">Задача №4</li>
            <li data-number="5">Задача №5</li>
            <li data-number="6">Задача №6</li>
        </ul>
    </div>
    <div class="tasks">
        <div data-number="1" class="active">
            <h3>Задача №1. Название задачи.</h3>
            <form action="" method="get">
                <p><span>Входное данное №1:</span> <input type="text" name="name"></p>
                <p><span>Входное данное №2:</span> <input type="text" name="name1"></p>
                <p><span>Входное данное №3:</span> <input type="text" name="name2"></p>
                <input type="button" class="button" value="Решить" onclick="javascript:SendPostContacts();">
            </form>
            <textarea class="resultArea"></textarea>
        </div>
        <div data-number="2">
            <h3>Задача №2. Название задачи.</h3>
            <form action="" method="get">
                <p><span>Входное данное №1:</span> <input type="text" name="name"></p>
                <p><span>Входное данное №2:</span> <input type="text" name="name1"></p>
                <p><span>Входное данное №3:</span> <input type="text" name="name2"></p>
                <input type="button" class="button" value="Решить" onclick="javascript:SendPostContacts();">
            </form>
            <textarea class="resultArea"></textarea>
        </div>
        <div data-number="3">
            <h3>Задача №3. Название задачи.</h3>
            <form action="" method="get">
                <p><span>Входное данное №1:</span> <input type="text" name="name"></p>
                <p><span>Входное данное №2:</span> <input type="text" name="name1"></p>
                <p><span>Входное данное №3:</span> <input type="text" name="name2"></p>
                <input type="button" class="button" value="Решить" onclick="javascript:SendPostContacts();">
            </form>
            <textarea class="resultArea"></textarea>
        </div>
        <div data-number="4" class="active">
            <h3>Задача №4. Название задачи.</h3>
            <form action="" method="get">
                <p><span>Входное данное №1:</span> <input type="text" name="name"></p>
                <p><span>Входное данное №2:</span> <input type="text" name="name1"></p>
                <p><span>Входное данное №3:</span> <input type="text" name="name2"></p>
                <input type="button" class="button" value="Решить" onclick="javascript:SendPostContacts();">
            </form>
            <textarea class="resultArea"></textarea>
        </div>
        <div data-number="5">
            <h3>Задача №5. Название задачи.</h3>
            <form action="" method="get">
                <p><span>Входное данное №1:</span> <input type="text" name="name"></p>
                <p><span>Входное данное №2:</span> <input type="text" name="name1"></p>
                <p><span>Входное данное №3:</span> <input type="text" name="name2"></p>
                <input type="button" class="button" value="Решить" onclick="javascript:SendPostContacts();">
            </form>
            <textarea class="resultArea"></textarea>
        </div>
        <div data-number="6">
            <h3>Задача №6. Название задачи.</h3>
            <form action="" method="get">
                <p><span>Входное данное №1:</span> <input type="text" name="name"></p>
                <p><span>Входное данное №2:</span> <input type="text" name="name1"></p>
                <p><span>Входное данное №3:</span> <input type="text" name="name2"></p>
                <input type="button" class="button" value="Решить" onclick="javascript:SendPostContacts();">
            </form>
            <textarea class="resultArea"></textarea>
        </div>
    </div>
</body>
</html>