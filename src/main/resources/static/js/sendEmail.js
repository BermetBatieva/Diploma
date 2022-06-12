$("#sendEmail").submit(function (e) {
    e.preventDefault()
    sendEmail()
})

function sendEmail() {
    let formData = {
        email: $("#email").val()
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/api/users/email?email=" + formData.email,
        data: JSON.stringify(formData),
        data_type: "json",
        beforeSend: function (xhr) {
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            xhr.setRequestHeader(header, token)
        }
    }).done(function () {
        alert("На вашу почту была отправлена ссылка для восстоновления пароля")
        window.location.href = "http://localhost:8080/login"
    })
}