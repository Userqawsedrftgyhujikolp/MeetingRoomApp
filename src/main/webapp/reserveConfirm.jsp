<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.MeetingRoom" %>
<%@ page import="bean.ReservationBean" %>
<%@ page import="bean.RoomBean" %>
<%@ page import="bean.UserBean" %>
<%@ page import="bean.Util"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>予約確認画面</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/style.css">
</head>
<<<<<<< HEAD
<!-- <body>
    <h1>会議室予約</h1>
    <hr>
    <h2>予約確認</h2>

    <form action="<%= request.getContextPath() %>/LoginServlet" method="post">

        <div class="marginbottom">
            <span class="display">予約日</span>
            <span>${reservation.date}</span>
            <span class="display">会議室</span>
            <span>${room.name}</span>
            <span class="display">予約時刻</span>
            <span>${reservation.start}～${reservation.end}</span>
            <span class="display">予約者</span>
            <span>${meetingRoom.user.name}</span>
=======
<body> -->
    <h1>会議室予約</h1>
    <hr>
    <h2>予約確認</h2>
    <form action="<%= request.getContextPath() %>/LoginServlet" method="post">
        
        <div style="margin-bottom: 10px;">
            <span style="display: inline-block; width: 100px;">予約日</span>
            <span>${reservation.date}</span>
        </div>
        <div style="margin-bottom: 10px;">
            <span style="display: inline-block; width: 100px;">会議室</span>
            <span> ${Util.htmlSpecialChars(meetingRoom.getRoom(reservation.roomId).name) }</span>
        </div>
        <div style="margin-bottom: 10px;">
            <span style="display: inline-block; width: 100px;">予約時刻</span>
            <span>${reservation.start}～${reservation.end}</span>
        </div>
        <div style="margin-bottom: 10px;">
            <span style="display: inline-block; width: 100px;">予約者</span>
            <span>${Util.htmlSpecialChars(meetingRoom.user.name) }</span>
<!-- >>>>>>> main
        </div>
        <hr>   
<<<<<<< HEAD
        
        <input type="submit" name="action" value="戻る">
        <input type="submit" name="action" value="決定">
    </form>

=======-->
<br>
        <form action="reserveInput.jsp"><input type="submit"value="戻る"></form>
        <form action="<%=request.getContextPath()%>/ReservationServlet" method="post">
        <input type="submit"value="決定">
        </form>
>>>>>>> main
</body>
</html>