<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Users</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
        #add {
            position: absolute;
            left: 550px;
            top: -10px  ;
        }

    </style>
</head>
<body>
<h1>Users:</h1>
<table >
    <tr>
        <th width="80">Id</th>
        <th width="120">Name</th>
        <th width="100">Age</th>
        <th width="55"></th>
        <th width="50"></th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td align="center">${user.getId()}</td>
            <td align="center">${user.getName()}</td>
            <td align="center">${user.getAge()}</td>
            <td>
                <form action="<c:url value="/update"/>" method="get">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="hidden" name="name" value="${user.getName()}">
                    <input type="hidden" name="age" value="${user.getAge()}">
                    <input type="submit" value="Update">
                </form>
            </td>
            <td>
                <form action="<c:url value="/delete"/>" method="post">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>

    </c:forEach>
</table>
<div id="add">
    <h1>Add User</h1>
    <form action="<c:url value="/add"/>" method="get">
        <input type="submit" value="Add ">
    </form>
</div>
</body>
</html>

