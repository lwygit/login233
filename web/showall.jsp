<%--
  Created by IntelliJ IDEA.
  User: jyh12
  Date: 2019/8/5
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.entity.User" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>所有用户页面</title>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
</head>

<body>
<h1>${xiaoxi}</h1>
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
<%--            <td><input type="text" id="id" name="id" value="<%= user.get(i).getId() %>">--%>
<%--            </td>--%>
            <td height="30"><p><%= user.get(i).getId() %></p></td>
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
            </td>
        </tr>
            <% } %>
</table>
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
            var id = $("#id").val();
            var name = $("#name").val();
            var sex = $("#sex").val();
            var password = $("#password").val();
            var home = $("#home").val();
            var info = $("#info").val();
            // $("#form").submit();
            $.post("UpdateServlet",
                {"id": id, "name": name, "sex": sex,"password":password,"home":home,"info":info}
            );

        });
    }
</script>
</body>
</html>
