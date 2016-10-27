<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List</title>
</head>
<body>
<c:forEach items="${requestScope.MyUsers}" var="currentUser">
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
