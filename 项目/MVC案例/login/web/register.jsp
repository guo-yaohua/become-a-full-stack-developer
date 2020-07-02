<%--
  Created by IntelliJ IDEA.
  User: zsquirrel
  Date: 2020/6/30
  Time: 5:06 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/user" method="post">
        <input type="hidden" name="op" value="register">
        用户名：<input type="text" name="username"><br>
        密码:<input type="password" name="password"><br>
        确认密码：<input type="password" name="confirmPassword"><br>
        <input type="submit">
    </form>
</body>
</html>
