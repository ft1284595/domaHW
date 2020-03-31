<%--
  Created by IntelliJ IDEA.
  User: tyler
  Date: 3/29/2020
  Time: 11:45 PM
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
<form action="registerCourse" method="post">
    Course name:<input type="text" name="courseName"><br>
    Department:<input type="text" name="department">
    <input type="submit" value="submit">
    <hr>
    <table border="1">
        <thead>
            <th>course name</th>
            <th>department</th>
        </thead>
        <tbody>
            <c:forEach items="${currentUser.courses}" var="course">
               <tr>
                   <td><c:out value="${course.courseName}"></c:out></td>
                   <td><c:out value="${course.department}"></c:out></td>

               </tr>
            </c:forEach>
        </tbody>
    </table>
</form>
</body>
</html>
