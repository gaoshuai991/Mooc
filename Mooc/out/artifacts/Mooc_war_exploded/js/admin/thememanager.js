var pageNum = 3;
var kw = "";
var rows;
var currPage;
$(function () {
    goPage(1);
});

function goPage(curPage) {
    $.post(getContextPath() + "/admin!themelist.action",
        {
            "curPage": curPage,
            "pageNum": pageNum,
            "kw": kw
        }, function (data) {
            $(".msgListDiv").empty();
            var result = data.data;
            for (var x = 0; x < result.length; x++) {
                var ele = $('<div class="row">' +
                    '        <div class="col-sm-offset-2 col-sm-8 col-xs-12 msglist">' +
                    '            <div class="col-sm-2 col-xs-2">'+result[x].theid+'</div>' +
                    '            <div class="col-sm-6 col-xs-5 title">'+result[x].thename+'</div>' +
                    '            <div class="col-sm-2 col-xs-2">'+result[x].count+'</div>' +
                    '            <div class="col-sm-2 col-xs-3 ">' +
                    '                <button class="btn btn-danger" onclick="del(' + result[x].theid +',' + curPage + ',' + data.rows + ',' + data.totalPage +')">删除</button>' +
                    '            </div>' +
                    '        </div>' +
                    '    </div>');
                rows = data.rows;
                currPage = curPage;
                $(".msgListDiv").append(ele);
            }
            setPage(curPage, data.totalPage, "goPage");
        }, "json");
}

function search() {
    kw = $("#key").val();
    goPage(1);
}

function add(){
    $.post(getContextPath() + "/admin!addtheme.action", {"thename":$("#thename").val()}, function (data) {
        if(data.trim().length > 0){
            goPage(rows/currPage == pageNum ? (currPage + 1) : currPage);
        }else{
            alert("添加失败！");
        }
    }, "text");
}


function del(theid,curPage,rows,totalPage) {
    $.post(getContextPath() + "/admin!deltheme.action", {"theid":theid}, function (data) {
        if(data.trim() == "true"){
            goPage((curPage == totalPage && rows % pageNum == 1) ? (curPage - 1) : curPage);
        }else{
            alert("状态切换失败！");
        }
    }, "text");
}