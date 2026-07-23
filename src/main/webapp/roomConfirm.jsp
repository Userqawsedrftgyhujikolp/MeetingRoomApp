<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>追加確認</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>
	<h1>会議室追加</h1>
	<hr>
	会議室情報を確認してください
	<table>
	<tr><th>階</th><td>${room.id }</td></tr>
	<tr><th>会議室名</th><td>${Util.htmlSpecialChars(room.name) }</td></tr>
	</table>
	${message }
	<a href="<%=request.getContextPath() %>/RoomAddConfirm">
	<input type="button" value="追加">
	</a>
</body>
</html>