$(function(){
    loadData();
});
function loadData(){
    $.post(getContextPath()+"/admin!index.action",{},function (data) {
        if(data) {
            $("#msgday").html(data.msgday);
            $("#msgweek").html(data.msgweek);
            $("#msgmonth").html(data.msgmonth);
            $("#replyday").html(data.replyday);
            $("#replyweek").html(data.replyweek);
            $("#replymonth").html(data.replymonth);
        }
    },"json");
}