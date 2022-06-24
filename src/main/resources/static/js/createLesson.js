$(document).ready(function () {
    renderLessons($("#group").val())
})

$("#group").change(function () {
    renderLessons($("#group").val())
})

$("#week").change(function () {
    renderLessons($("#group").val())
})

$("#lesson").submit(function (e) {
    e.preventDefault()
    if (!$("#weekTypeChislitel").is(":checked") && !$("#weekTypeZnamenatel").is(":checked")) {
        alert("Числитель или знаменатель не могут быть одновременно не выбраны!")
    } else {
        createLesson();
    }
})

function createLesson() {
    let formData = {
        disciplineId: parseInt($("#discipline").val()),
        weekId: parseInt($("#week").val()),
        timeLessonId: parseInt($("#timeLesson").val()),
        lecture: $("#lecture").is(":checked"),
        weekTypeChislitel: $("#weekTypeChislitel").is(":checked"),
        weekTypeZnamenatel: $("#weekTypeZnamenatel").is(":checked"),
        groupId: parseInt($("#group").val()),
        link: $("#link").val(),
        link2: $("#link2").val()
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/api/lessons",
        data: JSON.stringify(formData),
        data_type: "json",
        beforeSend: function (xhr) {
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            xhr.setRequestHeader(header, token)
        },
        success: function (data) {
            window.location = "/teacher/lessons"
            alert(data.responseText)
        },
        error: function (data) {
            alert(data.responseText)
        }
    })
}


function renderLessons(id) {
    $.get("http://localhost:8080/api/all-lesson-by-week-groupId/" + $("#week").val() + "/" + id, function (data) {
        let lessons = ''
        let typeLesson = ''
        let typeWeek = ''
        let arr = data
        let min = Number.POSITIVE_INFINITY;
        $.each(data, function (index, lesson) {
            lessons += `
                    <tr>
                        <td scope="row">${lesson.timeLesson}</td>
                        <td scope="row">${lesson.discipline}</td>
                        <td scope="row">${lesson.group}</td>
                        <td scope="row">${lesson.teacher}</td>
                `
            if (lesson.isLecture) {
                typeLesson = 'Лекция'
            } else {
                typeLesson = 'Лабораторная'
            }
            lessons += `<td scope="row">${typeLesson}</td>`
            if (lesson.weekTypeZnamenatel && lesson.weekTypeChislitel) {
                typeWeek = 'Каждую неделю'
            } else if (lesson.weekTypeZnamenatel) {
                typeWeek = 'Знаменатель'
            } else {
                typeWeek = 'Числитель'
            }
            lessons += `<td>${typeWeek}</td></tr>`
        })
        document.querySelector("#lessons tbody").innerHTML = lessons
    })
}