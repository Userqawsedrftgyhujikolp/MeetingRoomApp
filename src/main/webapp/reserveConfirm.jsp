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
<body> <!-- ★修正：bodyの開始タグをここ（見出しの上）に移動 -->
    <h1>会議室予約</h1>
    <hr>
    <h2>予約確認</h2>
    <!-- ★修正：actionの送信先を、状況に応じて適切なURLに変更してください（詳細は解説にて） -->
    <form action="<%= request.getContextPath() %>/LoginServlet" method="post">
        
        <!-- ★修正：tdタグはtableタグの中でしか使えないため、divやspanなどで綺麗に並べます -->
        <div style="margin-bottom: 10px;">
            <span style="display: inline-block; width: 100px;">予約日</span>
            <span>${reservation.date}</span>
        </div>
        <div style="margin-bottom: 10px;">
            <span style="display: inline-block; width: 100px;">会議室</span>
            <span>${room.name}</span>
        </div>
        <div style="margin-bottom: 10px;">
            <span style="display: inline-block; width: 100px;">予約時刻</span>
            <span>${reservation.start}～${reservation.end}</span>
        </div>
        <div style="margin-bottom: 10px;">
            <span style="display: inline-block; width: 100px;">予約者</span>
            <span>${meetingRoom.user.name}</span>
        </div>

        <hr>   
        
        <!-- ★修正：「戻る」と「決定」で送信先や処理を変えるための調整が必要になる場合があります -->
        <input type="submit" name="action" value="戻る">
        <input type="submit" name="action" value="決定">
    </form>

</body> <!-- ★修正：bodyの閉じタグはここ -->
</html>