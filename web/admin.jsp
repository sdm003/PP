<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Users</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
        #add {
            position: absolute;
            left: 750px;
            top: -10px  ;
        }

        #delete {
            position: absolute;
            left: 1000px;
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
        <th width="120">Password</th>
        <th width="100">Role</th>
        <th width="100">Age</th>
        <th width="55"></th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td align="center">${user.getId()}</td>
            <td align="center">${user.getName()}</td>
            <td align="center">${user.getPassword()}</td>
            <td align="center">${user.getRole()}</td>
            <td align="center">${user.getAge()}</td>
            <td>
                <form action="<c:url value="/admin/update"/>" method="get">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <input type="hidden" name="name" value="${user.getName()}">
                    <input type="hidden" name="age" value="${user.getAge()}">
                    <input type="hidden" name="role" value="${user.getRole()}">
                    <input type="hidden" name="password" value="${user.getPassword()}">
                    <input type="submit" value="Update">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<div id="add">
    <h1 style="margin-top: 20px">Add User</h1>
    <form action="<c:url value="/admin/add"/>" method="get">
        <input type="submit" value="Add">
    </form>
</div>

<div id="delete">
    <h1 style="margin-top: 20px">Delete User</h1>
    <form action="<c:url value="/admin/delete"/>" method="get">
        <input type="submit" value="DELETE">
    </form>
</div>

<form  style="margin-top: 20px" method="get" action="/login">
    <input type="submit" value="Logout"/>
</form>
</body>
</html>