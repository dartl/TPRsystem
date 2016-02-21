<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/css/style.css" var="mainCss" />
<spring:url value="/js/jQuery.js" var="jQuery" />
<spring:url value="/js/script.js" var="mainJs" />
<html>
<head>
    <title>Решение типовых задачь по Теории принятия решений</title>
    <link href="${mainCss}" rel="stylesheet" />
    <script src="${jQuery}"></script>
    <script type="text/javascript" src="${mainJs}"></script>
</head>
<body>
	<h1>Типовые задачи по «Теории принятия решений»</h1>
    <div class="block50">
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
    </div>
    <div class="block50">
        <div id="system-status" class="status block50">
            <div id="system-history" class="history">

            </div>
            <div id="pending-tasks" class="pending-tasks">

            </div>
        </div>
    </div>
    <div class="block50">
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
    </div>

    <div id="history-item-template" class="template history-item">
        <div class="task-name" data-field-name="taskIdentifier"></div>
        <div class="task-input" data-field-name="inputData"></div>
        <div class="task-output" data-field-name="outputData"></div>
        <div class="task-timestamp" data-field-name="timestamp"></div>
    </div>

    <div id="pending-task-template" class="template pending-task">
        <div class="task-name" data-field-name="taskIdentifier"></div>
        <div class="task-input" data-field-name="inputData"></div>
        <div class="task-timestamp" data-field-name="timestamp"></div>
        <div class="progress-bar"></div>
    </div>

</body>
</html>