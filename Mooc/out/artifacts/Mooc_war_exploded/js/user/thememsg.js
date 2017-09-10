var curPage = 1;
var pageNumber = 3;
$(function () {
    getMsg();
    $("#loadmore").click(getMsg);
});

function getMsg() {
    if ($("#loadmore").val() == "我也是有底线的...")
        return;
    $.post(getContextPath() + "/user/message!appendThememsg.action",
        {"curPage": curPage, "pageNumber": pageNumber}, function (data) {
            if (data == "") {
                $("#loadmore").val("我也是有底线的...");
                return;
            }
            for (var x = 0; x < data.length; x++) {
                var ele = '<div class="col-sm-12 msgitem" style="display: block;">' +
                    '                <h3>' +
                    '                    <a class="msg-title" href="user/message!msg_show.action?msgid='+data[x].msgid+'">'+data[x].msgtopic+'</a>' +
                    '                    &nbsp; &nbsp;&nbsp;' +
                    '                    <span class="badge">'+data[x].theme.thename+'</span>' +
                    '                </h3>' +
                    '                <p class="author">'+data[x].user.username+' • '+data[x].msgtime+'</p>' +
                    '                <p class="msgcontent">'+data[x].msgcontents+'</p>' +
                    '                <div class="rightinfo">' +
                    '                    <span class="count">'+data[x].count.accesscount+'次浏览    • '+data[x].count.replycount+'个回复 • </span>' +
                    '                    <a class="msglink" href="user/message!msg_show.action?msgid='+data.msgid+'">详细&gt;&gt;</a>' +
                    '                </div>' +
                    '            </div>';
                $("#msgDiv").append(ele);
            }
            curPage = curPage + 1;
        }, "json");
}