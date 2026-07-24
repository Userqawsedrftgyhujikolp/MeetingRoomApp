<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="bean.MeetingRoom"%>
<%@ page import="bean.ReservationBean"%>
<%@ page import="bean.RoomBean"%>
<%@ page import="bean.UserBean"%>
<%@ page import="bean.Util"%>
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
	<table class="margin">
	<tr><th>階</th><td>${room.id }</td></tr>
	<tr><th>会議室名</th><td>${Util.htmlSpecialChars(room.name) }</td></tr>
	</table>
	${message }
	<hr>
	<form action="<%=request.getContextPath() %>/RoomAddConfirm" method="post">
	<input type="submit" value="追加">
	</form>
</body>
</html>