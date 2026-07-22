<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>
<h1>新規登録</h1>
	<form action="<%=request.getContextPath()%>/AddServlet" method="post">
		<label>名前：</label><input type="text" name="name" maxlength="10" required><br>
		<label>パスワード：</label><input type="password" name="password" minlength="6" maxlength="10" required><br>
		<label>住所：</label><input type="text" name="address" maxlength="30" required><br>
		<input type="submit" value="登録">
		</form>
</body>
</html>