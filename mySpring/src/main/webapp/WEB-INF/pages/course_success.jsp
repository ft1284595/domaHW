<%--
  Created by IntelliJ IDEA.
  User: tyler
  Date: 3/30/2020
  Time: 12:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Title</title>
    <base href="<%=basePath%>">
</head>
<body>
this is course_success.jsp
Course: <c:out value="${courseName}"></c:out> registration successful for <c:out value="${currentUser.firstName}"/> &nbsp; <c:out value="${currentUser.lastName}"></c:out>

<a href="<%=basePath%>/logout">Logout</a>
</body>
</html>
