<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css">
<link rel="stylesheet" href="css/site.css">
<script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<title>慕课答疑平台</title>>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container">		
		<div class="row">
			<div class="col-sm-12 msgtitle"><h3>我的问题</h3></div>
		</div>
		<div class="row">
			<div class="col-sm-8 col-xs-8"><h4>标题</h4></div>
			<div class="col-sm-2 col-xs-4 text-center"><h4>时间</h4></div>
			<div class="col-sm-2 hidden-xs text-center"><h4>浏览 • 回复</h4></div>
		</div>
		<c:forEach var="msg" items="${allMessage}">
		<div class="row msglist" style="display: block;">
			<div class="col-sm-12">
				<div class="col-sm-8 col-xs-8 text-limit">
					<a class="title" href="user/message!msg_show.action?msgid=${msg.msgid}">${msg.msgtopic}</a>
				</div>
				<div class="col-sm-2  col-xs-4 text-center time"><fmt:formatDate value="${msg.msgtime}"
																				 pattern="yyyy-MM-dd HH:mm:ss"/></div>
				<div class="col-sm-2 hidden-xs text-center count">${msg.count.accesscount} • ${msg.count.replycount}</div>
			</div>
		</div>
		</c:forEach>

		<div class="row p">
			<div class="col-sm-12">
				<br/>
				<button id="loadmore" type="button" class="btn btn-default btn-lg btn-block"
				onclick="javascript:getMyMsg();">加载更多...</button>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>