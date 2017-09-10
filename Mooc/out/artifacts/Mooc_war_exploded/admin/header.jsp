<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
    <script src="jquery/jquery-2.2.4.min.js"></script>
    <script src="jquery/jquery.validate.min.js"></script>
    <script>
        function getContextPath() {
            return "${pageContext.request.contextPath}";
        }
    </script>
    <script src="js/admin/updatePwd.js"></script>
    <title>头部</title>
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#example-navbar-collapse">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="admin/index.jsp">慕课答疑平台</a>
            </div>
            <div class="collapse navbar-collapse" id="example-navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${admin == null}">
                        <li><a href="admin_login.jsp">登录</a></li>
                    </c:if>
                    <c:if test="${admin != null}">
                        <li><a>欢迎：${admin.username}</a></li>
                        <li><a href="javascript:void(0)" data-toggle="modal" data-target="#updatePwdModal"
                               id="updatePwdBtn">修改密码</a></li>
                        <li><a href="admin/msgmanager.jsp">贴子管理</a></li>
                        <li><a href="admin/usermanager.jsp">用户管理</a></li>
                        <li><a href="admin/thememanager.jsp">主题管理</a></li>
                        <li><a href="common!admin_logout.action">退出</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</nav>
<div class="jumbotron masthead">
    <div class="container">
        <h1>慕课答疑平台-后台</h1>
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="updatePwdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content modalcenter">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="replyLabel">修改密码：</h4>
            </div>
            <div class="modal-body">
                <form action="admin!updatePwd.action" method="post" id="updatePwdForm">
                    <div class="row" style="margin-top: 10px">
                        <div class="col-md-3 text-right"><strong>原密码：</strong></div>
                        <div class="col-md-8">
                            <input type="password" name="admin.password" id="admin.password">
                        </div>
                    </div>
                    <div class="row" style="margin-top: 10px">
                        <div class="col-md-3 text-right"><strong>新密码：</strong></div>
                        <div class="col-md-8">
                            <input type="password" name="newpwd" id="newpwd">
                        </div>
                    </div>
                    <div class="row" style="margin-top: 10px">
                        <div class="col-md-3 text-right"><strong>确认密码：</strong></div>
                        <div class="col-md-8">
                            <input type="password" name="confpwd" id="confpwd">
                        </div>
                    </div>
                    <div class="row" style="margin-top: 20px;margin-left: 150px">
                        <input type="hidden" name="admin.aid" value="${admin.aid}">
                        <input type="hidden" name="admin.username" value="${admin.username}">
                        <input type="submit" id="updatePwd" class="btn btn-primary" value="修改">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>