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
<title>予約エラー画面</title>
</head>
<body>
<h1>会議室予約</h1>
<hr>
<h2>予約エラー</h2>
	${error }
  <form action="<%= request.getContextPath() %>/LoginServlet"method="post">
    エラーメッセージ<br>
    予約日       <input type="tate"name="reserve.date"value="yyyy-mm-dd"><br>
    会議室名     <input type="text"name="room.name"value=""><br>
    予約開始時刻 <input type="time"name="reservation.start"value="hh:mm"><br>
    予約終了時刻 <input type="time"name="reservation.end"value="hh:mm"><br>
    予約者       <input type="text"name="meetingRoom.user.name"value=""><br>
<hr>   
    <input type="submit"value="確認">
    
  </form>

</body>
</html>