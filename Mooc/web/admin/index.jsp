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
<script src="js/admin/index.js" type="text/javascript"></script>
<title>慕课答疑平台</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">		
		<div class="row">
			<div class="col-sm-offset-2 col-sm-8 text-center">
				<h3>数据统计</h3>
				<table class="table table-striped">
				  <caption>发帖统计</caption>
				  <thead>
				    <tr>
				      <th>项目</th>
				      <th>数量</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <td>今日发帖</td>
				      <td id="msgday">10</td>
				    </tr>
				    <tr>
				      <td>本周发帖</td>
				      <td id="msgweek">100</td>
				    </tr>
				    <tr>
				      <td>本月发帖</td>
				      <td id="msgmonth">1000</td>
				    </tr>
				  </tbody>
				</table>
				<table class="table table-striped">
				  <caption>回复统计</caption>
				  <thead>
				    <tr>
				      <th>项目</th>
				      <th>数量</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <td>今日回复</td>
				      <td id="replyday">10</td>
				    </tr>
				    <tr>
				      <td>本周回复</td>
				      <td id="replyweek">100</td>
				    </tr>
				    <tr>
				      <td>本月回复</td>
				      <td id="replymonth">1000</td>
				    </tr>
				  </tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>