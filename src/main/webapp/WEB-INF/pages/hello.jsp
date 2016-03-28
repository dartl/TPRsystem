<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<spring:url value="/css/style.css" var="mainCss"/>
<spring:url value="/css/bootstrap.min.css" var="bootstrap"/>
<spring:url value="/js/jQuery.js" var="jQuery"/>
<spring:url value="/js/script.js" var="mainJs"/>
<html>
<head>
    <title>Решение типовых задачь по Теории принятия решений</title>
    <link href="${mainCss}" rel="stylesheet"/>
    <link href="${bootstrap}" rel="stylesheet"/>
    <script src="${jQuery}"></script>
    <script type="text/javascript" src="${mainJs}"></script>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-4" aria-expanded="false"><span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span></button>
            <a class="navbar-brand" href="#">ТПР</a></div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-4"><p class="navbar-text">Теория принятия решений</p></div>
    </div>
</nav>

<div class="row">
    <div class="col-lg-6 col-md-6 col-sm-12">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">Задачи</div>
                    <div class="panel-body">
                        Выберите интересующую вас задачу, введите условия и нажмите решить
                    </div>
                    <ul class="list-group">
                        <%--@elvariable id="taskDefinitions" type="java.util.List<ru.eltech.tprsystem.web.task.TaskDefinition>"--%>
                        <c:forEach items="${taskDefinitions}" var="task" varStatus="status">
                            <li class="list-group-item task" data-number="${status.index}">Задача №${status.index}: <c:out
                                    value="${task.title}"/></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="tasks">
                    <%--@elvariable id="taskDefinitions" type="java.util.List<ru.eltech.tprsystem.web.task.TaskDefinition>"--%>
                    <c:forEach items="${taskDefinitions}" var="task" varStatus="status">
                        <div class="panel panel-default" data-number="${status.index}">
                            <div class="panel-heading">
                                Задача №${status.index}. <c:out value="${task.title}"/>
                            </div>
                            <div class="panel-body">
                                <div class="well well-lg">
                                    <c:out value="${task.definition}"/>
                                </div>
                                <form action="" method="get">
                                    <c:forEach items="${task.variables}" var="variable">
                                        <div class="input-group">
                                            <span class="input-group-addon" id="basic-addon3"><c:out value="${variable}"/></span>
                                            <input type="text" class="form-control" name="<c:out value="${variable}"/>" aria-describedby="basic-addon3">
                                        </div>
                                        <br/>
                                    </c:forEach>
                                    <input type="button" class="btn btn-primary" value="Решить" onclick="javascript:SendPostContacts(${status.index});"/>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6 col-md-6 col-sm-12">
        <div id="system-status" class="status">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-12">
                    <div class="panel panel-success">
                        <div class="panel-heading">Выполненные задачи</div>
                        <div class="panel-body">
                            <div id="system-history" class="history">

                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-12">
                    <div class="panel panel-warning">
                        <div class="panel-heading">Выполняющиеся задачи</div>
                        <div class="panel-body">
                            <div id="pending-tasks" class="pending-tasks">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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