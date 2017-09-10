<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css">
    <link rel="stylesheet" href="css/site.css">
    <script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <title>慕课答疑平台</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">

    <div class="row">
        <div class="col-sm-4">
            <div
                    style="overflow: auto; height: 60px; line-height: 40px; padding-top: 20px;">
                <div style="float: left">
                    <h3 style="display: inline">最新</h3>
                </div>
                <div style="float: right; vertical-align: bottom;">
                    <a href="newmsg.jsp">更多>></a>
                </div>
            </div>
            <div>
                <ul class="list-group newList">
                    <c:forEach var="msg" items="${allNewMessage}">
                        <li class="list-group-item" display="block">
                            <span class="badge">${msg.count.replycount}回复/${msg.count.accesscount}浏览</span>
                            <a class="msgtile text-limit" href="user/message!msg_show.action?msgid=${msg.msgid}">${msg.msgtopic}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>

        </div>
        <div class="col-sm-4">
            <div
                    style="overflow: auto; height: 60px; line-height: 40px; padding-top: 20px;">
                <div style="float: left">
                    <h3 style="display: inline">最热</h3>
                </div>
                <div style="float: right; vertical-align: bottom;">
                    <a href="hotmsg.jsp">更多>></a>
                </div>
            </div>
            <div>
                <ul class="list-group hotList">
                    <c:forEach var="msg" items="${allHotMessage}">
                        <li class="list-group-item" display="block">
                            <span class="badge">${msg.count.replycount}回复</span>
                            <a class="msgtile text-limit" href="user/message!msg_show.action?msgid=${msg.msgid}">${msg.msgtopic}</a>
                        </li>
                    </c:forEach>

                </ul>
            </div>
        </div>
        <div class="col-sm-4">
            <div
                    style="overflow: auto; height: 60px; line-height: 40px; padding-top: 20px;">
                <div style="float: left">
                    <h3 style="display: inline">话题</h3>
                </div>
                <div style="float: right; vertical-align: bottom;">
                    <a href="thememsg.jsp">更多>></a>
                </div>
            </div>
            <div>
                <ul class="list-group themeList">
                    <c:forEach var="msg" items="${allMessageByTheme}">
                        <li class="list-group-item" display="block">
                            <span class="badge">${msg.theme.thename}</span>
                            <a class="msgtile text-limit" href="user/message!msg_show.action?msgid=${msg.msgid}">${msg.msgtopic}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>

</body>
</html>