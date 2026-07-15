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
    <table>
    	<tr>
    		<th>予約日</td>
    		<td>${yayaku.date}></td>
    	</tr>
    	<tr>
    		<th>会議室名</td>
    		<td>${Util.htmlSpecialChars(meetingRoom.getRoom(yayaku.roomId).name)}</td>
    	</tr>
    	<tr>
    		<th>予約時刻</th>
    		<td>${reservation.start} ～ ${reservation.end}</td>
    	</tr>
    	<tr>
    		<th>予約者</th>
    		<td>${Util.htmlSpecialChars(meetingRoom.user.name)}</td>
    	</tr>
    </table>
		<hr>
	<form action="<%=request.getContextPath()%>/menu.jsp" method="post">
		<input type="submit" value="確認">
	</form>
</body>
</html>