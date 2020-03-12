<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head></head>
<title>Update User</title>
<div id="update">
    <h1><c:out value="Update user with id : ${param.id}"/> </h1>
    <form action="<c:url value="/update"/>" method="post">
        <p>Name <input type="text" name="name" value="${param.name}"></p>
        <p style="margin-left:7px;" id="age">Age<input style="margin-left:8px" type="number" name="age" value="${param.age}"></p>
        <input type="hidden" name="id" value="${param.id}">
        <input type="submit" value="Update" style="margin-left: 175px">
    </form>
</div>
</body>
</html>
