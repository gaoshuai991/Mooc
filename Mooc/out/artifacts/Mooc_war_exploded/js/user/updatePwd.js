$(function () {
    // 验证表单
    $("#modifyform").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            /*输入框不同状态，显示图片的样式*/
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitButtons:'input[type="submit"]',
        fields: {
            /*验证*/
            "oldpwd": {
                validators: {
                    notEmpty: {
                        message: '旧密码不能为空'
                    }
                }
            },
            "newpwd": {
                validators: {
                    notEmpty: {
                        message: '新密码不能为空'
                    }
                }
            },
            "confpwd": {
                validators: {
                    notEmpty: {
                        message: '确认密码不能为空'
                    },
                    identical:{
                        field:"newpwd",
                        message:"两次密码输入不一致"
                    }
                }
            }
        }
    });
});