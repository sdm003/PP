<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head></head>
<title>Add User</title>
<div id="Add">
    <h1><c:out value="Add user new User"/> </h1>
    <form action="<c:url value="/add"/>" method="post">
        <p>Name <input type="text" name="name" ></p>
        <p style="margin-left:7px;" id="age">Age<input style="margin-left:8px" type="number" name="age"></p>
        <input type="submit" value="Add" style="margin-left: 175px">
    </form>
</div>
</body>
</html>