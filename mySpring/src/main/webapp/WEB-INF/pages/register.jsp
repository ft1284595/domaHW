<%--
  Created by IntelliJ IDEA.
  User: tyler
  Date: 3/29/2020
  Time: 5:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Register</title>
    <base href="<%=basePath%>">
</head>
<body>
    <form action="registerUser" method="post">
        First Name:<input type="text" name="firstName"><br/>
        Last Name:<input type="text" name="lastName"><br/>
        Email: <input type="text" name="email"><br>
        Password:<input type="text" name="password"><br>
        <input type="reset" value="reset"><input type="submit" value="submit">
    </form>
</body>
</html>
