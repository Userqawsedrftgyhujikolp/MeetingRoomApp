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
    <table>
    	<tr>
    		<th>予約日</td>
    		<td>${yayaku.date}</td>
    	</tr>
    	<tr>
    		<th>会議室名</td>
    		<td>${Util.cropString(heya.name)}</td>
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
<hr>   
  <form action="<%= request.getContextPath() %>/menu.jsp"method="post">
    <input type="submit"value="完了">
  </form>
</body>
</html>