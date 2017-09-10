<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css">
    <link rel="stylesheet" href="bootstrapvalidator/css/bootstrapValidator.css">
    <link rel="stylesheet" href="css/site.css">
    <script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 表单验证 -->
    <script src="bootstrapvalidator/js/bootstrapValidator.js" type="text/javascript"></script>
    <script src="js/user/message.js" type="text/javascript"></script>
    <title>慕课答疑平台</title>
    <script>
        function getMsgid() {
            return "${msg.msgid}";
        }
    </script>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container" id="msgDiv">
    <div class="row">
        <div class="col-sm-12 msgtitle">
            <h3>
                <span class="title">${msg.msgtopic}</span>&nbsp;&nbsp;<span class="badge">${msg.theme.thename}</span>
            </h3>
            <div class="replybtn">
                <c:if test="${sessionScope.user == null}">
                    <button type="button" class="btn btn-success" data-toggle="modal"
                            onclick="alert('请先登录！')">回复
                    </button>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <button type="button" class="btn btn-success" data-toggle="modal"
                            data-target="#reply">回复
                    </button>
                </c:if>
            </div>

        </div>
    </div>
    <div class="row reply" style="display: block;">
        <div class="col-sm-12" style="overflow: hidden;">
            <div class="rightinfo order">楼主</div>
        </div>
        <div class="col-sm-2 col-xs-2">
            <div class="author">${msg.user.realname}</div>
            <div class="sex">${msg.user.sex}</div>
            <div class="city">${msg.user.city}</div>
        </div>
        <div class="col-sm-10 col-xs-10">
            <div class="msgcontent">${msg.msgcontents}</div>
        </div>
        <div class="col-sm-12" style="overflow: hidden;">
            <div class="rightinfo time">${msg.msgip} • <fmt:formatDate value="${msg.msgtime}"
                                                                       pattern="yyyy-MM-dd HH:mm:ss"/></div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <br/>
            <input id="loadmore" type="button" class="btn btn-default btn-lg btn-block" value="加载更多...">
        </div>
    </div>
</div>


<!-- 模态框（Modal） -->
<div class="modal fade" id="reply" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content modalcenter">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="replyLabel">回复：</h4>
            </div>
            <div class="modal-body">
                <!--
                <textarea style="width:100%" rows="5" cols="" id="replycontent"></textarea>
                 -->
                <form id="replyform">
                    <div class="form-group">
                        <textarea style="width:100%" rows="5" cols="" name="replycontent" id="replycontent"></textarea>
                    </div>
                    <div class="text-right">
                        <span id="returnMessage" class="glyhicon"></span>
                        <p></p>
                        <button class="btn btn-default" data-dismiss="modal">关闭</button>
                        <input id="replyBtn" type="button" class="btn btn-primary" data-dismiss="modal" value="提交">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>