<%@ page import="com.servlet.entity.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: tyler
  Date: 3/18/2020
  Time: 6:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table border="3">
    <tbody>
    <tr>
        <th>ID</th>
        <th>title</th>
        <th>author</th>
        <th>publishedDate</th>
    </tr>
<%--    <c:out value="${bookList}"></c:out>--%>
    <hr>
    <c:forEach items="${requestScope.bookList}" var="book">
        <tr>
            <td><c:out value="${book.id}"></c:out></td>
            <td><c:out value="${book.title}"></c:out></td>
            <td><c:out value="${book.author}"></c:out></td>
            <td><c:out value="${book.publishedDate}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
