<%--
  Created by IntelliJ IDEA.
  User: Marat_2
  Date: 01.11.2016
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<c:forEach items="${MyUsers}" var="currentUser">
    <h5>
        <tr>
            <td>
                    <c:out value="${currentUser}"/>
            <td>
        </tr>
    </h5>
</c:forEach>
</body>
</html>
