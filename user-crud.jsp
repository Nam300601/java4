<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>User CRUD</title>
    <style>
        /* CSS tham khảo thêm để giao diện giống hình */
        body { font-family: Arial, sans-serif; padding: 20px; }
        .container { background-color: #f2f2f2; padding: 20px; border: 1px solid #ccc; width: 500px; }
        input[type="text"], input[type="password"] { width: 90%; padding: 5px; margin: 5px 0; border: 1px solid #f0ad4e; }
        table { border-collapse: collapse; width: 100%; margin-top: 15px; }
        th, td { border: 1px solid #fff; padding: 8px; text-align: left; }
        th { background-color: #5c7cb4; color: white; }
        td { background-color: #d1d9e9; }
        button { padding: 5px 10px; margin-right: 5px; cursor: pointer; }
    </style>
</head>
<body>
    <div class="container">
        <i>${message}</i>
        <c:url var="url" value="/user/crud"/>
        
        <form method="post">
            <label>Id:</label><br>
            <input name="id" value="${user.id}"><br>
            
            <label>Password:</label><br>
            <input name="password" type="password" value="${user.password}"><br>
            
            <label>Fullname:</label><br>
            <input name="fullname" value="${user.fullname}"><br>
            
            <label>Email Address:</label><br>
            <input name="email" value="${user.email}"><br>
            
            <label>Role:</label><br>
            <input name="admin" type="radio" value="true" ${user.admin ? 'checked' : ''}> Admin
            <input name="admin" type="radio" value="false" ${!user.admin ? 'checked' : ''}> User
            <hr>
            
            <button formaction="${url}/create">Create</button>
            <button formaction="${url}/update">Update</button>
            <button formaction="${url}/delete">Delete</button>
            <button formaction="${url}/reset">Reset</button>
        </form>
        
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Password</th>
                    <th>Fullname</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="u" items="${users}">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.password}</td>
                        <td>${u.fullname}</td>
                        <td>${u.email}</td>
                        <td>${u.admin ? 'Admin' : 'User'}</td>
                        <td><a href="${url}/edit/${u.id}">Edit</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>