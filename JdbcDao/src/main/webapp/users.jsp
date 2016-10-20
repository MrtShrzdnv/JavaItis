<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h1>
    <c:forEach items="${requestScope.myUsers}" var="currentUser">
        <tr>
            <td><c:out value="${currentUser}" /><td>
        </tr>
    </c:forEach>
</h1>
</body>
</html>