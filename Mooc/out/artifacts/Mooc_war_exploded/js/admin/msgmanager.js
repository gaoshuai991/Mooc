var pageNum = 3;
var kw = "";
// var col = "xxx";
var username="";
var theid=-1;
$(function () {
    loadAllTheme();
    goPage(1);

});

function goPage(curPage) {
    $.post(getContextPath() + "/admin!msglist.action",
        {
            "curPage": curPage,
            "pageNum": pageNum,
            "kw": kw,
            "username": username,
            "theid":theid
        }, function (data) {
            $(".msglist").remove();
            var result = data.data;
            for (var x = 0; x < result.length; x++) {
                var ele = $('<div class="row msglist">' +
                    '        <div class="col-sm-1 col-xs-1 text-center">' + result[x].msgid + '</div>' +
                    '        <div class="col-sm-9 col-xs-8">' +
                    '            <div class="col-sm-7 col-xs-12 title">' + result[x].msgtopic + '</div>' +
                    '            <div class="col-sm-2 col-xs-6 author">' + result[x].user.username + '</div>' +
                    '            <div class="col-sm-3 col-xs-6 time">' + result[x].msgtime + '</div>' +
                    '        </div>' +
                    '        <div class="col-sm-2 col-xs-3 ">' +
                    '            <button class="btn btn-'+(result[x].state == '1'?'danger':'success')+'" onclick="toggle(' + result[x].msgid +',' + (result[x].state ^ 1) + ',' + curPage +')">'+(result[x].state == '1'?'删除':'恢复')+'</button>' +
                    '        </div>' +
                    '    </div>');
                $(".msgListDiv").append(ele);
            }
            setPage(curPage, data.totalPage, "goPage");
        }, "json");
}

function search() {
    kw = $("#key").val();
    username = $("#username").val();
    theid = $("#theme").val();
    goPage(1);
}

function loadAllTheme() {
    $.post(getContextPath() + "/admin!alltheme.action", {}, function (data) {
        for(var x = 0; x < data.length; x++){
            var opele = $('<option value="'+data[x].theid+'">'+data[x].thename+'</option>');
            $("#theme").append(opele);
        }
    }, "json");
}

function toggle(msgid,state,curPage) {
    $.post(getContextPath() + "/admin!togglemsgstate.action", {"msgid":msgid,"state":state}, function (data) {
        if(data.trim() == "true"){
            goPage(curPage);
        }else{
            alert("状态切换失败！");
        }
    }, "text");
}