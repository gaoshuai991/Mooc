<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" href="bootstrapvalidator/css/bootstrapValidator.css">
<link rel="stylesheet" href="css/site.css">
<script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 表单验证 -->
<script src="bootstrapvalidator/js/bootstrapValidator.js" type="text/javascript"></script>
<script src="js/user/addmsg.js" type="text/javascript"></script>
<title>慕课答疑平台</title>
<script type="text/javascript">
</script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container">		
		<div class="row">
			<div class="col-sm-12 text-center">
				<h3>我要提问</h3>
			</div>
		</div>
		<form class="form-horizontal col-sm-offset-2" id="addmsgform" method="post" action="user!insertMsg.action">
			<div class="form-group">
				<label for="message.msgtopic" class="col-sm-2 control-label">标题：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="message.msgtopic" id="message.msgtopic" placeholder="请输入标题">
				</div>
			</div>
			<div class="form-group">
				<label for="message.theme.theid" class="col-sm-2 control-label">主题：</label>
				<div class="col-sm-6">
					<select class="form-control" name="message.theme.theid" id="message.theme.theid">
						<c:forEach var="theme" items="${allTheme}">
                            <option value="${theme.theid}">${theme.thename}</option>
                        </c:forEach>
				    </select>
				</div>
			</div>
			<div class="form-group">
				<label for="message.msgcontents" class="col-sm-2 control-label">内容：</label>
				<div class="col-sm-6">
					<textarea class="form-control" rows="5" name="message.msgcontents" id="message.msgcontents"></textarea>
				</div>
			</div>
			<div class="form-group has-error">
				<div class="col-sm-offset-2 col-sm-4 col-xs-6 ">
					<span class="text-warning"></span>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-3 col-xs-12">
					<input type="submit" class="btn btn-success" value="提问">
					<button type="reset" class="btn btn-default">重置</button>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>