$(function(){
    $("#addmsgform").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {/*输入框不同状态，显示图片的样式*/
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitButtons:'input[type="submit"]',
        fields: {/*验证*/
            "message.msgtopic": {/*键名username和input name值对应*/
                message: 'The msgtopic is not valid',
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '标题不能为空'
                    },
                    stringLength: {/*长度提示*/
                        min: 6,
                        max: 200,
                        message: '标题长度必须在6到200之间'
                    }/*最后一个没有逗号*/

                }
            },
            "message.msgcontents": {
                message:'The msgcontents is not valid',
                validators: {
                    notEmpty: {
                        message: '问题内容不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 1000,
                        message: '问题内容长度必须在6到1000之间'
                    }
                }
            }
        }
    });
});