<%--
  Created by IntelliJ IDEA.
  User: jyh12
  Date: 2019/8/5
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>My JSP 'success.jsp' starting page</title>
</head>
<body>
<div class="container">
    <div class="card">
        <div class="card-header">
            欢迎页面
        </div>
        <div class="card-body">
            <h5 class="card-title">${xiaoxi}</h5>
            <p class="card-text">您已成功登陆</p>
            <a href="SearchallServlet" class="btn btn-primary">查看所有用户</a>
        </div>
    </div>
</div>
</body>
</html>
