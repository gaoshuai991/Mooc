<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>慕课答疑平台</title>
</head>
<body>
<h1>对不起，程序出现错误，请与管理员联系！</h1>
<h3><a href="<%=basePath%>index.jsp">回到首页</a></h3>
<h4>${errors}</h4>
</body>
</html>
