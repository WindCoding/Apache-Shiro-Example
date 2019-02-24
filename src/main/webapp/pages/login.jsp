<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h1> 欢迎登录 </h1>
<form action="/loginUser" method="post">
    <input type="text" name="username" value="admin"> <br>
    <input type="password" name="password" value="123"> <br>
    <input type="submit" value="提交">
</form>
</body>
</html>