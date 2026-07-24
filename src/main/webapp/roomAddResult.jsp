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
<title>会議室追加</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>
	<h1>会議室追加</h1>
	<hr>
	${message }
	<table class="margin">
	<tr><th>${tDis }</th><td>${room.id }</td></tr>
	<tr><th>会議室名</th><td>${Util.htmlSpecialChars(room.name) }</td></tr>
	</table>
	<hr>
	<a href="<%=request.getContextPath() %>/roomAdd.jsp"><input type="button" value="会議室追加へ"></a>
	<a href="<%=request.getContextPath() %>/menu.jsp"><input type="button" value="メニューへ"></a>
</body>
</html>