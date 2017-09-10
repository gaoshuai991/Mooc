$(function () {
    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose: true,//选中自动关闭
        startDate:'1900-01-01',
        todayBtn: true//显示今日按钮
    });
    // 验证表单
    $("#registerform").bootstrapValidator({
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
                        min: 3,
                        max: 16,
                        message: '用户名长度必须在3到16之间'
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
                        min: 3,
                        max: 16,
                        message: '密码长度必须在3到16之间'
                    }
                }
            },
            "confpwd": {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '密码不合法, 请重新输入'
                    },
                    stringLength: {
                        min: 3,
                        max: 16,
                        message: '密码长度必须在3到16之间'
                    },
                    identical:{
                        field:"user.password",
                        message:"两次密码输入不一致"
                    }
                }
            },
            "user.realname": {
                validators: {
                    notEmpty: {
                        message: '真实姓名不能为空'
                    }
                }
            },
            "user.city": {
                validators: {
                    notEmpty: {
                        message: '所在城市不能为空'
                    }
                }
            },
            "user.email": {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    regexp: {
                        regexp: /^\w+@\w+\.\w+$/,
                        message: '邮箱格式不正确'
                    }
                }
            },
            "user.qq": {
                validators: {
                    notEmpty: {
                        message: 'QQ不能为空'
                    },
                    regexp: {
                        regexp: /^\d{5,11}$/,
                        message: '输入不合法, 请重新输入'
                    }
                }
            }
        }
    });
});