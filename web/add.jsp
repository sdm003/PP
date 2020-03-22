<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head></head>
<title>Add User</title>
<div id="add">
    <h1 style="margin-left: 45px">Add User</h1>
    <form action="<c:url value="/admin/add"/>" method="post">
        <p style="margin-left:11px">Name <input style="margin-left:12px" type="text" name="name"></p>
        <p>Password <input type="text" name="password"></p>
        <p style="margin-left:15px">Role <input style="margin-left:16px" type="text" name="role"></p>
        <p style="margin-left:17px;">Age<input style="margin-left:20px" type="number" name="age"></p>
        <input type="submit" value="Add" style="margin-left: 197px">
    </form>
</div>
</body>
</html>