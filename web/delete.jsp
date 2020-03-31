<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head></head>
<title>Delete User</title>
<div id="delete">
    <h1 style="margin-left: 45px">Delete User</h1>
    <form action="<c:url value="/admin/delete"/>" method="post">
        <p style="margin-left:11px">Id<input style="margin-left:12px" type="number" name="id"></p>
        <input type="submit" value="delete" style="margin-left: 197px">
    </form>
</div>
</body>
</html>
