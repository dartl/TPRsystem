$(document).ready(function() {
    $(".navigation ul li").click(function() {
        $(".tasks > div").fadeOut(400).removeClass("active");
        $(".tasks").children("[data-number="+$(this).attr("data-number")+"]").fadeIn(400).addClass("active");
    });
});

function SendPostContacts() // Наша функция, которая будет осуществлять ajax-отправку
{
    jQuery.ajax({	// Начала конструкции для работы с Ajax через jQuery
        type: "GET", // Метод, которым получаем данные из формы
        url: "/solveForm", // Обработчик формы.
        data: jQuery(".active form").serialize(), // Этот метод, берет форму с id=form и достает оттуда данные
        success: function(html) {	// функция выполняемая при успешном отправлении данных
            $(".active .resultArea").val(html);
        }
    });
}