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
                $historyContainer.empty();
                $pendingTasksContainer.empty();
                for (var i = 0; i < history.length; i++) {
                    var historyItem = history[i];
                    $historyContainer.append(renderTemplate("history-item-template", historyItem));
                }
                for (i = 0; i < running.length; i++) {
                    var runningItem = running[i];
                    $pendingTasksContainer.append(renderTemplate("pending-task-template", runningItem));
                }
            };
            timerId = setInterval(createPendingFunction(callback), 1500);
        };
    })();
    startPending();
});

function SendPostContacts() // Наша функция, которая будет осуществлять ajax-отправку
{
    jQuery.ajax({	// Начала конструкции для работы с Ajax через jQuery
        type: "POST", // Метод, которым получаем данные из формы
        url: "/trpsystem/solveForm", // Обработчик формы.
        data: jQuery(".active form").serialize(), // Этот метод, берет форму с id=form и достает оттуда данные
        success: function(html) {	// функция выполняемая при успешном отправлении данных
            $(".active .resultArea").val(html);
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

function renderTemplate(templateId, object) {
    var clone = $("#" + templateId).clone();
    clone.removeAttr("id");
    var keys = Object.keys(object);
    for (var i = 0; i < keys.length; i++) {
        var key = keys[i];
        var value = object[key];
        clone.find("[data-field-name=" + key + "]").text(value);
    }
    return clone;
}