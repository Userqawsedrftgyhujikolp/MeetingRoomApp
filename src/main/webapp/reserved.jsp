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
<title>予約確定画面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<h1>会議室予約</h1>
<hr>
<h2>予約完了</h2>

	<table class="reserve-table margin">
		<tr>
			<td class="display">予約ＩＤ</td>
			<td>${reservation.id}</td>
		</tr>
		<tr>
			<td class="display">予約日</td>
			<td>${reservation.date}</td>
		</tr>
		<tr>
			<td class="display">会議室</td>
			<td>${Util.htmlSpecialChars(meetingRoom.getRoom(reservation.roomId).name)}</td>
		</tr>
		<tr>
			<td class="display">予約時刻</td>
			<td>${reservation.start}～${reservation.end}</td>
		</tr>
		<tr>
			<td class="display">予約者</td>
			<td>${Util.htmlSpecialChars(meetingRoom.user.name)}</td>
		</tr>
	</table>

	<hr>
 	<form action="<%= request.getContextPath() %>/menu.jsp"method="post">
    <input type="submit"value="完了">

</form>
<body>

</body>
</html>
