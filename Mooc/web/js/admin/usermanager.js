var pageNum = 3;
var kw = "";
$(function () {
    goPage(1);

});

function goPage(curPage) {
    $.post(getContextPath() + "/admin!userlist.action",
        {
            "curPage": curPage,
            "pageNum": pageNum,
            "kw": kw
        }, function (data) {
            $(".msglist").remove();
            var result = data.data;
            for (var x = 0; x < result.length; x++) {
                var ele = $('<div class="row msglist">' +
                    '            <div class="col-sm-11 col-xs-8">' +
                    '                <div class="col-sm-1 col-xs-2 time">'+result[x].userid+'</div>' +
                    '                <div class="col-sm-1 col-xs-10 title">'+result[x].username+'</div>' +
                    '                <div class="col-sm-1 col-xs-4 time">'+result[x].realname+'</div>' +
                    '                <div class="col-sm-1 col-xs-4 time">'+result[x].sex+'</div>' +
                    // '                <div class="col-sm-1 col-xs-4 time" title="'+result[x].hobbys+'">'+result[x].hobbys+'</div>' +
                    '                <div class="col-sm-2 col-xs-4 time" title="'+result[x].birthday.split(" ")[0]+'">'+result[x].birthday.split(" ")[0]+'</div>' +
                    '                <div class="col-sm-1 col-xs-4 time">'+result[x].city+'</div>' +
                    '                <div class="col-sm-1 col-xs-4 time" title="'+result[x].qq+'">'+result[x].qq+'</div>' +
                    '                <div class="col-sm-2 col-xs-6 time tooltip-test" data-toggle="tooltip"' +
                    '                     title="' + result[x].email +'">' + result[x].email +
                    '                </div>' +
                    '                <div class="col-sm-2 col-xs-6 time">' + result[x].createtime +'</div>' +
                    '            </div>' +
                    '            <div class="col-sm-1 col-xs-3 ">' +
                    '                <button class="btn btn-'+(result[x].state == '1'?'danger':'success')+'" onclick="toggle(' + result[x].userid +',' + (result[x].state ^ 1) + ',' + curPage +')">'+(result[x].state == '1'?'删除':'恢复')+'</button>' +
                    '            </div>' +
                    '        </div>');
                $(".msgListDiv").append(ele);
            }
            setPage(curPage, data.totalPage, "goPage");
        }, "json");
}

function search() {
    kw = $("#key").val();
    goPage(1);
}


function toggle(userid,state,curPage) {
    $.post(getContextPath() + "/admin!toggleuserstate.action", {"userid":userid,"state":state}, function (data) {
        if(data.trim() == "true"){
            goPage(curPage);
        }else{
            alert("状态切换失败！");
        }
    }, "text");
}