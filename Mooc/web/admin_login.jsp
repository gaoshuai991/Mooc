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
<script src="bootstrapvalidator/js/bootstrapValidator.js" type="text/javascript"></script>
<script src="js/admin/login.js" type="text/javascript"></script>
<title>慕课答疑平台</title>
</head>
<body>
	<jsp:include page="admin/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-offset-3 col-sm-6 text-center">
				<h3>管理员登录</h3>
			</div>
		</div>
		<form class="form-horizontal col-sm-offset-3" id="loginform" action="common!admin_login.action" method="post">
			<div class="form-group">
				<label for="admin.username" class="col-sm-2 control-label">账号：</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="admin.username" id="admin.username" placeholder="请输入账号">
				</div>
			</div>
			<div class="form-group">
				<label for="admin.password" class="col-sm-2 control-label">密码：</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" name="admin.password" id="admin.password" placeholder="请输入密码">
				</div>
			</div>
			<div class="form-group has-error">
				<div class="col-sm-offset-2 col-sm-4 col-xs-6 ">
					<span class="help-block">${error}</span>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-4 col-xs-12">
					<input type="submit" class="btn btn-success btn-block" value="登录">
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="admin/footer.jsp" />
</body>
</html>