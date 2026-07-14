<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>
	<h1>会議室予約</h1>
	<hr>
	<h2>ログイン</h2>
	<form action="<%=request.getContextPath()%>/Login" method="post">
			<label>利用者ＩＤ：</label><input type="text" name="userId"><br>
			<label>パスワード：</label><input type="password" name="userPw"><br>
			<input type="submit" value="ログイン">
	</form>
</body>
</html>