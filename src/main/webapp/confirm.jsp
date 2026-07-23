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
<title>予約確認</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>
	<h1>あなたの予約</h1>
	<%
	MeetingRoom mr = (MeetingRoom) session.getAttribute("meetingRoom");
	ReservationBean[] reservs = (ReservationBean[]) request.getAttribute("reservs");
	if (reservs != null) {
		for (ReservationBean reserv : reservs) {
			String roomId = reserv.getRoomId();
	%>
	<hr>
	<table class="margin background">
		<tr>
			<th>予約番号</th>
			<td><%=reserv.getId()%></td>
		</tr>
		<tr>
			<th>会議室</th>
			<td><%=Util.htmlSpecialChars(mr.getRoom(roomId).getName())%></td>
		</tr>
		<tr>
			<th>利用日</th>
			<td><%=reserv.getDate()%></td>
		</tr>
		<tr>
			<th>利用時間</th>
			<td><%=reserv.getStart()%>～<%=reserv.getEnd()%></td>
		</tr>
	</table>
	<%
	}
	}
	%>
	<hr>
	<a href="<%=request.getContextPath()%>/menu.jsp"> <input
		type="button" value="戻る">
	</a>
</body>
</html>