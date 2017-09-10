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
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css">
    <link rel="stylesheet" href="css/site.css">
    <script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/admin/thememanager.js"></script>
    <script src="js/admin/pagetool.js"></script>
    <script src=""></script>
    <title>慕课答疑平台</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-sm-offset-2 col-sm-8 msgtitle">
            <h3>
                <span class="title">主题管理</span>
            </h3>
            <div class="replybtn">
                <button type="button" class="btn btn-warning" data-toggle="modal"
                        data-target="#add">添加
                </button>
                <button type="button" class="btn btn-success" data-toggle="modal"
                        data-target="#search">搜索
                </button>

            </div>
        </div>
    </div>
    <div class="row" style="font-size: 17px;font-weight: bold">
        <div class="col-sm-offset-2 col-sm-8 col-xs-12">
            <div class="col-sm-2 col-xs-2">主题ID</div>
            <div class="col-sm-6 col-xs-5 title">主题名称</div>
            <div class="col-sm-2 col-xs-2 title">贴子数量</div>
            <div class="col-sm-2 col-xs-3 ">
                操作
            </div>
        </div>
    </div>
    <div class="msgListDiv">
    </div>
    <%--<div class="row">--%>
        <%--<div class="col-sm-offset-2 col-sm-8 col-xs-12 msglist">--%>
            <%--<div class="col-sm-2 col-xs-2">2</div>--%>
            <%--<div class="col-sm-8 col-xs-7 title">Java</div>--%>
            <%--<div class="col-sm-2 col-xs-3 ">--%>
                <%--<button class="btn btn-danger">删除</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>


    <div class="row" style="text-align: center">
        <jsp:include page="pagetool.jsp"/>
        </ul>
    </div>

</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="search" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content modalcenter">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">搜索</h4>
            </div>
            <div class="modal-body">
                <form role="form">
                    <div class="form-group">
                        <label for="key">主题名：</label>
                        <input type="text" class="form-control" id="key" placeholder="请输入搜索关键字...">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="search()">搜索</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content modalcenter">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加主题</h4>
            </div>
            <div class="modal-body">
                <form role="form">
                    <div class="form-group">
                        <label for="thename">主题名：</label>
                        <input type="text" class="form-control" id="thename" placeholder="">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="add()">添加</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>