<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="bean.MeetingRoom"%>
<%
MeetingRoom mr = (MeetingRoom) session.getAttribute("meetingRoom");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約入力画面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>
	<h1>会議室予約</h1>
	<br>
	<hr>
	<h2>利用日</h2>
	<input type="date" id="date" value="<%=mr.getDate()%>" />
	<h2>
		予約可能時間(<%=mr.getUser().getName()%>)
	</h2>
	<%
for()
	%>
</body>
</html>