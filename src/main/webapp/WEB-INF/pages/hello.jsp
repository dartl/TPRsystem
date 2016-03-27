<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<spring:url value="/css/style.css" var="mainCss"/>
<spring:url value="/js/jQuery.js" var="jQuery"/>
<spring:url value="/js/script.js" var="mainJs"/>
<html>
<head>
    <title>Решение типовых задачь по Теории принятия решений</title>
    <link href="${mainCss}" rel="stylesheet"/>
    <script src="${jQuery}"></script>
    <script type="text/javascript" src="${mainJs}"></script>
</head>
<body>
<h1>Типовые задачи по «Теории принятия решений»</h1>
<div class="block50">
    <div class="navigation">
        <h2>Выберите интересующую вас задачу:</h2>
        <ul>
            <%--@elvariable id="taskDefinitions" type="java.util.List<ru.eltech.tprsystem.web.task.TaskDefinition>"--%>
            <c:forEach items="${taskDefinitions}" var="task" varStatus="status">
                <li data-number="${status.index}">Задача №${status.index}: <c:out value="${task.title}"/></li>
            </c:forEach>
        </ul>
    </div>
</div>
<div class="block50">
    <div id="system-status" class="status block50">
        <h2>Выполненные задачи</h2>
        <div>
            <div id="system-history" class="history">

            </div>
        </div>
        <div>
            <h2>Выполняющиеся задачи</h2>
            <div id="pending-tasks" class="pending-tasks">

            </div>
        </div>
    </div>
</div>
<div class="block50">
    <div class="tasks">
        <%--@elvariable id="taskDefinitions" type="java.util.List<ru.eltech.tprsystem.web.task.TaskDefinition>"--%>
        <c:forEach items="${taskDefinitions}" var="task" varStatus="status">
            <div data-number="${status.index}">
                <h3>Задача №${status.index}. <c:out value="${task.title}"/></h3>
                <c:out value="${task.definition}"/>
                <form action="" method="get">
                    <c:forEach items="${task.variables}" var="variable">
                        <p><span>Входное значение "<c:out value="${variable}"/>":</span> <input type="text" name="<c:out value="${variable}"/>"></p>
                    </c:forEach>
                    <input type="button" class="button" value="Решить" onclick="javascript:SendPostContacts(${status.index});">
                </form>
            </div>
        </c:forEach>
    </div>
</div>

<div id="history-item-template" class="template card history-item">
    <div class="line">
        <strong>Название задачи:</strong>
        <div class="task-name" data-field-name="taskIdentifier"></div>
    </div>
    <div class="line">
        <strong>Входные данные:</strong>
        <div class="task-input" data-field-name="inputData"></div>
    </div>
    <div class="line">
        <strong>Вывод:</strong>
        <div class="task-output" data-field-name="outputData"></div>
    </div>
    <div class="line">
        <strong>Выполнено:</strong>
        <div class="task-timestamp" data-field-name="timestamp"></div>
    </div>
</div>

<div id="pending-task-template" class="template card pending-task">
    <div class="line">
        <strong>Название задачи:</strong>
        <div class="task-name" data-field-name="taskIdentifier"></div>
    </div>
    <div class="line">
        <strong>Входные данные:</strong>
        <div class="task-input" data-field-name="inputData"></div>
    </div>
    <div class="line">
        <strong>Запущено:</strong>
        <div class="task-timestamp" data-field-name="timestamp"></div>
    </div>
    <progress></progress>
</div>

</body>
</html>