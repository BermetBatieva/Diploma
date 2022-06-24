let url = ""
$(document).ready(function () {
    currentDay()
    url = "http://localhost:8080/api/all-lessons-by-week/" + $(".active").data("id")
    renderLessons(url)
})

$("#groups").change(function () {
    if ($("#groups").val() == 'all') {
        url = "http://localhost:8080/api/all-lessons-by-week/" + $(".active").data("id")
    } else {
        url = "http://localhost:8080/api/all-lesson-by-week-groupId/" + $(".active").data("id") + "/" + $("#groups").val()
    }
    renderLessons(url)
})

function currentDay() {
    let currentDay = new Date().getDay()
    let array = $(".week-id").map(function () {
        return $(this).data("id")
    })
    $.each(array, function (index, element) {
        if (element == currentDay) {
            $("#weekDays").find("[data-id='" + element + "']").addClass("active")
        }
    })
}

function renderLessons(url) {
    $.get(url, function (data) {
        let lessons = ''
        let week = ''
        let typeLesson = ''
        $.each(data, function (index, lesson) {
            if (lesson.weekTypeZnamenatel && lesson.weekTypeChislitel) {
                week = 'Каждую неделю'
            } else if (lesson.weekTypeZnamenatel) {
                week = 'Знаменатель'
            } else if (lesson.weekTypeChislitel) {
                week = 'Числитель'
            }
            if (lesson.isLektion) {
                typeLesson = 'Лекция'
            } else {
                typeLesson = 'Лабораторная'
            }
            lessons += `
                    <tr>
                        <td>${lesson.timeLesson}</td>
                        <td>${lesson.discipline}</td>
                        <td>${lesson.group}</td>
                        <td>${typeLesson}</td>
                        <td>${week}</td>
                        <td class="text-center" style="width: 300px"><a href="${lesson.link}"><img width="38" src="/images/video-call.png"></a></td>
                        <td><a href="/teacher/lesson/update/${lesson.idLesson}" class="change btn btn-warning">Изменить</a></td>
                        <td><a href="/api/lessons/${lesson.idLesson}" class="change btn btn-warning">Изменить</a></td>
                    </tr>
                `
        })
        document.querySelector("#lessons tbody").innerHTML = lessons
    })
}

$("#weekDays .week-id").on("click", function (event) {
    let id = $(event.target).data("id");
    $(".active").removeClass("active")
    $("#weekDays").find("[data-id='" + id + "']").addClass("active")
    if ($("#groups").val() == 'all') {
        url = "http://localhost:8080/api/all-lessons-by-week/" + id
    } else {
        url = "http://localhost:8080/api/all-lesson-by-week-groupId/" + id + "/" + $("#groups").val()
    }

    renderLessons(url)
})