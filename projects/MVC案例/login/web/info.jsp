<%--
  Created by IntelliJ IDEA.
  User: zsquirrel
  Date: 2020/7/2
  Time: 10:12 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    欢迎您，<a href="<%=request.getContextPath()%>/user?op=logout"><%=session.getAttribute("username")%> 点我注销</a>
</body>
</html>
