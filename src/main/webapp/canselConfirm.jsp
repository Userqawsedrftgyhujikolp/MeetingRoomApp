<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page cancelPage="cancelInput.jsp"%>
<%@ page cancelPage="cancel"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>キャンセル確認画面</title>
</head>
<body>
  <h1>会議室予約キャンセル</h1>
<hr>
  <h2>キャンセル確認</h2>
  <form action="<%= request.getContextPath() %>/LoginServlet"method="post">
    
    予約日       <input type="tate"name="reserveDate"value="yyyy-mm-dd"><br>
    会議室       <input type="text"name="room.name"value=""><br>
    予約開始時刻 <input type="time"name="reservation.start"value="hh:mm"><br>
    予約終了時刻 <input type="time"name="reservation.end"value="hh:mm"><br>
    予約者       <input type="text"name="meetingRoom.user.name"value=""><br>
<hr>   
    <input type="submit"value="戻る">
    <input type="submit"value="決定">
  </form>
</body>
</html>