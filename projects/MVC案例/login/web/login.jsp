<%--
  Created by IntelliJ IDEA.
  User: zsquirrel
  Date: 2020/7/1
  Time: 10:06 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/user" method="post">
    <input type="hidden" name="op" value="login">
    用户名：<input type="text" name="username"><br>
    密码:<input type="password" name="password"><br>
    <input type="submit">
</form>

</body>
</html>
