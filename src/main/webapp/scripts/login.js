$(function(){
    const rememberMeCheckbox = $("#remember-me");
    const emailTextInput = $("#email");
    const loginForm = $("#login-form");
    if(localStorage.getItem("myEmail")) {
        rememberMeCheckbox.prop("checked", true);
        emailTextInput.val(localStorage.getItem("myEmail"));
    } else {
        rememberMeCheckbox.prop("checked", false);
    }

    loginForm.submit(function(event) {
        event.preventDefault();
        if(rememberMeCheckbox.is(":checked")) {
            localStorage.setItem("myEmail", emailTextInput.val());
        } else {
            localStorage.removeItem("myEmail");
        }
        event.currentTarget.submit();
    });
});