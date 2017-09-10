$(function () {
    $("#updatePwdForm").validate({
        debug: true, // 取消表单的提交操作
        submitHandler: function (form) {
            form.submit(); // 提交表单
        },
        errorPlacement: function (error, element) {
            // $("#" + $(element).attr("id").replace(".", "\\.") + "Msg").append(error);
        },
        highlight: function (element, errorClass) {
            $(element).fadeOut(1, function () {
                $(element).fadeIn(1, function () {
                    // $("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-error");
                    $("#" + $(element).attr("id").replace(".", "\\.")).css("border", "1px solid red");
                });

            })
        },
        unhighlight: function (element, errorClass) {
            $(element).fadeOut(1, function () {
                $(element).fadeIn(1, function () {
                    // $("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-success");
                    $("#" + $(element).attr("id").replace(".", "\\.")).css("border", "");
                });
            })
        },
        // errorClass: "text-danger",
        rules: {
            "admin.pwd": {
                required: true
            },
            "newpwd": {
                required: true
            },
            "confpwd": {
                required: true,
                equalTo:"#newpwd"
            }
        }
    });
});