var curPage = 1;
var pageNumber = 3;
var count = 0;
$(function () {
    getMsg();
    $("#loadmore").click(getMsg);
    $("#replyBtn").click(replyMsg);
});

function replyMsg() {
    if ($("#replycontent").val() == "") {
        alert("请输入回复内容！");
        return;
    }
    $.post(getContextPath() + "/user!reply.action",
        {
            "msgid": getMsgid(),
            "replycontent": $("#replycontent").val()
        },
        function (data) {
            if (data) {
                if($("div[id*=reply-]").length < (curPage - 1) * pageNumber || $("#loadmore").val() == "我也是有底线的..."){
                    var ele = $('<div class="row reply" id="reply-' + data.replyid + '" style="display: block;">' +
                        '            <div class="col-sm-12" style="overflow: hidden;">' +
                        '                <div class="rightinfo order">' + ($("div[id*=reply-]").length + 1) + '楼</div>' +
                        '            </div>' +
                        '            <div class="col-sm-2 col-xs-2">' +
                        '                <div class="author">' + data.user.realname + '</div>' +
                        '                <div class="sex">' + data.user.sex + '</div>' +
                        '                <div class="city">' + data.user.city + '</div>' +
                        '            </div>' +
                        '            <div class="col-sm-10 col-xs-10">' +
                        '                <div class="msgcontent">' + data.replycontents + '</div>' +
                        '            </div>' +
                        '            <div class="col-sm-12" style="overflow: hidden;">' +
                        '                <div class="rightinfo time">' + data.replyip + ' • ' + data.replytime + '</div>' +
                        '            </div>' +
                        '        </div>');
                    $("#msgDiv").append(ele);
                }
                alert("回复成功！");
            }else{
                alert("回复失败，未知错误！")
            }
        }, "json");
}

function getMsg() {
    if ($("#loadmore").val() == "我也是有底线的...")
        return;
    $.post(getContextPath() + "/user/message!appendReply.action",
        {"curPage": curPage, "pageNumber": pageNumber, "msgid": getMsgid()}, function (data) {
            if (data == "") {
                $("#loadmore").val("我也是有底线的...");
                return;
            }
            for (var x = 0; x < data.length; x++) {
                count = count + 1;
                var ele = $('<div class="row reply" id="reply-' + data[x].replyid + '" style="display: block;">' +
                    '            <div class="col-sm-12" style="overflow: hidden;">' +
                    '                <div class="rightinfo order">' + count + '楼</div>' +
                    '            </div>' +
                    '            <div class="col-sm-2 col-xs-2">' +
                    '                <div class="author">' + data[x].user.realname + '</div>' +
                    '                <div class="sex">' + data[x].user.sex + '</div>' +
                    '                <div class="city">' + data[x].user.city + '</div>' +
                    '            </div>' +
                    '            <div class="col-sm-10 col-xs-10">' +
                    '                <div class="msgcontent">' + data[x].replycontents + '</div>' +
                    '            </div>' +
                    '            <div class="col-sm-12" style="overflow: hidden;">' +
                    '                <div class="rightinfo time">' + data[x].replyip + ' • ' + data[x].replytime + '</div>' +
                    '            </div>' +
                    '        </div>');
                $("#msgDiv").append(ele);
            }
            curPage = curPage + 1;
        }, "json");
}