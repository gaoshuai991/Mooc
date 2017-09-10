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
<link rel="stylesheet" href="bootstrap-datetimepicker/css/bootstrap-datetimepicker.css">
<script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<script src="bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script src="bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
<!-- 表单验证 -->
<script src="bootstrapvalidator/js/bootstrapValidator.js" type="text/javascript"></script>
<script src="js/user/register.js" type="text/javascript"></script>
<title>慕课答疑平台</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">		
		<div class="row">
			<div class="col-sm-offset-3 col-sm-6 text-center">
				<h3>用户注册</h3>
			</div>
		</div>
		<form class="form-horizontal col-sm-offset-3" id="registerform" method="post" action="common!user_register.action">
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
			<div class="form-group">
				<label for="confpwd" class="col-sm-2 control-label">确认密码：</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" name="confpwd" id="confpwd" placeholder="请确认密码">
				</div>
			</div>
			<div class="form-group">
				<label for="user.realname" class="col-sm-2 control-label">真实姓名：</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="user.realname" id="user.realname" placeholder="请输入真实姓名">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">性别：</label>
				<div class="col-sm-4">
					<label class="radio-inline">
				        <input type="radio" name="user.sex" value="男" checked> 男
					</label>
				    <label class="radio-inline">
				        <input type="radio" name="user.sex" value="女"> 女
				    </label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">爱好：</label>
				<div class="col-sm-4">
					<label class="checkbox-inline" style="margin-left: 10px">
				        <input type="checkbox" name="hobbys" value="游戏">游戏
				    </label>
				    <label class="checkbox-inline">
				        <input type="checkbox" name="hobbys" value="睡觉">睡觉
				    </label>
				    <label class="checkbox-inline">
				        <input type="checkbox" name="hobbys" value="篮球">篮球
				    </label>
				    <label class="checkbox-inline">
				        <input type="checkbox" name="hobbys" value="足球">足球
				    </label>
				    <label class="checkbox-inline">
				        <input type="checkbox" name="hobbys" value="电影">电影
				    </label>
				    <label class="checkbox-inline">
				        <input type="checkbox" name="hobbys" value="吟诗">吟诗
				    </label>
				</div>
			</div>
			
			<div class="form-group">
				<label for="user.birthday" class="col-sm-2 control-label">生日：</label>
				<div class="col-sm-4">
					<div class="input-group date form_datetime" data-date-format="dd-MM-yyyy" data-link-field="dtp_input1">
	                    <input class="form-control" size="16" type="text" name="user.birthday" id="user.birthday" value="2000-01-01" readonly>
	                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
	                </div>
                </div>
			</div>
			<div class="form-group">
				<label for="user.city" class="col-sm-2 control-label">城市：</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="user.city" id="user.city" placeholder="请输入所在城市">
				</div>
			</div>
			<div class="form-group">
				<label for="user.email" class="col-sm-2 control-label">邮箱：</label>
				<div class="col-sm-4">
					<input type="email" class="form-control" name="user.email" id="user.email" placeholder="请输入邮箱">
				</div>
			</div>
			<div class="form-group">
				<label for="user.qq" class="col-sm-2 control-label">QQ：</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="user.qq" id="user.qq" placeholder="请输入QQ">
				</div>
			</div>
			<div class="form-group has-error">
				<div class="col-sm-offset-2 col-sm-4 col-xs-6 ">
					<span class="text-warning"></span>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-4 col-xs-12">
					<input type="submit" class="btn btn-success btn-block" value="注册">
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>