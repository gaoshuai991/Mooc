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
<script src="js/user/login.js" type="text/javascript"></script>
<title>慕课答疑平台</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-offset-3 col-sm-6 text-center">
				<h3>用户登录</h3>
			</div>
		</div>
		<form class="form-horizontal col-sm-offset-3" id="loginform" method="post" action="common!user_login.action">
			<div class="form-group">
				<label for="user.username" class="col-sm-2 control-label">账号：</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="user.username" id="user.username" placeholder="请输入账号">
				</div>
			</div>
			<div class="form-group">
				<label for="user.password" class="col-sm-2 control-label">密码：</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" name="user.password" id="user.password" placeholder="请输入密码">
				</div>
			</div>
			<div class="form-group has-error">
				<div class="col-sm-offset-2 col-sm-4 col-xs-6 ">
					<span class="text-warning"></span>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-2 col-xs-6">
					<input type="submit" class="btn btn-success btn-block" value="登录">
				</div>
				<div class="col-sm-2  col-xs-6">
					<a class="btn btn-warning btn-block" href="register.jsp">注册</a>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>