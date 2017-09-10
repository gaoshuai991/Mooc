$(function(){
    $("#loginform").bootstrapValidator({
        message: '输入有误',
        feedbackIcons: {/*输入框不同状态，显示图片的样式*/
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {/*验证*/
            username: {/*键名username和input name值对应*/
                message: '用户名不合法',
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '用户名不能为空'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '用户名只能包含字母、数字或下划线'
                    },
                    stringLength: {/*长度提示*/
                        min: 3,
                        max: 16,
                        message: '用户名长度必须在6到16之间'
                    }
                }
            },
            password: {
                message:'密码无效',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 3,
                        max: 16,
                        message: '用户名长度必须在6到30之间'
                    }
                }
            }
        }
    });
});