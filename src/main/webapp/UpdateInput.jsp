<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウントの変更</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>
<h1>アカウントの変更</h1>
	<form action="<%=request.getContextPath()%>/UpdateServlet" method="post">
		<label>名前：</label><input type="text" name="name" value= ${meetingRoom.user.name }><br>
		<label>パスワード：</label><input type="password" name="password" value= ${meetingRoom.user.password }><br>
		<label>住所：</label><input type="text" name="address" value= ${meetingRoom.user.address }><br>
		<input type="submit" value="更新">
	</form>
	<form action="<%=request.getContextPath()%>/menu.jsp" method="post">
		<input type="submit" value="戻る">
	</form>
</body>
</html>