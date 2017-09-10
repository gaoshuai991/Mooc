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
<title>慕课答疑平台</title>
<script src="js/user/updatePwd.js" type="text/javascript">
</script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container">		
		<div class="row">
			<div class="col-sm-offset-3 col-sm-6 text-center">
				<h3>修改密码</h3>
			</div>
		</div>
		<form class="form-horizontal col-sm-offset-3" id="modifyform" method="post" action="user!updatePwd.action">
			
			<div class="form-group">
				<label for="oldpwd" class="col-sm-2 control-label">旧密码：</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" name="oldpwd" id="oldpwd" placeholder="请输入旧密码">
				</div>
			</div>
			<div class="form-group">
				<label for="newpwd" class="col-sm-2 control-label">新密码：</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" name="newpwd" id="newpwd" placeholder="请输入新密码">
				</div>
			</div>
			<div class="form-group">
				<label for="confpwd" class="col-sm-2 control-label">确认密码：</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" name="confpwd" id="confpwd" placeholder="请确认新密码">
				</div>
			</div>
			<div class="form-group has-error">
				<div class="col-sm-offset-2 col-sm-4 col-xs-6 ">
					<span class="text-warning"></span>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-4 col-xs-12">
					<input type="hidden" name="user.userid" value="${user.userid}">
					<input type="hidden" name="user.username" value="${user.username}">
					<input type="submit" class="btn btn-success btn-block" value="提交">
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>