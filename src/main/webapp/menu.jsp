<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.MeetingRoom"%>
<%@ page import="bean.ReservationBean"%>
<%@ page import="bean.RoomBean"%>
<%@ page import="bean.UserBean"%>
<%@ page import="bean.Util"%>
<%
MeetingRoom mr = (MeetingRoom)session.getAttribute("meetingRoom");
String[] keys = { "reservation", "yoyaku", "heya", "yayaku" };
for (String key : keys) {
	session.removeAttribute(key);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>
	<h1>会議室予約</h1>
	<hr>
	<h2>メニュー</h2>
	<form action="<%=request.getContextPath()%>/reserveInput.jsp"
		method="post">
		<input type="submit" value="会議室予約">
	</form>
	<form action="<%=request.getContextPath()%>/cancelInput.jsp"
		method="post">
		<input type="submit" value="予約キャンセル">
	</form>
	<form action="<%=request.getContextPath()%>/Confirm"
		method="post">
		<input type="submit" value="予約の確認">
	</form>
	<%if(mr.getUser().isAdmin()){//管理者用メニュー%>
	<form action="<%=request.getContextPath()%>/csvSelectDate.jsp"
		method="post">
		<input type="submit" value="予約のCSV出力">
	</form>
	<%} %>
	
	<form action="<%=request.getContextPath()%>/Logout" method="post">
		<input type="submit" value="ログアウト">
	</form>
</body>
</html>