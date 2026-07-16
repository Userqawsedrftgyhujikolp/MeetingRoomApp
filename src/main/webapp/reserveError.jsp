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
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
<title>予約エラー画面</title>
</head>
<body>
<h1>会議室予約</h1>
<hr>
<h2>予約エラー</h2>
	
    ${error }<br>
    予約日       ${reservation.date }<br>
    会議室名     ${Util.htmlSpecialChars(meetingRoom.getRoom(reservation.roomId).name) }<br>
    予約時刻     ${reservation.start } ～ ${reservation.end }<br>
    予約者       ${Util.htmlSpecialChars(meetingRoom.user.name) }<br>
<hr>   
  <form action="<%= request.getContextPath() %>/menu.jsp"method="post">
    <input type="submit"value="確認">
  </form>

</body>
</html>