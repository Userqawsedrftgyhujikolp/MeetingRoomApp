<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
</head>
<body>
	<h1>会議室予約</h1>
	<h2>メニュー</h2>
	<form action="<%=request.getContextPath() %>/reserveInput.jsp" method="post">
		<input type="submit" value="会議室予約">
	</form>
	<form action="<%=request.getContextPath() %>/cancelInput.jsp" method="post">
		<input type="submit" value="予約キャンセル">
	</form>
	<form action="<%=request.getContextPath() %>/Logout" method="post">
		<input type="submit" value="ログアウト">
	</form>
</body>
</html>