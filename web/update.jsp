<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
</head>
<body>
<div id="update">
    <h1><c:out value="Update user with id : ${param.id}"/> </h1>
    <form action="<c:url value="/admin/update"/>" method="post">
        <p style="margin-left:11px">Name <input style="margin-left:12px" type="text" name="name" value=${param.name}></p>
        <p>Password <input type="text" name="password" value=${param.password}></p>
        <p style="margin-left:15px" >Role <input style="margin-left:16px" type="text" name="role" value=${param.role}></p>
        <p style="margin-left:17px;" >Age<input style="margin-left:20px" type="number" name="age" value=${param.age}></p>
        <input type="hidden" name="id" value="${param.id}">
        <input type="submit" value="Update" style="margin-left: 178px">
    </form>
</div>
</body>
</html>