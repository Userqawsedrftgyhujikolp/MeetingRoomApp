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
<title>ユーザー登録エラー画面</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/style.css">
</head>
<body>
	<h1>ユーザー登録エラー</h1>
	<hr>
	<h2>登録エラー</h2>
	<div class = "error" >${ error }</div>
<table>
	<tr>
		<th>名前</th>
		<td>${useradd.name}</td>
	</tr>
	<tr>
		<th>パスワード</th>
		<td>${useradd.password}</td>
	</tr>
	<tr>
		<th>住所</th>
		<td>${useradd.address}</td>
	</tr>
</table>
	<hr>
<form action="<%=request.getContextPath()%>/login.jsp" method="post">
	<input type="submit" value="確認">
</form>
</body>
</html>