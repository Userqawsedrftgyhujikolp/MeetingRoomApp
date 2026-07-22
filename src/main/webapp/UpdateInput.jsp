<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="bean.MeetingRoom"%>
    <%@ page import="bean.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウントの編集</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>
<h1>アカウントの編集</h1>
	<form action="<%=request.getContextPath()%>/UpdateServlet" method="post">
		<label>名前：</label><input type="text" name="name" value= ${user.name }><br>
		<label>パスワード：</label><input type="password" name="password" value= ${user.password }><br>
		<label>住所：</label><input type="text" name="address" value= ${user.address }><br>
		<input type="submit" value="更新">
	</form>
</body>
</html>