<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<html>
<head>
    <title>慕课答疑平台</title>
</head>
<body>
<script type="text/javascript">
    window.alert("${msg}");
    window.location = "<%=basePath%>${url}";
</script>
</body>
</html>
