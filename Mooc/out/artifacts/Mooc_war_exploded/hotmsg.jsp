<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    <link rel="stylesheet" href="css/site.css">
    <script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/user/hotmsg.js" type="text/javascript"></script>
    <title>慕课答疑平台</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div id="msgDiv" class="row">

    </div>
    <div class="row">
        <div class="col-sm-12">
            <br/>
            <input id="loadmore" type="button" class="btn btn-default btn-lg btn-block" value="加载更多...">
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>