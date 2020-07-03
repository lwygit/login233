<%--
  Created by IntelliJ IDEA.
  User: jyh12
  Date: 2019/8/5
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.entity.User" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>My JSP 'success.jsp' starting page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div class="card">
        <div class="card-header">
            欢迎页面
            <a href="login.html">退出</a>
        </div>
        <div class="card-body">
            <h5 class="card-title">${xiaoxi}</h5>
            <p class="card-text">您已成功登陆</p>
<%--            <a href="SearchallServlet" class="btn btn-primary">查看所有用户</a>--%>
        </div>

        <table border="2" cellpadding="0" align="center">
            <tr>
                <th width="40" align="center">ID</th>
                <th>姓名</th>
                <th>性别</th>
                <th>密码</th>
                <th>家乡</th>
                <th>备注</th>
                <th width="100" height="30">操作</th>
            </tr>
        <%
            List<User> user = (List) request.getAttribute("user"); // 取request里面的对象队列
            for (int i = 0; i < user.size(); i++) {
        %>
        <form id="form" action="UpdateServlet" method="post">
            <tr id="u<%= user.get(i).getId() %>" align="center">
                <td><input type="text" id="id" name="id" value="<%= user.get(i).getId() %>">
                </td>
                <%--            <td height="30"><p id="id"><%= user.get(i).getId() %></p></td>--%>
                <td><input type="text" id="name" name="name" value="<%= user.get(i).getName() %>">
                </td>
                <td><input type="text" id="sex" name="sex" value="<%= user.get(i).getSex() %>">
                </td>
                <td><input type="text" id="password" name="password" value="<%= user.get(i).getPwd() %>">
                </td>
                <td><input type="text" id="home" name="home" value="<%= user.get(i).getHome() %>">
                </td>
                <td><input type="text" id="info" name="info" value="<%= user.get(i).getInfo() %>">
                </td>
                <td>
                    <a href="javascript:del(<%= user.get(i).getId() %>)">删除</a>
                    <input id="update" type="submit" value="更新"/></td>
            </tr>

        </form>
        <% } %>
        </table>
    </div>
</div>

<script>
    function del(id) {
        $.ajax({
            url: "DeleteServlet?id=" + id,
            dataType: "json"
        }).done(function (json) {
            $("#form").submit();
        });
        $("#u" + id).remove()
    }

</script>

<script>
    function update() {
        $("#update").click(function () {
            var id = $("#uid").val();
            var name = $("#name").val();
            var sex = $("#sex").val();
            var password = $("#password").val();
            var home = $("#home").val();
            var info = $("#info").val();
            // $("#form").submit();
            $.post("UpdateServlet",
                {"id": id, "name": name, "sex": sex, "password": password, "home": home, "info": info}
            );
        });
    }
</script>
</body>
</html>
