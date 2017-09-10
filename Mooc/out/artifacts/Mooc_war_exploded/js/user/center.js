$(function(){
    $(".form_datetime").datetimepicker({
        format: 'yyyy-mm-dd',
        minView:'month',
        language: 'zh-CN',
        autoclose: true,//选中自动关闭
        startDate:'1900-01-01',
        todayBtn: true//显示今日按钮
    });

    getUser();

    validateForm();
});

function getUser(){
    $.post(getContextPath() + "/user!get.action",{"userid":getUserId()},function(data){
        $("#user\\.realname").val(data.realname);
        $("input[name='user.sex']").each(function(){
            this.checked=$(this).val() == data.sex;
        });
        $("input[name='hobbys']").each(function(){
            var hobbys = data.hobbys.split("、");
               this.checked=$.inArray($(this).val(),hobbys) != -1;
        });
        $("#user\\.birthday").val(new Date(data.birthday.time).format("yyyy-MM-dd"));
        $("#user\\.city").val(data.city);
        $("#user\\.email").val(data.email);
        $("#user\\.qq").val(data.qq);
    },"json");
}
function validateForm(){
    $("#updateform").bootstrapValidator({
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
}
