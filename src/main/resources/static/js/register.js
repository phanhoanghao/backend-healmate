var countdown;
clearInterval(countdown);
$("#verify").on("click", function () {
    var phone = $("#phone").val();
    $.ajax({
        url: "/api/v1/auth/signup",
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({
            "userId":"",
            "phone": phone,
            "roleNames":["ADMIN_USER"]
        }),
        success: function (response) {
            $("#otpbox").css("display", "block");
            $("#countdown").css("display", "block");
            $("#verify").text("again");
            startCountdown();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error("AJAX Error:", textStatus, errorThrown);

        },
    });
});

$("#verifyOtp").on("click", function () {
    var phone = $("#phone").val();
    var enteredOTP = $("#code").val();
    $.ajax({
        url: "/api/v1/auth/verifyOtp",
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({
            "phone": phone,
            "otp": enteredOTP
        }),
        success: function (response) {
            clearInterval(countdown);
            $("#sucess").css("display", "block");
            $("#verifyOtp").css("display", "none");
            $("#verify").css("display", "none");
            $("#countdown").css("display", "none");
            $("#register").removeAttr('disabled');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#verifyOtpError").css("d-block");
            console.error("AJAX Error:", textStatus, errorThrown);
        },
    });
});

$('#logout').click(function (e) {
    e.preventDefault();
    $.ajax({
        type: 'POST',
        url: 'logout',
        data: {action: "logout"},
        success: function (response) {
            window.location.href = '/logout';
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error('AJAX Error:', textStatus, errorThrown);
        }
    });
});

function startCountdown() {
    var minutes = 1;
    var seconds = 0;

    countdown = setInterval(function () {
        seconds--;
        if (seconds < 0) {
            seconds = 59;
            minutes--;
            if (minutes < 0) {
                minutes = seconds = 0;
                clearInterval(countdown);
            }
        }
        var displayMinutes = minutes < 10 ? "0" + minutes : minutes;
        var displaySeconds = seconds < 10 ? "0" + seconds : seconds;
        $(".countdown").text(displayMinutes + ":" + displaySeconds);
    }, 1000);
}