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
<link rel="stylesheet" href="<%=request.getContextPath() %>/style.css">
<title>キャンセル確定画面</title>
</head>
<body>
<h1>会議室予約キャンセル</h1>
<hr>
<h2>キャンセル完了</h2>
<<<<<<< HEAD

  <form action="<%= request.getContextPath() %>/LoginServlet"method="post">
    
    予約日       <%= session.yoyaku %><br>
    会議室       <input type="text"name="room.name"value=""><br>
    予約開始時刻 <input type="time"name="reservation.start"value="hh:mm"><br>
    予約終了時刻 <input type="time"name="reservation.end"value="hh:mm"><br>
    予約者       <input type="text"name="meetingRoom.user.name"value=""><br>
=======
    <table>
    	<tr>
    		<th>予約日</td>
    		<td>${yayaku.date}</td>
    	</tr>
    	<tr>
    		<th>会議室名</td>
    		<td>${Util.htmlSpecialChars(heya.name)}</td>
    	</tr>
    	<tr>
    		<th>予約時刻</th>
    		<td>${yayaku.start} ～ ${yayaku.end}</td>
    	</tr>
    	<tr>
    		<th>予約者</th>
    		<td>${Util.htmlSpecialChars(meetingRoom.user.name)}</td>
    	</tr>
    </table>
>>>>>>> main
<hr>   
  <form action="<%= request.getContextPath() %>/menu.jsp"method="post">
    <input type="submit"value="完了">
  </form>
</body>
</html>