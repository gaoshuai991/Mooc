$(function () {
    // 验证表单
    $("#loginform").bootstrapValidator({
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
            "user.username": {
                /*键名username和input name值对应*/
                message: '用户名格式错误',
                validators: {
                    notEmpty: {
                        /*非空提示*/
                        message: '用户名不能为空'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '用户名不合法, 请重新输入'
                    },
                    stringLength: {
                        /*长度提示*/
                        min: 2,
                        max: 16,
                        message: '用户名长度必须在2到16之间'
                    }
                }
            },
            "user.password": {
                message: '密码无效',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '密码不合法, 请重新输入'
                    },
                    stringLength: {
                        min: 2,
                        max: 16,
                        message: '密码长度必须在2到16之间'
                    }
                }
            }
        }
    });
});