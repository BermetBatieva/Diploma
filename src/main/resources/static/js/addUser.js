let role = $("#role").val();
$("#faculty").prop("disabled", true)

$("#role").on("change", function () {
    role = $("#role").val()
    if (role === "TEACHER") {
        $("#faculty").prop("disabled", false)
        $("#group").prop("disabled", true)
    } else {
        $("#faculty").prop("disabled", true)
        $("#group").prop("disabled", false)
    }
})

$("#user").submit(function (e) {
    e.preventDefault()
    addUser()
})

function addUser() {
    let formData = {
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        patronymic: $("#patronymic").val(),
        mobilePhoneNumber: $("#mobilePhoneNumber").val(),
        username: $("#username").val(),
        email: $("#email").val(),
        password: $("#password").val(),
        facultyId: parseInt($("#faculty").val()),
        groupId: parseInt($("#group").val()),
        course: parseInt($("#course").val()),
        role: $("#role").val()
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/api/users",
        data: JSON.stringify(formData),
        data_type: "json",
        beforeSend: function (xhr) {
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            xhr.setRequestHeader(header, token)
        }
    })
}