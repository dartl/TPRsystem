$(document).ready(function() {

    $(".navigation ul li").click(function() {
        $(".tasks > div").fadeOut(400).removeClass("active");
        $(".tasks").children("[data-number="+$(this).attr("data-number")+"]").fadeIn(400).addClass("active");
    });

    var startPending = null;
    (function() {
        var timerId = -1;
        var $historyContainer = $("#system-history");
        var $pendingTasksContainer = $("#pending-tasks");

        startPending = function() {
            var callback = function(history, running) {

                for (var i = 0; i < history.length; i++) {
                    var historyItem = history[i];
                    var possiblyExecution = $("#pending" + historyItem.taskId);
                    if (possiblyExecution.length > 0) {
                        possiblyExecution.remove();
                    }
                    if (!$("#history" + historyItem.taskId).length) {
                        $historyContainer.append(renderTemplate("history-item-template", historyItem, "history"));
                    }
                }
                for (i = 0; i < running.length; i++) {
                    var runningItem = running[i];
                    if (!$("#pending" + runningItem.taskId).length) {
                        $pendingTasksContainer.append(renderTemplate("pending-task-template", runningItem, "pending"));
                    }
                }
            };
            timerId = setInterval(createPendingFunction(callback), 1500);
        };
    })();
    startPending();
});

function SendPostContacts(taskId) // Наша функция, которая будет осуществлять ajax-отправку
{
    jQuery.ajax({	// Начала конструкции для работы с Ajax через jQuery
        type: "POST", // Метод, которым получаем данные из формы
        url: "/trpsystem/solveForm/" + taskId, // Обработчик формы.
        data: jQuery(".active form").serialize(), // Этот метод, берет форму с id=form и достает оттуда данные
        success: function(html) {	// функция выполняемая при успешном отправлении данных

        }
    });
}

function createPendingFunction(callback) {
    return function() {
        jQuery.ajax({
            type: "GET",
            url: "/trpsystem/status",
            contentType: "application/json",
            dataType: "json",
            success: function(data) {
                var history = data.history;
                var running = data.running;
                callback(history, running);
            }
        });
    };
}

function renderTemplate(templateId, object, idPrefix) {
    var clone = $("#" + templateId).clone();
    clone.attr("id", idPrefix + object.taskId);
    clone.removeClass("template");
    var keys = Object.keys(object);
    for (var i = 0; i < keys.length; i++) {
        var key = keys[i];
        var value = object[key];
        if (key == "timestamp") {
            value = new Date(value);
            value = value.getHours() + ":" + value.getMinutes() + ":" + value.getSeconds();
        }
        if (key == "inputData") {
            value = JSON.parse(value);
            var inputKeys = Object.keys(value);
            var str = "";
            for (var j = 0; j < inputKeys.length; j++) {
                var k = inputKeys[j];
                var v = value[k];
                str = str + k + ": " + v + (j < inputKeys.length - 1 ? ", " : "");
            }
            value = str;
        }
        clone.find("[data-field-name=" + key + "]").text(value);
    }
    return clone;
}