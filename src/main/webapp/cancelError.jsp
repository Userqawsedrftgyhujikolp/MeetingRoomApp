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
<title>キャンセルエラー画面</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/style.css">
</head>
<body>
	<h1>会議室予約キャンセル</h1>
	<hr>
	<h2>キャンセルエラー</h2>
	<form action="<%=request.getContextPath()%>/LoginServlet"
		method="post">
		<input type="label"value="errorReason"><br>
		予約日 <%= session.yoyaku %><br>
		会議室 <input type="text" name="room.name" value=""><br>
		予約時刻 <input type="time" name="reservation.start","reservation.end" value="hh:mm"><br>
		予約者 <input type="text" name="meetingRoom.user.name" value=""><br>
		<hr>
		<input type="submit" value="確認">

	</form>

</body>
</html>