<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.MeetingRoom"%>
<%@ page import="bean.UserBean"%>
<% MeetingRoom mr = (MeetingRoom) session.getAttribute("meetingRoom");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>
<h1>新規登録</h1>
	<form action="<%=request.getContextPath()%>/Login" method="post">
		<label>名前：</label><input type="text" name="name"><br>
		<label>パスワード：</label><input type="password" name="password"><br>
		<label>住所：</label><input type="text" name="address"><br>
		<input type="submit" value="登録">
</body>
</html>